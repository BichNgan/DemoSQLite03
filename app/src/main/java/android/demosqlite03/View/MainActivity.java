package android.demosqlite03.View;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.demosqlite03.Controller.LoaiSPHandler;
import android.demosqlite03.Model.LoaiSP;
import android.demosqlite03.R;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText edtMaLoai, edtTenLoai;
    Button btnInsert, btnUpdate;
    ListView lvLoaiSP;
    String path="/data/data/android.demosqlite03/database/qlsp3.db";
    ArrayList<String>lsMaloai = new ArrayList<>();

    ArrayList<LoaiSP>listLoaiSP = new ArrayList<>();
    ArrayAdapter<String> adapter;
    SQLiteDatabase db;

    LoaiSPHandler loaiSPHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControl();
        //----------------------------
//        loadLoaiSP();
//        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,lsMaloai);
//        lvLoaiSP.setAdapter(adapter);
        //--------------------------
       loaiSPHandler= new LoaiSPHandler(getApplicationContext(),LoaiSPHandler.DB_NAME,
                null,LoaiSPHandler.DB_VERSION);
        loaiSPHandler.onCreate(db);
        loaiSPHandler.initData();
        loadDataToDislpayOnListView();
        //-----------------------
        addEvent();

    }
    public void loadDataToDislpayOnListView()
    {
        loadLoaiSP();
        adapter=new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,lsMaloai);
        lvLoaiSP.setAdapter(adapter);

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
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mk = edtMaLoai.getText().toString();
                String tl = edtTenLoai.getText().toString();
                if(!mk.equals("")&& !tl.equals(""))
                {
                    LoaiSP sp = new LoaiSP(Integer.parseInt(mk),tl);
                    loaiSPHandler.insertANewRecord(sp);
                    loadDataToDislpayOnListView();
                }
                else
                    return; //thoát hàm void
            }
        });
        lvLoaiSP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LoaiSP sp = listLoaiSP.get(position);
                edtMaLoai.setText(String.valueOf(sp.getMaloai()));
                edtMaLoai.setEnabled(false);
                edtTenLoai.setText(sp.getTenloai());

            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSP newLoaiSP = new LoaiSP(Integer.parseInt(edtMaLoai.getText().toString()),
                        edtTenLoai.getText().toString());
                LoaiSP oldLoaiSP = timLoaiSP(listLoaiSP,newLoaiSP.getMaloai());
                loaiSPHandler.updateLoaiSP(oldLoaiSP,newLoaiSP);
                loadDataToDislpayOnListView();
            }
        });

    }
    public LoaiSP timLoaiSP(ArrayList<LoaiSP>listLoaiSP, int ml)
    {
        for (LoaiSP a: listLoaiSP ) {
            if(a.getMaloai()==ml)
                return a;
        }
        return null;
    }


    public void loadLoaiSP()
    {
        listLoaiSP.clear();
        db=SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.CREATE_IF_NECESSARY);
        Cursor cursor =db.rawQuery("select * from LoaiSP",null);
        cursor.moveToFirst();
        do {
            int ml=cursor.getInt(0);
            String tl=cursor.getString(1);
            LoaiSP sp = new LoaiSP(ml,tl);
            listLoaiSP.add(sp);
            lsMaloai  = convertListLoaiSPToListString(listLoaiSP);
        }while (cursor.moveToNext());
        cursor.close();
        db.close();
    }
    public ArrayList<String> convertListLoaiSPToListString (ArrayList<LoaiSP> lsLoaiSP)
    {
        ArrayList<String>lsKq = new ArrayList<>();
        for (LoaiSP sp: lsLoaiSP ) {
            String s = String.valueOf(sp.getMaloai()) + " " + sp.getTenloai();
            lsKq.add(s);
        }
        return lsKq;
    }
}