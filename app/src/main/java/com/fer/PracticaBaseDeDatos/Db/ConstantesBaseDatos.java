package com.fer.PracticaBaseDeDatos.Db;

/**
 * Created by me on 4/01/18.
 */

public class ConstantesBaseDatos {
/*Aunque no sería necesario aqui declararemos todas nuestras constantes, para tener el código más
* ordenado y fácilmente mantenible y escalable (bastaría con modificar este archivo)*/

    //Constantes de los campos o columnas de base de datos mascotas_db y las dos tablas que contendrá:
    public static final String DATABASE_NAME="mascotas_db";
    public static final int DATABASE_VERSION=1;

    //la tablas se llamarán tabla_mascotas y tabla_ultimas_mascotas tb definimos los nombres de sus columnas:
    public static final String TABLE_MASCOTAS="tabla_mascotas";                    //NOMBRE DE LA PRIMERA TABLA
    public static final String TABLE_ULTIMAS_MASCOTAS="tabla_ultimas_mascotas";    //NOMBRE DE LA SEGUNDA TABLA

    public static final String CAMPO_ID_AUTOINCREMENTADO="id_autoincrementado";
    public static final String CAMPO_ID="id";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_FOTO="foto";
    public static final String CAMPO_NUMLIKES = "numerolikes";


    //A tabla_ultimas_mascotas le vamos a añadir también un ultimo campo "timestamp":
    public static final String CAMPO_TIMESTAMP = "timestamp";











}
