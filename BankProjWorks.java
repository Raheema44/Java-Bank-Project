package New;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JEditorPane;                     //THANK YOU SIR!!
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class BankProjWorks extends JFrame {
	private JPanel contentPane;
	private JTextField txtAcNumber;
	private JTextField txtName;
	private JTextField txtAcBalance;
	private JLabel lblNewLabel_2;
	private JTable table;
	private JScrollPane scrollPane;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BankProjWorks frame = new BankProjWorks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public BankProjWorks() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1600, 860);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bank Project");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(668, 27, 281, 47);
		lblNewLabel.setFont(new Font("Bauhaus 93", Font.PLAIN, 45));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Insert Data");
		lblNewLabel_1.setFont(new Font("Lucida Fax", Font.PLAIN, 25));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(75, 118, 157, 34);
		contentPane.add(lblNewLabel_1);
		
		txtAcNumber = new JTextField();
		txtAcNumber.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAcNumber.setText("Ac Number");
		txtAcNumber.setToolTipText("");
		txtAcNumber.setBounds(75, 194, 157, 34);
		contentPane.add(txtAcNumber);
		txtAcNumber.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtName.setText("Name");
		txtName.setColumns(10);
		txtName.setBounds(318, 194, 157, 34);
		contentPane.add(txtName);
		
		txtAcBalance = new JTextField();
		txtAcBalance.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtAcBalance.setText("Ac Balance");
		txtAcBalance.setColumns(10);
		txtAcBalance.setBounds(560, 194, 157, 34);
		contentPane.add(txtAcBalance);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertrows();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(255, 0, 0));
		btnNewButton.setFont(new Font("Lucida Fax", Font.BOLD, 25));
		btnNewButton.setBounds(833, 183, 157, 47);
		btnNewButton.setFocusable(false);
		contentPane.add(btnNewButton);
		
		lblNewLabel_2 = new JLabel("View Data");
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setFont(new Font("Lucida Fax", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(75, 311, 132, 24);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("Refresh Table");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selectrows();
			}
		});
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setBackground(new Color(255, 0, 0));
		btnNewButton_1.setFont(new Font("Lucida Fax", Font.PLAIN, 25));
		btnNewButton_1.setBounds(75, 370, 229, 36);
		btnNewButton_1.setFocusable(false);
		contentPane.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(82, 430, 1304, 358);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Lucida Fax", Font.PLAIN, 15));
		scrollPane.setViewportView(table);
	}
	public void connect() {
		try{ 
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys59","root","raheema3544@"); 			
			}
		catch(Exception e){
			System.out.println(e);} 
	}
	public void selectrows() {
		try {
			table.setModel(new DefaultTableModel());
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys59","root","raheema3544@"); 			
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from bank"); 
			ResultSetMetaData rsmd=rs.getMetaData();
			DefaultTableModel model=(DefaultTableModel)table.getModel();

			int cols=rsmd.getColumnCount();
			String[] colName=new String[cols];
			for(int i=0;i<cols;i++) {
				colName[i]=(rsmd.getColumnName(i+1)).toUpperCase();
			}
			model.setColumnIdentifiers(colName);
			String acno,acbalance,acname;
			while(rs.next()) {
				acno=rs.getString(1);
				acname=rs.getString(2);
				acbalance=rs.getString(3);
				String[] row= {acno,acname,acbalance};
				model.addRow(row);
			}
			stmt.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	public void insertrows() {
		try {
			Scanner sc=new Scanner(System.in);
			Class.forName("com.mysql.cj.jdbc.Driver"); 			
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sys59","root","raheema3544@"); 			

			Statement stmt=con.createStatement();
			int acno=Integer.parseInt(txtAcNumber.getText());
			String acname=txtName.getText();
			int amount=Integer.parseInt(txtAcBalance.getText());
			stmt.executeUpdate("insert into bank values("+acno+",'"+acname+"',"+amount+")"); 
			System.out.println("Row Inserted successfully!!");
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}
