package com.clayer.sylvain.minecraftsync;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.hardware.camera2.CameraMetadata;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;
import com.dropbox.client2.session.AppKeyPair;

import java.io.File;
import java.io.FileFilter;

public class MainActivity extends AppCompatActivity {

    final static private String APP_KEY = "n1r554ne02vx7rc";
    final static private String APP_SECRET = "n8co2dn9qct7nvt";

    final static private String PREF_DB_ACCESS_TOKEN="DbAccessToken";

    // In the class declaration section:
    private DropboxAPI<AndroidAuthSession> mDBApi;

    //Preferences
    private SharedPreferences pref;

    //DB access token
    private String accessToken="";

    //File reader + zipper
    private MinecraftFileManager mMinecraftFile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // And later in some initialization function:
        AppKeyPair appKeys = new AppKeyPair(APP_KEY, APP_SECRET);
        AndroidAuthSession session = new AndroidAuthSession(appKeys);
        mDBApi = new DropboxAPI<AndroidAuthSession>(session);

        //preferences
        pref = getPreferences(MODE_PRIVATE);
        accessToken=pref.getString(PREF_DB_ACCESS_TOKEN,"");

        //update State connection
        updateStateConnection();

        // create file manager object
        mMinecraftFile = new MinecraftFileManager();

    }

    private void updateStateConnection() {
        TextView txt = (TextView) findViewById(R.id.stateConnect);

        if(accessToken==""){
            txt.setText(R.string.stateUnconnected);
            txt.setBackgroundColor(Color.RED);
        } else {
            txt.setText(R.string.stateConnected);
            txt.setBackgroundColor(Color.GREEN);
            DropboxManager.getInstance().setAccessToken(accessToken);
            DropboxManager.getInstance().setDBAPI(mDBApi);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("DbAuthLog", "onResume");
        if (mDBApi.getSession().authenticationSuccessful()) {
            try {
                // Required to complete auth, sets the access token on the session
                mDBApi.getSession().finishAuthentication();

                accessToken = mDBApi.getSession().getOAuth2AccessToken();
                updateStateConnection();
            } catch (IllegalStateException e) {
                Log.i("DbAuthLog", "Error authenticating", e);
            }
        }
    }

    public void btnConnectOnClick(View v){

        // MyActivity below should be your activity class name
        mDBApi.getSession().startOAuth2Authentication(MainActivity.this);
    }

    public void btnSaveOnClick(View v){
        //String filePath = mMinecraftFile.SaveGame();
        DropboxAPI.Entry existing= null;
        try {
            existing = mDBApi.metadata("/",1,null,false,null);
            Log.d("", existing.rev);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void btnViewFilesOnClick(View V){
        Intent intent = new Intent(this,FileListActivity.class);
        this.startActivity(intent);

    }

    @Override
    protected void onStop(){
        super.onStop();
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_DB_ACCESS_TOKEN,accessToken);

        editor.commit();
    }

}
