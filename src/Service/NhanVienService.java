
package Service;

import DomainModels.NhanVien;
import Service.Interface.INhanVienService;
import ViewModel.NhanVienModel;
import java.util.ArrayList;
import responsitory.NhanVienResponsitory;

/**
 *
 * @author duong
 */
public class NhanVienService implements INhanVienService {

    private NhanVienResponsitory nvr = new NhanVienResponsitory();

    @Override
    public ArrayList<NhanVienModel> getAllNV() {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        ArrayList<NhanVien> nv = nvr.getAllNhanVien();
        for (NhanVien x : nv) {
            list.add(new NhanVienModel(x.getId(), x.getCv(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getSdt(), x.getNgaySinh(), x.getDiaChi(), x.getEmail(), x.getMatKhau(), x.getTrangThai(), x.getHinhAnh()));

        }
        return list;
    }

    @Override
    public NhanVienModel insertNV(NhanVienModel cv) {
        ArrayList<NhanVien> ds = nvr.getAllNhanVien();
        for (NhanVien nvien : ds) {
            if (nvien.getMa().equalsIgnoreCase(cv.getMa())) {
                return null;
            }
        }

        var x = nvr.insertNhanVien(new NhanVien(cv.getId(), cv.getCv(), cv.getMa(), cv.getHoTen(), cv.getGioiTinh(), cv.getSdt(), cv.getNgaySinh(),
                cv.getDiaChi(), cv.getEmail(), cv.getMatKhau(), cv.getTrangThai(), cv.getHinhAnh()));
        return new NhanVienModel(x.getId(), x.getCv(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getSdt(), x.getNgaySinh(),
                x.getDiaChi(), x.getEmail(), x.getMatKhau(), x.getTrangThai(), x.getHinhAnh());
    }

    @Override
    public NhanVienModel updateNV(NhanVienModel cv) {
        var x = nvr.updateNV(new NhanVien(cv.getId(), cv.getCv(), cv.getMa(), cv.getHoTen(), cv.getGioiTinh(), cv.getSdt(), cv.getNgaySinh(),
                cv.getDiaChi(), cv.getEmail(), cv.getMatKhau(), cv.getTrangThai(), cv.getHinhAnh()));
        return new NhanVienModel(x.getId(), x.getCv(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getSdt(), x.getNgaySinh(),
                x.getDiaChi(), x.getEmail(), x.getMatKhau(), x.getTrangThai(), x.getHinhAnh());
    }
    
    @Override
    public String updatePass(String pass, String ma) {
        return nvr.updatePass(pass, ma);
    }

    @Override
    public Integer deleteNV(String ma) {
        return nvr.deleteNV(ma);
    }

    @Override
    public ArrayList<NhanVienModel> getTimTen(String ten) {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        ArrayList<NhanVien> nv = nvr.getTimTen(ten);
        for (NhanVien x : nv) {
            list.add(new NhanVienModel(x.getId(), x.getCv(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getSdt(), x.getNgaySinh(),
                    x.getDiaChi(), x.getEmail(), x.getMatKhau(), x.getTrangThai(), x.getHinhAnh()));

        }
        return list;
    }

    @Override
    public ArrayList<NhanVienModel> getVaiTro(String id) {
        ArrayList<NhanVienModel> list = new ArrayList<>();
        ArrayList<NhanVien> nv = nvr.getVaiTro(id);
        for (NhanVien x : nv) {
            list.add(new NhanVienModel(x.getId(), x.getCv(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getSdt(), x.getNgaySinh(),
                    x.getDiaChi(), x.getEmail(), x.getMatKhau(), x.getTrangThai(), x.getHinhAnh()));

        }
        return list;
    }

    @Override
    public ArrayList<NhanVienModel> getTT(String id) {
                ArrayList<NhanVienModel> list = new ArrayList<>();
        ArrayList<NhanVien> nv = nvr.getTT(id);
        for (NhanVien x : nv) {
            list.add(new NhanVienModel(x.getId(), x.getCv(), x.getMa(), x.getHoTen(), x.getGioiTinh(), x.getSdt(), x.getNgaySinh(),
                    x.getDiaChi(), x.getEmail(), x.getMatKhau(), x.getTrangThai(), x.getHinhAnh()));

        }
        return list;
    }

}
