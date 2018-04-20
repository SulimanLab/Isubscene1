import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.sun.glass.events.WindowEvent;

import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.*;

public class UIForURL {

	private JFrame frmIsubscene;
	private JTextField textField;
	Url u = new Url();
	private int NumberOfSearchs;
	static JProgressBar progressBar = new JProgressBar();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// launch(args);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIForURL window = new UIForURL();
					window.frmIsubscene.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws IOException
	 */

	public UIForURL() throws IOException {

		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException {
		String movieName = MangeTheFile.getTheMovieName();
		frmIsubscene = new JFrame();
		frmIsubscene.setFont(new Font("Lucida Bright", Font.BOLD, 12));
		frmIsubscene.setTitle("iSubscene");
		frmIsubscene.setBounds(100, 100, 576, 447);
		frmIsubscene.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmIsubscene.getContentPane().setLayout(null);
		// textField.getFocusTraversalKeys(1);
		// textField.getScrollableTracksViewportWidth();

		JList list = new JList();
		list.setBorder(new EmptyBorder(0, 0, 4, 0));
		// list.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(30,
		// 144, 255), new Color(30, 144, 255), new Color(30, 144, 255), new
		// Color(30, 144, 255)));

		list.setBounds(72, 98, 444, 246);

		JButton btnNewButton = new JButton("Search with extenstion");
		btnNewButton.setBounds(108, 61, 169, 36);
		// frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		progressBar.setFont(new Font("Lucida Grande", Font.PLAIN, 8));
		progressBar.setBounds(71, 346, 444, 29);

		frmIsubscene.getContentPane().add(progressBar);

		javax.swing.Timer t = new javax.swing.Timer(200, new ActionListener() {

			public void actionPerformed(ActionEvent e) {

//				progressBar.setValue(MangeTheFile.ti);
				if (progressBar.getValue() == 100)
					progressBar.setValue(0);

			}
		});

		JButton btnDownload = new JButton("Download");
		btnDownload.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		btnDownload.setBackground(Color.WHITE);
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// t.start();

				try {
					// MangeTheFile.ti = 30;
					// t.start();
					Url.mtf.changeDowloadLink(list.getSelectedIndex(), Url.getFp(), Url.getDd());
					// MangeTheFile.ti = 90;
					// t.start();
					Url.mtf.openFileWithVlc(MangeTheFile.getGlopalDis());

					// MangeTheFile.ti = 100;
					// t.start();

				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnDownload.setBounds(167, 371, 117, 29);
		// frmIsubscene.getContentPane().add(btnDownload);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(72, 98, 444, 246);

		// scrollPane.getViewport().add(list,
		// scrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		frmIsubscene.getContentPane().add(scrollPane);
		scrollPane.setViewportView(list);
		scrollPane.getVerticalScrollBar().setUnitIncrement(12);
		list.setSelectedIndex(0);

		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 576, 84);
		frmIsubscene.getContentPane().add(panel);
		panel.setLayout(null);
		textField = new JTextField() {

			public void addNotify() {
				super.addNotify();
				requestFocus();
			}
		};
		textField.setBounds(71, 16, 410, 36);
		panel.add(textField);
		textField.setColumns(10);
		if (panel.isFocusTraversable())
			textField.setText(MangeTheFile.getTheMovieName());

		// frame.getContentPane().add(list);

		JButton btnDefualtSearch = new JButton("Defualt Search");
		btnDefualtSearch.setFont(new Font("Lucida Bright", Font.BOLD, 13));
		btnDefualtSearch.setForeground(new Color(51, 255, 0));
		btnDefualtSearch.setBounds(423, 16, 141, 36);
		// panel.add(btnDefualtSearch);

		JLabel lblName = new JLabel("Title:");
		lblName.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblName.setForeground(Color.BLACK);
		lblName.setBounds(16, 23, 55, 23);
		panel.add(lblName);
		lblName.setBackground(new Color(192, 192, 192));

		JLabel lblNewLabel_1 = new JLabel("Search");
		lblNewLabel_1.setForeground(new Color(51, 255, 0));
		lblNewLabel_1.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(481, 15, 90, 36);
		lblNewLabel_1.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnDefualtSearch.doClick();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("Download");
		lblNewLabel.setFont(new Font("Lucida Bright", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(398, 371, 117, 35);
		frmIsubscene.getContentPane().add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(30, 144, 255));
		panel_1.setBounds(69, 96, 448, 250);
		// frmIsubscene.getContentPane().add(panel_1);
		lblNewLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				btnDownload.doClick();

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnDefualtSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (NumberOfSearchs != 0) {
					MangeTheFile.setSubsLinks();
					MangeTheFile.subsSize = 0;
					NumberOfSearchs = 0;
				}
				NumberOfSearchs++;
				String args[] = { textField.getText() };
				try {

					Url.main(args);

				} catch (IOException e1) {
					e1.printStackTrace();

				} catch (URISyntaxException e1) {
					e1.printStackTrace();

				}
				String s[] = u.getSubs();

				DefaultListModel<String> x = new DefaultListModel<String>();

				int size = 0;
				while (u.getSubs()[size] != null) {
					x.addElement(s[size++]);

				}
				progressBar.setValue(80);
				list.setModel(x);
				list.setSelectedIndex(0);
				progressBar.setValue(100);

			}
		});
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {

				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnDefualtSearch.doClick();
				}

				else if (e.isMetaDown() && e.getKeyCode() == 8)
					textField.setText("");
			}
		});

		// progressBar.

		// ScrollPane scrollPane = new ScrollPane();
		// scrollPane.setBounds(80, 98, 444, 245);
		// frame.getContentPane().add(scrollPane);
		// // scrollPane.setBounds(list.getX(), list.getY(), list.getWidth(),
		// // list.getHeight());
		// scrollPane.add(list, scrollPane.SCROLLBARS_AS_NEEDED);

		this.textField.requestFocus(true);
		this.textField.requestFocus();
		textField.setVisible(true);
		requestFocus();
		new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				textField.requestFocus();
			}
		};

		list.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					btnDownload.doClick();
			}
		});

	}

	public int getNumberOfSearchs() {
		return NumberOfSearchs;
	}

	public void requestFocus() {
		textField.requestFocusInWindow();
	}

	public void setNumberOfSearchs(int numberOfSearchs) {
		NumberOfSearchs = numberOfSearchs;
	}
}
