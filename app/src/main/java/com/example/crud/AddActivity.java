package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText user_input, regNo_input, email_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        user_input = findViewById(R.id.user_input);
        regNo_input = findViewById(R.id.regNo_input);
        email_input = findViewById(R.id.email_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(view -> {
            try (MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this)) {
                myDB.addUser(user_input.getText().toString().trim(),
                        regNo_input.getText().toString().trim(),
                        email_input.getText().toString().trim());
            }
        });
    }
}
