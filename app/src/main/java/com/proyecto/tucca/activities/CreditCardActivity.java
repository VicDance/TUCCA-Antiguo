package com.proyecto.tucca.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.tucca.fragments.MainFragment;
import com.proyecto.tucca.model.CreditCard;
import com.proyecto.tucca.R;
import com.proyecto.tucca.adapters.CardsAdapter;
import com.proyecto.tucca.adapters.CreditCardsAdapter;

import java.io.IOException;
import java.util.ArrayList;

import static com.proyecto.tucca.activities.MainActivity.dataIn;
import static com.proyecto.tucca.activities.MainActivity.dataOut;

public class CreditCardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CreditCardsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;
    private ArrayList<CreditCard> creditCardItems = null;
    private Button btnNewCredit;
    private int size;
    private String[] newDatos;
    private SensorManager sensorManager;
    private Sensor gyroscopeSensor;
    private SensorEventListener sensorEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        creditCardItems = new ArrayList<CreditCard>();
        try {
            dataOut.writeUTF("tarjetas");
            dataOut.flush();
            //System.out.println(dataIn.readUTF());
            size = dataIn.readInt();
            //System.out.println("size" + size);
            CreditCard creditCard = null;
            for(int i = 0; i < size; i++) {
                String datos;
                datos = dataIn.readUTF();
                newDatos = datos.split("-");
                creditCard = new CreditCard(newDatos[3], newDatos[0], newDatos[2]);
                creditCardItems.add(creditCard);
                //System.out.println(creditCard.getCardUser());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buildRecycler();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {

            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };
        btnNewCredit = findViewById(R.id.btn_new_credit_card);
        btnNewCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreditCardActivity.this, NewCreditCardActivity.class);
                startActivity(intent);
            }
        });
    }

    private void buildRecycler(){
        recyclerView = findViewById(R.id.recycler_view_credit_cards);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CreditCardsAdapter(creditCardItems);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new CreditCardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                /*new AlertDialog.Builder(MainActivity.this)
                        .setTitle("No se pudo conectar")
                        .setMessage("Usuario o contraseña incorrectos")
                        .show();*/
            }
        });
    }
}
