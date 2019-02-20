import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
public class AdminSignup extends JDialog implements ActionListener {
    JLabel adminsignupbar, lbladminsignup, firstname, lastname, email, dateOfBirth, dateformat, username, password, repassword;
    JTextField txtfirstname, txtlastname, txtemail, txtdateOfBirth, txtusername;
    JPasswordField txtpassword, txtrepassword;
    JButton btnSubmit, btnCancel;
    JPanel pan=new JPanel();
    
    PreparedStatement pstmt;
    ResultSet rs;
    
    public AdminSignup(){
        setSize(360,400);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NIBL- Admin Signup");
        setLocationRelativeTo(null);
        
//        Font ft= new Font("",Font.BOLD,15);
        
        add(pan);
        pan.setLayout(null);
        pan.setBackground(Color.WHITE);
        
        adminsignupbar=new JLabel(new ImageIcon("images/admin_signup.png"));
        pan.add(adminsignupbar);
        adminsignupbar.setBounds(0,0,360,33);
        
        lbladminsignup=new JLabel("Admin Signup");
        pan.add(lbladminsignup,0);
        lbladminsignup.setBounds(15,5,100,25);
        lbladminsignup.setForeground(Color.WHITE);
//        lbladminsignup.setFont(ft);
        
        firstname=new JLabel("First Name");
        pan.add(firstname);
        firstname.setBounds(15,50,100,25);
        txtfirstname=new JTextField();
        pan.add(txtfirstname);
        txtfirstname.setBounds(125,50,215,25);
        
        lastname=new JLabel("Last Name");
        pan.add(lastname);
        lastname.setBounds(15,90,100,25);
        txtlastname=new JTextField();
        pan.add(txtlastname);
        txtlastname.setBounds(125,90,215,25);
        
        email=new JLabel("Email");
        pan.add(email);
        email.setBounds(15,130,100,25);
        txtemail=new JTextField();
        pan.add(txtemail);
        txtemail.setBounds(125,130,215,25);
        
        dateOfBirth=new JLabel("Date Of Birth");
        pan.add(dateOfBirth);
        dateOfBirth.setBounds(15,170,100,25);
        txtdateOfBirth=new JTextField();
        pan.add(txtdateOfBirth);
        txtdateOfBirth.setBounds(125,170,130,25);
        dateformat=new JLabel("(yyyy-mm-dd)");
        pan.add(dateformat);
        dateformat.setBounds(260,170,150,20);
        dateformat.setForeground(Color.GRAY);
        
        username=new JLabel("Username");
        pan.add(username);
        username.setBounds(15,210,100,25);
        txtusername=new JTextField();
        pan.add(txtusername);
        txtusername.setBounds(125,210,215,25);
        
        password=new JLabel("Password");
        pan.add(password);
        password.setBounds(15,250,100,25);
        txtpassword=new JPasswordField();
        pan.add(txtpassword);
        txtpassword.setBounds(125,250,215,25);
        
        repassword=new JLabel("Re-type Password");
        pan.add(repassword);
        repassword.setBounds(15,290,110,25);
        txtrepassword=new JPasswordField();
        pan.add(txtrepassword);
        txtrepassword.setBounds(125,290,215,25);
        
        btnSubmit=new JButton("Submit");
        pan.add(btnSubmit);
        btnSubmit.setBounds(125,330,100,25);
        btnSubmit.addActionListener(this);
        
        btnCancel=new JButton("Cancel");
        pan.add(btnCancel);
        btnCancel.setBounds(238,330,100,25);
        btnCancel.addActionListener(this);
        
                
        
    
    }
    
    public static void main(String args[]){
    new AdminSignup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btnCancel){
       this.dispose();
       }
       
       if(e.getSource()==btnSubmit){
           try{   
               
               if(txtpassword.getText().equals(txtrepassword.getText())){
       DBConnection dbc=new DBConnection();
       pstmt=dbc.con.prepareStatement("INSERT INTO admin_details (firstname, lastname, email, dateofbirth, username, password) VALUES(?,?,?,?,?,?)");
       pstmt.setString(1,txtfirstname.getText());
       pstmt.setString(2,txtlastname.getText());
       pstmt.setString(3,txtemail.getText());
       pstmt.setString(4,txtdateOfBirth.getText());
       pstmt.setString(5,txtusername.getText());
       pstmt.setString(6,txtpassword.getText());
       int result=pstmt.executeUpdate();
       if(result>0){
       JOptionPane.showMessageDialog(null,"New Admin Added Successfully");
       }
               }
               else{
              JOptionPane.showMessageDialog(null, "Passwords doesn't match");
               }
           }
           catch(Exception ex){
           JOptionPane.showMessageDialog(null,ex);
           }
           }
       
       }
    }
    

