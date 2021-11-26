import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class Fenetre3 extends JFrame implements ActionListener{
	 JTable table;
	 DefaultTableModel dtm  = new DefaultTableModel(); 
	 JTextField txtRechercher;
	 JComboBox<String> Filliere;
	 JButton rechercher;
	 JButton fermer;
	 JButton supprimer;
	 JPanel tabContenaire;
	 JPanel contenaire;
	 
public Fenetre3() {
  	this.setSize(780,620);
    this.setTitle("Liste Etudiant");
    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    JPanel entete =new JPanel();
    contenaire=new JPanel();  
    JLabel lblNewLabel_1 = new JLabel("INFORMATIONS DES ETUDIANTS ");
    lblNewLabel_1.setForeground(new Color(105, 105, 105));
    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
    contenaire.add(lblNewLabel_1);
    tabContenaire=new JPanel();  
    tabContenaire.setBorder(BorderFactory.createTitledBorder("Liste des etudiants")); 
    JPanel rechContenaire=new JPanel();  
    rechContenaire.setBorder(BorderFactory.createTitledBorder("Rechereche")); 
    rechContenaire.setPreferredSize(new Dimension(400, 70));
    txtRechercher =new JTextField(15);
    rechercher =new JButton("Rechercher");
    JLabel lbl=new JLabel("Nom");
    rechercher.addActionListener(this);
    rechContenaire.add(lbl);
    rechContenaire.add(txtRechercher);
    rechContenaire.add(rechercher);
    
    JPanel filContenaire=new JPanel();  
    filContenaire.setBorder(BorderFactory.createTitledBorder("Filliere")); 
    filContenaire.setPreferredSize(new Dimension(280, 70));
    JLabel lblF=new JLabel("Filliere");
    filContenaire.add(lblF);
    String []Fil= {"SIGL","SMP","SMA","SME","All"};
    Filliere =new JComboBox<>(Fil);
    Filliere.addActionListener(this);
    filContenaire.add(Filliere);
    entete.add(filContenaire);
    entete.add(rechContenaire);
    dtm.addColumn("CNE"); 
    dtm.addColumn("Nom"); 
    dtm.addColumn("Prenom"); 
    dtm.addColumn("CIN"); 
    dtm.addColumn("Sexe"); 
    dtm.addColumn("Diplome"); 
    dtm.addColumn("Programmation"); 
    dtm.addColumn("Filliere"); 

    try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
		String req="select * from etudiant"; 
		PreparedStatement ps=con.prepareStatement(req); 
		 ResultSet res=ps.executeQuery();
		 ResultSetMetaData meta = res.getMetaData();
         int numberOfColumns = meta.getColumnCount();
         while (res.next())
         {
             Object [] rowData = new Object[numberOfColumns];
             for (int i = 0; i < rowData.length; ++i)
             {
                 rowData[i] = res.getObject(i+1);
             }
             dtm.addRow(rowData);
         }
	} catch (Exception e) {
		e.printStackTrace();
	}
	 table = new JTable(dtm); 	
	 JScrollPane sp1=new JScrollPane(table);   
	 sp1.setPreferredSize(new Dimension(730, 400));
     fermer=new JButton("Fermer");
     supprimer=new JButton("Supprimer");
     fermer.addActionListener(this);
     supprimer.addActionListener(this);
     tabContenaire.add(sp1);
     contenaire.add(entete,BorderLayout.NORTH);
     contenaire.add(tabContenaire,BorderLayout.CENTER);
     contenaire.add(fermer,BorderLayout.SOUTH);
     contenaire.add(supprimer,BorderLayout.SOUTH);

     this.setContentPane(contenaire);
     this.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==Filliere) {
	    try {
	        dtm.getDataVector().clear();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
			ResultSet res;
			ResultSetMetaData meta = null;
			if(Filliere.getSelectedItem().toString().equals("All")) {
				String req="select *  from  etudiant"; 
				PreparedStatement ps=con.prepareStatement(req); 
			    res=ps.executeQuery();
			    meta = res.getMetaData();
			}else {
				String req="select *  from  etudiant where  codeF like ?"; 
				PreparedStatement ps=con.prepareStatement(req); 
				ps.setString(1,"%"+Filliere.getSelectedItem().toString()+"%");
			    res=ps.executeQuery();
			    meta = res.getMetaData();
			}
	         int numberOfColumns = meta.getColumnCount();
	         while (res.next())
	         {
	             Object [] rowData = new Object[numberOfColumns];
	             for (int i = 0; i < rowData.length; ++i)
	             {
	                 rowData[i] = res.getObject(i+1);
	             }
	             dtm.addRow(rowData);
	         }
	         table = new JTable(dtm); 	
			 repaint();
	    }catch (Exception ex) {
		}
	}
	if(e.getSource()==fermer)
    this.dispose();
	if(e.getSource()==rechercher) {
	    try {
	        dtm.getDataVector().clear();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
			String req="select *  from  etudiant where  nom like ?"; 
			PreparedStatement ps=con.prepareStatement(req); 
			ps.setString(1,"%"+txtRechercher.getText()+"%");
			 ResultSet res=ps.executeQuery();
			 ResultSetMetaData meta = res.getMetaData();
	         int numberOfColumns = meta.getColumnCount();
	         while (res.next())
	         {
	             Object [] rowData = new Object[numberOfColumns];
	             for (int i = 0; i < rowData.length; ++i)
	             {
	                 rowData[i] = res.getObject(i+1);
	             }
	             dtm.addRow(rowData);
	         }
	         table = new JTable(dtm); 	
			    repaint();

	         this.setContentPane(contenaire);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	if(e.getSource()==supprimer) {
		if(table.getSelectedRow()==-1) {
			JOptionPane.showMessageDialog(this,"Il faut selectionner une ligne","Erreur!!!",JOptionPane.ERROR_MESSAGE);
							return;
						}
			Spprimer_Etudiant((String)table.getValueAt(table.getSelectedRow(), 0));
			dtm.removeRow(table.getSelectedRow());
	}
}
public void Spprimer_Etudiant(String nom){
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
		Statement st=con.createStatement();
		st.executeUpdate("delete from Etudiant where cne='"+nom+"'");
	} catch (Exception e) {
		e.printStackTrace();
	}
}
}