package com.proyecto.tucca.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.proyecto.tucca.fragments.FragmentTabDestinoSalida;
import com.proyecto.tucca.fragments.FragmentTabSalidaDestino;

public class PageAdapter extends FragmentPagerAdapter {
    private int numTabs;

    public PageAdapter(FragmentManager fm, int numTabs) {
        super(fm);
        this.numTabs = numTabs;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentTabSalidaDestino();
                break;
            case 1:
                fragment = new FragmentTabDestinoSalida();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return numTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "A Cortadura";
            case 1:
                return "A Plaza España";
        }
        return null;
    }
}
