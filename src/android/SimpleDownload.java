package dev.tribody.simpledownload;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SimpleDownload extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("download")) {
             String url = args.getString(0);
             String dest = args.getString(1);
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    download(url, dest, callbackContext);
                }
            });
            return true;
        }
        return false;
    }
    
    private void download(String url, String dest, CallbackContext callbackContext) {       
        try{
            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
            
            callbackContext.success();
            
        } catch (Exception e){
            callbackContext.error(e.toString());
        }
    }
}
