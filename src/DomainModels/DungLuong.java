package DomainModels;

import java.util.Date;

/**
 *
 * @author WellCome Win1021H2
 */
public class DungLuong {
    private String id;
    private String ma;
    private String soDungLuong;
    private Date ngayNhap;

    public DungLuong() {
    }

    public DungLuong(String id, String ma, String soDungLuong) {
        this.id = id;
        this.ma = ma;
        this.soDungLuong = soDungLuong;
    }

    public DungLuong(String id, String ma, String soDungLuong, Date ngayNhap) {
        this.id = id;
        this.ma = ma;
        this.soDungLuong = soDungLuong;
        this.ngayNhap = ngayNhap;
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

    public String getSoDungLuong() {
        return soDungLuong;
    }

    public void setSoDungLuong(String soDungLuong) {
        this.soDungLuong = soDungLuong;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    @Override
    public String toString() {
        return soDungLuong;
    }
    
}
