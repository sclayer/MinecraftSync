package com.clayer.sylvain.minecraftsync;

import android.os.DropBoxManager;
import android.util.Log;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.exception.DropboxException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sylvain on 12/11/15.
 * Get list of dropbox files
 * upload files on dropbox
 * manage save on dropbox folder
 */
public class DropboxManager {

    private static DropboxManager instance=null;
    private String accessToken;
    private DropboxAPI<AndroidAuthSession> mDBAPI;

    private DropboxManager(){

    }
    public List<String> getListFiles(){

        mDBAPI.getSession().setOAuth2AccessToken(accessToken);
        ArrayList<String> mList=new ArrayList<>();

        try {
            DropboxAPI.Entry existing=mDBAPI.metadata("/",1,null,false,null);
            for (DropboxAPI.Entry e : existing.contents
                    ) {
                Log.d("", e.fileName());
                //mList.add(e.fileName());
            }
        } catch (DropboxException e) {
            e.printStackTrace();
        }


       mList.add("item1");
        mList.add("item2");

        return mList;
    }

    public static DropboxManager getInstance(){
        if (instance==null) instance = new DropboxManager();
        return instance;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setDBAPI(DropboxAPI<AndroidAuthSession> DBAPI) {
        this.mDBAPI = DBAPI;
    }
}
