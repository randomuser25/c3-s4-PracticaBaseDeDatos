package com.fer.PracticaBaseDeDatos.pojo;

/**
 * Created by me on 21/12/17.
 */

public class Mascota { //Clase Mascota, a partir de la cual crearemos nuevas mascotas que formaran nuestro dataset (conjunto de datos)

    private String nombre_mascota;
    private int id_mascota;      //OJO UN STRING, NO UN INT! de ahí me venía el error q me daba
    private int foto_mascota;

    private int numero_likes_mascota;

    //######### VAMOS A CREAR UN CONSTRUCTOR VACÍO A VER QUÉ PASA ######################

        //????????????????

    //##################################################################################

    //MÉTODO CONSTRUCTOR:
    //(foto será un int, ya que al constructor le pasaremos posteriormente un recurso mapeado en R)
    public Mascota(int id, String nombre, int foto, int numlikes){   //método constructor de objetos mascota

        this.id_mascota = id;
        this.nombre_mascota = nombre;
        this.foto_mascota = foto;

        this.numero_likes_mascota = numlikes;
    }

    //CORRESPONDIENTES GETTERS AND SETTERS:

    public String getNombre_mascota() {

        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {

        this.nombre_mascota = nombre_mascota;
    }

    public int getId_mascota() {

        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getFoto_mascota() {
        return foto_mascota;
    }

    public void setFoto_mascota(int foto_mascota) {
        this.foto_mascota = foto_mascota;
    }

    //Para el número de likes de cada mascota:

    public int getNumeroLikes_mascota() {
        return numero_likes_mascota;
    }

    public void setNumero_likes_mascota(int numero_likes_mascota) {
        this.numero_likes_mascota = numero_likes_mascota;
    }


}
