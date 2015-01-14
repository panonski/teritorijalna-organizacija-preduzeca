/**************************************************************************/
//                                                                         /
//    Program koji komunicira sa bazom podataka u preduzecu                /
//    Ispitni zadatak iz predmeta Baze podataka II                         /
//                                                                         /
//    Dejan Acanski, C3                                                    /
//                                                                         /	
/**************************************************************************/
//                                                                         /	
//		Klasa koja implementira glavni prozor sa opcijama programa				/
//                                                                         /	
/**************************************************************************/




package preduzece;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FrameMain extends JFrame {
	private JPanel contentPane;
	private JLabel statusBar = new JLabel();
	private BorderLayout borderLayout1 = new BorderLayout();
	private JLabel jLabel1 = new JLabel();
	private JPanel jPanel1 = new JPanel();
	private JPanel jPanel2 = new JPanel();
	private GridBagLayout gridBagLayout1 = new GridBagLayout();
	private GridBagLayout gridBagLayout2 = new GridBagLayout();
	private JButton bUnosFilijale = new JButton();
	private JButton bBrisanjeFilijale = new JButton();
	private JButton bUnosPoslovnice = new JButton(); 
	private JButton bBrisanjePoslovnice = new JButton();
	private JButton bUnosRadnika = new JButton();
	private JButton bBrisanjeRadnika = new JButton(); 
	private JButton bPregledRadnika = new JButton();
	private JButton bIzlaz = new JButton();

	
  public FrameMain() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
	

  private void jbInit() throws Exception  {
	 contentPane = (JPanel) this.getContentPane();
	 contentPane.setLayout(borderLayout1);
	 this.setSize(new Dimension(600, 350));
	 this.setTitle("Teritorijalna organizacija preduze\u0107a");
	 statusBar.setFont(new java.awt.Font("Dialog", 0, 14));
	 statusBar.setBorder(BorderFactory.createEtchedBorder());
	 statusBar.setText(" ");
	 contentPane.setBorder(BorderFactory.createEtchedBorder());
	 jLabel1.setFont(new java.awt.Font("Dialog", 1, 14));
	 jLabel1.setBorder(BorderFactory.createEtchedBorder());
	 jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
	 jLabel1.setHorizontalTextPosition(SwingConstants.CENTER);
	 jLabel1.setText("Teritorijalna organizacija preduze\u0107a");
		
	 jPanel1.setBorder(BorderFactory.createEtchedBorder());
	 jPanel1.setLayout(gridBagLayout1);
		
	 jPanel2.setBorder(BorderFactory.createEtchedBorder());
	 jPanel2.setLayout(gridBagLayout1);
		
		
	 // postavljanje dugmadi sa opcijama i definisanje ponasanja dugmeta
		
	 bUnosFilijale.setPreferredSize(new Dimension(150, 27));
	 bUnosFilijale.setText("Unos filijale");
	 bUnosFilijale.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bUnosFilijale_actionPerformed(e);
	   }
	 });
		
	 bBrisanjeFilijale.setPreferredSize(new Dimension(150, 27));
	 bBrisanjeFilijale.setText("Brisanje filijale");
	 bBrisanjeFilijale.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bBrisanjeFilijale_actionPerformed(e);
	   }
	 });
	
	
	 bUnosPoslovnice.setPreferredSize(new Dimension(150, 27));
	 bUnosPoslovnice.setText("Unos poslovnice");
	 bUnosPoslovnice.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bUnosPoslovnice_actionPerformed(e);
	   }
	 });
		
	 bBrisanjePoslovnice.setPreferredSize(new Dimension(150, 27));
	 bBrisanjePoslovnice.setText("Brisanje poslovnice");
	 bBrisanjePoslovnice.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bBrisanjePoslovnice_actionPerformed(e);
	   }
	 });
		
	
	 bUnosRadnika.setPreferredSize(new Dimension(150, 27));
	 bUnosRadnika.setText("Unos radnika"); 
	 bUnosRadnika.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bUnosRadnika_actionPerformed(e);
	   }
	 });
		
	 bBrisanjeRadnika.setPreferredSize(new Dimension(150, 27));
	 bBrisanjeRadnika.setText("Brisanje radnika");
	 bBrisanjeRadnika.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bBrisanjeRadnika_actionPerformed(e);
	   }
	 });
		
	 bPregledRadnika.setPreferredSize(new Dimension(150, 27));
	 bPregledRadnika.setText("Pregled radnika");
	 bPregledRadnika.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bPregledRadnika_actionPerformed(e);
	   }
	 }); 
	
	 bIzlaz.setPreferredSize(new Dimension(150, 27));
	 bIzlaz.setText("Izlaz");
	 bIzlaz.addActionListener(new java.awt.event.ActionListener() {
	   public void actionPerformed(ActionEvent e) {
	     bIzlaz_actionPerformed(e);
	   }
	 });

		
	 contentPane.add(statusBar, BorderLayout.SOUTH);
	 contentPane.add(jLabel1, BorderLayout.NORTH);
	 contentPane.add(jPanel1, BorderLayout.CENTER);
	 contentPane.add(jPanel2, BorderLayout.SOUTH);					
	 jPanel1.add(bUnosFilijale,    new GridBagConstraints(0, 0, 1, 1, 10.0, 0.0
	         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
	 jPanel1.add(bBrisanjeFilijale,    new GridBagConstraints(0, 1, 1, 1, 0.0, 0.
	         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
		
	 jPanel1.add(bUnosPoslovnice,    new GridBagConstraints(1, 0, 1, 1, 10.0, 0.0
	         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
	 jPanel1.add(bBrisanjePoslovnice,    new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0
	         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
					
	 jPanel1.add(bUnosRadnika,    new GridBagConstraints(2, 0, 1, 1, 10.0, 0.0
	         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
	 jPanel1.add(bBrisanjeRadnika,    new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0
	         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
	 jPanel1.add(bPregledRadnika,   new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0
	         ,GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
	 
	 jPanel2.add(bIzlaz,   new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0
	         ,GridBagConstraints.CENTER, GridBagConstraints.CENTER, new Insets(10, 0, 10, 0), 0, 0));
	 
	}
	
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

	void bIzlaz_actionPerformed(ActionEvent e) {
    System.exit(0);
   }

   // povezivanje sa odgovarajucim prozorima za dijalog
	
	void bUnosFilijale_actionPerformed(ActionEvent e) {
   	DialogUnosFilijale dUnFil = new DialogUnosFilijale(this,"Unos nove filijale",true);
   	dUnFil.show();
 	}

	void bBrisanjeFilijale_actionPerformed(ActionEvent e) {
   	DialogBrisanjeFilijale dBriFil = new DialogBrisanjeFilijale(this,"Brisanje filijale",true);
    	dBriFil.show();
 	}
	
	void bUnosPoslovnice_actionPerformed(ActionEvent e) {
    	DialogUnosPoslovnice dUnPos = new DialogUnosPoslovnice(this,"Unos nove poslovnice",true);
    	dUnPos.show();
  	}

  	void bBrisanjePoslovnice_actionPerformed(ActionEvent e) {
    	DialogBrisanjePoslovnice dBriPos = new DialogBrisanjePoslovnice(this,"Brisanje poslovnice",true);
    	dBriPos.show();
  	}
	
 	void bUnosRadnika_actionPerformed(ActionEvent e) {
    	DialogUnosRadnika dUnRad = new DialogUnosRadnika(this,"Unos novog radnika",true);
    	dUnRad.show();
  	}

  	void bBrisanjeRadnika_actionPerformed(ActionEvent e) {
    	DialogBrisanjeRadnika dBriRad = new DialogBrisanjeRadnika(this,"Brisanje radnika",true);
    	dBriRad.show();
   }

 	void bPregledRadnika_actionPerformed(ActionEvent e) {
    	DialogPregledRadnika dPrRad = new DialogPregledRadnika(this,"Pregled radnika",true);
    	dPrRad.show();
   }

}

