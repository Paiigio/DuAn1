/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ViewModel;

import DomainModels.*;
import java.util.Date;

/**
 *
 * @author WellCome Win1021H2
 */
public class CTSanPhamModel {
    private String id;
    private MauSac ms;
    private CTKhuyenMai ctkm;
    private SanPham sp;
    private DungLuong dl;
    private String ma;
    private String maQR;
    private int soLuongTon;
    private String hinhAnh;
    private int namBH;
    private float giaNhap;
    private float giaBan;
    private Date ngayTao;
    private Date ngayNhap;

    public CTSanPhamModel() {
    }

    public CTSanPhamModel(String id, MauSac ms, CTKhuyenMai ctkm, SanPham sp, DungLuong dl, String ma, String maQR, int soLuongTon, String hinhAnh, int namBH, float giaNhap, float giaBan, Date ngayTao, Date ngayNhap) {
        this.id = id;
        this.ms = ms;
        this.ctkm = ctkm;
        this.sp = sp;
        this.dl = dl;
        this.ma = ma;
        this.maQR = maQR;
        this.soLuongTon = soLuongTon;
        this.hinhAnh = hinhAnh;
        this.namBH = namBH;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.ngayTao = ngayTao;
        this.ngayNhap = ngayNhap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MauSac getMs() {
        return ms;
    }

    public void setMs(MauSac ms) {
        this.ms = ms;
    }

    public CTKhuyenMai getCtkm() {
        return ctkm;
    }

    public void setCtkm(CTKhuyenMai ctkm) {
        this.ctkm = ctkm;
    }

    public SanPham getSp() {
        return sp;
    }

    public void setSp(SanPham sp) {
        this.sp = sp;
    }

    public DungLuong getDl() {
        return dl;
    }

    public void setDl(DungLuong dl) {
        this.dl = dl;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getMaQR() {
        return maQR;
    }

    public void setMaQR(String maQR) {
        this.maQR = maQR;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public int getNamBH() {
        return namBH;
    }

    public void setNamBH(int namBH) {
        this.namBH = namBH;
    }

    public float getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(float giaNhap) {
        this.giaNhap = giaNhap;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
}
