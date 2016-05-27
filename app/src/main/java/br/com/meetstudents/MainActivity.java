package br.com.meetstudents;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.meetstudents.adapter.UsersAdapter;
import br.com.meetstudents.dao.UserDAO;
import br.com.meetstudents.model.User;

public class MainActivity extends AppCompatActivity implements SwipeFlingAdapterView.onFlingListener {

    SwipeFlingAdapterView flingContainer;
    List<User> users = new ArrayList<>();
    UsersAdapter usersAdapter;

    Boolean isFirstLeft = false;
    Boolean isFirstRight = false;

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
            Intent intent = new Intent(this, UsersMatchActivity.class);
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

        if (!isFirstLeft) {
            isFirstLeft = true;
            Toast.makeText(MainActivity.this, "Não gostou", Toast.LENGTH_SHORT).show();
        }
        Log.d("LIST", "removed object!");
    }

    @Override
    public void onRightCardExit(Object dataObject) {
        User user = (User) dataObject;

        if (!isFirstRight) {
            isFirstRight = true;
            Toast.makeText(MainActivity.this, "Gostou", Toast.LENGTH_SHORT).show();
        }

        if (user.isLikedYour()) {
            // combinou, mostrar alert
            alert(user);
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

    public void onClickLeft(View view) {

        if (!isFirstLeft) {
            isFirstLeft = true;
            Toast.makeText(MainActivity.this, "Não gostou", Toast.LENGTH_SHORT).show();
        }

        flingContainer.getTopCardListener().selectLeft();
    }

    public void onClickRight(View view) {

        if (!isFirstRight) {
            isFirstRight = true;
            Toast.makeText(MainActivity.this, "Gostou", Toast.LENGTH_SHORT).show();
        }
        flingContainer.getTopCardListener().selectRight();
    }

    public void alert(final User user) {


        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_matched);

        final TextView vcEFulanaSeCombinam = (TextView) dialog.findViewById(R.id.voce_e_fulana_se_curtiram);
        final ImageView imagemDeQuemCombinouComVc = (ImageView) dialog.findViewById(R.id.imagem_quem_vc_combinou);
        final Button mandarMensagem = (Button) dialog.findViewById(R.id.mandar_uma_mensagem);
        final Button continuarProcurando = (Button) dialog.findViewById(R.id.continuar_procurando);

        vcEFulanaSeCombinam.setText("Você e " + user.getName() + " se curtiram.");

        Picasso.with(getApplicationContext()).load(user.getUrlPhoto()).error(R.drawable.placeholder).into(imagemDeQuemCombinouComVc);

        mandarMensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra("userExtra", user);
                startActivity(intent);
                dialog.dismiss();
            }
        });

        continuarProcurando.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }
}
