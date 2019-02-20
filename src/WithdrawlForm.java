import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class WithdrawlForm extends JFrame implements ActionListener{
    JPanel pan=new JPanel();
    JLabel withimg, withbar, withtext, date, accountNo, chequeNo, amountFigure, amountWord, benificiarybar, benificiarytext, name, savebar;
    JTextField txtdate, txtaccountNo, txtaccountName, txtamountFigure, txtamountWord, txtname, txtchequeNo;
    JButton btnwithdraw, btnreset;
    
    PreparedStatement pstmt;
    int result, chtrans;
    
    
    
    public WithdrawlForm(){
        setVisible(true);
    setSize(550,388);
        setResizable(false);
        setLocationRelativeTo(null);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    add(pan);
    pan.setLayout(null);
    pan.setBackground(Color.WHITE);
    
    withimg=new JLabel(new ImageIcon("images/create_account.png"));
    pan.add(withimg);
    withimg.setBounds(20,10,280,41);
    
    withbar=new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(withbar);
    withbar.setBounds(0,47,550,45);
    withtext=new JLabel("Cash Withdrawl");
    pan.add(withtext,0);
    withtext.setBounds(20,57,200,25);
    withtext.setForeground(Color.WHITE);
    
    date=new JLabel("Date");
    pan.add(date);
    date.setBounds(360,85,80,25);
    txtdate=new JTextField("yyyy-mm-dd");
    pan.add(txtdate);
    txtdate.setBounds(395,85,130,25);
    
    accountNo=new JLabel("A/C No.");
    pan.add(accountNo);
    accountNo.setBounds(20,120,100,25);
    txtaccountNo=new JTextField();
    pan.add(txtaccountNo);
    txtaccountNo.setBounds(180,120,345,25);
    
    chequeNo=new JLabel("Cheque No.");
    pan.add(chequeNo);
    chequeNo.setBounds(20,155,100,25);
    txtchequeNo=new JTextField();
    pan.add(txtchequeNo);
    txtchequeNo.setBounds(180,155,345,25);
    
    amountFigure=new JLabel("Amount in figure");
    pan.add(amountFigure);
    amountFigure.setBounds(20,190,100,25);
    txtamountFigure=new JTextField();
    pan.add(txtamountFigure);
    txtamountFigure.setBounds(180,190,345,25);
    
    amountWord=new JLabel("Amount in words");
    pan.add(amountWord);
    amountWord.setBounds(20,225,100,25);
    txtamountWord=new JTextField();
    pan.add(txtamountWord);
    txtamountWord.setBounds(180,225,345,25);
    
    benificiarybar=new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(benificiarybar);
    benificiarybar.setBounds(0,260,550,25);
    benificiarytext=new JLabel("Benificiary");
    pan.add(benificiarytext,0);
    benificiarytext.setBounds(20,260,100,25);
    benificiarytext.setForeground(Color.WHITE);
    
    name=new JLabel("Name");
    pan.add(name);
    name.setBounds(20,288,100,25);
    txtname=new JTextField();
    pan.add(txtname);
    txtname.setBounds(180,288,345,25);
    
    savebar=new JLabel(new ImageIcon("images/save_bar.png"));
    pan.add(savebar);
    savebar.setBounds(0,320,550,40);
    
    btnwithdraw=new JButton("Withdraw");
    pan.add(btnwithdraw,0);
    btnwithdraw.setBounds(180,325,100,25);
    btnwithdraw.addActionListener(this);
    
    btnreset=new JButton("Reset");
    pan.add(btnreset,0);
    btnreset.setBounds(290,325,80,25);
    btnreset.addActionListener(this);
    
  
    }
    
     public static void main(String args[]){
    new WithdrawlForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnwithdraw){
         try{
            ///////////////////////////////////////////////cash deposit////////////////////////////////////////////////////////////////
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("update customer_accounts set amount=amount-? where accountNo=? ");
            pstmt.setDouble(1,Double.parseDouble(txtamountFigure.getText()));
            pstmt.setInt(2,Integer.parseInt(txtaccountNo.getText()));
            result=pstmt.executeUpdate();
           if(result>0){
           JOptionPane.showMessageDialog(null,"Cash Withdrawn");
           }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        
        //////////////////////////////////////////////////add to transaction log////////////////////////////////////////////////////////
        try{
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("insert into transaction_details (transactionType, date, accountNo, debit, credit, remarks) values (?,?,?,?,?,?)");
             
        pstmt.setString(1,"Cash Withdrawl");
        pstmt.setString(2,txtdate.getText());
        pstmt.setInt(3,Integer.parseInt(txtaccountNo.getText()));
        pstmt.setDouble(4,0);
        pstmt.setDouble(5,Double.parseDouble(txtamountFigure.getText()));
        pstmt.setString(6,"Cash Withdrawn by "+txtname.getText()+" via Cheque No. "+txtchequeNo.getText());
        result=pstmt.executeUpdate();
        if(result>0){
        JOptionPane.showMessageDialog(null, "Added to Transaction Log");
        }
        }
        catch (Exception ex){}
        }
        
        if(e.getSource()==btnreset){
        txtdate.setText("yyyy-mm-dd");
        txtaccountNo.setText("");
        txtamountFigure.setText("");
        txtamountWord.setText("");
        txtname.setText("");
        txtchequeNo.setText("");
        }
    }

   
    }
    
   
 
