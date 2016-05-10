package br.com.meetstudents.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import br.com.meetstudents.R;
import br.com.meetstudents.model.User;

/**
 * Created by marinagosson on 09/05/16.
 */
public class UsersAdapter extends BaseAdapter {

    private List<User> users;
    private Context context;

    public UsersAdapter(Context context, List<User> users) {
        this.context = context;
        this.users = users;
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


        View view = convertView;
        ViewHolder holder = null;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {

            view = inflater.inflate(R.layout.item, parent, false);

            holder = new ViewHolder();

            holder.textName = (TextView) view.findViewById(R.id.name_user);
            holder.textDistance = (TextView) view.findViewById(R.id.distance);
            holder.imageUser = (ImageView) view.findViewById(R.id.image_user);

            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        User user = users.get(position);

        Random random = new Random();
        int km = random.nextInt(20);
        int age = random.nextInt((35 - 18) + 1) + 18;

        holder.textDistance.setText(km + " km");
        holder.textName.setText(user.getNome() + ", " + age);

        Picasso.with(context)
                .load(getItem(position).getUrlPhoto())
                .into(holder.imageUser);

        view.setTag(user);

        return view;
    }

    public static class ViewHolder {

        TextView textName;
        TextView textDistance;
        ImageView imageUser;

    }
}
