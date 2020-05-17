package paket;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class MainWindow {

	private ArrayList<Person> person = new ArrayList<Person>();

	private JFrame frame;
	static final int breite = 810, hoehe = 720;
	private LabelMain labelmain;
	static JComboBox<String> deletebox = new JComboBox<String>();

	/*
	 * Variables to create a new person
	 */
	private JTextField feldFirstname, feldSecondname;
	private JSpinner spinnerAge;
	private JRadioButton male, female;

	// Buttons
	static Button delete;

	// Variables for the table
	private String[] info;
	private Object[][] data;
	private JTable table;
	private JScrollPane scrollpane;
	private DefaultTableModel tablemodel;

	public MainWindow() {
		table = new JTable();
		scrollpane = new JScrollPane(table);
	}

	public void createMainWindow() {

		delete = new Button(Gui.breite / 2 - 50 + 490, 60, 100, 35, false);

		frame = new JFrame("edit Database");
		frame.setSize(breite, hoehe);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setBackground(new Color(11, 55, 56));
		frame.setResizable(true);

		labelmain = new LabelMain();
		labelmain.setBounds(0, 0, breite, hoehe);
		labelmain.setVisible(true);
		labelmain.setLayout(null);
		labelmain.addMouseMotionListener(new MouseHandler());
		labelmain.addMouseListener(new MouseHandler());
		frame.add(labelmain);

		createTable();
		erstelleTabelle();
		deletePerson();
		createKomponentenPlus();

		frame.setVisible(true);

	}

	public void createKomponentenPlus() {
		feldFirstname = new JTextField();
		feldFirstname.setBounds(50, hoehe / 2, 150, 30);
		feldFirstname.setVisible(true);
		labelmain.add(feldFirstname);

		feldSecondname = new JTextField();
		feldSecondname.setBounds(50, hoehe / 2 + 80, 150, 30);
		feldSecondname.setVisible(true);
		labelmain.add(feldSecondname);

		SpinnerNumberModel spinnerModel = new SpinnerNumberModel();

		spinnerModel.setMinimum(1);
		spinnerModel.setMaximum(120);
		spinnerModel.setStepSize(1);
		spinnerModel.setValue(1);
		spinnerAge = new JSpinner(spinnerModel);

		spinnerAge.setVisible(true);
		spinnerAge.setBounds(85, hoehe / 2 + 160, 70, 30);
		spinnerAge.setFont(new Font("Arial", Font.BOLD, 16));
		labelmain.add(spinnerAge);

		male = new JRadioButton();
		male.setText("male");
		male.setSelected(false);
		male.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (male.isSelected()) {
					female.setSelected(false);
				}
			}
		});
		male.setBounds(68, hoehe / 2 + 240, 100, 30);
		labelmain.add(male);

		female = new JRadioButton();
		female.setText("female");
		female.setSelected(false);
		female.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (female.isSelected()) {
					male.setSelected(false);
				}
			}
		});
		female.setBounds(68, hoehe / 2 + 270, 100, 30);
		labelmain.add(female);

		JLabel plusLabel = new JLabel();
		ImageIcon iconplus = new ImageIcon("images/plus.png");
		plusLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String vornameString = feldFirstname.getText();
				String nachnameString = feldSecondname.getText();
				int alterInt = (int) spinnerAge.getValue();
				String genderString = null;
				if (male.isSelected()) {
					genderString = "male";
				} else if (female.isSelected()) {
					genderString = "female";
				}
				if (genderString != null) {
					//person.add(new Person(vornameString, nachnameString, alterInt, genderString));

					MainWindow window = new MainWindow();
					erstelleTabelle();
					deleteAllPerson();
					deletePerson();

				} else {
					System.out.println("Person konnte nicht hinzugefügt werden");
				}

				// add person to the database
				int rs;
				Statement stmt = null;

				try {
					Class.forName("com.mysql.jdbc.Driver");

					Connection con = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/admin?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
							"root", "IronMan03");

					String query = "insert into person(firstName, secondName, age, gender)values(\"" + vornameString
							+ "\"" + ", " + "\"" + nachnameString + "\"" + ", " + alterInt + ", " + "\"" + genderString
							+ "\"" + ");";

					stmt = con.createStatement();
					rs = stmt.executeUpdate(query);
					System.out.println("Person wurde erfolgreich in der Datenbank hinzugefügt");
				} catch (Exception e1) {
					e1.printStackTrace();
				} finally {
					try {
						if (stmt != null) {
							stmt.close();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}

			}
		});
		plusLabel.setIcon(iconplus);
		plusLabel.setVisible(true);
		plusLabel.setBounds(230, 440, 100, 100);
		labelmain.add(plusLabel);

	}

	public void deletePerson() {

		for (int i = 0; i < person.size(); i++) {
			deletebox.addItem((i + 1) + ":" + person.get(i).getSecondName() + ":" + person.get(i).getFirstName());
		}
		deletebox.setBounds(breite / 2 - 75 + 160, 60, 150, 35);
		deletebox.setFont(new Font("Arial", Font.BOLD, 16));
		deletebox.setVisible(true);
		labelmain.add(deletebox);

	}

	public void deleteAllPerson() {
		for (int i = 1; i < 1; i++) {
			System.out.println("Einmal");
		}

		for (int i = 0; i < 1; i++) {
			System.out.println("Zweimal");
		}

		for (int i = 0; i < person.size(); i++) {
			deletebox.removeItemAt(i);
		}
	}

	public void deletePersonListener() {
		String indexPersonString = (String) deletebox.getSelectedItem();
		String indexPerson = indexPersonString.split(":")[1];

		// delete the person with the secondName from the Database
		int rs;
		Statement stmt = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/admin?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
					"root", "IronMan03");

			String query = "delete from person where secondName = \"" + indexPerson + "\";";

			stmt = con.createStatement();
			rs = stmt.executeUpdate(query);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void createTable() {
		// find data from the database
		ResultSet rs = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/admin?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin",
					"root", "IronMan03");

			String query = "select * from person";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("id");
				String firstNameDatabase = rs.getString("firstName");
				String secondNameDatabase = rs.getString("secondName");
				String ageDatabase = rs.getString("age");
				String genderDatabase = rs.getString("gender");

				int ageIntDatabase = Integer.parseInt(ageDatabase);

				person.add(new Person(id, firstNameDatabase, secondNameDatabase, ageIntDatabase, genderDatabase));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void erstelleTabelle() {
		// Tabelle erstellen

		info = new String[] { "ID", "firstName", "secondName", "Age", "Gender" };
		data = new Object[person.size()][];

		for (int i = 0; i < data.length; i++) {
			data[i] = new Object[] { this.person.get(i).getId(), this.person.get(i).getFirstName(),
					this.person.get(i).getSecondName(), this.person.get(i).getAge(), this.person.get(i).getGender() };
		}

		table.getTableHeader().setBackground(new Color(108, 144, 145));
		table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 15));
		table.setRowHeight(25);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Arial", Font.BOLD, 15));

		tablemodel = new DefaultTableModel(data, info);
		table.setModel(tablemodel);
		tablemodel.fireTableDataChanged();

		scrollpane.setBounds(50, 50, 400, 240);
		labelmain.add(scrollpane);

		labelmain.repaint();
	}

}
