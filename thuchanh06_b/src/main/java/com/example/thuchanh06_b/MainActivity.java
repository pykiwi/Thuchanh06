package com.example.thuchanh06_b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView ls;
     private List<Place> list = new ArrayList<Place>();
     private Button btnSave;
     private Button btnCancel;
     private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView ls = (ListView) findViewById(R.id.listPlace);
        DatabaseHandler db = new DatabaseHandler(this);

        Place place = new Place("Da Lat");
        Place place1 =  new Place("Phu Yen");
        db.addPlace(place);
        db.addPlace(place1);
        List<Place> list = db.getAllPlaces();

        ArrayAdapter<Place> arrayAdapter = new ArrayAdapter<Place>(this, android.R.layout.simple_list_item_1, list);
        ls.setAdapter(arrayAdapter);

        this.name = findViewById(R.id.name);
    }
}