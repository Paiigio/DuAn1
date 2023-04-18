package view.ThanhPhan;

import DomainModels.DungLuong;
import DomainModels.MauSac;
import DomainModels.SanPham;
import Service.CTSanPhamService;
import Service.DungLuongService;
import Service.IMEIService;
import Service.Interface.ICTSanPhamService;
import Service.Interface.IDungLuongService;
import Service.Interface.IIMEIService;
import Service.Interface.IMauSacService;
import Service.Interface.ISanPhamService;
import Service.MauSacService;
import Service.SanPhamService;
import ViewModel.CTSanPhamModel;
import ViewModel.DungLuongModel;
import ViewModel.IMEIModel;
import ViewModel.MauSacModel;
import ViewModel.NhanVienModel;
import ViewModel.SanPhamModel;
import java.awt.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.glxn.qrgen.image.ImageType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

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
    private IIMEIService iIMEIService = new IMEIService();
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
//        loadData();
        loadCBBSP();
        loadCBBMS();
        loadCBBDL();
        loadCBBTKDL();
        loadCBBTKMS();
        load();
    }

    private void load() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    loadData();
//                    loadCBBSP();
//                    loadCBBMS();
//                    loadCBBDL();
//                    loadCBBTKDL();
//                    loadCBBTKMS();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThongKeJpanel.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }).start();
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
    private int catMa(String ma){
        String chuSo = ma.substring(2);
        int so = Integer.valueOf(chuSo);
        return so;
    }
    private void loadData() {
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        Collections.sort(list, (CTSanPhamModel o1, CTSanPhamModel o2) -> catMa(o1.getMa()) > catMa(o2.getMa()) ? 1 : -1);
        dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < list.size(); ++i) {
            ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(list.get(i).getId());
            dtm.addRow(new Object[]{
                i + 1,
                list.get(i).getMa(),
                list.get(i).getSp(),
                list.get(i).getMs(),
                list.get(i).getDl(),
                list.get(i).getMa(),
                listIMEI.size(),
                list.get(i).getHinhAnh(),
                list.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan()),
                list.get(i).getTrangThai() == 0 ? "Còn bán" : "Không bán"

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

    private String checkSP() {
        CTSanPhamModel ctspM = getCTSPFormInput();
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        for (CTSanPhamModel x : list) {

            if (x.getSp().getTen().equals(ctspM.getSp().getTen())
                    && x.getDl().getSoDungLuong().equals(ctspM.getDl().getSoDungLuong())
                    && x.getMs().getTen().equals(ctspM.getMs().getTen())) {
                return "Có";
            }
        }

        return "Thêm";

    }

    private CTSanPhamModel getCTSPFormInput() {
        String giaNhap = txtGiaNhap.getText();
        String giaBan = txtGiaBan.getText();
        String namBH = txtNamBH.getText();
        SanPham sp = (SanPham) cbbSP.getSelectedItem();
        DungLuong dl = (DungLuong) cbbDL.getSelectedItem();
        MauSac ms = (MauSac) cbbMS.getSelectedItem();
        String anh = "";
        String maQR = sp.getTen() + "" + dl.getSoDungLuong() + "" + ms.getTen();

        if (strHinhanh == null) {
            anh = "NoAvatar.jpg";
        } else {
            anh = strHinhanh;
        }
        if (maQR.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã QR không được để trống");
            return null;
        }
        if (namBH.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Năm bảo hành không được để trống");
            return null;
        }
        if (giaNhap.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá nhập không được để trống");
            return null;
        }
        if (giaBan.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá bán không được để trống");
            return null;
        }
        return new CTSanPhamModel(null, ms, null, sp, dl, null, maQR, anh, Integer.valueOf(namBH), null, null, Float.valueOf(giaNhap.replace(".", "")), Float.valueOf(giaBan.replace(".", "")), 0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        btnDungLuong = new javax.swing.JButton();
        btnSP = new javax.swing.JButton();
        btnMauSac = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMota = new javax.swing.JTextArea();
        jLabel15 = new javax.swing.JLabel();
        rdBan = new javax.swing.JRadioButton();
        rdKhongBan = new javax.swing.JRadioButton();
        btnInMa = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
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

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
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

        txtMa.setEnabled(false);

        jLabel20.setText("Mã QR");

        jLabel21.setText("Sản phẩm");

        jLabel22.setText("Dung lượng");

        jLabel23.setText("Năm BH:");

        jLabel26.setText("Số lượng");

        jLabel13.setText("Giá nhập");

        txtSL.setEnabled(false);
        txtSL.setOpaque(true);

        jLabel46.setText("Màu sắc");

        jLabel14.setText("Giá bán");

        cbbDL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbMS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbbSP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        txtMaQR.setEnabled(false);

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

        btnDungLuong.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus-2-math.png"))); // NOI18N
        btnDungLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDungLuongActionPerformed(evt);
            }
        });

        btnSP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus-2-math.png"))); // NOI18N
        btnSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSPActionPerformed(evt);
            }
        });

        btnMauSac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/plus-2-math.png"))); // NOI18N
        btnMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMauSacActionPerformed(evt);
            }
        });

        jLabel1.setText("Mô tả:");

        txtMota.setColumns(20);
        txtMota.setRows(5);
        jScrollPane1.setViewportView(txtMota);

        jLabel15.setText("Trạng thái");

        buttonGroup1.add(rdBan);
        rdBan.setText("Còn bán");

        buttonGroup1.add(rdKhongBan);
        rdKhongBan.setText("Không bán");

        btnInMa.setText("In mã ");
        btnInMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInMaActionPerformed(evt);
            }
        });

        jButton1.setText("Import");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Refresh");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Export");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnUpAnhThemSP)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGap(8, 8, 8)
                                    .addComponent(cbbDL, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(cbbMS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbSP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtMa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDungLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton2)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNamBH)
                                    .addComponent(txtMaQR)
                                    .addComponent(txtSL)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiaBan))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtGiaNhap))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdBan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rdKhongBan)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnInMa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSua)
                        .addGap(2, 2, 2)
                        .addComponent(btnClear)))
                .addGap(114, 114, 114))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnInMa, btnSua, btnThem, jButton1, jButton2, jButton3});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel23)
                                    .addComponent(txtNamBH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
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
                                            .addComponent(cbbDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(btnSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnDungLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txtGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel46)
                                    .addComponent(cbbMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(btnMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(rdBan)
                                    .addComponent(rdKhongBan))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnClear)
                                    .addComponent(btnSua)
                                    .addComponent(btnThem)
                                    .addComponent(btnInMa)
                                    .addComponent(jButton3)
                                    .addComponent(jButton1)
                                    .addComponent(jButton2)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblAnhSP, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(btnUpAnhThemSP)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã CTSP", "Tên SP", "Màu Sắc", "Dung Lượng", "Mã QR", "Số lượng tồn", "Hình ảnh", "Năm BH", "Giá Nhập", "Giá Bán", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, true
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
                .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(JSanPhamLayout.createSequentialGroup()
                            .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(397, 397, 397)
                            .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbbTKMS, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(54, 54, 54)
                            .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cbbTKDL, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(293, Short.MAX_VALUE))
        );
        JSanPhamLayout.setVerticalGroup(
            JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JSanPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JSanPhamLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel28))
                    .addGroup(JSanPhamLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTKMS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbTKDL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(690, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 557, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpAnhThemSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpAnhThemSPActionPerformed
        try {
            JFileChooser f = new JFileChooser("D:\\DuAn1_FInal\\src\\icon\\anhSP");
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

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clear();
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        String ma = "SP"+(list.size()+1);
        CTSanPhamModel ctsp = getCTSPFormInput();
        ctsp.setMa(ma);
        if (ctsp == null) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
            return;
        }

//   
        if (checkSP().equals("Có")) {
            JOptionPane.showMessageDialog(null, "Đã có sản phẩm này");
//
//            btnSuaActionPerformed(evt);
//
//            loadData();
        return;

        } else if (checkSP().equals("Thêm")) {
            if (iCTSanPhamService.insertCTSanPham(ctsp) != null) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                loadData();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm thất bại");
            }
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        int index = tblHienThi.getSelectedRow();
        if (index < 0) {
            JOptionPane.showMessageDialog(null, "Mời bạn chọn dòng cần sửa");
            return;
        }
        CTSanPhamModel ctsp = getCTSPFormInput();
        ctsp.setMa(txtMa.getText());
        int tt = 0;
        if (rdKhongBan.isSelected()) {
            tt = 1;
        }
        ctsp.setTrangThai(tt);
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
                JOptionPane.showMessageDialog(null, "Mã đã tồn tại");
                return;
            }
            if (x.getMaQR() != null && x.getMaQR().equals(ctsp.getMaQR())) {
                JOptionPane.showMessageDialog(null, "Mã QR đã tồn tại");
                return;
            }
        }
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn sửa không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        if (iCTSanPhamService.updateCTSanPham(ctsp) != null) {
            JOptionPane.showMessageDialog(null, "Update thành công");
            loadData();
        } else {
            JOptionPane.showMessageDialog(null, "Update thất bại");
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
        ImageIcon imgicon = new ImageIcon(getClass().getResource("/icon/anhSP/" + hinh));
        Image img = imgicon.getImage();
        imgicon.setImage(img.getScaledInstance(lblAnhSP.getWidth(), lblAnhSP.getHeight(), 0));
        lblAnhSP.setIcon(imgicon);
        txtNamBH.setText(tblHienThi.getValueAt(index, 8).toString());
        txtGiaNhap.setText(tblHienThi.getValueAt(index, 9).toString());
        txtGiaBan.setText(tblHienThi.getValueAt(index, 10).toString());
        String trangThai = tblHienThi.getValueAt(index, 11).toString();
        if (trangThai.trim().equals("Còn bán")) {
            rdBan.setSelected(true);
        } else {
            rdKhongBan.setSelected(true);
        }
        SanPham sp = (SanPham) tblHienThi.getValueAt(index, 2);
        txtMota.setText(sp.getMoTa());
    }//GEN-LAST:event_tblHienThiMouseClicked

    private void txtTimKiemCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimKiemCaretUpdate
        String timKiem = txtTimKiem.getText();
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
        for (CTSanPhamModel x : list) {
            if (x.getSp().getTen() != null && x.getSp().getTen().toLowerCase().contains(timKiem.toLowerCase())) {
                listNEW.add(x);
            }
        }
        if (listNEW.size() < 0) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy dữ liệu");
            return;
        }
        dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < listNEW.size(); ++i) {
            ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(listNEW.get(i).getId());
            dtm.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp(),
                listNEW.get(i).getMs(),
                listNEW.get(i).getDl(),
                listNEW.get(i).getMaQR(),
                listIMEI.size(),
                listNEW.get(i).getHinhAnh(),
                listNEW.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan()),
                listNEW.get(i).getTrangThai() == 0 ? "Còn bán" : "Không bán"
            });
        }
    }//GEN-LAST:event_txtTimKiemCaretUpdate

    private void cbbTKMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTKMSActionPerformed
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
        MauSac m = (MauSac) cbbTKMS.getSelectedItem();
        for (CTSanPhamModel x : list) {
            if (x.getMs() != null && x.getMs().getTen().equals(m.getTen())) {
                listNEW.add(x);
            }
        }
        dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < listNEW.size(); ++i) {
            ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(listNEW.get(i).getId());
            dtm.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp(),
                listNEW.get(i).getMs(),
                listNEW.get(i).getDl(),
                listNEW.get(i).getMaQR(),
                listIMEI.size(),
                listNEW.get(i).getHinhAnh(),
                listNEW.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan()),
                listNEW.get(i).getTrangThai() == 0 ? "Còn bán" : "Không bán"
            });
        }
    }//GEN-LAST:event_cbbTKMSActionPerformed

    private void cbbTKDLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTKDLActionPerformed
        ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
        ArrayList<CTSanPhamModel> listNEW = new ArrayList<>();
        DungLuong d = (DungLuong) cbbTKDL.getSelectedItem();
        for (CTSanPhamModel x : list) {
            if (x.getDl() != null && x.getDl().getSoDungLuong().equals(d.getSoDungLuong())) {
                listNEW.add(x);
            }
        }
        dtm.setRowCount(0);
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat vn = NumberFormat.getInstance(localeVN);
        for (int i = 0; i < listNEW.size(); ++i) {
            ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(listNEW.get(i).getId());
            dtm.addRow(new Object[]{
                i + 1,
                listNEW.get(i).getMa(),
                listNEW.get(i).getSp(),
                listNEW.get(i).getMs(),
                listNEW.get(i).getDl(),
                listNEW.get(i).getMaQR(),
                listIMEI.size(),
                listNEW.get(i).getHinhAnh(),
                listNEW.get(i).getNamBH(),
                vn.format(list.get(i).getGiaNhap()),
                vn.format(list.get(i).getGiaBan()),
                listNEW.get(i).getTrangThai() == 0 ? "Còn bán" : "Không bán"
            });
        }
    }//GEN-LAST:event_cbbTKDLActionPerformed

    private void btnSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSPActionPerformed
        new QL_SanPham().setVisible(true);
    }//GEN-LAST:event_btnSPActionPerformed

    private void btnDungLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDungLuongActionPerformed
        new QL_DungLuong().setVisible(true);
    }//GEN-LAST:event_btnDungLuongActionPerformed

    private void btnMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMauSacActionPerformed
        new QL_MauSac().setVisible(true);
    }//GEN-LAST:event_btnMauSacActionPerformed

    private void btnInMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInMaActionPerformed
        int row = tblHienThi.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sp bạn muốn in mã");
            return;
        }
        String maQR = tblHienThi.getValueAt(row, 5).toString();

        try {

            ByteArrayOutputStream out = net.glxn.qrgen.QRCode.from(maQR)
                    .to(ImageType.PNG).stream();

            String f_name = maQR;
            String Path_name = "D:\\DuAn1_FInal\\src\\QRSANPHAM\\";

            FileOutputStream fout = new FileOutputStream(new File(Path_name + (f_name + ".PNG")));
            fout.write(out.toByteArray());
            fout.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnInMaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dcbmSP.removeAllElements();
        dcbmDL.removeAllElements();
        dcbmMS.removeAllElements();
        loadCBBSP();
        loadCBBMS();
        loadCBBDL();
        loadData();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new QL_SanPham().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục xuất file  không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Sản Phẩm");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sách Sản Phẩm");

            row = sheet.createRow(1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Số TT");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã CTSP");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên SP");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Màu sắc");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Dung lượng");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Mã QR");
            cell = row.createCell(6, CellType.NUMERIC);
            cell.setCellValue("Số lượng tồn");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Hình ảnh");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Năm BH");
            cell = row.createCell(9, CellType.STRING);
            cell.setCellValue("Giá Nhập");
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Giá Bán");
            cell = row.createCell(11, CellType.STRING);
            cell.setCellValue("Trạng Thái");

            ArrayList<CTSanPhamModel> list = iCTSanPhamService.getAllCTSanPham();
            dtm.setRowCount(0);
            Locale localeVN = new Locale("vi", "VN");
            NumberFormat vn = NumberFormat.getInstance(localeVN);
            for (int i = 0; i < list.size(); i++) {
                ArrayList<IMEIModel> listIMEI = iIMEIService.selectSL(list.get(i).getId());

                row = sheet.createRow(2 + i);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(i + 1);
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getMa());
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getSp().getTen());
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getMs().getTen());
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getDl().getSoDungLuong());
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(list.get(i).getMaQR());
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(list.size());
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(list.get(i).getHinhAnh());
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(list.get(i).getNamBH());
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(list.get(i).getGiaNhap());
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(list.get(i).getGiaBan());
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue(list.get(i).getTrangThai());

            }
            File f = new File("D:\\DuAn1_FInal\\excel\\SanPhamExcel.xlsx");

            try {
                FileOutputStream fis = new FileOutputStream(f);
                wb.write(fis);
                fis.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();

            } catch (IOException ex) {
                ex.printStackTrace();
            }

            JOptionPane.showMessageDialog(null, "Done !!!");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error !!!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JSanPham;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDungLuong;
    private javax.swing.JButton btnInMa;
    private javax.swing.JButton btnMauSac;
    private javax.swing.JButton btnSP;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpAnhThemSP;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbDL;
    private javax.swing.JComboBox<String> cbbMS;
    private javax.swing.JComboBox<String> cbbSP;
    private javax.swing.JComboBox<String> cbbTKDL;
    private javax.swing.JComboBox<String> cbbTKMS;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnhSP;
    private javax.swing.JRadioButton rdBan;
    private javax.swing.JRadioButton rdKhongBan;
    private javax.swing.JTable tblHienThi;
    private javax.swing.JTextField txtGiaBan;
    private javax.swing.JTextField txtGiaNhap;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtMaQR;
    private javax.swing.JTextArea txtMota;
    private javax.swing.JTextField txtNamBH;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
