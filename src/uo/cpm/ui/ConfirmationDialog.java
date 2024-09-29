package uo.cpm.ui;

import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import uo.cpm.service.CastleManager;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConfirmationDialog extends JPanel {

	private static final long serialVersionUID = 1L;
	private CastleManager cm;
	private MainWindow mw;
	private ReservationForm rf;
	private JLabel lblProcessed;
	private JLabel lblOk;
	private JLabel lblReservationTotal;
	private JTextField txtTotal;
	private JLabel lblThankYou;
	private JLabel lblProblem;
	private JButton btnMainMenu;

	/**
	 * Create the panel.
	 */
	public ConfirmationDialog(CastleManager cm, MainWindow mw, ReservationForm rf) {
		this.rf = rf;
		this.mw = mw;
		this.cm = cm;
		setLayout(null);
		setSize(new Dimension(684, 511));
		add(getLblProcessed());
		add(getLblOk());
		add(getLblReservationTotal());
		add(getTxtTotal());
		add(getLblThankYou());
		add(getLblProblem());
		add(getBtnMainMenu());
		setData();
	}

	private JLabel getLblProcessed() {
		if (lblProcessed == null) {
			lblProcessed = new JLabel("Your order has been processed!");
			lblProcessed.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 24));
			lblProcessed.setHorizontalAlignment(SwingConstants.CENTER);
			lblProcessed.setBounds(213, 70, 393, 58);
		}
		return lblProcessed;
	}
	private JLabel getLblOk() {
		if (lblOk == null) {
			lblOk = new JLabel("");
			lblOk.setBounds(31, 44, 159, 117);
		}
		return lblOk;
	}
	private JLabel getLblReservationTotal() {
		if (lblReservationTotal == null) {
			lblReservationTotal = new JLabel("Reservation total:");
			lblReservationTotal.setLabelFor(getTxtTotal());
			lblReservationTotal.setHorizontalAlignment(SwingConstants.CENTER);
			lblReservationTotal.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblReservationTotal.setBounds(115, 183, 232, 58);
		}
		return lblReservationTotal;
	}
	private JTextField getTxtTotal() {
		if (txtTotal == null) {
			txtTotal = new JTextField();
			txtTotal.setEditable(false);
			txtTotal.setBounds(357, 193, 232, 37);
			txtTotal.setColumns(10);
		}
		return txtTotal;
	}
	private JLabel getLblThankYou() {
		if (lblThankYou == null) {
			lblThankYou = new JLabel("<html>Thank you!<br>Have a great stay!</html>");
			lblThankYou.setHorizontalAlignment(SwingConstants.CENTER);
			lblThankYou.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblThankYou.setBounds(196, 280, 393, 93);
		}
		return lblThankYou;
	}
	private JLabel getLblProblem() {
		if (lblProblem == null) {
			lblProblem = new JLabel("Any problem? Please, contact us on crazycastles@castle.com");
			lblProblem.setHorizontalAlignment(SwingConstants.CENTER);
			lblProblem.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblProblem.setBounds(10, 447, 350, 54);
		}
		return lblProblem;
	}
	JButton getBtnMainMenu() {
		if (btnMainMenu == null) {
			btnMainMenu = new JButton("Go back to main menu");
			btnMainMenu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mw.resetMenuBar();
					((CardLayout) mw.getContentPane().getLayout()).show(mw.getContentPane(), "mainMenu");
					cm.reset();
					mw.getRootPane().setDefaultButton(null);
					rf.dispose();
				}
			});
			btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnMainMenu.setBounds(479, 447, 177, 37);
		}
		return btnMainMenu;
	}
	
	private void setData() {
		setOkImage();
		getTxtTotal().setText(cm.getBooking().getPrice() + "€");
	}

	private void setOkImage() {
		ImageIcon image = new ImageIcon(getClass().getResource("/img/ok.png"));
		float delta = ((getLblOk().getWidth() * 100) / image.getIconWidth()) / 100f;
		if (image.getIconHeight() > getLblOk().getHeight())
			delta = ((getLblOk().getHeight() * 100) / image.getIconHeight()) / 100f;
		int width = (int) (image.getIconWidth() * delta);
		int height = (int) (image.getIconHeight() * delta);
		getLblOk().setIcon(new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
	}
}
