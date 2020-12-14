package com.example.pradhan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class StocksActivity extends AppCompatActivity {
    ListView listView;
    ArrayAdapter arrayAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stocks);

        listView=findViewById(R.id.stocks_list);

        firebaseAuth=FirebaseAuth.getInstance();
        firebaseFirestore=FirebaseFirestore.getInstance();


        StockListAdapter stockListAdapter=new StockListAdapter(this,R.layout.item_stock_list);

        firebaseFirestore.collection("Details").document(firebaseAuth.getCurrentUser().getUid().toString())
                .collection("Products").orderBy("name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

               stockListAdapter.clear();
               stockListAdapter.addAll(value.toObjects(ItemSstockPPojo.class));
               listView.setAdapter(stockListAdapter);
               int toltal=value.size();
               listView.smoothScrollToPosition(1);
               listView.setSelection(0);



            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ItemSstockPPojo obj=stockListAdapter.getItem(position);
                String name=obj.name;
                Intent intent=new Intent(StocksActivity.this,OrderSummaryActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });



    }
}