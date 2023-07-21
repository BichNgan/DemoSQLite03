package android.demosqlite03.Model;

public class LoaiSP {
    int maloai;
    String tenloai;

    public int getMaloai() {
        return maloai;
    }

    public void setMaloai(int maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public LoaiSP(int maloai, String tenloai) {
        this.maloai = maloai;
        this.tenloai = tenloai;
    }
    public LoaiSP()
    {

    }
}
