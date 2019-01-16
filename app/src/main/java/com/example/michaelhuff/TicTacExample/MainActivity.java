package com.example.michaelhuff.TicTacExample;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;

import java.util.List;

public class MainActivity extends AppCompatActivity implements GameSystem.UpdateViewListener {

    GameSystem newGame = new GameSystem(this);
    TextView winnerTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        winnerTextView = findViewById(R.id.textView);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        Drawable d = ContextCompat.getDrawable(this, R.drawable.divider_drawable);

        recyclerView.addItemDecoration(new GridDividerItemDecoration(d, d, 3));
        TicAdapter adapter = new TicAdapter(newGame, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void notifyWon(GameSystem.Player player) {
        winnerTextView.setText("player " +player.letter+" won the game");
    }

    @Override
    public void updatePlayerName(char x) {
        winnerTextView.setText("Player "+x+"'s turn");
    }

}
