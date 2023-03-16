/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Service.Interface;

import ViewModel.MauSacModel;
import java.util.ArrayList;

/**
 *
 * @author duong
 */
public interface IMauSacService {
    public ArrayList<MauSacModel> getAllMauSac();
    public MauSacModel insertMS(MauSacModel cv);
    public MauSacModel updateMS(MauSacModel cv);
    public Integer deleteMS(String ma);
}
