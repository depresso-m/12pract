package com.example.mobile12;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.database.sqlite.SQLiteDatabase;

public class BookProvider extends ContentProvider {
    private SQLiteDatabase db;
    public static final Uri CONTENT_URI = Uri.parse("content://com.example.mobile12.provider/books");

    @Override
    public boolean onCreate()
    {
        DatabaseHelper dbHelper = new DatabaseHelper(getContext());
        db = dbHelper.getWritableDatabase();
        return (db != null);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder)
    {
        return db.query("books", projection, selection, selectionArgs, null, null, sortOrder);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        long rowID = db.insert("books", "", values);
        Uri _uri = ContentUris.withAppendedId(CONTENT_URI, rowID);
        getContext().getContentResolver().notifyChange(_uri, null);
        return _uri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        int count = db.delete("books", selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        int count = db.update("books", values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri)
    {
        return "vnd.android.cursor.dir/vnd.example.books";
    }
}

