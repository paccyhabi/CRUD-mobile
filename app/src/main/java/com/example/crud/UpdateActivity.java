package com.example.crud;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText user_input, regNo_input, email_input;
    Button update_button, delete_button;

    String id, username, regNo, email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        user_input = findViewById(R.id.user_input2);
        regNo_input = findViewById(R.id.regNo_input2);
        email_input = findViewById(R.id.email_input2);
        update_button = findViewById(R.id.update_button);
        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(username);
        }

        update_button.setOnClickListener(view -> {
            //And only then we call this
            try (MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this)) {
                username = user_input.getText().toString().trim();
                regNo = regNo_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                myDB.updateData(id, username, regNo, email);
            }
        });
        delete_button.setOnClickListener(view -> confirmDialog());

    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("username") &&
                getIntent().hasExtra("regNo") && getIntent().hasExtra("email")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            username = getIntent().getStringExtra("username");
            regNo = getIntent().getStringExtra("regNo");
            email = getIntent().getStringExtra("email");

            //Setting Intent Data
            user_input.setText(username);
            regNo_input.setText(regNo);
            email_input.setText(email);
            Log.d("paCcy", username+" "+regNo+" "+email);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + username + " ?");
        builder.setMessage("Are you sure you want to delete " + username + " ?");
        builder.setPositiveButton("Yes", (dialogInterface, i) -> {
            try (MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this)) {
                myDB.deleteOneRow(id);
            }
            finish();
        });
        builder.setNegativeButton("No", (dialogInterface, i) -> {

        });
        builder.create().show();
    }
}
