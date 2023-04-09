
package Service;

import DomainModels.NhaPhanPhoi;
import Service.Interface.INhaPhanPhoiService;
import ViewModel.NhaPhanPhoiModel;
import java.util.ArrayList;
import responsitory.NhaPhanPhoiResponsitory;
import responsitory.NhanVienResponsitory;

/**
 *
 * @author duong
 */
public class NhaPhanPhoiService implements INhaPhanPhoiService {

    private NhaPhanPhoiResponsitory npp = new NhaPhanPhoiResponsitory();

    @Override
    public ArrayList<NhaPhanPhoiModel> getAllNPP() {
        ArrayList<NhaPhanPhoiModel> list = new ArrayList<>();
        ArrayList<NhaPhanPhoi> cv = npp.getAllNPP();
        for (NhaPhanPhoi x : cv) {
            list.add(new NhaPhanPhoiModel(x.getId(), x.getMa(), x.getTen(), x.getDiaChi(), x.getEmail(), x.getSdt(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public NhaPhanPhoiModel insertNPP(NhaPhanPhoiModel cv) {
        ArrayList<NhaPhanPhoi> list = npp.getAllNPP();
        for (NhaPhanPhoi x : list) {
            if (x.getMa().equalsIgnoreCase(cv.getMa())) {
                return null;
            }

        }
        var x = npp.insertNPP(new NhaPhanPhoi(cv.getId(), cv.getMa(), cv.getTen(), cv.getDiaChi(), cv.getEmail(), cv.getSdt(), cv.getNgayTao(), cv.getNgaySua()));
        return  new NhaPhanPhoiModel(x.getId(), x.getMa(), x.getTen(), x.getDiaChi(), x.getEmail(), x.getSdt(), x.getNgayTao(), x.getNgaySua());

    }

    @Override
    public NhaPhanPhoiModel updateNPP(NhaPhanPhoiModel cv) {
          var x = npp.updateNPP(new NhaPhanPhoi(cv.getId(), cv.getMa(), cv.getTen(), cv.getDiaChi(), cv.getEmail(), cv.getSdt(), cv.getNgayTao(), cv.getNgaySua()));
        return  new NhaPhanPhoiModel(x.getId(), x.getMa(), x.getTen(), x.getDiaChi(), x.getEmail(), x.getSdt(), x.getNgayTao(), x.getNgaySua());

    }

}
