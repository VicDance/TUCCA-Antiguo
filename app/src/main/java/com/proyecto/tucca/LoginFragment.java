package com.proyecto.tucca;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.proyecto.tucca.MainActivity.cliente;

public class LoginFragment extends Fragment {
    private View view;
    private Button buttonRegister;
    private Button buttonLogin;
    private EditText editTextUser;
    private EditText editTextPassword;
    public static boolean login;
    private DataOutputStream dataOut;
    private DataInputStream dataIn;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_loggin, container, false);
        buttonRegister = view.findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        editTextUser = view.findViewById(R.id.edit_text_user);
        editTextPassword = view.findViewById(R.id.edit_text_password);
        buttonLogin = view.findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUser.getText().length() != 0 && editTextPassword.getText().length() != 0) {
                    try {
                        dataOut = new DataOutputStream(cliente.getOutputStream());
                        dataIn = new DataInputStream(cliente.getInputStream());
                        dataOut.writeUTF("inicio");
                        dataOut.flush();
                        dataOut.writeUTF(editTextUser.getText().toString());
                        dataOut.flush();
                        dataOut.writeUTF(editTextPassword.getText().toString());
                        dataOut.flush();
                        String respuesta = dataIn.readUTF();
                        if(respuesta.equalsIgnoreCase("correcto")){
                            login = true;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    new AlertDialog.Builder(getContext())
                            .setTitle("No se pudo conectar")
                            .setMessage("Alguno de los campos está vacío")
                            .show();
                }
            }
        });
        return view;
    }



    private void comprobarCredenciales(){

    }
}
