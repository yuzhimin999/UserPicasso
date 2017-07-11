package com.zm.userpicasso;

/**
 * Created by yuzhimin on 17-6-29.
 */

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

public class SampleGridViewActivity extends PicassoSampleActivity {
    private static final String TAG = "SampleGridViewActivity";
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_gridview_activity);

        GridView gv = (GridView) findViewById(R.id.grid_view);
        gv.setAdapter(new SampleGridViewAdapter(this));
        gv.setOnScrollListener(new SampleScrollListener(this));
        Log.e(TAG,"onCreate");
    }
}
