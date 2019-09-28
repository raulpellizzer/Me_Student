package com.example.mestudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.mestudent.schema.dtbaseCONSTS.CLASSROOM_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATABASE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATABASE_VERSION;
import static com.example.mestudent.schema.dtbaseCONSTS.DATE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_TABLE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.LOGIN_TABLE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.LOGIN_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.PASSWORD_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.TEACHER_COLUMN_NAME;

public class PostDbHelper extends SQLiteOpenHelper {

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String SQL_CREATE_USER =
            "CREATE TABLE " + LOGIN_TABLE_NAME + " (" +
                    schema.dtbaseCONSTS._ID + " INTEGER PRIMARY KEY," +
                    LOGIN_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    schema.dtbaseCONSTS.PASSWORD_COLUMN_NAME + TEXT_TYPE + " )";

    private static final String SQL_CREATE_DISCIPLINE =
            "CREATE TABLE " + DISCIPLINE_TABLE_NAME + " (" +
                    schema.dtbaseCONSTS._ID + " INTEGER PRIMARY KEY," +
                    DISCIPLINE_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    schema.dtbaseCONSTS.TEACHER_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    CLASSROOM_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    schema.dtbaseCONSTS.DATE_COLUMN_NAME + TEXT_TYPE + " )";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + LOGIN_TABLE_NAME;

    private static final String SQL_DELETE_DISCIPLINES =
            "DROP TABLE IF EXISTS " + DISCIPLINE_TABLE_NAME;

    public PostDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public PostDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_DISCIPLINE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_DISCIPLINES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertUser(String login, String password) {
        try {
            SQLiteDatabase DB = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(LOGIN_COLUMN_NAME, login);
            contentValues.put(PASSWORD_COLUMN_NAME, password);

            long result = DB.insert(LOGIN_TABLE_NAME, null, contentValues);

            if(result == -1)
                return false;
            else
                return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean readUserData(String tableName, String userName, String password) {
        boolean findUser;
        try {
            SQLiteDatabase DB = this.getReadableDatabase();
            String[] columns = new String[]{LOGIN_COLUMN_NAME, PASSWORD_COLUMN_NAME};
            Cursor c = DB.query(tableName, columns, null, null, null, null, null);

            findUser = false;

            int iUser = c.getColumnIndex(LOGIN_COLUMN_NAME);
            int iPassword = c.getColumnIndex(PASSWORD_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(iUser).equals(userName)) && (c.getString(iPassword).equals(password))) {
                    findUser = true;
                }
            }

            return findUser;

        } catch (Exception e) {
            e.getStackTrace();
            return false;
        }
    }

    public boolean insertNewDiscipline(String discName, String teacherName, String classroom, String date) {
        try{
            SQLiteDatabase DB = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(DISCIPLINE_COLUMN_NAME, discName);
            contentValues.put(TEACHER_COLUMN_NAME, teacherName);
            contentValues.put(CLASSROOM_COLUMN_NAME, classroom);
            contentValues.put(DATE_COLUMN_NAME, date);

            long result = DB.insert(DISCIPLINE_TABLE_NAME, null, contentValues);

            if(result == -1)
                return false;
            else
                return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor readDisciplineData () {
        Cursor c = null;
        String discTableName;

        discTableName = DISCIPLINE_TABLE_NAME;

        try{
            SQLiteDatabase DB = this.getReadableDatabase();
            String[] columns = new String[]{DISCIPLINE_COLUMN_NAME, TEACHER_COLUMN_NAME, CLASSROOM_COLUMN_NAME, DATE_COLUMN_NAME};
            c = DB.query(discTableName, columns, null, null, null, null, null);

            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }
    }

}
