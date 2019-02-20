import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;



public class CustomerForm extends JFrame implements ActionListener{
    ////////////////////////// Identity Details ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    JLabel niblLogo,lblurl,  personalBar, identityDetails, identityPhoto,
            title, currency, customerId, firstname,lastname, fatherName, grandFatherName, husbandWifeName,
            gurdianName, maritalStatus,gender, dateOfBirth, dateFormat, occupation, nationality, citizenshipNumber,
            passportNumber, religion, educationQualification, lblagree;    
    
    public JTextField txtcurrency, txtcustomerId, txtfirstname, txtlastname, txtfatherName, txtgrandFatherName, txthusbandWifeName,
            txtgurdianName, txtdateOfBirth, txtoccupation, txtnationality, txtcitizenshipNumber, txtpassportNumber;
    
    public JComboBox titleCombo, maritalStatusCombo, genderCombo, religionCombo, educationQualificationCombo;
    
    JButton btnupload, btnsave, btnreset;     
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    //////////////////////////////////// Contact Details /////////////////////////////////////////////////////////////////////////////////////////////////////////
    JLabel contactBar, contactDetails, temporaryAddress, permanentAddress, mailingAddress1, mailingAddress2, mobile1, mobile2, phone1, phone2,
            faxno, poBox, email, savebar;
    
    public JTextField txttemporaryAddress, txtpermanentAddress, txtmailingAddress1, txtmailingAddress2,txtmobile1, txtmobile2, txtphone1, txtphone2, txtfaxno,
            txtpoBox,txtemail;
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    JPanel panel = new JPanel(null);
    JPanel pan;
    JScrollPane sp;
    
    PreparedStatement pstmt;
    int x, result;
            
    public CustomerForm(){
       
        panel.setBackground(Color.WHITE);
        sp = new JScrollPane(panel);
        setLayout(new BorderLayout());
        add(sp);
        
        pan=new JPanel(null);
        panel.add(pan);
        pan.setBounds(300,0,805,1020);
    
    pan.setBackground(Color.RED);
      ////////////////////////// Identity Details ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    niblLogo= new JLabel(new ImageIcon("images/nibl_logo.png"));
    pan.add(niblLogo);
    niblLogo.setBounds(20,10,370,64);
    
    lblurl= new JLabel("www.nibl.com.np");
    pan.add(lblurl);
    lblurl.setBounds(650,55,100,25);
    lblurl.setForeground(Color.blue);
    
    personalBar= new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(personalBar);
    personalBar.setBounds(20,76,730,21);
    
    
    identityDetails= new JLabel("Identity Details");
    pan.add(identityDetails,0);
    identityDetails.setBounds(40,76,150,20);
    identityDetails.setForeground(Color.WHITE);
    
    identityPhoto=new JLabel(new ImageIcon("images/idPhoto.png"));
    pan.add(identityPhoto);
    identityPhoto.setBounds(640,110,88,94);
    
    title=new JLabel("Title");
    pan.add(title);
    title.setBounds(40,110,50,25);
    titleCombo= new JComboBox();
    pan.add(titleCombo);
    titleCombo.setBounds(260,110,100,25);
    titleCombo.addItem("");
    titleCombo.addItem("Mr");
    titleCombo.addItem("Mrs");
    titleCombo.addItem("Miss");
    titleCombo.addItem("Other");
    
    currency=new JLabel("Currency");
    pan.add(currency);
    currency.setBounds(450,110,80,25);
    txtcurrency=new JTextField();
    pan.add(txtcurrency);
    txtcurrency.setBounds(520,110,90,25);
    
    customerId= new JLabel("Customer ID");
    pan.add(customerId);
    customerId.setBounds(40,145,100,25);
    txtcustomerId=new JTextField();
    pan.add(txtcustomerId);
    txtcustomerId.setBounds(260,145,350,25);
    
    firstname=new JLabel("First Name");
    pan.add(firstname);
    firstname.setBounds(40,180,100,25);
    txtfirstname=new JTextField();
    pan.add(txtfirstname);
    txtfirstname.setBounds(260,180,350,25);
    
    lastname=new JLabel("Last Name");
    pan.add(lastname);
    lastname.setBounds(40,215,150,25);
    txtlastname=new JTextField();
    pan.add(txtlastname);
    txtlastname.setBounds(260,215,350,25);
    
    btnupload=new JButton("Upload");
    pan.add(btnupload);
    btnupload.setBounds(640,215,88,25);
    
    fatherName=new JLabel("Father's Name");
    pan.add(fatherName);
    fatherName.setBounds(40,250,150,25);
    txtfatherName=new JTextField();
    pan.add(txtfatherName);
    txtfatherName.setBounds(260,250,470,25);
    
    grandFatherName=new JLabel("Grandfather's Name");
    pan.add(grandFatherName);
    grandFatherName.setBounds(40,285,150,25);
    txtgrandFatherName=new JTextField();
    pan.add(txtgrandFatherName);
    txtgrandFatherName.setBounds(260,285,470,25);
    
    husbandWifeName=new JLabel("Husband/Wife name");
    pan.add(husbandWifeName);
    husbandWifeName.setBounds(40,320,150,25);
    txthusbandWifeName=new JTextField();
    pan.add(txthusbandWifeName);
    txthusbandWifeName.setBounds(260,320,470,25);
    
    gurdianName=new JLabel("Gurdian's name (In case of minor)");
    pan.add(gurdianName);
    gurdianName.setBounds(40,355,200,25);
    txtgurdianName=new JTextField();
    pan.add(txtgurdianName);
    txtgurdianName.setBounds(260,355,470,25);
    
    maritalStatus=new JLabel("Marital Status");
    pan.add(maritalStatus);
    maritalStatus.setBounds(40,390,150,25);
    maritalStatusCombo=new JComboBox();
    pan.add(maritalStatusCombo);
    maritalStatusCombo.setBounds(260,390,150,25);
    maritalStatusCombo.addItem("");
    maritalStatusCombo.addItem("Single");
    maritalStatusCombo.addItem("Married");
    maritalStatusCombo.addItem("Divorced");
    
    gender=new JLabel("Gender");
    pan.add(gender);
    gender.setBounds(550,390,100,25);
    genderCombo=new JComboBox();
    pan.add(genderCombo);
    genderCombo.setBounds(610,390,120,25);
    genderCombo.addItem("");
    genderCombo.addItem("Male");
    genderCombo.addItem("Female");
    genderCombo.addItem("Other");
    
    
    dateOfBirth=new JLabel("Date Of Birth");
    pan.add(dateOfBirth);
    dateOfBirth.setBounds(40,425,150,25);
    txtdateOfBirth= new JTextField();
    pan.add(txtdateOfBirth);
    txtdateOfBirth.setBounds(260,425,250,25);
    dateFormat=new JLabel("(yyyy-mm-dd)");
    pan.add(dateFormat);
    dateFormat.setBounds(520,425,150,25);
    dateFormat.setForeground(Color.GRAY);
    
    occupation=new JLabel("Occupation");
    pan.add(occupation);
    occupation.setBounds(40,460,150,25);
    txtoccupation=new JTextField();
    pan.add(txtoccupation);
    txtoccupation.setBounds(260,460,270,25);
    
    nationality=new JLabel("Nationality");
    pan.add(nationality);
    nationality.setBounds(40,495,150,25);
    txtnationality= new JTextField();
    pan.add(txtnationality);
    txtnationality.setBounds(260,495,270,25);
    
    citizenshipNumber=new JLabel("Citizenship number");
    pan.add(citizenshipNumber);
    citizenshipNumber.setBounds(40,530,150,25);
    txtcitizenshipNumber= new JTextField();
    pan.add(txtcitizenshipNumber);
    txtcitizenshipNumber.setBounds(260,530,150,25);
    
    passportNumber= new JLabel("Passport Number");
    pan.add(passportNumber);
    passportNumber.setBounds(450,530,150,25);   
    txtpassportNumber=new JTextField();
    pan.add(txtpassportNumber);
    txtpassportNumber.setBounds(560,530,170,25);
    
     religion=new JLabel("Religion");
    pan.add(religion);
    religion.setBounds(40,565,150,25);
    religionCombo= new JComboBox();
    pan.add(religionCombo);
    religionCombo.setBounds(260,565,270,25);
    religionCombo.addItem("");
    religionCombo.addItem("Hindu");
    religionCombo.addItem("Muslim");
    religionCombo.addItem("Christianity");
    religionCombo.addItem("Buddhist");
    religionCombo.addItem("Other");
    
    educationQualification=new JLabel("Education Qualification");
    pan.add(educationQualification);
    educationQualification.setBounds(40,600,150,25);  
    educationQualificationCombo= new JComboBox();
    pan.add(educationQualificationCombo);
    educationQualificationCombo.setBounds(260,600,270,25);
    educationQualificationCombo.addItem("");
    educationQualificationCombo.addItem("Literate");
    educationQualificationCombo.addItem("SLC");
    educationQualificationCombo.addItem("Graduate");
    educationQualificationCombo.addItem("Post-Graduate");
    

   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
     panel.setPreferredSize(new Dimension(775, 1020));
     
   /////////////////////////////////////////////////////// contact details///////////////////////////////////////////////////////////////////////////////////////
    contactBar= new JLabel(new ImageIcon("images/personal_bar.png"));
    pan.add(contactBar);
    contactBar.setBounds(20,645,730,21);
    
     contactDetails= new JLabel("Contact Details");
    pan.add(contactDetails,0);
   contactDetails.setBounds(40,645,150,20);
    contactDetails.setForeground(Color.WHITE);
    
    temporaryAddress=new JLabel("Temporary Address");
    pan.add(temporaryAddress);
    temporaryAddress.setBounds(40,680,260,25);
    txttemporaryAddress=new JTextField();
    pan.add(txttemporaryAddress);
    txttemporaryAddress.setBounds(260,680,470,25);
    
    permanentAddress=new JLabel("Permanent Address");
    pan.add(permanentAddress);
    permanentAddress.setBounds(40,715,260,25);
    txtpermanentAddress=new JTextField();
   pan.add(txtpermanentAddress);
   txtpermanentAddress.setBounds(260,715,470,25);
   
   mailingAddress1=new JLabel("Mailing Address 1");
   pan.add(mailingAddress1);
   mailingAddress1.setBounds(40,750,260,25);
   txtmailingAddress1=new JTextField();
   pan.add(txtmailingAddress1);
   txtmailingAddress1.setBounds(260,750,470,25);
   
   mailingAddress2=new JLabel("Mailing Address 2");
   pan.add(mailingAddress2);
   mailingAddress2.setBounds(40,785,260,25);
   txtmailingAddress2=new JTextField();
   pan.add(txtmailingAddress2);
   txtmailingAddress2.setBounds(260,785,470,25);
   
   mobile1=new JLabel("Moblie 1");
   pan.add(mobile1);
   mobile1.setBounds(40,820,100,25);
   txtmobile1=new JTextField();
   pan.add(txtmobile1);
   txtmobile1.setBounds(260,820,180,25);
   
    mobile2=new JLabel("Moblie 2");
   pan.add(mobile2);
   mobile2.setBounds(490,820,100,25);
   txtmobile2=new JTextField();
   pan.add(txtmobile2);
   txtmobile2.setBounds(550,820,180,25);
   
      phone1=new JLabel("Phone 1");
   pan.add(phone1);
   phone1.setBounds(40,865,100,25);
   txtphone1=new JTextField();
   pan.add(txtphone1);
   txtphone1.setBounds(260,865,180,25);
   
    phone2=new JLabel("Phone 2");
   pan.add(phone2);
   phone2.setBounds(490,865,100,25);
   txtphone2=new JTextField();
   pan.add(txtphone2);
   txtphone2.setBounds(550,865,180,25);
   
   faxno=new JLabel("Fax No.");
   pan.add(faxno);
   faxno.setBounds(40,900,260,25);
   txtfaxno=new JTextField();
   pan.add(txtfaxno);
   txtfaxno.setBounds(260,900,180,25);
   
   poBox=new JLabel("P. O. Box");
   pan.add(poBox);
   poBox.setBounds(490,900,260,25);
   txtpoBox=new JTextField();
   pan.add(txtpoBox);
   txtpoBox.setBounds(550,900,180,25);
   
   email=new JLabel("Email Address");
   pan.add(email);
   email.setBounds(40,935,260,25);
   txtemail=new JTextField();
   pan.add(txtemail);
   txtemail.setBounds(260,935,470,25);
    
  
    btnsave= new JButton("Save");
    pan.add(btnsave,0);
    btnsave.setBounds(300,985,80,25);
    btnsave.addActionListener(this);
    
    btnreset=new JButton("Reset");
    pan.add(btnreset,0);
    btnreset.setBounds(400,985,80,25);
    
    savebar= new JLabel(new ImageIcon("images/save_bar.png"));
    pan.add(savebar);
    savebar.setBounds(20,975,730,44);
   
   ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
   
   
   
    setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    
    setTitle("NIBL - Customer Registration Form");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setIconImage(new ImageIcon("images/nibl_icon.png").getImage());
    

    
    }
    //public static void main(String args[]){
       // new CustomerForm();
//    setDefaultLookAndFeelDecorated(true);
//        AccountFormAction afc= new AccountFormAction(); 
//    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btnsave){
           if(btnsave.getText()=="Update"){
              try{
       DBConnection dbc= new DBConnection();
       pstmt=dbc.con.prepareStatement("update customer_details set emailAddress=?, title=?, currency=?, firstname=?, lastname=?, fatherName=?, grandFatherName=?, husbandWifeName=?, gurdianName=?, maritalStatus=?, gender=?, dateOfBirth=?, occupation=?, nationality=?, citizenshipNumber=?, passportNumber=?, religion=?, educationQualification=?, temporaryAddress=?, permanentAddress=?, mailingAddress1=?, mailingAddress2=?, mobile1=?, mobile2=?, phone1=?, phone2=?, faxno=?, poBox=? where customerId=?");
       
       pstmt.setString(1, txtemail.getText());
       pstmt.setString(2, titleCombo.getSelectedItem().toString());
       pstmt.setString(3, txtcurrency.getText());
       pstmt.setString(4,txtfirstname.getText());
       pstmt.setString(5, txtlastname.getText());
       pstmt.setString(6,txtfatherName.getText());
       pstmt.setString(7, txtgrandFatherName.getText());
       pstmt.setString(8,txthusbandWifeName.getText());
       pstmt.setString(9, txtgurdianName.getText());
       pstmt.setString(10,maritalStatusCombo.getSelectedItem().toString());
       pstmt.setString(11,genderCombo.getSelectedItem().toString());
       pstmt.setString(12,txtdateOfBirth.getText());
       pstmt.setString(13, txtoccupation.getText());
       pstmt.setString(14, txtnationality.getText());
       pstmt.setString(15, txtcitizenshipNumber.getText());      
       pstmt.setString(16,txtpassportNumber.getText());
       pstmt.setString(17,religionCombo.getSelectedItem().toString());
       pstmt.setString(18, educationQualificationCombo.getSelectedItem().toString());
       pstmt.setString(19,txttemporaryAddress.getText());
       pstmt.setString(20,txtpermanentAddress.getText());
       pstmt.setString(21, txtmailingAddress1.getText());
       pstmt.setString(22, txtmailingAddress2.getText());
       pstmt.setString(23, txtmobile1.getText());
       pstmt.setString(24, txtmobile2.getText());
       pstmt.setString(25, txtphone1.getText());
       pstmt.setString(26, txtphone2.getText());
       pstmt.setString(27, txtfaxno.getText());
       pstmt.setString(28, txtpoBox.getText());
       pstmt.setInt(29,Integer.parseInt(txtcustomerId.getText()));
       
       
       result=pstmt.executeUpdate();
       if(result>0){
       JOptionPane.showMessageDialog(null,"Record Updated !");
       }
           }
           catch(Exception ex){
           JOptionPane.showMessageDialog(null,ex);
           }
           }
           }
         
       }
    
    
}
