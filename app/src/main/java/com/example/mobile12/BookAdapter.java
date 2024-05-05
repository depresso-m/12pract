package com.example.mobile12;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private ArrayList<Book> books;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView authorTextView;

        public ViewHolder(View view) {
            super(view);
            titleTextView = view.findViewById(R.id.book_title);
            authorTextView = view.findViewById(R.id.book_author);
        }

        public void bind(Book book) {
            titleTextView.setText(book.getTitle());
            authorTextView.setText(book.getAuthor());
        }
    }

    public BookAdapter(ArrayList<Book> books) {
        if (books == null) {
            throw new IllegalArgumentException("Books list cannot be null");
        }
        this.books = books;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> newBooks) {
        if (newBooks == null) {
            throw new IllegalArgumentException("Books list cannot be null");
        }
        this.books = newBooks;
        notifyDataSetChanged();
    }
}

