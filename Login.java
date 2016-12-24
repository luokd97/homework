import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login {
	private JFrame frame;
	private JPanel p;

	private int screenHeight, screenWidth;
	private JLabel accountLabel, passwordLabel;
	private JButton loginButton, exitButton;
	private JTextField accountText;
	private JPasswordField passwordText;

	public Login() {
		initFrame();
		addComponent();
		listen();

		frame.setVisible(true);
	}

	private void initFrame() {
		frame = new JFrame();
		Toolkit kit;
		Dimension screenSize;

		// get screen dimensions
		kit = Toolkit.getDefaultToolkit();
		screenSize = kit.getScreenSize();

		screenHeight = screenSize.height;
		screenWidth = screenSize.width;

		// set frame width,height and let platform pick screen location
		frame.setSize(screenWidth / 4, screenHeight / 4);
		frame.setLocationByPlatform(true);

		// set the frame others property
		frame.setResizable(false);
		frame.setTitle("Login");
		frame.setDefaultCloseOperation(3);
	}

	private void addComponent() {
		// add container
		p = new JPanel();
		frame.setContentPane(p);
		p.setLayout(null);

		// add components
		accountLabel = new JLabel("ÕËºÅ");
		passwordLabel = new JLabel("ÃÜÂë");
		accountText = new JTextField();
		passwordText = new JPasswordField();
		loginButton = new JButton("µÇÂ¼");
		exitButton = new JButton("ÍË³ö");

		int x = frame.getWidth() / 8;
		int y = frame.getHeight() / 8;

		accountLabel.setBounds(x, y, x, y);
		passwordLabel.setBounds(x, 3 * y, x, y);
		accountText.setBounds(3 * x, y, 4 * x, y);
		passwordText.setBounds(3 * x, 3 * y, 4 * x, y);
		loginButton.setBounds((3 / 2) * x, 5 * y, 2 * x, (3 / 2) * y);
		exitButton.setBounds(5 * x, 5 * y, 2 * x, (3 / 2) * y);

		p.add(accountLabel);
		p.add(passwordLabel);
		p.add(accountText);
		p.add(passwordText);
		p.add(loginButton);
		p.add(exitButton);
	}

	private void listen() {
		// listener
		exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		loginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				check();

			}
		});
		passwordText.addKeyListener(new KeyAdapter() {

			@Override
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10) {
					check();
				}

			}

		});
	}

	private void check() {
		if (accountText.getText().equals("admin") && new String(passwordText.getPassword()).equals("123456")) {
			JOptionPane.showMessageDialog(p, "µÇÂ¼³É¹¦£¡");
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(frame, "µÇÂ¼Ê§°Ü£¡ÇëÖØÐÂµÇÂ¼", "Failed", JOptionPane.ERROR_MESSAGE);
			frame.dispose();
			frame.setVisible(true);
			passwordText.requestFocus();
			passwordText.setText("");
		}

	}

	public static void main(String[] args) {
		new Login();

	}

}
