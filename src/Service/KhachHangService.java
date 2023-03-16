/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import DomainModels.KhachHang;
import Service.Interface.IKhachHangService;
import ViewModel.KhachHangModel;
import java.util.ArrayList;
import responsitory.KhachHangResponsitory;

/**
 *
 * @author duong
 */
public class KhachHangService implements IKhachHangService{
    private  KhachHangResponsitory khs= new KhachHangResponsitory();
    @Override
    public ArrayList<KhachHangModel> getAllKH() {
             ArrayList<KhachHangModel> list = new ArrayList<>();
        ArrayList<KhachHang> nv = khs.getAllKH();
        for (KhachHang x : nv) {
            list.add(new KhachHangModel(x.getId(), x.getMaKH(), x.getSdt(), x.getHoTen(), x.getDiaChi(), x.getGioiTinh(), x.getEmail(), x.getNgaySinh(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public ArrayList<KhachHangModel> getTimKH(String sdt) {
                 ArrayList<KhachHangModel> list = new ArrayList<>();
        ArrayList<KhachHang> nv = khs.getTimKH(sdt);
        for (KhachHang x : nv) {
            list.add(new KhachHangModel(x.getId(), x.getMaKH(), x.getSdt(), x.getHoTen(), x.getDiaChi(), x.getGioiTinh(), x.getEmail(), x.getNgaySinh(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public KhachHangModel insertKH(KhachHangModel nv) {
           ArrayList<KhachHang> ds = khs.getAllKH();
        for (KhachHang x : ds) {
            if (x.getMaKH().equalsIgnoreCase(nv.getMaKH())) {
                return null;
            }
        }

        var x = khs.insertKH(new KhachHang(nv.getId(), nv.getMaKH(), nv.getSdt(), nv.getHoTen(), nv.getDiaChi(), nv.getGioiTinh(), nv.getEmail(), nv.getNgaySinh(), nv.getNgayTao(), nv.getNgaySua()));
        return new KhachHangModel(x.getId(), x.getMaKH(), x.getSdt(), x.getHoTen(), x.getDiaChi(), x.getGioiTinh(), x.getEmail(), x.getNgaySinh(), x.getNgayTao(), x.getNgaySua());
  
    }

    @Override
    public KhachHangModel updateKH(KhachHangModel nv) {
             var x = khs.updateKH(new KhachHang(nv.getId(), nv.getMaKH(), nv.getSdt(), nv.getHoTen(), nv.getDiaChi(), nv.getGioiTinh(), nv.getEmail(), nv.getNgaySinh(), nv.getNgayTao(), nv.getNgaySua()));
        return new KhachHangModel(x.getId(), x.getMaKH(), x.getSdt(), x.getHoTen(), x.getDiaChi(), x.getGioiTinh(), x.getEmail(), x.getNgaySinh(), x.getNgayTao(), x.getNgaySua());
  
    }

    
}
