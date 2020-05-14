package com.proyecto.tucca.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.tucca.R;

import java.io.IOException;
import java.util.Random;

import static com.proyecto.tucca.activities.MainActivity.dataIn;
import static com.proyecto.tucca.activities.MainActivity.dataOut;
import static com.proyecto.tucca.activities.MainActivity.idCliente;

public class AddCardActivity extends AppCompatActivity {
    private TextView textViewDailyCard;
    private TextView textViewRetiredCard;
    private TextView textViewStudentCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);

        textViewDailyCard = findViewById(R.id.text_view_everyday_card);
        textViewRetiredCard = findViewById(R.id.text_view_retired_card);
        textViewStudentCard = findViewById(R.id.text_view_student_card);
        final Random rnd = new Random();
        textViewDailyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddCardActivity.this)
                        .setTitle("Crear tarjeta")
                        .setMessage("¿Quiere crear una tarjeta tipo estándar?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //Toast.makeText(AddCardActivity.this, "Se creó la tarjeta", Toast.LENGTH_SHORT).show();
                                try {
                                    dataOut.writeUTF("tarjeta_es");
                                    dataOut.flush();
                                    long numTarjeta = rnd.nextLong();
                                    //System.out.println(dig13);
                                    dataOut.writeUTF(numTarjeta + "/" + idCliente);
                                    dataOut.flush();
                                    String estado = dataIn.readUTF();
                                    if(estado.equalsIgnoreCase("correcto")){
                                        new AlertDialog.Builder(AddCardActivity.this)
                                                .setTitle("Correcto")
                                                .setMessage("Tarjeta creada correctamente")
                                                .show();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                Toast.makeText(AddCardActivity.this, "No se creó la tarjeta", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        textViewRetiredCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddCardActivity.this)
                        .setTitle("Crear tarjeta")
                        .setMessage("¿Quiere crear una tarjeta tipo jubilado?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AddCardActivity.this, "Se creó la tarjeta", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                Toast.makeText(AddCardActivity.this, "No se creó la tarjeta", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
        textViewStudentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(AddCardActivity.this)
                        .setTitle("Crear tarjeta")
                        .setMessage("¿Quiere crear una tarjeta tipo estudiante?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(AddCardActivity.this, "Se creó la tarjeta", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //dialog.cancel();
                                Toast.makeText(AddCardActivity.this, "No se creó la tarjeta", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();
            }
        });
    }
}
