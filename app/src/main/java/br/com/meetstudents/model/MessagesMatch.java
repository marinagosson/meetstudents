package br.com.meetstudents.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

/**
 * Created by marinagosson on 09/05/16.
 */

@DatabaseTable(tableName = "messagesMatch")
public class MessagesMatch implements Serializable {

    @DatabaseField(generatedId = true, columnName = "id")
    private Integer id;

    @DatabaseField(columnName = "idUser")
    private Integer idUser;

    @DatabaseField(columnName = "message")
    private String message;


    public MessagesMatch() {

    }

    public MessagesMatch(int idUser, String message) {
        this.idUser = idUser;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessagesMatch{" +
                "id=" + id +
                ", idUser='" + idUser + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
