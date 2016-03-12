package com.coupang.MOBILE005.hwangpang;

import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by hwang on 2016-02-04.
 */
public class PhotoViewerAdapter extends ArrayAdapter<PhotoViewer> {
    ArrayList<PhotoViewer> PhotoViewList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public PhotoViewerAdapter(Context context, int resource, ArrayList<PhotoViewer> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        PhotoViewList = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.tvName = (TextView) v.findViewById(R.id.tvName);
            holder.tvWidth = (TextView) v.findViewById(R.id.tvWidth);
            holder.tvHeight = (TextView) v.findViewById(R.id.tvHeight);
            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvDay = (TextView) v.findViewById(R.id.tvDay);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tvName.setText(PhotoViewList.get(position).getName());
        if ((PhotoViewList.get(position).getCountry().length() > 0) && (PhotoViewList.get(position).getHeight().length() > 0)) {
            holder.tvWidth.setText(PhotoViewList.get(position).getCountry() + "X" + PhotoViewList.get(position).getHeight());
        }
        holder.imageview.setImageResource(R.drawable.ic_launcher);
        holder.tvHeight.setText(PhotoViewList.get(position).getImage());
        holder.tvDay.setText(PhotoViewList.get(position).getDob());
        return v;

    }

    static class ViewHolder {

        public TextView tvName;
        public TextView tvWidth;
        public TextView tvHeight;
        public ImageView imageview;
        public TextView tvDay;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }

    }
}
