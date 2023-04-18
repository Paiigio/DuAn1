package view.ThanhPhan;

import Service.Interface.IKhachHangService;
import Service.KhachHangService;
import Utilites.JDBC_Helper;
import ViewModel.KhachHangModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import view.QL_DT;

/**
 *
 * @author duong
 */
public class KhachHangJpanel extends javax.swing.JPanel {

    private IKhachHangService khs = new KhachHangService();
    DefaultTableModel dtm = new DefaultTableModel();
    long count, soTrang, Trang = 1;

    public KhachHangJpanel() {
        initComponents();

        dtm = (DefaultTableModel) tblBang.getModel();
        loadTable();
        tblBang.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblBang.getColumnModel().getColumn(0).setPreferredWidth(30);

    }

    public void countKH() {
        try {
            String sql = "SELECT count(*) From KHACHHANG";
            ResultSet rs = JDBC_Helper.excuteQuery(sql);
            while (rs.next()) {
                count = rs.getLong(1);
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int catMa(String ma) {
        String chuSo = ma.substring(2);
        int so = Integer.valueOf(chuSo);
        return so;
    }

    public void loadTable() {
        ArrayList<KhachHangModel> list = khs.getAllKH();
        dtm.setRowCount(0);
        Collections.sort(list, (KhachHangModel o1, KhachHangModel o2) -> catMa(o1.getMaKH()) > catMa(o2.getMaKH()) ? 1 : -1);
        for (KhachHangModel x : list) {
            dtm.addRow(new Object[]{
                x.getMaKH(),
                x.getHoTen(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getNgaySinh(),
                x.getDiaChi(),
                x.getEmail(),
                x.getNgayTao(),
                x.getNgaySua()
            });

        }
    }

    public void loadTableTim(String sdt) {
        ArrayList<KhachHangModel> list = khs.getTimSDT(sdt);
        dtm.setRowCount(0);
        Collections.sort(list, (KhachHangModel o1, KhachHangModel o2) -> catMa(o1.getMaKH()) > catMa(o2.getMaKH()) ? 1 : -1);
        for (KhachHangModel x : list) {
            dtm.addRow(new Object[]{
                x.getMaKH(),
                x.getHoTen(),
                x.getGioiTinh(),
                x.getSdt(),
                x.getNgaySinh(),
                x.getDiaChi(),
                x.getEmail(),
                x.getNgayTao(),
                x.getNgaySua()
            });

        }
    }

    private KhachHangModel getFormData() {

        String ten = txtHoTen.getText().trim();
        String sdt = txtSDT.getText().trim();
        Date ngay = txtNgaySinh.getDate();
        String gt = rdoNam.isSelected() == true ? "Nam" : "Nữ";
        String diachi = txtDiaChi.getText().trim();
        String email = txtEmail.getText().trim();
        if (ten.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống tên");
            txtHoTen.requestFocus();
            return null;
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
        if (ngay == null) {
            JOptionPane.showMessageDialog(null, "Không được để trống ngày sinh");
            txtNgaySinh.requestFocus();
            return null;
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("YYYY-dd-MM");
            String date = sdf.format(txtNgaySinh.getDate());
        }
        if (diachi.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống địa chỉ");
            txtDiaChi.requestFocus();
            return null;
        }

        if (email.length() == 0) {
            JOptionPane.showMessageDialog(null, "Không được để trống email");
            txtEmail.requestFocus();
            return null;
        } else {
            String mail = "\\w+@(\\w+\\.\\w+){1,2}";
            Matcher matcher = Pattern.compile(mail).matcher(txtEmail.getText());
            if (!matcher.matches()) {
                JOptionPane.showMessageDialog(null, "Sai định dạng email");
                return null;
            }
        }

        return new KhachHangModel(null, null, sdt, ten, diachi, gt, email, ngay, null, null);

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
        KhachHang = new javax.swing.JPanel();
        TTNhanVien1 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        txtMaKh = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel63 = new javax.swing.JLabel();
        rdoNam = new javax.swing.JRadioButton();
        rdoNu = new javax.swing.JRadioButton();
        txtSDT = new javax.swing.JTextField();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        txtDiaChi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        btnThem = new javax.swing.JButton();
        btnSUa = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        txtNgaySinh = new com.toedter.calendar.JDateChooser();
        jScrollPane7 = new javax.swing.JScrollPane();
        tblBang = new javax.swing.JTable();
        jLabel72 = new javax.swing.JLabel();
        txtTim = new javax.swing.JTextField();
        jButton23 = new javax.swing.JButton();

        KhachHang.setBackground(new java.awt.Color(238, 232, 170));

        TTNhanVien1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(""), "Thiết lập thông tin khách hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        TTNhanVien1.setOpaque(false);

        jLabel60.setText("Mã KH");

        txtMaKh.setEnabled(false);

        jLabel62.setText("Họ tên");

        jLabel63.setText("Giới tính");

        buttonGroup1.add(rdoNam);
        rdoNam.setText("Nam");

        buttonGroup1.add(rdoNu);
        rdoNu.setText("Nữ");

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        jLabel64.setText("SDT");

        jLabel65.setText("Ngày sinh");

        jLabel66.setText("Địa chỉ");

        jLabel67.setText("Email");

        btnThem.setText("Thêm ");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSUa.setText("Sửa");
        btnSUa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSUaActionPerformed(evt);
            }
        });

        jButton22.setText("ClearForm");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout TTNhanVien1Layout = new javax.swing.GroupLayout(TTNhanVien1);
        TTNhanVien1.setLayout(TTNhanVien1Layout);
        TTNhanVien1Layout.setHorizontalGroup(
            TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTNhanVien1Layout.createSequentialGroup()
                .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TTNhanVien1Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btnSUa, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(jButton22))
                    .addGroup(TTNhanVien1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(TTNhanVien1Layout.createSequentialGroup()
                                .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(rdoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rdoNu, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTNhanVien1Layout.createSequentialGroup()
                                .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtHoTen))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TTNhanVien1Layout.createSequentialGroup()
                                .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMaKh, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TTNhanVien1Layout.createSequentialGroup()
                                .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87)
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(TTNhanVien1Layout.createSequentialGroup()
                                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(TTNhanVien1Layout.createSequentialGroup()
                                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(TTNhanVien1Layout.createSequentialGroup()
                                .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(544, Short.MAX_VALUE))
        );

        TTNhanVien1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnSUa, btnThem, jButton22});

        TTNhanVien1Layout.setVerticalGroup(
            TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TTNhanVien1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TTNhanVien1Layout.createSequentialGroup()
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel60)
                            .addComponent(txtMaKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel62)
                            .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(rdoNam)
                            .addComponent(rdoNu)))
                    .addGroup(TTNhanVien1Layout.createSequentialGroup()
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel65)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel66)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel67)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(TTNhanVien1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSUa)
                    .addComponent(jButton22))
                .addGap(18, 18, 18))
        );

        TTNhanVien1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnSUa, btnThem, jButton22});

        tblBang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã KH", "Họ tên", "Giới tính", "SDT", "Ngày sinh", "Địa chỉ", "Email", "Ngày tạo", "Ngày sửa"
            }
        ));
        tblBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBangMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(tblBang);

        jLabel72.setText("SDT");

        jButton23.setText("Tìm");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout KhachHangLayout = new javax.swing.GroupLayout(KhachHang);
        KhachHang.setLayout(KhachHangLayout);
        KhachHangLayout.setHorizontalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KhachHangLayout.createSequentialGroup()
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 1197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(KhachHangLayout.createSequentialGroup()
                                .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton23))))
                    .addGroup(KhachHangLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(TTNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        KhachHangLayout.setVerticalGroup(
            KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KhachHangLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(TTNhanVien1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(KhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton23))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(146, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(KhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(KhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        KhachHang.getAccessibleContext().setAccessibleName("cardKH");
    }// </editor-fold>//GEN-END:initComponents

    private void tblBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBangMouseClicked

        try {
            int row = this.tblBang.getSelectedRow();
            if (row == -1) {
                return;
            }
            txtMaKh.setText(tblBang.getValueAt(row, 0).toString());
            txtHoTen.setText(tblBang.getValueAt(row, 1).toString());
            String gt = tblBang.getValueAt(row, 2).toString();
            if (gt.equalsIgnoreCase("Nam")) {
                this.rdoNam.setSelected(true);
            } else {
                this.rdoNu.setSelected(true);
            }
            txtSDT.setText(tblBang.getValueAt(row, 3).toString());

            txtDiaChi.setText(tblBang.getValueAt(row, 5).toString());
            txtEmail.setText(tblBang.getValueAt(row, 6).toString());
            String ngay = tblBang.getValueAt(row, 4).toString();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
            txtNgaySinh.setDate(date);

        } catch (ParseException ex) {

        }
    }//GEN-LAST:event_tblBangMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        ArrayList<KhachHangModel> listKH = khs.getAllKH();
        String ma = "KH" + listKH.size();
        KhachHangModel kh = getFormData();
        for (KhachHangModel x : listKH) {
            if (x.getSdt() != null && x.getSdt().equals(kh.getSdt())) {
                JOptionPane.showMessageDialog(null, "Số điện thoại đã được sử dụng");
                return;
            }
        }
        if (kh == null) {
            return;
        }
        kh.setMaKH(ma);

        if (khs.insertKH(kh) != null) {
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Thêm thất bại do trùng mã");
        }
        loadTable();
        clearForm();

    }//GEN-LAST:event_btnThemActionPerformed
    public void clearForm() {
        txtMaKh.setText("");
        txtHoTen.setText("");
        txtSDT.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtNgaySinh.setDate(null);
        rdoNam.setSelected(true);
    }

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        clearForm();
    }//GEN-LAST:event_jButton22ActionPerformed

    private void btnSUaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSUaActionPerformed
        ArrayList<KhachHangModel> listKH = khs.getAllKH();
        int row = tblBang.getSelectedRow();
        String sdt = tblBang.getValueAt(row, 3).toString();
        if (row < 0) {
            JOptionPane.showMessageDialog(null, "Chọn dòng cần sửa");
            return;
        }
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục sửa không?", "Thông báo", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
            return;
        }
        KhachHangModel nv = getFormData();
        if (nv == null) {
            return;
        }
        nv.setMaKH(txtMaKh.getText());
        for (KhachHangModel x : listKH) {
            if (x.getSdt() != null && x.getSdt().equals(sdt)) {
                continue;
            } else if (x.getSdt() != null && x.getSdt().equals(nv.getSdt())) {

                JOptionPane.showMessageDialog(null, "Số điện thoại đã được sử dụng");
                return;
            }
        }

        String id = tblBang.getValueAt(row, 0).toString();
        nv.setId(id);
        if (khs.updateKH(nv) != null) {
            JOptionPane.showMessageDialog(null, "Sửa thành công");
        } else {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
        }
        loadTable();
        clearForm();
    }//GEN-LAST:event_btnSUaActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        String sdt = txtTim.getText().trim();
        if (sdt.length() == 0) {
            JOptionPane.showMessageDialog(null, "nhập ô tìm theo sdt");
        }
        if (khs.getTimSDT(sdt).size() > 0) {
            JOptionPane.showMessageDialog(null, "tìm thành công");
        } else {
            JOptionPane.showMessageDialog(null, "tìm thất bại");
        }
        loadTableTim(sdt);
        txtTim.setText("");

    }//GEN-LAST:event_jButton23ActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSDTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel KhachHang;
    private javax.swing.JPanel TTNhanVien1;
    private javax.swing.JButton btnSUa;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JRadioButton rdoNam;
    private javax.swing.JRadioButton rdoNu;
    private javax.swing.JTable tblBang;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaKh;
    private com.toedter.calendar.JDateChooser txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTim;
    // End of variables declaration//GEN-END:variables
}
