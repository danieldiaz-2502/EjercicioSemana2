package com.example.ejerciciosemana2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView pregunta;
    private TextView userPuntaje;
    private TextView userTiempo;
    private EditText userRespuesta;
    private Button enviarBoton;
    private Button intentarBoton;
    private Pregunta p;

    private int puntaje = 0;
    private int tiempo = 30;

    private boolean cuenta = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pregunta = findViewById(R.id.pregunta);
        userRespuesta = findViewById(R.id.userRespuesta);
        enviarBoton = findViewById(R.id.enviarBoton);
        intentarBoton = findViewById(R.id.intentarBoton);
        userTiempo = findViewById(R.id.userTiempo);
        userPuntaje = findViewById(R.id.userPuntaje);

        nuevaPregunta();
        Temporizador();

        enviarBoton.setOnClickListener(
                v-> {
                    responder();
                    }
        );
        intentarBoton.setOnClickListener(
                v-> {
                    intentarDeNuevo();
                }
        );
        userPuntaje.setText(""+puntaje);

    }

    public void responder(){
        String respuesta = userRespuesta.getText().toString();
        int respuestaUsuario = Integer.parseInt(respuesta);
        int correcto = p.getRespuesta();

        if (respuestaUsuario == correcto){
            puntaje += 5;
            userPuntaje.setText(""+puntaje);
        } else {
            Toast.makeText(this,"Respuesta incorrecta", Toast.LENGTH_SHORT).show();
            puntaje += 0;
        }
        nuevaPregunta();
    }

    public void nuevaPregunta(){
        p = new Pregunta();
        pregunta.setText(p.getPregunta());
    }

    public void Temporizador(){
        new Thread (
                () -> {
                    while(cuenta){
                        tiempo--;

                        runOnUiThread(
                                () -> {
                                    userTiempo.setText(""+tiempo);
                                    if(tiempo == 0){
                                        cuenta = false;
                                        intentarBoton.setVisibility(View.VISIBLE);
                                    }
                                }
                        );
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
        ).start();
    }
    public void intentarDeNuevo(){
        puntaje = 0;
        tiempo = 30;
        cuenta = true;
        userPuntaje.setText(""+puntaje);
        userTiempo.setText(""+tiempo);
        Temporizador();
        intentarBoton.setVisibility(View.GONE);
    }

}