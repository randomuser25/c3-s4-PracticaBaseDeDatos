package com.fer.PracticaBaseDeDatos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fer.PracticaBaseDeDatos.Db.ConexionSQLiteHelper;
import com.fer.PracticaBaseDeDatos.Db.ConstantesBaseDatos;
import com.fer.PracticaBaseDeDatos.pojo.Mascota;

import java.util.ArrayList;
import java.util.Iterator;

public class TestDB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_db);

        Button miBoton = (Button) findViewById(R.id.boton_borrar);

        //TextView miTextView = (TextView) findViewById(R.id.un_text_view);
    }

    /*
    public void borraLasTablas(View v) {

        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this); //si no instanciamos ni siquiera se crearia la tabla

        //borrala y creala de nuevo llamando a onCreate:

        SQLiteDatabase db = conn.getWritableDatabase();

        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_MASCOTAS);
        db.execSQL("DROP TABLE IF EXISTS " + ConstantesBaseDatos.TABLE_ULTIMAS_MASCOTAS);

        db.close();

    }
    */

}
