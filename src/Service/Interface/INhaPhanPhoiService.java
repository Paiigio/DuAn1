/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.NhaPhanPhoiModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface INhaPhanPhoiService {
        public ArrayList<NhaPhanPhoiModel> getAllNPP() ;
    public NhaPhanPhoiModel insertNPP(NhaPhanPhoiModel cv);
    public NhaPhanPhoiModel updateNPP(NhaPhanPhoiModel cv);
}
