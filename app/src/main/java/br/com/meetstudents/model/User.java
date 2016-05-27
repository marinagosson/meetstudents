package br.com.meetstudents.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Random;

// MODELO DO USUARIO DO BANCO LOCAL

@DatabaseTable(tableName = "user")
public class User implements Serializable {

    @DatabaseField(generatedId = true, columnName = "id")
    private Integer id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "urlPhoto")
    private String urlPhoto;

    @DatabaseField(columnName = "dob")
    private String dob;

    @DatabaseField(columnName = "km")
    private String km;

    @DatabaseField(columnName = "isLikeYour")
    private boolean isLikedYour; // se usuario gosta ou nao de vc

    @DatabaseField(columnName = "isILike")
    private boolean isILike; // se voce gosta ou nao da pessoa


    public User() {

    }

    public User(String nome, String urlPhoto, boolean isLikedYour) {
        this.name = nome;
        this.urlPhoto = urlPhoto;
        this.isLikedYour = isLikedYour;
        this.isILike = false;

        Random random = new Random();
        int km = random.nextInt(20);
        int age = random.nextInt((35 - 18) + 1) + 18;

        this.dob = String.valueOf(age);
        this.km = String.valueOf(km);

    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public boolean isLikedYour() {
        return isLikedYour;
    }

    public void setIsLikedYour(boolean isLikedYour) {
        this.isLikedYour = isLikedYour;
    }

    public boolean isILike() {
        return isILike;
    }

    public void setILike(boolean ILike) {
        this.isILike = ILike;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", urlPhoto='" + urlPhoto + '\'' +
                ", isLikedYour=" + isLikedYour +
                ", isILike=" + isILike +
                '}';
    }
}
