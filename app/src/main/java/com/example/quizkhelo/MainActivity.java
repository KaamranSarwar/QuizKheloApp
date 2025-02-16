package com.example.quizkhelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextInputEditText name;
    Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.nameText);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener((v)->{
            String personName = Objects.requireNonNull(name.getText()).toString();
            if(personName.isEmpty() )
            {
                Toast.makeText(MainActivity.this,"Please Enter your name first",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("personName",personName);
                startActivity(intent);
                finish();
            }

        });




    }
}