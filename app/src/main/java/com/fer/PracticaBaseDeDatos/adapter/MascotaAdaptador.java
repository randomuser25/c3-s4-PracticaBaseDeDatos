package com.fer.PracticaBaseDeDatos.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fer.PracticaBaseDeDatos.Db.ConexionSQLiteHelper;
import com.fer.PracticaBaseDeDatos.Db.ConstantesBaseDatos;
import com.fer.PracticaBaseDeDatos.R;
import com.fer.PracticaBaseDeDatos.Ultimas5Mascotas;
import com.fer.PracticaBaseDeDatos.pojo.Mascota;

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
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, final int position) {

         final Mascota mascota = mascotas.get(position);   //va iterando con cada posicion

        //Valores de la mascota actual:

        final int fotomascotaactual = mascota.getFoto_mascota();
        final String nombremascotaactual = mascota.getNombre_mascota();
        final int idmascotaactual = mascota.getId_mascota();
        final int likesmascotaactual = mascota.getNumeroLikes_mascota();

        //los asignamos:
        mascotaViewHolder.imgFoto.setImageResource(fotomascotaactual);
        mascotaViewHolder.tvNombreCV.setText(nombremascotaactual);
        mascotaViewHolder.tvIdCV.setText(Integer.toString(idmascotaactual));
        //ocultamos el id provisionalmente, para que no solape:
        //mascotaViewHolder.tvIdCV.setText("");

        mascotaViewHolder.tvNumeroLikesCV.setText(Integer.toString(likesmascotaactual));


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


                //DEBEMOS ACTUALIZAR TANTO EL DATO EN EL ARRAYLIST COMO EN LA tabla_mascotas:

                //calculamos nuevos likes y actualizamos un item del dataset (PODRÍA SIMPLIFICARSE):

                //int likesactuales = mascota.getNumeroLikes_mascota();

                int likesactuales = likesmascotaactual;
                int nuevoslikes = likesactuales + 1;

                //int likes = nuevoslikes;

                mascota.setNumero_likes_mascota(nuevoslikes);

                //int posicion = mascota.getId_mascota() - 1; //sacamos la posicion, que sera el id-1


                /*
                //yourAdapter.notifyDataSetChanged();
                MascotaAdaptador.this.notifyItemChanged(posicion);  //actualizamos el item
                */

                /******* ACTUALIZAMOS EL DATO EN LA tabla_mascotas:  *********/
                ConexionSQLiteHelper conn = new ConexionSQLiteHelper(activity);

                //conn.actualizarLikeEnRowPorSuId(posicion+1, nuevoslikes);

                int ID = mascota.getId_mascota(); //identifica el id de la mascota a la que se dio like

                /*//######## MOSTRAR INFORMACION DE LA MASCOTA CON LIKE ###########

                String mistring = "ID de mascota: "+Integer.toString(ID) +
                                  "\n likes: "+likesactuales+
                                  "\n nuevos likes: "+nuevoslikes;

                Toast.makeText(activity, mistring, Toast.LENGTH_SHORT).show();
                //################################################################*/

                conn.actualizarLikeEnRowPorSuId(ConstantesBaseDatos.TABLE_MASCOTAS,ID, nuevoslikes);
                conn.actualizarLikeEnRowPorSuId(ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS,ID, nuevoslikes);


                //AHORA DEBERIAMOS AÑADIR UN REGISTRO A LA TABLA tabla_ultimas_mascotas:

                /************** ACTUALIZAMOS EL DATO EN tabla_ultimas_mascotas *****************************/

                //VERIFICAR SI YA EXISTE UNA MASCOTA CON ESE ID EN LA TABLA  -- OK
                //VERIFICAR EL TAMAÑO DE LA TABLA, SI = 5, SUSTITUIR
                //PODEMOS VALERNOS DEL ID AUTOINCREMENTADO <---


                if(!conn.existeEstaId(ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS,ID)){

                    Toast.makeText(activity, "no existe la id, se va a insertar en tabla_ultimas_mascotas", Toast.LENGTH_SHORT).show();

                    ContentValues values = new ContentValues();

                    values.put(ConstantesBaseDatos.CAMPO_ID, idmascotaactual);
                    values.put(ConstantesBaseDatos.CAMPO_NOMBRE, nombremascotaactual);
                    values.put(ConstantesBaseDatos.CAMPO_FOTO, fotomascotaactual);
                    values.put(ConstantesBaseDatos.CAMPO_NUMLIKES, nuevoslikes);

                    conn.insertarMascota(ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS, values);

                }else{ //Existe la id en tabla_ultimas_mascotas

                    Toast.makeText(activity, "ya existe la id, SE VAN A ACTUALIZAR SUS LIKES Y SU TIMESTAMP", Toast.LENGTH_SHORT).show();


                    /**** la id ya existe, no hay que introducir un nuevo registro sólo hay que actualizar sus likes y su timestamp en tabla_ultimas_mascotas***/

                        conn.actualizarLikesyTimestampPorSuId(ID, nuevoslikes);



                }


                /*** IDEA: SI DEBO ABRIR Y CERRAR DOS O MAS VECES UNA DB (PEJ EN UN IF) QUIZA PODRÍA MODIFICAR LAS
                 * FUNCIONES PARA RECIBIR UN PARÁMETRO EXTRA TRUE/FALSE PARA QUE CIERRE LA CONEXION DE LA DB O NO ***/

                //yourAdapter.notifyDataSetChanged();
                MascotaAdaptador.this.notifyDataSetChanged();



                //MascotaAdaptador.this.notifyItemChanged(position);  //actualizamos el item


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
