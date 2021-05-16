package com.example.thuchanh06_b;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "travelManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "places";

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_place_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s TEXT)", TABLE_NAME,
                KEY_ID, KEY_NAME);
        db.execSQL(create_place_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_place_table = String.format("DROP TABLE IF EXIST %s", TABLE_NAME);
        db.execSQL(drop_place_table);
        onCreate(db);
    }

    public Place getPlaces(int placeId){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, KEY_ID + " = ?", new String[] { String.valueOf(placeId) },null, null, null);
        if(cursor != null)
            cursor.moveToFirst();
        Place place = new Place(cursor.getInt(0), cursor.getString(1));
        return place;
    }

    public void addPlace(Place place){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, place.getName());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }
    public List<Place> getAllPlaces() {
        List<Place> placeList = new ArrayList<>();
        String q1 = "SELECT " ;
        String q = "* FROM " + TABLE_NAME;
        String query = q1.concat(q);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from places", null);
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false){
            Place place = new Place(cursor.getInt(0), cursor.getString(1));
            placeList.add(place);
            cursor.moveToNext();
        }
        return placeList;
    }
}
