/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModels.ChucVu;
import java.util.Date;

/**
 *
 * @author duong
 */
public class NhanVienModel {

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

    public NhanVienModel() {
    }

    public NhanVienModel(String id, String ma, String hoTen) {
        this.id = id;
        this.ma = ma;
        this.hoTen = hoTen;
    }

    public NhanVienModel(String id, ChucVu cv, String ma, String hoTen) {
        this.id = id;
        this.cv = cv;
        this.ma = ma;
        this.hoTen = hoTen;
    }

    public NhanVienModel(ChucVu cv, String ma, String hoTen, String gioiTinh, String sdt, Date ngaySinh, String diaChi, String email, String matKhau, int trangThai, String hinhAnh) {
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

    public NhanVienModel(String id, ChucVu cv, String ma, String hoTen, String gioiTinh, String sdt, Date ngaySinh, String diaChi, String email, String matKhau, int trangThai, String hinhAnh) {
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
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

}
