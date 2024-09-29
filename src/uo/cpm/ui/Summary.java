package uo.cpm.ui;

import javax.swing.JPanel;

import uo.cpm.service.CastleManager;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Summary extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnCastleInfo;
	private JLabel lblTitle;
	private JLabel lblPicture;
	private JLabel lblPrice;
	private JLabel lblEnchantments;
	private JScrollPane scEnchantments;
	private JLabel lblDiscount;
	private JPanel pnTotal;
	private JLabel lblSubtotal;
	private JLabel lblTotal;
	private JTextField txtSubtotal;
	private JTextField txtTotal;
	private JLabel lblAfterdiscount;
	private JTextPane tpWarning;
	private JTextPane tpEnchantments;
	private CastleManager cm;
	private JPanel pnData;
	private JLabel lblArrival;
	private JLabel lblNights;
	private JLabel lblRooms;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblId;
	private JTextField txtDNI;
	private JTextField txtEmail;
	private JLabel lblEmail;
	private JLabel lblComments;
	private JTextArea taComments;
	private JScrollPane scComments;
	private JTextField txtArrival;
	private JTextField txtNights;
	private JTextField txtRooms;
	private JButton btnGoBack;
	private JButton btnConfirm;

	/**
	 * Create the panel.
	 */
	Summary(CastleManager cm) {
		this.cm = cm;
		setLayout(null);
		setSize(new Dimension(684, 511));
		add(getPnCastleInfo());
		add(getPnTotal());
		add(getPnData());
		setInfo();
	}
	
	private JPanel getPnCastleInfo() {
		if (pnCastleInfo == null) {
			pnCastleInfo = new JPanel();
			pnCastleInfo.setBounds(0, 0, 270, 388);
			pnCastleInfo.setLayout(null);
			pnCastleInfo.setBorder(null);
			pnCastleInfo.add(getLblTitle());
			pnCastleInfo.add(getLblPicture());
			pnCastleInfo.add(getLblPrice());
			pnCastleInfo.add(getLblEnchantments());
			pnCastleInfo.add(getScEnchantments());
			pnCastleInfo.add(getLblDiscount());
		}
		return pnCastleInfo;
	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel((String) null);
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setBounds(10, 42, 250, 32);
		}
		return lblTitle;
	}
	private JLabel getLblPicture() {
		if (lblPicture == null) {
			lblPicture = new JLabel("");
			lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
			lblPicture.setBounds(10, 84, 250, 142);
		}
		return lblPicture;
	}
	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("0€/night");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPrice.setBounds(10, 236, 104, 32);
		}
		return lblPrice;
	}
	private JLabel getLblEnchantments() {
		if (lblEnchantments == null) {
			lblEnchantments = new JLabel("Enchantments:");
			lblEnchantments.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblEnchantments.setBounds(10, 286, 127, 24);
		}
		return lblEnchantments;
	}
	private JScrollPane getScEnchantments() {
		if (scEnchantments == null) {
			scEnchantments = new JScrollPane();
			scEnchantments.setBorder(null);
			scEnchantments.setBounds(10, 320, 250, 58);
			scEnchantments.setViewportView(getTpEnchantments());
		}
		return scEnchantments;
	}
	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("");
			lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiscount.setForeground(Color.RED);
			lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDiscount.setBounds(124, 236, 136, 32);
		}
		return lblDiscount;
	}
	private JPanel getPnTotal() {
		if (pnTotal == null) {
			pnTotal = new JPanel();
			pnTotal.setBounds(0, 388, 270, 126);
			pnTotal.setLayout(null);
			pnTotal.setBorder(null);
			pnTotal.add(getLblSubtotal());
			pnTotal.add(getLblTotal());
			pnTotal.add(getTxtSubtotal());
			pnTotal.add(getTxtTotal());
			pnTotal.add(getLblAfterdiscount());
		}
		return pnTotal;
	}
	private JLabel getLblSubtotal() {
		if (lblSubtotal == null) {
			lblSubtotal = new JLabel("Subtotal:");
			lblSubtotal.setLabelFor(getTxtSubtotal());
			lblSubtotal.setHorizontalAlignment(SwingConstants.TRAILING);
			lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblSubtotal.setBounds(10, 10, 96, 35);
		}
		return lblSubtotal;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total:");
			lblTotal.setLabelFor(getTxtTotal());
			lblTotal.setHorizontalAlignment(SwingConstants.TRAILING);
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTotal.setBounds(10, 52, 96, 34);
		}
		return lblTotal;
	}
	private JTextField getTxtSubtotal() {
		if (txtSubtotal == null) {
			txtSubtotal = new JTextField();
			txtSubtotal.setText("0.0€");
			txtSubtotal.setEditable(false);
			txtSubtotal.setColumns(10);
			txtSubtotal.setBounds(116, 10, 144, 35);
		}
		return txtSubtotal;
	}
	private JTextField getTxtTotal() {
		if (txtTotal == null) {
			txtTotal = new JTextField();
			txtTotal.setText("0.0€");
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
			txtTotal.setBounds(116, 55, 144, 34);
		}
		return txtTotal;
	}
	private JLabel getLblAfterdiscount() {
		if (lblAfterdiscount == null) {
			lblAfterdiscount = new JLabel("after discount");
			lblAfterdiscount.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAfterdiscount.setBounds(0, 73, 102, 22);
		}
		return lblAfterdiscount;
	}
	private JTextPane getTpWarning() {
		if (tpWarning == null) {
			tpWarning = new JTextPane();
			tpWarning.setBounds(10, 10, 394, 51);
			tpWarning.setFont(new Font("Tahoma", Font.PLAIN, 12));
			tpWarning.setEditable(false);
			tpWarning.setBackground(new Color(214, 217, 223));
			tpWarning.setText("There is only one step left in order to formalize your reservation. "
					+ "Please, check your data. Once the confirm button is pressed, your "
					+ "reservation will be formalized.");
		}
		return tpWarning;
	}
	private JTextPane getTpEnchantments() {
		if (tpEnchantments == null) {
			tpEnchantments = new JTextPane();
			tpEnchantments.setEditable(false);
			tpEnchantments.setBackground(new Color(214, 217, 223));
		}
		return tpEnchantments;
	}
	private JPanel getPnData() {
		if (pnData == null) {
			pnData = new JPanel();
			pnData.setBounds(269, 0, 415, 511);
			pnData.setLayout(null);
			pnData.add(getTpWarning());
			pnData.add(getLblArrival());
			pnData.add(getLblNights());
			pnData.add(getLblRooms());
			pnData.add(getLblName());
			pnData.add(getTxtName());
			pnData.add(getLblId());
			pnData.add(getTxtDNI());
			pnData.add(getTxtEmail());
			pnData.add(getLblEmail());
			pnData.add(getLblComments());
			pnData.add(getScComments());
			pnData.add(getTxtArrival());
			pnData.add(getTxtNights());
			pnData.add(getTxtRooms());
			pnData.add(getBtnGoBack());
			pnData.add(getBtnConfirm());
		}
		return pnData;
	}
	private JLabel getLblArrival() {
		if (lblArrival == null) {
			lblArrival = new JLabel("Arrival:");
			lblArrival.setLabelFor(getTxtArrival());
			lblArrival.setHorizontalAlignment(SwingConstants.TRAILING);
			lblArrival.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblArrival.setBounds(10, 72, 87, 26);
		}
		return lblArrival;
	}
	private JLabel getLblNights() {
		if (lblNights == null) {
			lblNights = new JLabel("Nights:");
			lblNights.setLabelFor(getTxtNights());
			lblNights.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNights.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNights.setBounds(10, 108, 87, 26);
		}
		return lblNights;
	}
	private JLabel getLblRooms() {
		if (lblRooms == null) {
			lblRooms = new JLabel("Rooms:");
			lblRooms.setLabelFor(getTxtRooms());
			lblRooms.setHorizontalAlignment(SwingConstants.TRAILING);
			lblRooms.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblRooms.setBounds(16, 144, 81, 26);
		}
		return lblRooms;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Full name:");
			lblName.setLabelFor(getTxtName());
			lblName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblName.setBounds(10, 180, 87, 26);
		}
		return lblName;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setEditable(false);
			txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtName.setColumns(10);
			txtName.setBounds(107, 180, 290, 26);
		}
		return txtName;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID:");
			lblId.setLabelFor(getTxtDNI());
			lblId.setHorizontalAlignment(SwingConstants.TRAILING);
			lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblId.setBounds(10, 216, 87, 26);
		}
		return lblId;
	}
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setEditable(false);
			txtDNI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtDNI.setColumns(10);
			txtDNI.setBounds(107, 216, 290, 26);
		}
		return txtDNI;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setEditable(false);
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtEmail.setColumns(10);
			txtEmail.setBounds(107, 252, 290, 26);
		}
		return txtEmail;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setLabelFor(getTxtEmail());
			lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEmail.setBounds(10, 252, 87, 26);
		}
		return lblEmail;
	}
	private JLabel getLblComments() {
		if (lblComments == null) {
			lblComments = new JLabel("Comments:");
			lblComments.setLabelFor(getTaComments());
			lblComments.setHorizontalAlignment(SwingConstants.TRAILING);
			lblComments.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblComments.setBounds(10, 288, 87, 26);
		}
		return lblComments;
	}
	private JTextArea getTaComments() {
		if (taComments == null) {
			taComments = new JTextArea();
			taComments.setEditable(false);
			taComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return taComments;
	}
	private JScrollPane getScComments() {
		if (scComments == null) {
			scComments = new JScrollPane();
			scComments.setBounds(107, 290, 288, 61);
			scComments.setViewportView(getTaComments());
		}
		return scComments;
	}
	private JTextField getTxtArrival() {
		if (txtArrival == null) {
			txtArrival = new JTextField();
			txtArrival.setEditable(false);
			txtArrival.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtArrival.setColumns(10);
			txtArrival.setBounds(107, 71, 290, 26);
		}
		return txtArrival;
	}
	private JTextField getTxtNights() {
		if (txtNights == null) {
			txtNights = new JTextField();
			txtNights.setEditable(false);
			txtNights.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNights.setColumns(10);
			txtNights.setBounds(107, 108, 290, 26);
		}
		return txtNights;
	}
	private JTextField getTxtRooms() {
		if (txtRooms == null) {
			txtRooms = new JTextField();
			txtRooms.setEditable(false);
			txtRooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtRooms.setColumns(10);
			txtRooms.setBounds(107, 144, 290, 26);
		}
		return txtRooms;
	}
	JButton getBtnGoBack() {
		if (btnGoBack == null) {
			btnGoBack = new JButton("Go back");
			btnGoBack.setMnemonic('G');
			btnGoBack.setBounds(299, 433, 98, 42);
		}
		return btnGoBack;
	}
	JButton getBtnConfirm() {
		if (btnConfirm == null) {
			btnConfirm = new JButton("Continue");
			btnConfirm.setBounds(187, 433, 98, 42);
		}
		return btnConfirm;
	}
	
	private void setInfo() {
		getLblTitle().setText(cm.getSelectedCastle().getName());
		setPictureImage("/img/" + cm.getSelectedCastle().getCode() + ".png");
		getLblDiscount().setText(cm.getReservationDiscountString());
		getLblPrice().setText(cm.getSelectedCastle().getPricePerNight() + "€/night");
		getTxtSubtotal().setText(cm.calculateSubtotal(cm.getBooking().getDays(), cm.getBooking().getNumRooms()) + "");
		getTxtTotal().setText(cm.getBooking().getPrice() + "");
		getTpEnchantments().setText(cm.getSelectedCastle().getEnchantmentsString());
		getTxtArrival().setText(cm.getBooking().getDate());
		getTxtNights().setText(cm.getBooking().getDays() + "");
		getTxtRooms().setText(cm.getBooking().getNumRooms() + "");
		getTxtName().setText(cm.getBooking().getNameAndSurname());
		getTxtDNI().setText(cm.getBooking().getID());
		getTxtEmail().setText(cm.getBooking().getEmail());
		getTaComments().setText(cm.getBooking().getComment());
	}
	
	private void setPictureImage(String imagePath) {
		ImageIcon image = new ImageIcon(getClass().getResource(imagePath));
		float delta = ((getLblPicture().getWidth() * 100) / image.getIconWidth()) / 100f;
		if (image.getIconHeight() > getLblPicture().getHeight())
			delta = ((getLblPicture().getHeight() * 100) / image.getIconHeight()) / 100f;
		int width = (int) (image.getIconWidth() * delta);
		int height = (int) (image.getIconHeight() * delta);
		getLblPicture().setIcon(new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
}
