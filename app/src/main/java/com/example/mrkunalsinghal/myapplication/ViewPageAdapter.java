package com.example.mrkunalsinghal.myapplication;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Mr kunal singhal on 27-Jul-17.
 */
public class ViewPageAdapter extends PagerAdapter {
private Context context;
private LayoutInflater layoutInflater;
private Integer []images={R.drawable.haaaa,R.drawable.haaaaa,R.drawable.healthtip,R.drawable.haaaa,R.drawable.haaaaa,R.drawable.healthtip,R.drawable.haaaa,R.drawable.haaaaa,R.drawable.healthtip};

    public ViewPageAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return  view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(images[position]);
        ((ViewPager)container).addView(imageView,0);
        return imageView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager)container).removeView((ImageView)object);
    }
}

