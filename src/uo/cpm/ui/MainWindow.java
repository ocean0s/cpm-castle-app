package uo.cpm.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import uo.cpm.model.Castle;
import uo.cpm.service.CastleManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final int FIXED_HEIGHT = 400;
	private CastleManager cm = new CastleManager();
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnApp;
	private JMenu mnHelp;
	private JMenuItem mntmContents;
	private JSeparator separator;
	private JMenuItem mntmAbout;
	private JMenuItem mntmExit;
	private List<Castle> castles;
	private int carouselCastleIndex;
	private MainMenu mainMenu = new MainMenu();
	private GameScreen gameScreen /* = new GameScreen(cm, this)*/;
	private SaveDiscount sd;
	private ListCastles lc = new ListCastles(cm);
	private ReservationForm rf;
	private JSeparator separator_1;
	private JMenuItem mntmRestart;
	private HelpBroker hb;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/ESC001.png")));
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			throw new RuntimeException("Fatal error in the initialization of the Look and Feel.");
		}
		lc = new ListCastles(cm);
//		gameScreen = new GameScreen(cm, this);
		mainMenu = new MainMenu();
		setBounds(100, 100, 1101, 753);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setMinimumSize(new Dimension(941, 720));
		setTitle("Crazy Castles: Reservation App");
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(mainMenu, "mainMenu");
//		contentPane.add(gameScreen, "gameScreen");
		contentPane.add(lc, "listCastles");
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (checkExit())
					System.exit(0);
			}
		});
		castles = cm.loadCastles();
		carouselCastleIndex = -1;
		triggerCarousel(true);
		addMainMenuEvents();
		addListCastlesEvents();
		loadHelp();
	}

	private void showGame() {
		gameScreen = new GameScreen(cm, this);
		contentPane.add(gameScreen, "gameScreen");
		hb.enableHelp(gameScreen, "play", hb.getHelpSet());
	}
	
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnApp());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnApp() {
		if (mnApp == null) {
			mnApp = new JMenu("App");
			mnApp.setMnemonic('A');
			mnApp.add(getMntmRestart());
			mnApp.add(getSeparator_1());
			mnApp.add(getMntmExit());
		}
		return mnApp;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('H');
			mnHelp.add(getMntmContents());
			mnHelp.add(getSeparator());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
		}
		return mntmContents;
	}

	private JSeparator getSeparator() {
		if (separator == null) {
			separator = new JSeparator();
		}
		return separator;
	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
			mntmAbout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(contentPane,
							"Crazy Castles booking app by Alfonso Alonso Menéndez." + "\nUniversity of Oviedo, 2024.");
				}
			});
		}
		return mntmAbout;
	}

	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkExit())
						System.exit(0);
				}
			});
		}
		return mntmExit;
	}

	private JSeparator getSeparator_1() {
		if (separator_1 == null) {
			separator_1 = new JSeparator();
		}
		return separator_1;
	}

	private JMenuItem getMntmRestart() {
		if (mntmRestart == null) {
			mntmRestart = new JMenuItem("Restart");
			mntmRestart.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
			mntmRestart.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (checkRestart()) {
						if (rf != null) {
							rf.dispose();
							rf = null;
						}
						resetMenuBar();
						cm.reset();
						((CardLayout) contentPane.getLayout()).show(contentPane, "mainMenu");
					}
				}

				private boolean checkRestart() {
					return (JOptionPane.showConfirmDialog(MainWindow.this, "Are you sure you want to restart the app?\n"
							+ "All progress will be lost!") == JOptionPane.YES_OPTION);
				}
			});
		}
		return mntmRestart;
	}

	void setListCastlesButton() {
		getRootPane().setDefaultButton(lc.getBtnSelect());
	}
	
	void resetMenuBar() {
		setJMenuBar(null);
		menuBar = null;
		setJMenuBar(getMenuBar_1());
		validate();
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
		hb.enableHelpKey(getRootPane(), "welcome", hs);
		hb.enableHelpOnButton(getMntmContents(), "welcome", hs);
		hb.enableHelp(lc, "castles", hs);
//		hb.enableHelp(gameScreen, "play", hs);
		hb.enableHelp(mainMenu, "welcome", hs);
	}

	private boolean checkExit() {
		int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to leave the app?");
		if (response == JOptionPane.YES_OPTION)
			return true;
		return false;
	}

	private void addListCastlesEvents() {
		lc.getBtnBack().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cm.reset();
				((CardLayout) contentPane.getLayout()).show(contentPane, "mainMenu");
				getRootPane().setDefaultButton(null);
			}
		});
		lc.getBtnSelect().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(MainWindow.this,
						"Are you over 18 years old to make the reservation?") == JOptionPane.YES_OPTION) {
					cm.selectCastle(lc.getSelectedCastle());
					showReservationForm();
				}
			}
		});
	}

	private void showReservationForm() {
		rf = new ReservationForm(this.cm, this);
		rf.setJMenuBar(getMenuBar_1());
		rf.setLocationRelativeTo(this);
		rf.setModal(true);
		rf.setVisible(true);
	}

	private void addMainMenuEvents() {
		mainMenu.getBtnNext().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				triggerCarousel(true);
			}
		});
		mainMenu.getBtnPrevious().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				triggerCarousel(false);
			}
		});
		mainMenu.getBtnPlay().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showGame();
				((CardLayout) contentPane.getLayout()).show(contentPane, "gameScreen");
				getRootPane().setDefaultButton(gameScreen.getBtnThrow());
			}
		});
		mainMenu.getBtnSeeCastles().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "listCastles");
				getRootPane().setDefaultButton(lc.getBtnSelect());
			}
		});
	}

	/**
	 * Forces images to a fixed width with variable height to keep aspect ratio and
	 * activates the carousel.
	 * 
	 * @param isForward
	 */
	private void triggerCarousel(boolean isForward) {
		if (isForward) {
			carouselCastleIndex++;
			if (carouselCastleIndex >= castles.size())
				carouselCastleIndex = 0;
		} else {
			carouselCastleIndex--;
			if (carouselCastleIndex < 0)
				carouselCastleIndex = castles.size() - 1;
		}
		ImageIcon img = new ImageIcon(
				getClass().getResource("/img/" + castles.get(carouselCastleIndex).getCode() + ".png"));
		int width = img.getIconWidth() * FIXED_HEIGHT / img.getIconHeight();
		;
		int height = FIXED_HEIGHT;
		mainMenu.getLblImage_1()
				.setIcon(new ImageIcon(img.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH)));
		mainMenu.getLblCastleName().setText(castles.get(carouselCastleIndex).getName());
	}

	ActionListener getGameContinueAL() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cm.hasDiscount()) {
					// Show option pane offering to save the discount
					int option = JOptionPane.showConfirmDialog(rootPane,
							"You have obtained a discount code.\nDo you want to save it?\n"
									+ "(Note: if you have already associated a discount to your DNI, "
									+ "this action will not be performed until you have made use of the previous discount code)");
					if (option == JOptionPane.NO_OPTION) {
						((CardLayout) contentPane.getLayout()).show(contentPane, "listCastles");
					} else if (option == JOptionPane.YES_OPTION) {
						processSaveDiscount();
					}
				} else {
					((CardLayout) contentPane.getLayout()).show(contentPane, "listCastles");
				}
			}

		};
	}

	private void processSaveDiscount() {
		sd = new SaveDiscount(cm, this);
		sd.setLocationRelativeTo(MainWindow.this);
		sd.setVisible(true);
		sd.setAlwaysOnTop(true);
		sd.getBtnBookNow().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				((CardLayout) contentPane.getLayout()).show(contentPane, "listCastles");
				sd.dispose();
			}
		});
	}
}
