
package Service;

import DomainModels.MauSac;
import Service.Interface.IMauSacService;
import ViewModel.MauSacModel;
import java.util.ArrayList;
import responsitory.MauSacResponsitory;

/**
 *
 * @author duong
 */
public class MauSacService implements IMauSacService {

    private MauSacResponsitory msr = new MauSacResponsitory();

    @Override
    public ArrayList<MauSacModel> getAllMauSac() {
        ArrayList<MauSacModel> list = new ArrayList<>();
        ArrayList<MauSac> cv = msr.getAllMauSac();
        for (MauSac x : cv) {
            list.add(new MauSacModel(x.getId(), x.getMa(), x.getTen()));

        }
        return list;
    }

    @Override
    public MauSacModel insertMS(MauSacModel cv) {
        ArrayList<MauSac> list = msr.getAllMauSac();
        for (MauSac x : list) {
            if (x.getMa().equalsIgnoreCase(cv.getMa())) {
                return null;
            }

        }
        var x = msr.insertMS(new MauSac(cv.getId(), cv.getMa(), cv.getTen()));
        return new MauSacModel(x.getId(), x.getMa(), x.getTen());
    }

    @Override
    public MauSacModel updateMS(MauSacModel cv) {
        var x = msr.updateMS(new MauSac(cv.getId(), cv.getMa(), cv.getTen()));
        return new MauSacModel(x.getId(), x.getMa(), x.getTen());
    }

    @Override
    public Integer deleteMS(String ma) {
        return msr.deleteMS(ma);
    }

}
