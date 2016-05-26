package br.com.meetstudents.dao;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import br.com.meetstudents.application.MeetStudentsApplication;
import br.com.meetstudents.database.DatabaseHelper;
import br.com.meetstudents.model.User;

/**
 * Created by marinagosson on 26/05/16.
 */
public class UserDAO {

    private final String TAG = UserDAO.class.getName();

    private DatabaseHelper dataBaseHelper;
    private Dao<User, Integer> userDAO;

    public UserDAO() {
        this.dataBaseHelper = MeetStudentsApplication.getDataBaseHelper();
        this.userDAO = getUserDao();
    }

    public Dao<User, Integer> getUserDao() {
        Dao<User, Integer> dao;
        try {
            dao = dataBaseHelper.getUserDao();
            return dao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createUser(User user) {
        try {
            this.userDAO.createIfNotExists(user);
            Log.i(TAG, "Created new user " + user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user) {
        try {
            this.userDAO.update(user);
            Log.i(TAG, "Update user " + user.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createListUser(List<User> userList) {
        try {
            for (User user : userList) {
                userDAO.createIfNotExists(user);
                Log.i(TAG, "Created new user " + user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUser() {

        try {
            List<User> userList = this.userDAO.queryForAll();
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public List<User> getAllUserNotLike() {

        try {
            QueryBuilder<User, Integer> queryBuilder = userDAO.queryBuilder();
            PreparedQuery<User> preparedQuery = queryBuilder.where().eq("isILike", false).prepare();
            List<User> userList = userDAO.query(preparedQuery);
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
