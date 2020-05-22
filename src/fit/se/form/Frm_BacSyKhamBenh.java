package fit.se.form;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.naming.NamingException;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.Gson;

import fit.se.dao.BenhNhanDAO;
import fit.se.entities.BacSy;
import fit.se.entities.BenhNhan;
import fit.se.publisher.MyTSubscriber;
import javafx.scene.text.Text;

@SuppressWarnings("all")
public class Frm_BacSyKhamBenh extends JFrame implements MessageListener, ActionListener{ 
	private final int WIDTH = 300;
	private final int HEIGHT = 30;
	private final int BLANK = 10;
	private final Font FONT = new Font("Arail", Font.PLAIN, 18);
	private JList<BenhNhan> dsBNChoKham;
	private JLabel lblMSBN, lblSoCMND, lblHoTen, lblDiaChi;
	private JTextField txtMSBN, txtCMND, txtHoTen;
	private JTextArea txtDiaChi, txtNoiDungKham;
	private JButton btnCapNhat, btnGoiKham;
	private BacSy bacSy;
	private List<BenhNhan> listBenhNhans;
	private BenhNhanDAO benhNhanDAO;
	public static void main(String[] args) {
		try {
			new Frm_BacSyKhamBenh();
		} catch (NamingException | JMSException e) {
			e.printStackTrace();
		}
	}
	public Frm_BacSyKhamBenh() throws NamingException, JMSException {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		init();
		setVisible(true);
	}
	
	public void setValue(BacSy bacSy) {
		this.bacSy = bacSy;
	}
	
	private void init() throws NamingException, JMSException {
		Box boxCenter = Box.createHorizontalBox();
		Box boxLeft = Box.createVerticalBox();
		Box boxRight = Box.createVerticalBox();
		
		boxCenter.add(boxLeft);
		boxCenter.add(boxRight);
		
		boxLeft.setBorder(BorderFactory.createTitledBorder("Danh sách bệnh nhân chờ khám"));
		boxRight.setBorder(BorderFactory.createTitledBorder("Thông tin bệnh nhân được chọn"));
		boxLeft.setMaximumSize(new Dimension(300, 500));
		boxRight.setMaximumSize(new Dimension(500, 500));
		
		dsBNChoKham = new JList<BenhNhan>();
		dsBNChoKham.setPreferredSize(new Dimension(200, 300));
		boxLeft.add(dsBNChoKham);
		Box boxL1 = Box.createHorizontalBox();
		boxLeft.add(boxL1);
		boxL1.add(dsBNChoKham);
		dsBNChoKham.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
		dsBNChoKham.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		boxLeft.add(Box.createVerticalStrut(2));
		
		Box boxL2 = Box.createHorizontalBox();
		boxLeft.add(boxL2);
		boxL2.add(btnGoiKham = new JButton("Gọi khám..."));
		btnGoiKham.setMaximumSize(new Dimension(boxLeft.getMaximumSize().hashCode(), 30));
			
		Box box1 = Box.createHorizontalBox();
		boxRight.add(box1);
		box1.add(lblMSBN = new JLabel("Mã số bệnh nhân : ")); 
		lblMSBN.setFont(FONT);
		box1.add(txtMSBN = new JTextField());
		txtMSBN.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		boxRight.add(Box.createVerticalStrut(BLANK));
		
		Box box2 = Box.createHorizontalBox();
		boxRight.add(box2);
		box2.add(lblSoCMND = new JLabel("Số CMND : "));
		lblSoCMND.setFont(FONT);
		lblSoCMND.setMaximumSize(new Dimension(lblMSBN.getMaximumSize()));
		box2.add(txtCMND = new JTextField());
		txtCMND.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		
		boxRight.add(Box.createVerticalStrut(BLANK));
		
		Box box3 = Box.createHorizontalBox();
		boxRight.add(box3);
		box3.add(lblHoTen = new JLabel("Họ tên : "));
		lblHoTen.setFont(FONT);
		lblHoTen.setMaximumSize(new Dimension(lblMSBN.getMaximumSize()));
		box3.add(txtHoTen = new JTextField());
		txtHoTen.setMaximumSize(new Dimension(WIDTH, HEIGHT));

		boxRight.add(Box.createVerticalStrut(BLANK));
		
		Box box4 = Box.createHorizontalBox();
		boxRight.add(box4);
		box4.add(lblDiaChi = new JLabel("Địa chỉ : "));
		lblDiaChi.setFont(FONT);
		lblDiaChi.setMaximumSize(new Dimension(lblMSBN.getMaximumSize()));
		JScrollPane scrollPane = new JScrollPane(txtDiaChi = new JTextArea(4, 20), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		box4.add(scrollPane);
		scrollPane.setMaximumSize(new Dimension(WIDTH, 4*HEIGHT));
		txtDiaChi.setWrapStyleWord(true);
		txtDiaChi.setLineWrap(true);
		
		boxRight.add(Box.createVerticalStrut(BLANK));
		
		Box box5 = Box.createHorizontalBox();
		boxRight.add(box5);
		JScrollPane scrollPane2 = new JScrollPane(txtNoiDungKham = new JTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		txtNoiDungKham.setWrapStyleWord(true);
		txtNoiDungKham.setLineWrap(true);
		box5.add(scrollPane2);
		box5.setBorder(BorderFactory.createTitledBorder("Nội dung khám"));
		scrollPane2.setMaximumSize(new Dimension(boxRight.getMaximumSize().hashCode(), 5*HEIGHT));
		
		boxRight.add(Box.createVerticalStrut(BLANK));
		
		Box box6 = Box.createHorizontalBox();
		boxRight.add(box6);
		box6.add(btnCapNhat = new JButton("Cập nhật thông tin khám bệnh"));
		
		add(boxCenter, BorderLayout.CENTER);
		
		btnGoiKham.addActionListener(this);
		
		MyTSubscriber myTSubscriber = new MyTSubscriber();
		
		benhNhanDAO = new BenhNhanDAO();
		
		listBenhNhans = new ArrayList<BenhNhan>();
		
		myTSubscriber.getQueueReceiver().setMessageListener(this);
		
	}
	
	private void updateData() {
		BenhNhan[] arrays = new BenhNhan[listBenhNhans.size()];
		listBenhNhans.toArray(arrays);
		dsBNChoKham.setListData(arrays);
	}
	
	@Override
	public void onMessage(Message message) {
		Gson gson = new Gson();
		if(message instanceof TextMessage) {
			try {
				TextMessage textMessage = (TextMessage) message;
				String json = textMessage.getText();
				System.out.println(json);
				BenhNhan benhNhan = gson.fromJson(json, BenhNhan.class);
				System.out.println(benhNhan);
				listBenhNhans.add(benhNhan);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		updateData();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if(object.equals(btnGoiKham)) {
			System.out.println("Goi kham");
			updateData();
		}
	}
	
}
