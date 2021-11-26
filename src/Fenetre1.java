import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;


public class Fenetre1 extends JFrame implements ActionListener{

	JMenuBar jmb=new JMenuBar();
	JMenuItem AffEt;
	JMenuItem addEt;
	JMenuItem AffN;
	JMenuItem AffPr;
	JMenuItem addPr;
	JMenuItem AffAb;
	private JLabel lblNewLabel;
	
public Fenetre1() {
	
		JMenu Et=new JMenu("Gestion Etudiant");
	    addEt=new JMenuItem("Ajouter etudiant");
		addEt.addActionListener(this);
		AffEt =new JMenuItem("Afficher etudiant");
		AffEt.addActionListener(this);
		Et.add(AffEt);
		Et.add(addEt);
		jmb.add(Et);
		
		JMenu Fi=new JMenu("Gestion Note");
		AffN =new JMenuItem("Afficher Note");
		AffN.addActionListener(this);
		Fi.add(AffN);
		jmb.add(Fi);
		
		JMenu Pr=new JMenu("Gestison des moyenne");
	    addPr=new JMenuItem("Afficher les moyennes");
		addPr.addActionListener(this);
		Pr.add(addPr);
		jmb.add(Pr);

		JMenu Ab=new JMenu("Gestison des absences");
	    AffAb=new JMenuItem("Afficher les absences");
		AffAb.addActionListener(this);
		Ab.add(AffAb);
		jmb.add(Ab);
	 
	  	this.setSize(550, 500);
	    this.setTitle("Licence pro SIGL");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    JPanel contenaire=new JPanel();
	    contenaire.setForeground(SystemColor.textHighlight);
	    contenaire.setBackground(SystemColor.controlHighlight);
	    this.setContentPane(contenaire);
	    
	    lblNewLabel = new JLabel("");
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ZIS\\Desktop\\FSDM.jpg"));
	    JLabel lblNewLabel_1 = new JLabel("GESTION DES ETUDIANTS ");
	    lblNewLabel_1.setForeground(new Color(105, 105, 105));
	    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	    
	    JLabel lblSigl = new JLabel("SIGL");
	    lblSigl.setBackground(new Color(240, 255, 240));
	    lblSigl.setForeground(UIManager.getColor("controlShadow"));
	    lblSigl.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
	    
	    JLabel label = new JLabel("");
	    label.setIcon(new ImageIcon("C:\\Users\\ZIS\\Desktop\\SIGL\\Java\\LP-SIGL-INFO\\resource\\ELE.png"));
	    GroupLayout gl_contenaire = new GroupLayout(contenaire);
	    gl_contenaire.setHorizontalGroup(
	    	gl_contenaire.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contenaire.createSequentialGroup()
	    			.addComponent(lblNewLabel)
	    			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	    		.addGroup(gl_contenaire.createSequentialGroup()
	    			.addContainerGap(253, Short.MAX_VALUE)
	    			.addComponent(lblSigl, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
	    			.addGap(230))
	    		.addGroup(gl_contenaire.createSequentialGroup()
	    			.addGap(82)
	    			.addGroup(gl_contenaire.createParallelGroup(Alignment.LEADING)
	    				.addGroup(gl_contenaire.createSequentialGroup()
	    					.addGap(10)
	    					.addComponent(lblNewLabel_1))
	    				.addComponent(label))
	    			.addContainerGap(111, Short.MAX_VALUE))
	    );
	    gl_contenaire.setVerticalGroup(
	    	gl_contenaire.createParallelGroup(Alignment.LEADING)
	    		.addGroup(gl_contenaire.createSequentialGroup()
	    			.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(label, GroupLayout.PREFERRED_SIZE, 285, Short.MAX_VALUE)
	    			.addPreferredGap(ComponentPlacement.RELATED)
	    			.addComponent(lblNewLabel_1)
	    			.addGap(35)
	    			.addComponent(lblSigl, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
	    			.addGap(80))
	    );
	    contenaire.setLayout(gl_contenaire);
	    this.setJMenuBar(jmb);
	    this.setVisible(true);
}
public void actionPerformed(ActionEvent e){
		if(e.getSource()==addEt) {
			new Fenetre2();
		}
		if(e.getSource()==AffEt) {
			new Fenetre3();
		}
		if(e.getSource()==AffN) {
		    new Fenetre5();
		}
		if(e.getSource()==addPr) {
			new Gs_Moynne();
		}
		if (e.getSource()==AffAb) {
			new Gs_Ab();
		}
}
}
