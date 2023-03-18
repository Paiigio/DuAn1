package ViewModel;

/**
 *
 * @author kazee
 */
public class DungLuongModel {

    private String id;
    private String ma;
    private String soDungLuong;

    public DungLuongModel() {
    }

    public DungLuongModel(String id, String ma, String soDungLuong) {
        this.id = id;
        this.ma = ma;
        this.soDungLuong = soDungLuong;
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

    @Override
    public String toString() {
        return soDungLuong;
    }
    
}
