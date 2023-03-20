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
public class NhanVien {

    private String id;
    private ChucVu cv;
    private String ma;
    private String hoTen;
    private String gioiTinh;
    private String sdt;
    private Date ngaySinh;
    private String diaChi;
    private String email;
    private String matKhau;
    private int trangThai;
    private String hinhAnh;
    private Date ngayTao;
    private Date ngaySua;

    public NhanVien() {
    }

    public NhanVien(String id, ChucVu cv, String ma, String hoTen) {
        this.id = id;
        this.cv = cv;
        this.ma = ma;
        this.hoTen = hoTen;
    }

    public NhanVien(String id, String ma, String hoTen) {
        this.id = id;
        this.ma = ma;
        this.hoTen = hoTen;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public NhanVien(String id, ChucVu cv, String ma, String hoTen, String gioiTinh, String sdt, Date ngaySinh, String diaChi, String email, String matKhau, int trangThai, String hinhAnh) {
        this.id = id;
        this.cv = cv;
        this.ma = ma;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
    }

    public NhanVien(String id, ChucVu cv, String ma, String hoTen, String gioiTinh, String sdt, Date ngaySinh, String diaChi, String email, String matKhau, int trangThai, String hinhAnh, Date ngayTao, Date ngaySua) {
        this.id = id;
        this.cv = cv;
        this.ma = ma;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.email = email;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.hinhAnh = hinhAnh;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ChucVu getCv() {
        return cv;
    }

    public void setCv(ChucVu cv) {
        this.cv = cv;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
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
