package com.proyecto.tucca;

import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TripFragment extends Fragment {
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private RecyclerView.LayoutManager layoutManager;
    private View view;
    private Retrofit retrofit;
    private FareSystemAPI fareSystemAPI;
    private Spinner spinnerOriginCity;
    private Spinner spinnerDestinyCity;
    private Spinner spinnerOriginCentre;
    private Spinner spinnerDestinyCentre;
    private City[] listaMunicipios;
    private String[] listaNombreMunicipios;
    private Centre[] listaNucleos;
    private String[] listaNombreNucleos;
    private String[] listaLineas;
    private ArrayAdapter<String> adapterOriginCities;
    private ArrayAdapter<String> adapterOriginCentre;
    private ArrayAdapter<String> adapterDestinyCities;
    private ArrayAdapter<String> adapterDestinyCentre;
    private String[] tableHeaders = {String.valueOf(R.string.line), String.valueOf(R.string.stops), String.valueOf(R.string.frequency)};
    private String[] tableRows;
    private Button btnSearch;
    private TextView textViewLines;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trip, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ctan.es/v1/Consorcios/2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        fareSystemAPI = retrofit.create(FareSystemAPI.class);
        listarMunicipios();
        textViewLines = view.findViewById(R.id.text_view_lines);
        btnSearch = view.findViewById(R.id.btn_search_trip);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nucleoOrigen = (String) spinnerOriginCentre.getSelectedItem();
                String nucleoDestino = (String) spinnerDestinyCentre.getSelectedItem();
                /*for(int i = 0; i < listaNucleos.length; i++){
                    if(nucleoOrigen == listaNucleos[i].getNombreNucleo()){
                        int id = listaNucleos[i].getIdNucleo();
                        System.out.println("Id: " + id);
                        listarLineasPorNucleo(id);
                    }
                }*/
            }
        });
        return view;
    }

    private void listarMunicipios(){
        Call<CityList> cityListCall = fareSystemAPI.getCityList();
        cityListCall.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    return;
                }
                final CityList cityList = response.body();
                listaMunicipios = new City[cityList.getCities().size()];
                listaNombreMunicipios = new String[cityList.getCities().size()];
                for(int i = 0; i < cityList.getCities().size(); i++){
                    listaMunicipios[i] = cityList.getCities().get(i);
                    listaNombreMunicipios[i] = cityList.getCities().get(i).getNombreMunicipio();
                }

                setSpinnerOriginCity();
                setSpinnerDestinyCity();
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    private void setSpinnerOriginCity(){
        spinnerOriginCity = view.findViewById(R.id.spinner_origin_city);
        adapterOriginCities = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaNombreMunicipios);
        adapterOriginCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOriginCity.setAdapter(adapterOriginCities);
        spinnerOriginCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ciudad = (String) parent.getSelectedItem();
                for(int i = 0; i < listaMunicipios.length; i++){
                    if(ciudad.equalsIgnoreCase(listaMunicipios[i].getNombreMunicipio())){
                        id = listaMunicipios[i].getIdMunicipio();
                    }
                }
                listarNucleosOrigen(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setSpinnerDestinyCity(){
        spinnerDestinyCity = view.findViewById(R.id.spinner_destiny_city);
        adapterDestinyCities = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, listaNombreMunicipios);
        adapterDestinyCities.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDestinyCity.setAdapter(adapterDestinyCities);
        spinnerDestinyCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String ciudad = (String) parent.getSelectedItem();
                for (int i = 0; i < listaMunicipios.length; i++) {
                    if (ciudad.equalsIgnoreCase(listaMunicipios[i].getNombreMunicipio())) {
                        id = listaMunicipios[i].getIdMunicipio();
                    }
                }
                listarNucleosDestino(id);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getSelectedCity(View view){
        String ciudad = (String) spinnerOriginCity.getSelectedItem();
        long id = 0;
        for(int i = 0; i < listaMunicipios.length; i++){
            if(ciudad.equalsIgnoreCase(listaMunicipios[i].getNombreMunicipio())){
                id = listaMunicipios[i].getIdMunicipio();
            }
        }
        City city = new City(id, ciudad);
        displayCityInfo(city);
    }

    private void displayCityInfo(City city){
        long id = city.getIdMunicipio();
        String name = city.getNombreMunicipio();

        String data = "Id: " + id +". Nombre: " + name;
        Toast.makeText(getContext(), data, Toast.LENGTH_SHORT).show();
    }

    public void listarNucleosOrigen(final long idMunicipio){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ctan.es/v1/Consorcios/2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FareSystemAPI fareSystemAPI = retrofit.create(FareSystemAPI.class);
        Call<CityList> cityListCall = fareSystemAPI.getCentreList(idMunicipio);
        cityListCall.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    return;
                }
                CityList cityList = response.body();
                listaNucleos = new Centre[cityList.getCentreList().size()];
                listaNombreNucleos = new String[cityList.getCentreList().size()];
                for(int i = 0; i < cityList.getCentreList().size(); i++){
                    listaNucleos[i] = cityList.getCentreList().get(i);
                    listaNombreNucleos[i] = cityList.getCentreList().get(i).getNombreNucleo();
                }
                spinnerOriginCentre = view.findViewById(R.id.spinner_origin_centre);
                adapterOriginCentre = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaNombreNucleos);
                adapterOriginCentre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerOriginCentre.setAdapter(adapterOriginCentre);
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    public void listarNucleosDestino(final long idMunicipio){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ctan.es/v1/Consorcios/2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FareSystemAPI fareSystemAPI = retrofit.create(FareSystemAPI.class);
        Call<CityList> cityListCall = fareSystemAPI.getCentreList(idMunicipio);
        cityListCall.enqueue(new Callback<CityList>() {
            @Override
            public void onResponse(Call<CityList> call, Response<CityList> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    return;
                }
                CityList cityList = response.body();
                listaNucleos = new Centre[cityList.getCentreList().size()];
                listaNombreNucleos = new String[cityList.getCentreList().size()];
                for(int i = 0; i < cityList.getCentreList().size(); i++){
                    listaNombreNucleos[i] = cityList.getCentreList().get(i).getNombreNucleo();
                    listaNucleos[i] = cityList.getCentreList().get(i);
                }
                spinnerDestinyCentre = view.findViewById(R.id.spinner_destiny_centre);
                adapterDestinyCentre = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, listaNombreNucleos);
                adapterDestinyCentre.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerDestinyCentre.setAdapter(adapterDestinyCentre);
            }

            @Override
            public void onFailure(Call<CityList> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    public void listarPlanificador(long idLinea){
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ctan.es/v1/Consorcios/2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FareSystemAPI fareSystemAPI = retrofit.create(FareSystemAPI.class);
        Call<LineList> lineListCall = fareSystemAPI.getLineList(idLinea);
        lineListCall.enqueue(new Callback<LineList>() {
            @Override
            public void onResponse(Call<LineList> call, Response<LineList> response) {
                if(!response.isSuccessful()){
                    System.out.println("Code: " + response.code());
                    return;
                }
                LineList lineList = response.body();
                listaLineas = new String[lineList.getLines().size()];
                String cadena = "";
                for(int i = 0; i < lineList.getLines().size(); i++){
                    listaLineas[i] = lineList.getLines().get(i).getNombre();
                }
                /*for(int i = 0; i < listaLineas.length; i++){
                    cadena += listaLineas[i] + "\n";
                    //System.out.println(listaLineas[i]);
                }
                textViewLines.append(cadena);*/
            }

            @Override
            public void onFailure(Call<LineList> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange", newText);

                    return true;
                }

                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit", query);

                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}
