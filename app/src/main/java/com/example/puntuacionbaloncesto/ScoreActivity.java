package com.example.puntuacionbaloncesto;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;
public class ScoreActivity extends AppCompatActivity {

    private TextView localScoreTextView;
    private TextView visitorScoreTextView;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        // Obtener datos enviados desde MainActivity
        localScoreTextView = findViewById(R.id.local_score_text);
        visitorScoreTextView = findViewById(R.id.visitor_score_text);
        resultTextView = findViewById(R.id.result_text);

        // Obtener datos de la intent
        int localScore = getIntent().getIntExtra("localScore", 0);  // Valor por defecto 0
        int visitorScore = getIntent().getIntExtra("visitorScore", 0);  // Valor por defecto 0

        // Mostrar los puntajes en los TextViews
        localScoreTextView.setText(String.valueOf(localScore));
        visitorScoreTextView.setText(String.valueOf(visitorScore));

        // Mostrar el resultado
        String resultMessage = determineResult(localScore, visitorScore);
        resultTextView.setText(resultMessage);
    }

    // Función para determinar el resultado del partido
    private String determineResult(int localScore, int visitorScore) {
        if (localScore > visitorScore) {
            return "¡Ganó el equipo local! 😄";
        } else if (visitorScore > localScore) {
            return "¡Ganaron los visitantes! 😄";
        } else {
            return "😟 Empate 😟";
        }
    }
}


