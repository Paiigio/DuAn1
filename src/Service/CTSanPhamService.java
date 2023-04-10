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
            list.add(new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai()));

        }
        return list;
    }

    @Override
    public ArrayList<CTSanPhamModel> getAllCTSanPham_DangBan() {
        ArrayList<CTSanPhamModel> list = new ArrayList<>();
        ArrayList<CTSanPham> sp = ctsp.getAllCTSanPham_DangBan();
        for (CTSanPham x : sp) {
            list.add(new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai()));

        }
        return list;
    }

    @Override
    public ArrayList<CTSanPhamModel> getAllCTSanPham_KhongBan() {
        ArrayList<CTSanPhamModel> list = new ArrayList<>();
        ArrayList<CTSanPham> sp = ctsp.getAllCTSanPham_KhongBan();
        for (CTSanPham x : sp) {
            list.add(new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai()));

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
        var x = ctsp.insertCTSanPham(new CTSanPham(sp.getId(), sp.getMs(), sp.getCtkm(), sp.getSp(), sp.getDl(), sp.getMa(), sp.getMaQR(), sp.getHinhAnh(), sp.getNamBH(), sp.getNgayTao(), sp.getNgaySua(), sp.getGiaNhap(), sp.getGiaBan(), sp.getTrangThai()));
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai());
    }

    @Override
    public CTSanPhamModel updateCTSanPham(CTSanPhamModel sp) {
        var x = ctsp.updateCTSanPham(new CTSanPham(sp.getId(), sp.getMs(), sp.getCtkm(), sp.getSp(), sp.getDl(), sp.getMa(), sp.getMaQR(), sp.getHinhAnh(), sp.getNamBH(), sp.getNgayTao(), sp.getNgaySua(), sp.getGiaNhap(), sp.getGiaBan(), sp.getTrangThai()));
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai());
    }

    @Override
    public Integer deleteCTSanPham(String ma) {
        return ctsp.deleteCTSanPham(ma);
    }

    @Override
    public CTSanPhamModel updateCTKMSanPham(CTSanPhamModel sp) {
        var x = ctsp.updateCTKMSanPham(new CTSanPham(sp.getId(), sp.getMs(), sp.getCtkm(), sp.getSp(), sp.getDl(), sp.getMa(), sp.getMaQR(), sp.getHinhAnh(), sp.getNamBH(), sp.getNgayTao(), sp.getNgaySua(), sp.getGiaNhap(), sp.getGiaBan(), sp.getTrangThai()));
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai());
    }

    @Override
    public CTSanPhamModel deleteCTKM(CTSanPhamModel sp) {
        var x = ctsp.deleteCTKM(new CTSanPham(sp.getId(), sp.getMs(), sp.getCtkm(), sp.getSp(), sp.getDl(), sp.getMa(), sp.getMaQR(), sp.getHinhAnh(), sp.getNamBH(), sp.getNgayTao(), sp.getNgaySua(), sp.getGiaNhap(), sp.getGiaBan(), sp.getTrangThai()));
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai());
    }

    @Override
    public CTSanPhamModel getCTSPById(String ma) {
        var x = ctsp.getCTSanPhamByID(ma);
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai());
    }

    @Override
    public CTSanPhamModel getCTSPByMa(String ma) {
        var x = ctsp.quetQR(ma);
        return new CTSanPhamModel(x.getId(), x.getMs(), x.getCtkm(), x.getSp(), x.getDl(), x.getMa(), x.getMaQR(), x.getHinhAnh(), x.getNamBH(), x.getNgayTao(), x.getNgaySua(), x.getGiaNhap(), x.getGiaBan(), x.getTrangThai());
    }

}
