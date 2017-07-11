package com.zm.userpicasso;

import android.content.Context;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.StatsSnapshot;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yuzhimin on 17-6-27.
 */

public class ImagelistAdapter extends BaseAdapter {
    private static final String TAG = "ImagelistAdapter";
    private Context mcontext;
    ArrayList<String> list = new ArrayList<>();
    public ImagelistAdapter(Context context, ArrayList<String> getlist) {
        Log.e(TAG,"currentThread ID = " + Thread.currentThread().getId());
        this.mcontext = context;
        this.list = getlist;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    boolean isMain() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder =new ViewHolder();
            view = LayoutInflater.from(mcontext).inflate(R.layout.list_item, null);
            viewHolder.imagelist = (ImageView) view.findViewById(R.id.item_img);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        //Log.e(TAG,"isMain = " + isMain());

        //最简单的加载方式，load里面为图片的url（当然也可以是本地资源啊什么的），into进image控件里面去。
        Picasso singleton;
        singleton = Picasso.with(mcontext);
        singleton.setIndicatorsEnabled(true);
        singleton.setLoggingEnabled(true);
        //正常加载
        //StatsSnapshot picassoStats = singleton.getSnapshot();
        //singleton.load(list.get(position)).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).fit().into(viewHolder.imagelist);

        //设置 非渐显 .noFade()
        //singleton.load(list.get(position)).noFade().placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round).fit().into(viewHolder.imagelist);

        // 设置 .noPlaceholder
        //singleton.load(list.get(position)).noFade().noPlaceholder().error(R.mipmap.ic_launcher_round).fit().into(viewHolder.imagelist);

        //加载会有空洞 如网络加载图片等网络错误情况下
        //singleton.load(list.get(position)).fit().into(viewHolder.imagelist);

        // 旋转 .rotate()
        //singleton.load(list.get(position)).rotate(70f,122f,220f)
        //        .into(viewHolder.imagelist); // issue FATAL Exception
        
        //singleton.load(list.get(position)).
        // resize(200,200).rotate(70f,122f,220f).into(viewHolder.imagelist); // Normal
        //singleton.load(list.get(position)).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher)
        //        .rotate(60f,100f,200f).into(viewHolder.imagelist);

        // 自适应 .fit()
        //singleton.load(list.get(position)).fit().into(viewHolder.imagelist);
        //singleton.load(list.get(position)).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).fit().into(viewHolder.imagelist);

        //使用.fetch() 方法异步加载。
        //singleton.load(list.get(position)).fetch();

        //使用.get() 方法同步加载，在main线程中会Crash
        /*try {
            singleton.load(list.get(position)).get();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //变换（Transformation) 可自定义
        //singleton.load(list.get(position)).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round)
        //        .fit().transform(new BlurTransformation(mcontext)).into(viewHolder.imagelist);


        //Network Policy Policy 的使用
        //singleton.load(list.get(position)).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round)
        //        .fit().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE).into(viewHolder.imagelist);
        //Memory Policy 的使用
        //singleton.load(list.get(position)).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher_round)
        //        .fit().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(viewHolder.imagelist);
        //StatsSnapshot picassoStats = singleton.getSnapshot();
        //Log.d("Picasso Stats", picassoStats.toString());

        return view;
    }
    class ViewHolder {
        ImageView imagelist;
    }
}
