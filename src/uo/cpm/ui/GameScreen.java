package uo.cpm.ui;

import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import uo.cpm.service.CastleManager;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import javax.swing.Box;
import javax.swing.JCheckBox;
import java.awt.FlowLayout;
import java.awt.Component;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel pnDiceThrows;
	private JPanel pnThrows;
	private JPanel pnMainArea;
	private JPanel pnDice;
	private JButton btnThrow;
	private JLabel lblDiceThrow;
	private JPanel panelThrowResult;
	private JLabel lblDiceResult;
	private JPanel pnButtons;
	private JButton btnPlayAgain;
	private JButton btnContinue;
	private JCheckBox chckbxOver18;
	private JPanel pnBoardArea;
	private JPanel pnBoard;
	private JPanel pnResult;
	private JPanel pnRow0;
	private JPanel pnRow1;
	private JPanel pnRow2;
	private JPanel pnRow3;
	private JPanel pnRow4;
	private JLabel lblDiscountObtained;
	private JLabel lblGameOver;
	private JLabel lblDiscount;
	private JLabel lblMaxDiscount;
	private CastleManager cm;
	private MainWindow mw;
	private BoardActionListener bal = new BoardActionListener();
	private boolean hasContinueAl = false;

	/**
	 * Create the panel.
	 */
	public GameScreen(CastleManager cm, MainWindow mw) {
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(getPnDiceThrows());
		add(getPnMainArea());
		this.cm = cm;
		this.mw = mw;
		createThrowsLabels();
		initBoard();
	}

	private JPanel getPnDiceThrows() {
		if (pnDiceThrows == null) {
			pnDiceThrows = new JPanel();
			pnDiceThrows.setLayout(new BorderLayout(0, 0));
			pnDiceThrows.add(getPnThrows(), BorderLayout.CENTER);
		}
		return pnDiceThrows;
	}

	private JPanel getPnThrows() {
		if (pnThrows == null) {
			pnThrows = new JPanel();
			pnThrows.setMinimumSize(new Dimension(32767, 50));
			pnThrows.setMaximumSize(new Dimension(32767, 50));
			pnThrows.setPreferredSize(new Dimension(32767, 0));
			pnThrows.setBorder(new EmptyBorder(20, 20, 45, 45));
			pnThrows.setLayout(new GridLayout(1, 0, 0, 0));
		}
		return pnThrows;
	}

	private JPanel getPnMainArea() {
		if (pnMainArea == null) {
			pnMainArea = new JPanel();
			pnMainArea.setLayout(new BorderLayout(0, 0));
			pnMainArea.add(getPnDice(), BorderLayout.EAST);
			pnMainArea.add(getPanel_2(), BorderLayout.CENTER);
		}
		return pnMainArea;
	}

	private JPanel getPnDice() {
		if (pnDice == null) {
			pnDice = new JPanel();
			pnDice.setPreferredSize(new Dimension(300, 400));
			pnDice.setLayout(new BoxLayout(pnDice, BoxLayout.Y_AXIS));
			pnDice.add(Box.createRigidArea(new Dimension(0, 40)));
			pnDice.add(getBtnThrow());
			pnDice.add(Box.createRigidArea(new Dimension(0, 40)));
			pnDice.add(getLblDiceThrow());
			pnDice.add(Box.createRigidArea(new Dimension(0, 40)));
			pnDice.add(getPanelThrowResult());
			Component rigidArea = Box.createRigidArea(new Dimension(0, 40));
			rigidArea.setPreferredSize(new Dimension(0, 80));
			pnDice.add(rigidArea);
			pnDice.add(getPnButtons());
		}
		return pnDice;
	}

	JButton getBtnThrow() {
		if (btnThrow == null) {
			btnThrow = new JButton("Throw the dice!");
			btnThrow.setBackground(Color.WHITE);
			btnThrow.setFont(new Font("Tahoma", Font.BOLD, 24));
			btnThrow.addActionListener(new ThrowActionListener());
		}
		return btnThrow;
	}

	private JLabel getLblDiceThrow() {
		if (lblDiceThrow == null) {
			lblDiceThrow = new JLabel("You threw a:");
			lblDiceThrow.setBounds(new Rectangle(0, 0, 0, 50));
			lblDiceThrow.setFont(new Font("Tahoma", Font.BOLD, 24));
		}
		return lblDiceThrow;
	}

	private JPanel getPanelThrowResult() {
		if (panelThrowResult == null) {
			panelThrowResult = new JPanel();
			panelThrowResult.setMaximumSize(new Dimension(9999, 50));
			panelThrowResult.add(getLblDiceResult());
		}
		return panelThrowResult;
	}

	private JLabel getLblDiceResult() {
		if (lblDiceResult == null) {
			lblDiceResult = new JLabel();
			lblDiceResult.setPreferredSize(new Dimension(50, 50));
			lblDiceResult.setMinimumSize(new Dimension(20, 20));
			lblDiceResult.setMaximumSize(new Dimension(20, 20));
			lblDiceResult.setHorizontalAlignment(SwingConstants.CENTER);
			lblDiceResult.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		}
		return lblDiceResult;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setLayout(null);
			pnButtons.add(getBtnPlayAgain());
			pnButtons.add(getBtnContinue());
			pnButtons.add(getChckbxOver18());
		}
		return pnButtons;
	}

	private JButton getBtnPlayAgain() {
		if (btnPlayAgain == null) {
			btnPlayAgain = new JButton("Play Again!");
			btnPlayAgain.setMnemonic('P');
			btnPlayAgain.setBackground(Color.WHITE);
			btnPlayAgain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cm.initializeGame();
					updateBoard();
					disableBoard();
					getBtnThrow().setEnabled(true);
					createThrowsLabels();
					getBtnPlayAgain().setEnabled(false);
					getBtnContinue().setEnabled(false);
					getChckbxOver18().setEnabled(false);
					for (Component c : getPnResult().getComponents())
						c.setVisible(false);
					getLblDiceResult().setText("");
				}
			});
			btnPlayAgain.setEnabled(false);
			btnPlayAgain.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnPlayAgain.setBounds(10, 10, 144, 58);
		}
		return btnPlayAgain;
	}

	JButton getBtnContinue() {
		if (btnContinue == null) {
			btnContinue = new JButton("Continue");
			btnContinue.setMnemonic('C');
			btnContinue.setBackground(Color.WHITE);
			btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 16));
			btnContinue.setEnabled(false);
			btnContinue.setBounds(164, 12, 126, 56);
		}
		return btnContinue;
	}

	private JCheckBox getChckbxOver18() {
		if (chckbxOver18 == null) {
			chckbxOver18 = new JCheckBox("I'm over 18");
			chckbxOver18.setMnemonic('O');
			chckbxOver18.setFont(new Font("Tahoma", Font.PLAIN, 16));
			chckbxOver18.setEnabled(false);
			chckbxOver18.setBounds(164, 74, 126, 32);
			chckbxOver18.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (chckbxOver18.isSelected())
						getBtnContinue().setEnabled(true);
					else
						getBtnContinue().setEnabled(false);
				}
			});
		}
		return chckbxOver18;
	}

	private JPanel getPanel_2() {
		if (pnBoardArea == null) {
			pnBoardArea = new JPanel();
			pnBoardArea.setLayout(new BoxLayout(pnBoardArea, BoxLayout.Y_AXIS));
			pnBoardArea.add(getPnBoard());
			pnBoardArea.add(getPnResult());
		}
		return pnBoardArea;
	}

	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setBorder(new EmptyBorder(0, 40, 0, 40));
			pnBoard.setLayout(new GridLayout(0, 1, 0, 0));
			pnBoard.add(getPnRow0());
			pnBoard.add(getPnRow1());
			pnBoard.add(getPnRow2());
			pnBoard.add(getPnRow3());
			pnBoard.add(getPnRow4());
		}
		return pnBoard;
	}

	private JPanel getPnResult() {
		if (pnResult == null) {
			pnResult = new JPanel();
			pnResult.setLayout(null);
			pnResult.add(getLblDiscountObtained());
			pnResult.add(getLblGameOver());
			pnResult.add(getLblDiscount());
			pnResult.add(getLblMaxDiscount());
		}
		return pnResult;
	}

	JPanel getPnRow0() {
		if (pnRow0 == null) {
			pnRow0 = new JPanel();
			pnRow0.setPreferredSize(new Dimension(10, 42));
			pnRow0.setMinimumSize(new Dimension(10, 42));
			pnRow0.setMaximumSize(new Dimension(32767, 42));
			pnRow0.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return pnRow0;
	}

	JPanel getPnRow1() {
		if (pnRow1 == null) {
			pnRow1 = new JPanel();
			pnRow1.setPreferredSize(new Dimension(10, 42));
			pnRow1.setMinimumSize(new Dimension(10, 42));
			pnRow1.setMaximumSize(new Dimension(32767, 42));
			pnRow1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return pnRow1;
	}

	JPanel getPnRow2() {
		if (pnRow2 == null) {
			pnRow2 = new JPanel();
			pnRow2.setPreferredSize(new Dimension(10, 42));
			pnRow2.setMinimumSize(new Dimension(10, 42));
			pnRow2.setMaximumSize(new Dimension(32767, 42));
			pnRow2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return pnRow2;
	}

	JPanel getPnRow3() {
		if (pnRow3 == null) {
			pnRow3 = new JPanel();
			pnRow3.setPreferredSize(new Dimension(10, 42));
			pnRow3.setMinimumSize(new Dimension(10, 42));
			pnRow3.setMaximumSize(new Dimension(32767, 42));
			pnRow3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return pnRow3;
	}

	JPanel getPnRow4() {
		if (pnRow4 == null) {
			pnRow4 = new JPanel();
			pnRow4.setPreferredSize(new Dimension(10, 42));
			pnRow4.setMinimumSize(new Dimension(10, 42));
			pnRow4.setMaximumSize(new Dimension(32767, 42));
			pnRow4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		}
		return pnRow4;
	}

	JLabel getLblDiscountObtained() {
		if (lblDiscountObtained == null) {
			lblDiscountObtained = new JLabel("Discount Obtained:");
			lblDiscountObtained.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblDiscountObtained.setBounds(149, 51, 239, 38);
			lblDiscountObtained.setVisible(false);
		}
		return lblDiscountObtained;
	}

	JLabel getLblGameOver() {
		if (lblGameOver == null) {
			lblGameOver = new JLabel("Game Over!");
			lblGameOver.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblGameOver.setBounds(149, 10, 239, 37);
			lblGameOver.setVisible(false);
		}
		return lblGameOver;
	}

	JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("");
			lblDiscount.setFont(new Font("Tahoma", Font.BOLD, 24));
			lblDiscount.setBounds(398, 51, 548, 38);
			lblDiscount.setVisible(false);
		}
		return lblDiscount;
	}

	private JLabel getLblMaxDiscount() {
		if (lblMaxDiscount == null) {
			lblMaxDiscount = new JLabel("Maximum discount obtained!");
			lblMaxDiscount.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblMaxDiscount.setHorizontalAlignment(SwingConstants.CENTER);
			lblMaxDiscount.setBounds(398, 12, 226, 29);
			lblMaxDiscount.setVisible(false);
		}
		return lblMaxDiscount;
	}

	void createThrowsLabels() {
		pnThrows.removeAll();
		for (int i = 0; i < cm.getMaxPlays(); i++) {
			pnThrows.add(createDiceThrowLabel());
		}
	}

	private JLabel createDiceThrowLabel() {
		JLabel res = new JLabel();
		res.setBorder(new LineBorder(Color.BLACK, 2));
		res.setVisible(true);
		res.setBackground(Color.GRAY);
		res.setForeground(Color.BLACK);
		res.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 55));
		res.setHorizontalAlignment(SwingConstants.CENTER);
		return res;
	}

	/**
	 * Initializes the board
	 */
	void initBoard( ) {
		int[] sizes = cm.getSizes();
		String[][] text = cm.getGameTexts();
		int col = 0;
		col = 3;
		for (int i = 0; i < sizes[0]; i++) {
			pnRow0.add(createButtonBoard(text[0][i], 0, col));}
		col = 2;
		for (int i = 0; i < sizes[1]; i++) {
			pnRow1.add(createButtonBoard(text[1][i], 1, col));
			col++;}
		col = 1;
		for (int i = 0; i < sizes[2]; i++) {
			pnRow2.add(createButtonBoard(text[2][i], 2, col));
			col++; }
		for (int i = 0; i < sizes[3]; i++) {
			pnRow3.add(createButtonBoard(text[3][i], 3, i));
			}
		for (int i = 0; i < sizes[4]; i++) {
			pnRow4.add(createButtonBoard(text[4][i], 4, i));
			}
		validate();
	}

	/**
	 * enables gb cells
	 */
	void enableBoard() {
		for (Component c : getPnRow4().getComponents())
			tryEnableCell((JButton) c);
		for (Component c : getPnRow3().getComponents())
			tryEnableCell((JButton) c);
		for (Component c : getPnRow2().getComponents())
			tryEnableCell((JButton) c);
		for (Component c : getPnRow1().getComponents())
			tryEnableCell((JButton) c);
		for (Component c : getPnRow0().getComponents())
			tryEnableCell((JButton) c);
	}

	void tryEnableCell(JButton c) {
		String[] strcoords = new String[2];
		strcoords = c.getActionCommand().split(";");
		if (cm.hasGhostBuster(Integer.parseInt(strcoords[0]), Integer.parseInt(strcoords[1]))) {
			c.setEnabled(true);
			c.setBackground(Color.ORANGE);
		}
		else {
			c.setEnabled(false);
			c.setBackground(Color.WHITE);
		}
	}

	/**
	 * updates the state of the board
	 */
	void updateBoard() {
		String[][] texts = cm.getGameTexts();
		int[] sizes = cm.getSizes();
		for (int i = 0; i < sizes[0]; i++) 
			((JButton) pnRow0.getComponent(i)).setText(texts[0][i]);
		for (int i = 0; i < sizes[1]; i++) 
			((JButton) pnRow1.getComponent(i)).setText(texts[1][i]);
		for (int i = 0; i < sizes[2]; i++) 
			((JButton) pnRow2.getComponent(i)).setText(texts[2][i]);
		for (int i = 0; i < sizes[3]; i++) 
			((JButton) pnRow3.getComponent(i)).setText(texts[3][i]);
		for (int i = 0; i < sizes[4]; i++) 
			((JButton) pnRow4.getComponent(i)).setText(texts[4][i]);
	}

	/**
	 * Disables all the board
	 */
	void disableBoard() {
		for (Component c : getPnRow4().getComponents()) {
			c.setEnabled(false);
			c.setBackground(Color.WHITE);
		}
		for (Component c : getPnRow3().getComponents()) {
			c.setEnabled(false);
			c.setBackground(Color.WHITE);
		}
		for (Component c : getPnRow2().getComponents()) {
			c.setEnabled(false);
			c.setBackground(Color.WHITE);
		}
		for (Component c : getPnRow1().getComponents()) {
			c.setEnabled(false);
			c.setBackground(Color.WHITE);
		}
		for (Component c : getPnRow0().getComponents()) {
			c.setEnabled(false);
			c.setBackground(Color.WHITE);
		}
	}

	void triggerGameFinish(String discount) {
		// show result zone
		getLblDiscount().setVisible(true);
		getLblDiscountObtained().setVisible(true);
		getLblGameOver().setVisible(true);
		getLblDiscount().setText(discount);
		// enable buttons
		getBtnPlayAgain().setEnabled(true);
		getChckbxOver18().setEnabled(true);
		if (getChckbxOver18().isSelected())
			getBtnContinue().setEnabled(true);
		else
			getBtnContinue().setEnabled(false);
			
	}

	private void triggerMaxDiscount() {
		getLblMaxDiscount().setVisible(true);
	}

	private JButton createButtonBoard(String text, int row, int col) {
		JButton res = new JButton(text);
		res.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 60));
		res.setActionCommand(row + ";" + col);
		res.setEnabled(false);
		res.addActionListener(bal);
		res.setForeground(Color.BLACK);
		return res;
	}

	private class ThrowActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			cm.roll();
			getLblDiceResult().setText(cm.getCurrentThrow() + "");
			getBtnThrow().setEnabled(false);
			enableBoard();
			((JLabel) getPnThrows().getComponent(cm.getMaxPlays()-cm.getPlaysRemaining())).
				setText(cm.getCurrentThrow()+"");
		}

	}

	private class BoardActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// get caller and parse coords
			JButton caller = (JButton) e.getSource();
			String[] movestr = caller.getActionCommand().split(";");
			int[] move = new int[2];
			for (int i = 0; i < 2; i++)
				move[i] = Integer.parseInt(movestr[i]);
			
			// try to move, 
			//				if no space: inform and do nothing else
			//				else: refill, disable board, enable throw
			boolean hasMoved = cm.move(move[1]);
			if (!hasMoved) {
				JOptionPane.showMessageDialog(GameScreen.this,
						"This ghostbuster cannot go that further!\nPlease, click another one");
				return;
			} else {
				updateBoard();
				disableBoard();
				if (cm.isGameFinished()) {
					triggerGameFinish(cm.getCurrentDiscountCode());
					if (!hasContinueAl) {
						btnContinue.addActionListener(mw.getGameContinueAL());
						hasContinueAl = true;
					}
					if (cm.isMaxDiscount())
						triggerMaxDiscount();
				} else {
					btnThrow.setEnabled(true);
				}
			}
		}

	}
}
