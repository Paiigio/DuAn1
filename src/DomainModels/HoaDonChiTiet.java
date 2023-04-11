/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.Date;

/**
 *
 * @author duong
 */
public class HoaDonChiTiet {

    private String id;
    private HoaDon idhd;
    private CTSanPham idctsp;
    private float dongia;
    private int sl;
    private float thanhTien;
    private Date ngayTao;
    private Date ngaySua;
    private String ghiChu;
    private int baoHanh;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String id, HoaDon idhd, CTSanPham idctsp, float dongia, int sl, float thanhTien, Date ngayTao, Date ngaySua, String ghiChu, int baoHanh) {
        this.id = id;
        this.idhd = idhd;
        this.idctsp = idctsp;
        this.dongia = dongia;
        this.sl = sl;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ghiChu = ghiChu;
        this.baoHanh = baoHanh;
    }

    public HoaDonChiTiet(String id, HoaDon idhd, CTSanPham idctsp, float dongia, int sl, float thanhTien, Date ngayTao, Date ngaySua, String ghiChu) {
        this.id = id;
        this.idhd = idhd;
        this.idctsp = idctsp;
        this.dongia = dongia;
        this.sl = sl;
        this.thanhTien = thanhTien;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ghiChu = ghiChu;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getBaoHanh() {
        return baoHanh;
    }

    public void setBaoHanh(int baoHanh) {
        this.baoHanh = baoHanh;
    }

 

    public HoaDon getIdhd() {
        return idhd;
    }

    public void setIdhd(HoaDon idhd) {
        this.idhd = idhd;
    }

    public CTSanPham getIdctsp() {
        return idctsp;
    }

    public void setIdctsp(CTSanPham idctsp) {
        this.idctsp = idctsp;
    }

    public float getDongia() {
        return dongia;
    }

    public void setDongia(float dongia) {
        this.dongia = dongia;
    }

    public int getSl() {
        return sl;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
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

}
