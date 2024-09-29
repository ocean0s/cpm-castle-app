package uo.cpm.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import uo.cpm.model.Castle;
import uo.cpm.service.CastleManager;

import javax.swing.JCheckBox;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class FilterDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel pnEnchantments;
	private JPanel pnWest;
	private JPanel pnEast;
	private JCheckBox chckbxEn;
	private JCheckBox chckbxAp;
	private JCheckBox chckbxDe;
	private JCheckBox chckbxRu;
	private JCheckBox chckbxOb;
	private JCheckBox chckbxOl;
	private JPanel pnPrice;
	private JTextField txtPrice;
	private JLabel lblMaxPrice;
	private JPanel buttonPane;
	private JButton okButton;
	private JButton cancelButton;
	private boolean isOKOption;

	static List<Castle> processFilter(CastleManager cm){
		FilterDialog filterDialog = new FilterDialog();
		if (filterDialog.isOKOption)
			return cm.getFilteredCastles(
				filterDialog.getMaxPrice(),
				filterDialog.getEnchantmentFilters());	
		return cm.loadCastles();
	}
	
	/**
	 * Create the dialog.
	 * @param fal 
	 */
	private FilterDialog() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FilterDialog.class.getResource("/img/ESC001.png")));
		setTitle("Crazy Castles: Filters");
		setResizable(false);
		setAlwaysOnTop(true);
		setModal(true);
		setBounds(100, 100, 400, 350);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		contentPanel.add(getPnEnchantments());
		contentPanel.add(getPnPrice());
		contentPanel.add(getButtonPane());
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		getRootPane().setDefaultButton(getOkButton());
		setVisible(true);
	}
	private JPanel getPnEnchantments() {
		if (pnEnchantments == null) {
			pnEnchantments = new JPanel();
			pnEnchantments.setBorder(new TitledBorder(null, "Enchantments", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnEnchantments.setBounds(10, 10, 366, 182);
			pnEnchantments.setLayout(new GridLayout(1, 0, 0, 0));
			pnEnchantments.add(getPnWest());
			pnEnchantments.add(getPnEast());
		}
		return pnEnchantments;
	}
	private JPanel getPnWest() {
		if (pnWest == null) {
			pnWest = new JPanel();
			pnWest.setLayout(new GridLayout(0, 1, 0, 0));
			pnWest.add(getChckbxAp());
			pnWest.add(getChckbxDe());
			pnWest.add(getChckbxEn());
		}
		return pnWest;
	}
	private JPanel getPnEast() {
		if (pnEast == null) {
			pnEast = new JPanel();
			pnEast.setLayout(new GridLayout(0, 1, 0, 0));
			pnEast.add(getChckbxOb());
			pnEast.add(getChckbxOl());
			pnEast.add(getChckbxRu());
		}
		return pnEast;
	}
	private JCheckBox getChckbxEn() {
		if (chckbxEn == null) {
			chckbxEn = new JCheckBox("Lights blinking");
			chckbxEn.setMnemonic('L');
			chckbxEn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chckbxEn;
	}
	private JCheckBox getChckbxAp() {
		if (chckbxAp == null) {
			chckbxAp = new JCheckBox("Apparitions of ghosts");
			chckbxAp.setMnemonic('A');
			chckbxAp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chckbxAp;
	}
	private JCheckBox getChckbxDe() {
		if (chckbxDe == null) {
			chckbxDe = new JCheckBox("Drop in temperature");
			chckbxDe.setMnemonic('D');
			chckbxDe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chckbxDe;
	}
	private JCheckBox getChckbxRu() {
		if (chckbxRu == null) {
			chckbxRu = new JCheckBox("Strange noises");
			chckbxRu.setMnemonic('S');
			chckbxRu.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chckbxRu;
	}
	private JCheckBox getChckbxOb() {
		if (chckbxOb == null) {
			chckbxOb = new JCheckBox("Moving objects");
			chckbxOb.setMnemonic('M');
			chckbxOb.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chckbxOb;
	}
	private JCheckBox getChckbxOl() {
		if (chckbxOl == null) {
			chckbxOl = new JCheckBox("Nauseous smells");
			chckbxOl.setMnemonic('N');
			chckbxOl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return chckbxOl;
	}
	private JPanel getPnPrice() {
		if (pnPrice == null) {
			pnPrice = new JPanel();
			pnPrice.setBorder(new TitledBorder(null, "Price", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPrice.setBounds(10, 202, 366, 70);
			pnPrice.setLayout(new BorderLayout(0, 0));
			pnPrice.add(getTxtPrice(), BorderLayout.CENTER);
			pnPrice.add(getLblMaxPrice(), BorderLayout.WEST);
		}
		return pnPrice;
	}
	private JTextField getTxtPrice() {
		if (txtPrice == null) {
			txtPrice = new JTextField();
			txtPrice.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					char key = e.getKeyChar();
					if (!Character.isDigit(key)) {
						e.consume();
					}
				}
			});
			txtPrice.setColumns(10);
		}
		return txtPrice;
	}
	private JLabel getLblMaxPrice() {
		if (lblMaxPrice == null) {
			lblMaxPrice = new JLabel("Maximum price per night:");
			lblMaxPrice.setDisplayedMnemonic('M');
			lblMaxPrice.setLabelFor(getTxtPrice());
			lblMaxPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblMaxPrice;
	}
	private JPanel getButtonPane() {
		if (buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setBounds(0, 272, 386, 31);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			buttonPane.add(getOkButton());
			buttonPane.add(getCancelButton());
		}
		return buttonPane;
	}
	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("Apply");
			okButton.setBackground(Color.WHITE);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					isOKOption = true;
					dispose();
				}
			});
			okButton.setActionCommand("OK");
		}
		return okButton;
	}
	private JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
			cancelButton.setMnemonic('C');
			cancelButton.setBackground(Color.WHITE);
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			cancelButton.setActionCommand("Cancel");
		}
		return cancelButton;
	}
	private List<String> getEnchantmentFilters(){
		List <String> filters = new ArrayList<>();
		if (chckbxAp.isSelected())
			filters.add("Ap");
		if (chckbxDe.isSelected())
			filters.add("De");
		if (chckbxEn.isSelected())
			filters.add("En");
		if (chckbxOb.isSelected())
			filters.add("Ob");
		if (chckbxOl.isSelected())
			filters.add("Ol");
		if (chckbxRu.isSelected())
			filters.add("Ru");
		return filters;
	}
	
	private int getMaxPrice() {
		if (getTxtPrice().getText().isBlank())
			return Integer.MAX_VALUE;
		return Integer.parseInt(getTxtPrice().getText());
	}
}
