import java.awt.event.*;
import javax.swing.JOptionPane;
import java.sql.*;
public class CustomerFormAction implements ActionListener {
    CustomerForm af= new CustomerForm();
    
    PreparedStatement pstmt;
    int result;
    int x;
    
    public CustomerFormAction(){
    af.btnreset.addActionListener(this);
    af.btnsave.addActionListener(this);
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==af.btnreset){
       af.txtcurrency.setText("");
       af.txtcustomerId.setText("");
       af.txtfirstname.setText("");
       af.txtlastname.setText("");
       af.txtfatherName.setText("");
       af.txtgrandFatherName.setText("");
       af.txthusbandWifeName.setText("");
       af.txtgurdianName.setText("");
       af.txtdateOfBirth.setText("");
       af.txtoccupation.setText("");
       af.txtnationality.setText("");
       af.txtcitizenshipNumber.setText("");
       af.txtpassportNumber.setText("");
       af.titleCombo.setSelectedItem("");
       af.maritalStatusCombo.setSelectedItem("");
       af.genderCombo.setSelectedItem("");
       af.religionCombo.setSelectedItem("");
       af.educationQualificationCombo.setSelectedItem("");
       af.txttemporaryAddress.setText("");
       af.txtpermanentAddress.setText("");
       af.txtmailingAddress1.setText("");
       af.txtmailingAddress2.setText("");
       af.txtmobile1.setText("");
       af.txtmobile2.setText("");
       af.txtphone1.setText("");
       af.txtphone2.setText("");
       af.txtfaxno.setText("");
       af.txtpoBox.setText("");
       af.txtemail.setText("");
       }
       
       if(e.getSource()==af.btnsave){
           try{
       DBConnection dbc= new DBConnection();
       pstmt=dbc.con.prepareStatement("insert into customer_details values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       pstmt.setString(1,af.txtcustomerId.getText());
       pstmt.setString(2, af.titleCombo.getSelectedItem().toString());
       pstmt.setString(3, af.txtcurrency.getText());
       pstmt.setString(4,af.txtfirstname.getText());
       pstmt.setString(5, af.txtlastname.getText());
       pstmt.setString(6,af.txtfatherName.getText());
       pstmt.setString(7, af.txtgrandFatherName.getText());
       pstmt.setString(8,af.txthusbandWifeName.getText());
       pstmt.setString(9, af.txtgurdianName.getText());
       pstmt.setString(10,af.maritalStatusCombo.getSelectedItem().toString());
       pstmt.setString(11,af.genderCombo.getSelectedItem().toString());
       pstmt.setString(12,af.txtdateOfBirth.getText());
       pstmt.setString(13, af.txtoccupation.getText());
       pstmt.setString(14, af.txtnationality.getText());
       pstmt.setString(15, af.txtcitizenshipNumber.getText());
//       if(af.txtpassportNumber.getText().equals("")){
//        x=0;
//       }
//       else{
//       x=Integer.parseInt(af.txtpassportNumber.getText());
//       }       
       pstmt.setString(16,af.txtpassportNumber.getText());
       pstmt.setString(17,af.religionCombo.getSelectedItem().toString());
       pstmt.setString(18, af.educationQualificationCombo.getSelectedItem().toString());
       pstmt.setString(19,af.txttemporaryAddress.getText());
       pstmt.setString(20,af.txtpermanentAddress.getText());
       pstmt.setString(21, af.txtmailingAddress1.getText());
       pstmt.setString(22, af.txtmailingAddress2.getText());
       pstmt.setString(23, af.txtmobile1.getText());
       pstmt.setString(24, af.txtmobile2.getText());
       pstmt.setString(25, af.txtphone1.getText());
       pstmt.setString(26, af.txtphone2.getText());
       pstmt.setString(27, af.txtfaxno.getText());
       pstmt.setString(28, af.txtpoBox.getText());
       pstmt.setString(29, af.txtemail.getText());
       pstmt.setString(30,"active");
       
       result=pstmt.executeUpdate();
       if(result>0){
       JOptionPane.showMessageDialog(null,"Record Saved !");
       }
           }
           catch(Exception ex){
           JOptionPane.showMessageDialog(null,ex);
           }
               
       }
    }
    
}

