package com.example.conor.moblilecourseworkapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 * Created by conor on 16/12/2015.
 */
public class mapDataDBmgr extends SQLiteOpenHelper{
    private static final int DB_VER = 1;
    private static final String DB_PATH = "/data/data/com.example.conor.moblilecourseworkapp/databases/";//database filepath
    private static final String DB_NAME = "mapEKFamous5.s3db";//name of database file
    private static final String TBL_MAPEKFAME = "mapEKFame5";
//column values
    public static final String COL_ENTRYID = "entryID";
    public static final String COL_SURNAME = "Surname";
    public static final String COL_FIRSTNAME = "FirstName";
    public static final String COL_STARSIGN = "StarSign";
    public static final String COL_OCCUPATION = "Occupation";
    public static final String COL_LATITUDE = "Latitude";
    public static final String COL_LONGITUDE = "Longitude";
//declares context
    private final Context appContext;
//constructor
    public mapDataDBmgr(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.appContext = context;//sets context
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_MAPEKFAME_TABLE = "CREATE TABLE IF NOT EXISTS " +
                TBL_MAPEKFAME + "("
                + COL_ENTRYID + " INTEGER PRIMARY KEY," + COL_SURNAME
                + " TEXT," + COL_FIRSTNAME + " TEXT," + COL_STARSIGN + " TEXT,"
                + COL_OCCUPATION + " TEXT" + COL_LATITUDE + " FLOAT" + COL_LONGITUDE + " FLOAT" +")";//a string frame for the table
        db.execSQL(CREATE_MAPEKFAME_TABLE);//creates table

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion > oldVersion)//checks for an updated table
        {
            db.execSQL("DROP TABLE IF EXISTS " + TBL_MAPEKFAME);
            onCreate(db);//calls database creation again
        }
    }

    // ================================================================================
    // Creates a empty database on the system and rewrites it with your own database.
    // ================================================================================
    public void dbCreate() throws IOException {

        boolean dbExist = dbCheck();//

        if(!dbExist){
            //By calling this method an empty database will be created into the default system path
            //of your application so we can overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDBFromAssets();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    // ============================================================================================
    // Check if the database already exist to avoid re-copying the file each time you open the application.
    // @return true if it exists, false if it doesn't
    // ============================================================================================
    private boolean dbCheck(){

        SQLiteDatabase db = null;//sets to null

        try{
            String dbPath = DB_PATH + DB_NAME; //form a String filepath
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);//opensdatabase
            db.setLocale(Locale.getDefault());//sets locale
            db.setVersion(1);//sets version

        }catch(SQLiteException e){//catches if the database isnt found

            Log.e("SQLHelper","Database not Found!");

        }

        if(db != null){//if its not null then close the database

            db.close();

        }

        return db != null ? true : false;//return the value of whether the database is not null
    }

    // ============================================================================================
    // Copies your database from your local assets-folder to the just created empty database in the
    // system folder, from where it can be accessed and handled.
    // This is done by transfering bytestream.
    // ============================================================================================
    private void copyDBFromAssets() throws IOException{

        InputStream dbInput = null;//nulls input
        OutputStream dbOutput = null;//nulls output
        String dbFileName = DB_PATH + DB_NAME;//forms String filename

        try {

            dbInput = appContext.getAssets().open(DB_NAME);//attempt to open database
            dbOutput = new FileOutputStream(dbFileName);
            //transfer bytes from the dbInput to the dbOutput
            byte[] buffer = new byte[1024];
            int length;
            while ((length = dbInput.read(buffer)) > 0) {
                dbOutput.write(buffer, 0, length);
            }

            //Close the streams
            dbOutput.flush();//flush output
            dbOutput.close();//close output
            dbInput.close();//close input
        } catch (IOException e)//catch any errors in copying the database
        {
            throw new Error("Problems copying DB!");
        }
    }


    public void addaMapEKFameEntry(mapData aMapEKFame) {

        ContentValues values = new ContentValues();//new ContentValues instance
        values.put(COL_SURNAME, aMapEKFame.getSurname());//put surname in values
        values.put(COL_FIRSTNAME, aMapEKFame.getFirstname());//put firstname in values
        values.put(COL_STARSIGN, aMapEKFame.getStarSign());//put starsign in values
        values.put(COL_OCCUPATION, aMapEKFame.getOccupation());//put occupation in values
        values.put(COL_LATITUDE, aMapEKFame.getLatitude());//put latitude in values
        values.put(COL_LONGITUDE, aMapEKFame.getLongitude());//put longitude in values

        SQLiteDatabase db = this.getWritableDatabase();//set dp to the database

        db.insert(TBL_MAPEKFAME, null, values);//insert information into the database
        db.close();//close the database
    }

    public mapData getMapEKFameEntry(String aMapEKFameEntry) {
        String query = "Select * FROM " + TBL_MAPEKFAME + " WHERE " + COL_SURNAME + " =  \"" + aMapEKFameEntry + "\"";//query for the database

        SQLiteDatabase db = this.getReadableDatabase();//get the databasse

        Cursor cursor = db.rawQuery(query, null);//cursor = the answer of the query

        mapData MapDataEntry = new mapData();//new instance of map data

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            MapDataEntry.setEntryID(Integer.parseInt(cursor.getString(0)));//Set the information entries on the map
            MapDataEntry.setSurname(cursor.getString(1));
            MapDataEntry.setFirstname(cursor.getString(2));
            MapDataEntry.setStarSign(cursor.getString(3));
            MapDataEntry.setOccupation(cursor.getString(4));
            MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(5)));
            MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(6)));
            cursor.close();
        } else {
            MapDataEntry = null;//null map data if cursor cannot be moved to first
        }
        db.close();
        return MapDataEntry;//return the information
    }

   // public boolean removeaMapEKFameEntry(String aMapEKFameEntry) {

   //     boolean result = false;

  //      String query = "Select * FROM " + TBL_MAPEKFAME + " WHERE " + COL_SURNAME + " =  \"" + aMapEKFameEntry + "\"";

   //     SQLiteDatabase db = this.getWritableDatabase();

       // Cursor cursor = db.rawQuery(query, null);

     //   mcStarSignsInfo StarSignsInfo = new mcStarSignsInfo();

    //   if (cursor.moveToFirst()) {
      //      StarSignsInfo.setStarSignID(Integer.parseInt(cursor.getString(0)));
      //      db.delete(TBL_MAPEKFAME, COL_ENTRYID + " = ?",
      //              new String[] { String.valueOf(StarSignsInfo.getStarSignID()) });
      //      cursor.close();
     //       result = true;
      //  }
    //    db.close();
     //   return result;
   // }

    public List<mapData> allMapData()//list all map data
    {
        String query = "Select * FROM " + TBL_MAPEKFAME;//database query
        List<mapData> mcMapDataList = new ArrayList<mapData>();//new array list

        SQLiteDatabase db = this.getReadableDatabase();//get the database

        Cursor cursor = db.rawQuery(query, null);//send the query

        if (cursor.moveToFirst()) {
            while (cursor.isAfterLast()==false) {
                mapData MapDataEntry = new mapData();//Set the map information
                MapDataEntry.setEntryID(Integer.parseInt(cursor.getString(0)));
                MapDataEntry.setSurname(cursor.getString(1));
                MapDataEntry.setFirstname(cursor.getString(2));
                MapDataEntry.setStarSign(cursor.getString(3));
                MapDataEntry.setOccupation(cursor.getString(4));
                MapDataEntry.setLatitude(Float.parseFloat(cursor.getString(5)));
                MapDataEntry.setLongitude(Float.parseFloat(cursor.getString(6)));
                mcMapDataList.add(MapDataEntry);
                cursor.moveToNext();
            }
        } else {
            mcMapDataList.add(null);//add a null in the event that the curser is not after the last data entry
        }
        db.close();//close database
        return mcMapDataList;//return the mcMapDataList values
    }
}
