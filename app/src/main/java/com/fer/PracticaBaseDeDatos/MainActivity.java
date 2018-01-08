package com.fer.PracticaBaseDeDatos;

import android.content.ContentValues;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fer.PracticaBaseDeDatos.Db.ConexionSQLiteHelper;
import com.fer.PracticaBaseDeDatos.Db.ConstantesBaseDatos;
import com.fer.PracticaBaseDeDatos.adapter.PageAdapter;
import com.fer.PracticaBaseDeDatos.fragment.PerfilFragment;
import com.fer.PracticaBaseDeDatos.fragment.RecyclerViewFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //NO OLVIDAR INSTANCIAR LA TOOLBAR,etc..:

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager= (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager(); //seteamos el viewPager con el método que definimos más abajo




        //hacemos una validacion para la toolbar (no explica porqué):
        if(toolbar != null){
            setSupportActionBar(toolbar);
        }


        //CREAMOS LA ESTRUCTURA DE LA BASE DE DATOS SOLO "IF NO EXIST" con las dos tablas:

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this);

        //LLENAMOS LA TABLA "tabla_mascotas" SÓLO SI NO HAY DATOS EN ELLA:


        if(conn.estaLaTablaVacia(ConstantesBaseDatos.TABLE_MASCOTAS)){

            llenaLaTabla();
        }



    }

    /************************/
    /**EL PROBLEMA ERA EL SIGUIENTE: El adaptador actualizaba correctamente sus datos al dar like tanto en la
     * activity Ultimas5Mascotas, como en el fragment RecyclerViewFragment, sin embargo al pasar del
     * activity al fragment no reflejaba los cambios (viceversa sí). En cualquier caso las tablas de
     * la base de datos sí se actualizaban correctamente. ESTA LA SOLUCION PRIVISIONAL QUE HE
     * ENCONTRADO PARA QUE AL HACER BACK DESDE LAS
     * 5 ÚLTIMAS MASCOTAS ACTUALICE LOS DATOS DEL ADAPTADOR: **/
    @Override
    protected void onStart() {
        super.onStart();
        setUpViewPager();
    }

    /**********************************************************************************************/

    //###################################################################################
    //Primero vamos a declarar nuestra lista de fragments en un ArrayList, ya que el constructor
    //de nuestro adaptador (PageAdapter) recibe como uno de sus parámetros un ArrayList
    //de fragments:

    private ArrayList<Fragment> agregarFragments(){
        //declaramos e instanciamos un ArrayList de fragments:
        ArrayList<Fragment> fragments = new ArrayList<>();
        //Ahora comenzamos a llenar el ArrayList con los fragments que necesitamos, en este caso:
        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        //finalmente retornamos el ArrayList de fragments:

        return fragments;

    }

    //Ahora seteamos el viewpager (hemos escogido el nombre setUpViewPager) :
    private void setUpViewPager(){
        //le seteamos un adaptador PageAdapter, que construimos pasándole el Arralist de fragments
        //que retorna agregarFragments() y el soporte getSupportFragmentManager()

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        //Una vez que teneos el viewPager configurado, hay que setear tb el tabLayout:
        tabLayout.setupWithViewPager(viewPager); //supongo que relaciona las tabs con el viewPager..

        //ahora seteamos los iconos de cada tab:
        tabLayout.getTabAt(0).setIcon(R.drawable.casa);
        tabLayout.getTabAt(1).setIcon(R.drawable.perfil);


    } //ahora llamamos a este método mas arriba con un setUpViewPager()



    //####################################################################################


    //sobreescribimos este método para crear nuestro menu de opciones del appbar. Tomará nuestro menu_del_appbar.xml
    //y lo inflará o mostrará en nuestra vista:
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_del_appbar, menu);
        return true;
    }

    //ahora discriminamos de en que item del menu se hiz click:
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.item_acerca_de:

                Intent intent = new Intent(MainActivity.this, BioDesarrollador.class);
                startActivity(intent);

                break;

            case R.id.item_contacto:
                Intent i = new Intent(MainActivity.this, FormularioContacto.class);
                startActivity(i);

                break;

            case R.id.item_test_db:
                Intent inn = new Intent(MainActivity.this, TestDB.class);
                startActivity(inn);

                break;

            case R.id.item_estrella:

                Intent in = new Intent(MainActivity.this, Ultimas5Mascotas.class);
                startActivity(in);
                //Toast.makeText(this, "Las 5 últimas mascotas no se piden en esta práctica", Toast.LENGTH_SHORT).show();

                break;

            case R.id.item_inicio:   //habria q ocultar el item en inicio, como?

                Toast.makeText(this, "Ya estas en Inicio", Toast.LENGTH_SHORT).show();

                break;

            }

        return super.onOptionsItemSelected(item);

    }

    /*****************método para cargar la tabla "tabla_mascotas" con 10 mascotas ***************/

    public void llenaLaTabla(){

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this); //primero instanciamos para acceder a los métodos de la clase

        ContentValues values = new ContentValues();
        values.put(ConstantesBaseDatos.CAMPO_ID, 1);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "FUFFY");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog1);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 2);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "MUFFIN");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog2);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 3);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "PINKY");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog3);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 4);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "DOZER");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog4);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 5);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "CAROLO");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog5);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 6);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "LUPI");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog6);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 7);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "TOBBY");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog7);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 8);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "WINCHESTER");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog8);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 9);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "CHUSTO");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog9);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);


        values.put(ConstantesBaseDatos.CAMPO_ID, 10);
        values.put(ConstantesBaseDatos.CAMPO_NOMBRE, "TREBOL");
        values.put(ConstantesBaseDatos.CAMPO_FOTO, R.drawable.dog10);
        values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, 0);
        conn.insertarMascota(ConstantesBaseDatos.TABLE_MASCOTAS, values);

        Toast.makeText(getBaseContext(), "Se inicializado la tabla_mascotas", Toast.LENGTH_LONG).show();


    }




}
