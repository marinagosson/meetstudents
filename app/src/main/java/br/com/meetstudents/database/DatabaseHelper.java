package br.com.meetstudents.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.meetstudents.dao.UserDAO;
import br.com.meetstudents.model.User;

/**
 * Created by marinagosson on 26/05/16.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private final String TAG = DatabaseHelper.class.getName();

    private static final String DATABASE_NAME = "meetStudents.db";
    private static final int DATABASE_VERSION = 2;

    private Dao<User, Integer> userDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, User.class);
            Log.d(TAG, "Create database");
        } catch (SQLException e) {
            Log.d("Can't create database", e.getMessage());
            throw new RuntimeException(e);
        }

        createDataFake();
    }

    public void createDataFake() {

        Log.d(TAG, "Init create listUsers");
        List<User> users = new ArrayList<>();

        users.add(new User("Brielle Walker", "https://randomuser.me/api/portraits/women/1.jpg", false));
        users.add(new User("Milja Lammi ", "https://randomuser.me/api/portraits/women/2.jpg", false));
        users.add(new User("Charlie Bronw ", "https://randomuser.me/api/portraits/women/3.jpg", false));
        users.add(new User("Andrea Gordon", "https://randomuser.me/api/portraits/women/4.jpg", true));
        users.add(new User("Patricia Torres", "https://randomuser.me/api/portraits/women/5.jpg", false));
        users.add(new User("Brooke Montgomery", "https://randomuser.me/api/portraits/women/6.jpg", false));
        users.add(new User("Nádia Teixeira", "https://randomuser.me/api/portraits/women/7.jpg", false));

        users.add(new User("Sandra Wendt", "https://randomuser.me/api/portraits/women/8.jpg", false));
        users.add(new User("Katja Baumann ", "https://randomuser.me/api/portraits/women/9.jpg", false));
        users.add(new User("Charlene Sanchez ", "https://randomuser.me/api/portraits/women/10.jpg", true));
        users.add(new User("Elizabeth White", "https://randomuser.me/api/portraits/women/11.jpg", false));
        users.add(new User("Roshni Van Embden", "https://randomuser.me/api/portraits/women/12.jpg", false));
        users.add(new User("Victoire Fontai", "https://randomuser.me/api/portraits/women/13.jpg", true));
        users.add(new User("Michèlle Ploeger", "https://randomuser.me/api/portraits/women/14.jpg", false));

        users.add(new User("Emma Harcourt", "https://randomuser.me/api/portraits/women/15.jpg", false));
        users.add(new User("Eva Laurent ", "https://randomuser.me/api/portraits/women/16.jpg", false));
        users.add(new User("Hannah King ", "https://randomuser.me/api/portraits/women/17.jpg", true));
        users.add(new User("Diana Marshall", "https://randomuser.me/api/portraits/women/18.jpg", false));
        users.add(new User("Ceylan Babaoglu", "https://randomuser.me/api/portraits/women/19.jpg", false));
        users.add(new User("Emily Torres", "https://randomuser.me/api/portraits/women/20.jpg", false));
        users.add(new User("Ege Eskola", "https://randomuser.me/api/portraits/women/21.jpg", false));

        users.add(new User("Emilia Peña", "https://randomuser.me/api/portraits/women/22.jpg", false));
        users.add(new User("Sue Pearson ", "https://randomuser.me/api/portraits/women/23.jpg", true));
        users.add(new User("Thea Mortensen ", "https://randomuser.me/api/portraits/women/24.jpg", false));
        users.add(new User("Larke Rasmussen", "https://randomuser.me/api/portraits/women/25.jpg", true));
        users.add(new User("Maëly Bertrand", "https://randomuser.me/api/portraits/women/26.jpg", false));
        users.add(new User("Geke Verhoeve", "https://randomuser.me/api/portraits/women/27.jpg", true));
        users.add(new User("Alexandra Ward", "https://randomuser.me/api/portraits/women/28.jpg", false));

        new UserDAO().createListUser(users);
        Log.d(TAG, "Finish create listUsers");
    }

    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, User.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.d("Can't drop databases", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public Dao<User, Integer> getUserDao() throws SQLException {
        if (userDao == null) {
            userDao = getDao(User.class);
        }
        return userDao;
    }


    @Override
    public void close() {
        super.close();
        userDao = null;
    }

}
