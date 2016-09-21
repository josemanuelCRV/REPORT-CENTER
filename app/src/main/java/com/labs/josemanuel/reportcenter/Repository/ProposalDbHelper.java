package com.labs.josemanuel.reportcenter.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Miguel on 21-09-16.
 */
public class ProposalDbHelper extends SQLiteOpenHelper{
    public static final String DBname= "propuestas";
    public static final String tableName= "propuestas";
    private static final String CREATE_PROPUESTAS_TABLE_SQL =
            "CREATE TABLE propuestas (" +
                    "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    " title varchar(200) " +
                    " )";
    public ProposalDbHelper(Context context) {
        super(context, DBname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ProposalDbHelper","ProposalDbHelper.onCreate()");
        db.execSQL(CREATE_PROPUESTAS_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
