import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EditAccount extends JDialog implements ActionListener {
    JLabel editlabel, edittxt, accountNo;
    JTextField txtaccountNo;
    JButton btnedit;
    JPanel pan=new JPanel();
    PreparedStatement pstmt;
    ResultSet rs;
    
    public EditAccount(){
//        Font ft= new Font("",Font.BOLD,15);
        setSize(500,115);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        
        add(pan);
        pan.setBackground(Color.WHITE);
        pan.setLayout(null);
        
        editlabel=new JLabel(new ImageIcon("images/edit_account.png"));
        pan.add(editlabel);
        editlabel.setBounds(0,0,500,30);
        
        edittxt=new JLabel("Edit Account");
        pan.add(edittxt,0);
        edittxt.setBounds(20,4,300,25);
        edittxt.setForeground(Color.WHITE);
//        edittxt.setFont(ft);
        
        accountNo=new JLabel("A/C No.");
        pan.add(accountNo);
        accountNo.setBounds(20,45,100,25);
        
        txtaccountNo=new JTextField();
        pan.add(txtaccountNo);
        txtaccountNo.setBounds(100,45,270,25);
        
        btnedit=new JButton("Edit");
        pan.add(btnedit);
        btnedit.setBounds(382,45,90,25);
        btnedit.addActionListener(this);
    
    }
    
    public static void main(String args[]){
    new EditAccount();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnedit){
        try{
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("select * from customer_accounts where accountNo=?");
            pstmt.setString(1,txtaccountNo.getText());
            rs=pstmt.executeQuery();
            if(rs.next()){
                CreateAccount crt=new CreateAccount();
                crt.txtaccountNo.setText(rs.getString("accountNo"));
                  crt.txtcustomerId.setText(rs.getString("customerId"));
                  crt.txtcustomerId.setEditable(false);
        crt.cbaccountType.setSelectedItem(rs.getString("accountType"));
        crt.cbaccountType.setEnabled(false);
        crt.cbaccountName.setSelectedItem(rs.getString("accountName"));
        crt.cbaccountName.setEnabled(false);
        crt.txtamount.setText(rs.getString("amount"));
        crt.txtamount.setEditable(false);
        crt.txtdate.setText(rs.getString("createDate"));
        crt.txtdate.setEditable(false);
        crt.txtnomineeName.setText(rs.getString("nameOfNominee"));
        crt.txtmailingAddress.setText(rs.getString("mailingAddress"));
        crt.txtnationality.setText(rs.getString("nationality"));
        crt.cbgender.setSelectedItem(rs.getString("gender"));
        crt.txtcitizenshipNo.setText(rs.getString("citizenshipNo"));
        crt.txtpassportNo.setText(rs.getString("passportNo"));
        crt.txtmobile.setText(rs.getString("mobile"));
        crt.txtphone.setText(rs.getString("phone"));
        crt.txtemail.setText(rs.getString("emailAddress"));
        boolean status=rs.getBoolean("isActive");
        crt.checkActive.setSelected(status);
     
       
       crt.btnsave.setText("Update");
       crt.btnsave.setBounds(170,620,200,25);
      crt.btnreset.hide();
       this.hide();
            }
        }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);
        }
        }
        
    }
    
}
