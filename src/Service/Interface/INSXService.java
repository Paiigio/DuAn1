/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.NSXModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface INSXService {

    public ArrayList<NSXModel> getAllNSX();

    public NSXModel insertNSX(NSXModel n);

    public NSXModel updateNSX(NSXModel n);

    public Integer deleteNSX(String ma);
}
