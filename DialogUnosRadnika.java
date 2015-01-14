/**************************************************************************/
//                                                                         /
//                                                                         /
//    Program koji komunicira sa bazom podataka u preduzecu                /
//                                                                         /
//    Ispitni zadatak iz predmeta Baze podataka II                         /
//                                                                         /
//    Dejan Acanski, C3                                                    /
//                                                                         /	
/**************************************************************************/
//                                                                         /	
//    Klasa koja implementira prozor sa dijalogom za                       /
//    unos filijale u preduzece                                            /	
//                                                                         /	
/**************************************************************************/



package preduzece;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;



public class DialogUnosRadnika extends JDialog {
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
	
	private JTextField txtMBR = new JTextField();
	private JTextField txtIme = new JTextField();
	private JTextField txtPrezime = new JTextField();
	private JTextField txtAdresa = new JTextField();
	private JTextField txtMesto = new JTextField();	
	private JTextField txtTelefon = new JTextField();
	private JTextField txtDatZ = new JTextField();
	private JTextField txtDatR = new JTextField();
	private JTextField txtIDPOS = new JTextField();
	private JTextField txtIDF = new JTextField();
	private JTextField txtIDSL = new JTextField();
	private JTextField txtIDS = new JTextField();
		
	private JLabel jLabel2 = new JLabel();	
	private JLabel jLabel3 = new JLabel();
	private JLabel jLabel4 = new JLabel();
	private JLabel jLabel5 = new JLabel();
	private JLabel jLabel6 = new JLabel();
	private JLabel jLabel7 = new JLabel();
	private JLabel jLabel8 = new JLabel();
	private JLabel jLabel9 = new JLabel();
	private JLabel jLabel10 = new JLabel();
	private JLabel jLabel11 = new JLabel();
	private JLabel jLabel12 = new JLabel();
	private JLabel jLabel13 = new JLabel();	
	private JLabel jLabel14 = new JLabel();	
	private JLabel jLabel15 = new JLabel();	
	
	private JComboBox cbIDP = new JComboBox();
	private JComboBox cbPol = new JComboBox();
	
	private Vector IDPbrojevi = null;		// brojevi postojecih preduzeca

	
	
	public DialogUnosRadnika(Frame frame, String title, boolean modal) {
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
		
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public DialogUnosRadnika() {
		this(null, "", false);
	}



	void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		panel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel1.setText("Unos novog radnika");
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
		jLabel2.setText("Maticni broj radnika:");
		txtMBR.setPreferredSize(new Dimension(80, 21));	
		jLabel3.setText("Ime:");
		txtIme.setPreferredSize(new Dimension(200, 21));
		jLabel4.setText("Prezime:");
		txtPrezime.setPreferredSize(new Dimension(200, 21));		
		jLabel5.setText("Ulica i broj:");
		txtAdresa.setPreferredSize(new Dimension(200, 21));		
		jLabel6.setText("Mesto:");
		txtMesto.setPreferredSize(new Dimension(200, 21));		
		jLabel7.setText("Telefon:");
		txtTelefon.setPreferredSize(new Dimension(80, 21));	
		jLabel8.setText("Datum zaposlenja:");
		txtDatZ.setPreferredSize(new Dimension(80, 21));	
		jLabel9.setText("Datum rodjenja:");
		txtDatR.setPreferredSize(new Dimension(80, 21));	
		jLabel10.setText("Pol:");
		cbPol.setPreferredSize(new Dimension(40, 21));	  
		jLabel11.setText("Identifikacioni broj preduzeca:");
		cbIDP.setPreferredSize(new Dimension(80, 21));	   	
		jLabel12.setText("Identifikacioni broj filijale:");
		txtIDF.setPreferredSize(new Dimension(80, 21));			  
		jLabel13.setText("Identifikacioni broj poslovnice:");
		txtIDPOS.setPreferredSize(new Dimension(80, 21));	   	
		jLabel14.setText("Identifikacioni broj sektora:");
		txtIDS.setPreferredSize(new Dimension(80, 21));		
		jLabel15.setText("Identifikacioni broj sluzbe:");
		txtIDSL.setPreferredSize(new Dimension(80, 21));			  
		
		getContentPane().add(panel1);
		panel1.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(jLabel1, null);
		panel1.add(jPanel2, BorderLayout.CENTER);
		
		jPanel2.add(jLabel2,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtMBR,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel3,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtIme,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel4,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtPrezime,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel5,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtAdresa,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel6,  new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtMesto,  new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel7,  new GridBagConstraints(0, 5, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtTelefon,  new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));			
				
		jPanel2.add(jLabel8,  new GridBagConstraints(0, 6, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtDatZ,  new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel9,  new GridBagConstraints(0, 7, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtDatR,  new GridBagConstraints(1, 7, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel10,  new GridBagConstraints(0, 8, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(cbPol,  new GridBagConstraints(1, 8, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel11,  new GridBagConstraints(0, 9, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(cbIDP,  new GridBagConstraints(1, 9, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel12,  new GridBagConstraints(0, 10, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtIDF,  new GridBagConstraints(1, 10, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel13,  new GridBagConstraints(0, 11, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtIDPOS,  new GridBagConstraints(1, 11, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel14,  new GridBagConstraints(0, 12, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtIDS,  new GridBagConstraints(1, 12, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel15,  new GridBagConstraints(0, 13, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtIDSL,  new GridBagConstraints(1, 13, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
									
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
		
		// popuni combo box za pol
		cbPol.addItem("-"); 
		cbPol.addItem("M");
		cbPol.addItem("Z");
		cbPol.setSelectedItem("-");			
 	}


	// ako korisnik odustane
	void bOdustani_actionPerformed(ActionEvent e) {
	 dispose();
	}
	
	
	
	// ako korisnik potvrdi izabranu opciju
	void bPotvrdi_actionPerformed(ActionEvent e) {
		if ((txtMBR.getText().equals("")) || (txtIme.getText().equals("")) ||
			(txtPrezime.getText().equals("")) || (txtAdresa.getText().equals("")) ||
			(txtMesto.getText().equals("")) || (txtTelefon.getText().equals("")) ||
			(txtDatZ.getText().equals("")) || (txtDatR.getText().equals(""))) {
		   JOptionPane.showMessageDialog(null,"Morate uneti sve licne podatke!");
		   txtMBR.grabFocus(); 
		   return;
		}		
		if(cbPol.getSelectedItem().equals("-")){
	   	JOptionPane.showMessageDialog(null,"Morate izabrati pol!");
	   	return;
	 	}
		if(cbIDP.getSelectedItem().equals("-")){
	   	JOptionPane.showMessageDialog(null,"Mora biti izabrano jedno preduzece!");
	   	return;
	 	}		
		if(txtIDF.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti broj filijale!");
		   txtIDF.grabFocus(); 
		   return;
		}		
		if(txtIDPOS.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti broj poslovnice!");
		   txtIDPOS.grabFocus(); 
		   return;
		}				
		if(txtIDS.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti broj sektora!");
		   txtIDS.grabFocus(); 
		   return;
		}
		if(txtIDSL.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti broj sluzbe!");
		   txtIDSL.grabFocus(); 
		   return;
		}
		
		
		// posalji podatke bazi
		String res = DBF.insertRadnik(new Integer((String)txtMBR.getText()),
												txtIme.getText(),
												txtPrezime.getText(),
												txtAdresa.getText(),
												txtMesto.getText(),
												txtTelefon.getText(),
												java.sql.Date.valueOf(txtDatZ.getText()),
												java.sql.Date.valueOf(txtDatR.getText()),
												(String)cbPol.getSelectedItem(),
												new Integer(txtIDPOS.getText()),
												new Integer(txtIDF.getText()),
												new Integer(txtIDSL.getText()),
												new Integer(txtIDS.getText()),
												(Integer)((Vector)IDPbrojevi.elementAt(cbIDP.getSelectedIndex())).elementAt(0));
		
		// ispisi eventualnu poruku o gresci											
		if(res!=null) {
			JOptionPane.showMessageDialog(null,res);
		} else {
			JOptionPane.showMessageDialog(null,"Radnik je uspesno unet!");
			dispose();
		}
	}
}
