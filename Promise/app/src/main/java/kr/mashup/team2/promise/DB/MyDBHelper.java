package kr.mashup.team2.promise.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "my_db";
    public static final String TABLE_NAME_USER = "user_table";
    public static final String TABLE_NAME_FRIEND = "friend_table";
    public static final String TABLE_NAME_APPOINTMENT_INFO = "appointment_info_table";
    public static final String TABLE_NAME_APPOINTMENT = "appointment_table";


    public MyDBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "create table " + TABLE_NAME_USER +
                " ( _id integer primary key, name text, success integer, fail integer);";
        String createAppointmentInfoTable = "create table " + TABLE_NAME_APPOINTMENT_INFO +
                " ( _id integer primary key autoincrement, aptmnt_date datetime, user_id integer," +
                " location text, latitude text, longitude text);";
        String createFriendTable = "create table " + TABLE_NAME_FRIEND +
                " ( _id integer primary key autoincrement, user_id integer, friend_id integer);";
        String createAppointmentTable = "create table " + TABLE_NAME_APPOINTMENT +
                " ( _id integer primary key autoincrement, user_id integer, aptmnt_id integer);";

        sqLiteDatabase.execSQL(createUserTable);
        sqLiteDatabase.execSQL(createAppointmentInfoTable);
        sqLiteDatabase.execSQL(createFriendTable);
        sqLiteDatabase.execSQL(createAppointmentTable);

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_USER + " values (1111, " +
                "'이종석', 1, 1)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_USER + " values (2222, " +
                "'박보검', 1, 1)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_USER + " values (3333, " +
                "'이제훈', 1, 1)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_USER + " values (4444, " +
                "'박신혜', 1, 1)");

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_FRIEND + " values (null, " +
                "1111, 2222)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_FRIEND + " values (null, " +
                "1111, 3333)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_FRIEND + " values (null, " +
                "1111, 4444)");

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_APPOINTMENT_INFO + " values (null, " +
                "'2016-08-13 15:00', 2222, '파리', '48.863138', '2.3345')");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_APPOINTMENT_INFO + " values (null, " +
                "'2016-08-15 20:00', 3333, '파리', '48.863138', '2.3345')");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_APPOINTMENT_INFO + " values (null, " +
                "'2016-08-17 18:00', 4444, '파리', '48.863138', '2.3345')");

        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_APPOINTMENT + " values(null, " +
                "1111, 1)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_APPOINTMENT + " values(null, " +
                "1111, 2)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME_APPOINTMENT + " values(null, " +
                "1111, 3)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
