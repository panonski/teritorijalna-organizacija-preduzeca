/**************************************************************************/
//                                                                         /
//    Program koji komunicira sa bazom podataka u preduzecu                /
//    Ispitni zadatak iz predmeta Baze podataka II                         /
//                                                                         /
//    Dejan Acanski, C3                                                    /
//                                                                         /	
/**************************************************************************/
//                                                                         /	
//		Klasa koja implementira prozor sa dijalogom za                       /
//		brisanje poslovnice iz preduzeca                                     /	
//                                                                         /	
/**************************************************************************/




package preduzece;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;


public class DialogBrisanjeRadnika extends JDialog {
	private JPanel panel1 = new JPanel();
	private BorderLayout borderLayout1 = new BorderLayout();
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private JPanel jPanel3 = new JPanel();
	private JLabel jLabel1 = new JLabel();
	private GridBagLayout gridBagLayout1 = new GridBagLayout();
	private JButton bOdustani = new JButton();
	private JButton bPotvrdi = new JButton();

	private GridBagLayout gridBagLayout2 = new GridBagLayout();
	private JLabel jLabel2 = new JLabel();	
	private JComboBox cbIDP = new JComboBox();
	private JLabel jLabel3 = new JLabel();
	private JTextField txtMBR = new JTextField();
	
	private Vector IDPbrojevi = null;
	
	
	
	public DialogBrisanjeRadnika(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		try {
			jbInit();
			pack();
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension dialogSize = this.getSize();
			if (dialogSize.height > dialogSize.height) {
				dialogSize.height = screenSize.height;
			}
			if (dialogSize.width > screenSize.width) {
				dialogSize.width = screenSize.width;
		}
		this.setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}



	public DialogBrisanjeRadnika() {
		this(null, "", false);
	}
	
	
	
	void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		panel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel1.setText("Brisanje zaposlenog radnika");
		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		jPanel2.setLayout(gridBagLayout2);
		jPanel3.setBorder(BorderFactory.createEtchedBorder());
		jPanel3.setLayout(gridBagLayout1);
		
		bOdustani.setPreferredSize(new Dimension(80, 27));
		bOdustani.setMargin(new Insets(0, 0, 0, 0));
		bOdustani.setText("Odustani");
		bOdustani.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
	   		bOdustani_actionPerformed(e);
			}
		});
		
		bPotvrdi.setPreferredSize(new Dimension(80, 27));
		bPotvrdi.setText("Potvrdi");
		bPotvrdi.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
		  		bPotvrdi_actionPerformed(e);
			}
		});
		
		jLabel2.setText("Identifikacioni broj preduzeca:");
		cbIDP.setPreferredSize(new Dimension(80, 21));
		jLabel3.setText("Maticni broj radnika:");
		txtMBR.setPreferredSize(new Dimension(200, 21));
		
		getContentPane().add(panel1);
		panel1.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(jLabel1, null);
		panel1.add(jPanel2, BorderLayout.CENTER);
		jPanel2.add(jLabel2,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 10, 2, 0), 0, 0));
		jPanel2.add(cbIDP,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 2, 0), 0, 0));
		jPanel2.add(jLabel3,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(20, 0, 20, 0), 0, 0));
		jPanel2.add(txtMBR,   new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(20, 10, 20, 10), 0, 0));
				
		panel1.add(jPanel3,  BorderLayout.SOUTH);
		jPanel3.add(bOdustani,     new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 30, 0, 0), 0, 0));
		jPanel3.add(bPotvrdi,    new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(5, 0, 5, 30), 0, 0));
		init();
	}



	void init(){
		IDPbrojevi = DBF.getPreduzece(); // ucitaj identifikacione brojeve preduzeca
		Iterator it = IDPbrojevi.iterator();
		Vector pom = null;
		while(it.hasNext()){
			pom = (Vector)it.next(); // izaberi sledeci red
			cbIDP.addItem(""+(Integer)pom.elementAt(0)); //uzmi prvi element iz tog reda i dodaj ga u combo box
		}
		cbIDP.addItem("-"); // ubaci nulti element
		cbIDP.setSelectedItem("-");  
	}


	// ako korisnik odustane
	void bOdustani_actionPerformed(ActionEvent e) {
	 dispose();
	}
	
	
	
	// ako korisnik potvrdi izabranu opciju
	void bPotvrdi_actionPerformed(ActionEvent e) {

		if(cbIDP.getSelectedItem().equals("-")){
	   	JOptionPane.showMessageDialog(null,"Mora biti izabrano jedno preduzece!");
	   	return;
	 	}	
			
		if(txtMBR.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti maticni broj!");
		   txtMBR.grabFocus(); 
		   return;
		}
		
		// posalji bazi podatke za brisanje
		String res = DBF.deleteRadnik((Integer)((Vector)IDPbrojevi.elementAt(cbIDP.getSelectedIndex())).elementAt(0),
												  new Integer(txtMBR.getText()));
													
		// ispisi eventualnu poruku o gresci											
		if (res!=null) {
			JOptionPane.showMessageDialog(null,res);
		} else {
			JOptionPane.showMessageDialog(null,"Radnik je uspešno obrisan");
			txtMBR.grabFocus();
			txtMBR.setSelectionStart(0);
			txtMBR.setSelectionEnd(txtMBR.getText().length());
		}
	}
}

