package pl.lab.galinski.mtmproj.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Paweł Galiński
 * 17.12.2015
 */
public class PlacesDbHelper extends SQLiteOpenHelper {

    public PlacesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public PlacesDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL(PlacesDetailsContract.AlternativeIdsTable.CREATE_QUEY);
        sqLiteDatabase.execSQL(PlacesDetailsContract.PlaceDetailsTable.CREATE_QUEY);
        sqLiteDatabase.execSQL(PlacesDetailsContract.ImagesTable.CREATE_QUEY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

 /*   public void insertNewPlace(PlaceDetails details){
    }

    private long insertNewPlaceDetails(){

    }

    private long insertNewImage(){

    }*/


}
