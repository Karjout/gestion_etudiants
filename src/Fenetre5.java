import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.BorderFactory;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;

public class Fenetre5 extends JFrame implements ActionListener{
	 JPanel tabContenaire;
	 JPanel contenaire;
	 JPanel entete =new JPanel();
	 JTable table;
	 DefaultTableModel dtm  = new DefaultTableModel(); 
	 JButton fermer;
	 JButton ajouter;
	 JButton modifier;
	 JComboBox<String>Module=new JComboBox<String>();
	 JLabel pr=new JLabel("");
public Fenetre5() {
  	this.setSize(550,580);
    this.setTitle("Liste Etudiant");
    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    contenaire=new JPanel(); 
    JLabel lblNewLabel_1 = new JLabel("GESTION DES NOTES ");
    lblNewLabel_1.setForeground(new Color(105, 105, 105));
    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
    contenaire.add(lblNewLabel_1);
    //------------------------------------------------------------
    JPanel panelM = new JPanel();
    panelM.setBorder(BorderFactory.createTitledBorder("Module")); 
    JLabel f=new JLabel("Module");
    f.setPreferredSize(new Dimension(100, 30));
    try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
		 String req="select codeM from Module"; 
		 PreparedStatement ps=con.prepareStatement(req); 
		 ResultSet res=ps.executeQuery();
         while (res.next())
         {
        	Module.addItem(res.getString(1));
         }
 	    Module.setPreferredSize(new Dimension(100, 30));
	} catch (Exception e) {
		e.printStackTrace();
	}
    panelM.add(f);
    panelM.add(Module);
    panelM.setPreferredSize(new Dimension(240, 70));
    Module.addActionListener(this);
    entete.add(panelM,BorderLayout.NORTH);
    //-------------------------------------------------------------
    JPanel panelP = new JPanel();
    panelP.setBorder(BorderFactory.createTitledBorder("Professeur")); 
    JLabel r=new JLabel("Professeur :");
	r.setPreferredSize(new Dimension(100, 30));
    try {
		 Class.forName("com.mysql.jdbc.Driver");
		 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
		 String req="select codeM from Module where codeM+'"+Module.getSelectedItem().toString()+"'"; 
		 PreparedStatement ps=con.prepareStatement(req); 
		 ResultSet res=ps.executeQuery();
         while (res.next())
         {
        	 pr.setText(res.getString(1));
         }
  	   pr.setPreferredSize(new Dimension(100, 30));

	} catch (Exception e) {
		e.printStackTrace();
	}
    panelP.setPreferredSize(new Dimension(240, 70));
    panelP.add(r);
    panelP.add(pr);
    entete.add(panelP,BorderLayout.NORTH);
    //-------------------------------------------------------------
    tabContenaire=new JPanel();  
    tabContenaire.setBorder(BorderFactory.createTitledBorder("Liste des etudiants")); 
    dtm.addColumn("CNE"); 
    dtm.addColumn("Nom"); 
    dtm.addColumn("Module"); 
    dtm.addColumn("Note"); 
	 table = new JTable(dtm); 	
	 JScrollPane sp1=new JScrollPane(table);   
	 sp1.setPreferredSize(new Dimension(470, 350));
     fermer=new JButton("Fermer");
     ajouter=new JButton("Ajouter");
     fermer.addActionListener(this);
     ajouter.addActionListener(this);
     tabContenaire.add(sp1);
     contenaire.add(entete,BorderLayout.CENTER);
     contenaire.add(tabContenaire,BorderLayout.CENTER);
     contenaire.add(ajouter,BorderLayout.SOUTH);
     contenaire.add(fermer,BorderLayout.SOUTH);
     this.setContentPane(contenaire);
     this.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource()==fermer) {
		this.dispose();
	}
	if(e.getSource()==Module){
        dtm.getDataVector().clear();
        try {
    		Class.forName("com.mysql.jdbc.Driver");
    		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
    		String req="select distinct etudiant.CNE,nom,codeM,note__ from etudiant,note_ where etudiant.cne=note_.cne and codeM='"+Module.getSelectedItem().toString()+"'"; 
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
                 String req1="select Prof from Module where codeM='"+Module.getSelectedItem().toString()+"'"; 
        		 PreparedStatement ps1=con.prepareStatement(req1); 
        		 ResultSet res1=ps1.executeQuery();
                 while (res1.next())
                 {
                	 pr.setText(res1.getString(1));
                 }
        		 repaint();

             }
    	} catch (Exception e1) {
    		e1.printStackTrace();
    	}
    	 table = new JTable(dtm); 	
		 repaint();
	}
	if(e.getSource()==ajouter) {
		new ajouterNote();
		this.dispose();
	}
}
}
