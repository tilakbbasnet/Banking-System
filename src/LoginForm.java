import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class LoginForm extends JFrame implements ActionListener, MouseListener {
    JLabel headimg, login, username, password, forgotPassword, newAdmin;
    JTextField txtusername;
    JPasswordField txtpassword;
    JButton btnlogin;
    
    JPanel pan = new JPanel();
    
    PreparedStatement pstmt;
    ResultSet rs;
    
    
    public LoginForm(){
        setBackground(Color.red);
        setSize(294,350);
//        setLocation(500,200);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("NIBIL - Login Form");   
        setIconImage(new ImageIcon("images/nibl_icon.png").getImage());
        
        Font ft= new Font("",Font.BOLD,15);
        
        add(pan);
        pan.setBackground(Color.WHITE);
        pan.setLayout(null);
        
        headimg=new JLabel(new ImageIcon("images/headimg.png"));
        pan.add(headimg);
        headimg.setBounds(17,20,249,75);
        
        login=new JLabel("Admin Login");
        pan.add(login,0);
        login.setBounds(100,70,100,25);
        login.setForeground(Color.WHITE);
        login.setFont(ft);
        
        username=new JLabel("Username");
        pan.add(username);
        username.setBounds(45,100,100,25);
        
        txtusername=new JTextField();
        pan.add(txtusername);
        txtusername.setBounds(45,125,200,25);
        
         password=new JLabel("Password");
        pan.add(password);
        password.setBounds(45,155,100,25);
        
        txtpassword=new JPasswordField();
        pan.add(txtpassword);
        txtpassword.setBounds(45,180,200,25);
        
        btnlogin=new JButton("Login");
        pan.add(btnlogin);
        btnlogin.setBounds(45,220,200,25);
        btnlogin.addActionListener(this);
        
        forgotPassword= new JLabel("Forgot Password?");
        pan.add(forgotPassword);
        forgotPassword.setBounds(90,255,110,25);
        forgotPassword.setForeground(Color.GRAY);
        forgotPassword.addMouseListener(this);
        
        newAdmin=new JLabel("New Admin?");
        pan.add(newAdmin);
       newAdmin.setBounds(105,275,100,25);
       newAdmin.setForeground(Color.GRAY);
       newAdmin.addMouseListener(this);
        
    }
    
    public static void main(String args[]){
        new LoginForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==btnlogin){
        
            try{
               DBConnection dbc=new DBConnection();
               pstmt=dbc.con.prepareStatement("select * from admin_details where username=? and password=?");
               pstmt.setString(1,txtusername.getText());
               pstmt.setString(2,txtpassword.getText());
               
               rs=pstmt.executeQuery();
               
               if(rs.next()){
                   new SplashClass();
               Thread.sleep(3000);
               MainForm mf=new MainForm();
               mf.admingreeting.setText("Hi, "+rs.getString("firstname"));
                   this.dispose();
               }
            }
            
            catch(Exception ex){
             JOptionPane.showMessageDialog(null, "Error: "+ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       
    }

    @Override
    public void mousePressed(MouseEvent e) {
      
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getSource()==newAdmin){
        new AdminSignup();
        }
        
        if(e.getSource()==forgotPassword){
        new ForgotPassword();
        }
       
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       if(e.getSource()==forgotPassword){
       forgotPassword.setForeground(Color.BLUE);
       forgotPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
       
       if( e.getSource()==newAdmin){
       newAdmin.setForeground(Color.BLUE);
       newAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
       }
       
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(e.getSource()==forgotPassword){
        forgotPassword.setForeground(Color.GRAY);
        }
        
        if(e.getSource()==newAdmin){
        newAdmin.setForeground(Color.GRAY);
        }
    }
    
}
