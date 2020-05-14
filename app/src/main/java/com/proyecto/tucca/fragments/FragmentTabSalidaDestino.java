package com.proyecto.tucca.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.proyecto.tucca.activities.MapStopsActivity;
import com.proyecto.tucca.R;
import com.proyecto.tucca.adapters.StopsAdapter;
import com.proyecto.tucca.StopsItem;

import java.util.ArrayList;

public class FragmentTabSalidaDestino extends Fragment {
    private ArrayList<StopsItem> listItems;
    private RecyclerView recyclerView;
    private StopsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_salida_destino, container, false);

        listItems = new ArrayList<>();
        listItems.add(new StopsItem(("PLAZA ESPAÑA ")));
        listItems.add(new StopsItem(("AVDA. DEL PUERTO")));
        listItems.add(new StopsItem(("PUERTAS DE TIERRA")));
        listItems.add(new StopsItem(("AVDA. ANDALUCIA")));
        listItems.add(new StopsItem(("COMISARIA")));
        listItems.add(new StopsItem(("CUARTELES")));
        listItems.add(new StopsItem(("SAN FELIPE")));
        listItems.add(new StopsItem(("SAN JOSÉ")));
        listItems.add(new StopsItem(("RESIDENCIA")));
        listItems.add(new StopsItem(("BALNEARIO")));
        listItems.add(new StopsItem(("ESTADIO")));
        listItems.add(new StopsItem(("TELEGRAFÍA")));
        listItems.add(new StopsItem(("CORTADURA")));

        recyclerView = view.findViewById(R.id.recyclerViewStartFinish);
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
                Intent intent = new Intent(getActivity(), MapStopsActivity.class);
                startActivity(intent);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapStopsFragment()).commit();
            }
        });
    }
}