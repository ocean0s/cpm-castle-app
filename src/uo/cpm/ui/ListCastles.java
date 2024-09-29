package uo.cpm.ui;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uo.cpm.model.Castle;
import uo.cpm.service.CastleManager;

import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListCastles extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnCastles;
	private JPanel pnCastleInfo;
	private JPanel pnCastlesTop;
	private JLabel lblAvailableCastles;
	private JPanel pnListCastles;
	private JScrollPane scCastles;
	private JList<Castle> listCastles;
	private JPanel pnPicture;
	private JLabel lblPicture;
	private JPanel pnInfo;
	private JPanel pnNameAndPrice;
	private JLabel lblName;
	private JPanel pnButtons;
	private JButton btnSelect;
	private JButton btnBack;
	private JPanel pnDescriptionAndEnchantments;
	private JPanel pnEnchantments;
	private JLabel lblEnchantmentInfo;
	private JLabel lblPrice;
	private JPanel pnDescription;
	private JScrollPane scDescription;
	private JTextPane tpDescription;
	private JLabel lblDescription;
	private CastleManager cm;
	private DefaultListModel<Castle> modelCastles;
	private JScrollPane spEnchantments;
	private JTextPane tpEnchantments;
	private JButton btnFilter;
	private boolean isFiltering;

	/**
	 * Create the panel.
	 */
	public ListCastles(CastleManager cm) {
		this.cm = cm;
		setLayout(new GridLayout(1, 0, 0, 0));
		add(getPnCastles());
		add(getPnCastleInfo());
	}

	private JPanel getPnCastles() {
		if (pnCastles == null) {
			pnCastles = new JPanel();
			pnCastles.setLayout(new BorderLayout(0, 0));
			pnCastles.add(getPnCastlesTop(), BorderLayout.NORTH);
			pnCastles.add(getPnListCastles(), BorderLayout.CENTER);
		}
		return pnCastles;
	}

	private JPanel getPnCastleInfo() {
		if (pnCastleInfo == null) {
			pnCastleInfo = new JPanel();
			pnCastleInfo.setLayout(new GridLayout(0, 1, 0, 0));
			pnCastleInfo.add(getPnPicture());
			pnCastleInfo.add(getPnInfo());
		}
		return pnCastleInfo;
	}

	private JPanel getPnCastlesTop() {
		if (pnCastlesTop == null) {
			pnCastlesTop = new JPanel();
			pnCastlesTop.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnCastlesTop.setLayout(new BorderLayout(0, 0));
			pnCastlesTop.add(getLblAvailableCastles(), BorderLayout.WEST);
			pnCastlesTop.add(getBtnFilter(), BorderLayout.EAST);
		}
		return pnCastlesTop;
	}

	private JLabel getLblAvailableCastles() {
		if (lblAvailableCastles == null) {
			lblAvailableCastles = new JLabel("Available castles");
			lblAvailableCastles.setHorizontalAlignment(SwingConstants.CENTER);
			lblAvailableCastles.setFont(new Font("Tahoma", Font.BOLD, 26));
		}
		return lblAvailableCastles;
	}

	private JPanel getPnListCastles() {
		if (pnListCastles == null) {
			pnListCastles = new JPanel();
			pnListCastles.setBorder(new EmptyBorder(15, 15, 15, 15));
			pnListCastles.setLayout(new BorderLayout(0, 0));
			pnListCastles.add(getScCastles(), BorderLayout.CENTER);
		}
		return pnListCastles;
	}

	private JScrollPane getScCastles() {
		if (scCastles == null) {
			scCastles = new JScrollPane();
			scCastles.setViewportView(getListCastles());
		}
		return scCastles;
	}

	private JList<Castle> getListCastles() {
		if (listCastles == null) {
			listCastles = new JList<Castle>();
			listCastles.addListSelectionListener(new ListSelectionListener() {
				public void valueChanged(ListSelectionEvent e) {
					if (isFiltering || modelCastles.size() == 0) {
						getBtnSelect().setEnabled(false);
						return;
					}
					Castle selected = listCastles.getSelectedValue();
					if (selected == null) {
						getBtnSelect().setEnabled(false);
						return;
					}
					getBtnSelect().setEnabled(true);
					getTpEnchantments().setText(selected.getEnchantmentsString());
					getLblName().setText(selected.getName());
					getLblPrice().setText(selected.getPricePerNight() + " €");
					getTpDescription().setText(selected.getDescription());
					getLblPicture().setText("");
					setPictureImage("/img/" + selected.getCode() + ".png");
				}
			});
			modelCastles = new DefaultListModel<>();
			modelCastles.addAll(cm.loadCastles());
			listCastles.setModel(modelCastles);
			listCastles.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			listCastles.setFont(new Font("Tahoma", Font.PLAIN, 18));
		}
		return listCastles;
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

	private JPanel getPnPicture() {
		if (pnPicture == null) {
			pnPicture = new JPanel();
			pnPicture.setBorder(new EmptyBorder(20, 10, 20, 10));
			pnPicture.setLayout(new BorderLayout(0, 0));
			pnPicture.add(getLblPicture(), BorderLayout.CENTER);
		}
		return pnPicture;
	}

	private JLabel getLblPicture() {
		if (lblPicture == null) {
			lblPicture = new JLabel("Select a castle from the list");
			lblPicture.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblPicture;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnInfo.setLayout(new BorderLayout(0, 0));
			pnInfo.add(getPnNameAndPrice(), BorderLayout.NORTH);
			pnInfo.add(getPnButtons(), BorderLayout.SOUTH);
			pnInfo.add(getPnDescriptionAndEnchantments(), BorderLayout.CENTER);
		}
		return pnInfo;
	}

	private JPanel getPnNameAndPrice() {
		if (pnNameAndPrice == null) {
			pnNameAndPrice = new JPanel();
			pnNameAndPrice.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnNameAndPrice.setLayout(new BorderLayout(0, 0));
			pnNameAndPrice.add(getLblName(), BorderLayout.WEST);
			pnNameAndPrice.add(getLblPrice(), BorderLayout.EAST);
		}
		return pnNameAndPrice;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("");
			lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		return lblName;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setHgap(15);
			pnButtons.add(getBtnSelect());
			pnButtons.add(getBtnBack());
		}
		return pnButtons;
	}

	JButton getBtnSelect() {
		if (btnSelect == null) {
			btnSelect = new JButton("Select");
			btnSelect.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnSelect.setBackground(Color.WHITE);
			btnSelect.setEnabled(false);
		}
		return btnSelect;
	}

	JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back to main menu");
			btnBack.setMnemonic('B');
			btnBack.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnBack.setBackground(Color.WHITE);
		}
		return btnBack;
	}

	private JPanel getPnDescriptionAndEnchantments() {
		if (pnDescriptionAndEnchantments == null) {
			pnDescriptionAndEnchantments = new JPanel();
			pnDescriptionAndEnchantments.setLayout(new BorderLayout(0, 0));
			pnDescriptionAndEnchantments.add(getPnEnchantments(), BorderLayout.SOUTH);
			pnDescriptionAndEnchantments.add(getPnDescription(), BorderLayout.CENTER);
		}
		return pnDescriptionAndEnchantments;
	}

	private JPanel getPnEnchantments() {
		if (pnEnchantments == null) {
			pnEnchantments = new JPanel();
			pnEnchantments.setBorder(new EmptyBorder(10, 10, 10, 10));
			pnEnchantments.setLayout(new BorderLayout(0, 0));
			pnEnchantments.add(getLblEnchantmentInfo(), BorderLayout.NORTH);
			pnEnchantments.add(getSpEnchantments(), BorderLayout.CENTER);
		}
		return pnEnchantments;
	}

	private JLabel getLblEnchantmentInfo() {
		if (lblEnchantmentInfo == null) {
			lblEnchantmentInfo = new JLabel("Enchantments:");
			lblEnchantmentInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblEnchantmentInfo;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel("");
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblPrice;
	}

	private JPanel getPnDescription() {
		if (pnDescription == null) {
			pnDescription = new JPanel();
			pnDescription.setBorder(new EmptyBorder(0, 10, 0, 10));
			pnDescription.setLayout(new BorderLayout(0, 0));
			pnDescription.add(getScDescription(), BorderLayout.CENTER);
			pnDescription.add(getLblDescription(), BorderLayout.NORTH);
		}
		return pnDescription;
	}

	private JScrollPane getScDescription() {
		if (scDescription == null) {
			scDescription = new JScrollPane();
			scDescription.setBorder(null);
			scDescription.setViewportView(getTpDescription());
		}
		return scDescription;
	}

	private JTextPane getTpDescription() {
		if (tpDescription == null) {
			tpDescription = new JTextPane();
			tpDescription.setBorder(null);
			tpDescription.setEditable(false);
			tpDescription.setBackground(new Color(214, 217, 223));
		}
		return tpDescription;
	}

	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel("Description:");
			lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblDescription;
	}

	private JScrollPane getSpEnchantments() {
		if (spEnchantments == null) {
			spEnchantments = new JScrollPane();
			spEnchantments.setBorder(null);
			spEnchantments.setViewportView(getTpEnchantments());
		}
		return spEnchantments;
	}

	private JTextPane getTpEnchantments() {
		if (tpEnchantments == null) {
			tpEnchantments = new JTextPane();
			tpEnchantments.setPreferredSize(new Dimension(7, 35));
			tpEnchantments.setMinimumSize(new Dimension(7, 20));
			tpEnchantments.setBackground(new Color(214, 217, 223));
			tpEnchantments.setBorder(null);
			tpEnchantments.setEditable(false);
		}
		return tpEnchantments;
	}

	private JButton getBtnFilter() {
		if (btnFilter == null) {
			btnFilter = new JButton("Filter by...");
			btnFilter.setMnemonic('F');
			btnFilter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					isFiltering = true;
					modelCastles.removeAllElements();
					modelCastles.addAll(FilterDialog.processFilter(cm));
					getTpEnchantments().setText("");
					getLblName().setText("");
					getLblPrice().setText("");
					getTpDescription().setText("");
					getLblPicture().setText("Select a castle from the list");
					getLblPicture().setIcon(null);
					validate();
					isFiltering = false;
				}
			});
			btnFilter.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return btnFilter;
	}
	
	Castle getSelectedCastle() {
		return listCastles.getSelectedValue();	
	}
}
