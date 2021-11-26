import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Fenetre4 extends JFrame implements ActionListener{
	JTextField txtCodeFilliere;
	JTextField txtLibelleFilliere;
	JButton ajouter;
	JButton fermer;
public Fenetre4() {
  	this.setSize(300, 170);
    this.setTitle("Ajouter filliere");
    this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    JPanel contenaire=new JPanel();
    JPanel panelN = new JPanel();
    panelN.setBorder(BorderFactory.createTitledBorder("Filliere")); 
    JLabel n=new JLabel("Code Filliere:");
    txtCodeFilliere = new JTextField(10);
    panelN.add(n);
    panelN.add(txtCodeFilliere);
    panelN.setPreferredSize(new Dimension(250, 90));
    JLabel nC=new JLabel("Libelle Filliere");
    txtLibelleFilliere = new JTextField(10);
    panelN.add(nC);
    panelN.add(txtLibelleFilliere);
    contenaire.add(panelN,BorderLayout.NORTH);
    ajouter =new JButton("Ajouter");
    ajouter.addActionListener(this);
    fermer =new JButton("Fermer");
    fermer.addActionListener(this);
    contenaire.add(ajouter,BorderLayout.SOUTH);
    contenaire.add(fermer,BorderLayout.SOUTH);
    this.setContentPane(contenaire);
    this.setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
	if(e.getSource()==fermer)
	    this.dispose();
	if(e.getSource()==ajouter) {
			if (testChamps()==false) {
				JOptionPane.showMessageDialog(null, "Verifier que tout les champs sont remplis");
			}else {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/etudiant","root","");
			String req="insert into filliere values (?,?)"; 
			PreparedStatement ps=con.prepareStatement(req); 
			ps.setString(1,txtCodeFilliere.getText());
			ps.setString(2,txtLibelleFilliere.getText());
			ps.executeUpdate();
			vider();
			this.dispose();
			Fenetre5 f5=new Fenetre5();
	}catch (Exception e1) {
		// TODO: handle exception
	}
			}
	}
}
public boolean testChamps() {
	if(txtCodeFilliere.getText().equals("")||txtLibelleFilliere.getText().equals(""))
		return false;
	return true;
}
public void vider() {
	txtCodeFilliere.setText("");
	txtLibelleFilliere.setText("");

}
}
