package br.com.meetstudents.model;

/**
 * Created by marinagosson on 09/05/16.
 */
public class User {

    private String nome;
    private String urlPhoto;


    public User(String nome, String urlPhoto) {
        this.nome = nome;
        this.urlPhoto = urlPhoto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
