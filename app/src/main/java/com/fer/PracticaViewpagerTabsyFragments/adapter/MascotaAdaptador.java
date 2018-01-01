package com.fer.PracticaViewpagerTabsyFragments.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fer.PracticaViewpagerTabsyFragments.R;
import com.fer.PracticaViewpagerTabsyFragments.pojo.Mascota;

import java.util.ArrayList;

/**
 * Created by me on 21/12/17.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {


    ArrayList<Mascota> mascotas;


    Activity activity; //para el listener de click y más

    //creamos un constructor que recibirá nuestra lista de mascotas como parámetro, para
    //que el resto de métodos que hay despues puedan utilizar dichos datos

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity){   //el constructor recibirá nuestra lista de mascotas
                                                                               //y ahora tb el activity para manejar el onclick
        this.mascotas = mascotas;
        this.activity = activity; //estamos diciendo que estamos en la clase MascotaAdaptador.java (creo), para el tema de eventos, intents..etc



    }

    //Inflará el layout y lo pasará al viewholder para que obtenga los views:
    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Indicamos qué layout estará reciclando el RecyclerView:
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v); //Retorna un objeto ViewHolder cuyo parámetro es el view v, ya "inflado"
    }

    //seteamos o asciamos los valores de cada uno de los objetos que trae  la lista de mascotas:
    //onBindViewHolder va a hacer una iteración por cada elemento u objeto que tenga la lista
    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);   //va iterando con cada posicion
        mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto_mascota());
        mascotaViewHolder.tvNombreCV.setText(mascota.getNombre_mascota());
        //mascotaViewHolder.tvIdCV.setText(mascota.getId_mascota());
        //ocultamos el id provisionalmente, para que no solape:
        mascotaViewHolder.tvIdCV.setText("");

        mascotaViewHolder.tvNumeroLikesCV.setText(mascota.getNumeroLikes_mascota());


        /* manejador de click en la imagen:
        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(activity, mascota.getNombre_mascota(), Toast.LENGTH_SHORT).show();

                //AQUI PODRÍAMOS COLOCAR UN INTENT PARA SALTAR A OTRA ACTIVITY:
                Intent i = new Intent(activity, LOQUESEA.class);
                i.putExtra("parametro1",mascota.getNombre_mascota());
                i.putExtra("parametro2",mascota.getFoto_mascota()); <-- COMPROBAR ÉSTO
                activity.startActivity(i);
            }
        });
        */

        //########### EVENTO CLICK EN EL EL HUESO BLANCO ######################

        mascotaViewHolder.huesoblanco.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //calculamos nuevos likes y actualizamos un item del dataset (PODRÍA SIMPLIFICARSE):

                int likesactuales = Integer.parseInt(mascota.getNumeroLikes_mascota());
                int nuevoslikes = likesactuales + 1;

                String likes = Integer.toString(nuevoslikes);

                mascota.setNumero_likes_mascota(likes);


                int posicion = Integer.parseInt(mascota.getId_mascota()) - 1; //sacamos la posicion, que sera el id-1

                //yourAdapter.notifyDataSetChanged();
                MascotaAdaptador.this.notifyItemChanged(posicion);  //actualizamos el item:
                //FUENTE: https://stackoverflow.com/questions/33176336/need-an-example-about-recyclerview-adapter-notifyitemchangedint-position-objec

                //Mostramos un toast informando del nombre de la mascota que se dio like:

                Toast.makeText(activity, "Diste like a: " + mascota.getNombre_mascota(), Toast.LENGTH_SHORT).show();


            }
        });

        //###################################################################

    }

    @Override
    public int getItemCount() { //cantidad de elementos que contiene mi lista o coleccion mascotas

        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgFoto;    // ImageView Foto del CardView
        private TextView tvNombreCV; //"TextView Nombre del CardView"
        private TextView tvIdCV; //"TextView Id del CardView"

        private TextView tvNumeroLikesCV; //

        //creo que no seria lo optimo capturar el hueso blanco desde aqui, ya que su imagen no cambia,
        //aunque por el momento lo dejaremos asi.. :

        private ImageButton huesoblanco;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            //Ahora comenzamos a asociar cada objeto imgFoto,etc con su respectivo view,
            // al que hemos dado su id correspondiente en el archivo cardview_mascota.xml:

            imgFoto         = (ImageView) itemView.findViewById(R.id.card_view_foto_imageview);
            tvNombreCV      = (TextView) itemView.findViewById(R.id.card_view_nombre_textview);
            tvIdCV          = (TextView) itemView.findViewById(R.id.card_view_id_textview);

            tvNumeroLikesCV = (TextView) itemView.findViewById(R.id.card_view_numlikes_textview);

            //creo q no seria para nada optimo:

            huesoblanco     = (ImageButton) itemView.findViewById(R.id.imagebutton_huesoblanco);



        }
    }
}
