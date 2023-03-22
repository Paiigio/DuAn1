/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModels.HoaDon;
import DomainModels.HoaDonChiTiet;
import Service.Interface.IHoaDonChiTietService;
import Service.Interface.IHoaDonService;
import ViewModel.HoaDonChiTietModel;
import ViewModel.HoaDonModel;
import java.util.ArrayList;
import responsitory.HoaDonChiTietResponsitory;
import responsitory.HoaDonResponsitory;

/**
 *
 * @author duong
 */
public class HoaDonChiTietService implements  IHoaDonChiTietService{
    private  HoaDonChiTietResponsitory hdct = new HoaDonChiTietResponsitory();

    @Override
    public ArrayList<HoaDonChiTietModel> getAllHoaDonCT() {
              ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        ArrayList<HoaDonChiTiet> hd = hdct.getAllHoaDonCT();
        for (HoaDonChiTiet x : hd) {
            list.add(new HoaDonChiTietModel(x.getIdhd(),x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    
    
}
