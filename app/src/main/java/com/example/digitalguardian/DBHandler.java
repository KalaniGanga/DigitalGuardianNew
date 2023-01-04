package com.example.digitalguardian;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper{
    // creating a constant variables for our database.
    // below variable is for our database name.
    private static final String DB_NAME = "healthcaredb";

    // below int is our database version
    private static final int DB_VERSION = 1;

    // below variable is for our table name.
    private static final String TABLE_PATIENT = "patent";

    private static final String INDEX_COL = "id";
    private static final String NAME_COL = "name";
    private static final String GENDER_COL = "gender";
    private static final String AGE_COL = "age";
    private static final String EMAIL_COL = "email";
    private static final String PHONE_COL = "phone";
    private static final String BPM_COL = "bpm";
    private static final String TEMP_COL = "temperature";
    private static final String DESCRIPTION_COL = "description";
    private static final String ROOM_NO_COL = "roomNumber";
    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {
        // on below line we are creating
        // an sqlite query and we are
        // setting our column names
        // along with their data types.
        String query = "CREATE TABLE " + TABLE_PATIENT + " ("
                + INDEX_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + GENDER_COL + " TEXT,"
                + AGE_COL + " INTEGER,"
                + DESCRIPTION_COL + " TEXT,"
                + ROOM_NO_COL + " INTEGER,"
                + EMAIL_COL + " TEXT,"
                + PHONE_COL + " TEXT,"
                + BPM_COL + " INTEGER,"
                + TEMP_COL + " INTEGER,"
                + USER_NAME + " TEXT,"
                + PASSWORD + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query);
    }

    // this method is use to add new course to our sqlite database.
    public void addNewPatient(Patient patient) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        values.put(NAME_COL, patient.getName());
        values.put(GENDER_COL, patient.getGender());
        values.put(AGE_COL, patient.getAge());
        values.put(ROOM_NO_COL, patient.getRoomNumber());
        values.put(DESCRIPTION_COL, patient.getDesc());
        values.put(EMAIL_COL, patient.getEmail());
        values.put(PHONE_COL, patient.getPhone());
        values.put(BPM_COL, patient.getBpm());
        values.put(TEMP_COL, patient.getTemperature());

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_PATIENT, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Patient> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                INDEX_COL,
                NAME_COL,
                GENDER_COL,
                DESCRIPTION_COL,
                ROOM_NO_COL
        };
        // sorting orders
        String sortOrder =
                NAME_COL + " ASC";
        List<Patient> userList = new ArrayList<Patient>();
        SQLiteDatabase db = this.getReadableDatabase();
        // query the patient table
        Cursor cursor = db.query(TABLE_PATIENT, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order
        // Traversing through all rows and adding to list
        if( cursor != null && cursor.moveToFirst() ){
            do {
                Patient user = new Patient();
                user.setIndex(cursor.getString(cursor.getColumnIndexOrThrow(INDEX_COL)));
                user.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME_COL)));
                user.setGender(cursor.getString(cursor.getColumnIndexOrThrow(GENDER_COL)));
                user.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_COL)));
//                user.setRoomNumber(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(ROOM_NO_COL))));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return user list
        return userList;
    }

    /**
     * This method to update user record
     *
     * @param user
     */
    public void updatePatient(Patient user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(NAME_COL, user.getName());
//        values.put(GENDER_COL, user.getGender());
//        values.put(DESCRIPTION_COL, user.getDesc());
//        values.put(ROOM_NO_COL, user.getRoomNumber());
        values.put(USER_NAME, user.getUserName());
        values.put(PASSWORD, user.getPassword());
        // updating row
        db.update(TABLE_PATIENT, values, INDEX_COL + " = ?",
                new String[]{String.valueOf(user.getIndex())});
        db.close();
    }

    public Patient getPatientDetails(String patientId) {
        SQLiteDatabase db = this.getWritableDatabase();
        Patient patient = new Patient();
//        db.rawQuery(TABLE_PATIENT, values, INDEX_COL + " = ?", new String[]{""+patientId});
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_PATIENT+" WHERE "+INDEX_COL+"=?", new String[]{patientId});
        if( cursor != null && cursor.moveToFirst() ) {
            patient.setIndex(cursor.getString(cursor.getColumnIndexOrThrow(INDEX_COL)));
            patient.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME_COL)));
            patient.setGender(cursor.getString(cursor.getColumnIndexOrThrow(GENDER_COL)));
            patient.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_COL)));
        }
        cursor.close();
        db.close();
        return patient;
    }

    public Patient getPatientDetailsByUsername(String userName) {
        SQLiteDatabase db = this.getWritableDatabase();
        Patient patient = new Patient();
//        db.rawQuery(TABLE_PATIENT, values, INDEX_COL + " = ?", new String[]{""+patientId});
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_PATIENT+" WHERE "+USER_NAME+"=?", new String[]{userName});
        if( cursor != null && cursor.moveToFirst() ) {
            patient.setIndex(cursor.getString(cursor.getColumnIndexOrThrow(INDEX_COL)));
            patient.setName(cursor.getString(cursor.getColumnIndexOrThrow(NAME_COL)));
            patient.setGender(cursor.getString(cursor.getColumnIndexOrThrow(GENDER_COL)));
            patient.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION_COL)));
        }
        cursor.close();
        db.close();
        return patient;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // this method is called to check if the table exists already.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENT);
        onCreate(db);
    }
}
