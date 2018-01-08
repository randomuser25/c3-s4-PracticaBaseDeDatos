package com.fer.PracticaBaseDeDatos.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fer.PracticaBaseDeDatos.Db.ConexionSQLiteHelper;
import com.fer.PracticaBaseDeDatos.Db.ConstantesBaseDatos;
import com.fer.PracticaBaseDeDatos.R;
import com.fer.PracticaBaseDeDatos.adapter.MascotaAdaptador;
import com.fer.PracticaBaseDeDatos.pojo.Mascota;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {

    //declaramos un ArrayList de objetos Mascota, llamado misMascotas:
    ArrayList<Mascota> misMascotas;
    //declaramos nuestro recyclerview y lo llamamos listaMascotas:
    private RecyclerView listaMascotas;  //equivale a rvContactos en el video


    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);

        //################## recyclerview ####################################################


        //instanciamos nuestro RecyclerView y lo llamamos "listaMascotas"
        //ponemos v.find.. pq v es el objeto View del fragment "fragment_recyclerview" (cuando no
        //usábamos fragments era sin el "v"):
        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        //definimos nuestro LinearLayoutManager y como contexto le pasamos getActivity() (cuando
        // no usábamos fragments el contexto era "this"):
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL); //le damos orientación horizontal (forma de lista)

        //le colocamos a nuestro RecyclerView "listaMascotas" el LayoutManager "llm":
        listaMascotas.setLayoutManager(llm);

        //######################################################################################

        //cargamos las mascotas de la base de datos en arraylist:
        cargarMascotasEnArrayList();

        //inicializamos el adaptador con el método que definimos más abajo:
        /*** SE VA INICIAR EL ADAPTADOR Y DEBERIA MOSTRAR UN MENSAJE ***/

        Toast.makeText(getContext(),"se va a iniciar el adaptador", Toast.LENGTH_LONG).show();


        inicializarAdaptador();


        // Retornamos el layout "inflado" para este fragmento:
        return v;
    }


    public void inicializarAdaptador(){
        //declaramos e instanciamos nuestro adaptador y le pasamos misMascotas:
        //en el contexto no ponemos "this" sino getActivity(), ya que ahora estamos trabajando con fragments
        MascotaAdaptador adaptador = new MascotaAdaptador(misMascotas, getActivity()); //y ahora le pasamos tb el activity, para el tema del click
        listaMascotas.setAdapter(adaptador); //le colocamos al recyclerview ese adaptador

    }

    public void cargarMascotasEnArrayList(){



        //instanciamos (ya está declarado anteriormente más arriba) el ArrayList misMascotas:
        misMascotas = new ArrayList<Mascota>();

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(getContext()); //si no instanciamos ni siquiera se crearia la tabla

        misMascotas = conn.obtenerTodasLasMascotas(ConstantesBaseDatos.TABLE_MASCOTAS);


/*
        misMascotas = new ArrayList<Mascota>();

        misMascotas.add(new Mascota(1,"sparky", R.drawable.dog1,0));
        misMascotas.add(new Mascota(2,"bobby", R.drawable.dog2, 0));
        misMascotas.add(new Mascota(3,"Tommy", R.drawable.dog3, 0));
        misMascotas.add(new Mascota(4,"dozer", R.drawable.dog4, 0));
        misMascotas.add(new Mascota(5,"taco", R.drawable.dog5, 0));
        misMascotas.add(new Mascota(6,"carolo", R.drawable.dog6, 0));
        misMascotas.add(new Mascota(7,"crazy", R.drawable.dog7, 0));
        misMascotas.add(new Mascota(8,"pinky", R.drawable.dog8, 0));
        misMascotas.add(new Mascota(9,"chuko", R.drawable.dog9, 0));
        misMascotas.add(new Mascota(10,"chusto", R.drawable.dog10, 0));
        misMascotas.add(new Mascota(11,"lupo", R.drawable.dog11, 0));

*/

    }

}
