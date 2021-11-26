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
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Gs_Ab extends JFrame implements ActionListener{
	JPanel tabContenaire =new JPanel();
	 JPanel contenaire;
	 JPanel entete =new JPanel();
	 JTable table;
	 DefaultTableModel dtm  = new DefaultTableModel(); 
	 JButton fermer;
	 JButton rechercher;
	 JButton Sauvgarder;
	 JComboBox<String>Module=new JComboBox<String>();
	 JComboBox<String>ModuleS=new JComboBox<String>();
	 String []s= {"Seance 1","Seance 2","Seance 3","Seance 4","Seance 5"};
	 JComboBox<String>Seance=new JComboBox
			 <String>(s);
	 String []a= {"Absent","Present"};	 
	 JComboBox<String>ab=new JComboBox<String>(a);
	 JLabel n=new JLabel();
	 JLabel p=new JLabel();
	 JLabel c=new JLabel();
	 JTextField cs=new JTextField();
	 JLabel nbrAb=new JLabel();
	 public Gs_Ab() {
		  	this.setSize(780,580);
		    this.setTitle("Liste Etudiant");
		    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    contenaire=new JPanel(); 
		    JLabel lblNewLabel_1 = new JLabel("GESTION DES ABSENCES ");
		    lblNewLabel_1.setForeground(new Color(105, 105, 105));
		    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		    contenaire.add(lblNewLabel_1);
		    //--------------------------------------------------------------------
		    JPanel panelM = new JPanel();
		    panelM.setBorder(BorderFactory.createTitledBorder("Marquer les absence")); 
		    JLabel f=new JLabel("Nom");
		    n.setPreferredSize(new Dimension(140, 30));
		    f.setPreferredSize(new Dimension(140, 30));
		    JLabel f1=new JLabel("Prenom");
		    p.setPreferredSize(new Dimension(140, 30));
		    f1.setPreferredSize(new Dimension(140, 30));
		    JLabel f2=new JLabel("CNE");
		    c.setPreferredSize(new Dimension(140, 30));
		    f2.setPreferredSize(new Dimension(140, 30));
		    JLabel f3=new JLabel("Module");
		    f3.setPreferredSize(new Dimension(140, 30));
		    Seance.setPreferredSize(new Dimension(140, 30));
		    ab.setPreferredSize(new Dimension(140, 30));
		    panelM.add(f);
		    panelM.add(n);
		    panelM.add(f1);
		    panelM.add(p);
		    panelM.add(f2);
		    panelM.add(c);
		    panelM.add(f3);
		    try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
				 String req="select codeM from Module"; 
				 PreparedStatement ps=con.prepareStatement(req); 
				 ResultSet res=ps.executeQuery();
		         while (res.next())
		         {
		        	Module.addItem(res.getString(1));
		        	ModuleS.addItem(res.getString(1));
		         }
		 	    Module.setPreferredSize(new Dimension(140, 30));
			} catch (Exception e) {
				e.printStackTrace();
			}
		    panelM.add(Module);
		    panelM.add(Seance);
		    panelM.add(ab);
		    panelM.setPreferredSize(new Dimension(350, 230));
		    Module.addActionListener(this);
		    Sauvgarder=new JButton("Sauvgarder");
		    panelM.add(Sauvgarder);
		    Sauvgarder.addActionListener(this);
		    entete.add(panelM,BorderLayout.NORTH);
//------------------------
		    JPanel panelS = new JPanel();
		    panelS.setBorder(BorderFactory.createTitledBorder("Statistique")); 
		    JLabel f7=new JLabel("CNE");
		    f7.setPreferredSize(new Dimension(140, 30));
		    panelS.add(f7);
		    cs.setPreferredSize(new Dimension(140, 30));
		    panelS.add(cs);
		    JLabel f6=new JLabel("Module");
		    f6.setPreferredSize(new Dimension(140, 30));
		    panelS.add(f6);
		    ModuleS.setPreferredSize(new Dimension(140, 30));
		    panelS.add(ModuleS);
		    JLabel f5=new JLabel("Nbre Absence");
		    f5.setPreferredSize(new Dimension(140, 30));
		    panelS.add(f5);
		    nbrAb.setPreferredSize(new Dimension(140, 30));
		    panelS.add(nbrAb);
		    panelS.setPreferredSize(new Dimension(350, 230));
		    rechercher=new JButton("Rechercher");
		    rechercher.addActionListener(this);
		    panelS.add(rechercher);
		    entete.add(panelS);
		    //-----------------------------------------------------------
		    tabContenaire.setBorder(BorderFactory.createTitledBorder("Liste des etudiants")); 
		    dtm.addColumn("CNE"); 
		    dtm.addColumn("Nom"); 
		    dtm.addColumn("Prenom"); 
		    dtm.addColumn("Seance :1"); 
		    dtm.addColumn("Seance :2"); 
		    dtm.addColumn("Seance :3"); 
		    dtm.addColumn("Seance :4"); 
		    dtm.addColumn("Seance :5"); 
			 table = new JTable(dtm); 	
			 JScrollPane sp1=new JScrollPane(table);   
			 sp1.setPreferredSize(new Dimension(730, 190));
		     tabContenaire.add(sp1);
		     contenaire.add(entete);
		     fermer =new JButton("Fermer");
		     contenaire.add(tabContenaire);
		     contenaire.add(fermer);
		     this.setContentPane(contenaire);
		     this.setVisible(true);
		     
	 }
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==rechercher) {
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
				 String req="select count(*) from absence where codeM='"+ModuleS.getSelectedItem().toString()+"'and absent=1 and cne='"+cs.getText()+"'"; 
				 PreparedStatement ps=con.prepareStatement(req); 
				 ResultSet res=ps.executeQuery();
		         while (res.next())
		         {
		        	 nbrAb.setText(res.getString(1));
		         }
		         repaint();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		if(e.getSource()==Module) {
			try {
				c.setText("");
				p.setText("");
				n.setText("");
				table.getSelectionModel().addSelectionInterval(0, 0);
				SwingUtilities.updateComponentTreeUI(this);
		        dtm.getDataVector().clear();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
	    		String req="SELECT cne,nom,prenom from etudiant"; 
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
		         
		         for(int j=0;j<dtm.getRowCount();j++) {
		        	 req="select * from absence where cne ='"+dtm.getValueAt(j, 0)+"' and codeM='"+Module.getSelectedItem().toString()+"'";
		        	 ps=con.prepareStatement(req); 
					 res=ps.executeQuery();
					 int a=3;
					 while (res.next())
			         {
			             if (res.getInt("absent")==0) {
			        		 dtm.setValueAt("Present", j, a);
			             }else if (res.getInt("absent")==1){
			        		 dtm.setValueAt("Absent", j, a);
			             }
			             a++;
			         }
					 a=3;
		         }
		         table = new JTable(dtm); 	
				 JScrollPane sp1=new JScrollPane(table);   
				 sp1.setPreferredSize(new Dimension(730, 190));
				 tabContenaire.removeAll();
			     tabContenaire.add(sp1);
				 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
						@Override
						public void valueChanged(ListSelectionEvent e) {
							if (table.getSelectedRow() > -1) {
								c.setText((String) dtm.getValueAt(table.getSelectedRow(),0));
								n.setText((String) dtm.getValueAt(table.getSelectedRow(),1));
								p.setText((String) dtm.getValueAt(table.getSelectedRow(),2));
					        }	
						}
					});	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
		if(e.getSource()==Sauvgarder) {
				try {
		    		Class.forName("com.mysql.jdbc.Driver");
		    		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
		    		String req="";
					if (ab.getSelectedIndex()==0) 
			    		req="update absence set absent = 1 where cne='"+c.getText()+"'and codeM='"+Module.getSelectedItem().toString()+"' and numSeance="+Seance.getSelectedIndex(); 
					else
			    		req="update absence set absent = 0 where cne='"+c.getText()+"'and codeM='"+Module.getSelectedItem().toString()+"' and numSeance="+Seance.getSelectedIndex(); 
		    		PreparedStatement ps=con.prepareStatement(req); 
		    			ps.executeUpdate();
		    			table.getSelectionModel().addSelectionInterval(0, 0);
						SwingUtilities.updateComponentTreeUI(this);
				        dtm.getDataVector().clear();
						Class.forName("com.mysql.jdbc.Driver");
						 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
			    		 req="SELECT cne,nom,prenom from etudiant"; 
						 ps=con.prepareStatement(req); 
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
				         
				         for(int j=0;j<dtm.getRowCount();j++) {
				        	 req="select * from absence where cne ='"+dtm.getValueAt(j, 0)+"' and codeM='"+Module.getSelectedItem().toString()+"'";
				        	 ps=con.prepareStatement(req); 
							 res=ps.executeQuery();
							 int a=3;
							 while (res.next())
					         {
					             if (res.getInt("absent")==0) {
					        		 dtm.setValueAt("Present", j, a);
					             }else if (res.getInt("absent")==1){
					        		 dtm.setValueAt("Absent", j, a);
					             }
					             a++;
					         }
							 a=3;
				         }
				         table = new JTable(dtm); 	
						 JScrollPane sp1=new JScrollPane(table);   
						 sp1.setPreferredSize(new Dimension(730, 190));
						 tabContenaire.removeAll();
					     tabContenaire.add(sp1);
						 table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
								@Override
								public void valueChanged(ListSelectionEvent e) {
									if (table.getSelectedRow() > -1) {
										c.setText((String) dtm.getValueAt(table.getSelectedRow(),0));
										n.setText((String) dtm.getValueAt(table.getSelectedRow(),1));
										p.setText((String) dtm.getValueAt(table.getSelectedRow(),2));
							        }	
								}
							});	
					
		    	} catch (Exception e1) {
		    		e1.printStackTrace();
		    	}			
		}
	}
}