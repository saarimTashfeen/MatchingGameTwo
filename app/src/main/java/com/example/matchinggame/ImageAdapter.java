package com.example.matchinggame;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {


    private Context context;

    public ImageAdapter(Context context){

        this.context = context;

    }


    @Override
    public int getCount() {
        return 20; //Returns the amount of image squares needed in alignment to grid
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }




    //@Override
    public View getView( int position, View convertView, ViewGroup parent) {

        ImageView imView;


        if(convertView == null){

            imView = new ImageView(this.context);
            imView.setLayoutParams(new GridView.LayoutParams(250,250));
            imView.setScaleType(ImageView.ScaleType.FIT_XY);

        } else

            imView = (ImageView)convertView;
            imView.setImageResource(R.drawable.qmark);





        return imView;
    }
}
