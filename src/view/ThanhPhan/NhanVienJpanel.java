package view.ThanhPhan;

import DomainModels.ChucVu;
import DomainModels.NhanVien;
import Service.ChucVuService;
import Service.Interface.IChucVuService;
import Service.Interface.INhanVienService;
import Service.NhanVienService;
import ViewModel.ChucVuModel;
import ViewModel.NhanVienModel;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author duong
 */
public class NhanVienJpanel extends javax.swing.JPanel {

    IChucVuService cvs = new ChucVuService();
    INhanVienService nvs = new NhanVienService();
    DefaultComboBoxModel<ChucVu> dcmCV;
    DefaultComboBoxModel<ChucVu> dcmCV1;
    DefaultTableModel dtmNV = new DefaultTableModel();
    String strHinhanh = null;

    public NhanVienJpanel() {
        initComponents();
        dcmCV = new DefaultComboBoxModel<>();
        dcmCV1 = new DefaultComboBoxModel<>();
        loadComboCV();
        loadComboVaiTro();
        cbbCV.setModel((DefaultComboBoxModel) dcmCV);
        cbbVaiTroNV.setModel((DefaultComboBoxModel) dcmCV1);

        dtmNV = (DefaultTableModel) tblBangTTNV.getModel();
        loadTableNV();
        tblBangTTNV.getColumnModel().getColumn(0).setPreferredWidth(30);
        tblBangTTNV.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblBangTTNV.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblBangTTNV.getColumnModel().getColumn(3).setPreferredWidth(10);
        tblBangTTNV.getColumnModel().getColumn(7).setPreferredWidth(200);

    }

    private void loadComboCV() {
        ArrayList<ChucVuModel> list = cvs.getAllChucVu();
        for (ChucVuModel x : list) {
            dcmCV.addElement(new ChucVu(x.getMa(), x.getTenCV()));
        }
    }

    private void loadComboVaiTro() {
        ArrayList<ChucVuModel> list = cvs.getAllChucVu();
        for (ChucVuModel x : list) {
            dcmCV1.addElement(new ChucVu(x.getMa(), x.getTenCV()));
        }
    }

    private NhanVienModel getFormData() {
        ChucVu cv = (ChucVu) cbbCV.getSelectedItem();

        String ten = txtHoTen.getText().trim();
        String sdt = txtSDT.getText().trim();
        Date ngay = txtNgaySinhNV.getDate();

        String gt = rdoNam.isSelected() == true ? "Nam" : "Nữ";
        String diachi = txtDCNV.getText().trim();
        String email = txtEmail.getText().trim();
        String mk = txtMK.getText().trim();
        int tt = rdoDiLam.isSelected() == true ? 0 : 1;

        ArrayList<ChucVuModel> list = cvs.getAllChucVu();
        for (ChucVuModel c : list) {
            if (c.getMa() != null && c.getMa().equalsIgnoreCase(cv.getMa())) {
                cv.setId(c.getId());
            }
        }

        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống tên");
            txtHoTen.requestFocus();
            return null;
        }
        if (ngay == null) {
            JOptionPane.showMessageDialog(null, "Không được để trống ngày sinh");
            txtNgaySinhNV.requestFocus();
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-dd-MM");
            String date = sdf.format(txtNgaySinhNV.getDate());
        }

        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống số điện thoại");
            txtSDT.requestFocus();
            return null;
        } else {
            try {
                int dienthoai = Integer.parseInt(txtSDT.getText());

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Sai định dạng số điện thoại");
                txtSDT.requestFocus();
                e.printStackTrace();
                return null;
            }
            String ktsdt = "0\\d{9}";
            if (txtSDT.getText().matches(ktsdt) == false) {
                JOptionPane.showMessageDialog(null, "Bạn nhập sai số điện thoại");
                txtSDT.requestFocus();
                return null;
            }
        }
        if (email.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống email");
            txtMK.requestFocus();
            return null;
        } else {
            String mail = "\\w+@(\\w+\\.\\w+){1,2}";
            Matcher matcher = Pattern.compile(mail).matcher(txtEmail.getText());
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "Sai định dạng email");
                return null;
            }
        }
        if (mk.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống mật khẩu");
            txtMK.requestFocus();
            return null;
        }

        if (diachi.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống địa chỉ");
            txtDCNV.requestFocus();
            return null;
        }
        String anh = "";
        if (strHinhanh == null) {
            anh = "NoAvatar.jpg";
        } else {
            anh = strHinhanh;
        }

        return new NhanVienModel(null, cv, null, ten, gt, sdt, ngay, diachi, email, mk, tt, anh);

    }

    public void clearFormTTNV() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        rdoNam.setSelected(true);
        txtSDT.setText("");
        cbbCV.setSelectedIndex(0);
        txtDCNV.setText("");
        txtEmail.setText("");
        txtMK.setText("");
        rdoDiLam.setSelected(true);
        lblAnhNV.setIcon(null);
        txtNgaySinhNV.setDate(null);
    }

    private int catMa(String ma) {
        String chuSo = ma.substring(2);
        int so = Integer.valueOf(chuSo);
        return so;
    }

    public void loadTableNV() {
        ArrayList<NhanVienModel> list = nvs.getAllNV();
        Collections.sort(list, (NhanVienModel o1, NhanVienModel o2) -> catMa(o1.getMa()) > catMa(o2.getMa()) ? 1 : -1);
        dtmNV.setRowCount(0);
        for (NhanVienModel x : list) {
            dtmNV.addRow(new Object[]{
                x.getCv(),
                x.getMa(),
                x.getHoTen(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getNgaySinh(),
                x.getDiaChi(),
                x.getEmail(),
                x.getMatKhau(),
                x.getTrangThai() == 0 ? "Đi làm" : "Nghỉ làm",
                x.getHinhAnh()
            });

        }
    }

    public void loadTableNV1(String ten) {
        ArrayList<NhanVienModel> list = nvs.getTimTen(ten);
        Collections.sort(list, (NhanVienModel o1, NhanVienModel o2) -> catMa(o1.getMa()) > catMa(o2.getMa()) ? 1 : -1);
        dtmNV.setRowCount(0);
        for (NhanVienModel x : list) {
            dtmNV.addRow(new Object[]{
                x.getCv(),
                x.getMa(),
                x.getHoTen(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getNgaySinh(),
                x.getDiaChi(),
                x.getEmail(),
                x.getMatKhau(),
                x.getTrangThai() == 0 ? "Đi làm" : "Nghỉ làm",
                x.getHinhAnh()
            });

        }
    }

    public void loadTableVaiTro(String ten) {
        ArrayList<NhanVienModel> list = nvs.getVaiTro(ten);
        Collections.sort(list, (NhanVienModel o1, NhanVienModel o2) -> catMa(o1.getMa()) > catMa(o2.getMa()) ? 1 : -1);
        dtmNV.setRowCount(0);
        for (NhanVienModel x : list) {
            dtmNV.addRow(new Object[]{
                x.getCv(),
                x.getMa(),
                x.getHoTen(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getNgaySinh(),
                x.getDiaChi(),
                x.getEmail(),
                x.getMatKhau(),
                x.getTrangThai() == 0 ? "Đi làm" : "Nghỉ làm",
                x.getHinhAnh()
            });

        }
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
        buttonGroup2 = new javax.swing.ButtonGroup();
        JNhanVien = new javax.swing.JPanel();
        TTNhanVien = new javax.swing.JPanel();
        lblAnhNV = new javax.swing.JLabel();
        btnUpAnhNV = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbCV = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtDCNV = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMK = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        rdoDiLam = new javax.swing.JRadioButton();
        rdoNghiLam = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        txtNgaySinhNV = new com.toedter.calendar.JDateChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBangTTNV = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        txtTimNV = new javax.swing.JTextField();
        btnTIm = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        cbbVaiTroNV = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        cbbTT = new javax.swing.JComboBox<>();
        XuatExcel = new javax.swing.JButton();

        setBackground(new java.awt.Color(238, 232, 170));

        JNhanVien.setBackground(new java.awt.Color(238, 232, 170));

        TTNhanVien.setBackground(new java.awt.Color(238, 232, 170));
        TTNhanVien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Thiết lập thông tin nhân viên", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        TTNhanVien.setOpaque(false);

        lblAnhNV.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        lblAnhNV.setOpaque(true);

        btnUpAnhNV.setText("Thêm ảnh");
        btnUpAnhNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpAnhNVActionPerformed(evt);
            }
        });

        jLabel4.setText("Mã NV");

        txtMaNV.setEnabled(false);

        jLabel5.setText("Chức vụ");

        jLabel6.setText("Họ tên");

        jLabel7.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        jLabel8.setText("SDT");

        jLabel9.setText("Ngày sinh");

        jLabel10.setText("Địa chỉ");

        jLabel11.setText("Email");

        jLabel12.setText("Mật khẩu");

        jLabel3.setText("Tình trạng");

        buttonGroup2.add(rdoDiLam);
        rdoDiLam.setText("Đi làm");

        buttonGroup2.add(rdoNghiLam);
        rdoNghiLam.setText("Nghỉ làm");

        btnThem.setText("Thêm ");
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

        btnClear.setText("ClearForm");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TTNhanVienLayout = new javax.swing.GroupLayout(TTNhanVien);
        TTNhanVien.setLayout(TTNhanVienLayout);
        TTNhanVienLayout.setHorizontalGroup(
            TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTNhanVienLayout.createSequentialGroup()
                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TTNhanVienLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHoTen))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTNhanVienLayout.createSequentialGroup()
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtMaNV)
                                    .addComponent(cbbCV, 0, 169, Short.MAX_VALUE)))
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSDT)))
                        .addGap(87, 87, 87)
                        .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(rdoDiLam)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rdoNghiLam, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TTNhanVienLayout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, TTNhanVienLayout.createSequentialGroup()
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(txtDCNV, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(TTNhanVienLayout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(btnUpAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206)
                        .addComponent(btnThem)
                        .addGap(82, 82, 82)
                        .addComponent(btnSua)
                        .addGap(106, 106, 106)
                        .addComponent(btnClear)))
                .addContainerGap(390, Short.MAX_VALUE))
        );

        TTNhanVienLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnClear, btnSua, btnThem});

        TTNhanVienLayout.setVerticalGroup(
            TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTNhanVienLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(TTNhanVienLayout.createSequentialGroup()
                        .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(cbbCV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(rdoNam)
                                    .addComponent(rdoNu)))
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtNgaySinhNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtDCNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(TTNhanVienLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(rdoDiLam)
                                    .addComponent(rdoNghiLam))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TTNhanVienLayout.createSequentialGroup()
                        .addComponent(btnUpAnhNV, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTNhanVienLayout.createSequentialGroup()
                        .addGroup(TTNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem)
                            .addComponent(btnSua)
                            .addComponent(btnClear))
                        .addGap(18, 18, 18))))
        );

        TTNhanVienLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnClear, btnSua, btnThem});

        tblBangTTNV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Chức vụ", "Mã NV", "Tên NV", "Giới tính", "SDT", "Ngày sinh", "Địa chỉ", "Email", "Mật khẩu", "Tình trạng ", "Hình ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBangTTNV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangTTNVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBangTTNV);

        jLabel16.setText("Tên nhân viên");

        btnTIm.setText("Tìm");
        btnTIm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTImActionPerformed(evt);
            }
        });

        jLabel17.setText("Vai trò");

        cbbVaiTroNV.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nhân viên", "Giám đốc", "Quản lý" }));
        cbbVaiTroNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbVaiTroNVActionPerformed(evt);
            }
        });

        jLabel18.setText("Trạng thái");

        cbbTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đi làm", "Nghỉ làm" }));
        cbbTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbTTActionPerformed(evt);
            }
        });

        XuatExcel.setText("Export Excel");
        XuatExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                XuatExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JNhanVienLayout = new javax.swing.GroupLayout(JNhanVien);
        JNhanVien.setLayout(JNhanVienLayout);
        JNhanVienLayout.setHorizontalGroup(
            JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JNhanVienLayout.createSequentialGroup()
                .addGroup(JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(JNhanVienLayout.createSequentialGroup()
                            .addGap(45, 45, 45)
                            .addGroup(JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(JNhanVienLayout.createSequentialGroup()
                                    .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(btnTIm)))
                            .addGap(80, 80, 80)
                            .addGroup(JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cbbVaiTroNV, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(99, 99, 99)
                            .addGroup(JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel18)
                                .addGroup(JNhanVienLayout.createSequentialGroup()
                                    .addComponent(cbbTT, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(48, 48, 48)
                                    .addComponent(XuatExcel))))
                        .addGroup(JNhanVienLayout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addComponent(TTNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(813, Short.MAX_VALUE))
        );
        JNhanVienLayout.setVerticalGroup(
            JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JNhanVienLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(TTNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTIm)
                    .addComponent(cbbVaiTroNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(XuatExcel))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(176, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpAnhNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpAnhNVActionPerformed
        try {
            JFileChooser f = new JFileChooser("D:\\duan1_nhom8\\DuAn1\\src\\AnhNV");
            f.showOpenDialog(null);
            File file = f.getSelectedFile();
            Image img = ImageIO.read(file);
            strHinhanh = file.getName();
            lblAnhNV.setText("");
            int width = lblAnhNV.getWidth();
            int height = lblAnhNV.getHeight();
            lblAnhNV.setIcon(new ImageIcon(img.getScaledInstance(width, height, 0)));
        } catch (Exception e) {
            e.printStackTrace();

        }
    }//GEN-LAST:event_btnUpAnhNVActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ArrayList<NhanVienModel> listNV = nvs.getAllNV();

        String ma = "NV" + (listNV.size() + 1);
        NhanVienModel nv = getFormData();
        for (NhanVienModel x : listNV) {
            if (x.getSdt() != null && x.getSdt().equals(nv.getSdt())) {
                JOptionPane.showMessageDialog(null, "Số điện thoại này đã được sử dụng");
                return;
            }
        }
        if (nv == null) {
            return;
        }
        nv.setMa(ma);

        if (nvs.insertNV(nv) != null) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại do trùng mã");
        }
        loadTableNV();
        clearFormTTNV();

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        ArrayList<NhanVienModel> listNV = nvs.getAllNV();
        int row = tblBangTTNV.getSelectedRow();
        String sdt = tblBangTTNV.getValueAt(row, 4).toString();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn dòng cần sửa");
            return;
        }
        NhanVienModel nv = getFormData();
        nv.setMa(txtMaNV.getText());
        for (NhanVienModel x : listNV) {
            if (x.getSdt() != null && x.getSdt().equals(sdt)) {
                continue;
                } else if (x.getSdt() != null && x.getSdt().equals(txtSDT.getText())){
                JOptionPane.showMessageDialog(null, "Số điện thoại này đã được sử dụng");
                return;
            }
        }
        if (nv == null) {
            return;
        }
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục sửa không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        String id = tblBangTTNV.getValueAt(row, 1).toString();
        String anh = tblBangTTNV.getValueAt(row, 10).toString();
        if (anh != null) {
            nv.setHinhAnh(anh);
        }
        nv.setId(id);
        if (nvs.updateNV(nv) != null) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
        loadTableNV();
        clearFormTTNV();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        clearFormTTNV();
    }//GEN-LAST:event_btnClearActionPerformed

    private void tblBangTTNVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangTTNVMouseClicked

        try {
            int row = this.tblBangTTNV.getSelectedRow();
            if (row == -1) {
                return;
            }
            txtMaNV.setText(tblBangTTNV.getValueAt(row, 1).toString());
            dcmCV.setSelectedItem(tblBangTTNV.getValueAt(row, 0));
            txtHoTen.setText(tblBangTTNV.getValueAt(row, 2).toString());
            String gt = tblBangTTNV.getValueAt(row, 3).toString();
            if (gt.equalsIgnoreCase("Nam")) {
                this.rdoNam.setSelected(true);
            } else {
                this.rdoNu.setSelected(true);
            }
            txtSDT.setText(tblBangTTNV.getValueAt(row, 4).toString());

            txtDCNV.setText(tblBangTTNV.getValueAt(row, 6).toString());
            txtEmail.setText(tblBangTTNV.getValueAt(row, 7).toString());
            txtMK.setText(tblBangTTNV.getValueAt(row, 8).toString());
            String tt = tblBangTTNV.getValueAt(row, 9).toString();
            String hinh = tblBangTTNV.getValueAt(row, 10).toString();
            String ngay = tblBangTTNV.getValueAt(row, 5).toString();
            if (tt.equalsIgnoreCase("Đi làm")) {
                rdoDiLam.setSelected(true);
            } else {
                rdoNghiLam.setSelected(true);
            }

            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            txtNgaySinhNV.setDate(date);

            ImageIcon imgicon = new ImageIcon(getClass().getResource("/AnhNV/" + hinh));
            Image img = imgicon.getImage();
            img.getScaledInstance(lblAnhNV.getWidth(), lblAnhNV.getY(), 0);
            lblAnhNV.setIcon(imgicon);
        } catch (ParseException ex) {
            Logger.getLogger(NhanVienJpanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_tblBangTTNVMouseClicked

    private void btnTImActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTImActionPerformed
        String tim = txtTimNV.getText().trim();
        if (tim.length() == 0) {
            JOptionPane.showMessageDialog(this, "không đc để trống tìm");
        }
        if (nvs.getTimTen(tim).size() > 0) {
            JOptionPane.showMessageDialog(null, "Tìm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Tìm thành thất bại");
        }
        loadTableNV1(tim);
    }//GEN-LAST:event_btnTImActionPerformed

    private void cbbVaiTroNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbVaiTroNVActionPerformed

        ArrayList<NhanVienModel> list = nvs.getAllNV();
        ArrayList<NhanVienModel> listNew = new ArrayList<>();
        ChucVu cv = (ChucVu) cbbVaiTroNV.getSelectedItem();

        for (NhanVienModel x : list) {
            if (x.getCv() != null && x.getCv().getMa().equals(cv.getMa())) {
                listNew.add(x);
            }
        }
        dtmNV.setRowCount(0);
        for (NhanVienModel z : listNew) {
            dtmNV.addRow(new Object[]{
                z.getCv(),
                z.getMa(),
                z.getHoTen(),
                z.getGioiTinh(),
                z.getSdt(),
                z.getNgaySinh(),
                z.getDiaChi(),
                z.getEmail(),
                z.getMatKhau(),
                z.getTrangThai() == 0 ? "Đi làm" : "Nghỉ làm",
                z.getHinhAnh()
            });

        }
    }//GEN-LAST:event_cbbVaiTroNVActionPerformed
    public void loadTableTT(String ten) {
        ArrayList<NhanVienModel> list = nvs.getVaiTro(ten);
        dtmNV.setRowCount(0);
        for (NhanVienModel x : list) {
            dtmNV.addRow(new Object[]{
                x.getCv(),
                x.getMa(),
                x.getHoTen(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getNgaySinh(),
                x.getDiaChi(),
                x.getEmail(),
                x.getMatKhau(),
                x.getTrangThai() == 0 ? "Đi làm" : "Nghỉ làm",
                x.getHinhAnh()
            });

        }
    }
    private void cbbTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbTTActionPerformed

        int index = cbbTT.getSelectedIndex();
        ArrayList<NhanVienModel> list = nvs.getAllNV();
        ArrayList<NhanVienModel> listNew = new ArrayList<>();
        for (NhanVienModel x : list) {
            if (x.getTrangThai() == index) {
                listNew.add(x);
            }
        }
        dtmNV.setRowCount(0);
        for (NhanVienModel z : listNew) {
            dtmNV.addRow(new Object[]{
                z.getCv(),
                z.getMa(),
                z.getHoTen(),
                z.getGioiTinh(),
                z.getSdt(),
                z.getNgaySinh(),
                z.getDiaChi(),
                z.getEmail(),
                z.getMatKhau(),
                z.getTrangThai() == 0 ? "Đi làm" : "Nghỉ làm",
                z.getHinhAnh()
            });

        }
    }//GEN-LAST:event_cbbTTActionPerformed

    private void XuatExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_XuatExcelActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục xuất file  không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        ArrayList<NhanVienModel> list = nvs.getAllNV();
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("Nhân Viên");
            XSSFRow row = null;
            Cell cell = null;
            row = sheet.createRow(0);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sách Nhân Viên");

            row = sheet.createRow(1);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Chức Vụ");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã NV");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên NV");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Giới Tính");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("SDT");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Ngày Sinh");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Địa Chỉ");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("Email");
            cell = row.createCell(8, CellType.STRING);
            cell.setCellValue("Mật Khẩu");
            cell = row.createCell(9, CellType.NUMERIC);
            cell.setCellValue("Tình Trạng");
            cell = row.createCell(10, CellType.STRING);
            cell.setCellValue("Hình Ảnh");

            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(2 + i);
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(list.get(i).getCv().getTenCV());
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(list.get(i).getMa());
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(list.get(i).getHoTen());
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(list.get(i).getGioiTinh());
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(list.get(i).getSdt());
                cell = row.createCell(5, CellType.STRING);
                cell.setCellValue(list.get(i).getNgaySinh());
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(list.get(i).getDiaChi());
                cell = row.createCell(7, CellType.STRING);
                cell.setCellValue(list.get(i).getEmail());
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(list.get(i).getMatKhau());
                cell = row.createCell(9, CellType.NUMERIC);
                cell.setCellValue(list.get(i).getTrangThai());
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(list.get(i).getHinhAnh());

            }

            File f = new File("D:\\DuAn1_FInal\\excel\\NhanVienExcel.xlsx");

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
    }//GEN-LAST:event_XuatExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JNhanVien;
    private javax.swing.JPanel TTNhanVien;
    private javax.swing.JButton XuatExcel;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTIm;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnUpAnhNV;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbbCV;
    private javax.swing.JComboBox<String> cbbTT;
    private javax.swing.JComboBox<String> cbbVaiTroNV;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAnhNV;
    private javax.swing.JRadioButton rdoDiLam;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNghiLam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblBangTTNV;
    private javax.swing.JTextField txtDCNV;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private com.toedter.calendar.JDateChooser txtNgaySinhNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTimNV;
    // End of variables declaration//GEN-END:variables
}
