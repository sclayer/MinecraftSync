package com.clayer.sylvain.minecraftsync;

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

    private DropboxManager(){

    }
    public List<String> getListFiles(){
        ArrayList<String> mList=new ArrayList<>();
        mList.add("item1");
        mList.add("item2");

        return mList;
    }

    public static DropboxManager getInstance(){
        if (instance==null) instance = new DropboxManager();
        return instance;
    }

}
