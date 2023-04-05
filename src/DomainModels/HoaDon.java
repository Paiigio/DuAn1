/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DomainModels;

import java.util.Date;

/**
 *
 * @author WellCome Win1021H2
 */
public class HoaDon {
    private String id;
    private KhachHang kh;
    private NhanVien nv;
    private Coupon cp;
    private String ma;
    private float thanhTien;
    private int hinhThucThanhToan;
    private Date ngayThanhToan;
    private int trangThai;
    private Date ngayTao;
    private Date ngaySua;
    private String ghiChu;
    
    public HoaDon() {
    }

    public HoaDon(String id, KhachHang kh, NhanVien nv, Coupon cp, String ma, float thanhTien, int hinhThucThanhToan, Date ngayThanhToan, int trangThai, Date ngayTao, Date ngaySua) {
        this.id = id;
        this.kh = kh;
        this.nv = nv;
        this.cp = cp;
        this.ma = ma;
        this.thanhTien = thanhTien;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public HoaDon(String id, KhachHang kh, NhanVien nv, Coupon cp, String ma, float thanhTien, int hinhThucThanhToan, Date ngayThanhToan, int trangThai, Date ngayTao, Date ngaySua, String ghiChu) {
        this.id = id;
        this.kh = kh;
        this.nv = nv;
        this.cp = cp;
        this.ma = ma;
        this.thanhTien = thanhTien;
        this.hinhThucThanhToan = hinhThucThanhToan;
        this.ngayThanhToan = ngayThanhToan;
        this.trangThai = trangThai;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.ghiChu = ghiChu;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KhachHang getKh() {
        return kh;
    }

    public void setKh(KhachHang kh) {
        this.kh = kh;
    }

    public NhanVien getNv() {
        return nv;
    }

    public void setNv(NhanVien nv) {
        this.nv = nv;
    }

    public Coupon getCp() {
        return cp;
    }

    public void setCp(Coupon cp) {
        this.cp = cp;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public float getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(float thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getHinhThucThanhToan() {
        return hinhThucThanhToan;
    }

    public void setHinhThucThanhToan(int hinhThucThanhToan) {
        this.hinhThucThanhToan = hinhThucThanhToan;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
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

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
    
}
