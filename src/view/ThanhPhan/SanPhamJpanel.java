/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view.ThanhPhan;

import DomainModels.DungLuong;
import DomainModels.MauSac;
import DomainModels.SanPham;
import Service.CTSanPhamService;
import Service.DungLuongService;
import Service.Interface.ICTSanPhamService;
import Service.Interface.IDungLuongService;
import Service.Interface.IMauSacService;
import Service.Interface.ISanPhamService;
import Service.MauSacService;
import Service.SanPhamService;
import ViewModel.CTSanPhamModel;
import ViewModel.DungLuongModel;
import ViewModel.MauSacModel;
import ViewModel.SanPhamModel;
import java.awt.Image;
import java.io.File;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author duong
 */
public class SanPhamJpanel extends javax.swing.JPanel {

    private DefaultTableModel dtm = new DefaultTableModel();
    private DefaultComboBoxModel dcbmSP = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmDL = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmTKDL = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmMS = new DefaultComboBoxModel();
    private DefaultComboBoxModel dcbmTKMS = new DefaultComboBoxModel();
    private ICTSanPhamService iCTSanPhamService = new CTSanPhamService();
    private IMauSacService iMauSacService = new MauSacService();
    private IDungLuongService iDungLuongService = new DungLuongService();
    private ISanPhamService iSanPhamService = new SanPhamService();
    private String strHinhanh = "";

    /**
     * Creates new form SanPhamJpanel
     */
    public SanPhamJpanel() {
        initComponents();
        dtm = (DefaultTableModel) tblHienThi.getModel();
        cbbSP.setModel(dcbmSP);
        cbbMS.setModel(dcbmMS);
        cbbDL.setModel(dcbmDL);
        cbbTKDL.setModel(dcbmTKDL);
        cbbTKMS.setModel(dcbmTKMS);
        loadData();
        loadCBBSP();
        loadCBBMS();
        loadCBBDL();
        loadCBBTKDL();
        loadCBBTKMS();
    }

    private void loadCBBSP() {
        ArrayList<SanPhamModel> listSP = iSanPhamService.getAllSanPham();
        for (SanPhamModel x : listSP) {
            dcbmSP.addElement(new SanPham(x.getId(), x.getNsx(), x.getMa(), x.getTen(), x.getMoTa(), null, null));
        }
    }

    private void loadCBBMS() {
        ArrayList<MauSacModel> listMS = iMauSacService.getAllMauSac();
        for (MauSacModel x : listMS) {
            dcbmMS.addElement(new MauSac(x.getId(), x.getMa(), x.getTen()));
        }
    }

    private void loadCBBDL() {
        ArrayList<DungLuongModel> listDL = iDungLuongService.getAllDungLuong();
        for (DungLuongModel x : listDL) {
            dcbmDL.addElement(new DungLuong(x.getId(), x.getMa(), x.getSoDungLuong()));
        }
    }

    private void loadCBBTKMS() {
        ArrayList<MauSacModel> listMS = iMauSacService.getAllMauSac();
        for (MauSacModel x : listMS) {
            dcbmTKMS.addElement(new MauSac(x.getId(), x.getMa(), x.getTen()));
        }
    }

    private void loadCBBTKDL() {
        ArrayList<DungLuongModel> listDL = iDungLuongService.getAllDungLuong();
        for (DungLuongModel x : listDL) {
            dcbmTKDL.addElement(new DungLuong(x.getId(), x.getMa(), x.getSoDungLuong()));
        }
    }

    private void loadData() {
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < list.size(); ++i) {
            dtm.addRow(new Object[]{
                i + 1,
                list.get(i).getMa(),
                list.get(i).getSp(),
                list.get(i).getMs(),
                list.get(i).getDl(),
                list.get(i).getMaQR(),
                list.get(i).getSoLuongTon(),
                list.get(i).getHinhAnh(),
                list.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan())

            });
        }
    }

    private void clear() {
        txtGiaBan.setText("");
        txtGiaNhap.setText("");
        txtMa.setText("");
        txtMaQR.setText("");
        txtNamBH.setText("");
        txtSL.setText("");
        cbbDL.setSelectedIndex(0);
        cbbMS.setSelectedIndex(0);
        cbbSP.setSelectedIndex(0);
        lblAnhSP.setIcon(null);
    }
//    private String deleteDot(String chuoi){
//        for (int i = 0 ; i < chuoi.length() ; ++i){
//            c
//        }
//    }

    private CTSanPhamModel getCTSPFormInput() {
        String ma = txtMa.getText();
        String maQR = txtMaQR.getText();
        String soLuong = txtSL.getText();
        String giaNhap = txtGiaNhap.getText();
        String giaBan = txtGiaBan.getText();
        String namBH = txtNamBH.getText();
        SanPham sp = (SanPham) cbbSP.getSelectedItem();
        DungLuong dl = (DungLuong) cbbDL.getSelectedItem();
        MauSac ms = (MauSac) cbbMS.getSelectedItem();
        String anh = "";
        if (strHinhanh == null) {
            anh = "NoAvatar.jpg";
        } else {
            anh = strHinhanh;
        }
        if (ma.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã không được để trống");
            return null;
        }
        if (maQR.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Mã QR không được để trống");
            return null;
        }
        if (soLuong.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Số lượng tồn không được để trống");
            return null;
        }
        if (namBH.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Năm bảo hành không được để trống");
            return null;
        }
        if (giaNhap.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá nhập không được để trống");
            return null;
        }
        if (giaBan.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Giá bán không được để trống");
            return null;
        }
        return new CTSanPhamModel(null, ms, null, sp, dl, ma, maQR, Integer.valueOf(soLuong), anh, Integer.valueOf(namBH), null, null, Float.valueOf(giaNhap.replace(".","")), Float.valueOf(giaBan.replace(".","")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JSanPham = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        lblAnhSP = new javax.swing.JLabel();
        btnUpAnhThemSP = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        txtGiaNhap = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtGiaBan = new javax.swing.JTextField();
        cbbDL = new javax.swing.JComboBox<>();
        cbbMS = new javax.swing.JComboBox<>();
        cbbSP = new javax.swing.JComboBox<>();
        txtMaQR = new javax.swing.JTextField();
        txtNamBH = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        cbbTKMS = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHienThi = new javax.swing.JTable();
        cbbTKDL = new javax.swing.JComboBox<>();
        jLabel30 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(238, 232, 170));

        JSanPham.setBackground(new java.awt.Color(238, 232, 170));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Thêm sản phẩm"), "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel1.setOpaque(false);

        lblAnhSP.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblAnhSP.setOpaque(true);

        btnUpAnhThemSP.setText("Upload");
        btnUpAnhThemSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpAnhThemSPActionPerformed(evt);
            }
        });

        jLabel19.setText("Mã CTSP");

        jLabel20.setText("Mã QR");

        jLabel21.setText("Sản phẩm");

        jLabel22.setText("Dung lượng");

        jLabel23.setText("Năm BH:");

        jLabel26.setText("Số lượng");

        jLabel13.setText("Giá nhập");

        jLabel46.setText("Màu sắc");

        jLabel14.setText("Giá bán");

        cbbDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbSPActionPerformed(evt);
            }
        });

        txtNamBH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamBHActionPerformed(evt);
            }
        });

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(116, 116, 116)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(25, 25, 25))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18))
                            .addComponent(jLabel22)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnUpAnhThemSP)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(cbbDL, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(cbbMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(99, 99, 99)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(30, 30, 30)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNamBH)
                                        .addComponent(txtMaQR)
                                        .addComponent(txtSL)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtGiaBan))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(18, 18, 18)
                                    .addComponent(txtGiaNhap)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 287, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnThem)
                        .addGap(68, 68, 68)
                        .addComponent(btnSua)
                        .addGap(68, 68, 68)
                        .addComponent(btnClear)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 208, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(115, 115, 115))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSua, btnThem});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(cbbSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel20)
                                .addComponent(txtMaQR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel22)
                            .addComponent(cbbDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel46)
                            .addComponent(cbbMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnUpAnhThemSP)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnClear))))
                .addGap(17, 17, 17))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnSua, btnThem});

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setText("Tìm kiếm");

        txtTimKiem.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimKiemCaretUpdate(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setText("Màu sắc");

        cbbTKMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTKMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTKMSActionPerformed(evt);
            }
        });

        tblHienThi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTSP", "Tên SP", "Màu Sắc", "Dung Lượng", "Mã QR", "Số lượng tồn", "Hình ảnh", "Năm BH", "Giá Nhập", "Giá Bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHienThi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHienThiMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblHienThi);

        cbbTKDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbTKDL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTKDLActionPerformed(evt);
            }
        });

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel30.setText("Dung lượng");

        javax.swing.GroupLayout JSanPhamLayout = new javax.swing.GroupLayout(JSanPham);
        JSanPham.setLayout(JSanPhamLayout);
        JSanPhamLayout.setHorizontalGroup(
            JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JSanPhamLayout.createSequentialGroup()
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1076, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(JSanPhamLayout.createSequentialGroup()
                                .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(397, 397, 397)
                                .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTKMS, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbbTKDL, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(293, Short.MAX_VALUE))
        );
        JSanPhamLayout.setVerticalGroup(
            JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSanPhamLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JSanPhamLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addComponent(jLabel28))
                    .addGroup(JSanPhamLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTKMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTKDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(629, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1163, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpAnhThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpAnhThemSPActionPerformed
        try {
            JFileChooser f = new JFileChooser("D:\\duan1_nhom8\\src\\icon\\anhSP");
            f.showOpenDialog(null);
            File file = f.getSelectedFile();
            Image img = ImageIO.read(file);
            strHinhanh = file.getName();
            lblAnhSP.setText("");
            int width = lblAnhSP.getWidth();
            int height = lblAnhSP.getHeight();
            lblAnhSP.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnUpAnhThemSPActionPerformed

    private void txtNamBHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamBHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamBHActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        CTSanPhamModel ctsp = getCTSPFormInput();
        if (ctsp == null) {
            JOptionPane.showMessageDialog(this, "Thêm thất bại");
            return;
        }
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        for (CTSanPhamModel x : list) {
            if (x.getMa() != null && x.getMa().equals(ctsp.getMa())) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
                return;
            }
            if (x.getMaQR() != null && x.getMaQR().equals(ctsp.getMaQR())) {
                JOptionPane.showMessageDialog(this, "Mã QR đã tồn tại");
                return;
            }
        }
        if (iCTSanPhamService.insertCTSanPham(ctsp) != null) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            loadData();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int index = tblHienThi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(this, "Mời bạn chọn dòng cần sửa");
            return;
        }
        CTSanPhamModel ctsp = getCTSPFormInput();
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        for (CTSanPhamModel x : list) {
            if (x.getMa() != null && x.getMa().equals(tblHienThi.getValueAt(index, 1).toString())) {
                ctsp.setId(x.getId());
                continue;
            }
            if (x.getMaQR() != null && x.getMaQR().equals(tblHienThi.getValueAt(index, 5).toString())) {
                continue;
            }
            if (x.getMa() != null && x.getMa().equals(ctsp.getMa())) {
                JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
                return;
            }
            if (x.getMaQR() != null && x.getMaQR().equals(ctsp.getMaQR())) {
                JOptionPane.showMessageDialog(this, "Mã QR đã tồn tại");
                return;
            }
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        if (iCTSanPhamService.updateCTSanPham(ctsp) != null) {
            JOptionPane.showMessageDialog(this, "Update thành công");
            loadData();
        } else {
            JOptionPane.showMessageDialog(this, "Update thất bại");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void tblHienThiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHienThiMouseClicked
        int index = tblHienThi.getSelectedRow();
        txtMa.setText(tblHienThi.getValueAt(index, 1).toString());
        dcbmSP.setSelectedItem(tblHienThi.getValueAt(index, 2));
        dcbmMS.setSelectedItem(tblHienThi.getValueAt(index, 3));
        dcbmDL.setSelectedItem(tblHienThi.getValueAt(index, 4));
        txtMaQR.setText(tblHienThi.getValueAt(index, 5).toString());
        txtSL.setText(tblHienThi.getValueAt(index, 6).toString());
        String hinh = tblHienThi.getValueAt(index, 7).toString();
        ImageIcon imgicon = new ImageIcon(getClass().getResource("/icon/" + hinh));
        Image img = imgicon.getImage();
        imgicon.setImage(img.getScaledInstance(lblAnhSP.getWidth(), lblAnhSP.getHeight(), 0));
        lblAnhSP.setIcon(imgicon);
        txtNamBH.setText(tblHienThi.getValueAt(index, 8).toString());
        txtGiaNhap.setText(tblHienThi.getValueAt(index, 9).toString());
        txtGiaBan.setText(tblHienThi.getValueAt(index, 10).toString());
    }//GEN-LAST:event_tblHienThiMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        String timKiem = txtTimKiem.getText();
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
        for (CTSanPhamModel x : list){
            if (x.getSp().getTen()!=null && x.getSp().getTen().toLowerCase().contains(timKiem.toLowerCase())){
                listNEW.add(x);
            }
        }
        if (listNEW.size() < 0){
            JOptionPane.showMessageDialog(this, "Không tìm thấy dữ liệu");
            return;
        }
        dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < listNEW.size(); ++i) {
            dtm.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp(),
                listNEW.get(i).getMs(),
                listNEW.get(i).getDl(),
                listNEW.get(i).getMaQR(),
                listNEW.get(i).getSoLuongTon(),
                listNEW.get(i).getHinhAnh(),
                listNEW.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan())

            });
        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void cbbTKMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTKMSActionPerformed
       ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
       ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
       MauSac m = (MauSac) cbbTKMS.getSelectedItem();
       for (CTSanPhamModel x :list){
           if (x.getMs()!=null && x.getMs().getTen().equals(m.getTen())){
               listNEW.add(x);
           }
       }
               dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < listNEW.size(); ++i) {
            dtm.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp(),
                listNEW.get(i).getMs(),
                listNEW.get(i).getDl(),
                listNEW.get(i).getMaQR(),
                listNEW.get(i).getSoLuongTon(),
                listNEW.get(i).getHinhAnh(),
                listNEW.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan())

            });
        }
    }//GEN-LAST:event_cbbTKMSActionPerformed

    private void cbbTKDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTKDLActionPerformed
            ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
       ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
       DungLuong d = (DungLuong) cbbTKDL.getSelectedItem();
       for (CTSanPhamModel x :list){
           if (x.getDl()!=null && x.getDl().getSoDungLuong().equals(d.getSoDungLuong())){
               listNEW.add(x);
           }
       }
               dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < listNEW.size(); ++i) {
            dtm.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp(),
                listNEW.get(i).getMs(),
                listNEW.get(i).getDl(),
                listNEW.get(i).getMaQR(),
                listNEW.get(i).getSoLuongTon(),
                listNEW.get(i).getHinhAnh(),
                listNEW.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan())

            });
        }
    }//GEN-LAST:event_cbbTKDLActionPerformed

    private void cbbSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbSPActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JSanPham;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpAnhThemSP;
    private javax.swing.JComboBox<String> cbbDL;
    private javax.swing.JComboBox<String> cbbMS;
    private javax.swing.JComboBox<String> cbbSP;
    private javax.swing.JComboBox<String> cbbTKDL;
    private javax.swing.JComboBox<String> cbbTKMS;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JTable tblHienThi;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaQR;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
