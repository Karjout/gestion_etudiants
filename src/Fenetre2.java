import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;	
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Fenetre2 extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	JTextField txtNom;
    JTextField txtPrenom;
    JTextField txtCi;
    JTextField txtCNE;
    JComboBox<String> sexe ;
    JComboBox<String> Filliere ;
    JRadioButton cBts;
    JRadioButton cDut;
    JRadioButton cFac;
    ButtonGroup formation;
    ButtonGroup pre;
    JCheckBox java;
    JCheckBox cp;
    JCheckBox erp;
    JButton ajouter;
    JButton init;
    JButton afficher;
	public Fenetre2() {
	  	this.setSize(450, 400);
	    this.setTitle("Ajouter etudiant");
	    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    JPanel contenaire=new JPanel();
	    //------------------------------------------------------------------------------
	    JPanel panelCNE = new JPanel();
	    panelCNE.setBorder(BorderFactory.createTitledBorder("CNE")); 
	    JLabel cCNE=new JLabel("CNE");
	    txtCNE = new JTextField(10);
	    panelCNE.add(cCNE);
	    panelCNE.add(txtCNE);
	    panelCNE.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelCNE,BorderLayout.NORTH);
	    //------------------------------------------------------------------------------
	    JPanel panelN = new JPanel();
	    panelN.setBorder(BorderFactory.createTitledBorder("Nom")); 
	    JLabel n=new JLabel("Nom");
	    txtNom = new JTextField(10);
	    panelN.add(n);
	    panelN.add(txtNom);
	    panelN.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelN,BorderLayout.NORTH);
	   //--------------------------------------------------------------------------------
	    JPanel panelP = new JPanel();
	    panelP.setBorder(BorderFactory.createTitledBorder("Prenom")); 
	    JLabel p=new JLabel("Prenom");
	    txtPrenom = new JTextField(10);
	    panelP.add(p);
	    panelP.add(txtPrenom);
	    panelP.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelP,BorderLayout.NORTH);
	    //--------------------------------------------------------------------------------
	    JPanel panelC = new JPanel();
	    panelC.setBorder(BorderFactory.createTitledBorder("CIN")); 
	    JLabel c=new JLabel("CIN");
	    txtCi = new JTextField(10);
	    panelC.add(c);
	    panelC.add(txtCi);
	    panelC.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelC,BorderLayout.NORTH);
	    //--------------------------------------------------------------------------------
	    JPanel panelS = new JPanel();
	    panelS.setBorder(BorderFactory.createTitledBorder("Sexe")); 
	    JLabel s=new JLabel("Sexe");
	    String []se= {"Masculin","Feminin"};
	    sexe = new JComboBox<String>(se);
	    panelS.add(s);
	    panelS.add(sexe);
	    panelS.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelS,BorderLayout.NORTH);
	    //-------------------------------------------------------------------------------
	    JPanel panelF = new JPanel();
	    panelF.setBorder(BorderFactory.createTitledBorder("Flliere")); 
	    JLabel f=new JLabel("Filliere");
	    String []Fil= {"SIGL","SMP","SMA","SME"};
	    Filliere = new JComboBox<String>(Fil);
	    panelF.add(f);
	    panelF.add(Filliere);
	    panelF.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelF,BorderLayout.NORTH);
	    //-------------------------------------------------------------------------------
	    JPanel panelD = new JPanel();
	    panelD.setBorder(BorderFactory.createTitledBorder("Diplome")); 
	    cBts=new JRadioButton("BTS");
	    cDut=new JRadioButton("DUT");
	    cFac = new JRadioButton("La fac");
	    formation=new ButtonGroup();
	    formation.add(cBts);formation.add(cDut);formation.add(cFac);
	    panelD.add(cBts);
	    panelD.add(cDut);
	    panelD.add(cFac);
	    panelD.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelD,BorderLayout.SOUTH);
	    //------------------------------------------------------------------------------
	    JPanel panelPr = new JPanel();
	    panelPr.setBorder(BorderFactory.createTitledBorder("Programmation")); 
	    java=new JCheckBox("JAVA");
	    cp=new JCheckBox("C++");
	    erp = new JCheckBox("ERP");
	    panelPr.add(java);
	    panelPr.add(cp);
	    panelPr.add(erp);
	    panelPr.setPreferredSize(new Dimension(205, 70));
	    contenaire.add(panelPr,BorderLayout.SOUTH);
	    //-------------------------------------------------------------------------------
	    ajouter =new JButton("Ajouter");
	    init = new JButton("Initialiser");
	    afficher=new JButton("Afficher");
	    afficher.addActionListener(this);
	    ajouter.addActionListener(this);
	    init.addActionListener(this);
	    contenaire.add(ajouter,BorderLayout.SOUTH);
	    contenaire.add(init,BorderLayout.SOUTH);
	    contenaire.add(afficher,BorderLayout.SOUTH);
	    //-------------------------------------------------------------------------------
	    this.setContentPane(contenaire);
	    this.setVisible(true);
}
	public void actionPerformed(ActionEvent e){
		if (e.getSource()==ajouter) {
			if (testChamps()==false) {
				JOptionPane.showMessageDialog(null, "Verifier que tout les champs sont remplis");
			}else {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/gs_etudiant","root","");
					String req="insert into etudiant(nom,prenom,cin,sexe,diplome,programmation,codeF,cne) values (?,?,?,?,?,?,?,?)"; 
					PreparedStatement ps=con.prepareStatement(req); 
					ps.setString(1,txtNom.getText());
					ps.setString(2,txtPrenom.getText());
					ps.setString(3,txtCi.getText());
					ps.setString(4,sexe.getSelectedItem().toString());
					if(cBts.isSelected()) {
						ps.setString(5,cBts.getText());
					}
					if(cDut.isSelected()) {
						ps.setString(5,cDut.getText());
					}
					if(cFac.isSelected()) {
						ps.setString(5,cFac.getText());
					}
					String prog="";
					if(java.isSelected()) {
						prog+=java.getText()+" ";
					}
					if(cp.isSelected()) {
						prog+=cp.getText()+" ";
					}
					if(erp.isSelected()) {
						prog+=erp.getText()+" ";
					}
					ps.setString(6,prog);
					ps.setString(7, Filliere.getSelectedItem().toString());
					ps.setString(8, txtCNE.getText());
					ps.executeUpdate();
					for (int i=0;i<5;i++) {
						req="INSERT INTO absence (CNE, codeM, numSeance, absent) VALUES ('"+txtCNE.getText()+"','C#',"+i+",3)";  
					    ps=con.prepareStatement(req); 
						ps.executeUpdate();
						req="INSERT INTO absence (CNE, codeM, numSeance, absent) VALUES ('"+txtCNE.getText()+"','JAVA',"+i+",3)"; 
					    ps=con.prepareStatement(req); 
						ps.executeUpdate();
					}
					vider();
					Fenetre3 f3=new Fenetre3();	
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		if(e.getSource()==afficher) {
			Fenetre3 f3=new Fenetre3();	
		}
		if(e.getSource()==init) {
			vider();
		}
	}
	public void vider() {
		txtNom.setText("");
		txtPrenom.setText("");
		txtCi.setText("");
		txtCNE.setText("");
		sexe.setSelectedIndex(0);
		formation.clearSelection();
		java.setSelected(false);
		cp.setSelected(false);
		erp.setSelected(false);
	}
	public boolean testChamps() {
		if(txtNom.getText().equals("")||txtPrenom.getText().equals("")||txtCi.getText().equals("")||formation.getSelection()==null)
			return false;
		return true;
	}
	}