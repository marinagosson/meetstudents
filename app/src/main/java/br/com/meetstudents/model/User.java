package br.com.meetstudents.model;

/**
 * Created by marinagosson on 09/05/16.
 */
public class User {

    private String nome;
    private String idade;
    private String distancia;
    private String urlPhoto;


    public User(String nome, String idade, String distancia, String urlPhoto) {
        this.nome = nome;
        this.idade = idade;
        this.distancia = distancia;
        this.urlPhoto = urlPhoto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
