package com.example.quizkhelo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    TextView result,name;
    Button btnPrevious,btnShare;
    String score,personName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        result = findViewById(R.id.quizScore);
        score = String.valueOf(getIntent().getIntExtra("score",0))+"/"+String.valueOf(getIntent().getIntExtra("totalQuestions",10));
        result.setText(score);
        name = findViewById(R.id.personName);
        personName = getIntent().getStringExtra("personName");
        name.setText(personName);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnShare = findViewById(R.id.btnShare);
        btnPrevious.setOnClickListener((v)->{
            Intent intent = new Intent(ResultActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        });
        btnShare.setOnClickListener(v -> {
            String message = personName + " scored " + score + " in the QuizKhelo app!";
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT,message);
            shareActivityResultLauncher.launch(Intent.createChooser(intent, "Share your score via"));
        });


    }
    private final ActivityResultLauncher<Intent> shareActivityResultLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Toast.makeText(this, "Thanks for sharing your score!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Sharing canceled.", Toast.LENGTH_SHORT).show();
                }
            });
}