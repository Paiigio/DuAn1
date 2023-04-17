package view;

import Service.Interface.INhanVienService;
import Service.NhanVienService;
import ViewModel.NhanVienModel;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Mail.ResetPass;
import java.util.Random;

public class Login extends javax.swing.JFrame {

    private INhanVienService nvs = new NhanVienService();
    public static NhanVienModel nv = null;
    private ResetPass mail = new ResetPass();
    public String userLogin = "";

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setTitle("Đăng nhập");
        lab3.setVisible(false);
        lab4.setVisible(false);
        btnReset.setVisible(false);
        
        this.setLocationRelativeTo(this);

    }

    public String getUser() {
        ArrayList<NhanVienModel> list = nvs.getAllNV();
        for (NhanVienModel x : list) {
            if (x.getMa().equals(txtUser.getText()) && x.getMatKhau().equals(String.valueOf(txtPass.getPassword()))) {
                nv = x;
            }
        }
        return nv.getMa();
    }

    private boolean check() {
        if (txtUser.getText().trim().length() == 0 & txtPass.getPassword().length == 0) {
            txtUser.requestFocus();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if (txtUser.getText().trim().length() == 0) {
            txtUser.requestFocus();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ID");
            return false;
        }
        if (txtPass.getPassword().length == 0) {
            txtPass.requestFocus();
            JOptionPane.showMessageDialog(this, "Vui lòng nhập Pass");
            return false;
        }
        return true;
    }

    private void clearMauButoon() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lab3 = new javax.swing.JLabel();
        lab4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        btnReset = new javax.swing.JButton();
        Panel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        btnLogin = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        lab8 = new javax.swing.JLabel();
        lab7 = new javax.swing.JLabel();
        chkCheck = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        lab2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        Lab1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập");
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 40, 30));

        lab3.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lab3.setForeground(new java.awt.Color(255, 255, 255));
        lab3.setText("Forgot Pass");
        jPanel1.add(lab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 120, 220, 50));

        lab4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit.png"))); // NOI18N
        jPanel1.add(lab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 230, 30, 20));

        jPanel6.setOpaque(false);

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnReset.setText("Reset Password");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnReset, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 420, 270, 50));

        Panel1.setBackground(new java.awt.Color(51, 51, 51));
        Panel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(255, 51, 51));
        jPanel4.setForeground(new java.awt.Color(255, 51, 102));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        Panel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, 10));

        txtUser.setBackground(new java.awt.Color(51, 51, 51));
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(null);
        Panel1.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 230, 30));

        txtPass.setBackground(new java.awt.Color(51, 51, 51));
        txtPass.setForeground(new java.awt.Color(255, 255, 255));
        txtPass.setBorder(null);
        Panel1.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 230, 30));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Gear.png"))); // NOI18N
        Panel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 30, 20));

        jPanel5.setOpaque(false);

        btnLogin.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnLogin.setText("Sign in");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        Panel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 310, 270, 50));
        Panel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 230, 10));
        Panel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 230, 10));

        lab8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        lab8.setForeground(new java.awt.Color(255, 255, 255));
        lab8.setText("Sign in");
        Panel1.add(lab8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 50));

        lab7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/User.png"))); // NOI18N
        Panel1.add(lab7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 40, -1));

        chkCheck.setForeground(new java.awt.Color(255, 255, 255));
        chkCheck.setText("Show Password");
        chkCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCheckActionPerformed(evt);
            }
        });
        Panel1.add(chkCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, -1, -1));

        jPanel1.add(Panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 420, 440));

        lab2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lab2.setText("Forgot Password");
        lab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lab2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 80, 120, 30));

        Lab1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Lab1.setText("           Sign in");
        Lab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                Lab1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Lab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Lab1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 130, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("QUẢN LÝ BÁN ĐIỆN THOẠI");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 20, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/iphone-x-mac.gif"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 490, 490));

        jLabel1.setBackground(new java.awt.Color(3, 2, 3));
        jLabel1.setOpaque(true);
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 610));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void Lab1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Lab1MousePressed

        lab3.setVisible(false);
        lab4.setVisible(false);
        btnReset.setVisible(false);
        btnLogin.setVisible(true);
        lab7.setVisible(true);
        lab8.setVisible(true);
        txtPass.setVisible(true);
        chkCheck.setVisible(true);
        jSeparator2.setVisible(true);
        jLabel4.setVisible(true);
        Lab1.setEnabled(false);
        lab2.setEnabled(true);
        txtPass.setText("");
        txtUser.setText("");

    }//GEN-LAST:event_Lab1MousePressed

    private void lab2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lab2MousePressed

        btnLogin.setVisible(false);
        lab7.setVisible(false);
        lab8.setVisible(false);
        lab3.setVisible(true);
        lab4.setVisible(true);
        btnReset.setVisible(true);
        txtPass.setVisible(false);
        chkCheck.setVisible(false);
        jSeparator2.setVisible(false);
        jLabel4.setVisible(false);
        lab2.setEnabled(false);
        Lab1.setEnabled(true);
        txtUser.setText("");

    }//GEN-LAST:event_lab2MousePressed

    private void chkCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCheckActionPerformed
        if (chkCheck.isSelected()) {
            txtPass.setEchoChar((char) 0);
        }
        if (chkCheck.isSelected() == false) {
            txtPass.setEchoChar('\u25cf');
        }
    }//GEN-LAST:event_chkCheckActionPerformed
 
    
    public String randomPassword() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int len = 6;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
   
        return generatedString;
    }
            
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        String user = txtUser.getText();

        ArrayList<NhanVienModel> list = nvs.getAllNV();
        for (NhanVienModel x : list) {
            if (!(x.getMa().equals(user))) {
                JOptionPane.showMessageDialog(this, "Username (Mã nhân viên) không tồn tại trong hệ thống hoặc đã hết hạn!");
                break;
            }
            String random = randomPassword();
            mail.sendEmail(x.getEmail(), random);
            nvs.updatePass(random, x.getMa());
            JOptionPane.showMessageDialog(this, "Dùng mật khẩu vừa được gửi đến email " + x.getEmail() + " của bạn để đăng nhập.");
        }
        
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
       
        ArrayList<NhanVienModel> list = nvs.getAllNV();
        if (txtUser.getText().trim().isEmpty() | String.valueOf(txtPass.getPassword()).trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tài khoản và mật khẩu không được để trống!");
            return;
        }
        boolean check = false;

        for (NhanVienModel x : list) {
            if (x.getMa().equals(txtUser.getText()) && x.getMatKhau().equals(String.valueOf(txtPass.getPassword()))) {
                nv = x;
                check = true;
            } 
        }

        if (check) {
            if (nv.getTrangThai() == 1) {
                JOptionPane.showMessageDialog(this, "Tài khoản hết hạn");
                return;

            } else {

                new Loading().setVisible(true);

                this.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Sai tên đăng nhập hoặc mật khẩu");
            return;
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Lab1;
    private javax.swing.JPanel Panel1;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnReset;
    private javax.swing.JCheckBox chkCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lab2;
    private javax.swing.JLabel lab3;
    private javax.swing.JLabel lab4;
    private javax.swing.JLabel lab7;
    private javax.swing.JLabel lab8;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
