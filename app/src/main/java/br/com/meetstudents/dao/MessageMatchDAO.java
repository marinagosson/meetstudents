package br.com.meetstudents.dao;

import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

import br.com.meetstudents.application.MeetStudentsApplication;
import br.com.meetstudents.database.DatabaseHelper;
import br.com.meetstudents.model.MessagesMatch;

/**
 * Created by marinagosson on 26/05/16.
 */
public class MessageMatchDAO {

    private final String TAG = MessageMatchDAO.class.getName();

    private DatabaseHelper dataBaseHelper;
    private Dao<MessagesMatch, Integer> messageMatchDAO;

    public MessageMatchDAO() {
        this.dataBaseHelper = MeetStudentsApplication.getDataBaseHelper();
        this.messageMatchDAO = getMessageMatch();
    }

    public Dao<MessagesMatch, Integer> getMessageMatch() {
        Dao<MessagesMatch, Integer> dao;
        try {
            dao = dataBaseHelper.getMessageDao();
            return dao;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void createMessage(MessagesMatch messagesMatch) {
        try {
            this.messageMatchDAO.createIfNotExists(messagesMatch);
            Log.i(TAG, "Created new user " + messagesMatch.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<MessagesMatch> getAllMessages(int idUser) {

        try {
            QueryBuilder<MessagesMatch, Integer> queryBuilder = messageMatchDAO.queryBuilder();
            PreparedQuery<MessagesMatch> preparedQuery = queryBuilder.where().eq("idUser", idUser).prepare();
            List<MessagesMatch> userList = messageMatchDAO.query(preparedQuery);
            return userList;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

}
