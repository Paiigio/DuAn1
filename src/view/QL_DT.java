package view;

import DomainModels.NhanVien;
import ViewModel.KhachHangModel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import view.ThanhPhan.BanHangJpanel;
import view.ThanhPhan.DoiMatKhauJpanel;
import view.ThanhPhan.HoaDonJpanel;
import view.ThanhPhan.KhachHangJpanel;
import view.ThanhPhan.KhuyenMaiJpanel;
import view.ThanhPhan.NhanVienJpanel;
import view.ThanhPhan.SanPhamJpanel;
import view.ThanhPhan.ThongKeJpanel;

public class QL_DT extends javax.swing.JFrame {

    CardLayout cardLayout;

    public QL_DT() {
        initComponents();
        this.setLocationRelativeTo(this);
        cardLayout = (CardLayout) panelCardGoc.getLayout();
        //show card 1 
        cardLayout.show(panelCardGoc, "trangchu");
        JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
        loaddulieuDangNhap();
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xsize = (int) tk.getScreenSize().getWidth();
        int ysize = (int) tk.getScreenSize().getHeight();
        Insets scrmax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBar = scrmax.bottom;
        this.setSize(xsize, ysize - taskBar);

    }

    public void loaddulieuDangNhap() {
        NhanVien nv = new NhanVien();
        lblTen.setText(Login.nv.getHoTen());
        lblChucVU.setText(Login.nv.getCv().getTenCV());
        ImageIcon imgicon = new ImageIcon(getClass().getResource("/AnhNV/" + Login.nv.getHinhAnh()));
        Image img = imgicon.getImage();
        img.getScaledInstance(lblAnhDangNhap.getWidth(), lblAnhDangNhap.getY(), 0);
        lblAnhDangNhap.setIcon(imgicon);
        if (Login.nv.getCv().getTenCV().equalsIgnoreCase("Nhân Viên")) {
            btnNhanVien.setEnabled(false);
            btnKhuyenMai.setEnabled(false);
            btnSanPham.setEnabled(false);
            btnNhanVien.addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    e.consume();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
                }
            });

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        Tong = new javax.swing.JPanel();
        Trai = new javax.swing.JPanel();
        btnTrangChu = new javax.swing.JButton();
        btnBanHang = new javax.swing.JButton();
        btnHoaDon = new javax.swing.JButton();
        btnSanPham = new javax.swing.JButton();
        btnKhuyenMai = new javax.swing.JButton();
        lblAnhDangNhap = new javax.swing.JLabel();
        btnKhachHang = new javax.swing.JButton();
        btnNhanVien = new javax.swing.JButton();
        btnThongke = new javax.swing.JButton();
        lblChucVU = new javax.swing.JLabel();
        Tren = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblTen = new javax.swing.JLabel();
        panelCardGoc = new javax.swing.JPanel();
        JKhachHang = new javax.swing.JPanel();
        JNhanVien = new javax.swing.JPanel();
        JThongKe = new javax.swing.JPanel();
        JBanHang = new javax.swing.JPanel();
        JKhuyenMai = new javax.swing.JPanel();
        JHoaDon = new javax.swing.JPanel();
        JSanPham = new javax.swing.JPanel();
        TrangChu = new javax.swing.JPanel();
        anhTrangchu = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý bán điện thoại");

        Tong.setBackground(new java.awt.Color(238, 232, 170));

        Trai.setBackground(new java.awt.Color(51, 51, 51));
        Trai.setRequestFocusEnabled(false);

        btnTrangChu.setBackground(new java.awt.Color(255, 215, 0));
        btnTrangChu.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnTrangChu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/trangchu.png"))); // NOI18N
        btnTrangChu.setText("Trang chủ");
        btnTrangChu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnTrangChu.setOpaque(true);
        btnTrangChu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnTrangChuMouseExited(evt);
            }
        });

        btnBanHang.setBackground(new java.awt.Color(255, 215, 0));
        btnBanHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/banhang.png"))); // NOI18N
        btnBanHang.setText("Bán Hàng");
        btnBanHang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBanHang.setOpaque(true);
        btnBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnBanHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnBanHangMouseExited(evt);
            }
        });
        btnBanHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanHangActionPerformed(evt);
            }
        });

        btnHoaDon.setBackground(new java.awt.Color(255, 215, 0));
        btnHoaDon.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/hoadon.png"))); // NOI18N
        btnHoaDon.setText("Hóa đơn");
        btnHoaDon.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnHoaDon.setOpaque(true);
        btnHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnHoaDonMouseExited(evt);
            }
        });
        btnHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHoaDonActionPerformed(evt);
            }
        });

        btnSanPham.setBackground(new java.awt.Color(255, 215, 0));
        btnSanPham.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnSanPham.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sanpham.png"))); // NOI18N
        btnSanPham.setText("Sản phẩm");
        btnSanPham.setOpaque(true);
        btnSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSanPhamMouseExited(evt);
            }
        });
        btnSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSanPhamActionPerformed(evt);
            }
        });

        btnKhuyenMai.setBackground(new java.awt.Color(255, 215, 0));
        btnKhuyenMai.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnKhuyenMai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/sale.png"))); // NOI18N
        btnKhuyenMai.setText("Khuyến mại");
        btnKhuyenMai.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnKhuyenMai.setOpaque(true);
        btnKhuyenMai.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKhuyenMaiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKhuyenMaiMouseExited(evt);
            }
        });
        btnKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhuyenMaiActionPerformed(evt);
            }
        });

        btnKhachHang.setBackground(new java.awt.Color(255, 215, 0));
        btnKhachHang.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnKhachHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/khachhang.png"))); // NOI18N
        btnKhachHang.setText("Khách hàng");
        btnKhachHang.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnKhachHang.setOpaque(true);
        btnKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnKhachHangMouseExited(evt);
            }
        });
        btnKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhachHangActionPerformed(evt);
            }
        });

        btnNhanVien.setBackground(new java.awt.Color(255, 215, 0));
        btnNhanVien.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/nhanvien.png"))); // NOI18N
        btnNhanVien.setText("Nhân viên");
        btnNhanVien.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnNhanVien.setOpaque(true);
        btnNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNhanVienMouseExited(evt);
            }
        });
        btnNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhanVienActionPerformed(evt);
            }
        });

        btnThongke.setBackground(new java.awt.Color(255, 215, 0));
        btnThongke.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnThongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/thongke.png"))); // NOI18N
        btnThongke.setText("Thống kê");
        btnThongke.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnThongke.setOpaque(true);
        btnThongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnThongkeMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnThongkeMouseExited(evt);
            }
        });
        btnThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongkeActionPerformed(evt);
            }
        });

        lblChucVU.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblChucVU.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout TraiLayout = new javax.swing.GroupLayout(Trai);
        Trai.setLayout(TraiLayout);
        TraiLayout.setHorizontalGroup(
            TraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TraiLayout.createSequentialGroup()
                .addGroup(TraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(TraiLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(TraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(TraiLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(TraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(TraiLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblChucVU, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblAnhDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        TraiLayout.setVerticalGroup(
            TraiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, TraiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblAnhDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblChucVU, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnTrangChu, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBanHang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnThongke, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TraiLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnBanHang, btnHoaDon, btnKhachHang, btnKhuyenMai, btnNhanVien, btnSanPham, btnThongke, btnTrangChu});

        Tren.setBackground(new java.awt.Color(238, 232, 170));
        Tren.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Đổi mật khẩu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Đăng xuất");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblTen.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        javax.swing.GroupLayout TrenLayout = new javax.swing.GroupLayout(Tren);
        Tren.setLayout(TrenLayout);
        TrenLayout.setHorizontalGroup(
            TrenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrenLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(589, 589, 589)
                .addComponent(jButton1)
                .addGap(29, 29, 29)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        TrenLayout.setVerticalGroup(
            TrenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrenLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(TrenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelCardGoc.setBackground(new java.awt.Color(238, 232, 170));
        panelCardGoc.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelCardGoc.setLayout(new java.awt.CardLayout());

        JKhachHang.setBackground(new java.awt.Color(238, 232, 170));
        JKhachHang.setPreferredSize(new java.awt.Dimension(1265, 710));

        javax.swing.GroupLayout JKhachHangLayout = new javax.swing.GroupLayout(JKhachHang);
        JKhachHang.setLayout(JKhachHangLayout);
        JKhachHangLayout.setHorizontalGroup(
            JKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        JKhachHangLayout.setVerticalGroup(
            JKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        panelCardGoc.add(JKhachHang, "cardkh");

        JNhanVien.setBackground(new java.awt.Color(238, 232, 170));
        JNhanVien.setPreferredSize(new java.awt.Dimension(1265, 710));

        javax.swing.GroupLayout JNhanVienLayout = new javax.swing.GroupLayout(JNhanVien);
        JNhanVien.setLayout(JNhanVienLayout);
        JNhanVienLayout.setHorizontalGroup(
            JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        JNhanVienLayout.setVerticalGroup(
            JNhanVienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        panelCardGoc.add(JNhanVien, "cardnhanvien");
        JNhanVien.getAccessibleContext().setAccessibleName("nhanvien");

        JThongKe.setBackground(new java.awt.Color(238, 232, 170));
        JThongKe.setPreferredSize(new java.awt.Dimension(1265, 710));

        javax.swing.GroupLayout JThongKeLayout = new javax.swing.GroupLayout(JThongKe);
        JThongKe.setLayout(JThongKeLayout);
        JThongKeLayout.setHorizontalGroup(
            JThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        JThongKeLayout.setVerticalGroup(
            JThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        panelCardGoc.add(JThongKe, "cardthongke");
        JThongKe.getAccessibleContext().setAccessibleName("thongke");
        JThongKe.getAccessibleContext().setAccessibleDescription("");

        JBanHang.setBackground(new java.awt.Color(238, 232, 170));
        JBanHang.setPreferredSize(new java.awt.Dimension(1265, 710));

        javax.swing.GroupLayout JBanHangLayout = new javax.swing.GroupLayout(JBanHang);
        JBanHang.setLayout(JBanHangLayout);
        JBanHangLayout.setHorizontalGroup(
            JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        JBanHangLayout.setVerticalGroup(
            JBanHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        panelCardGoc.add(JBanHang, "cardBH");
        JBanHang.getAccessibleContext().setAccessibleName("banhang");

        JKhuyenMai.setBackground(new java.awt.Color(238, 232, 170));
        JKhuyenMai.setPreferredSize(new java.awt.Dimension(1265, 710));

        javax.swing.GroupLayout JKhuyenMaiLayout = new javax.swing.GroupLayout(JKhuyenMai);
        JKhuyenMai.setLayout(JKhuyenMaiLayout);
        JKhuyenMaiLayout.setHorizontalGroup(
            JKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        JKhuyenMaiLayout.setVerticalGroup(
            JKhuyenMaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        panelCardGoc.add(JKhuyenMai, "cardkhuyenmai");
        JKhuyenMai.getAccessibleContext().setAccessibleName("khuyenmai");

        JHoaDon.setBackground(new java.awt.Color(238, 232, 170));
        JHoaDon.setPreferredSize(new java.awt.Dimension(1265, 710));

        javax.swing.GroupLayout JHoaDonLayout = new javax.swing.GroupLayout(JHoaDon);
        JHoaDon.setLayout(JHoaDonLayout);
        JHoaDonLayout.setHorizontalGroup(
            JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        JHoaDonLayout.setVerticalGroup(
            JHoaDonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        panelCardGoc.add(JHoaDon, "cardhoadon");
        JHoaDon.getAccessibleContext().setAccessibleName("hoadon");

        JSanPham.setBackground(new java.awt.Color(238, 232, 170));
        JSanPham.setPreferredSize(new java.awt.Dimension(1265, 710));

        javax.swing.GroupLayout JSanPhamLayout = new javax.swing.GroupLayout(JSanPham);
        JSanPham.setLayout(JSanPhamLayout);
        JSanPhamLayout.setHorizontalGroup(
            JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1290, Short.MAX_VALUE)
        );
        JSanPhamLayout.setVerticalGroup(
            JSanPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 749, Short.MAX_VALUE)
        );

        panelCardGoc.add(JSanPham, "cardsanpham");
        JSanPham.getAccessibleContext().setAccessibleName("sanpham");

        TrangChu.setBackground(new java.awt.Color(241, 94, 103));
        TrangChu.setPreferredSize(new java.awt.Dimension(1265, 710));

        anhTrangchu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/login.gif"))); // NOI18N

        javax.swing.GroupLayout TrangChuLayout = new javax.swing.GroupLayout(TrangChu);
        TrangChu.setLayout(TrangChuLayout);
        TrangChuLayout.setHorizontalGroup(
            TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrangChuLayout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(anhTrangchu)
                .addContainerGap(216, Short.MAX_VALUE))
        );
        TrangChuLayout.setVerticalGroup(
            TrangChuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TrangChuLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(anhTrangchu, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        panelCardGoc.add(TrangChu, "trangchu");
        TrangChu.getAccessibleContext().setAccessibleName("trangchu");

        javax.swing.GroupLayout TongLayout = new javax.swing.GroupLayout(Tong);
        Tong.setLayout(TongLayout);
        TongLayout.setHorizontalGroup(
            TongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TongLayout.createSequentialGroup()
                .addComponent(Trai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(TongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelCardGoc, javax.swing.GroupLayout.DEFAULT_SIZE, 1294, Short.MAX_VALUE)
                    .addComponent(Tren, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        TongLayout.setVerticalGroup(
            TongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TongLayout.createSequentialGroup()
                .addComponent(Tren, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCardGoc, javax.swing.GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE))
            .addComponent(Trai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelCardGoc.getAccessibleContext().setAccessibleName("panelCardGoc");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Tong, javax.swing.GroupLayout.PREFERRED_SIZE, 1522, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(Tong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnTrangChuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseClicked
        cardLayout.show(panelCardGoc, "trangchu");
    }//GEN-LAST:event_btnTrangChuMouseClicked

    private void btnTrangChuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseEntered
        btnTrangChu.setBackground(Color.pink);
    }//GEN-LAST:event_btnTrangChuMouseEntered

    private void btnTrangChuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTrangChuMouseExited
        btnTrangChu.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnTrangChuMouseExited

    private void btnHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseEntered
        btnHoaDon.setBackground(Color.pink);
    }//GEN-LAST:event_btnHoaDonMouseEntered

    private void btnHoaDonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoaDonMouseExited
        btnHoaDon.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnHoaDonMouseExited

    private void btnSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseEntered
        btnSanPham.setBackground(Color.pink);
    }//GEN-LAST:event_btnSanPhamMouseEntered

    private void btnSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSanPhamMouseExited
        btnSanPham.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnSanPhamMouseExited

    private void btnKhuyenMaiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhuyenMaiMouseEntered
        btnKhuyenMai.setBackground(Color.pink);
    }//GEN-LAST:event_btnKhuyenMaiMouseEntered

    private void btnKhuyenMaiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhuyenMaiMouseExited
        btnKhuyenMai.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnKhuyenMaiMouseExited

    private void btnNhanVienMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseEntered
        btnNhanVien.setBackground(Color.pink);
    }//GEN-LAST:event_btnNhanVienMouseEntered

    private void btnNhanVienMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanVienMouseExited
        btnNhanVien.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnNhanVienMouseExited

    private void btnNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhanVienActionPerformed
        NhanVienJpanel nvj = new NhanVienJpanel();
        nvj.setSize(panelCardGoc.getWidth(), panelCardGoc.getHeight());
        JNhanVien.add(nvj, BorderLayout.CENTER);
        cardLayout.show(panelCardGoc, "cardnhanvien");
    }//GEN-LAST:event_btnNhanVienActionPerformed

    private void txtTenKMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenKMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenKMActionPerformed

    private void btnKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhachHangActionPerformed
        KhachHangJpanel khj = new KhachHangJpanel();
        khj.setSize(panelCardGoc.getWidth(), panelCardGoc.getHeight());
        JKhachHang.add(khj, BorderLayout.CENTER);
        cardLayout.show(panelCardGoc, "cardkh");
    }//GEN-LAST:event_btnKhachHangActionPerformed

    private void btnKhachHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseExited
        btnKhachHang.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnKhachHangMouseExited

    private void btnKhachHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnKhachHangMouseEntered
        btnKhachHang.setBackground(Color.pink);
    }//GEN-LAST:event_btnKhachHangMouseEntered

    private void btnThongkeMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongkeMouseExited
        btnThongke.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnThongkeMouseExited

    private void btnThongkeMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThongkeMouseEntered
        btnThongke.setBackground(Color.pink);
    }//GEN-LAST:event_btnThongkeMouseEntered

    private void btnBanHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanHangActionPerformed
        BanHangJpanel bhj = new BanHangJpanel();
        bhj.setSize(panelCardGoc.getWidth(), panelCardGoc.getHeight());
        JBanHang.add(bhj, BorderLayout.CENTER);
        cardLayout.show(panelCardGoc, "cardBH");
    }//GEN-LAST:event_btnBanHangActionPerformed

    private void btnBanHangMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseExited
        btnBanHang.setBackground(new Color(255, 215, 0));
    }//GEN-LAST:event_btnBanHangMouseExited

    private void btnBanHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBanHangMouseEntered
        btnBanHang.setBackground(Color.pink);
    }//GEN-LAST:event_btnBanHangMouseEntered

    private void btnKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhuyenMaiActionPerformed
        KhuyenMaiJpanel kmj = new KhuyenMaiJpanel();
        kmj.setSize(panelCardGoc.getWidth(), panelCardGoc.getHeight());
        JKhuyenMai.add(kmj, BorderLayout.CENTER);
        cardLayout.show(panelCardGoc, "cardkhuyenmai");
    }//GEN-LAST:event_btnKhuyenMaiActionPerformed

    private void btnSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSanPhamActionPerformed
        SanPhamJpanel spj = new SanPhamJpanel();
        spj.setSize(panelCardGoc.getWidth(), panelCardGoc.getHeight());
        JSanPham.add(spj, BorderLayout.CENTER);
        cardLayout.show(panelCardGoc, "cardsanpham");
    }//GEN-LAST:event_btnSanPhamActionPerformed

    private void btnHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHoaDonActionPerformed
        HoaDonJpanel hdj = new HoaDonJpanel();
        hdj.setSize(panelCardGoc.getWidth(), panelCardGoc.getHeight());
        JHoaDon.add(hdj, BorderLayout.CENTER);
        cardLayout.show(panelCardGoc, "cardhoadon");
    }//GEN-LAST:event_btnHoaDonActionPerformed

    private void btnThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongkeActionPerformed
        ThongKeJpanel tkj = new ThongKeJpanel();
        tkj.setSize(panelCardGoc.getWidth(), panelCardGoc.getHeight());
        JThongKe.add(tkj, BorderLayout.CENTER);
        cardLayout.show(panelCardGoc, "cardthongke");
    }//GEN-LAST:event_btnThongkeActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
        new Login().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        new DoiMatKhauJpanel().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(QL_DT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QL_DT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QL_DT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QL_DT.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QL_DT().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JBanHang;
    private javax.swing.JPanel JHoaDon;
    private javax.swing.JPanel JKhachHang;
    private javax.swing.JPanel JKhuyenMai;
    private javax.swing.JPanel JNhanVien;
    private javax.swing.JPanel JSanPham;
    private javax.swing.JPanel JThongKe;
    private javax.swing.JPanel Tong;
    private javax.swing.JPanel Trai;
    private javax.swing.JPanel TrangChu;
    private javax.swing.JPanel Tren;
    private javax.swing.JLabel anhTrangchu;
    private javax.swing.JButton btnBanHang;
    private javax.swing.JButton btnHoaDon;
    private javax.swing.JButton btnKhachHang;
    private javax.swing.JButton btnKhuyenMai;
    private javax.swing.JButton btnNhanVien;
    private javax.swing.JButton btnSanPham;
    private javax.swing.JButton btnThongke;
    private javax.swing.JButton btnTrangChu;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel lblAnhDangNhap;
    private javax.swing.JLabel lblChucVU;
    private javax.swing.JLabel lblTen;
    private javax.swing.JPanel panelCardGoc;
    // End of variables declaration//GEN-END:variables
}
