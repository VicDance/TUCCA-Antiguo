package com.proyecto.tucca.fragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.proyecto.tucca.R;
import com.proyecto.tucca.activities.CreditCardActivity;
import com.proyecto.tucca.activities.MenuActivity;
import com.proyecto.tucca.model.User;

import java.io.IOException;

import static com.proyecto.tucca.activities.MainActivity.dataIn;
import static com.proyecto.tucca.activities.MainActivity.dataOut;
import static com.proyecto.tucca.activities.MainActivity.invitado;
import static com.proyecto.tucca.activities.MainActivity.login;
import static com.proyecto.tucca.activities.MainActivity.nombreCliente;

public class MeFragment extends Fragment {
    private TextView textViewUser;
    private TextView textViewBorn;
    private TextView textViewEmail;
    private TextView textViewPhone;
    private Button btnExit;
    private TextView textViewName;
    public static User user;
    private View view;
    private String nombre;
    private String contraseña;
    private String correo;
    private String tfno;
    private String fecha_nac;
    private String[] newDatos;
    private int id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_me, container, false);
        btnExit = view.findViewById(R.id.btn_exit);
        textViewUser = view.findViewById(R.id.text_view_user);
        textViewBorn= view.findViewById(R.id.text_view_born_date);
        textViewEmail = view.findViewById(R.id.text_view_email);
        textViewPhone = view.findViewById(R.id.text_view_phone);
        textViewName = view.findViewById(R.id.text_view_name);
        String datos = null;
        //try {
            if(login && !invitado) {
                /*dataOut.writeUTF("cliente");
                dataOut.flush();
                dataOut.writeUTF(nombreCliente);
                dataOut.flush();
                datos = dataIn.readUTF();
                newDatos = datos.split("/");
                nombre = newDatos[0];
                contraseña = newDatos[1];
                correo = newDatos[2];
                fecha_nac = newDatos[3];
                tfno = newDatos[4];

                textViewUser.setText(nombre);
                textViewBorn.setText(fecha_nac);
                textViewEmail.setText(correo);
                textViewPhone.setText(tfno);*/
            }

            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    login = false;
                    Intent intent = new Intent(getContext(), MenuActivity.class);
                    startActivity(intent);
                    //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
                }
            });
        /*} catch (IOException e) {
            e.printStackTrace();
        }*/
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //menu.clear();
        //super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.add_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_add_card:
                item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Intent intent = new Intent(getContext(), CreditCardActivity.class);
                        startActivity(intent);
                        return true;
                    }
                });
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
