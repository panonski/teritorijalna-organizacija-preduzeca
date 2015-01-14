/**************************************************************************/
//                                                                         /
//    Program koji komunicira sa bazom podataka u preduzecu                /
//    Ispitni zadatak iz predmeta Baze podataka II                         /
//                                                                         /
//    Dejan Acanski, C3                                                    /
//                                                                         /	
/**************************************************************************/
//                                                                         /	
//		Klasa koja implementira tabelarni pregled podataka 						/
//		o radnicima	u datoj poslovnici													/	
//                                                                         /	
/**************************************************************************/




package preduzece;

import javax.swing.table.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.JTable;


public class TModelRadnika extends AbstractTableModel {
	
	
  private Vector data;
	
  // dajemo nazive kolona
  private String[] columnNames = {"MBR","Ime","Prezime","Adresa","Mesto","Broj telefona",
                                    "Datum zaposlenja","Datum rodjenja","Pol"};
  JTable jTab;

  public TModelRadnika(JTable jTab){
    this.jTab = jTab;
    data = new Vector();
  }


  public void joinData(Vector data){
    this.data = data;
    fireTableDataChanged();
  }


  public void addItem(int row, Vector item){
    if (row==-1)
      data.addElement(item);
    else
      data.add(row,item);
    fireTableDataChanged();
  }


  public void removeItem(int row){
    if ((row>=0) && (row<=data.size()-1) && (data.size()>0)){
      jTab.editingCanceled(new ChangeEvent(this));
      data.removeElementAt(row);
      fireTableDataChanged();
    }
  }


  public int getColumnCount(){
    return columnNames.length;
  }


  public void setValueAt(Object val, int row, int col){
  }

  // u zavisnosti od kolone, imamo razlicite tipove podataka
  public Object getValueAt(int row, int col){
	  if ( ((col>=1) && (col<=5)) || (col==8) )
			return (String)((Vector)data.get(row)).get(col);  
    else if ((col==6) || (col==7))
    	return (java.sql.Date)((Vector)data.get(row)).get(col);
	else
      return (Integer)((Vector)data.get(row)).get(col); 
			
    }


  public int getRowCount(){
    return data.size();
  }


  public String getColumnName(int col){
    return columnNames[col];
  }


  public boolean isCellEditable(int row, int col){
    return false;
  }


  private Vector getRow(int row){
    return (Vector)data.elementAt(row);
  }


  public void moveUp(int row){
    Vector dataRow = getRow(row);
    removeItem(row);
    addItem(row-1,dataRow);
  }


  public void moveDown(int row){
    Vector dataRow = getRow(row);
    removeItem(row);
    addItem(row+1,dataRow);
  }

	
  public Vector getData(){
    return data;
  }


  public void rejoinData(Vector data){
    this.data = data;
    fireTableDataChanged();
  }
	
	
  public void initColumns(){
    jTab.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }
	
}




