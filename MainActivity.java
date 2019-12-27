package com.example.mydata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper mydb;
    EditText time,period,place,delt,table;
    Button add,show,del;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydb = new DatabaseHelper(this);
        time = findViewById(R.id.e1);
        period = findViewById(R.id.editperiod);
        place = findViewById(R.id.editplace);
        add = findViewById(R.id.add);
        show = findViewById(R.id.show);
        del = findViewById(R.id.del);
        delt = findViewById(R.id.editdel);
        table = findViewById(R.id.tablename);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isinsetred = mydb.insertData(time.getText().toString(),period.getText().toString(),place.getText().toString(),table.getText().toString().toUpperCase());
                if(isinsetred) {
                    Toast.makeText(MainActivity.this, "inserted", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(MainActivity.this,"not inserted",Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getdata(table.getText().toString());
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No data for"+table.getText().toString(),Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    StringBuffer buffer = new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append(res.getString(0)+"     ");
                        buffer.append(res.getString(1)+"     ");
                        buffer.append(res.getString(2)+"     ");
                    }
                    showmessage("Table",buffer.toString());
                }
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ID = delt.getText().toString();
                mydb.delete(ID,table.getText().toString());
            }
        });
    }
    public void showmessage(String title, String table){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(table);
        builder.show();

    }
}
