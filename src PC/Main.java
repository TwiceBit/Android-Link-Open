import java.awt.EventQueue;
import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	protected Thread server = null;
	private JFrame frame;
	private final JLabel ip = new JLabel("IP:");
	private JTextField textField;
	private JTextField textField_1;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		ip.setBounds(32, 30, 42, 33);
		frame.getContentPane().add(ip);

		JLabel port = new JLabel("Port:");
		port.setBounds(32, 89, 42, 33);
		frame.getContentPane().add(port);

		textField = new JTextField();
		textField.setBounds(81, 37, 160, 19);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("8888");
		textField_1.setBounds(81, 96, 160, 19);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (btnStart.getText().equals("Stop")) {
					server.stop();
					btnStart.setText("Start");

				} else {
					Scanner src = new Scanner(textField_1.getText());
					if (src.hasNextInt()) {
						server = new API(src.nextInt());
						server.start();
						btnStart.setText("Stop");
					}

				}
			}
		});
		btnStart.setBounds(284, 34, 70, 25);
		frame.getContentPane().add(btnStart);
		frame.setBounds(100, 100, 374, 157);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		new Timer(1000 * 10) {

			@Override
			public void TimerBlock() {

				try {
					textField.setText(Inet4Address.getLocalHost().getHostAddress());
				} catch (UnknownHostException e) {

					e.printStackTrace();
				}

			}
		};
	}
}
