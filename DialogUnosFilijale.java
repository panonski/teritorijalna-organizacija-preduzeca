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


public class DialogUnosFilijale extends JDialog {
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
	private JTextField txtIDF = new JTextField();
	private JTextField txtAdresa = new JTextField();
	private JTextField txtMesto = new JTextField();
	private JTextField txtTelefon = new JTextField();
	
	private JLabel jLabel3 = new JLabel();
	private JLabel jLabel4 = new JLabel();
	private JLabel jLabel5 = new JLabel();
	private JLabel jLabel6 = new JLabel();
	private JComboBox cbIDP = new JComboBox();
	
	private Vector IDPbrojevi = null; // ID brojevi postojecih preduzeca



	public DialogUnosFilijale(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		try{
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
		
			this.setLocation((screenSize.width - dialogSize.width) / 2, 
                                        (screenSize.height - dialogSize.height) / 2);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}



	public DialogUnosFilijale() {
		this(null, "", false);
  	}
	


	void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		panel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel1.setText("Unos nove filijale");
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
		jLabel3.setText("Identifikacioni broj filijale:");
		txtIDF.setPreferredSize(new Dimension(80, 21));
		jLabel4.setText("Ulica i broj:");
		txtAdresa.setPreferredSize(new Dimension(200, 21)); 
		jLabel5.setText("Mesto:");
		txtMesto.setPreferredSize(new Dimension(200, 21));		 	
		jLabel6.setText("Telefon:");
		txtTelefon.setPreferredSize(new Dimension(80, 21));	
		
		getContentPane().add(panel1);
		panel1.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(jLabel1, null);
		panel1.add(jPanel2, BorderLayout.CENTER);
		
		jPanel2.add(jLabel2,  new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(cbIDP,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
				
		jPanel2.add(jLabel3,  new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtIDF,  new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel4,  new GridBagConstraints(0, 2, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtAdresa,  new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel5,  new GridBagConstraints(0, 3, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtMesto,  new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 2, 0), 0, 0));
							
		jPanel2.add(jLabel6,  new GridBagConstraints(0, 4, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(10, 0, 2, 0), 0, 0));
		jPanel2.add(txtTelefon,  new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0
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
	}


	// ako korisnik odustane
	void bOdustani_actionPerformed(ActionEvent e) {
	 dispose();
	}
	
	
	
	// ako korisnik potvrdi izabranu opciju
	void bPotvrdi_actionPerformed(ActionEvent e) {
	
		//proveri da li su uneti svi podaci
		
		if(cbIDP.getSelectedItem().equals("-")){
	   	JOptionPane.showMessageDialog(null,"Mora biti izabrano jedno preduzece!");
	   	return;
	 	}		
	
		if(txtIDF.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti broj filijale!");
		   txtIDF.grabFocus(); 
		   return;
		}
		if(txtAdresa.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti ulicu i broj!");
		   txtAdresa.grabFocus(); 
		   return;
		}
		if(txtMesto.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti mesto!");
		   txtMesto.grabFocus(); 
		   return;
		}
		if(txtTelefon.getText().equals("")){
		   JOptionPane.showMessageDialog(null,"Morate uneti telefon!");
		   txtTelefon.grabFocus(); 
		   return;
		}
		
		// posalji podatke bazi
		String res = DBF.insertFilijala((Integer)
                        ((Vector)IDPbrojevi.elementAt(cbIDP.getSelectedIndex())).elementAt(0),
                                                        new Integer(txtIDF.getText()),
                                                        txtAdresa.getText(),
                                                        txtMesto.getText(),
                                                        txtTelefon.getText());

		// ako imamo poruku o gresci, ispisi je
		// ako ne, javi da je operacija uspela
		if(res!=null) {
			JOptionPane.showMessageDialog(null,res);
		} else {
			JOptionPane.showMessageDialog(null,"Filijala je uspesno uneta!");
			dispose();
		}
	}
}
