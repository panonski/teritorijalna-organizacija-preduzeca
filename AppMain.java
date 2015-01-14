/**************************************************************************/
//                                                                         /
//    Program koji komunicira sa bazom podataka u preduzecu                /
//    Ispitni zadatak iz predmeta Baze podataka II                         /
//                                                                         /
//    Dejan Acanski, C3                                                    /
//                                                                         /	
/**************************************************************************/
//                                                                         /	
//    AppMain je glavna klasa - sluzi za pokretanje programa               /	
//                                                                         /	
/**************************************************************************/


package preduzece;

import javax.swing.*;
import java.awt.*;

public class AppMain {
  private boolean packFrame = false;


  // konstruktor aplikacije
  public AppMain() {
    FrameMain frame = new FrameMain();
		
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
		
    // odredjivanje dimenzija i centriranje prozora
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    frame.setVisible(true);
    DBConnection dBC = new DBConnection();
  }
	
	
  // pokretanje
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new AppMain();	
  }
}

