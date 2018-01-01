package com.fer.PracticaViewpagerTabsyFragments;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fer.PracticaViewpagerTabsyFragments.adapter.MascotaAdaptador;
import com.fer.PracticaViewpagerTabsyFragments.adapter.PageAdapter;
import com.fer.PracticaViewpagerTabsyFragments.fragment.PerfilFragment;
import com.fer.PracticaViewpagerTabsyFragments.fragment.RecyclerViewFragment;
import com.fer.PracticaViewpagerTabsyFragments.pojo.Mascota;

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


    }

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

            case R.id.item_estrella:
                //LO DESACTIVAMOS DE MOMENTO:
                /*Intent intent = new Intent(MainActivity.this, Ultimas5Mascotas.class);
                startActivity(intent);*/
                Toast.makeText(this, "Las 5 últimas mascotas no se piden en esta práctica", Toast.LENGTH_SHORT).show();

                break;

            case R.id.item_inicio:   //habria q ocultar el item en inicio, como?

                Toast.makeText(this, "Ya estas en Inicio", Toast.LENGTH_SHORT).show();

                break;

            }

        return super.onOptionsItemSelected(item);

    }




}
