import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class ajouterNote extends JFrame implements ActionListener{
	
	 JPanel contenaire;
	 JComboBox<String> Etudiant=new JComboBox<>();
	 JComboBox<String> Module=new JComboBox<>();
	 JLabel eNom=new JLabel("");
	 JLabel ePrenom =new JLabel("");
	 JTextField note =new JTextField();
	 JButton fermer;
	 JButton enregistrer;
	 
	public ajouterNote() {

	  	this.setSize(530,340);
	    this.setTitle("Liste Etudiant");
	    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    contenaire=new JPanel();  
	    //------------------------------------------------------------
	    JPanel panelN = new JPanel();
	    panelN.setBorder(BorderFactory.createTitledBorder("Etudiant")); 
	    JLabel e=new JLabel("Etudiant  :");
	    JLabel e2=new JLabel("Nom  :");
	    JLabel e3=new JLabel("Prenom  :");
	    try {
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
			 String req="select cne,nom,prenom from etudiant"; 
			 PreparedStatement ps=con.prepareStatement(req); 
			 ResultSet res=ps.executeQuery();
	         while (res.next())
	         {
	        	Etudiant.addItem(res.getString(1));
	         }
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    e.setPreferredSize(new Dimension(150, 30));
	    panelN.add(e);
	    Etudiant.setPreferredSize(new Dimension(250, 30));
	    Etudiant.addActionListener(this);
	    panelN.add(Etudiant);
	    e2.setPreferredSize(new Dimension(150, 30));
	    panelN.add(e2);
	    eNom.setPreferredSize(new Dimension(250, 30));
	    panelN.add(eNom);
	    e3.setPreferredSize(new Dimension(150, 30));
	    panelN.add(e3);
	    ePrenom.setPreferredSize(new Dimension(250, 30));
	    panelN.add(ePrenom);
	    panelN.setPreferredSize(new Dimension(480, 150));
	    Etudiant.addActionListener(this);
	    //-----------------------------------------------
	    JPanel panelM = new JPanel();
	    panelM.setBorder(BorderFactory.createTitledBorder("Module")); 
	    JLabel f=new JLabel("Module");
	    f.setPreferredSize(new Dimension(150, 30));
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
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	    panelM.add(f);
	    Module.setPreferredSize(new Dimension(250, 30));
	    panelM.add(Module);
	    JLabel e5=new JLabel("Note  :");
	    e5.setPreferredSize(new Dimension(150, 30));
	    panelM.add(e5);
	    note.setPreferredSize(new Dimension(250, 30));getContentPane();
	    panelM.add(note);
	    panelM.setPreferredSize(new Dimension(480, 100));
	    Module.addActionListener(this);
	    //----------------------------------------------
	    contenaire.add(panelN,BorderLayout.NORTH);
	    contenaire.add(panelM,BorderLayout.NORTH);
	     fermer=new JButton("Fermer");
	     enregistrer=new JButton("Enregistrer");
	     fermer.addActionListener(this);
	     enregistrer.addActionListener(this);
	     contenaire.add(enregistrer,BorderLayout.SOUTH);

	     contenaire.add(fermer,BorderLayout.SOUTH);
	     this.setContentPane(contenaire);
	     this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Etudiant) {
	        try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
	    		String req="select nom,prenom from etudiant where cne='"+Etudiant.getSelectedItem().toString()+"'"; 
	    		PreparedStatement ps=con.prepareStatement(req); 
	    		 ResultSet res=ps.executeQuery();
	             while (res.next())
	             {
	            	 eNom.setText(res.getString(1));
	            	 ePrenom.setText(res.getString(2));
	             }
	        		 repaint();
	    	} catch (Exception e1) {
	    		e1.printStackTrace();
	    	}
		}
		if(e.getSource()==enregistrer) {
			try {
	    		Class.forName("com.mysql.jdbc.Driver");
	    		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
	    		String req="select * from note_ where cne='"+Etudiant.getSelectedItem().toString()+"' and codeM='"+Module.getSelectedItem().toString()+"'"; 
	    		PreparedStatement ps=con.prepareStatement(req); 
	    		 ResultSet res=ps.executeQuery();
	             if (res.isBeforeFirst()) {
	 				JOptionPane.showMessageDialog(null, "Deja existe");
	             }else {
	            	    req="insert into note_(cne,codeM,note__) values (?,?,?)";
		 	    		ps=con.prepareStatement(req); 
						ps.setString(1,Etudiant.getSelectedItem().toString());
						ps.setString(2,Module.getSelectedItem().toString());
						ps.setString(3,note.getText());
						ps.executeUpdate();
						new Fenetre5();
						this.dispose();
	             }
	        		 repaint();
	    	} catch (Exception e1) {
	    		e1.printStackTrace();
	    	}
		}
		if (e.getSource()==fermer) {
			this.dispose();
		}
	}
}
