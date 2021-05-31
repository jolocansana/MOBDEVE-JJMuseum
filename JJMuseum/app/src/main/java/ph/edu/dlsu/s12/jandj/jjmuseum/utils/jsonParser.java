package ph.edu.dlsu.s12.jandj.jjmuseum.utils;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class jsonParser {
    /*
     * getJsonFromAssets
     * coverts the data from the Json File to jsonString String
     * Context context - context of the current activity
     * String fileName - name of the file to parse
     * String jsonString containing the data from the JsonFile
     */
    public static String getJsonFromAssets(Context context, String fileName) {
        String jsonString;
        try {
            InputStream is = context.getAssets().open(fileName);

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            jsonString = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return jsonString;
    }
}
