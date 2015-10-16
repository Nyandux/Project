package se.mah.ae2942.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Database for all the expenses.
 */
public class ExpenseDB extends SQLiteOpenHelper{
/**
    public static final String TABLE_NAME = "Transactions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_CATEGORY = "category";
    private static final String DATABASE_NAME = "transactions.db";
    private static final int DATABASE_VERSION = 1;
    private int incomeAmount, expensesAmount;

    private static final String DATABASE_CREATE =
            "create table " + TABLE_NAME + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_CATEGORY + " text, " +
                    COLUMN_TITLE + " text, " +
                    COLUMN_AMOUNT + " integer, " +
                    COLUMN_DATE + " integer);";

    /**
     * Constructor
     * @param context
     */
/**
    public TransactionDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    /**
     * Adds a new transaction to the database.
     * @param transaction new transaction
     */
/**
    public void addTransaction(Transaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TransactionDB.COLUMN_TITLE, transaction.getTitle());
        values.put(TransactionDB.COLUMN_AMOUNT, transaction.getAmount());
        values.put(TransactionDB.COLUMN_DATE, transaction.getDate());
        values.put(TransactionDB.COLUMN_CATEGORY, transaction.getCategory());
        db.insert(TransactionDB.TABLE_NAME, "", values);
    }

    /**
     * Empties the database of all transactions.
     */
/**
    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TransactionDB.TABLE_NAME, null, null);
    }

    /**
     * Returns all the transactions in databse.
     * @return all transactions[]
     */
/**
    public Transaction[] getAllTransactions() {
        int titleIndex, amountIndex, dateIndex, categoryIndex;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " ORDER BY " + COLUMN_DATE, null);

        Transaction[] transactions = new Transaction[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        amountIndex = cursor.getColumnIndex(COLUMN_AMOUNT);
        categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
        dateIndex = cursor.getColumnIndex(COLUMN_DATE);

        for(int i=0; i<transactions.length; i++) {
            cursor.moveToPosition(i);
            transactions[i] = new Transaction(cursor.getString(categoryIndex), cursor.getString(titleIndex),
                    cursor.getString(amountIndex), cursor.getString(dateIndex));
        }
        return transactions;
    }

    /**
     * Returns all the income transactions.
     * @return income transactions[]
     */
/**
    public Transaction[] getIncomeTransactions() {
        int titleIndex, amountIndex, dateIndex, categoryIndex;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CATEGORY + " IN ('Other Income','Work');",null);

        Transaction[] transactions = new Transaction[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        amountIndex = cursor.getColumnIndex(COLUMN_AMOUNT);
        categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
        dateIndex = cursor.getColumnIndex(COLUMN_DATE);

        for(int i=0; i<transactions.length; i++) {
            cursor.moveToPosition(i);
            transactions[i] = new Transaction(cursor.getString(categoryIndex), cursor.getString(titleIndex),
                    cursor.getString(amountIndex), cursor.getString(dateIndex));
        }
        cursor.close();
        return transactions;
    }

    /**
     * Returns all the expenses transactions.
     * @return expenses transactions[]
     */
/**
    public Transaction[] getExpensesTransactions() {
        int titleIndex, amountIndex, dateIndex, categoryIndex;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CATEGORY + " IN ('Food','Other Expense','Travel','Entertainment','Home');"
                + " ORDER BY " + COLUMN_DATE,null);

        Transaction[] transactions = new Transaction[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        amountIndex = cursor.getColumnIndex(COLUMN_AMOUNT);
        categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
        dateIndex = cursor.getColumnIndex(COLUMN_DATE);

        for(int i=0; i<transactions.length; i++) {
            cursor.moveToPosition(i);
            transactions[i] = new Transaction(cursor.getString(categoryIndex), cursor.getString(titleIndex),
                    cursor.getString(amountIndex), cursor.getString(dateIndex));
        }
        cursor.close();
        return transactions;
    }

    /**
     * Returns all the expense transactions whose date is >= date input from parameter.
     * @param date - user input data
     * @return expense transactions[] with date >= to date input
     */
/**
    public Transaction[] getByDateExpensesTransactions(int date) {
        int titleIndex, amountIndex, dateIndex, categoryIndex;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_DATE + " >= " + date + " AND " + COLUMN_CATEGORY +
                " IN ('Food','Other Expense','Travel','Entertainment','Home');", null);

        Transaction[] transactions = new Transaction[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        amountIndex = cursor.getColumnIndex(COLUMN_AMOUNT);
        categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
        dateIndex = cursor.getColumnIndex(COLUMN_DATE);

        for(int i=0; i<transactions.length; i++) {
            cursor.moveToPosition(i);
            transactions[i] = new Transaction(cursor.getString(categoryIndex), cursor.getString(titleIndex),
                    cursor.getString(amountIndex), cursor.getString(dateIndex));
        }
        cursor.close();
        return transactions;
    }

    /**
     * Returns all the income transactions whose date is >= date input from parameter.
     * @param date - user input data
     * @return income transactions[] with date >= to date input
     */
/**
    public Transaction[] getByDateIncomeTransactions(int date) {
        int titleIndex, amountIndex, dateIndex, categoryIndex;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_DATE + " >= " + date + " AND " + COLUMN_CATEGORY +
                " IN ('Work','Other Income');", null);

        Transaction[] transactions = new Transaction[cursor.getCount()];

        titleIndex = cursor.getColumnIndex(COLUMN_TITLE);
        amountIndex = cursor.getColumnIndex(COLUMN_AMOUNT);
        categoryIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
        dateIndex = cursor.getColumnIndex(COLUMN_DATE);

        for(int i=0; i<transactions.length; i++) {
            cursor.moveToPosition(i);
            transactions[i] = new Transaction(cursor.getString(categoryIndex), cursor.getString(titleIndex),
                    cursor.getString(amountIndex), cursor.getString(dateIndex));
        }
        cursor.close();
        return transactions;
    }

    /**
     * Returns the total amount value of all transactions.
     * @return total amount
     */
/**
    public int getTransactionTotal() {
        int amountIndex;
        incomeAmount = 0;
        expensesAmount = 0;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorExpenses = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CATEGORY + " IN ('Food','Other Expense','Travel','Entertainment','Home');"
                + " ORDER BY " + COLUMN_DATE, null);

        Cursor cursorIncome = db.rawQuery("SELECT * FROM " + TABLE_NAME +
                " WHERE " + COLUMN_CATEGORY + " IN ('Other Income','Work');",null);

        Transaction[] transactions = new Transaction[cursorIncome.getCount()];

        amountIndex = cursorIncome.getColumnIndex(COLUMN_AMOUNT);

        for(int i=0; i<transactions.length; i++) {
            cursorIncome.moveToPosition(i);
            incomeAmount += Integer.parseInt(cursorIncome.getString(amountIndex));
        }
        cursorIncome.close();

        transactions = new Transaction[cursorExpenses.getCount()];

        amountIndex = cursorExpenses.getColumnIndex(COLUMN_AMOUNT);

        for(int i=0; i<transactions.length; i++) {
            cursorExpenses.moveToPosition(i);
            expensesAmount += Integer.parseInt(cursorExpenses.getString(amountIndex));
        }

        cursorExpenses.close();

        incomeAmount -= expensesAmount;

        return incomeAmount;
    }
}
