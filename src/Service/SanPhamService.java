
package Service;

import DomainModels.SanPham;
import Service.Interface.ISanPhamService;
import ViewModel.SanPhamModel;
import java.util.ArrayList;
import responsitory.SanPhamResponsitory;

/**
 *
 * @author duong
 */
public class SanPhamService implements ISanPhamService {

    private SanPhamResponsitory spr = new SanPhamResponsitory();


    @Override
    public ArrayList<SanPhamModel> getAllSanPham() {
        ArrayList<SanPhamModel> list = new ArrayList<>();
        ArrayList<SanPham> sp = spr.getAllSanPham();
        for (SanPham x : sp) {
            list.add(new SanPhamModel(x.getId(), x.getNsx(), x.getMa(), x.getTen(), x.getMoTa(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public SanPhamModel insertSP(SanPhamModel sp) {
        ArrayList<SanPham> list = spr.getAllSanPham();
        for (SanPham x : list) {
            if (x.getMa().equalsIgnoreCase(sp.getMa())) {
                return null;
            }

        }
        var x = spr.insertSP(new SanPham(sp.getId(), sp.getNsx(), sp.getMa(), sp.getTen(), sp.getMoTa(), sp.getNgayTao(), sp.getNgaySua()));
        return new SanPhamModel(x.getId(), x.getNsx(), x.getMa(), x.getTen(), x.getMoTa(), x.getNgayTao(), x.getNgaySua());
    }

    @Override
    public SanPhamModel updateSP(SanPhamModel sp) {
        var x = spr.updateSP(new SanPham(sp.getId(), sp.getNsx(), sp.getMa(), sp.getTen(), sp.getMoTa(), sp.getNgayTao(), sp.getNgaySua()));
        return new SanPhamModel(x.getId(), x.getNsx(), x.getMa(), x.getTen(), x.getMoTa(), x.getNgayTao(), x.getNgaySua());
    }

    @Override
    public Integer deleteSP(String ma) {
        return spr.deleteSP(ma);
    }


}
