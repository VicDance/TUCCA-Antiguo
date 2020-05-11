package com.proyecto.tucca.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.proyecto.tucca.model.CreditCard;
import com.proyecto.tucca.R;
import com.proyecto.tucca.adapters.CardsAdapter;
import com.proyecto.tucca.adapters.CreditCardsAdapter;

import java.util.ArrayList;

public class CreditCardActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CreditCardsAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private TextView textView;
    private ArrayList<CreditCard> creditCardItems = null;
    private Button btnNewCredit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit_card);
        creditCardItems = new ArrayList<CreditCard>();
        /*creditCardItems.add(new CreditCardItem("Vic","1234"));
        creditCardItems.add(new CreditCardItem("Vic","5678"));*/
        buildRecycler();
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
    }
}
