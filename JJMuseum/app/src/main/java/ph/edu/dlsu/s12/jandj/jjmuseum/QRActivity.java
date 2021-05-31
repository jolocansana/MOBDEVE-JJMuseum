package ph.edu.dlsu.s12.jandj.jjmuseum;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.Result;

import java.lang.reflect.Type;
import java.util.ArrayList;

import ph.edu.dlsu.s12.jandj.jjmuseum.model.Piece;
import ph.edu.dlsu.s12.jandj.jjmuseum.utils.jsonParser;

public class QRActivity extends AppCompatActivity {

    private CodeScanner qrScanner;
    private String recentScannedString;
    private final int CAMERA_REQUEST_CODE = 101;

    private ArrayList<Piece> pieceArrayList;
    private boolean isValid;

    /*
     *  onCreate
     *  function called on creation of the activity (as seen in Activity Lifecycle)
     *  Bundle savedInstanceState - reference to the Bundle passed on to activity
     *  void
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r);

        init();

        setupPermissions();
        codeScanner();
    }

    /*
     *  init
     *  initializes XML references and gets data from json files using Gson()
     *  void
     */
    private void init() {

        isValid = false;

        String piecesJSONString = jsonParser.getJsonFromAssets(getApplicationContext(), "piecesdata.json");
        Log.d("JSON", piecesJSONString);

        Gson gson = new Gson();
        Type pieceType = new TypeToken<ArrayList<Piece>>(){}.getType();

        pieceArrayList = new ArrayList<>();
        pieceArrayList = gson.fromJson(piecesJSONString, pieceType);
    }

    /*
     *  codeScanner
     *  Function called to scan QR code from Camera
     *  void
     */
    private void codeScanner() {

        recentScannedString = "";

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
                        if(!recentScannedString.equals(result.getText())) {
                            recentScannedString = result.getText();
                            for (Piece piece : pieceArrayList) {
                                if(piece.getID().equals(recentScannedString)) {
                                    Intent PieceActivity = new Intent(getApplicationContext(), PieceActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("ID", piece.getID());
                                    bundle.putString("Name", piece.getName());
                                    bundle.putString("Collection", piece.getCollection());
                                    bundle.putString("CollectionID", piece.getCollectionID());
                                    bundle.putString("Time", piece.getTime());
                                    bundle.putString("Description", piece.getDescription());
                                    bundle.putStringArrayList("Assets", piece.getAssets());
                                    PieceActivity.putExtras(bundle);
                                    startActivity(PieceActivity);
                                    finish();
                                    isValid = true;
                                }
                            }
                            if(!isValid) Toast.makeText(QRActivity.this, "Invalid QR Code", Toast.LENGTH_SHORT).show();
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

    /*
     *  onResume
     *  Lifecycle function to start QR Scanning on resume of activity
     *  void
     */
    @Override
    protected void onResume() {
        super.onResume();

        qrScanner.startPreview();
    }

    /*
     *  onPause
     *  Lifecycle function to stop and release resources for QR Scanning on pause of activity
     *  void
     */
    @Override
    protected void onPause() {
        super.onPause();

        qrScanner.releaseResources();
    }

    /*
     *  setupPermissions
     *  Checks if the app has valid permissions to use the camera for QR Code Scanning
     *  void
     */
    private void setupPermissions () {
        int permissions = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);

        if(permissions != PackageManager.PERMISSION_GRANTED){
            String[] permissionArray = new String[1];
            permissionArray[0] = Manifest.permission.CAMERA;

            ActivityCompat.requestPermissions(this, permissionArray, CAMERA_REQUEST_CODE);
        }
    }
}