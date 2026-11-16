package com.example.battaglia_navale;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewGameActivity extends AppCompatActivity {

    private static final int N = 10;
    private final View[][] celle = new View[N][N];
    private GridLayout grid;
    private TextView txtStato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newgame);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.newgame), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtStato = findViewById(R.id.txtStato);
        grid = findViewById(R.id.gridCampo);

        grid.post(new Runnable() {
            @Override
            public void run() {
                costruisciGriglia();
            }
        });

    }

    private void costruisciGriglia() {
        int lato = grid.getWidth() / N;
        int margine = 2;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                View cella = new View(this);
                cella.setBackgroundColor(Color.parseColor("#4FC3F7")); //blu

                GridLayout.LayoutParams p = new GridLayout.LayoutParams(
                        GridLayout.spec(r), GridLayout.spec(c));
                p.width = lato - margine * 2;
                p.height = lato - margine * 2;
                p.setMargins(margine, margine, margine, margine);

                final int riga = r;
                final int colonna = c;
                cella.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onCellaPremuta(riga, colonna);
                    }
                });

                grid.addView(cella, p);
                celle[r][c] = cella;
            }
        }
    }

    private void onCellaPremuta(int riga, int colonna) {
        // Qui chiamerai la tua logica, ad esempio:
        // risultato = campo.spara(riga, colonna);
        txtStato.setText("Colpita cella " + riga + "," + colonna);
        colora(riga, colonna, Color.RED);
    }

    private void colora(int riga, int colonna, int colore) {
        celle[riga][colonna].setBackgroundColor(colore);
    }
}
