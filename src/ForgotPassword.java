import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class ForgotPassword extends JDialog implements ActionListener{
    JLabel forgotpasswordbar, lblforgotpassword, username, dateOfBirth, dateformat;
    JTextField txtusername, txtdateOfBirth;
    JButton btnRetrieve, btnCancel;
    JPanel pan= new JPanel();
    
    PreparedStatement pstmt;
    ResultSet rs;
    
    
    public ForgotPassword(){
        
        setSize(360,205);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NIBL- Forgot Password?");
        setLocationRelativeTo(null);
        
//        Font ft= new Font("",Font.BOLD,15);
        
        add(pan);
        pan.setLayout(null);
        pan.setBackground(Color.WHITE);
        
        forgotpasswordbar=new JLabel(new ImageIcon("images/admin_signup.png"));
        pan.add(forgotpasswordbar);
        forgotpasswordbar.setBounds(0,0,360,33);
        
        lblforgotpassword=new JLabel("Forgot Password?");
        pan.add(lblforgotpassword,0);
        lblforgotpassword.setBounds(20,5,150,25);
        lblforgotpassword.setForeground(Color.WHITE);
//        lblforgotpassword.setFont(ft);
        
        username=new JLabel("Username");
        pan.add(username);
        username.setBounds(20,50,100,25);
        txtusername=new JTextField();
        pan.add(txtusername);
        txtusername.setBounds(120,50,215,25);
        
        dateOfBirth=new JLabel("Date Of Birth");
        pan.add(dateOfBirth);
        dateOfBirth.setBounds(20,90,100,25);
        txtdateOfBirth=new JTextField();
        pan.add(txtdateOfBirth);
        txtdateOfBirth.setBounds(120,90,130,25);
        dateformat=new JLabel("(yyyy-mm-dd)");
        pan.add(dateformat);
        dateformat.setBounds(255,90,150,20);
        dateformat.setForeground(Color.GRAY);
        
         btnRetrieve=new JButton("Retrieve");
        pan.add(btnRetrieve);        
        btnRetrieve.setBounds(120,130,100,25);
        btnRetrieve.addActionListener(this);
        
        btnCancel=new JButton("Cancel");
        pan.add(btnCancel);
         btnCancel.setBounds(233,130,100,25);
        btnCancel.addActionListener(this);
    
    }
    
    public static void main(String args[]){
    new ForgotPassword();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==btnRetrieve){
        try{
            DBConnection dbc= new DBConnection();
            pstmt=dbc.con.prepareStatement("select * from admin_details where username=? and dateofbirth=?");
           pstmt.setString(1,txtusername.getText());
           pstmt.setString(2,txtdateOfBirth.getText());
           rs=pstmt.executeQuery();
           if(rs.next()){
           String pass=rs.getString("password");
           JOptionPane.showMessageDialog(null,"Your Password is"+" '"+pass+"'");
           }
        }
        catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex);
        }
        }
        
        if(e.getSource()==btnCancel){
        this.dispose();
        }
       
    }
    
}
