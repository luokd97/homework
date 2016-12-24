import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class Notepad {
	private int screenHeight, screenWidth;
	private JFrame frame;
	private JPanel pane;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JMenu fileMenu;
	private JMenuItem newMenuItem, closeMenuItem, openMenuItem, saveMenuItem;
	private JFileChooser openFileChooser, saveFileChooser;
	private File inFile, outFile;

	public Notepad() {
		initFrame();
		addComponent();
		listen();

		frame.setVisible(true);
	}

	private void listen() {
		// listenMenuItem

		listenMenuItem(closeMenuItem);
		listenMenuItem(newMenuItem);
		listenMenuItem(openMenuItem);
		listenMenuItem(saveMenuItem);

		// listWindow
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				textArea.setSize(frame.getWidth(), frame.getHeight() - menuBar.getHeight());
			}
		});

	}

	private void addComponent() {
		// add JPanel
		pane = new JPanel();
		frame.setContentPane(pane);
		pane.setLayout(null);

		// add JMenuBar ,JMenu and JmenuItem
		menuBar = new JMenuBar();
		fileMenu = new JMenu("文件");
		newMenuItem = new JMenuItem("新建");
		openMenuItem = new JMenuItem("打开");
		saveMenuItem = new JMenuItem("保存");
		closeMenuItem = new JMenuItem("关闭");

		frame.setJMenuBar(menuBar);
		menuBar.add(fileMenu);
		fileMenu.add(newMenuItem);
		fileMenu.add(openMenuItem);
		fileMenu.add(saveMenuItem);
		fileMenu.addSeparator();
		fileMenu.add(closeMenuItem);

		// add JTextArea
		textArea = new JTextArea();
		textArea.setBounds(0, menuBar.getHeight(), frame.getWidth(), frame.getHeight() - menuBar.getHeight());
		pane.add(textArea);

	}

	private void listenMenuItem(JMenuItem menuItem) {
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == openMenuItem) {
					openFile(frame);
				} else if (e.getSource() == closeMenuItem) {
					frame.dispose();
				} else if (e.getSource() == newMenuItem) {
					new Notepad();
				} else if (e.getSource() == saveMenuItem) {
					saveFile();
				}

			}
		});

	}

	private void initFrame() {
		frame = new JFrame();

		Toolkit kit;
		Dimension screenSize;

		kit = Toolkit.getDefaultToolkit();
		screenSize = kit.getScreenSize();

		screenHeight = screenSize.height;
		screenWidth = screenSize.width;
		frame.setSize(screenWidth / 3, screenHeight / 3);
		frame.setLocationByPlatform(true);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("MyNotePad");

	}

	private void openFile(JFrame frame) {

		try {
			openFileChooser = new JFileChooser();
			openFileChooser.showOpenDialog(frame);
			inFile = openFileChooser.getSelectedFile();
			if (inFile != null) {

				FileReader fis = new FileReader(inFile);
				char[] c = new char[(int) inFile.length()];
				fis.read(c);
				textArea.setText(new String(c));
				fis.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void saveFile() {

		try {
			saveFileChooser = new JFileChooser();
			saveFileChooser.showSaveDialog(frame);
			outFile = saveFileChooser.getSelectedFile();

			if (outFile != null) {
				FileOutputStream fos = new FileOutputStream(outFile);
				fos.write(textArea.getText().getBytes());
				fos.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Notepad();
	}

}
