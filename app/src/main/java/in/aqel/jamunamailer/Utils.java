package in.aqel.jamunamailer;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ahammad on 22/01/17.
 */

public class Utils {

    private static String sp = "prefs";
    public static String spIsConfig = "config";

    public static void setBoolean(Context context, String key, Boolean option){
        SharedPreferences preferences = context.getSharedPreferences(sp, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, option);
        editor.commit();
    }

    public static boolean checkBoolean(Context context, String key){
        SharedPreferences pref = context.getSharedPreferences(sp, Context.MODE_PRIVATE);
        return pref.getBoolean(key, false);
    }

    public static String loadJSONFromAsset(Context context, String file) {
        String json = null;
        try {

            InputStream is = context.getAssets().open(file);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;

    }

}
