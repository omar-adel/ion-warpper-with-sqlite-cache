package com.freelanceapps33.weather.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.freelanceapps33.weather.R;
import com.freelanceapps33.weather.models.Weather;
import java.util.ArrayList;


public class WeatherAdapter extends BaseAdapter {

    public ArrayList<Weather> weatherArrayList = new ArrayList<Weather>();

    Activity activity;
    LayoutInflater inflater;

    public WeatherAdapter(Activity activity, ArrayList<Weather> weatherArrayList) {
        this.activity = activity;
        this.weatherArrayList = weatherArrayList;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return weatherArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final int pos = position;
        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_weather, null);
            holder = new ViewHolder();
            convertView.setTag(holder);

            holder.imgvIcon = (ImageView) convertView.findViewById(R.id.imgvIcon);
            holder.txtvDay = (TextView) convertView.findViewById(R.id.txtvDay);
            holder.txtvDate = (TextView) convertView.findViewById(R.id.txtvDate);
            holder.txtvHighTempValue = (TextView) convertView.findViewById(R.id.txtvHighTempValue);
            holder.txtvLowTempValue = (TextView) convertView.findViewById(R.id.txtvLowTempValue);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(holder.imgvIcon);
        Glide.with(activity).load(weatherArrayList.get(pos).getIcon_url()).placeholder(R.mipmap.ic_launcher).into(imageViewTarget);

        holder.txtvDay.setText(weatherArrayList.get(pos).getWeekday());
        holder.txtvDate.setText(weatherArrayList.get(pos).getDay() + " " + weatherArrayList.get(pos).getMonthname()
                + " " + weatherArrayList.get(pos).getYear());
        holder.txtvHighTempValue.setText(weatherArrayList.get(pos).getHigh_celsius());
        holder.txtvLowTempValue.setText(weatherArrayList.get(pos).getLow_celsius());

        return convertView;
    }

    public class ViewHolder {
        public ImageView imgvIcon;
        public TextView txtvDay, txtvDate, txtvHighTempValue, txtvLowTempValue;
    }

}
