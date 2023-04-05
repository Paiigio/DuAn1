/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModels.HoaDon;
import Service.Interface.IHoaDonService;
import ViewModel.HoaDonModel;
import java.util.ArrayList;
import responsitory.HoaDonResponsitory;

/**
 *
 * @author duong
 */
public class HoaDonService implements  IHoaDonService{
    private  HoaDonResponsitory hdr= new HoaDonResponsitory();

    @Override
    public ArrayList<HoaDonModel> getAllHoaDon() {
              ArrayList<HoaDonModel> list = new ArrayList<>();
        ArrayList<HoaDon> nv = hdr.getAllHoaDon();
        for (HoaDon x : nv) {
            list.add(new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public HoaDonModel insertHD(HoaDonModel nv) {
        var x = hdr.insertHD(new HoaDon(nv.getId(), nv.getKh(), nv.getNv(), nv.getCp(), nv.getMa(), nv.getThanhTien(), 
                    nv.getHinhThucThanhToan(), nv.getNgayThanhToan(), nv.getTrangThai(), nv.getNgayTao(), nv.getNgaySua()));
        return new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua());
    }

    @Override
    public HoaDonModel updateTinhTrangHD(HoaDonModel nv) {
           var x = hdr.updateTinhTrangHD(new HoaDon(nv.getId(), nv.getKh(), nv.getNv(), nv.getCp(), nv.getMa(), nv.getThanhTien(), 
                    nv.getHinhThucThanhToan(), nv.getNgayThanhToan(), nv.getTrangThai(), nv.getNgayTao(), nv.getNgaySua()));
        return new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua());
    }

    @Override
    public ArrayList<HoaDonModel> getAllHoaDonTT() {
           ArrayList<HoaDonModel> list = new ArrayList<>();
        ArrayList<HoaDon> nv = hdr.getAllHoaDonTT();
        for (HoaDon x : nv) {
            list.add(new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public ArrayList<HoaDonModel> getAllHoaDonCTT() {
           ArrayList<HoaDonModel> list = new ArrayList<>();
        ArrayList<HoaDon> nv = hdr.getAllHoaDonCTT();
        for (HoaDon x : nv) {
            list.add(new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public HoaDonModel upadteHD(HoaDonModel nv) {
        var x = hdr.upadteHD(new HoaDon(nv.getId(), nv.getKh(), nv.getNv(), nv.getCp(), nv.getMa(), nv.getThanhTien(), 
                    nv.getHinhThucThanhToan(), nv.getNgayThanhToan(), nv.getTrangThai(), nv.getNgayTao(), nv.getNgaySua()));
        return new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua());
    }
     @Override
    public HoaDonModel upadteHD_ThanhToan(HoaDonModel nv) {
        var x = hdr.upadteHD_ThanhToan(new HoaDon(nv.getId(), nv.getKh(), nv.getNv(), nv.getCp(), nv.getMa(), nv.getThanhTien(), 
                    nv.getHinhThucThanhToan(), nv.getNgayThanhToan(), nv.getTrangThai(), nv.getNgayTao(), nv.getNgaySua()));
        return new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua());
    }   

    @Override
    public HoaDonModel updateHuyHD(HoaDonModel nv) {
           var x = hdr.updateHuyHD(new HoaDon(nv.getId(), nv.getKh(), nv.getNv(), nv.getCp(), nv.getMa(), nv.getThanhTien(), 
                    nv.getHinhThucThanhToan(), nv.getNgayThanhToan(), nv.getTrangThai(), nv.getNgayTao(), nv.getNgaySua(),nv.getGhiChu()));
        return new HoaDonModel(x.getId(), x.getKh(), x.getNv(), x.getCp(), x.getMa(), x.getThanhTien(), 
                    x.getHinhThucThanhToan(), x.getNgayThanhToan(), x.getTrangThai(), x.getNgayTao(), x.getNgaySua(),x.getGhiChu());
    }
    
}
