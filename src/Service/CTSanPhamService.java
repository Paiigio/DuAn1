/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModels.CTSanPham;
import Service.Interface.ICTSanPhamService;
import ViewModel.CTSanPhamModel;
import java.util.ArrayList;
import responsitory.CTSanPhamResponsitory;

/**
 *
 * @author duong
 */
public class CTSanPhamService implements ICTSanPhamService {

    private CTSanPhamResponsitory ctsp = new CTSanPhamResponsitory();

    @Override
    public ArrayList<CTSanPhamModel> getAllCTSanPham() {
        ArrayList<CTSanPhamModel> list = new ArrayList<>();
        ArrayList<CTSanPham> sp = ctsp.getAllCTSanPham();
        for (CTSanPham x : sp) {
            list.add(new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getSoLuongTon(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan()));

        }
        return list;
    }

    @Override
    public CTSanPhamModel insertCTSanPham(CTSanPhamModel sp) {
        ArrayList<CTSanPham> list = ctsp.getAllCTSanPham();
        for (CTSanPham x : list) {
            if (x.getMa().equalsIgnoreCase(sp.getMa())) {
                return null;
            }

        }
        var x = ctsp.insertCTSanPham(new CTSanPham(sp.getId(), sp.getMs(), sp.getCtkm(), sp.getSp(), sp.getDl(), sp.getMa(), sp.getMaQR(), sp.getSoLuongTon(), sp.getHinhAnh(), sp.getNamBH(), sp.getNgayTao(), sp.getNgaySua(), sp.getGiaNhap(), sp.getGiaBan()));
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getSoLuongTon(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan());
    }

    @Override
    public CTSanPhamModel updateCTSanPham(CTSanPhamModel sp) {
        var x = ctsp.updateCTSanPham(new CTSanPham(sp.getId(), sp.getMs(), sp.getCtkm(), sp.getSp(), sp.getDl(), sp.getMa(), sp.getMaQR(), sp.getSoLuongTon(), sp.getHinhAnh(), sp.getNamBH(), sp.getNgayTao(), sp.getNgaySua(), sp.getGiaNhap(), sp.getGiaBan()));
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getSoLuongTon(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan());
    }

    @Override
    public Integer deleteCTSanPham(String ma) {
        return ctsp.deleteCTSanPham(ma);
    }

}
