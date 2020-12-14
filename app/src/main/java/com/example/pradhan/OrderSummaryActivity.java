package com.example.pradhan;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class OrderSummaryActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter arrayAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    String data;
    TextView textView_head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);

        data=getIntent().getExtras().getString("name").toString();

        listView=findViewById(R.id.product_summary_list);
        textView_head=findViewById(R.id.order_summary_heading);
        textView_head.setText(data);

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


    }
}