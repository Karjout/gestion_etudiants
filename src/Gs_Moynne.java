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
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JTable;
	import javax.swing.table.DefaultTableModel;
	
	public class Gs_Moynne extends JFrame implements ActionListener{
		 JTable table;
		 DefaultTableModel dtm  = new DefaultTableModel(); 
		JPanel tabContenaire;
		 JPanel contenaire;
		 JButton fermer;
	
		public Gs_Moynne() {
		  	this.setSize(780,550);
		    this.setTitle("Liste Etudiant");
		    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		    this.setLocationRelativeTo(null);
		    this.setResizable(false);
		    contenaire=new JPanel();  
		    JLabel lblNewLabel_1 = new JLabel("GESTION DES MOYENNES ");
		    lblNewLabel_1.setForeground(new Color(105, 105, 105));
		    lblNewLabel_1.setFont(new Font("Roboto", Font.BOLD | Font.BOLD, 25));
		    contenaire.add(lblNewLabel_1);
		    tabContenaire=new JPanel();  
		    tabContenaire.setBorder(BorderFactory.createTitledBorder("Liste des etudiants")); 
		    
		    dtm.addColumn("CNE"); 
		    dtm.addColumn("Nom"); 
		    dtm.addColumn("Prenom"); 
		    dtm.addColumn("Moyenne"); 
		    dtm.addColumn("Mention"); 
		    dtm.addColumn("Valide"); 
		    try {
		        dtm.getDataVector().clear();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
	    		String req="SELECT etudiant.cne,nom,prenom,AVG(note__) from note_,etudiant WHERE etudiant.cne=note_.cne GROUP by cne"; 
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
		        	 if((Double.parseDouble(dtm.getValueAt(j, 3).toString()))>10) {
		        		 dtm.setValueAt("Passable", j, 4);
		        		 dtm.setValueAt("Valider", j, 5);
		        	 }
		        	 if((Double.parseDouble(dtm.getValueAt(j, 3).toString()))>=10 & (Double.parseDouble(dtm.getValueAt(j, 3).toString()))<14 ) {
		        		 dtm.setValueAt("Assez Bien", j, 4);
		        		 dtm.setValueAt("Valider", j, 5);
		        	 }
		        	 if((Double.parseDouble(dtm.getValueAt(j, 3).toString()))>=14 & (Double.parseDouble(dtm.getValueAt(j, 3).toString()))<16 ) {
		        		 dtm.setValueAt("Bien", j, 4);
		        		 dtm.setValueAt("Valider", j, 5);
		        	 }
		        	 if((Double.parseDouble(dtm.getValueAt(j, 3).toString()))>=16) {
		        		 dtm.setValueAt("Tres Bien", j, 4);
		        		 dtm.setValueAt("Valider", j, 5);
		        	 }
		        	 if((Double.parseDouble(dtm.getValueAt(j, 3).toString()))<10) {
		        		 dtm.setValueAt("--", j, 4);
		        		 dtm.setValueAt("Non Valider", j, 5);
		        	 }
		         }
		         table = new JTable(dtm); 	
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			 table = new JTable(dtm); 	
			 JScrollPane sp1=new JScrollPane(table);   
			 sp1.setPreferredSize(new Dimension(730, 400));
		     tabContenaire.add(sp1);
	         this.setContentPane(contenaire);
		     contenaire.add(tabContenaire,BorderLayout.CENTER);	    
		     fermer=new JButton("Fermer");
		     fermer.addActionListener(this);
		     contenaire.add(fermer,BorderLayout.SOUTH);
		     this.setContentPane(contenaire);
		     this.setVisible(true);
			 repaint();
		}
	
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource()==fermer) {
				this.dispose();
			}
		}
	   
	}
