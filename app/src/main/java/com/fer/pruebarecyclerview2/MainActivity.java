package com.fer.pruebarecyclerview2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*la profe no explica porque el ArrayList no se instancia y declara
    (ArrayList<Mascota> misMascotas = new ArrayList<Mascota>();) dentro del método onCreate, pero
    al hacerlo así, el IDE informaba que había que declararlo como "final" así que lo hacemos así,
    lo declaramos aquí y luego lo instanciamos dentro de onCreate
     */

    //declaramos un ArrayList de objetos Mascota, llamado misMascotas:
    ArrayList<Mascota> misMascotas;
    //declaramos nuestro recyclerview y lo llamamos listaMascotas:
    private RecyclerView listaMascotas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //instanciamos nuestro RecyclerView y lo llamamos "listaMascotas"
        //el id será el mismo que le hemos dado al RecyclerView en activity_main.xml:
        //y con esto ya podemos controlar nuestro RecyclerView "listaMascotas" desde java:
        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager llm = new LinearLayoutManager(this); //definimos nuestro LinearLayoutManager
        llm.setOrientation(LinearLayoutManager.VERTICAL); //le damos orientación horizontal (forma de lista)

        //le colocamos a nuestro RecyclerView "listaMascotas" el LayoutManager "llm":
        listaMascotas.setLayoutManager(llm);



        //inicializamos la lista de mascotas con el método que definimos más abajo:
        inicializarListaMascotas();

        Toast.makeText(this, "se iniclializaron las mascotas", Toast.LENGTH_SHORT).show();



        //inicializamos el adaptador con el método que definimos más abajo:
        inicializarAdaptador();



        //NO OLVIDAR INSTANCIAR LA TOOLBAR O APPBAR O NO SE VERÁ

        Toolbar appbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(appbar);

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

            case R.id.item_about:

                break;

            case R.id.item_saber_mas:
                break;

            case R.id.item_estrella:

                Intent intent = new Intent(MainActivity.this, Ultimas5Mascotas.class);
                startActivity(intent);

                break;

            case R.id.item_inicio:   //habria q ocultar el item en inicio, como?

                Toast.makeText(this, "Ya estas en Inicio", Toast.LENGTH_SHORT).show();

                break;

            }

        return super.onOptionsItemSelected(item);

    }

    public void inicializarAdaptador(){
        //declaramos e instanciamos nuestro adaptador y le pasamos misMascotas:
        MascotaAdaptador adaptador = new MascotaAdaptador(misMascotas, this); //y ahora le pasamos tb el activity, para el tema del click
        listaMascotas.setAdapter(adaptador); //le colocamos al recyclerview ese adaptador

    }

    public void inicializarListaMascotas(){

        //instanciamos (ya está declarado anteriormente más arriba) el ArrayList misMascotas:
        misMascotas = new ArrayList<Mascota>();

        misMascotas.add(new Mascota("1","sparky", R.drawable.dog1,"0"));
        misMascotas.add(new Mascota("2","bobby", R.drawable.dog2, "0"));
        misMascotas.add(new Mascota("3","Tommy", R.drawable.dog3, "0"));
        misMascotas.add(new Mascota("4","dozer", R.drawable.dog4, "0"));
        misMascotas.add(new Mascota("5","taco", R.drawable.dog5, "0"));
        misMascotas.add(new Mascota("6","carolo", R.drawable.dog6, "0"));
        misMascotas.add(new Mascota("7","crazy", R.drawable.dog7, "0"));
        misMascotas.add(new Mascota("8","pinky", R.drawable.dog8, "0"));
        misMascotas.add(new Mascota("9","chuko", R.drawable.dog9, "0"));
        misMascotas.add(new Mascota("10","chusto", R.drawable.dog10, "0"));
        misMascotas.add(new Mascota("11","lupo", R.drawable.dog11, "0"));



    }


}
