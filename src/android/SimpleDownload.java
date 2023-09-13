package dev.tribody.simpledownload;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.LOG;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class SimpleDownload extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("download")) {           
            cordova.getThreadPool().execute(new Runnable() {
                @Override
                public void run() {
                    download(args, callbackContext);
                }
            });
            return true;
        }
        return false;
    }
    
    private void download(JSONArray args, CallbackContext callbackContext) {       
        try{
            String url = args.getString(0);
            String dir = args.getString(1);
            String name = args.getString(2);
            if(!dir.endsWith("/")) {
                dir = dir + "/";
            }
            File file = new File(dir);
            if(!file.exists()) {
                file.mkdirs();
            }
            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(dir + name), StandardCopyOption.REPLACE_EXISTING);            
            callbackContext.success();
        } catch (Exception e){
            callbackContext.error(e.toString());
        }
    }
}
