package com.clayer.sylvain.minecraftsync;

import android.util.Log;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by sylvain on 15/10/15.
 */
public class MinecraftFileManager implements FileFilter {

    private String minecraftPath="/sdcard/games/com.mojang";


    public String SaveGame() {
        Log.i("MinecraftFileManager", "SaveGame");
        File fs = new File(minecraftPath);
        //fs.listFiles(this);
        explore(fs);

        return null;
    }

    private void explore(File fs) {
        for (File st : fs.listFiles()
             ) {
            if (st.isDirectory()){
                explore(st);
            } else {
                Log.i("", st.getAbsolutePath());
            }
        }
    }

    @Override
    public boolean accept(File pathname) {
        Log.i("",pathname.getAbsolutePath());
        return true;
    }
}
