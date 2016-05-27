package br.com.meetstudents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import br.com.meetstudents.adapter.MessageChatAdapter;
import br.com.meetstudents.dao.MessageMatchDAO;
import br.com.meetstudents.model.MessagesMatch;
import br.com.meetstudents.model.User;

public class ChatActivity extends AppCompatActivity {

    private EditText editTextMessage;
    private RecyclerView recyclerView;
    private MessageChatAdapter messageChatAdapter;
    private User user = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            user = (User) bundle.get("userExtra");
        }

        recyclerView = (RecyclerView) findViewById(R.id.recycler_uses_mached);
        editTextMessage = (EditText) findViewById(R.id.editTextMessage);

        messageChatAdapter = new MessageChatAdapter(this, user.getId());
        recyclerView.setAdapter(messageChatAdapter);

        recyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        recyclerView.setLayoutManager(mLayoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_chat);
        if (user != null) {
            toolbar.setTitle(user.getName());
        } else {
            toolbar.setTitle("Chat");
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickSendMessage(View view) {
        MessagesMatch messagesMatch = new MessagesMatch(user.getId(), editTextMessage.getText().toString());
        new MessageMatchDAO().createMessage(messagesMatch);
        messageChatAdapter.addMessage(messagesMatch);
        messageChatAdapter.notifyDataSetChanged();
        editTextMessage.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
