package fit.se.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.jms.JMSException;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fit.se.entities.BenhNhan;
import fit.se.publisher.MyTPublisher;

@SuppressWarnings("unused")
public class Frm_LeTan extends JFrame implements ActionListener{
	private JLabel lblMaBN, lblCMND, lblHoTen, lblDiaChi, lblBlank;
	private JTextField txtMaBN, txtCMND, txtHoTen;
	private JTextArea txtDiaChi;
	private JButton btnLuuThongTin, btnMaBN, btnCMND;
	private final Font font = new Font("Arial", Font.PLAIN, 18);
	private static final int WIDTH = 350;
	private static final int HEIGHT = 30;
	private static final int BLANK = 10;
	private JScrollPane scrollPane;
	private MyTPublisher publisher;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static void main(String[] args) {
		new Frm_LeTan();
	}
	public Frm_LeTan() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(700, 500);

		setLocationRelativeTo(null);
		init();
		
		setVisible(true);
	}
	private void init() {
		JLabel lblTitle = new JLabel("NHẬN BỆNH");
		lblTitle.setFont(new Font("Arail", Font.PLAIN, 30));
		JPanel panelNorth = new JPanel();
		panelNorth.add(lblTitle);

		add(panelNorth, BorderLayout.NORTH);
		
		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(BorderFactory.createTitledBorder("Thông tin bệnh nhân"));
		GridLayout gridLayout = new GridLayout(4, 3, 10, 10);
		
		Box box = Box.createVerticalBox();		
		Box box1 = Box.createHorizontalBox();
		box.add(Box.createVerticalStrut(HEIGHT+HEIGHT));
		box.add(box1);
		
		box1.add(lblMaBN = new JLabel("Mã số bệnh nhân :"));
		lblMaBN.setFont(font);
		box1.add(Box.createHorizontalStrut(BLANK));
		txtMaBN = new JTextField();
		txtMaBN.setEditable(false);
		
		
		box1.add(txtMaBN);
		txtMaBN.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		box1.add(Box.createHorizontalStrut(BLANK));
		box1.add(btnMaBN = new JButton());
		btnMaBN.setMaximumSize(new Dimension(HEIGHT, HEIGHT));
		
		Box box2 = Box.createHorizontalBox();
		box.add(Box.createVerticalStrut(BLANK));
		box.add(box2);
		
		box2.add(lblCMND = new JLabel("Số CMND :"));
		lblCMND.setFont(font);
		lblCMND.setPreferredSize(new Dimension(lblMaBN.getMaximumSize()));
		box2.add(Box.createHorizontalStrut(BLANK));
		txtCMND = new JTextField();
		box2.add(txtCMND);
		txtCMND.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		box2.add(Box.createHorizontalStrut(BLANK));
		box2.add(btnCMND = new JButton());
		btnCMND.setMaximumSize(new Dimension(HEIGHT, HEIGHT));
		
		Box box3 = Box.createHorizontalBox();
		box.add(Box.createVerticalStrut(BLANK));
		box.add(box3);
		
		box3.add(lblHoTen = new JLabel("Họ và tên:"));
		lblHoTen.setFont(font);
		lblHoTen.setPreferredSize(new Dimension(lblMaBN.getMaximumSize()));
		box3.add(Box.createHorizontalStrut(BLANK));
		txtHoTen = new JTextField();
		box3.add(txtHoTen);
		txtHoTen.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		box3.add(Box.createHorizontalStrut(BLANK+BLANK+BLANK+BLANK+4));
		
		Box box4 = Box.createHorizontalBox();

		box.add(Box.createVerticalStrut(BLANK));
		box.add(box4);
		box4.setAlignmentY(BoxLayout.Y_AXIS);
		
		Box box41 = Box.createVerticalBox();
		box41.add(lblDiaChi = new JLabel("Địa chỉ"));
		box4.add(box41);
		lblDiaChi.setFont(font);
		lblDiaChi.setPreferredSize(new Dimension(lblMaBN.getMaximumSize()));
		box4.add(Box.createHorizontalStrut(BLANK));
		txtDiaChi = new JTextArea(5, 30);
		scrollPane = new JScrollPane(txtDiaChi, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		txtDiaChi.setWrapStyleWord(true);
		txtDiaChi.setLineWrap(true);

		box4.add(scrollPane);
		scrollPane.setPreferredSize(new Dimension(WIDTH, 100));
		box4.add(Box.createHorizontalStrut(BLANK + BLANK + BLANK + BLANK + 4));
		
		
		panelCenter.add(box);
		add(panelCenter, BorderLayout.CENTER);
		JPanel panelSouth = new JPanel();
		btnLuuThongTin = new JButton("Lưu thông tin");
		panelSouth.add(btnLuuThongTin);
		btnLuuThongTin.setPreferredSize(new Dimension(200, 50));
		add(panelSouth, BorderLayout.SOUTH);
		
		btnLuuThongTin.addActionListener(this);
		try {
			publisher = new MyTPublisher();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void clearForm() {
		txtMaBN.setText("");
		txtCMND.setText("");
		txtHoTen.setText("");
		txtDiaChi.setText("");
		if(txtMaBN.getText().equalsIgnoreCase("")) {
			txtMaBN.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy-hh:mm:ss")));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if(object.equals(btnLuuThongTin)) {
			System.out.println("hello");
			String maBN = txtMaBN.getText();
			String cmnd = txtCMND.getText();
			String hoTen = txtHoTen.getText();
			String diaChi = txtDiaChi.getText();
			BenhNhan benhNhan = new BenhNhan(maBN, cmnd, hoTen, diaChi);
			System.out.println(benhNhan);
			try {
				publisher.sendMessage(benhNhan);
				clearForm();
			} catch (JMSException e1) {
				e1.printStackTrace();
			}
		}
	}
}
