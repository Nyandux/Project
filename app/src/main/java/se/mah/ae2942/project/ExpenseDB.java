package se.mah.ae2942.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Iterator;

/**
 * Created by Arbie A on 18/10/15.
 */
public class ExpenseDB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "expense.db";
    public static final String TABLE_NAME = "expense_table";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_TITLE = "TITLE";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final String COLUMN_AMOUNT = "AMOUNT";
    public static final String COLUMN_DATE = "DATE";
    public static final String COLUMN_LATITUDE = "LATITUD";
    public static final String COLUMN_LONGITUD = "LONGITUD";


    /**
     * Constructor
     * @param context - application enviroment
     */
    public ExpenseDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +
                TABLE_NAME + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_AMOUNT + " REAL, " +
                COLUMN_DATE + " INTEGER, " +
                COLUMN_LATITUDE + " TEXT, " +
                COLUMN_LONGITUD + " TEXT);");
    }

    /**
     * Updates table schema to requested version
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }



    /**
     * Inserts data to database
     * @param expense - expense to add to database
     * @return - returns false if an error acurred
     */
    public boolean insertData(Expense expense) { // add coordinate
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, expense.getTitle());
        contentValues.put(COLUMN_CATEGORY, expense.getCategory());
        contentValues.put(COLUMN_AMOUNT, expense.getAmount());
        contentValues.put(COLUMN_DATE, expense.getDate());
        contentValues.put(COLUMN_LONGITUD, expense.getLatitude());
        contentValues.put(COLUMN_LATITUDE, expense.getLongitude());
        //put values for coordinate
        long res = DB.insert(TABLE_NAME, null, contentValues);

        if (res == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Expense[] getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);

        Expense[] expense = new Expense[result.getCount()];

        for(int i=0; i<expense.length; i++){
            result.moveToPosition(i);
            expense[i] = new Expense(result.getString(result.getColumnIndex(COLUMN_TITLE)),
                    result.getString(result.getColumnIndex(COLUMN_CATEGORY)),
                    Double.parseDouble(result.getString(result.getColumnIndex(COLUMN_AMOUNT))),
                    result.getString(result.getColumnIndex(COLUMN_DATE)),
                    Double.parseDouble(result.getString(result.getColumnIndex(COLUMN_LATITUDE))),
                    Double.parseDouble(result.getString(result.getColumnIndex(COLUMN_LONGITUD))));
        }
        result.close();
        return expense;
    }
    
    public void dropTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(this.TABLE_NAME, null, null);
    }


}
