package uo.cpm.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.service.CastleManager;
import uo.cpm.util.FormatChecker;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.awt.event.ActionEvent;

public class SaveDiscount extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblSaveYourDiscount;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JButton btnSave;
	private JLabel lblWarning;
	private JLabel lblResult;
	private JButton btnCancel;
	private JButton btnBookNow;
	private CastleManager cm;
	private MainWindow mw;
	private HelpBroker hb;

	/**
	 * Create the dialog.
	 */
	public SaveDiscount(CastleManager cm, MainWindow mw) {
		this.mw = mw;
		setTitle("Crazy Castles: Save your discount");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 761, 494);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getLblSaveYourDiscount());
		contentPanel.add(getLblDNI());
		contentPanel.add(getTxtDNI());
		contentPanel.add(getBtnSave());
		contentPanel.add(getLblWarning());
		contentPanel.add(getLblResult());
		contentPanel.add(getBtnCancel());
		contentPanel.add(getBtnBookNow());
		setVisible(false);
		this.cm = cm;
		getRootPane().setDefaultButton(getBtnSave());
		loadHelp();
	}

	private JLabel getLblSaveYourDiscount() {
		if (lblSaveYourDiscount == null) {
			lblSaveYourDiscount = new JLabel("Save your discount!");
			lblSaveYourDiscount.setFont(new Font("Tahoma", Font.BOLD, 30));
			lblSaveYourDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblSaveYourDiscount.setBounds(0, 46, 747, 69);
		}
		return lblSaveYourDiscount;
	}

	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setDisplayedMnemonic('D');
			lblDNI.setLabelFor(getTxtDNI());
			lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
			lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 24));
			lblDNI.setBounds(79, 153, 78, 39);
		}
		return lblDNI;
	}

	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setFont(new Font("Tahoma", Font.PLAIN, 16));
			txtDNI.setBounds(167, 152, 345, 40);
			txtDNI.setColumns(10);
		}
		return txtDNI;
	}

	private JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Save!");
			btnSave.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (FormatChecker.blankCheck(getTxtDNI().getText())) {
						getLblResult().setText(
								"<html>The input DNI is not a valid DNI number. "
								+ "Please, ensure it is not blank.</html>");
						getLblResult().setForeground(Color.RED);
					}
					if (cm.saveDiscount(getTxtDNI().getText())) {
						getBtnSave().setEnabled(false);
						getLblResult().setText(
								"<html>Success! Your discount has " + "been saved associated with your DNI.</html>");
						getLblResult().setForeground(new Color(0, 100, 0));
					} else {
						getLblResult().setText("<html>Oops! Your DNI has a discount "
								+ "associated to it already, or you have already saved "
								+ "the obtained discount to another DNI.</html>");
						getLblResult().setForeground(Color.RED);
					}
					getLblResult().setVisible(true);
				}
			});
			btnSave.setBackground(Color.WHITE);
			btnSave.setFont(new Font("Tahoma", Font.PLAIN, 24));
			btnSave.setBounds(522, 153, 110, 39);
		}
		return btnSave;
	}

	private JLabel getLblWarning() {
		if (lblWarning == null) {
			lblWarning = new JLabel(
					"<html>Be careful! If you save your discount, you will not be able to store any other discount.</html>");
			lblWarning.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblWarning.setHorizontalAlignment(SwingConstants.CENTER);
			lblWarning.setBounds(94, 219, 544, 69);
		}
		return lblWarning;
	}

	private JLabel getLblResult() {
		if (lblResult == null) {
			lblResult = new JLabel("");
			lblResult.setVisible(false);
			lblResult.setBounds(94, 320, 544, 49);
		}
		return lblResult;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('C');
			btnCancel.setBackground(Color.WHITE);
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setBounds(597, 395, 136, 39);
		}
		return btnCancel;
	}

	JButton getBtnBookNow() {
		if (btnBookNow == null) {
			btnBookNow = new JButton("Book now!");
			btnBookNow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					((CardLayout) mw.getContentPane().getLayout()).show(mw.getContentPane(), "listCastles");
					mw.setListCastlesButton();
					dispose();
				}
			});
			btnBookNow.setMnemonic('B');
			btnBookNow.setBackground(Color.WHITE);
			btnBookNow.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnBookNow.setBounds(451, 395, 136, 39);
		}
		return btnBookNow;
	}
	
	private void loadHelp() {
		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/Help.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}
		catch (Exception e) {
			System.out.println("Help not found!");
			return;
		}
		hb = hs.createHelpBroker();
		hb.initPresentation(); // Preloads the help support
		hb.enableHelpKey(getRootPane(), "save discount", hs);
		hb.enableHelp(getRootPane(), "save discount", hs);
	}
}
