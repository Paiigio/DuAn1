/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import java.util.Date;

/**
 *
 * @author duong
 */
public class KhachHangModel {
        private String id;
    private String maKH;
    private String sdt;
    private String hoTen;
    private String diaChi;
    private String gioiTinh;
    private String email;
    private Date ngaySinh;
        private Date ngayTao;
    private Date ngaySua;

    public KhachHangModel() {
    }

    public KhachHangModel(String id, String maKH, String sdt, String hoTen, String diaChi, String gioiTinh, String email, Date ngaySinh, Date ngayTao, Date ngaySua) {
        this.id = id;
        this.maKH = maKH;
        this.sdt = sdt;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
    }

    public KhachHangModel(String maKH, String sdt, String hoTen, String diaChi, String gioiTinh, String email, Date ngaySinh) {
        this.maKH = maKH;
        this.sdt = sdt;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
        this.email = email;
        this.ngaySinh = ngaySinh;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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
        return sdt;
    }


}
