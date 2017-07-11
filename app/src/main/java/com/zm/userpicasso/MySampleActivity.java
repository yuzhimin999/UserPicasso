package com.zm.userpicasso;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.zm.testjava.reference.TestReference;

import java.util.ArrayList;

//import static com.squareup.picasso.MemoryPolicy.shouldWriteToMemoryCache;

public class MySampleActivity extends AppCompatActivity {
    private static final String TAG = "MySampleActivity";
    private ListView imagelist;
    private ImagelistAdapter adapter;
    //放图片资源的集合
    private ArrayList<String> list =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imagelist= (ListView) findViewById(R.id.imagelist);
        list.add("http://h.hiphotos.baidu.com/image/w%3D310/sign=53d5f82103e9390156028b3f4bec54f9/574e9258d109b3deb38ea469cebf6c81800a4cf9.jpg");
        list.add("http://c.hiphotos.baidu.com/image/w%3D310/sign=4bebf5c06e81800a6ee58f0f813433d6/dfgfgfghfdgfgdfgadf45234534545.jpg");
        list.add("https://images-assets.nasa.gov/image/PIA08653/PIA08653~orig.jpg");
        list.add("http://c.hiphotos.baidu.com/image/w%3D310/sign=1214242166380cd7e61ea4ec9145ad14/ae51f3deb48f8c5436841ebe39292df5e1fe7fc8.jpg");
        list.add("http://c.hiphotos.baidu.com/image/w%3D310/sign=4bebf5c06e81800a6ee58f0f813433d6/7c1ed21b0ef41bd537dde4bb53da81cb38db3df6.jpg");
        list.add("http://c.hiphotos.baidu.com/image/w%3D310/sign=4bebf5c06e81800a6ee58f0f813433d6/4514523452345fdfdferqwetqerter.jpg");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.e(TAG, "currentThread is " + String.valueOf(Thread.currentThread()));
                adapter=new ImagelistAdapter(MySampleActivity.this,list);
                imagelist.setAdapter(adapter);
            }
        }).start();
        RecyclerView mRecyclerView;
        MutliThread m1=new MutliThread("Window 1");
        MutliThread m2=new MutliThread("Window 2");
        MutliThread m3=new MutliThread("Window 3");
        Thread t1=new Thread(m1);
        Thread t2=new Thread(m2);
        Thread t3=new Thread(m3);
        t1.start();
        t2.start();
        t3.start();
        //TestReference.Test();
        //boolean b = shouldWriteToMemoryCache(1);

    }

    public class MutliThread implements Runnable{
        StringBuilder strbl;
        StringBuffer strbf;
        private String name;
        MutliThread(String name){
            this.name=name;
        }
        public void run(){

            Log.e(TAG, "currentThread is " + String.valueOf(Thread.currentThread()));
        }
    }
}
