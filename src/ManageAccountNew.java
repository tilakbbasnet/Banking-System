import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class ManageAccountNew extends JDialog implements ActionListener {
    JLabel editbar, edittext, accountType, accountName, minimumBalance, interestRate, minimumDuration;
    JTextField txtaccountName, txtminimumBalance, txtinterestRate, txtminimumDuration;
    JComboBox cbaccountType;
    JButton btnsave, btnreset;
    
    JPanel pan= new JPanel();
    
    PreparedStatement pstmt;
    int result;
    
    public ManageAccountNew(){
    setSize(395,325);
    setLocationRelativeTo(null);
    setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    add(pan);
    pan.setLayout(null);
    pan.setBackground(Color.WHITE);
    
    editbar=new JLabel(new ImageIcon("images/manage_account.png"));
    pan.add(editbar);
    editbar.setBounds(0,0,400,34);
    edittext=new JLabel("New Account");
    pan.add(edittext,0);
    edittext.setBounds(20,5,200,25);
    edittext.setForeground(Color.WHITE);
    
    accountType=new JLabel("Account Type");
    pan.add(accountType);
    accountType.setBounds(20,45,100,25);
    cbaccountType=new JComboBox();
    pan.add(cbaccountType);
    cbaccountType.setBounds(150,45,210,25);
    cbaccountType.addItem("");
    cbaccountType.addItem("Current");
    cbaccountType.addItem("Saving");
    cbaccountType.addItem("Fixed");
    
    accountName=new JLabel("Account Name");
    pan.add(accountName);
    accountName.setBounds(20,85,100,25);
    txtaccountName=new JTextField();
    pan.add(txtaccountName);
    txtaccountName.setBounds(150,85,210,25);
    
    minimumBalance=new JLabel("Minimum Balance");
    pan.add(minimumBalance);
    minimumBalance.setBounds(20,125,150,25);
    txtminimumBalance=new JTextField("0");
    pan.add(txtminimumBalance);
    txtminimumBalance.setBounds(150,125,210,25);
    
    interestRate=new JLabel("Interest Rate");
    pan.add(interestRate);
    interestRate.setBounds(20,165,150,25);
    txtinterestRate=new JTextField("0");
    pan.add(txtinterestRate);
    txtinterestRate.setBounds(150,165,80,25);
    JLabel txtrate=new JLabel("%");
    pan.add(txtrate);
    txtrate.setBounds(235,165,35,25);
    
    minimumDuration=new JLabel("Minimum Duration");
    pan.add(minimumDuration);
    minimumDuration.setBounds(20,205,150,25);
    txtminimumDuration=new JTextField();
    pan.add(txtminimumDuration);
    txtminimumDuration.setBounds(150,205,210,25);
    
    btnsave=new JButton("Save");
    pan.add(btnsave);
    btnsave.setBounds(150,245,100,25);
    btnsave.addActionListener(this);
    
    btnreset=new JButton("Reset");
    pan.add(btnreset);
    btnreset.setBounds(260,245,100,25);
    btnreset.addActionListener(this);
    }
    
    public static void main(String args[]){
    new ManageAccountNew();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnsave){
            if(btnsave.getText()!="Update"){
        try{
        DBConnection dbc= new DBConnection();
        pstmt=dbc.con.prepareStatement("insert into account_details (accountType, accountName, minimumBalance, interestRate, minimumDuration) values(?,?,?,?,?)");
        pstmt.setString(1, cbaccountType.getSelectedItem().toString());
        pstmt.setString(2,txtaccountName.getText());
        pstmt.setDouble(3,Double.parseDouble(txtminimumBalance.getText()));
        pstmt.setDouble(4,Double.parseDouble(txtinterestRate.getText()));
        pstmt.setString(5,txtminimumDuration.getText());
        result=pstmt.executeUpdate();
        if(result>0){
        JOptionPane.showMessageDialog(null, "Account Added");
        }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        }
            else{
            try{
        DBConnection dbc= new DBConnection();
        pstmt=dbc.con.prepareStatement("update account_details set minimumBalance=?, interestRate=?, minimumDuration=? where accountType=? and accountName=?");
        pstmt.setDouble(1,Double.parseDouble(txtminimumBalance.getText()));
        pstmt.setDouble(2,Double.parseDouble(txtinterestRate.getText()));
        pstmt.setString(3,txtminimumDuration.getText());
        pstmt.setString(4, cbaccountType.getSelectedItem().toString());
        pstmt.setString(5,txtaccountName.getText());
        result=pstmt.executeUpdate();
        if(result>0){
        JOptionPane.showMessageDialog(null, "Account Updated");
        }
        }
        catch(Exception ex){JOptionPane.showMessageDialog(null, ex);}
        }
            
        }
        
        if(e.getSource()==btnreset){
            cbaccountType.setSelectedItem("");
            txtaccountName.setText("");
            txtminimumBalance.setText("0");
            txtinterestRate.setText("0");
            txtminimumDuration.setText("");
        }
        
    }
    
}
