package Service.Interface;

import ViewModel.DungLuongModel;
import java.util.ArrayList;

/**
 *
 * @author kazee
 */
public interface IDungLuongService {

    public ArrayList<DungLuongModel> getAllDungLuong();

    public DungLuongModel insertDL(DungLuongModel dl);

    public DungLuongModel updateDL(DungLuongModel dl);

    public Integer deleteDL(String ma);
}
