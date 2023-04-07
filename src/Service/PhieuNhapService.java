
package Service;

import DomainModels.PhieuNhap;
import Service.Interface.IPhieuNhapService;
import ViewModel.PhieuNhapModel;
import java.util.ArrayList;
import responsitory.PhieuNhapResponsitory;

/**
 *
 * @author duong
 */
public class PhieuNhapService implements IPhieuNhapService{
    private  PhieuNhapResponsitory pnr = new PhieuNhapResponsitory();

    @Override
    public ArrayList<PhieuNhapModel> getAllPhieuNhap() {
          ArrayList<PhieuNhapModel> list = new ArrayList<>();
        ArrayList<PhieuNhap> nv = pnr.getAllPhieuNhap();
        for (PhieuNhap x : nv) {
            list.add(new PhieuNhapModel(x.getId(), x.getNhaPhanPhoi(), x.getNhanVien(), x.getMaPN(), x.getTongGia(), x.getNgayTao()));

        }
        return list;
    }

    @Override
    public PhieuNhapModel insertPN(PhieuNhapModel cv) {
           ArrayList<PhieuNhap> ds = pnr.getAllPhieuNhap();
        for (PhieuNhap phieuNhap : ds) {
            if (phieuNhap.getMaPN().equalsIgnoreCase(cv.getMaPN())) {
                return null;
            }
        }

        var x = pnr.insertPN(new PhieuNhap(cv.getId(), cv.getNhaPhanPhoi(), cv.getNhanVien(), cv.getMaPN(), cv.getTongGia(), cv.getNgayTao()));
        return new PhieuNhapModel(x.getId(), x.getNhaPhanPhoi(), x.getNhanVien(), x.getMaPN(), x.getTongGia(), x.getNgayTao());
    
    }

    @Override
    public PhieuNhapModel updatePN(PhieuNhapModel cv) {
    var x = pnr.updatePN(new PhieuNhap(cv.getId(), cv.getNhaPhanPhoi(), cv.getNhanVien(), cv.getMaPN(), cv.getTongGia(), cv.getNgayTao()));
        return new PhieuNhapModel(x.getId(), x.getNhaPhanPhoi(), x.getNhanVien(), x.getMaPN(), x.getTongGia(), x.getNgayTao());
    

    }
    
}
