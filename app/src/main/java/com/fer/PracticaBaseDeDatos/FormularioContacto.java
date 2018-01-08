package com.fer.PracticaBaseDeDatos;

//RECURSO UTILIZADO PARA ESTA PRÁCTICA:
//https://www.simplifiedcoding.net/android-email-app-using-javamail-api-in-android-studio/

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FormularioContacto extends AppCompatActivity implements View.OnClickListener {

    //Declaring EditText
    private EditText editTextNombre;
    private EditText editTextEmail;
    private EditText editTextSubject;
    private EditText editTextMessage;

    //Send button
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_contacto);

        //Initializing the views
        editTextEmail = (EditText) findViewById(R.id.miEditText2);
        editTextNombre = (EditText) findViewById(R.id.miEditText1);
        editTextMessage = (EditText) findViewById(R.id.miEditText3);
        editTextSubject = (EditText) findViewById(R.id.miEditText4);

        buttonSend = (Button) findViewById(R.id.boton_enviar);

        //Adding click listener
        buttonSend.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        String email = editTextEmail.getText().toString().trim();
        String subject = editTextSubject.getText().toString().trim();
        //String message = editTextMessage.getText().toString().trim();

        String nombredelremitente = editTextNombre.getText().toString().trim();
        String mensaje = editTextMessage.getText().toString().trim();

        String message = "Mensaje enviado por: " + nombredelremitente +" | "+ mensaje;


        //Comprobamos que no haya campos vacíos y avisamos al usuario en caso afirmativo:

        if(TextUtils.isEmpty(nombredelremitente) || TextUtils.isEmpty(email) ||
                TextUtils.isEmpty(subject) || TextUtils.isEmpty(mensaje)){

            Toast.makeText(getBaseContext(), "Complete los campos vacíos", Toast.LENGTH_SHORT).show();

            //FALTARIA VALIDAR QUE SE TRATA DE UN MAIL CORRECTO


        }else {


            //Creating SendMail object
            SendMail sm = new SendMail(this, email, subject, message);

            //Executing sendmail to send email
            sm.execute();

        }
    }

    @Override
    public void onClick(View v) {

        sendEmail();

    }

}



