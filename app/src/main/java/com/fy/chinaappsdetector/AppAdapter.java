package com.fy.chinaappsdetector;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<AppInfo> {
    LayoutInflater layoutInflater;
    PackageManager packageManager;
    List<AppInfo> apps;

    public AppAdapter(@NonNull Context context, List<AppInfo> apps) {
        super(context, R.layout.items_layout, apps);
        layoutInflater = LayoutInflater.from(context);
        packageManager = context.getPackageManager();
        this.apps = apps;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AppInfo current = apps.get(position);
        View view = convertView;
        if(view == null){
            view = layoutInflater.inflate(R.layout.items_layout, parent, false);
        }
        TextView titleText = view.findViewById(R.id.titletextview);
        titleText.setText(current.label);
        ImageView imageView = (ImageView) view.findViewById(R.id.iconImage);
        Drawable background = current.info.loadIcon(packageManager);
        imageView.setBackground(background);

        return view;
    }
}
