package android.demosqlite03.View;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.demosqlite03.Controller.LoaiSPHandler;
import android.demosqlite03.Model.LoaiSP;
import android.demosqlite03.R;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtMaLoai, edtTenLoai;
    Button btnInsert, btnUpdate;
    ListView lvLoaiSP;
    String path="/data/data/android.demosqlite03/database/qlsp2.db";
    ArrayList<String>lsMaloai = new ArrayList<>();
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        //----------------------------
        loadLoaiSP();
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lsMaloai);
        lvLoaiSP.setAdapter(adapter);
        //--------------------------
        LoaiSPHandler loaiSPHandler= new LoaiSPHandler(getApplicationContext(),LoaiSPHandler.DB_NAME,
                null,LoaiSPHandler.DB_VERSION);
        loaiSPHandler.onCreate(db);


    }

    public  void addControl()
    {
        edtMaLoai=(EditText) findViewById(R.id.edtMaLoai);
        edtTenLoai=(EditText) findViewById(R.id.edtTenLoai);
        btnInsert=(Button)findViewById(R.id.btnInsert);
        btnUpdate=(Button) findViewById(R.id.btnUpdate);
        lvLoaiSP=(ListView) findViewById(R.id.lvMaLoai);
    }
    public void addEvent()
    {

    }
    public void loadLoaiSP()
    {
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor =db.rawQuery("select * from LoaiSP",null);
        cursor.moveToFirst();
        do {
            int ml=cursor.getInt(0);
            String tl=cursor.getString(1);
            String kq = String.valueOf(ml) + " " + tl;
            lsMaloai.add(kq);
        }while (cursor.moveToNext());
        cursor.close();
        db.close();
    }
}