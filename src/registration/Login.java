package registration;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.text.DefaultHighlighter;

import tcsmp.TCSMPClient;
import tcsmp.TCSMPClientSession;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	/**
	 * @author CHalHouB
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	static int adjustWidth = 37;
	static int adjustHeight = 38;
	private JTextField textField;
	private JPasswordField confirmField;
	private char passwordChar;
	private char passwordChar1;
	private JPasswordField passwordField;
	private JLabel lblSignUp;
	private JLabel loginLabel;
	private JLabel passlabel;
	private JLabel confirmlabel;
	private JLabel visiblelabel;
	/**
	 * Launch the application.
	 */

	protected Socket tcsmpSocket;
	protected DataInputStream in;
	protected DataOutputStream out;

    static HashMap<String, Integer> tcsmpdns;
	
	public static void main(String[] args) {
		try {
			//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {

		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setAutoRequestFocus(false);
		setLocationRelativeTo(null);
		setTitle("Login");
		setSize(this.getWidth() + adjustWidth, this.getHeight() + adjustHeight);

		tcsmpdns = new HashMap<String, Integer>();
		tcsmpdns.put("BINIOU.com", 1998);
		tcsmpdns.put("POUET.com", 1999);
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////textField/////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (textField.getText().toString().equals("")) {
					textField.setText("Enter your NAME");
					textField.setForeground(Color.GRAY);
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (textField.getText().toString().equals("Enter your NAME")) {
					textField.setText("");
					textField.setHighlighter(new DefaultHighlighter());
					textField.setForeground(Color.BLACK);
				}
			}
		});
		textField.setText("Enter your NAME");
		textField.setHighlighter(null);
		textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textField.setForeground(Color.GRAY);
		textField.setBounds(200, 65, 400, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////passwordField/////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		passwordField = new JPasswordField();
		passwordField.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (passwordField.getText().toString().equals("")) {
					passwordField.setEchoChar((char) 0);
					passwordField.setText("Enter your PASSWORD");
					passwordField.setForeground(Color.GRAY);
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (passwordField.getText().toString().equals("Enter your PASSWORD")) {
					passwordField.setEchoChar((char) 0);
					passwordField.setText("");
					passwordField.setHighlighter(new DefaultHighlighter());
					passwordField.setForeground(Color.BLACK);
					passwordField.setEchoChar(passwordChar);
				}
			}
		});
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setHighlighter(null);
		passwordChar = passwordField.getEchoChar();
		passwordField.setEchoChar((char) 0);
		passwordField.setText("Enter your PASSWORD");
		passwordField.setForeground(Color.GRAY);
		passwordField.setBounds(200, 108, 400, 30);
		contentPane.add(passwordField);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////confirmField/////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		confirmField = new JPasswordField();
		confirmField.setVisible(false);
		confirmField.addFocusListener(new FocusAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void focusLost(FocusEvent e) {
				if (confirmField.getText().toString().equals("")) {
					confirmField.setEchoChar((char) 0);
					confirmField.setText("Confirm your PASSWORD");
					confirmField.setForeground(Color.GRAY);
				}
			}

			@SuppressWarnings("deprecation")
			@Override
			public void focusGained(FocusEvent e) {
				if (confirmField.getText().toString().equals("Confirm your PASSWORD")) {
					confirmField.setEchoChar((char) 0);
					confirmField.setText("");
					confirmField.setHighlighter(new DefaultHighlighter());
					confirmField.setForeground(Color.BLACK);
					confirmField.setEchoChar(passwordChar1);
				}
			}
		});
		confirmField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		confirmField.setHighlighter(null);
		passwordChar1 = confirmField.getEchoChar();
		confirmField.setEchoChar((char) 0);
		confirmField.setText("Confirm your PASSWORD");
		confirmField.setForeground(Color.GRAY);
		confirmField.setBounds(200, 151, 400, 30);
		contentPane.add(confirmField);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////btnLogin///////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//System.out.println(textField.getText().toString());
					//System.out.println(passwordField.getText().toString());

					TCSMPClientSession tcsmp = new TCSMPClientSession("localhost", tcsmpdns.get(getDomain(textField.getText().toString())));
					boolean done = tcsmp.register(textField.getText().toString(), passwordField.getText().toString());
					if(done) {
						TCSMPClient frame = new TCSMPClient(textField.getText().toString(), passwordField.getText().toString());
						frame.setVisible(true);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				dispose();
			}
		});
		btnLogin.setBounds(200, 223, 150, 50);
		contentPane.add(btnLogin);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////////btnCancel//////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(450, 223, 150, 50);
		contentPane.add(btnCancel);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////lblSignUp/////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		lblSignUp = new JLabel("Sign up ?");
		lblSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblSignUp.setForeground(Color.CYAN);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblSignUp.setForeground(Color.BLUE);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if (lblSignUp.getText().toString().equals("Sign up ?")) {
					contentPane.requestFocusInWindow();
					lblSignUp.setText("Login ?");
					confirmField.setVisible(true);
					confirmlabel.setVisible(true);
					btnLogin.setText("OK");
				} else {
					contentPane.requestFocusInWindow();
					lblSignUp.setText("Sign up ?");
					confirmField.setVisible(false);
					confirmlabel.setVisible(false);
					btnLogin.setText("LOGIN");
				}

			}
		});
		lblSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSignUp.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSignUp.setForeground(Color.BLUE);
		lblSignUp.setBounds(705, 321, 65, 19);
		contentPane.add(lblSignUp);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////loginLabel//////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		loginLabel = new JLabel("");
		ImageIcon login_logo = new ImageIcon(
				new ImageIcon("C:\\Users\\Chalhoub\\eclipse-workspace\\TCSMPProject\\src\\assets\\login.png").getImage()
						.getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginLabel.setIcon(login_logo);
		loginLabel.setBounds(158, 65, 35, 35);
		contentPane.add(loginLabel);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////passlabel/////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		passlabel = new JLabel("");
		ImageIcon pass_logo = new ImageIcon(
				new ImageIcon("C:\\Users\\Chalhoub\\eclipse-workspace\\TCSMPProject\\src\\assets\\password.png").getImage()
						.getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		passlabel.setHorizontalAlignment(SwingConstants.CENTER);
		passlabel.setIcon(pass_logo);
		passlabel.setBounds(158, 108, 35, 35);
		contentPane.add(passlabel);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////confirmlabel/////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		confirmlabel = new JLabel("");
		ImageIcon confirm_logo = new ImageIcon(
				new ImageIcon("C:\\Users\\Chalhoub\\eclipse-workspace\\TCSMPProject\\src\\assets\\confirm.png").getImage()
						.getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		confirmlabel.setVisible(false);
		confirmlabel.setIcon(confirm_logo);
		confirmlabel.setHorizontalAlignment(SwingConstants.CENTER);
		confirmlabel.setBounds(158, 151, 35, 35);
		contentPane.add(confirmlabel);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////visiblelabel////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		visiblelabel = new JLabel("");
		ImageIcon visible_logo = new ImageIcon(
				new ImageIcon("C:\\Users\\Chalhoub\\eclipse-workspace\\TCSMPProject\\src\\assets\\visible.png").getImage()
						.getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		ImageIcon invisible_logo = new ImageIcon(
				new ImageIcon("C:\\Users\\Chalhoub\\eclipse-workspace\\TCSMPProject\\src\\assets\\invisible.png").getImage()
						.getScaledInstance(35, 35, Image.SCALE_DEFAULT));
		visiblelabel.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mousePressed(MouseEvent e) {
				if (!passwordField.getText().toString().equals("Enter your PASSWORD")) {
					visiblelabel.setIcon(visible_logo);
					passwordField.setEchoChar((char) 0);
				}
				if (!confirmField.getText().toString().equals("Confirm your PASSWORD")) {
					visiblelabel.setIcon(visible_logo);
					confirmField.setEchoChar((char) 0);
				}
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseReleased(MouseEvent e) {
				if (!passwordField.getText().toString().equals("Enter your PASSWORD")) {
					visiblelabel.setIcon(invisible_logo);
					passwordField.setEchoChar(passwordChar);
				}
				if (!confirmField.getText().toString().equals("Confirm your PASSWORD")) {
					visiblelabel.setIcon(invisible_logo);
					confirmField.setEchoChar(passwordChar1);
				}
			}
		});
		
		visiblelabel.setIcon(invisible_logo);
		visiblelabel.setHorizontalAlignment(SwingConstants.CENTER);
		visiblelabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		visiblelabel.setBounds(612, 104, 35, 35);
		contentPane.add(visiblelabel);
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////////////////////frame_logo/////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		ImageIcon frame_logo = new ImageIcon("C:\\Users\\Chalhoub\\eclipse-workspace\\TCSMPProject\\src\\assets\\frameicon.png");
		setIconImage(frame_logo.getImage());
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
	}
	static String getDomain(String mail) {
		String[] domain = mail.split("@");
		return domain[1];
	}

}
