package br.com.meetstudents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import br.com.meetstudents.adapter.UsersChatMachedAdapter;
import br.com.meetstudents.model.User;

public class UsersMatchActivity extends AppCompatActivity implements UsersChatMachedAdapter.OnClickUserMatch {

    private RecyclerView recyclerView;
    private UsersChatMachedAdapter usersChatMachedAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_match);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.title_activity_chat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usersChatMachedAdapter = new UsersChatMachedAdapter(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_uses_mached);
        recyclerView.setAdapter(usersChatMachedAdapter);

        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClickUserMatch(User user) {
        // DETALHES DA CONVERSA
        Intent intent = new Intent(this, ChatActivity.class);
        intent.putExtra("userExtra", user);
        startActivity(intent);
    }
}
