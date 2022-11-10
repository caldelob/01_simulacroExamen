package com.example.a01_simulacroexamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {


    private TextView lblDinero;
    private TextView lblCigarrillos;
    private ImageButton btnFumar;


    //Buenas prácticas:

    private final int NUM_CIGARROS = 20;
    private final int COSTE_PAQUETE =5;


    //Contadores
    private int cigarrosRestantes= NUM_CIGARROS;
    private int dineroAhorrado = 0;

    private NumberFormat nf;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarVistas();

        btnFumar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cigarrosRestantes--;
                if(cigarrosRestantes==0){
                    dineroAhorrado+=COSTE_PAQUETE;
                    cigarrosRestantes= NUM_CIGARROS;
                }
                actualizaVista();
            }
        });
    }

    private void inicializarVistas() {

        lblDinero = findViewById(R.id.lblDinerAhorrado);
        lblCigarrillos = findViewById(R.id.lblCigarrosRestantes);
        btnFumar = findViewById(R.id.btnFumar);
        nf= NumberFormat.getCurrencyInstance();

        actualizaVista();
    }

    private void actualizaVista() {
        lblCigarrillos.setText(String.valueOf(cigarrosRestantes));
        lblDinero.setText(nf.format(dineroAhorrado));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("DINERO", dineroAhorrado);
        outState.putInt("CIGARROS", cigarrosRestantes);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        dineroAhorrado = savedInstanceState.getInt("DINERO");
        cigarrosRestantes = savedInstanceState.getInt("CIGARROS");
        actualizaVista();
    }
}