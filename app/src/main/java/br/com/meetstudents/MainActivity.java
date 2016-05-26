package br.com.meetstudents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.List;

import br.com.meetstudents.adapter.UsersAdapter;
import br.com.meetstudents.dao.UserDAO;
import br.com.meetstudents.model.User;

public class MainActivity extends AppCompatActivity implements SwipeFlingAdapterView.onFlingListener {

    SwipeFlingAdapterView flingContainer;
    List<User> users = new ArrayList<>();
    UsersAdapter usersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        toolbar.setTitle(R.string.app_name);
        setSupportActionBar(toolbar);

        flingContainer = (SwipeFlingAdapterView) findViewById(R.id.frame);

        usersAdapter = new UsersAdapter(this);

        flingContainer.setAdapter(usersAdapter);
        flingContainer.setFlingListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_chat) {
            Intent intent = new Intent(this, ChatActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public void right() {
        flingContainer.getTopCardListener().selectRight();
    }

    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }

    @Override
    public void removeFirstObjectInAdapter() {
        Log.d("LIST", "removeFirstObjectInAdapter");
        usersAdapter.removeItem(0);
        usersAdapter.notifyDataSetChanged();

    }

    @Override
    public void onLeftCardExit(Object o) {
        Log.d("LIST", "removed object!");
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        User user = (User) dataObject;

        if (user.isLikedYour()) {
            // combinou, mostrar alert
            Toast.makeText(MainActivity.this, "Combinou!", Toast.LENGTH_SHORT).show();
        }
        user.setILike(true);
        new UserDAO().updateUser(user);
//                    }
    }

    @Override
    public void onAdapterAboutToEmpty(int i) {
        Log.d("LIST", "onAdapterAboutToEmpty");
        usersAdapter.notifyDataSetChanged();
    }

    @Override
    public void onScroll(float v) {

    }
}
