package br.com.meetstudents.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.meetstudents.R;
import br.com.meetstudents.UsersMatchActivity;
import br.com.meetstudents.dao.MessageMatchDAO;
import br.com.meetstudents.model.MessagesMatch;
import br.com.meetstudents.model.User;

public class MessageChatAdapter extends RecyclerView.Adapter<MessageChatAdapter.MessageViewHolver> implements View.OnClickListener {

    private Context context;
    private List<MessagesMatch> messagesMatches;

    public MessageChatAdapter(Context context, int idUser) {
        this.context = context;
        this.messagesMatches = new MessageMatchDAO().getAllMessages(idUser);
        ordenarLista();
    }


    public void ordenarLista(){
        for (int i = 0; i < messagesMatches.size() - 1; ++i) {
            MessagesMatch tmp = messagesMatches.get(i);
            for (int j = i + 1; j < messagesMatches.size(); ++j) {
                MessagesMatch tmp2 = messagesMatches.get(j);
                if (tmp.getId().compareTo(tmp2.getId()) < 0) {
                    MessagesMatch temp = tmp;
                    messagesMatches.set(i, tmp2);
                    messagesMatches.set(j, temp);
                }
            }
        }
    }

    @Override
    public MessageViewHolver onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        MessageViewHolver holder = new MessageViewHolver(context, view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MessageViewHolver holder, int position) {
        MessagesMatch user = messagesMatches.get(position);
        holder.message.setText(user.getMessage());
    }

    public void addMessage(MessagesMatch messagesMatch) {
        messagesMatches.add(messagesMatch);
        ordenarLista();
    }


    @Override
    public int getItemCount() {
        if (messagesMatches == null)
            return 0;
        return messagesMatches.size();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.relative_user_match) {
            User user = (User) v.getTag();
            ((UsersMatchActivity) context).onClickUserMatch(user);
        }

    }

    public class MessageViewHolver extends RecyclerView.ViewHolder {

        private Context context;
        private TextView message;


        public MessageViewHolver(Context context, View itemView) {
            super(itemView);
            this.context = context;
            this.message = (TextView) itemView.findViewById(R.id.text_message);

        }
    }
}
