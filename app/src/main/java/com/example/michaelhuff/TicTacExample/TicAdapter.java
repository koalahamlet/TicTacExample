package com.example.michaelhuff.TicTacExample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import static com.example.michaelhuff.TicTacExample.Constants.*;

class TicAdapter extends RecyclerView.Adapter<TicAdapter.CellViewHolder> {

    private final GameSystem game;
    private final Drawable drawO;
    private final Drawable drawX;
    private final Drawable drawEmpty;

    char[] board;


    public TicAdapter(GameSystem game, Context context) {
        this.game = game;
        this.board = game.getBoard();
        drawO = ContextCompat.getDrawable(context, R.drawable.o);
        drawX = ContextCompat.getDrawable(context, R.drawable.x);
        drawEmpty = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background);
    }

    @NonNull
    @Override
    public CellViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_cell, viewGroup, false);
        return new CellViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final CellViewHolder cellViewHolder, final int i) {

        if (board[i] == O) {
            cellViewHolder.imageView.setImageDrawable(drawO);
        } else if (board[i] == X) {
            cellViewHolder.imageView.setImageDrawable(drawX);
        } else {
            cellViewHolder.imageView.setImageDrawable(drawEmpty);
        }

        cellViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.play(i)) {
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return board.length;
    }

    public class CellViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CellViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
