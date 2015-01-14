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
//		pregledanjem podataka o radnicima zaposlenim u datoj poslovnici      /	
//                                                                         /	
/**************************************************************************/




package preduzece;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;



public class DialogPregledRadnika extends JDialog {
	private JPanel panel1 = new JPanel();
	private BorderLayout borderLayout1 = new BorderLayout();
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();	

	private JScrollPane jScrollPane1 = new JScrollPane();
	private BorderLayout borderLayout2 = new BorderLayout();
	private JLabel jLabel1 = new JLabel();
	private GridBagLayout gridBagLayout1 = new GridBagLayout();
	private JLabel jLabel2 = new JLabel();
	private JLabel jLabel3 = new JLabel();
	private JLabel jLabel4 = new JLabel();
	private JComboBox cbIDP = new JComboBox();
	private JTextField txtIDF = new JTextField();
	private JTextField txtIDPOS = new JTextField();
	private JButton bPronadji = new JButton();
	private JButton bIzlaz = new JButton();
	private JTable tabRadnici = new JTable();
	
	private Vector IDPbrojevi = null;
	
	public DialogPregledRadnika(Frame frame, String title, boolean modal) {
		super(frame, title, modal);
		try {
			jbInit();
			pack();
			setSize(800,572);
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			Dimension dialogSize = this.getSize();
			setLocation((screenSize.width - dialogSize.width) / 2, (screenSize.height - dialogSize.height) / 2);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}



	public DialogPregledRadnika() {
   	this(null, "", false);
	}
	
	
	void jbInit() throws Exception {
		panel1.setLayout(borderLayout1);
		jPanel1.setBorder(BorderFactory.createEtchedBorder());
		jPanel1.setPreferredSize(new Dimension(14, 40));
		jPanel1.setLayout(borderLayout2);
		jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
		jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		jLabel1.setText("Pregled radnika u poslovnici");
		jPanel2.setBorder(BorderFactory.createEtchedBorder());
		jPanel2.setPreferredSize(new Dimension(50, 100));
		jPanel2.setLayout(gridBagLayout1);		
		jLabel2.setText("ID broj preduzeca:");
		cbIDP.setPreferredSize(new Dimension(80, 21));	   	
		jLabel3.setText("ID filijale:");
		txtIDF.setPreferredSize(new Dimension(80, 21));			  
		jLabel4.setText("ID poslovnice:");
		txtIDPOS.setPreferredSize(new Dimension(80, 21));
		
		bPronadji.setPreferredSize(new Dimension(80, 21));
		bPronadji.setText("Prona\u0111i");
		bPronadji.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
	      	bPronadji_actionPerformed(e);
	      }
		});
	
	
		bIzlaz.setPreferredSize(new Dimension(80, 21));
		bIzlaz.setText("Izlaz");
		bIzlaz.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bIzlaz_actionPerformed(e);
			}
		});
			
		getContentPane().add(panel1);
		panel1.add(jPanel1, BorderLayout.NORTH);
		jPanel1.add(jLabel1, BorderLayout.CENTER);
		panel1.add(jPanel2, BorderLayout.SOUTH);
		jPanel2.add(jLabel2, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		jPanel2.add(cbIDP,  new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		jPanel2.add(jLabel3, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		jPanel2.add(txtIDF,  new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
				
		jPanel2.add(jLabel4, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		jPanel2.add(txtIDPOS,  new GridBagConstraints(5, 0, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		jPanel2.add(bPronadji,  new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0)); 
				
		jPanel2.add(bIzlaz,  new GridBagConstraints(4, 1, 1, 1, 0.0, 0.0
		      ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));
		
		panel1.add(jScrollPane1,  BorderLayout.CENTER);
		jScrollPane1.getViewport().add(tabRadnici, null);
		TModelRadnika tMR = new TModelRadnika(tabRadnici);
		tabRadnici.setModel(tMR);
		((TModelRadnika)tabRadnici.getModel()).initColumns();
		
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
	void bIzlaz_actionPerformed(ActionEvent e) {
	 dispose();
	}
	
	
	
	// ako korisnik potvrdi izabranu opciju
	void bPronadji_actionPerformed(ActionEvent e) {
		if(cbIDP.getSelectedItem().equals("-")){
			JOptionPane.showMessageDialog(null,"Morate izabrati jedno preduzece!");
			cbIDP.grabFocus(); 
		   return;
		}
		if(txtIDF.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Morate uneti jednu filijalu!");
			txtIDF.grabFocus(); 
		   return;
		}
		if(txtIDPOS.getText().equals("")){
			JOptionPane.showMessageDialog(null,"Morate uneti jednu poslovnicu!");
			txtIDPOS.grabFocus(); 
		   return;
		}
		
		// posalji bazi podatke za pregled
	   Vector radnici = DBF.getRadnici((Integer)((Vector)IDPbrojevi.elementAt(cbIDP.getSelectedIndex())).elementAt(0),
												  new Integer(txtIDF.getText()),
												  new Integer(txtIDPOS.getText()));
		// prikazi pronadjene radnike											
      if(!radnici.isEmpty()){
        ((TModelRadnika)tabRadnici.getModel()).rejoinData(radnici);
      }
      else{
        JOptionPane.showMessageDialog(null,"Nije prona\u0111en nijedan radnik");
      }
   }  
}
