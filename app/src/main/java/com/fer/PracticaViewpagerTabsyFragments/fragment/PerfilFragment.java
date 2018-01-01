package com.fer.PracticaViewpagerTabsyFragments.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fer.PracticaViewpagerTabsyFragments.R;
import com.fer.PracticaViewpagerTabsyFragments.adapter.MascotaAdaptador;
import com.fer.PracticaViewpagerTabsyFragments.pojo.Mascota;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    //declaramos un ArrayList de objetos Mascota, llamado misMascotas:
    ArrayList<Mascota> misMascotas;
    //declaramos nuestro recyclerview y lo llamamos listaMascotas:
    private RecyclerView listaMascotas;  //equivale a rvContactos en el video


    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        //################## recyclerview ####################################################


        //instanciamos nuestro RecyclerView y lo llamamos "listaMascotas"
        //ponemos v.find.. pq v es el objeto View del fragment "fragment_recyclerview" (cuando no
        //usábamos fragments era sin el "v"):
        listaMascotas = (RecyclerView) view.findViewById(R.id.rvFotosDePerfil);

        //definimos nuestro LinearLayoutManager y como contexto le pasamos getActivity() (cuando
        // no usábamos fragments el contexto era "this"):

        /*
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL); //le damos orientación horizontal (forma de lista)
        */

        //esta vez colocamos un GridLayoutManager en lugar de LinearLayoutManager, y le
        //decimos que 2 columnas:

        GridLayoutManager glm = new GridLayoutManager(getActivity(),2);

        //lLayout = new GridLayoutManager(MainActivity.this, 4);

        //le colocamos a nuestro RecyclerView "listaMascotas" el LayoutManager "llm":
        listaMascotas.setLayoutManager(glm);

        //######################################################################################

        //inicializamos la lista de mascotas con el método que definimos más abajo:
        inicializarListaMascotas();

        //inicializamos el adaptador con el método que definimos más abajo:
        inicializarAdaptador();



        // Retornamos el layout "inflado" para este fragmento:
        return view;
    }

    public void inicializarAdaptador(){
        //declaramos e instanciamos nuestro adaptador y le pasamos misMascotas:
        //en el contexto no ponemos "this" sino getActivity(), ya que ahora estamos trabajando con fragments
        MascotaAdaptador adaptador = new MascotaAdaptador(misMascotas, getActivity()); //y ahora le pasamos tb el activity, para el tema del click
        listaMascotas.setAdapter(adaptador); //le colocamos al recyclerview ese adaptador

    }

    public void inicializarListaMascotas(){

        //instanciamos (ya está declarado anteriormente más arriba) el ArrayList misMascotas:
        misMascotas = new ArrayList<Mascota>();

        misMascotas.add(new Mascota("1","pinky1", R.drawable.pic1,"22"));
        misMascotas.add(new Mascota("2","pinky2", R.drawable.pic2, "15"));
        misMascotas.add(new Mascota("3","pinky3", R.drawable.pic3, "12"));
        misMascotas.add(new Mascota("4","pinky4", R.drawable.pic4, "10"));
        misMascotas.add(new Mascota("5","pinky5", R.drawable.pic5, "9"));
        misMascotas.add(new Mascota("6","pinky6", R.drawable.pic6, "8"));
        misMascotas.add(new Mascota("7","pinky7", R.drawable.pic7, "5"));
        misMascotas.add(new Mascota("8","pinky8", R.drawable.pic8, "3"));
        misMascotas.add(new Mascota("9","pinky9", R.drawable.pic9, "1"));
        misMascotas.add(new Mascota("10","pinky10", R.drawable.pic1, "0"));
        misMascotas.add(new Mascota("11","pinky11", R.drawable.pic2, "0"));



    }

}
