package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.zxing.Result;

public class QRActivity extends AppCompatActivity {

    private CodeScanner qrScanner;
    private boolean testing;
    private final int CAMERA_REQUEST_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);

        setupPermissions();
        codeScanner();
    }

    private void codeScanner() {

        testing = true;

        qrScanner = new CodeScanner(this, findViewById(R.id.scanner_view));

        qrScanner.setCamera(CodeScanner.CAMERA_BACK);
        qrScanner.setFormats(CodeScanner.ALL_FORMATS);
        qrScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        qrScanner.setScanMode(ScanMode.CONTINUOUS);
        qrScanner.setAutoFocusEnabled(true);
        qrScanner.setFlashEnabled(false);

        qrScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(testing) {
                            Toast.makeText(QRActivity.this, result.getText(), Toast.LENGTH_SHORT).show();
                            testing = false;
                        }
                    }
                });
            }
        });

        qrScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Exception error) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(QRActivity.this, "ERROR IN READ", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        qrScanner.startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();

        qrScanner.releaseResources();
    }

    private void setupPermissions () {
        int permissions = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if(permissions != PackageManager.PERMISSION_GRANTED){
            String[] permissionArray = new String[1];
            permissionArray[0] = Manifest.permission.CAMERA;

            ActivityCompat.requestPermissions(this, permissionArray, CAMERA_REQUEST_CODE);
        }
    }
}