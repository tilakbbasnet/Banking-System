import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CreateAccount extends JDialog implements ActionListener {
    JLabel createAccountimg, createAccountbar, createAccounttext, accountNo, customerId, accountType, accountName, amount, date,
            nomineedetailsbar, nomineedetailstext, nomineeName, mailingAddress, nationality, gender, citizenshipNo, passportNo, mobile, phone, email, savebar;
    JTextField txtaccountNo, txtcustomerId, txtamount, txtdate,
            txtnomineeName, txtmailingAddress, txtnationality, txtcitizenshipNo, txtpassportNo, txtmobile, txtphone, txtemail;
    JComboBox cbaccountType, cbaccountName, cbgender;
    JCheckBox checkActive;
    JButton btnsave, btnreset;
    JPanel pan= new JPanel();
    
    PreparedStatement pstmt, pstmt2;
    Statement stmt;
    ResultSet rs, rs2;
    int result;
    
    public CreateAccount(){
    setVisible(true);
    setSize(480,685);
        setResizable(false);
        setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    add(pan);
    pan.setLayout(null);
    pan.setBackground(Color.WHITE);
      
    createAccountimg=new JLabel(new ImageIcon("images/create_account.png"));
    pan.add(createAccountimg);
    createAccountimg.setBounds(20,10,280,41);
    
    createAccountbar=new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(createAccountbar);
    createAccountbar.setBounds(0,47,480,45);
    createAccounttext=new JLabel("Create New Account");
    pan.add(createAccounttext,0);
    createAccounttext.setBounds(20,57,200,25);
    createAccounttext.setForeground(Color.WHITE);
    
    accountNo=new JLabel("A/C No.");
    pan.add(accountNo);
    accountNo.setBounds(20,90,100,25);
    txtaccountNo=new JTextField();
    pan.add(txtaccountNo);
    txtaccountNo.setBounds(170,90,290,25);
    txtaccountNo.setEnabled(false);
    
    customerId=new JLabel("Customer ID");
    pan.add(customerId);
    customerId.setBounds(20,125,100,25);
    txtcustomerId=new JTextField();
    pan.add(txtcustomerId);
    txtcustomerId.setBounds(170,125,290,25);
    
    accountType=new JLabel("Account Type");
    pan.add(accountType);
    accountType.setBounds(20,160,100,25);
    cbaccountType=new JComboBox();
    pan.add(cbaccountType);
    cbaccountType.setBounds(170,160,290,25);
    cbaccountType.addItem("");
    cbaccountType.addActionListener(this);
    
    accountName=new JLabel("Account Name");
    pan.add(accountName);
    accountName.setBounds(20,195,100,25);
    cbaccountName=new JComboBox();
    pan.add(cbaccountName);
    cbaccountName.setBounds(170,195,290,25);
    
    amount=new JLabel("Initial Amount");
    pan.add(amount);
    amount.setBounds(20,230,100,25);
    txtamount=new JTextField();
    pan.add(txtamount);
    txtamount.setBounds(170,230,290,25);
    
    date=new JLabel("Date");
    pan.add(date);
    date.setBounds(20,265,100,25);
    txtdate=new JTextField();
    pan.add(txtdate);
    txtdate.setBounds(170,265,200,25);
    JLabel dateFormat=new JLabel("(yyyy-mm-dd)");
    pan.add(dateFormat);
    dateFormat.setBounds(380,265,100,25);
    dateFormat.setForeground(Color.GRAY);
    
    nomineedetailsbar=new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(nomineedetailsbar);
    nomineedetailsbar.setBounds(0,295,480,45);
    nomineedetailstext=new JLabel("Nominee Details");
    pan.add(nomineedetailstext,0);
    nomineedetailstext.setBounds(20,305,200,25);
    nomineedetailstext.setForeground(Color.WHITE);
    
    nomineeName=new JLabel("Name Of Nominee");
    pan.add(nomineeName);
    nomineeName.setBounds(20,338,150,25);
    txtnomineeName=new JTextField();
    pan.add(txtnomineeName);
    txtnomineeName.setBounds(170,338,290,25);
    
    mailingAddress=new JLabel("Mailing Address");
    pan.add(mailingAddress);
    mailingAddress.setBounds(20,373,100,25);
    txtmailingAddress=new JTextField();
    pan.add(txtmailingAddress);
    txtmailingAddress.setBounds(170,373,290,25);
    
    nationality=new JLabel("Nationality");
    pan.add(nationality);
    nationality.setBounds(20,408,100,25);
    txtnationality=new JTextField();
    pan.add(txtnationality);
    txtnationality.setBounds(170,408,120,25);
    
    gender=new JLabel("Gender");
    pan.add(gender);
    gender.setBounds(310,408,80,25);
    cbgender=new JComboBox();
    pan.add(cbgender);
    cbgender.setBounds(360,408,100,25);
    cbgender.addItem("");
    cbgender.addItem("Male");
    cbgender.addItem("Female");
    cbgender.addItem("Other");
    
    citizenshipNo=new JLabel("Citizenship No.");
    pan.add(citizenshipNo);
    citizenshipNo.setBounds(20,443,100,25);
    txtcitizenshipNo= new JTextField();
    pan.add(txtcitizenshipNo);
    txtcitizenshipNo.setBounds(170,443,290,25);
    
    passportNo=new JLabel("Passport No.");
    pan.add(passportNo);
    passportNo.setBounds(20,478,100,25);
    txtpassportNo= new JTextField();
    pan.add(txtpassportNo);
    txtpassportNo.setBounds(170,478,290,25);
    
    mobile=new JLabel("Mobile");
    pan.add(mobile);
    mobile.setBounds(20,513,100,25);
    txtmobile= new JTextField();
    pan.add(txtmobile);
    txtmobile.setBounds(170,513,290,25);
    
    phone=new JLabel("Phone");
    pan.add(phone);
    phone.setBounds(20,548,100,25);
    txtphone= new JTextField();
    pan.add(txtphone);
    txtphone.setBounds(170,548,290,25);
    
    email=new JLabel("Email Address");
    pan.add(email);
    email.setBounds(20,583,100,25);
    txtemail= new JTextField();
    pan.add(txtemail);
    txtemail.setBounds(170,583,290,25);
    
    checkActive=new JCheckBox("Active");
    pan.add(checkActive);
    checkActive.setBounds(20,620,60,25);
    
    savebar=new JLabel(new ImageIcon("images/save_bar.png"));
    pan.add(savebar);
    savebar.setBounds(0,618,480,40);
    
    btnsave=new JButton("Save");
    pan.add(btnsave,0);
    btnsave.setBounds(170,620,80,25);
    btnsave.addActionListener(this);
    
    btnreset=new JButton("Reset");
    pan.add(btnreset,0);
    btnreset.setBounds(260,620,80,25);
    btnreset.addActionListener(this);
    
    try{
        DBConnection dbc=new DBConnection();
        stmt=dbc.con.createStatement();
        rs=stmt.executeQuery("select * from account_details");
        while(rs.next()){
        cbaccountType.addItem(rs.getString("accountType"));
        }
    }
    catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
    
    
    
    }
    
    public static void main(String args[]){
    new CreateAccount();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnsave){
            if(btnsave.getText()!="Update"){
                ////////////////////////////////create account////////////////////////////////////////////////////////////////////////////
        try{
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("insert into customer_accounts (customerId, accountType, accountName, amount, createDate, nameOfNominee, mailingAddress, nationality, gender, citizenshipNo, passportNo, mobile, phone, emailAddress, isActive) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1,txtcustomerId.getText());
            pstmt.setString(2,cbaccountType.getSelectedItem().toString());
            pstmt.setString(3,cbaccountName.getSelectedItem().toString());
            pstmt.setDouble(4,Double.parseDouble(txtamount.getText()));
            pstmt.setString(5,txtdate.getText());
            pstmt.setString(6,txtnomineeName.getText());
            pstmt.setString(7, txtmailingAddress.getText());
            pstmt.setString(8, txtnationality.getText());
            pstmt.setString(9, cbgender.getSelectedItem().toString());
            pstmt.setString(10, txtcitizenshipNo.getText());
            pstmt.setString(11,txtpassportNo.getText());
            pstmt.setString(12,txtmobile.getText());
            pstmt.setString(13, txtphone.getText());
            pstmt.setString(14,txtemail.getText());
            boolean status;
            if(checkActive.isSelected()){ status=true;}
            else{
                status=false;
            }
            pstmt.setBoolean(15, status);
            
            result=pstmt.executeUpdate();
            if(result>0){
            try{
                pstmt2= dbc.con.prepareStatement("select * from customer_accounts where customerId=? and accountName=?");
                pstmt2.setString(1,txtcustomerId.getText());
                pstmt2.setString(2,cbaccountName.getSelectedItem().toString());
                rs2=pstmt2.executeQuery();
                if(rs2.next()){
                JOptionPane.showMessageDialog(null,"New Account Created with A/C No. "+rs2.getString("accountNo"));
                }
            }
            catch(Exception ex){JOptionPane.showMessageDialog(null, ex);            }
            }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
        
        ////////////////////////////////////////////////////////////add to transaction log///////////////////////////////////////////////////////////////////////
        try{
        DBConnection dbc=new DBConnection();
        pstmt=dbc.con.prepareStatement("insert into transaction_details (transactionType, date, accountNo, debit, credit, remarks) values (?,?,?,?,?,?)");
        pstmt.setString(1,"Account Create");
        pstmt.setString(2,txtdate.getText());
        pstmt.setInt(3,Integer.parseInt(rs2.getString("accountNo")));
        pstmt.setDouble(4,Double.parseDouble(txtamount.getText()));
        pstmt.setDouble(5,0);
        pstmt.setString(6,"New "+cbaccountName.getSelectedItem().toString()+" Created");
        result=pstmt.executeUpdate();
        if(result>0){
        JOptionPane.showMessageDialog(null, "Added to Transaction Log");
        }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
           
            else{
               try{
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("update customer_accounts set customerId=?, accountType=?, accountName=?, amount=?, createDate=?, nameOfNominee=?, mailingAddress=?, nationality=?, gender=?, citizenshipNo=?,passportNo=?, mobile=?, phone=?, emailAddress=?,isActive=? where accountNo=?");
            pstmt.setString(1,txtcustomerId.getText());
            pstmt.setString(2,cbaccountType.getSelectedItem().toString());
            pstmt.setString(3,cbaccountName.getSelectedItem().toString());
            pstmt.setDouble(4,Double.parseDouble(txtamount.getText()));
            pstmt.setString(5,txtdate.getText());
            pstmt.setString(6,txtnomineeName.getText());
            pstmt.setString(7, txtmailingAddress.getText());
            pstmt.setString(8, txtnationality.getText());
            pstmt.setString(9, cbgender.getSelectedItem().toString());
            pstmt.setString(10, txtcitizenshipNo.getText());
            pstmt.setString(11,txtpassportNo.getText());
            pstmt.setString(12,txtmobile.getText());
            pstmt.setString(13, txtphone.getText());
            pstmt.setString(14,txtemail.getText());
            boolean status;
            if(checkActive.isSelected()){ status=true;}
            else{
                status=false;
            }
            pstmt.setBoolean(15, status);
            pstmt.setInt(16,Integer.parseInt(txtaccountNo.getText()));
            int result=pstmt.executeUpdate();
            if(result>0){
            JOptionPane.showMessageDialog(null, "Account Updated");
            }
            if(status==false){
             ////////////////////////////////////////////////////////////add to transaction log///////////////////////////////////////////////////////////////////////
        try{
        pstmt=dbc.con.prepareStatement("insert into transaction_details (transactionType, date, accountNo, debit, credit, remarks) values (?,?,?,?,?,?)");
        pstmt.setString(1,"Account Deactive");
        pstmt.setString(2,txtdate.getText());
        pstmt.setInt(3,Integer.parseInt(txtaccountNo.getText()));
        pstmt.setDouble(4,0);
        pstmt.setDouble(5,0);
        pstmt.setString(6,"Account Deactivated");
        result=pstmt.executeUpdate();
        if(result>0){
        JOptionPane.showMessageDialog(null, "Added to Transaction Log");
        }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
         /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
            }
            }
        
        
        if(e.getSource()==btnreset){
        txtcustomerId.setText("");
        cbaccountType.setSelectedItem("");
//        cbaccountName.setSelectedItem("");
        txtamount.setText("");
        txtdate.setText("");
        txtnomineeName.setText("");
        txtmailingAddress.setText("");
        txtnationality.setText("");
        cbgender.setSelectedItem("");
        txtcitizenshipNo.setText("");
        txtpassportNo.setText("");
        txtmobile.setText("");
        txtphone.setText("");
        txtemail.setText("");
        }
        
        if(e.getSource()==cbaccountType){
        try{
            cbaccountName.removeAllItems();
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("select * from account_details where accountType=?");
            pstmt.setString(1,cbaccountType.getSelectedItem().toString());
            rs=pstmt.executeQuery();
            while(rs.next()){
            cbaccountName.addItem(rs.getString("accountName"));
            }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        }
    }
    
}
