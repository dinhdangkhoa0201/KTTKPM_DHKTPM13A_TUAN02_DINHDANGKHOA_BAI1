package fit.se.form;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import fit.se.dao.BacSyDAO;
import fit.se.entities.BacSy;

@SuppressWarnings("serial")
public class Frm_Login extends JFrame implements ActionListener{
	private static final int WIDTH = 250;
	private static final int HEIGHT = 20;
	private static final int BLANK = 10;
	private static final Font FONT = new Font("Arial", Font.PLAIN, 18);
	private BacSyDAO bacSyDAO = new BacSyDAO();
	
	private JLabel lblTitle, lblMaBS, lblMK;
	private JTextField txtMaBS;
	private JPasswordField pwdMK;
	private JButton btnDangNhap, btnThoat;
	
	public static void main(String[] args) {
		new Frm_Login();
	}
	
	public Frm_Login() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(500, 300);
		setLocationRelativeTo(null);
		
		init();
		
		setVisible(true);
	}
	
	private void init() {
		JPanel panelNorth = new JPanel();
		add(panelNorth, BorderLayout.NORTH);
		panelNorth.add(lblTitle = new JLabel("Đăng Nhập"));
		lblTitle.setFont(new Font("Arail", Font.PLAIN, 22));
		
		JPanel panelCenter = new JPanel();
		Box box = Box.createVerticalBox();
		panelCenter.add(box);
		add(panelCenter);

		Box box1 = Box.createHorizontalBox();
		box.add(Box.createVerticalStrut(50));
		box.add(box1);
		
		box1.add(lblMaBS = new JLabel("Mã Bác Sĩ : "));
		lblMaBS.setFont(FONT);
		box1.add(Box.createHorizontalStrut(BLANK));
		box1.add(txtMaBS = new JTextField());
		txtMaBS.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		Box box2 = Box.createHorizontalBox();
		box.add(Box.createVerticalStrut(BLANK + BLANK));
		box.add(box2);
		box2.add(lblMK = new JLabel("Mật khẩu : "));
		lblMK.setFont(FONT);
		lblMK.setPreferredSize(new Dimension(lblMaBS.getMaximumSize()));
		box2.add(Box.createHorizontalStrut(BLANK));
		box2.add(pwdMK = new JPasswordField());
		pwdMK.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		JPanel panelSouth = new JPanel();
		panelSouth.add(btnDangNhap = new JButton("Đăng Nhập"));
		panelSouth.add(btnThoat = new JButton("Thoát"));
		btnThoat.setPreferredSize(new Dimension(btnDangNhap.getMaximumSize()));
		add(panelSouth, BorderLayout.SOUTH);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src.equals(btnDangNhap)) {
			if(!txtMaBS.getText().equalsIgnoreCase("") && !String.valueOf(pwdMK.getPassword()).equalsIgnoreCase("")) {
				BacSy bacSy = bacSyDAO.getBacSy(txtMaBS.getText());
				if(bacSy != null && bacSy.getPwd().equalsIgnoreCase(String.copyValueOf(pwdMK.getPassword()))) {
					Frm_BacSyKhamBenh frm_BacSyKhamBenh;
					try {
						frm_BacSyKhamBenh = new Frm_BacSyKhamBenh();
						frm_BacSyKhamBenh.setValue(bacSy);
					} catch (NamingException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JMSException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			}
		}
	}
	
}
