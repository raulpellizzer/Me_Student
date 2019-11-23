package com.example.mestudent;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import static com.example.mestudent.schema.dtbaseCONSTS.AUTH_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.CLASSROOM_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.CONFIG_TABLE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATABASE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DATABASE_VERSION;
import static com.example.mestudent.schema.dtbaseCONSTS.DATE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.DISCIPLINE_TABLE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.GRADE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.GRADE_DISCIPLINE_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.GRADE_TABLE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.LOGIN_TABLE_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.LOGIN_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.PASSWORD_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.TEACHER_COLUMN_NAME;
import static com.example.mestudent.schema.dtbaseCONSTS.THEME_COLUMN_NAME;

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

    private static final String SQL_CREATE_GRADES =
            "CREATE TABLE " + GRADE_TABLE_NAME + " (" +
                    schema.dtbaseCONSTS._ID + " INTEGER PRIMARY KEY," +
                    GRADE_DISCIPLINE_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    GRADE_COLUMN_NAME + TEXT_TYPE + " )";

    private static final String SQL_CREATE_CONFIG =
            "CREATE TABLE " + CONFIG_TABLE_NAME + " (" +
                    schema.dtbaseCONSTS._ID + " INTEGER PRIMARY KEY," +
                    AUTH_COLUMN_NAME + TEXT_TYPE + COMMA_SEP +
                    THEME_COLUMN_NAME + TEXT_TYPE + " )";

    private static final String SQL_DELETE_USER =
            "DROP TABLE IF EXISTS " + LOGIN_TABLE_NAME;

    private static final String SQL_DELETE_DISCIPLINES =
            "DROP TABLE IF EXISTS " + DISCIPLINE_TABLE_NAME;

    private static final String SQL_DELETE_GRADES =
            "DROP TABLE IF EXISTS " + GRADE_TABLE_NAME;

    private static final String SQL_DELETE_CONFIG =
            "DROP TABLE IF EXISTS " + CONFIG_TABLE_NAME;

    public PostDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public PostDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USER);
        db.execSQL(SQL_CREATE_DISCIPLINE);
        db.execSQL(SQL_CREATE_GRADES);
        db.execSQL(SQL_CREATE_CONFIG);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_USER);
        db.execSQL(SQL_DELETE_DISCIPLINES);
        db.execSQL(SQL_DELETE_GRADES);
        db.execSQL(SQL_DELETE_CONFIG);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean insertUser(String login, String password) {
        long result;
        Cursor c = null;
        int iLogin;
        boolean userExists = false;

        try {
            SQLiteDatabase DB = this.getWritableDatabase();

            String[] columns = new String[]{LOGIN_COLUMN_NAME};
            c = DB.query(LOGIN_TABLE_NAME, columns, null, null, null, null, null);
            iLogin = c.getColumnIndex(LOGIN_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(iLogin).equals(login))) {
                    userExists = true;
                    break;
                }
            }

            if(!userExists) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(LOGIN_COLUMN_NAME, login);
                contentValues.put(PASSWORD_COLUMN_NAME, password);
                result = DB.insert(LOGIN_TABLE_NAME, null, contentValues);
            } else {
                result = -1;
            }

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
        int iUser;
        int iPassword;
        boolean findUser;

        try {
            SQLiteDatabase DB = this.getReadableDatabase();
            String[] columns = new String[]{LOGIN_COLUMN_NAME, PASSWORD_COLUMN_NAME};
            Cursor c = DB.query(tableName, columns, null, null, null, null, null);

            findUser = false;
            iUser = c.getColumnIndex(LOGIN_COLUMN_NAME);
            iPassword = c.getColumnIndex(PASSWORD_COLUMN_NAME);

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
        long result;
        Cursor c = null;
        int idiscName;
        boolean discExists = false;

        try{
            SQLiteDatabase DB = this.getWritableDatabase();

            String[] columns = new String[]{DISCIPLINE_COLUMN_NAME};
            c = DB.query(DISCIPLINE_TABLE_NAME, columns, null, null, null, null, null);
            idiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(idiscName).equals(discName))) {
                    discExists = true;
                    break;
                }
            }

            if(!discExists) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DISCIPLINE_COLUMN_NAME, discName);
                contentValues.put(TEACHER_COLUMN_NAME, teacherName);
                contentValues.put(CLASSROOM_COLUMN_NAME, classroom);
                contentValues.put(DATE_COLUMN_NAME, date);
                result = DB.insert(DISCIPLINE_TABLE_NAME, null, contentValues);
            } else {
                result = -1;
            }

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

    public boolean resetSemester() {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean result;

        try{
            DB.execSQL(SQL_DELETE_DISCIPLINES);
            DB.execSQL(SQL_DELETE_GRADES);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            return result;
        }
        return result;
    }

    public boolean createDisciplinesTable() {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean result;

        try{
            DB.execSQL(SQL_CREATE_DISCIPLINE);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            return result;
        }
        return result;
    }

    public boolean createGradesTable() {
        SQLiteDatabase DB = this.getWritableDatabase();
        boolean result;

        try{
            DB.execSQL(SQL_CREATE_GRADES);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
            return result;
        }
        return result;
    }

    public Boolean deleteDiscipline(String discName){
        Cursor c = null;
        SQLiteDatabase DB = this.getWritableDatabase();
        String sqliteQuery;
        int iDisc;

        sqliteQuery = "DELETE FROM " + DISCIPLINE_TABLE_NAME + " WHERE discipline = '" + discName + "';";

        try {
            String[] columns = new String[]{DISCIPLINE_COLUMN_NAME};
            c = DB.query(DISCIPLINE_TABLE_NAME, columns, null, null, null, null, null);

            iDisc = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(iDisc).equals(discName))) {
                    DB.execSQL(sqliteQuery);
                }
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean updateRegister(String currentDiscName, String discName, String teacherName, String classroom, String date) {
        int iDiscName;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor c = null;
        String discTableName;
        String sqliteQuery;
        boolean queryExecuted = false;

        discTableName = DISCIPLINE_TABLE_NAME;

        try {
            String[] columns = new String[]{DISCIPLINE_COLUMN_NAME, TEACHER_COLUMN_NAME, CLASSROOM_COLUMN_NAME, DATE_COLUMN_NAME};
            c = DB.query(discTableName, columns, null, null, null, null, null);

            iDiscName = c.getColumnIndex(DISCIPLINE_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(iDiscName).equals(currentDiscName))) {
                    sqliteQuery = "UPDATE " + DISCIPLINE_TABLE_NAME +
                                  " SET " + DISCIPLINE_COLUMN_NAME + " = '" + discName + "'," +
                                            TEACHER_COLUMN_NAME + " = '" + teacherName + "'," +
                                            CLASSROOM_COLUMN_NAME + " = '" + classroom + "'," +
                                            DATE_COLUMN_NAME + " = '" + date + "'" +
                                  " WHERE " + DISCIPLINE_COLUMN_NAME + " = '" + currentDiscName + "';";
                    DB.execSQL(sqliteQuery);
                    queryExecuted = true;
                }
            }

            if(queryExecuted)
                return true;
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean startDiscipline(String discName) {
        long result;
        Cursor c = null;
        int idiscName;
        boolean discExists = false;
        String empty = "";

        try {
            SQLiteDatabase DB = this.getWritableDatabase();

            String[] columns = new String[]{GRADE_DISCIPLINE_COLUMN_NAME};
            c = DB.query(GRADE_TABLE_NAME, columns, null, null, null, null, null);
            idiscName = c.getColumnIndex(GRADE_DISCIPLINE_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(idiscName).equals(discName))) {
                    discExists = true;
                    break;
                }
            }

            if (!discExists) {
                ContentValues contentValues = new ContentValues();
                contentValues.put(GRADE_DISCIPLINE_COLUMN_NAME, discName);
                contentValues.put(GRADE_COLUMN_NAME, empty);
                result = DB.insert(GRADE_TABLE_NAME, null, contentValues);
            } else {
                result = -1;
            }

            if (result == -1) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteDisciplineGrades(String discName){
        Cursor c = null;
        SQLiteDatabase DB = this.getWritableDatabase();
        String sqliteQuery;
        int iDisc;

        sqliteQuery = "DELETE FROM " + GRADE_TABLE_NAME + " WHERE discipline = '" + discName + "';";

        try {
            String[] columns = new String[]{GRADE_DISCIPLINE_COLUMN_NAME};
            c = DB.query(GRADE_TABLE_NAME, columns, null, null, null, null, null);

            iDisc = c.getColumnIndex(GRADE_DISCIPLINE_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(iDisc).equals(discName))) {
                    DB.execSQL(sqliteQuery);
                }
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor readGradeData () {
        Cursor c = null;
        String gradeTableName;

        gradeTableName = GRADE_TABLE_NAME;

        try{
            SQLiteDatabase DB = this.getReadableDatabase();
            String[] columns = new String[]{GRADE_DISCIPLINE_COLUMN_NAME, GRADE_COLUMN_NAME};
            c = DB.query(gradeTableName, columns, null, null, null, null, null);

            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }
    }

    public boolean insertNewGrade(String discipline, String grade){
        int iDiscName;
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor c = null;
        String gradeTableName;
        String sqliteQuery;
        boolean queryExecuted = false;

        gradeTableName = GRADE_TABLE_NAME;

        try {
            String[] columns = new String[]{GRADE_COLUMN_NAME, GRADE_DISCIPLINE_COLUMN_NAME};
            c = DB.query(gradeTableName, columns, null, null, null, null, null);

            iDiscName = c.getColumnIndex(GRADE_DISCIPLINE_COLUMN_NAME);

            for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
                if ((c.getString(iDiscName).equals(discipline))) {
                    sqliteQuery = "UPDATE " + GRADE_TABLE_NAME +
                            " SET " + GRADE_COLUMN_NAME + " = '" + grade + "'" +
                            " WHERE " + GRADE_DISCIPLINE_COLUMN_NAME + " = '" + discipline + "';";
                    DB.execSQL(sqliteQuery);
                    queryExecuted = true;
                }
            }

            if (queryExecuted)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Cursor readConfigData () {
        Cursor c = null;
        String configTableName;

        configTableName = CONFIG_TABLE_NAME;

        try{
            SQLiteDatabase DB = this.getReadableDatabase();
            String[] columns = new String[]{AUTH_COLUMN_NAME, THEME_COLUMN_NAME};
            c = DB.query(configTableName, columns, null, null, null, null, null);

            return c;
        } catch (Exception e) {
            e.printStackTrace();
            return c;
        }
    }

    public boolean initializeConfig() {
        long result;

        try {
            SQLiteDatabase DB = this.getWritableDatabase();

            ContentValues contentValues = new ContentValues();
            contentValues.put(AUTH_COLUMN_NAME, "AUTH:YES");
            contentValues.put(THEME_COLUMN_NAME, "WHITE:YES");
            result = DB.insert(CONFIG_TABLE_NAME, null, contentValues);

            if (result != -1)
                return true;
            else
                return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean toggleAuthOn(){
        Cursor c;
        int iAuth;
        String currentAuth = "", SQLiteUpdateQuery;

        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            c = this.readConfigData();
            c.moveToFirst();

            iAuth = c.getColumnIndex(AUTH_COLUMN_NAME);
            currentAuth = c.getString(iAuth);

            SQLiteUpdateQuery = "UPDATE " + CONFIG_TABLE_NAME +
                    " SET " + AUTH_COLUMN_NAME + " = '" + "AUTH:YES" + "'" +
                    " WHERE " + AUTH_COLUMN_NAME + " = '" + currentAuth + "';";

            DB.execSQL(SQLiteUpdateQuery);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean toggleAuthOff(){
        Cursor c;
        int iAuth;
        String currentAuth = "", SQLiteUpdateQuery;

        try {
            SQLiteDatabase DB = this.getWritableDatabase();
            c = this.readConfigData();
            c.moveToFirst();

            iAuth = c.getColumnIndex(AUTH_COLUMN_NAME);
            currentAuth = c.getString(iAuth);

            SQLiteUpdateQuery = "UPDATE " + CONFIG_TABLE_NAME +
                    " SET " + AUTH_COLUMN_NAME + " = '" + "AUTH:NO" + "'" +
                    " WHERE " + AUTH_COLUMN_NAME + " = '" + currentAuth + "';";

            DB.execSQL(SQLiteUpdateQuery);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}



