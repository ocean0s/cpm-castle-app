package uo.cpm.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;

public class MainMenu extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int MAX_WIDTH = 725;
	private JPanel pnSlideshow;
	private JButton btnPrevious;
	private JButton btnNext;
	private JPanel pnCastleName;
	private JPanel pnFiller2;
	private JPanel pnButtons;
	private JButton btnPlay;
	private JButton btnSeeCastles;
	private JPanel pnImage;
	private JLabel lblImage;
	private JLabel lblCastleName;
	private JPanel pnFiller3;

	/**
	 * Create the panel.
	 */
	MainMenu() {
			this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			this.add(getPnFiller3());
			this.add(getPnSlideshow());
			this.add(getPnCastleName());
			this.add(getPnButtons());
			this.add(getPnFiller2());
	}

	private JPanel getPnSlideshow() {
		if (pnSlideshow == null) {
			pnSlideshow = new JPanel();
			pnSlideshow.setLayout(new BoxLayout(pnSlideshow, BoxLayout.X_AXIS));
			pnSlideshow.add(getBtnPrevious());
			pnSlideshow.add(getPnImage());
			pnSlideshow.add(getBtnNext());
		}
		return pnSlideshow;
	}
	JButton getBtnPrevious() {
		if (btnPrevious == null) {
			btnPrevious = new JButton("◄");
			btnPrevious.setBackground(Color.WHITE);
			btnPrevious.setHorizontalAlignment(SwingConstants.LEFT);
		}
		return btnPrevious;
	}
	JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("►");
			btnNext.setBackground(Color.WHITE);
		}
		return btnNext;
	}
	private JPanel getPnCastleName() {
		if (pnCastleName == null) {
			pnCastleName = new JPanel();
			pnCastleName.add(getLblCastleName());
		}
		return pnCastleName;
	}
	private JPanel getPnFiller2() {
		if (pnFiller2 == null) {
			pnFiller2 = new JPanel();
		}
		return pnFiller2;
	}
	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 5));
			pnButtons.add(getBtnPlay());
			pnButtons.add(getBtnSeeCastles());
		}
		return pnButtons;
	}
	JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("Play!");
			btnPlay.setBackground(Color.WHITE);
			btnPlay.setMnemonic('P');
			btnPlay.setFont(new Font("Tahoma", Font.BOLD, 32));
		}
		return btnPlay;
	}
	JButton getBtnSeeCastles() {
		if (btnSeeCastles == null) {
			btnSeeCastles = new JButton("See castles and book");
			btnSeeCastles.setMnemonic('S');
			btnSeeCastles.setBackground(Color.WHITE);
			btnSeeCastles.setFont(new Font("Tahoma", Font.BOLD, 32));
		}
		return btnSeeCastles;
	}

	private JPanel getPnImage() {
		if (pnImage == null) {
			pnImage = new JPanel();
			pnImage.setLayout(new BoxLayout(pnImage, BoxLayout.Y_AXIS));
			pnImage.add(getLblImage_1());
		}
		return pnImage;
	}
	JLabel getLblImage_1() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setMaximumSize(new Dimension(MAX_WIDTH, 400));
			lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImage;
	}
	JLabel getLblCastleName() {
		if (lblCastleName == null) {
			lblCastleName = new JLabel();
			lblCastleName.setHorizontalAlignment(SwingConstants.CENTER);
			lblCastleName.setVerticalAlignment(SwingConstants.TOP);
			lblCastleName.setText((String) null);
			lblCastleName.setAlignmentX(0.5f);
		}
		return lblCastleName;
	}
	private JPanel getPnFiller3() {
		if (pnFiller3 == null) {
			pnFiller3 = new JPanel();
		}
		return pnFiller3;
	}
}
