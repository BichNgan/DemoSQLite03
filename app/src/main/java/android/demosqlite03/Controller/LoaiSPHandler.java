package android.demosqlite03.Controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LoaiSPHandler extends SQLiteOpenHelper {

    public static final String DB_NAME = "qlsp3";
    public static final String TABLE_NAME_LOAISP = "LoaiSP";
    public static final String MLOAI_COL = "maloai";
    public static final String TLOAI_COL = "tenloai";
    public static final String TABLE_NAME_SP = "SanPham";
    public static final String MSP_COL = "masp";
    public static final String TSP_COL = "tensp";
    public static final String SL_COL = "soluong";
    public static final int DB_VERSION = 1;
    public static final String PATH= "/data/data/android.demosqlite03/database/qlsp3.db";


    public LoaiSPHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, factory, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db=SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_LOAISP + "( " + MLOAI_COL + " INTEGER NOT NULL UNIQUE, " +
                TLOAI_COL + " TEXT NOT NULL UNIQUE, PRIMARY KEY("+ MLOAI_COL + "))";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_SP + "( " + MSP_COL + " INTEGER NOT NULL UNIQUE, " +
                TSP_COL + " TEXT NOT NULL UNIQUE, " + SL_COL + "INTEGER, "+ MLOAI_COL +
                "INTEGER NOT NULL, PRIMARY KEY("+ MSP_COL + "))";
        db.execSQL(sql2);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
