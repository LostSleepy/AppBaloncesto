package com.example.puntuacionbaloncesto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.MutableLiveData;
import com.example.puntuacionbaloncesto.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private MutableLiveData<Integer> localScore = new MutableLiveData<>(0);
    private MutableLiveData<Integer> visitorScore = new MutableLiveData<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Variables con Data Binding
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLocalScore(localScore);
        binding.setVisitorScore(visitorScore);
        binding.setLifecycleOwner(this);

        // Funciones a los botones de puntuación
        binding.localPlusButton.setOnClickListener(v -> incrementLocalScore(1)); // +1 local
        binding.localTwoPointsButton.setOnClickListener(v -> incrementLocalScore(2)); // +2 local
        binding.visitorPlusButton.setOnClickListener(v -> incrementVisitorScore(1)); // +1 visitante
        binding.visitorTwoPointsButton.setOnClickListener(v -> incrementVisitorScore(2)); // +2 visitante

        // Funciones de restar
        binding.localMinusButton.setOnClickListener(v -> decrementLocalScore(1)); // -1 local
        binding.visitorMinusButton.setOnClickListener(v -> decrementVisitorScore(1)); // -1 visitante

        // Restablecer ambos marcadores a cero (reloj izquierdo)
        binding.restartButton.setOnClickListener(v -> resetScores());

        // Función para abrir la pantalla de detalles (flecha derecha)
        binding.resultsButton.setOnClickListener(v -> openScoreDetails());
    }

    // Función para incrementar puntos local
    private void incrementLocalScore(int points) {
        localScore.setValue(localScore.getValue() + points);
    }

    // Función para incrementar puntos visitante
    private void incrementVisitorScore(int points) {
        visitorScore.setValue(visitorScore.getValue() + points);
    }

    // Función para restar puntos local, sin permitir valores negativos
    private void decrementLocalScore(int points) {
        int currentScore = localScore.getValue() != null ? localScore.getValue() : 0;
        if (currentScore > 0) {
            localScore.setValue(currentScore - points >= 0 ? currentScore - points : 0);
        }
    }

    // Función para restar puntos visitante, sin permitir valores negativos
    private void decrementVisitorScore(int points) {
        int currentScore = visitorScore.getValue() != null ? visitorScore.getValue() : 0;
        if (currentScore > 0) {
            visitorScore.setValue(currentScore - points >= 0 ? currentScore - points : 0);
        }
    }

    // Función para restablecer los puntajes de ambos equipos a cero (reloj izquierdo)
    private void resetScores() {
        localScore.setValue(0);
        visitorScore.setValue(0);
    }

    // Abrir la actividad de detalles (ScoreActivity)
    private void openScoreDetails() {
        Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
        // Pasamos los puntajes a la nueva actividad si lo deseas
        intent.putExtra("localScore", localScore.getValue());
        intent.putExtra("visitorScore", visitorScore.getValue());
        startActivity(intent);
    }
}




