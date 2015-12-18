package pl.lab.galinski.mtmproj.database;

import android.provider.BaseColumns;

/**
 * Created by Paweł Galiński
 * 16.12.2015
 */
public class PlacesDetailsContract {

public static final String DATABASE_NAME = "places";

    public static abstract class PlaceDetailsTable implements BaseColumns {
        public static final String TABLE_NAME = "place_details";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_FORMATTED_ADDRESS = "formatted_address";
        public static final String COLUMN_NAME_ICON_URL = "icon_url";
        public static final String COLUMN_NAME_LATITUDE = "latitude";
        public static final String COLUMN_NAME_LONGITUDE = "longitude";
        public static final String COLUMN_NAME_RATING = "rating";
        public static final String COLUMN_NAME_REFERENCE = "reference";

        public static final String CREATE_QUEY =
                "CREATE TABLE " + TABLE_NAME + "( " +
                        COLUMN_NAME_ID + " TEXT, " +
                        COLUMN_NAME_NAME + " TEXT, " +
                        COLUMN_NAME_FORMATTED_ADDRESS + " TEXT, " +
                        COLUMN_NAME_ICON_URL + " TEXT, " +
                        COLUMN_NAME_LATITUDE + " REAL, " +
                        COLUMN_NAME_LONGITUDE + " REAL, " +
                        COLUMN_NAME_RATING + " REAL, " +
                        COLUMN_NAME_REFERENCE + " TEXT" +
                ");";
    }

    public static abstract class AlternativeIdsTable implements BaseColumns {
        public static final String TABLE_NAME = "alternative_ids";
        public static final String COLUMN_PLACE_ID = "place_id";
        public static final String COLUMN_NAME_SCOPE = "scope";

        public static final String CREATE_QUEY =
                "CREATE TABLE " + TABLE_NAME + "( " +
                        COLUMN_PLACE_ID + " TEXT, " +
                        COLUMN_NAME_SCOPE + " TEXT" +
                        ");";
    }

    public static abstract class ImagesTable implements BaseColumns {
        public static final String TABLE_NAME = "Images";
        public static final String COLUMN_PLACE_ID = "place_id";
        public static final String COLUMN_NAME_IMAGE = "image";

        public static final String CREATE_QUEY =
                "CREATE TABLE " + TABLE_NAME + "( " +
                        COLUMN_PLACE_ID + " TEXT, " +
                        COLUMN_NAME_IMAGE + " BLOB" +
                        ");";
    }

}
