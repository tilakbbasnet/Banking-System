import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DepositForm extends JFrame implements ActionListener {
    JPanel pan=new JPanel();
    JLabel depositimg, depositbar, deposittext,
            mode, date, benificiarybar, benificiarytext, accountNo, accountName, amountFigure, amountWord, receivedFrombar, receivedFromtext, name, chequeNo, chequeAccount, savebar;
    JTextField txtdate, txtaccountNo, txtaccountName, txtamountFigure, txtamountWord, txtname, txtchequeNo, txtchequeAccount;
    JRadioButton cash, cheque;
    JButton btndeposit, btnreset;
    
    PreparedStatement pstmt;
    int result, chtrans;
    
    
    
    public DepositForm(){
        setVisible(true);
    setSize(550,477);
        setResizable(false);
        setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    add(pan);
    pan.setLayout(null);
    pan.setBackground(Color.WHITE);
    
    depositimg=new JLabel(new ImageIcon("images/create_account.png"));
    pan.add(depositimg);
    depositimg.setBounds(20,10,280,41);
    
    depositbar=new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(depositbar);
    depositbar.setBounds(0,47,550,45);
    deposittext=new JLabel("Cash/Cheque Deposit");
    pan.add(deposittext,0);
    deposittext.setBounds(20,57,200,25);
    deposittext.setForeground(Color.WHITE);
    
    mode=new JLabel("Mode:");
    pan.add(mode);
    mode.setBounds(20,85,100,25);
    cash=new JRadioButton("Cash");
    pan.add(cash);
    cash.setBounds(60,85,60,25);
    cash.setBackground(Color.WHITE);
    cash.addActionListener(this);
    cheque=new JRadioButton("Cheque");
    pan.add(cheque);
    cheque.setBounds(120,85,80,25);
    cheque.setBackground(Color.WHITE);
    cheque.addActionListener(this);
    
    date=new JLabel("Date");
    pan.add(date);
    date.setBounds(360,85,80,25);
    txtdate=new JTextField("yyyy-mm-dd");
    pan.add(txtdate);
    txtdate.setBounds(395,85,130,25);
    
    benificiarybar=new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(benificiarybar);
    benificiarybar.setBounds(0,112,550,25);
    benificiarytext=new JLabel("Benificiary");
    pan.add(benificiarytext,0);
    benificiarytext.setBounds(20,112,100,25);
    benificiarytext.setForeground(Color.WHITE);
    
    accountNo=new JLabel("A/C No.");
    pan.add(accountNo);
    accountNo.setBounds(20,140,100,25);
    txtaccountNo=new JTextField();
    pan.add(txtaccountNo);
    txtaccountNo.setBounds(180,140,345,25);
    
    accountName=new JLabel("A/C Name");
    pan.add(accountName);
    accountName.setBounds(20,175,100,25);
    txtaccountName=new JTextField();
    pan.add(txtaccountName);
    txtaccountName.setBounds(180,175,345,25);
    
    amountFigure=new JLabel("Amount in figure");
    pan.add(amountFigure);
    amountFigure.setBounds(20,210,100,25);
    txtamountFigure=new JTextField();
    pan.add(txtamountFigure);
    txtamountFigure.setBounds(180,210,345,25);
    
    amountWord=new JLabel("Amount in words");
    pan.add(amountWord);
    amountWord.setBounds(20,245,100,25);
    txtamountWord=new JTextField();
    pan.add(txtamountWord);
    txtamountWord.setBounds(180,245,345,25);
    
    receivedFrombar=new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(receivedFrombar);
    receivedFrombar.setBounds(0,280,550,25);
    receivedFromtext=new JLabel("Received From");
    pan.add(receivedFromtext,0);
    receivedFromtext.setBounds(20,280,100,25);
    receivedFromtext.setForeground(Color.WHITE);
    
    name=new JLabel("Name");
    pan.add(name);
    name.setBounds(20,308,100,25);
    txtname=new JTextField();
    pan.add(txtname);
    txtname.setBounds(180,308,345,25);
    
     chequeNo=new JLabel("Cheque No");
    pan.add(chequeNo);
    chequeNo.setBounds(20,343,100,25);
    txtchequeNo=new JTextField();
    pan.add(txtchequeNo);
    txtchequeNo.setBounds(180,343,345,25);
    
     chequeAccount=new JLabel("A/C No.");
    pan.add(chequeAccount);
    chequeAccount.setBounds(20,378,100,25);
    txtchequeAccount=new JTextField();
    pan.add(txtchequeAccount);
    txtchequeAccount.setBounds(180,378,345,25);
    
    savebar=new JLabel(new ImageIcon("images/save_bar.png"));
    pan.add(savebar);
    savebar.setBounds(0,410,550,40);
    
    btndeposit=new JButton("Deposit");
    pan.add(btndeposit,0);
    btndeposit.setBounds(180,415,80,25);
    btndeposit.addActionListener(this);
    
    btnreset=new JButton("Reset");
    pan.add(btnreset,0);
    btnreset.setBounds(270,415,80,25);
    btnreset.addActionListener(this);
    
    cash.setSelected(true);
    txtchequeNo.setEditable(false);
    txtchequeAccount.setEditable(false);
    }
    
     public static void main(String args[]){
    new DepositForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cash){
        cheque.setSelected(false);
        txtchequeNo.setText("");
        txtchequeAccount.setText("");
        txtchequeNo.setEditable(false);
        txtchequeAccount.setEditable(false);
        }
        if(e.getSource()==cheque){
        cash.setSelected(false);
        txtchequeNo.setEditable(true);
        txtchequeAccount.setEditable(true);
        }
        
        if(e.getSource()==btnreset){
        cash.setSelected(true);
        cheque.setSelected(false);
        txtdate.setText("yyyy-mm-dd");
        txtaccountNo.setText("");
        txtaccountName.setText("");
        txtamountFigure.setText("");
        txtamountWord.setText("");
        txtname.setText("");
        txtchequeNo.setText("");
        txtchequeAccount.setText("");
        txtchequeNo.setEditable(false);
        txtchequeAccount.setEditable(false);
        }
        
        if(e.getSource()==btndeposit){
        try{
            ///////////////////////////////////////////////cash deposit////////////////////////////////////////////////////////////////
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("update customer_accounts set amount=amount+? where accountNo=? ");
            pstmt.setDouble(1,Double.parseDouble(txtamountFigure.getText()));
            pstmt.setInt(2,Integer.parseInt(txtaccountNo.getText()));
            result=pstmt.executeUpdate();
           if(result>0){
           JOptionPane.showMessageDialog(null,"Cash Deposited");
           }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
        //////////////////////////////////////////////////add to transaction log////////////////////////////////////////////////////////
        try{
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("insert into transaction_details (transactionType, date, accountNo, debit, credit, remarks) values (?,?,?,?,?,?)");
             String modetext, transtext;
        if(cash.isSelected()){
        transtext="Cash Deposit";
        modetext="Cash Deposited by "+txtname.getText();
        }
        else{
            chtrans=1;
            transtext="Cheque Deposit";
        modetext="Cheque Deposited by "+txtname.getText()+" via Cheque No. "+txtchequeNo.getText()+" & A/C No. "+txtchequeAccount.getText();
        }
        pstmt.setString(1,transtext);
        pstmt.setString(2,txtdate.getText());
        pstmt.setInt(3,Integer.parseInt(txtaccountNo.getText()));
        pstmt.setDouble(4,Double.parseDouble(txtamountFigure.getText()));
        pstmt.setDouble(5,0);
        pstmt.setString(6,modetext);
        result=pstmt.executeUpdate();
        if(result>0){
        JOptionPane.showMessageDialog(null, "Added to Transaction Log");
        }
        ///////////////////////////////////////////////////////////cheque a/c update////////////////////////////////////////////////////////////////////////////////
        if(chtrans==1){
        try{
         pstmt=dbc.con.prepareStatement("update customer_accounts set amount=amount-? where accountNo=? ");
            pstmt.setDouble(1,Double.parseDouble(txtamountFigure.getText()));
            pstmt.setInt(2,Integer.parseInt(txtchequeAccount.getText()));
            result=pstmt.executeUpdate();
           if(result>0){
           JOptionPane.showMessageDialog(null,"Cheque A/C Updated");
           pstmt=dbc.con.prepareStatement("insert into transaction_details (transactionType, date, accountNo, debit, credit, remarks) values (?,?,?,?,?,?)");
            pstmt.setString(1,"Cash Transfer");
        pstmt.setString(2,txtdate.getText());
        pstmt.setInt(3,Integer.parseInt(txtchequeAccount.getText()));
        pstmt.setDouble(4,0);
        pstmt.setDouble(5,Double.parseDouble(txtamountFigure.getText()));
        pstmt.setString(6,"Cash Transferred to A/C No. "+txtaccountNo.getText()+" via Cheque No. "+txtchequeNo.getText());
        result=pstmt.executeUpdate();
        if(result>0){
        JOptionPane.showMessageDialog(null, "Added to Transaction Log");
        }
           
        }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null,ex);}
        }
    }
    }
    
   
 
