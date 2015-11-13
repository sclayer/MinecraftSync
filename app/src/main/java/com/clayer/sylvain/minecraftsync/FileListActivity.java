package com.clayer.sylvain.minecraftsync;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class FileListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        // list file from dropbox
        ListView list = (ListView) this.findViewById(R.id.fileListView);

       FileListAdapter adapter = new FileListAdapter(this,DropboxManager.getInstance().getListFiles());

        list.setAdapter(adapter);




    }
}
