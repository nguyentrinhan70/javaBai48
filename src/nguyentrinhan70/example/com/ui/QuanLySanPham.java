package nguyentrinhan70.example.com.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import nguyentrinhan70.example.com.model.DanhMuc;
import nguyentrinhan70.example.com.model.SanPham;
import nguyentrinhan70.example.com.service.DanhMucService;
import nguyentrinhan70.example.com.service.SanPhamServive;

public class QuanLySanPham extends JFrame {
	JList<DanhMuc> listDanhMuc;
	
	JButton btnThemMoiDanhMuc, btnChinhSuaDanhMuc, btnXoaDanhMuc;
	
	
	DefaultTableModel dtmSanPham;
	JTable tblSanPham;
	
	JTextField txtMaSp, txtTenSp, txtSoLuong, txtDonGia;
	
	JButton btnTaoMoiSp, btnLuuSp, btnXoaSp;
	
	JComboBox<DanhMuc> cboDanhMuc;
	DanhMuc dmSeleleted = null;
	
	ArrayList<SanPham> dsSp;
	public QuanLySanPham(String title) {
		super(title);
		addControls();
		addEvents();
		hienThiDanhMucLenList();
	}

	private void hienThiDanhMucLenList() {
		// TODO Auto-generated method stub
		DanhMucService dmService = new DanhMucService();
		Vector<DanhMuc> vec = dmService.docToanBoDanhMuc();
		listDanhMuc.setListData(vec);
		cboDanhMuc.removeAllItems();
		for(DanhMuc dm: vec){
			cboDanhMuc.addItem(dm);
		}
		
	}

	private void addEvents() {
		// TODO Auto-generated method stub
		listDanhMuc.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(listDanhMuc.getSelectedValue()==null) return;
				dmSeleleted = listDanhMuc.getSelectedValue();
				SanPhamServive spServive = new SanPhamServive();
				dsSp = 
						spServive.docSanPhamTheoDanhMuc(
								 listDanhMuc.getSelectedValue().getMaDM());
				dtmSanPham.setRowCount(0);
				
				for(SanPham sp: dsSp){
					Vector<Object> vec = new Vector<>();
					vec.add(sp.getMaSp());
					vec.add(sp.getTenSp());
					vec.add(sp.getSoLuong());
					vec.add(sp.getDonGia());
					dtmSanPham.addRow(vec);
				}
				
			}
		});
		tblSanPham.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int row = tblSanPham.getSelectedRow();
				if(row==-1) return;
				SanPham sp = dsSp.get(row);
				txtMaSp.setText(sp.getMaSp());
				txtTenSp.setText(sp.getTenSp());
				txtSoLuong.setText(sp.getSoLuong()+"");
				txtDonGia.setText(sp.getDonGia()+"");
				
				
			}
		});
		btnTaoMoiSp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtMaSp.setText("");
				txtTenSp.setText("");
				txtSoLuong.setText("");
				txtDonGia.setText("");
				txtMaSp.requestFocus();
			}
		});
		
		btnLuuSp.addActionListener(new ActionListener() {
			
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xuLyLuuSanPham();
			}
		});
	}

	protected void xuLyLuuSanPham() {
		// TODO Auto-generated method stub
		SanPham sp = new SanPham();
		sp.setMaDM(dmSeleleted.getMaDM());
		sp.setMaSp(txtMaSp.getText());
		sp.setTenSp(txtTenSp.getText());
		sp.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
		sp.setDonGia(Integer.parseInt(txtDonGia.getText()));
		SanPhamServive spService = new SanPhamServive();
		if(spService.luuSanPham(sp)>0){
			JOptionPane.showMessageDialog(null, "Lưu sản phẩm thành công");
		}
		else
		{
			
		}
	}

	private void addControls() {
		// TODO Auto-generated method stub
		Container con = getContentPane();
		con.setLayout(new BorderLayout());
		JPanel pnTitle = new JPanel();
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		Font font = new Font("aria", 1,20);
		JLabel lblTitle = new JLabel("QUẢN LÝ SẢN PHẨM");
		lblTitle.setFont(font);
		lblTitle.setForeground(Color.BLUE);
		pnTitle.add(lblTitle);
		con.add(pnTitle, BorderLayout.NORTH);
		
		JPanel pnLeft = new JPanel(); 
		pnLeft.setPreferredSize(new Dimension(0,0));
		JPanel pnRight = new JPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, pnLeft, pnRight);
		sp.setOneTouchExpandable(true);
		con.add(sp, BorderLayout.CENTER);
		
		pnLeft.setLayout(new BorderLayout());
		listDanhMuc = new JList<DanhMuc>();
		JScrollPane scListDanhMuc = new JScrollPane(listDanhMuc, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnLeft.add(scListDanhMuc, BorderLayout.CENTER);
		TitledBorder borderListDm = 
				new TitledBorder(BorderFactory.createLineBorder(Color.RED),
						"Danh mục sản phẩm");
		listDanhMuc.setBorder(borderListDm);
		
		btnThemMoiDanhMuc = new JButton("Thêm mới DM");
		btnChinhSuaDanhMuc = new JButton("Cập nhật DM");
		btnXoaDanhMuc = new JButton("Xóa DM");
		JPanel pnButton = new JPanel(); 
		pnButton.setLayout(new FlowLayout());
		pnButton.add(btnThemMoiDanhMuc);
		pnButton.add(btnChinhSuaDanhMuc);
		pnButton.add(btnXoaDanhMuc);
		pnLeft.add(pnButton, BorderLayout.SOUTH);
		
		pnRight.setLayout(new BorderLayout());
		JPanel pnTopOfRight = new JPanel();
		pnTopOfRight.setLayout(new BorderLayout());
		pnRight.add(pnTopOfRight, BorderLayout.CENTER);
		pnTopOfRight.setPreferredSize(new Dimension(0, 300));
		
		dtmSanPham = new DefaultTableModel();
		dtmSanPham.addColumn("Mã SP");
		dtmSanPham.addColumn("Tên SP");
		dtmSanPham.addColumn("Số lượng");
		dtmSanPham.addColumn("Đơn giá");
		tblSanPham = new JTable(dtmSanPham);
		JScrollPane scTable = new JScrollPane(tblSanPham,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pnTopOfRight.add(scTable, BorderLayout.CENTER);
		JPanel pnThongTinChiTiet = new JPanel();
		JLabel lblThongTinChiTiet = new JLabel("Thông tin chi tiết");
		pnThongTinChiTiet.add(lblThongTinChiTiet);
		pnTopOfRight.add(pnThongTinChiTiet, BorderLayout.NORTH);
				
		
		JPanel pnBottomOfRight = new JPanel(); 
		pnBottomOfRight.setLayout(new BoxLayout(pnBottomOfRight, BoxLayout.Y_AXIS));
		pnRight.add(pnBottomOfRight,BorderLayout.SOUTH);
		
		JPanel pnDanhMuc = new JPanel();
		pnDanhMuc.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblDanhMuc = new JLabel("Danh mục:");
		cboDanhMuc = new JComboBox<>();
		cboDanhMuc.setPreferredSize(new Dimension(350, 30));
		pnDanhMuc.add(lblDanhMuc);
		pnDanhMuc.add(cboDanhMuc);
		pnBottomOfRight.add(pnDanhMuc, BorderLayout.SOUTH);
		
		JPanel pnMaSp = new JPanel();
		pnMaSp.setLayout(new FlowLayout(FlowLayout.LEFT));
		JLabel lblMaSp = new JLabel("Mã sản phẩm");
		txtMaSp = new JTextField(30);
		pnMaSp.add(lblMaSp);
		pnMaSp.add(txtMaSp);
		pnBottomOfRight.add(pnMaSp);
		
		JPanel pnTenSp = new JPanel();
		pnTenSp.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		JLabel lblTenSp = new JLabel("Tên sản phẩm");
		txtTenSp = new JTextField(30);
		pnTenSp.add(lblTenSp);
		pnTenSp.add(txtTenSp);
		pnBottomOfRight.add(pnTenSp);
		
		JPanel pnSoLuong = new JPanel();
		pnSoLuong.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		JLabel lblSoLuong = new JLabel("Số lượng");
		txtSoLuong = new JTextField(30);
		pnSoLuong.add(lblSoLuong);
		pnSoLuong.add(txtSoLuong);
		pnBottomOfRight.add(pnSoLuong);
		
		JPanel pnDonGia = new JPanel();
		pnDonGia.setLayout(new FlowLayout(FlowLayout.LEFT)); 
		JLabel lblDonGia = new JLabel("Đơn giá:");
		txtDonGia = new JTextField(30);
		pnDonGia.add(lblDonGia);
		pnDonGia.add(txtDonGia);
		pnBottomOfRight.add(pnDonGia);
		
		lblSoLuong.setPreferredSize(lblTenSp.getPreferredSize());
		lblMaSp.setPreferredSize(lblTenSp.getPreferredSize());
		lblDonGia.setPreferredSize(lblTenSp.getPreferredSize());
		lblDanhMuc.setPreferredSize(lblTenSp.getPreferredSize());
		
		
		JPanel pnButtonSp = new JPanel(); 
		pnButtonSp.setLayout(new FlowLayout());
		btnTaoMoiSp = new JButton("Tạo mới sp");
		btnLuuSp = new JButton("Lưu sp:");
		btnXoaSp = new JButton("Xóa sp");
		pnButtonSp.add(btnTaoMoiSp);
		pnButtonSp.add(btnLuuSp);
		pnButtonSp.add(btnXoaSp);
		pnBottomOfRight.add(pnButtonSp);
		
	}
	
	public void showWindow(){
		this.setSize(800, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
