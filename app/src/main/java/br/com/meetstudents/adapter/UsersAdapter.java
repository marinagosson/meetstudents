package br.com.meetstudents.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.util.List;

import br.com.meetstudents.R;
import br.com.meetstudents.dao.UserDAO;
import br.com.meetstudents.model.User;

public class UsersAdapter extends BaseAdapter {

    private List<User> users;
    private Context context;
    private LayoutInflater mInflater;

    private int count;

    public UsersAdapter(Context context) {
        this.context = context;
        this.users = new UserDAO().getAllUserNotLike();
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        count = getCount();
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public int getCount() {
        if (users == null)
            return 0;
        return users.size();
    }

    public void removeItem(int position) {
        count = getCount();
        users.remove(position);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public User getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.item, parent, false);

            holder = new ViewHolder();

            holder.textName = (TextView) convertView.findViewById(R.id.name_user);
            holder.textDistance = (TextView) convertView.findViewById(R.id.distance);
            holder.imageUser = (ImageView) convertView.findViewById(R.id.image_user);
            holder.progress = (ProgressBar) convertView.findViewById(R.id.progress);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        User user = users.get(position);

        holder.textDistance.setText(user.getKm() + " km");
        holder.textName.setText(user.getName() + ", " + user.getDob());

//        new DownloadImageTask(holder.imageUser)
//                .execute(user.getUrlPhoto());

//        Uri uri = Uri.parse(user.getUrlPhoto());
//        Picasso.with(context)
//                .load(uri)
//                .error(R.drawable.placeholder)
//                .into(holder.imageUser);

        final ViewHolder finalHolder = holder;
        Picasso.with(context).load(user.getUrlPhoto()).placeholder(R.drawable.placeholder).into(holder.imageUser, new Callback() {
            @Override
            public void onSuccess() {
                finalHolder.progress.setVisibility(View.GONE);
            }

            @Override
            public void onError() {
                finalHolder.progress.setVisibility(View.GONE);
            }
        });

        convertView.setTag(user);


        return convertView;
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

    public static class ViewHolder {

        TextView textName;
        TextView textDistance;
        ImageView imageUser;
        ProgressBar progress;

    }
}
