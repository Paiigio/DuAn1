package Service;

import DomainModels.DungLuong;
import Service.Interface.IDungLuongService;
import ViewModel.DungLuongModel;
import java.util.ArrayList;
import responsitory.DungLuongResponsitory;

/**
 *
 * @author kazee
 */
public class DungLuongService implements IDungLuongService {

    private DungLuongResponsitory dlr = new DungLuongResponsitory();

    @Override
    public ArrayList<DungLuongModel> getAllDungLuong() {
        ArrayList<DungLuongModel> list = new ArrayList<>();
        ArrayList<DungLuong> dl = dlr.getAllDungLuong();
        for (DungLuong x : dl) {
            list.add(new DungLuongModel(x.getId(),x.getMa(), x.getSoDungLuong()));
        }
        return list;
    }

    @Override
    public DungLuongModel insertDL(DungLuongModel dl) {
        ArrayList<DungLuong> list = dlr.getAllDungLuong();
        for (DungLuong x : list) {
            if (x.getMa().equalsIgnoreCase(dl.getMa())) {
                return null;
            }

        }
        var x = dlr.insertDL(new DungLuong(dl.getId(), dl.getMa(), dl.getSoDungLuong()));
        return new DungLuongModel(x.getId(), x.getMa(), x.getSoDungLuong());
    }

    @Override
    public DungLuongModel updateDL(DungLuongModel dl) {
        var x = dlr.updateDL(new DungLuong(dl.getId(), dl.getMa(), dl.getSoDungLuong()));
        return new DungLuongModel(x.getId(), x.getMa(), x.getSoDungLuong());
    }

    @Override
    public Integer deleteDL(String ma) {
        return dlr.deleteDL(ma);
    }
}
