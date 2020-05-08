package com.proyecto.tucca.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.proyecto.tucca.R;
import com.proyecto.tucca.adapters.StopsAdapter;
import com.proyecto.tucca.StopsItem;
import com.proyecto.tucca.activities.StopsActivity;

import java.util.ArrayList;

public class FragmentTabDestinoSalida extends Fragment {
    private ArrayList<StopsItem> listItems;
    private RecyclerView recyclerView;
    private StopsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_destino_salida, container, false);
        listItems = new ArrayList<>();
        listItems.add(new StopsItem(("CORTADURA")));
        listItems.add(new StopsItem(("TELEGRAFÍA")));
        listItems.add(new StopsItem(("ESTADIO")));
        listItems.add(new StopsItem(("BALNEARIO")));
        listItems.add(new StopsItem(("RESIDENCIA")));
        listItems.add(new StopsItem(("SAN JOSÉ")));
        listItems.add(new StopsItem(("SAN FELIPE")));
        listItems.add(new StopsItem(("CUARTELES")));
        listItems.add(new StopsItem(("COMISARIA")));
        listItems.add(new StopsItem(("PUERTAS DE TIERRA")));
        listItems.add(new StopsItem(("AVDA. ANDALUCIA")));
        listItems.add(new StopsItem(("AVDA. DEL PUERTO")));
        listItems.add(new StopsItem(("PLAZA ESPAÑA ")));

        recyclerView = view.findViewById(R.id.recyclerViewFinishStar);
        buildRecyclerView();
        return view;
    }

    private void buildRecyclerView(){
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        adapter = new StopsAdapter(listItems);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new StopsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), StopsActivity.class);
                startActivity(intent);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapStopsFragment()).commit();
            }
        });
    }
}
