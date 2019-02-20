import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class TransactionLog extends JFrame implements ActionListener{
    JPanel panel=new JPanel(null);
    JLabel niblLogo, lblurl, transactionBar, transactionLog, sortby;
    JTextField txtsortby;
    JComboBox cbsortby;
    JButton btnrefresh;
    
    DefaultTableModel model= new DefaultTableModel();
    JTable table= new JTable(model);
    
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    
    public TransactionLog(){
       
//    Dimension scrsize= Toolkit.getDefaultToolkit().getScreenSize();
//    setSize(scrsize);
        setExtendedState(MAXIMIZED_BOTH);
    setVisible(true);
    setTitle("NIBL- Transaction Log");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        add(panel);
        panel.setBackground(Color.WHITE);
        
        niblLogo= new JLabel(new ImageIcon("images/nibl_logo.png"));
    panel.add(niblLogo);
    niblLogo.setBounds(130,10,370,64);
    
    lblurl= new JLabel("www.nibl.com.np");
    panel.add(lblurl);
    lblurl.setBounds(1100,55,100,25);
    lblurl.setForeground(Color.blue);
    
    transactionBar= new JLabel(new ImageIcon("images/transaction_bar.png"));
    panel.add(transactionBar);
    transactionBar.setBounds(130,76,1100,21);
    
    transactionLog= new JLabel("Transaction Log");
    panel.add(transactionLog,0);
    transactionLog.setBounds(600,73,150,25);
    transactionLog.setForeground(Color.WHITE);
    
    sortby=new JLabel("Sort By:");
    panel.add(sortby);
    sortby.setBounds(130,105,100,25);
    cbsortby=new JComboBox();
    panel.add(cbsortby);
    cbsortby.setBounds(180,105,160,25);
    cbsortby.addItem("");
    cbsortby.addItem("Transaction Type");
    cbsortby.addItem("Date");
    cbsortby.addItem("A/C No.");
    cbsortby.addItem("Remarks");
    cbsortby.addActionListener(this);
    
    txtsortby=new JTextField();
    panel.add(txtsortby);
    txtsortby.setBounds(350,105,475,25);
    addWindowListener( new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				txtsortby.requestFocus();
			  }
			} );
    txtsortby.getDocument().addDocumentListener(new MyDocumentListener());
    
    btnrefresh=new JButton(new ImageIcon("images/refresh.png"));
    panel.add(btnrefresh);
    btnrefresh.setBounds(835,105,25,25);
    btnrefresh.setToolTipText("Refresh");
    btnrefresh.addActionListener(this);
    
    model.addColumn("Transaction Type");
    model.addColumn("Date");
    model.addColumn("A/C No.");
    model.addColumn("Dr.");
    model.addColumn("Cr.");
    model.addColumn("Remarks");
    int v= ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h= ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JScrollPane jsp = new JScrollPane(table,v,h);
    panel.add(jsp);
    jsp.setBounds(130,138,1100,this.getHeight()-220);
    table.getColumn("Remarks").setMinWidth(350);
    table.setRowHeight(30);
    
    DisplayData();
    }
    
    public void DisplayData(){
       try{
           model.setRowCount(0);
        DBConnection dbc= new DBConnection();
        stmt=dbc.con.createStatement();
        rs=stmt.executeQuery("select * from transaction_details");          
        while(rs.next()){
        model.addRow(new Object[]{rs.getString("transactionType"), rs.getString("date"), rs.getString("accountNo"), rs.getString("debit"), rs.getString("credit"), rs.getString("remarks")});
        } 
    }    
    catch(Exception ex){
    JOptionPane.showMessageDialog(null,ex);
    }
       cbsortby.setSelectedItem("");
       txtsortby.setText("");
       txtsortby.setEditable(false);
    }
    
    public static void main(String args[]){
    new TransactionLog();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnrefresh){
        DisplayData();
        }
        
        if(e.getSource()==cbsortby){
        if(cbsortby.getSelectedItem()==""){
        DisplayData();
        }
        else{
        txtsortby.setEditable(true);
        }
        }
    }
    


   public class MyDocumentListener implements DocumentListener
		{
		
			String cbtxt;
			public void update(DocumentEvent de) {
			Document doc = (Document)de.getDocument();
			int length=doc.getLength();
			String strng=null;
			
			try
				{
					strng = doc.getText(0,length);
				}

			catch (BadLocationException ex)
				{
                                                                    System.out.println(ex);
                                
				}
				
			try
				{  
                                                                            switch(cbsortby.getSelectedItem().toString()){
                                                                                case "Transaction Type":
                                                                                    cbtxt="transactionType";
                                                                                    break;
                                                                                     case "Date":
                                                                                    cbtxt="date";
                                                                                    break;
                                                                                          case "A/C No.":
                                                                                    cbtxt="accountNo";
                                                                                    break;
                                                                                          case "Remarks":
                                                                                    cbtxt="remarks";
                                                                                    break;   
                                                                            }
					DBConnection dbc=new DBConnection();
					pstmt=dbc.con.prepareStatement("select * from transaction_details where "+cbtxt+" LIKE '%"+strng+"%'");
					rs=pstmt.executeQuery();
					
					// This method resets the number of rows to 0(zero) thus giving an illusion of table refresh
					
					model.setRowCount(0);
					
					while(rs.next())
						{
							model.addRow(new Object[] { rs.getString("transactionType"),rs.getString("date"),rs.getString("accountNo"),rs.getString("debit"),rs.getString("credit"),rs.getString("remarks")});
						}
										
					pstmt.close();
					dbc.con.close();
                                                                         
                                                                                
				}
			
			catch(Exception e)
				{
					JOptionPane.showMessageDialog(null,"Unidentified Error : "+e,"Display",JOptionPane.INFORMATION_MESSAGE);
				}
			
			}

        @Override
        public void insertUpdate(DocumentEvent de) {
           update(de);
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
           update(de);
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            update(de);
        }

    }

       

    }
 
