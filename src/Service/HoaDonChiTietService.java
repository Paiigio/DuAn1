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
            list.add(new HoaDonChiTietModel(x.getId(),x.getIdhd(),x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(),x.getGhiChu()));

        }
        return list;
    }
    @Override
    public HoaDonChiTietModel insertHDCT(HoaDonChiTietModel h) {
            ArrayList<HoaDonChiTiet> ds = hdct.getAllHoaDonCT();
        var x = hdct.insertHDCT(new HoaDonChiTiet(h.getId(),h.getIdhd(), h.getIdctsp(), h.getDongia(), h.getSl(), h.getThanhTien(), h.getNgayTao(), h.getNgaySua(),h.getGhiChu()));
        return new HoaDonChiTietModel(x.getId(),x.getIdhd(),x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(),x.getGhiChu());
    }
    @Override
    public Integer deleteHDCT(String id){
        return hdct.deleteHDCT(id);
    }
     @Override
    public ArrayList<HoaDonChiTietModel> getAllHoaDonCTBYIDHD(String id) {
              ArrayList<HoaDonChiTietModel> list = new ArrayList<>();
        ArrayList<HoaDonChiTiet> hd = hdct.getAllHoaDonCTByIDHD(id);
        for (HoaDonChiTiet x : hd) {
            list.add(new HoaDonChiTietModel(x.getId(),x.getIdhd(),x.getIdctsp(), x.getDongia(), x.getSl(), x.getThanhTien(), x.getNgayTao(), x.getNgaySua(),x.getGhiChu()));

        }
        return list;
    }   
}
