package com.fer.PracticaBaseDeDatos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fer.PracticaBaseDeDatos.Db.ConexionSQLiteHelper;
import com.fer.PracticaBaseDeDatos.Db.ConstantesBaseDatos;
import com.fer.PracticaBaseDeDatos.adapter.MascotaAdaptador;
import com.fer.PracticaBaseDeDatos.pojo.Mascota;

import java.util.ArrayList;

public class Ultimas5Mascotas extends AppCompatActivity {


    //declaramos un ArrayList de objetos Mascota, llamado misMascotas2:
    ArrayList<Mascota> misMascotas2;
    //declaramos nuestro recyclerview y lo llamamos listaMascotas2:
    private RecyclerView listaMascotas2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ultimas5_mascotas);


        //instanciamos nuestro RecyclerView y lo llamamos "listaMascotas2"
        //el id será el mismo que le hemos dado al RecyclerView en activity_main.xml:
        //y con esto ya podemos controlar nuestro RecyclerView "listaMascotas2" desde java:
        listaMascotas2 = (RecyclerView) findViewById(R.id.rvUltimasMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this); //definimos nuestro LinearLayoutManager
        llm.setOrientation(LinearLayoutManager.VERTICAL); //le damos orientación horizontal (forma de lista)

        //le colocamos a nuestro RecyclerView "listaMascotas" el LayoutManager "llm":
        listaMascotas2.setLayoutManager(llm);


        cargarMascotasEnArrayList();

        /*** SE VA INICIAR EL ADAPTADOR Y DEBERIA MOSTRAR UN MENSAJE ***/

        Toast.makeText(this,"se va a iniciar el adaptador", Toast.LENGTH_LONG).show();

        inicializarAdaptador();




        /* COMENTAMOS ESTO PARA ESTA PRACTICA...
        //con tan solo getSupport().. me daba error, asiq primero habia que declarar la
        //toolbar:

        Toolbar appbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(appbar);

        //activamos el uso del boton "arriba" XD en la toolbar para regresar a la actividad
        //padre que hemos declarado en Android manifest:
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher_background);

        */

    }

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

                break;

            case R.id.item_contacto:
                break;

            case R.id.item_estrella:   //habria q ocultar el item, como?

                Toast.makeText(this, "Ya estas en ultimas 5 mascotas", Toast.LENGTH_SHORT).show();
                break;

            case R.id.item_inicio:

                Intent intent = new Intent(Ultimas5Mascotas.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); //(1*)
                startActivity(intent);
                //el objetivo es no instanciar desde cero MainActivity, pues se reinicializan las mascotas:
                //(1*) It makes old activity to front without new instance...
                //otra solucion sería:
                //finish();
                break;

        }

        return super.onOptionsItemSelected(item);

    }

    public void inicializarAdaptador(){
        //declaramos e instanciamos nuestro adaptador y le pasamos misMascotas:
        //en el contexto no ponemos "this" sino getActivity(), ya que ahora estamos trabajando con fragments
        MascotaAdaptador adaptador2 = new MascotaAdaptador(misMascotas2, this); //y ahora le pasamos tb el activity, para el tema del click
        listaMascotas2.setAdapter(adaptador2); //le colocamos al recyclerview ese adaptador

    }

    public void cargarMascotasEnArrayList(){



        //instanciamos (ya está declarado anteriormente más arriba) el ArrayList misMascotas:
        misMascotas2 = new ArrayList<Mascota>();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this); //pasamos this y no getContext(), pq estamos en un activity y no en un fregment

        //misMascotas2 = conn.obtenerTodasLasMascotas(ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS);

        misMascotas2 = conn.obtener5UltimasMascotas();


/*
        //VAMOS A INICIALIZAR ALGUNAS MASCOTAS "HARDCODEADAS":

        misMascotas2 = new ArrayList<Mascota>();

        misMascotas2.add(new Mascota(7,"crazy", R.drawable.dog7, 16));
        misMascotas2.add(new Mascota(8,"pinky", R.drawable.dog8, 12));
        misMascotas2.add(new Mascota(3,"Tommy", R.drawable.dog3, 8));
        misMascotas2.add(new Mascota(4,"dozer", R.drawable.dog4, 7));
        misMascotas2.add(new Mascota(5,"taco", R.drawable.dog5, 2));

*/

    }


}
