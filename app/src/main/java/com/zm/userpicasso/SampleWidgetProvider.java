package com.zm.userpicasso;

/**
 * Created by yuzhimin on 17-6-29.
 */

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.widget.RemoteViews;
import com.squareup.picasso.Picasso;
import java.util.Random;

public class SampleWidgetProvider extends AppWidgetProvider {
    private static final String TAG = "SampleWidgetProvider";
    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        Log.e(TAG,"onUpdate");
        RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.sample_widget);
        // Load image for all appWidgetIds.
        Picasso picasso = Picasso.with(context);
        picasso.load(Data.URLS[new Random().nextInt(Data.URLS.length)]) //
                .placeholder(R.drawable.placeholder) //
                .error(R.drawable.error) //
                .transform(new GrayscaleTransformation(picasso)) //
                .into(updateViews, R.id.image, appWidgetIds);
    }
}
