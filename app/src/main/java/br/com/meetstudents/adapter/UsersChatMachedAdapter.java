package br.com.meetstudents.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.meetstudents.UsersMatchActivity;
import br.com.meetstudents.R;
import br.com.meetstudents.dao.UserDAO;
import br.com.meetstudents.model.User;

/**
 * Created by marinagosson on 27/05/16.
 */
public class UsersChatMachedAdapter extends RecyclerView.Adapter<UsersChatMachedAdapter.UsersMatchViewHolver> implements View.OnClickListener {

    private Context context;
    private List<User> listUser;

    public UsersChatMachedAdapter(Context context) {
        this.context = context;
        listUser = new UserDAO().getAllUserMatched();

    }

    @Override
    public UsersMatchViewHolver onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_user_matched, parent, false);
        UsersMatchViewHolver holder = new UsersMatchViewHolver(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UsersMatchViewHolver holder, int position) {
        User user = listUser.get(position);
        holder.nameUser.setText(user.getName());
        holder.relativeLayout.setTag(user);

        holder.relativeLayout.setOnClickListener(this);

        Picasso.with(context).load(user.getUrlPhoto()).error(R.drawable.placeholder).into(holder.imageUser);
    }

    @Override
    public int getItemCount() {
        if (listUser == null)
            return 0;
        return listUser.size();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.relative_user_match){
            User user = (User) v.getTag();
            ((UsersMatchActivity) context).onClickUserMatch(user);
        }

    }

    public interface OnClickUserMatch {
        public void onClickUserMatch(User user);
    }

    public class UsersMatchViewHolver extends RecyclerView.ViewHolder {

        private Context context;
        private ImageView imageUser;
        private TextView nameUser;
        private RelativeLayout relativeLayout;


        public UsersMatchViewHolver(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.imageUser = (ImageView) itemView.findViewById(R.id.image_user);
            this.nameUser = (TextView) itemView.findViewById(R.id.text_name_user);
            this.relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relative_user_match);

        }
    }
}
