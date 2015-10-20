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
     * @param title - title of expense
     * @param category - category of expense
     * @param amount - amount of expense
     * @param date - date when expense is created
     * @return - returns false if an error acurred
     */
    public boolean insertData(String title, String category, double amount, String date) { // add coordinate
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_AMOUNT, amount);
        contentValues.put(COLUMN_DATE, date);
        //put values for coordinate
        long res = DB.insert(TABLE_NAME, null, contentValues);

        if (res == -1)
            return false;
        return true;
    }


    public Expense[] getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("select * from " + TABLE_NAME, null);

        Expense[] expense = new Expense[result.getCount()];

     //   titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
     //   amountIndex = cursor.getColumnIndex(COLUMN_AMOUNT);
      //  categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
      //  dateIndex = cursor.getColumnIndex(COLUMN_DATE);

        for(int i=0; i<expense.length; i++){
            result.moveToPosition(i);
            expense[i] = new Expense(result.getString(result.getColumnIndex(COLUMN_TITLE)),
                    result.getString(result.getColumnIndex(COLUMN_CATEGORY)),
                    Double.parseDouble(result.getString(result.getColumnIndex(COLUMN_AMOUNT))),
                    result.getString(result.getColumnIndex(COLUMN_DATE)));
        }
        result.close();
        return expense;

    }






}
