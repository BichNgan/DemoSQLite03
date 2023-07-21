package android.demosqlite03.Controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.demosqlite03.Model.LoaiSP;

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
        String sql = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_LOAISP +
                "( " + MLOAI_COL + " INTEGER NOT NULL UNIQUE, " +
                TLOAI_COL + " TEXT NOT NULL UNIQUE, PRIMARY KEY("+ MLOAI_COL + "))";
        db.execSQL(sql);

        String sql2 = "CREATE TABLE IF NOT EXISTS "+ TABLE_NAME_SP + "( " +
                MSP_COL + " INTEGER NOT NULL UNIQUE, " +
                TSP_COL + " TEXT NOT NULL UNIQUE, " + SL_COL + "INTEGER, "+ MLOAI_COL +
                "INTEGER NOT NULL, PRIMARY KEY("+ MSP_COL + "))";
        db.execSQL(sql2);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void initData()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,null,SQLiteDatabase.OPEN_READWRITE);
        String sql1 = "INSERT OR IGNORE INTO " + TABLE_NAME_LOAISP + " VALUES ('1', 'Nước giải khát')";
        db.execSQL(sql1);
        String sql2 = "INSERT OR IGNORE INTO " + TABLE_NAME_LOAISP + " VALUES ('2', 'Bánh kẹo')";
        db.execSQL(sql2);
        String sql3 = "INSERT OR IGNORE INTO " + TABLE_NAME_LOAISP + " VALUES ('3', 'Sữa')";
        db.execSQL(sql3);
        db.close();

    }
   public void insertANewRecord (LoaiSP sp)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,
                null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values = new ContentValues();
        values.put(MLOAI_COL,sp.getMaloai());
        values.put(TLOAI_COL,sp.getTenloai());
        db.insert(TABLE_NAME_LOAISP,null,values);
        db.close();
    }

    public void updateLoaiSP(LoaiSP oldLoaiSP, LoaiSP newLoaiSP)
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(PATH,
                null,SQLiteDatabase.OPEN_READWRITE);
        ContentValues values= new ContentValues();
        values.put(MLOAI_COL,newLoaiSP.getMaloai());
        values.put(TLOAI_COL,newLoaiSP.getTenloai());
        db.update(TABLE_NAME_LOAISP,values,MLOAI_COL+"=?",
                new String[]{String.valueOf(newLoaiSP.getMaloai())});
        db.close();
    }

}
