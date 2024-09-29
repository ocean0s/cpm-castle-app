package uo.cpm.ui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.Color;

import javax.swing.border.MatteBorder;

import uo.cpm.model.Castle;
import uo.cpm.service.CastleManager;
import uo.cpm.util.FormatChecker;

import java.awt.Toolkit;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.awt.event.ItemEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class ReservationForm extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel pnCastleInfo;
	private JPanel pnTotal;
	private JLabel lblTitle;
	private JLabel lblPicture;
	private JLabel lblPrice;
	private JLabel lblEnchantments;
	private JScrollPane scrollPane;
	private JTextPane tpEnchantments;
	private JLabel lblDiscount;
	private JLabel lblSubtotal;
	private JLabel lblTotal;
	private JTextField txtSubtotal;
	private JTextField txtTotal;
	private JLabel lblAfterdiscount;
	private JPanel pnForm;
	private JLabel lblArrival;
	private JLabel lblNights;
	private JLabel lblPax;
	private JLabel lblName;
	private JLabel lblSurname;
	private JLabel lblId;
	private JLabel lblEmail;
	private JLabel lblComments;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtID;
	private JTextField txtEmail;
	private JScrollPane scComments;
	private JTextArea taComments;
	private JComboBox<Integer> cbArrivalDay;
	private JComboBox<Integer> cbArrivalMonth;
	private JComboBox<Integer> cbArrivalYear;
	private JSpinner spPax;
	private JLabel lblRooms;
	private JSpinner spRooms;
	private JButton btnDiscount;
	private JButton btnCancel;
	private JButton btnContinue;
	private CastleManager cm;
	private DateItemListener dil;
	private JSpinner spNights;
	private JLabel lblRoomsNote;
	private PriceChangeListener pcl = new PriceChangeListener();
	private JPanel pnReservationForm;
	private Summary summary;
	private ConfirmationDialog cd;
	private MainWindow mw;
	private ConfirmCloseWindowAdapter ccwa = new ConfirmCloseWindowAdapter();
	private HelpBroker hb;

	/**
	 * Create the dialog.
	 */
	public ReservationForm(CastleManager cm, MainWindow mw) {
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.mw = mw;
		this.cm = cm;
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservationForm.class.getResource("/img/ESC001.png")));
		setTitle("Crazy Castles: Reservation Form");
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 700, 550);
		getContentPane().setLayout(new CardLayout(0, 0));
		getContentPane().add(getPnReservationForm(), "reservationForm");
		addWindowListener(ccwa);
		setCastleInfo();
		setComboModels();
		setPrices();
		setRoomSpinner();
		getRootPane().setDefaultButton(getBtnContinue());
		loadHelp();
	}

	private ConfirmationDialog getPnConfirm() {
		if (cd == null) {
			cd = new ConfirmationDialog(cm, mw, this);
			getRootPane().setDefaultButton(summary.getBtnConfirm());
		}
		return cd;
	}

	private Summary getSummary() {
		if (summary == null)
			summary = new Summary(cm);
		return summary;
	}

	private JPanel getPnCastleInfo() {
		if (pnCastleInfo == null) {
			pnCastleInfo = new JPanel();
			pnCastleInfo.setBorder(new MatteBorder(0, 0, 1, 2, (Color) new Color(0, 0, 0)));
			pnCastleInfo.setBounds(0, 0, 270, 388);
			pnCastleInfo.setLayout(null);
			pnCastleInfo.add(getLblTitle());
			pnCastleInfo.add(getLblPicture());
			pnCastleInfo.add(getLblPrice());
			pnCastleInfo.add(getLblEnchantments());
			pnCastleInfo.add(getScrollPane());
			pnCastleInfo.add(getLblDiscount());
		}
		return pnCastleInfo;
	}
	private JPanel getPnTotal() {
		if (pnTotal == null) {
			pnTotal = new JPanel();
			pnTotal.setBorder(new MatteBorder(2, 0, 0, 2, (Color) new Color(0, 0, 0)));
			pnTotal.setBounds(0, 387, 270, 126);
			pnTotal.setLayout(null);
			pnTotal.add(getLblSubtotal());
			pnTotal.add(getLblTotal());
			pnTotal.add(getTxtSubtotal());
			pnTotal.add(getTxtTotal());
			pnTotal.add(getLblAfterdiscount());
		}
		return pnTotal;
	}
	private JLabel getLblTitle() {
		if (lblTitle == null) {
			lblTitle = new JLabel("");
			lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
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
			lblPrice = new JLabel("");
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
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBorder(null);
			scrollPane.setBounds(10, 320, 250, 58);
			scrollPane.setViewportView(getTpEnchantments());
		}
		return scrollPane;
	}
	private JTextPane getTpEnchantments() {
		if (tpEnchantments == null) {
			tpEnchantments = new JTextPane();
			tpEnchantments.setEditable(false);
			tpEnchantments.setBackground(new Color(214, 217, 223));
		}
		return tpEnchantments;
	}
	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("");
			lblDiscount.setForeground(Color.RED);
			lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiscount.setBounds(124, 236, 136, 32);
		}
		return lblDiscount;
	}
	private JLabel getLblSubtotal() {
		if (lblSubtotal == null) {
			lblSubtotal = new JLabel("Subtotal:");
			lblSubtotal.setLabelFor(getTxtSubtotal());
			lblSubtotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblSubtotal.setHorizontalAlignment(SwingConstants.TRAILING);
			lblSubtotal.setBounds(10, 10, 96, 29);
		}
		return lblSubtotal;
	}
	private JLabel getLblTotal() {
		if (lblTotal == null) {
			lblTotal = new JLabel("Total:");
			lblTotal.setLabelFor(getTxtTotal());
			lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTotal.setHorizontalAlignment(SwingConstants.TRAILING);
			lblTotal.setBounds(10, 49, 96, 29);
		}
		return lblTotal;
	}
	private JTextField getTxtSubtotal() {
		if (txtSubtotal == null) {
			txtSubtotal = new JTextField();
			txtSubtotal.setEditable(false);
			txtSubtotal.setBounds(116, 10, 144, 29);
			txtSubtotal.setColumns(10);
		}
		return txtSubtotal;
	}
	private JTextField getTxtTotal() {
		if (txtTotal == null) {
			txtTotal = new JTextField();
			txtTotal.setEditable(false);
			txtTotal.setColumns(10);
			txtTotal.setBounds(116, 49, 144, 29);
		}
		return txtTotal;
	}
	private JLabel getLblAfterdiscount() {
		if (lblAfterdiscount == null) {
			lblAfterdiscount = new JLabel("after discount");
			lblAfterdiscount.setHorizontalAlignment(SwingConstants.TRAILING);
			lblAfterdiscount.setBounds(0, 72, 102, 13);
		}
		return lblAfterdiscount;
	}
	private JPanel getPnForm() {
		if (pnForm == null) {
			pnForm = new JPanel();
			pnForm.setBounds(269, 0, 417, 513);
			pnForm.setLayout(null);
			pnForm.add(getLblArrival());
			pnForm.add(getLblNights());
			pnForm.add(getLblPax());
			pnForm.add(getLblName());
			pnForm.add(getLblSurname());
			pnForm.add(getLblId());
			pnForm.add(getLblEmail());
			pnForm.add(getLblComments());
			pnForm.add(getTxtName());
			pnForm.add(getTxtSurname());
			pnForm.add(getTxtID());
			pnForm.add(getTxtEmail());
			pnForm.add(getScComments());
			pnForm.add(getCbArrivalDay());
			pnForm.add(getCbArrivalMonth());
			pnForm.add(getCbArrivalYear());
			pnForm.add(getSpPax());
			pnForm.add(getLblRooms());
			pnForm.add(getSpRooms());
			pnForm.add(getBtnDiscount());
			pnForm.add(getBtnCancel());
			pnForm.add(getBtnContinue());
			pnForm.add(getSpNights());
			pnForm.add(getLblRoomsNote());
		}
		return pnForm;
	}
	private JLabel getLblArrival() {
		if (lblArrival == null) {
			lblArrival = new JLabel("Arrival:");
			lblArrival.setDisplayedMnemonic('A');
			lblArrival.setLabelFor(getCbArrivalDay());
			lblArrival.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblArrival.setHorizontalAlignment(SwingConstants.TRAILING);
			lblArrival.setBounds(20, 20, 87, 36);
		}
		return lblArrival;
	}
	private JLabel getLblNights() {
		if (lblNights == null) {
			lblNights = new JLabel("Nights:");
			lblNights.setLabelFor(getSpNights());
			lblNights.setDisplayedMnemonic('N');
			lblNights.setHorizontalAlignment(SwingConstants.TRAILING);
			lblNights.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNights.setBounds(20, 66, 87, 44);
		}
		return lblNights;
	}
	private JLabel getLblPax() {
		if (lblPax == null) {
			lblPax = new JLabel("Pax:");
			lblPax.setLabelFor(getSpPax());
			lblPax.setDisplayedMnemonic('P');
			lblPax.setHorizontalAlignment(SwingConstants.TRAILING);
			lblPax.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPax.setBounds(218, 65, 81, 47);
		}
		return lblPax;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name:");
			lblName.setLabelFor(getTxtName());
			lblName.setDisplayedMnemonic('M');
			lblName.setHorizontalAlignment(SwingConstants.TRAILING);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblName.setBounds(20, 175, 87, 36);
		}
		return lblName;
	}
	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel("Surname:");
			lblSurname.setLabelFor(getTxtSurname());
			lblSurname.setDisplayedMnemonic('S');
			lblSurname.setHorizontalAlignment(SwingConstants.TRAILING);
			lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblSurname.setBounds(20, 221, 87, 36);
		}
		return lblSurname;
	}
	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("ID:");
			lblId.setLabelFor(getTxtID());
			lblId.setDisplayedMnemonic('I');
			lblId.setHorizontalAlignment(SwingConstants.TRAILING);
			lblId.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblId.setBounds(20, 267, 87, 36);
		}
		return lblId;
	}
	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("Email:");
			lblEmail.setLabelFor(getTxtEmail());
			lblEmail.setDisplayedMnemonic('E');
			lblEmail.setHorizontalAlignment(SwingConstants.TRAILING);
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEmail.setBounds(20, 313, 87, 36);
		}
		return lblEmail;
	}
	private JLabel getLblComments() {
		if (lblComments == null) {
			lblComments = new JLabel("Comments:");
			lblComments.setLabelFor(getTaComments());
			lblComments.setDisplayedMnemonic('T');
			lblComments.setHorizontalAlignment(SwingConstants.TRAILING);
			lblComments.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblComments.setBounds(20, 359, 87, 36);
		}
		return lblComments;
	}
	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtName.setBounds(117, 175, 290, 36);
			txtName.setColumns(10);
		}
		return txtName;
	}
	private JTextField getTxtSurname() {
		if (txtSurname == null) {
			txtSurname = new JTextField();
			txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtSurname.setColumns(10);
			txtSurname.setBounds(117, 221, 290, 36);
		}
		return txtSurname;
	}
	private JTextField getTxtID() {
		if (txtID == null) {
			txtID = new JTextField();
			txtID.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtID.setColumns(10);
			txtID.setBounds(117, 267, 182, 36);
		}
		return txtID;
	}
	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtEmail.setColumns(10);
			txtEmail.setBounds(117, 313, 290, 36);
		}
		return txtEmail;
	}
	private JScrollPane getScComments() {
		if (scComments == null) {
			scComments = new JScrollPane();
			scComments.setBounds(117, 367, 290, 55);
			scComments.setViewportView(getTaComments());
		}
		return scComments;
	}
	private JTextArea getTaComments() {
		if (taComments == null) {
			taComments = new JTextArea();
			taComments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return taComments;
	}
	private JComboBox<Integer> getCbArrivalDay() {
		if (cbArrivalDay == null) {
			cbArrivalDay = new JComboBox<Integer>();
			cbArrivalDay.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cbArrivalDay.setBounds(117, 20, 81, 36);
		}
		return cbArrivalDay;
	}
	private JComboBox<Integer> getCbArrivalMonth() {
		if (cbArrivalMonth == null) {
			cbArrivalMonth = new JComboBox<Integer>();
			cbArrivalMonth.addItemListener(dil);
			cbArrivalMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cbArrivalMonth.setBounds(218, 20, 81, 36);
		}
		return cbArrivalMonth;
	}
	private JComboBox<Integer> getCbArrivalYear() {
		if (cbArrivalYear == null) {
			cbArrivalYear = new JComboBox<Integer>();
			cbArrivalYear.addItemListener(dil);
			cbArrivalYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cbArrivalYear.setBounds(320, 20, 87, 36);
		}
		return cbArrivalYear;
	}
	private JSpinner getSpPax() {
		if (spPax == null) {
			spPax = new JSpinner();
			spPax.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
					setRoomSpinner();
					setPrices();
				}
			});
			spPax.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spPax.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spPax.setBounds(320, 66, 87, 44);
		}
		return spPax;
	}
	
	private JLabel getLblRooms() {
		if (lblRooms == null) {
			lblRooms = new JLabel("Rooms:");
			lblRooms.setLabelFor(getSpRooms());
			lblRooms.setDisplayedMnemonic('R');
			lblRooms.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblRooms.setHorizontalAlignment(SwingConstants.TRAILING);
			lblRooms.setBounds(30, 120, 81, 45);
		}
		return lblRooms;
	}
	private JSpinner getSpRooms() {
		if (spRooms == null) {
			spRooms = new JSpinner();
			spRooms.addChangeListener(pcl);
			spRooms.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spRooms.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spRooms.setBounds(117, 121, 87, 44);
		}
		return spRooms;
	}
	private JButton getBtnDiscount() {
		if (btnDiscount == null) {
			btnDiscount = new JButton("Apply discount");
			btnDiscount.setMnemonic('P');
			btnDiscount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (FormatChecker.blankCheck(getTxtID().getText())) {
						if (cm.retrieveDiscountCode(getTxtID().getText())) {
							getLblDiscount().setText(cm.getReservationDiscountString());
						} else {
							getLblDiscount().setText("");
							JOptionPane.showMessageDialog(ReservationForm.this, 
									"The input DNI has not got a discount associated "
									+ "to it. Please try with a different one.");
						}
					} else
						JOptionPane.showMessageDialog(ReservationForm.this, 
								"The input DNI is not a valid DNI number. Please, "
								+ "ensure the field is not blank.");
					setPrices();
				}
			});
			btnDiscount.setFont(new Font("Tahoma", Font.PLAIN, 10));
			btnDiscount.setBounds(309, 267, 98, 36);
		}
		return btnDiscount;
	}
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('C');
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkExit()) {
						mw.resetMenuBar();
						cm.resetBooking();
						dispose();
					}
				}
			});
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnCancel.setBounds(309, 432, 98, 43);
		}
		return btnCancel;
	}
	private JButton getBtnContinue() {
		if (btnContinue == null) {
			btnContinue = new JButton("Continue");
			btnContinue.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String dni = getTxtID().getText();
					if (!FormatChecker.blankCheck(dni)) {
						JOptionPane.showMessageDialog(ReservationForm.this, 
								"The input DNI is not a valid DNI number. Please, "
								+ "ensure the field is not blank.");
						return;
					}
					String name = getTxtName().getText();
					if (!FormatChecker.blankCheck(name)) {
						JOptionPane.showMessageDialog(ReservationForm.this, 
								"The input name is not valid. Please, ensure the field is not blank.");
						return;
					}
					String surname = getTxtSurname().getText();
					if (!FormatChecker.blankCheck(surname)) {
						JOptionPane.showMessageDialog(ReservationForm.this, 
								"The input surname is not valid. Please, ensure the field is not blank.");
						return;
					}
					String email = getTxtEmail().getText();
					if (!FormatChecker.isValidEmail(email)) {
						JOptionPane.showMessageDialog(ReservationForm.this, 
								"The input email is not valid. Please, ensure the field is not blank, "
								+ "and contains a valid email address.");
						return;
					}
					String date = getCbArrivalDay().getSelectedItem() + "/" + getCbArrivalMonth().getSelectedItem() + "/" +
							getCbArrivalYear().getSelectedItem();
					if (!FormatChecker.isValidDate(date)) {
						JOptionPane.showMessageDialog(ReservationForm.this, 
								"The input arrival date is not valid since it is previous to today.\n"
								+ "Please, check the field.");
						return;
					}
					int nights = (int) getSpNights().getValue();
					int rooms = (int) getSpRooms().getValue();
					String comments = getTaComments().getText();
					cm.prepareBooking(dni, name + " " + surname, email, date, nights, rooms, comments);
					// move to summary
					getContentPane().add(getSummary(), "summary");
					setSummaryEvents();
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "summary");
					getRootPane().setDefaultButton(summary.getBtnConfirm());
				}
			});
			btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnContinue.setBounds(201, 432, 98, 43);
		}
		return btnContinue;
	}
	private JSpinner getSpNights() {
		if (spNights == null) {
			spNights = new JSpinner();
			spNights.addChangeListener(pcl);
			spNights.setModel(new SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
			spNights.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spNights.setBounds(117, 66, 87, 44);
		}
		return spNights;
	}
	
	private JLabel getLblRoomsNote() {
		if (lblRoomsNote == null) {
			lblRoomsNote = new JLabel("At least 1 for each 2 pax");
			lblRoomsNote.setHorizontalAlignment(SwingConstants.CENTER);
			lblRoomsNote.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRoomsNote.setBounds(218, 122, 189, 44);
		}
		return lblRoomsNote;
	}
	private JPanel getPnReservationForm() {
		if (pnReservationForm == null) {
			pnReservationForm = new JPanel();
			pnReservationForm.setLayout(null);
			pnReservationForm.add(getPnCastleInfo());
			pnReservationForm.add(getPnTotal());
			pnReservationForm.add(getPnForm());
		}
		return pnReservationForm;
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
		hb.enableHelpKey(getRootPane(), "reservation", hs);
		hb.enableHelp(getRootPane(), "reservation", hs);
	}

	private class ConfirmCloseWindowAdapter extends WindowAdapter{
		@Override
		public void windowClosing(WindowEvent e) {
			if (checkExit()) {
				mw.resetMenuBar();
				cm.resetBooking();
				dispose();
			}
		}
	}
	
	private void setSummaryEvents() {
		summary.getBtnConfirm().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(ReservationForm.this, "Are you sure you want to formalize the booking?") == JOptionPane.YES_OPTION) {
					getContentPane().add(getPnConfirm(), "confirmationDialog");
					getRootPane().setDefaultButton(cd.getBtnMainMenu());
					((CardLayout) getContentPane().getLayout()).show(getContentPane(), "confirmationDialog");
					cm.formalize();
					removeWindowListener(ccwa);
					addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							((CardLayout) mw.getContentPane().getLayout()).show(mw.getContentPane(), "mainMenu");
							cm.reset();
							dispose();
						}
					});
				}
			}
		});
		summary.getBtnGoBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) getContentPane().getLayout()).show(getContentPane(), "reservationForm");
				cm.resetBooking();
				summary = null;
				getRootPane().setDefaultButton(getBtnContinue());
			}
		});
	}
	
	private class PriceChangeListener implements ChangeListener{
		@Override
		public void stateChanged(ChangeEvent e) {
			setPrices();
		}
	}
	
	
	private void setPrices() {
		int nights = (int) getSpNights().getValue();
		int rooms = (int) getSpRooms().getValue();
		double subtotal = cm.calculateSubtotal(nights, rooms);
		double total = cm.calculateTotal(nights, rooms);
		getTxtSubtotal().setText(subtotal + "€");
		getTxtTotal().setText(total + "€");
	}
	
	
	private void setRoomSpinner() {
		int pax = (int) getSpPax().getValue();
		int minRooms = (int) Math.ceil((double) pax /(double) 2);
		getSpRooms().setModel(new SpinnerNumberModel(Integer.valueOf(minRooms), Integer.valueOf(minRooms), Integer.valueOf(pax), Integer.valueOf(1)));
	}
	
	private boolean checkExit() {
		int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave and delete the order?");
		if (response == JOptionPane.YES_OPTION)
			return true;
		return false;
	}
	
	private void setComboModels() {
		Calendar today = Calendar.getInstance();
		int year = today.get(Calendar.YEAR);
		getCbArrivalYear().setModel(createComboModel(year, year+50));
		getCbArrivalMonth().setModel(createComboModel(1, 12));
		setArrivalModel();
	}

	private void setArrivalModel() {
		int month = (int) getCbArrivalMonth().getSelectedItem();
		int year = (int) getCbArrivalYear().getSelectedItem();
		getCbArrivalDay().setModel(createComboModel(1, getDaysInMonth(month, year)));
	}
	
	private int getDaysInMonth(int month, int year) {
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			return 31;
		case 2:
			if ((year % 4 == 0 && year % 100 != 0) || (year % 100 == 0 && year % 400 == 0))
				return 29;
			else
				return 28;
		default:
			return 30;
		}
	}

	/**
	 * both inclusive
	 */
	private DefaultComboBoxModel<Integer> createComboModel(int from, int to){
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
		for (int i = from; i <= to; i++) {
			model.addElement( i);
		}
		return model;
	}

	private class DateItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			setArrivalModel();
		}
	}
	
	private void setCastleInfo() {
		Castle c = cm.getSelectedCastle();
		setPictureImage("/img/" + c.getCode() + ".png");
		getLblTitle().setText(c.getName());
		getLblPrice().setText(c.getPricePerNight() + "€/night");
		getTpEnchantments().setText(c.getEnchantmentsString());
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
