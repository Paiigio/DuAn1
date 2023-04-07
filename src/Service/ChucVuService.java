
package Service;

import Service.Interface.IChucVuService;
import DomainModels.ChucVu;
import ViewModel.ChucVuModel;
import java.util.ArrayList;
import responsitory.ChucVuResponsitory;

/**
 *
 * @author duong
 */
public class ChucVuService implements  IChucVuService{
private ChucVuResponsitory cvr = new ChucVuResponsitory();
    @Override
    public ArrayList<ChucVuModel> getAllChucVu() {
                ArrayList<ChucVuModel> list = new ArrayList<>();
        ArrayList<ChucVu> cv = cvr.getAllChucVu();
        for (ChucVu x : cv) {
            list.add( new ChucVuModel(x.getId(), x.getMa(),x.getTenCV(),x.getNgayTao(),x.getNgaySua()));
            
        }
        return list;
    }

    @Override
    public ChucVuModel insertCV(ChucVuModel cv) {
         ArrayList<ChucVu> list= cvr.getAllChucVu();
          for (ChucVu x : list) {
            if(x.getMa().equalsIgnoreCase(cv.getMa())){
                return null;
            }
            
        }
        var x= cvr.insertCV(new ChucVu(cv.getId(), cv.getMa(), cv.getTenCV(),cv.getNgayTao(),cv.getNgaySua()));
        return  new ChucVuModel(x.getId(), x.getMa(),x.getTenCV(),x.getNgayTao(),x.getNgaySua());
    }

    @Override
    public ChucVuModel updateCV(ChucVuModel cv) {
          var x= cvr.updateCV(new ChucVu(cv.getId(), cv.getMa(), cv.getTenCV(),cv.getNgayTao(),cv.getNgaySua()));
        return  new ChucVuModel(x.getId(), x.getMa(),x.getTenCV(),x.getNgayTao(),x.getNgaySua());
    }

    @Override
    public Integer deleteCV(String ma) {
             return cvr.deleteCV(ma);
    }
    
}
