package paket;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Gui {

	// Variables for gui
	private JFrame frame;
	static final int breite = 430, hoehe = 330;
	private Label label;

	private JTextField usernamefeld;
	private JPasswordField passwordfeld;
	private JButton login;
	private Connection con = null;

	private String data = null;
	private String usernameDatabase = null;
	private String passwordDatabase = null;

	public void createWindow() {

		frame = new JFrame();
		frame.setTitle("mySQL Database");
		frame.getContentPane().setBackground(new Color(207, 207, 207));
		frame.setResizable(false);
		frame.setSize(breite, hoehe);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);

		label = new Label();
		label.setBounds(0, 0, breite, hoehe);
		label.setVisible(true);
		label.setLayout(null);
		frame.add(label);

		// Textfields
		usernamefeld = new JTextField();
		usernamefeld.setBounds(breite / 2, 50, 150, 30);
		usernamefeld.setVisible(true);
		label.add(usernamefeld);

		passwordfeld = new JPasswordField();
		passwordfeld.setBounds(breite / 2, 150, 150, 30);
		passwordfeld.setVisible(true);
		label.add(passwordfeld);

		// Button
		login = new JButton("Login");
		login.setVisible(true);
		login.setBounds(breite / 2 - 50, hoehe - 40 - 70, 100, 40);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String usernameString = usernamefeld.getText();
				@SuppressWarnings("deprecation")
				String passwordString = passwordfeld.getText();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/admin?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
							"root", "IronMan03");

					showAll("user");

					if (usernameDatabase.equals(usernameString) && passwordDatabase.equals(passwordString)) {
						// dann bekommt man die Admin-Rechte...
						MainWindow mainwindow = new MainWindow();
						mainwindow.createMainWindow();
					} else {
						System.out.println("Falsche Eingabe");
						System.exit(0);
					}

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

		});
		label.add(login);

		frame.setVisible(true);

		JOptionPane.showMessageDialog(null, "Login as Administrator");
	}

	public void showAll(String tableName) {
		ResultSet rs = null;
		Statement stmt = null;
		try {
			String query = "SELECT * FROM " + tableName;
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			int columns = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columns; i++) {

					String id = rs.getString(1);
					String user = rs.getString(2);
					String password = rs.getString(3);

					data = id + ":" + user + ":" + password;

				}
			}
			// init correct Strings from the table
			usernameDatabase = data.split(":")[1];
			passwordDatabase = data.split(":")[2];

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			try {
				if (rs != null && stmt != null) {
					rs.close();
					stmt.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

}
