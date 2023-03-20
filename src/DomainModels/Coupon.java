package DomainModels;

import java.util.Date;

/**
 *
 * @author WellCome Win1021H2
 */
public class Coupon {
    private String id;
    private String ma;
    private Date hanSuDung;
    private String hinhThuc;
    private float giamGia;
    private Date ngayTao;
    private Date ngaySua;

    public Coupon() {
    }

    public Coupon(String id, String ma, Date hanSuDung, String hinhThuc, float giamGia, Date ngayTao, Date ngaySua) {
        this.id = id;
        this.ma = ma;
        this.hanSuDung = hanSuDung;
        this.hinhThuc = hinhThuc;
        this.giamGia = giamGia;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public Date getHanSuDung() {
        return hanSuDung;
    }

    public void setHanSuDung(Date hanSuDung) {
        this.hanSuDung = hanSuDung;
    }

    public String getHinhThuc() {
        return hinhThuc;
    }

    public void setHinhThuc(String hinhThuc) {
        this.hinhThuc = hinhThuc;
    }

    public float getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(float giamGia) {
        this.giamGia = giamGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    @Override
    public String toString() {
        return ma;
    }
    
}
