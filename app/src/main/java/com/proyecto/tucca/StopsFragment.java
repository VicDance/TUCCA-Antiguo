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
    ViewPager viewPager;
    private TabItem tabItem1, tabItem2;
    PageAdapter pageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tabs_stops, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        pageAdapter = new PageAdapter(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager = view.findViewById(R.id.viewPagerStops);
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}
