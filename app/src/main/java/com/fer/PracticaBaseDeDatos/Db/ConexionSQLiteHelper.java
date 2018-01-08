package com.fer.PracticaBaseDeDatos.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.fer.PracticaBaseDeDatos.pojo.Mascota;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static java.text.DateFormat.DEFAULT;
import static javax.xml.datatype.DatatypeConstants.DATETIME;

/**
 * Created by me on 4/01/18.
 */

//Esta clase será la que nos ayude a crear y gestionar las bases de datos que creemos que hereda de SQLiteOpenHelper:
public class ConexionSQLiteHelper extends SQLiteOpenHelper {

    Context context;

    //Al llamar a este constructor se creará nuestra base de datos y automáticamente llamará a onCreate
    public ConexionSQLiteHelper(Context context) {
        super(context, ConstantesBaseDatos.DATABASE_NAME, null, ConstantesBaseDatos.DATABASE_VERSION);
        this.context = context;
    }

    //onCreate se llama automáticamente al llamar al constructor, recibe automáticamente la base de datos creada (db)
    //y generará la tabla o tablas de nuestra base de datos:
    @Override
    public void onCreate(SQLiteDatabase db) {

        /*sentencia necesaria para crear nuestra tabla; incluye el nombre de la tabla (tabla_mascotas) y los
        nombres de las columnas acompañadas de su tipo de dato. Mirar lo de TEXT NOT NULL:*/



        //CREA LA tabla_mascotas SÓLO SI NO EXISTE:
        String queryCrearTablaMascotas="CREATE TABLE IF NOT EXISTS "+ConstantesBaseDatos.TABLE_MASCOTAS+" ("
                +ConstantesBaseDatos.CAMPO_ID_AUTOINCREMENTADO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConstantesBaseDatos.CAMPO_ID+" INTEGER, "
                +ConstantesBaseDatos.CAMPO_NOMBRE+" TEXT, "
                +ConstantesBaseDatos.CAMPO_FOTO+" INTEGER, "
                +ConstantesBaseDatos.CAMPO_NUMLIKES+" INTEGER" + ")";


        /*
        //CREA LA tabla_ultimas_mascotas SÓLO SI NO EXISTE:
        String queryCrearTablaUltimasMascotas="CREATE TABLE IF NOT EXISTS "+ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS+" ("
                +ConstantesBaseDatos.CAMPO_ID_AUTOINCREMENTADO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConstantesBaseDatos.CAMPO_ID+" INTEGER, "
                +ConstantesBaseDatos.CAMPO_NOMBRE+" TEXT, "
                +ConstantesBaseDatos.CAMPO_FOTO+" INTEGER, "
                +ConstantesBaseDatos.CAMPO_NUMLIKES+" INTEGER" + ")";

        */

        //CREA LA tabla_ultimas_mascotas SÓLO SI NO EXISTE y además le añadimos un campo timestamp automatico:
        String queryCrearTablaUltimasMascotas="CREATE TABLE IF NOT EXISTS "+ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS+" ("
                +ConstantesBaseDatos.CAMPO_ID_AUTOINCREMENTADO+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +ConstantesBaseDatos.CAMPO_ID+" INTEGER, "
                +ConstantesBaseDatos.CAMPO_NOMBRE+" TEXT, "
                +ConstantesBaseDatos.CAMPO_FOTO+" INTEGER, "
                +ConstantesBaseDatos.CAMPO_NUMLIKES+" INTEGER, "
                //+ConstantesBaseDatos.CAMPO_TIMESTAMP+" DATETIME DEFAULT CURRENT_TIMESTAMP" + ")";  //ojo es la hora GMT, no la local
                +ConstantesBaseDatos.CAMPO_TIMESTAMP+" DATETIME DEFAULT "+ "("+"DATETIME"+"("+"CURRENT_TIMESTAMP, 'LOCALTIME'"+")"+")"+")"; //lo pasamos a hora local
                                                                                                                                            //dudo que fuera necesario concatenar asi XD: +")"+")"+")"


        /*** NOTA SOBRE DATETIME DEFAULT CURRENT_TIMESTAMP: El caso es que la hora establecida no coincide
         * con la hora retornada por la funcion getDateTime() a la hora de actualizar, por lo que he usado la linea anterior
          */





        //sobre FOREIGN KEY: https://www.w3schools.com/sql/sql_foreignkey.asp

        //EJECUTAMOS LOS COMANDOS SQL PARA CREAR LOS CAMPOS O ESTRUCTURA DE LAS TABLAS:

        db.execSQL(queryCrearTablaMascotas);
        db.execSQL(queryCrearTablaUltimasMascotas);



    }

    //verifica si existia o no una version antigua de la db al instalar de nuevo la app por ej y actúa en consecuancia:
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Si existia una version antigua de la base de datos al reinstalar la aplicacion
        //borrala y creala de nuevo llamando a onCreate:
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS);
        onCreate(db);

        //hay por ahi una version mas en condiciones con un if

    }

    /**********************************************************************+

    //########### AHORA VAMOS A CREAR UNA SERIE DE MÉTODOS PARA INSERTAR, OBTENER LOS CONTACTOS ETC..: ###########






    public void insertarLikeContacto(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(ConstantesBaseDatos.TABLE_LIKES_CONTACT, null, contentValues); //EN LA TABLA "contacto_likes"
        db.close();
    }


    public int obtenerLikesContacto(Contacto contacto){
        int likes = 0;

        String query = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES+")" +
                " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT +
                " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "="+contacto.getId();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor registros = db.rawQuery(query, null);

        if (registros.moveToNext()){
            likes = registros.getInt(0);
        }

        db.close();

        return likes;
    }

    *********************************************************************/

    /********** COMPROBAR SI LA TABLA ESTA VACÍA  **********************************/
    //no es la solucion mas optima, pero funciona:

    public boolean estaLaTablaVacia(String nombretabla) {

        SQLiteDatabase db = this.getWritableDatabase();  //podria ser readabledatabase..
        String count = "SELECT count(*) FROM " + nombretabla;
        Cursor mcursor = db.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0) {
            return false; //tabla tiene elementos
        } else {
            return true;//tabla vacia
        }
    }

    /****************** RETORNAR EL NÚMERO DE ROWS (FILAS) EN LA TABLA  *****************/

    public long numeroDeFilas(String nombretabla){

        SQLiteDatabase db = this.getReadableDatabase();

        long resultado = DatabaseUtils.queryNumEntries(db, nombretabla);

        db.close();

        return resultado;



    }






    /********** INSERTAR UNA NUEVA MASCOTA EN LA tabla **********************************/


    public void insertarMascota(String nombretabla, ContentValues values){  //ojo recibirá un objeto ContentValues
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(nombretabla,null, values); //EN LA TABLA nombretabla
        db.close();
    }

    /********** INSERTAR UN LIKE EN LA tabla **********************************/

    public void actualizarLikeEnRowPorSuId(String nombretabla, int id, int nuevoslikes){

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, nuevoslikes);
        db.update(nombretabla, values, "id="+id, null); //seleccionamos la tabla, la row por su id (id normal, no el autoincrementado), y pasamos en values las claves de las columnas y sus nuevos valores.
        db.close();


    }

    /********** BUSCAR SI EXISTE UNA DETERMINADA ID  *************************/

    public boolean existeEstaId(String nombredelatabla, int id){

        String query = "SELECT * FROM " +nombredelatabla+ " WHERE " + "id=" + id;

        SQLiteDatabase db = this.getReadableDatabase();             //abrimos la base de datos en modo lectura
        Cursor registros = db.rawQuery(query, null);    //null = sin filtros adicionales para los datos

        if(registros.moveToFirst()){

            db.close();
            return true;//existe

        }
        db.close();
        return false;  //no existe

    }

    /********** RETORNAR LOS 5 REGISTROS MÁS ACTUALES DE LA TABLA tabla_ultimas_mascotas *********/

    public ArrayList<Mascota> obtener5UltimasMascotas() {
        ArrayList<Mascota> mascotasRecientes = new ArrayList<>();

        String query = "SELECT * FROM " + ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS +" ORDER BY timestamp DESC LIMIT 5";


        SQLiteDatabase db = this.getReadableDatabase();
        //Usamos un objeto Cursor "registros" que contendrá los resultados de la consulta:
        Cursor registros = db.rawQuery(query, null);

        while (registros.moveToNext()){                 //mientras podamos desplazarnos por el cursor con moveToNext..

            int id = registros.getInt(1);            //no empezamos en el indice 0, pq contiene el id autoincrementado
            String nombre = registros.getString(2);
            int foto = registros.getInt(3);
            int numlikes= registros.getInt(4);
            //cremos una nueva mascota con los valores recogidos de la posicion actual de cursor:
            Mascota mascotaActual = new Mascota(id,nombre,foto,numlikes);   //creamos una nueva mascota

            mascotasRecientes.add(mascotaActual);  //lo añadimos al Arraylist "mascotas"
        }
        registros.close();   //No olvidar cerrar el cursor tb
        db.close();         //NO OLVIDAR CERRAR LA CONEXIÓN! <------- OJO

        return mascotasRecientes;   //Regresamos el Arraylist con las 5 ultimas mascotas



    }

    /***************  ACTUALIZAR NUMERO DE LIKES Y TIMESTAMP    ********************/

    public void actualizarLikesyTimestampPorSuId(int id, int nuevoslikes){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES,nuevoslikes);
        values.put(ConstantesBaseDatos.CAMPO_TIMESTAMP,getDateTime());  //No se podia usar simplemente CURRENT_TIMESTAMP, ya que es keyword de SQL (con execSQL() hubiera sido mas sencilo)
        //Actualizamos el registro en la base de datos
        db.update(ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS, values, "id="+id, null);

        db.close();

    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    /********** RETORNAR TODAS LAS MASCOTAS EN FORMA DE ARRAYLIST de la tabla_mascotas  ***********/

    //Con este método vamos ejecutar una consulta a la base de datos y retornaremos un Arraylist con
    // todos las mascotas de la tabla "tabla_mascotas":
    public ArrayList<Mascota> obtenerTodasLasMascotas(String nombredelatabla) {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        String query = "SELECT * FROM " + nombredelatabla; //consulta SQL que devuelve todos los datos de la tabla
        SQLiteDatabase db = this.getWritableDatabase();             //abrimos la base de datos en modo escritura (pq no en modo lectura?)
        //Usamos un objeto Cursor "registros" que contendrá los resultados de la consulta:
        Cursor registros = db.rawQuery(query, null);    //null = sin filtros adicionales para los datos

        while (registros.moveToNext()){                 //mientras podamos desplazarnos por el cursor con moveToNext..

            int id = registros.getInt(1);            //no empezamos en el indice 0, pq contiene el id autoincrementado
            String nombre = registros.getString(2);
            int foto = registros.getInt(3);
            int numlikes= registros.getInt(4);
            //cremos una nueva mascota con los valores recogidos de la posicion actual de cursor:
            Mascota mascotaActual = new Mascota(id,nombre,foto,numlikes);   //creamos una nueva mascota


            /*
            mascotaActual.setId(registros.getInt(0));      //asignamos los correspondientes valores usando un indice que comienza en cero y termina en el nº de columnas de la tabla -1
            mascotaActual.setNombre(registros.getString(1));
            mascotaActual.setTelefono(registros.getString(2));
            mascotaActual.setEmail(registros.getString(3));
            mascotaActual.setFoto(registros.getInt(4));


            String queryLikes = "SELECT COUNT("+ConstantesBaseDatos.TABLE_LIKES_CONTACT_NUMERO_LIKES+") as likes " +
                    " FROM " + ConstantesBaseDatos.TABLE_LIKES_CONTACT +
                    " WHERE " + ConstantesBaseDatos.TABLE_LIKES_CONTACT_ID_CONTACTO + "=" + contactoActual.getId();

            Cursor registrosLikes = db.rawQuery(queryLikes, null);
            if (registrosLikes.moveToNext()){
                contactoActual.setLikes(registrosLikes.getInt(0));
            }else {
                contactoActual.setLikes(0);
            }

            */

            mascotas.add(mascotaActual);  //lo añadimos al Arraylist "mascotas"
        }
        registros.close();   //No olvidar cerrar el cursor tb
        db.close();         //NO OLVIDAR CERRAR LA CONEXIÓN! <------- OJO

        return mascotas;   //Regresamos el Arraylist con todas las mascotas
    }






}
