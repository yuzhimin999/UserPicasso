package com.zm.userpicasso;

/**
 * Created by yuzhimin on 17-6-29.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.support.v7.widget.AppCompatImageView;

/** An image view which always remains square with respect to its width. */
public final class SquaredImageView extends AppCompatImageView {
    public SquaredImageView(Context context) {
        super(context);
    }

    public SquaredImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth());
    }
}
