import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class ViewCustomer extends JFrame implements ActionListener{
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
    
    public ViewCustomer(){
       
//    Dimension scrsize= Toolkit.getDefaultToolkit().getScreenSize();
//    setSize(scrsize);
        setExtendedState(MAXIMIZED_BOTH);
    setVisible(true);
    setTitle("NIBL- View Customer");
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
    
    transactionLog= new JLabel("View Customer");
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
    cbsortby.addItem("A/C No.");
    cbsortby.addItem("Customer Id");
    cbsortby.addItem("First Name");
    cbsortby.addItem("Last Name");
    cbsortby.addItem("Account Type");
    cbsortby.addItem("Account Name");
    cbsortby.addItem("Amount");
    cbsortby.addItem("Date Created");
    cbsortby.addItem("Mailing Address");
    cbsortby.addItem("Phone");
    cbsortby.addItem("Mobile");
    cbsortby.addItem("Gender");
    cbsortby.addItem("Citizenship No.");
    cbsortby.addItem("Passport No.");
    cbsortby.addItem("Date Of Birth");
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
    
    model.addColumn("A/C No.");
    model.addColumn("Customer Id");
    model.addColumn("Name");
    model.addColumn("Account Type");
    model.addColumn("Account Name.");
    model.addColumn("Amount");
    model.addColumn("Date Created");
    model.addColumn("Mailing Address");
    model.addColumn("Phone");
    model.addColumn("Mobile");
    model.addColumn("Gender");
    model.addColumn("Citizenship No.");
    model.addColumn("Passport No.");
    model.addColumn("Date Of Birth");
    int v= ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h= ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JScrollPane jsp = new JScrollPane(table,v,h);
    panel.add(jsp);
    jsp.setBounds(130,138,1100,this.getHeight()-220);
    table.setRowHeight(30);
    DisplayData();
    }
    
    public void DisplayData(){
       try{
           model.setRowCount(0);
        DBConnection dbc= new DBConnection();
        stmt=dbc.con.createStatement();
        rs=stmt.executeQuery("SELECT accountNo,a.customerId, firstname, lastname, accountType, accountName, amount, createDate, mailingAddress1, phone1, mobile1, a.gender, citizenshipNumber, passportNumber, dateOfBirth  FROM customer_accounts AS a JOIN customer_details AS d ON a.customerId=d.customerId");          
        while(rs.next()){
        model.addRow(new Object[]{rs.getString("accountNo"), rs.getString("a.customerId"), rs.getString("firstname")+" "+rs.getString("lastname"), rs.getString("accountType"), rs.getString("accountName"), rs.getString("amount"),rs.getString("createDate"),rs.getString("mailingAddress1"), rs.getString("phone1"), rs.getString("mobile1"), rs.getString("a.gender"), rs.getString("citizenshipNumber"), rs.getString("passportNumber"), rs.getString("dateOfBirth")});
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
    new ViewCustomer();
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
                                                                                case "A/C No.":
                                                                                    cbtxt="accountNo";
                                                                                    break;
                                                                                     case "Customer Id":
                                                                                    cbtxt="a.customerId";
                                                                                    break;
                                                                                          case "First Name":
                                                                                    cbtxt="firstname";
                                                                                    break;
                                                                                          case "Last Name":
                                                                                    cbtxt="lastname";
                                                                                    break;   
                                                                                           case "Account Type":
                                                                                    cbtxt="accountType";
                                                                                    break; 
                                                                                      case "Account Name":
                                                                                    cbtxt="accountName";
                                                                                    break;
                                                                                     case "Amount":
                                                                                    cbtxt="amount";
                                                                                    break;
                                                                                       case "Date Created":
                                                                                    cbtxt="createDate";
                                                                                    break;
                                                                                       case "Mailing Address":
                                                                                    cbtxt="mailingAddress1";
                                                                                    break;
                                                                                       case "Phone":
                                                                                    cbtxt="phone1";
                                                                                    break;
                                                                                       case "Mobile":
                                                                                    cbtxt="mobile1";
                                                                                    break;
                                                                                       case "Gender":
                                                                                    cbtxt="a.gender";
                                                                                    break;
                                                                                       case "Citizenship No.":
                                                                                    cbtxt="citizenshipNumber";
                                                                                    break;
                                                                                       case "Passport No.":
                                                                                    cbtxt="passportNumber";
                                                                                    break;
                                                                                       case "Date Of Birth":
                                                                                    cbtxt="dateOfBirth";
                                                                                    break;
                                                                            }
					DBConnection dbc=new DBConnection();
					pstmt=dbc.con.prepareStatement("SELECT accountNo,a.customerId, firstname, lastname, accountType, accountName, amount, createDate, mailingAddress1, phone1, mobile1, a.gender, citizenshipNumber, passportNumber, dateOfBirth  FROM customer_accounts AS a JOIN customer_details AS d ON a.customerId=d.customerId where "+cbtxt+" LIKE '%"+strng+"%'");
					rs=pstmt.executeQuery();
					
					// This method resets the number of rows to 0(zero) thus giving an illusion of table refresh
					
					model.setRowCount(0);
					
					while(rs.next())
						{
                                                                                                 model.addRow(new Object[]{rs.getString("accountNo"), rs.getString("a.customerId"), rs.getString("firstname")+" "+rs.getString("lastname"), rs.getString("accountType"), rs.getString("accountName"), rs.getString("amount"),rs.getString("createDate"),rs.getString("mailingAddress1"), rs.getString("phone1"), rs.getString("mobile1"), rs.getString("a.gender"), rs.getString("citizenshipNumber"), rs.getString("passportNumber"), rs.getString("dateOfBirth")});
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
 
