package com.proyecto.tucca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class StopsFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabItem tabItem1, tabItem2;
    PageAdapter pageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabs_stops, container, false);
        /*listItems = new ArrayList<>();
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

        recyclerView = view.findViewById(R.id.recyclerViewStops);
        buildRecyclerView();*/

        tabLayout = view.findViewById(R.id.tabLayout);
        tabItem1 = view.findViewById(R.id.tab1);
        tabItem2 = view.findViewById(R.id.tab2);
        viewPager = view.findViewById(R.id.viewPagerStops);
        pageAdapter = new PageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        /*viewPager.setAdapter(pageAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
        return view;
    }
}
