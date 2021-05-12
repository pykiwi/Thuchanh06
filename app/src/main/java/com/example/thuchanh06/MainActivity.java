package com.example.thuchanh06;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView ls;
    private List<Student> list = new ArrayList<Student>();
    private Button btnSave;
    private EditText name;
    private EditText address;
    private EditText phoneNumber;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView ls = (ListView) findViewById(R.id.listStudent);

        DatabaseHandler db = new DatabaseHandler(this);

        List<Student> list = db.getAllStudents();
        ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, list);
        ls.setAdapter(arrayAdapter);
        this.name = findViewById(R.id.name);
        this.address = findViewById(R.id.address);
        this.phoneNumber = findViewById(R.id.phoneNumber);
        this.btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                buttonSaveClicked(db);
            }
        });

    }
    public void buttonSaveClicked(DatabaseHandler db)  {
        String name = this.name.getText().toString();
        String address = this.address.getText().toString();
        String phonenumber = this.phoneNumber.getText().toString();
        this.student = new Student(name, address, phonenumber);
        db.addStudent(this.student);
        this.list = db.getAllStudents();


    }


}