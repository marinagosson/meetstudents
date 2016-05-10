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
import br.com.meetstudents.model.User;

public class MainActivity extends AppCompatActivity {

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

        updateListUses();

        usersAdapter = new UsersAdapter(this, users);

        flingContainer.setAdapter(usersAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                users.remove(0);
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                Toast.makeText(MainActivity.this, "Left!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                Toast.makeText(MainActivity.this, "Right!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                updateListUses();
                usersAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
            }
        });

        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                Toast.makeText(MainActivity.this, "Clicked!", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void updateListUses() {
        users.add(new User("Brielle Walker", "https://randomuser.me/api/portraits/women/12.jpg"));
        users.add(new User("Milja Lammi ", "https://randomuser.me/api/portraits/women/23.jpg"));
        users.add(new User("Charlie Bronw ", "https://randomuser.me/api/portraits/women/24.jpg"));
        users.add(new User("Andrea Gordon", "https://randomuser.me/api/portraits/women/7.jpg"));
        users.add(new User("Patricia Torres", "https://randomuser.me/api/portraits/women/2.jpg"));
        users.add(new User("Brooke Montgomery", "https://randomuser.me/api/portraits/women/82.jpg"));
        users.add(new User("Nádia Teixeira", "https://randomuser.me/api/portraits/women/11.jpg"));
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

}
