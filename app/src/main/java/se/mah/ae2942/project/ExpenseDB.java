package se.mah.ae2942.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String COLUMN_COORDINATE = "COORDINATE";


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
                COLUMN_AMOUNT + " INTEGER, " +
                COLUMN_DATE + " INTEGER, " +
                COLUMN_COORDINATE + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String title, String category, double amount, String data){ // add coordinate
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, title);
        contentValues.put(COLUMN_CATEGORY, category);
        contentValues.put(COLUMN_AMOUNT, amount);
        contentValues.put(COLUMN_DATE, data);
        DB.insert(TABLE_NAME, null, contentValues);

        return false;
    }
}
