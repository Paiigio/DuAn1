
package Service;

import DomainModels.CTKhuyenMai;
import Service.Interface.ICTKhuyenMaiService;
import ViewModel.CTKhuyenMaiModel;
import java.util.ArrayList;
import responsitory.CTKhuyenMaiResponsitory;

/**
 *
 * @author duong
 */
public class CTKhuyenMaiService implements ICTKhuyenMaiService {

    private CTKhuyenMaiResponsitory ct = new CTKhuyenMaiResponsitory();

    @Override
    public ArrayList<CTKhuyenMaiModel> getAllCTKM() {
        ArrayList<CTKhuyenMaiModel> list = new ArrayList<>();
        ArrayList<CTKhuyenMai> cv = ct.getAllCTKM();
        for (CTKhuyenMai x : cv) {
            list.add(new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));

        }
        return list;
    }
    @Override
    public ArrayList<CTKhuyenMaiModel> getAllCTKM_HoatDong() {
        ArrayList<CTKhuyenMaiModel> list = new ArrayList<>();
        ArrayList<CTKhuyenMai> cv = ct.getAllCTKM_HoatDong();
        for (CTKhuyenMai x : cv) {
            list.add(new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));

        }
        return list;
    }
    @Override
    public ArrayList<CTKhuyenMaiModel> getAllCTKM_KhongHoatDong() {
        ArrayList<CTKhuyenMaiModel> list = new ArrayList<>();
        ArrayList<CTKhuyenMai> cv = ct.getAllCTKM_KhongHoatDong();
        for (CTKhuyenMai x : cv) {
            list.add(new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));

        }
        return list;
    }
    @Override
    public CTKhuyenMaiModel insertCTKM(CTKhuyenMaiModel cv) {
        ArrayList<CTKhuyenMai> list = ct.getAllCTKM();
        for (CTKhuyenMai x : list) {
            if (x.getMa().equalsIgnoreCase(cv.getMa())) {
                return null;
            }

        }
        var x = ct.insertCTKM(new CTKhuyenMai(cv.getId(), cv.getMa(), cv.getTen(), cv.getThoiGianBatDau(), cv.getThoiGianKetThuc(), cv.getHinhThuc(), cv.getNgayTao(), cv.getNgaySua(), cv.getTrangThai()));
        return new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai());

    }

    @Override
    public CTKhuyenMaiModel updateCTKM(CTKhuyenMaiModel cv) {
        var x = ct.updateCTKM(new CTKhuyenMai(cv.getId(), cv.getMa(), cv.getTen(), cv.getThoiGianBatDau(), cv.getThoiGianKetThuc(), cv.getHinhThuc(), cv.getNgayTao(), cv.getNgaySua(), cv.getTrangThai()));
        return new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai());

    }
    
    @Override
    public CTKhuyenMaiModel updateTrangThai(CTKhuyenMaiModel cv) {
        var x = ct.updateTrangThai(new CTKhuyenMai(cv.getId(), cv.getMa(), cv.getTen(), cv.getThoiGianBatDau(), cv.getThoiGianKetThuc(), cv.getHinhThuc(), cv.getNgayTao(), cv.getNgaySua(), cv.getTrangThai()));
        return new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai());
    }
    @Override
    public CTKhuyenMaiModel updateTrangThaiHoatDong (CTKhuyenMaiModel cv) {
        var x = ct.updateTrangThaiHoatDong(new CTKhuyenMai(cv.getId(), cv.getMa(), cv.getTen(), cv.getThoiGianBatDau(), cv.getThoiGianKetThuc(), cv.getHinhThuc(), cv.getNgayTao(), cv.getNgaySua(), cv.getTrangThai()));
        return new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai());
    }
    @Override
    public ArrayList<CTKhuyenMaiModel> getTimTen(String ten) {
        ArrayList<CTKhuyenMaiModel> list = new ArrayList<>();
        ArrayList<CTKhuyenMai> cv = ct.getTimTen(ten);
        for (CTKhuyenMai x : cv) {
            list.add(new CTKhuyenMaiModel(x.getId(), x.getMa(), x.getTen(), x.getThoiGianBatDau(), x.getThoiGianKetThuc(), x.getHinhThuc(), x.getNgayTao(), x.getNgaySua(), x.getTrangThai()));

        }
        return list;
    }

}
