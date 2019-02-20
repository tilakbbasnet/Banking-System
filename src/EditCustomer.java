import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class EditCustomer extends JDialog implements ActionListener {
    JLabel editlabel, edittxt, customerId;
    JTextField txtcustomerId;
    JButton btnedit;
    JPanel pan=new JPanel();
    PreparedStatement pstmt;
    ResultSet rs;
    
    public EditCustomer(){
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
        
        customerId=new JLabel("Customer ID");
        pan.add(customerId);
        customerId.setBounds(20,45,100,25);
        
        txtcustomerId=new JTextField();
        pan.add(txtcustomerId);
        txtcustomerId.setBounds(100,45,270,25);
        
        btnedit=new JButton("Edit");
        pan.add(btnedit);
        btnedit.setBounds(382,45,90,25);
        btnedit.addActionListener(this);
    
    }
    
    public static void main(String args[]){
    new EditCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnedit){
        try{
            DBConnection dbc=new DBConnection();
            pstmt=dbc.con.prepareStatement("select * from customer_details where customerId=?");
            pstmt.setString(1,txtcustomerId.getText());
            rs=pstmt.executeQuery();
            if(rs.next()){
                
            CustomerForm af=new CustomerForm();
       af.txtcurrency.setText(rs.getString("currency"));
       af.txtcustomerId.setText(rs.getString("customerId"));
       af.txtcustomerId.setEnabled(false);
       af.txtfirstname.setText(rs.getString("firstname"));
       af.txtlastname.setText(rs.getString("lastname"));
       af.txtfatherName.setText(rs.getString("fatherName"));
       af.txtgrandFatherName.setText(rs.getString("grandFatherName"));
       af.txthusbandWifeName.setText(rs.getString("husbandWifeName"));
       af.txtgurdianName.setText(rs.getString("gurdianName"));
       af.txtdateOfBirth.setText(rs.getString("dateOfBirth"));
       af.txtoccupation.setText(rs.getString("occupation"));
       af.txtnationality.setText(rs.getString("nationality"));
       af.txtcitizenshipNumber.setText(rs.getString("nationality"));
       af.txtpassportNumber.setText(rs.getString("passportNumber"));
       af.titleCombo.setSelectedItem(rs.getString("title"));
       af.maritalStatusCombo.setSelectedItem(rs.getString("maritalStatus"));
       af.genderCombo.setSelectedItem(rs.getString("gender"));
       af.religionCombo.setSelectedItem(rs.getString("religion"));
       af.educationQualificationCombo.setSelectedItem(rs.getString("educationQualification"));
       af.txttemporaryAddress.setText(rs.getString("temporaryAddress"));
       af.txtpermanentAddress.setText(rs.getString("permanentAddress"));
       af.txtmailingAddress1.setText(rs.getString("mailingAddress1"));
       af.txtmailingAddress2.setText(rs.getString("mailingAddress2"));
       af.txtmobile1.setText(rs.getString("mobile1"));
       af.txtmobile2.setText(rs.getString("mobile2"));
       af.txtphone1.setText(rs.getString("phone1"));
       af.txtphone2.setText(rs.getString("phone2"));
       af.txtfaxno.setText(rs.getString("faxno"));
       af.txtpoBox.setText(rs.getString("poBox"));
       af.txtemail.setText(rs.getString("emailAddress"));
       
       af.btnsave.setText("Update");
       af.btnsave.setBounds(300,985,180,25);
       af.btnreset.hide();
       
       af.identityPhoto.setIcon(new ImageIcon("Images/"+rs.getString("customerId")+".png"));
       this.hide();
            }
        }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);
        }
        }
        
    }
    
}
