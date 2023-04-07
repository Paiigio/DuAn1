
package Service;

import DomainModels.NSX;
import Service.Interface.INSXService;
import ViewModel.NSXModel;
import java.util.ArrayList;
import responsitory.NSXResponsitory;

/**
 *
 * @author duong
 */
public class NSXService implements INSXService {

    private NSXResponsitory n = new NSXResponsitory();

    @Override
    public ArrayList<NSXModel> getAllNSX() {
        ArrayList<NSXModel> list = new ArrayList<>();
        ArrayList<NSX> nsx = n.getAllNSX();
        for (NSX x : nsx) {
            list.add(new NSXModel(x.getId(), x.getMa(), x.getTen(), x.getNgayTao(), x.getNgaySua()));

        }
        return list;
    }

    @Override
    public NSXModel insertNSX(NSXModel nsx) {
        ArrayList<NSX> list = n.getAllNSX();
        for (NSX x : list) {
            if (x.getMa().equalsIgnoreCase(nsx.getMa())) {
                return null;
            }

        }
        var x = n.insertNSX(new NSX(nsx.getId(), nsx.getMa(), nsx.getTen(), nsx.getNgayTao(), nsx.getNgaySua()));
        return new NSXModel(x.getId(), x.getMa(), x.getTen(), x.getNgayTao(), x.getNgaySua());
    }

    @Override
    public NSXModel updateNSX(NSXModel nsx) {
        var x = n.updateNSX(new NSX(nsx.getId(), nsx.getMa(), nsx.getTen(), nsx.getNgayTao(), nsx.getNgaySua()));
        return new NSXModel(x.getId(), x.getMa(), x.getTen(), x.getNgayTao(), x.getNgaySua());
    }

    @Override
    public Integer deleteNSX(String ma) {
        return n.deleteNSX(ma);
    }

}
