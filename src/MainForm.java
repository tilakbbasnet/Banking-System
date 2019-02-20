import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.*;

public class MainForm extends JFrame implements ActionListener {
    
    JMenuBar menuBar;
    JMenu mcustomer, maccount, metransaction, mloan, mabout;
    JMenuItem micnew, micedit, micview, miexit, 
            miacreate, miaedit, miamanage,
            mitdeposit, mitwithdraw,mittransactionLog,
            mitloanAmortization,
            mideveloper;
    JDesktopPane jdp;
    JLabel admingreeting;
    
    public MainForm(){
       
     jdp= new JDesktopPane();
    add(jdp);
    jdp.setBackground(Color.DARK_GRAY);
    
    menuBar=new JMenuBar();
    setJMenuBar(menuBar);
    
    mcustomer=new JMenu("Customer");
    maccount=new JMenu("Account");
    metransaction=new JMenu("Transaction");
    mloan=new JMenu("Loan");
    mabout=new JMenu("About");
    
    micnew=new JMenuItem("New");
    micedit=new JMenuItem("Edit");
    micview=new JMenuItem("View");
    miexit=new JMenuItem("Exit");
    
    menuBar.add(mcustomer);
    mcustomer.add(micnew);
    micnew.addActionListener(this);
    mcustomer.add(micedit);
    micedit.addActionListener(this);
    mcustomer.add(micview);
    micview.addActionListener(this);
    mcustomer.add(miexit);
    miexit.addActionListener(this);
    
    miacreate= new JMenuItem("Create");
    miaedit=new JMenuItem("Edit");
   miamanage=new JMenuItem("Manage");
          
    
    menuBar.add(maccount);
    maccount.add(miacreate);
    miacreate.addActionListener(this);
    maccount.add(miaedit);
    miaedit.addActionListener(this);
    maccount.add(miamanage);
    miamanage.addActionListener(this);
    
    mitdeposit=new JMenuItem("Deposit");
    mitwithdraw=new JMenuItem("Withdraw");
    mittransactionLog=new JMenuItem("Transaction Log");
    
    menuBar.add(metransaction);
    metransaction.add(mitdeposit);
    mitdeposit.addActionListener(this);
    metransaction.add(mitwithdraw);
    mitwithdraw.addActionListener(this);
    metransaction.add(mittransactionLog);
    mittransactionLog.addActionListener(this);
    
    menuBar.add(mloan);
    mitloanAmortization=new JMenuItem("Loan Amortization");
    mloan.add(mitloanAmortization);
    mitloanAmortization.addActionListener(this);
    
    mideveloper =new JMenuItem("Developer");
    menuBar.add(mabout);
    mabout.add(mideveloper);
    mideveloper.addActionListener(this);
    
    admingreeting=new JLabel();
    jdp.add(admingreeting,0);
    admingreeting.setBounds(1250,0,100,25);
    admingreeting.setForeground(Color.WHITE);
    
    setVisible(true);
        setExtendedState(MAXIMIZED_BOTH);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setIconImage(new ImageIcon("images/nibl_icon.png").getImage());
    setTitle("Welcome To NIBL- Account Management System");
    sc.dispose();
    }
   
    
    public static void main(String args[]){
          try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){}
 
      SplashClass sc=new SplashClass();
        try{
     Thread.sleep(2000);
        }catch(Exception ex){}
        
           new MainForm();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==micnew){
           new CustomerFormAction();
        
        }
        
        if(e.getSource()==micedit){
        new EditCustomer();
        }
        
        if(e.getSource()==micview){
        new ViewCustomer();
        }
        
        if(e.getSource()==miexit){
        this.dispose();
        }
        
        if(e.getSource()==miacreate){
        new CreateAccount();
        }
        
        if(e.getSource()==miaedit){
        new EditAccount();
        }
        
        if(e.getSource()==miamanage){
        new ManageAccount();
        }
        
        if(e.getSource()==mitdeposit){
        new DepositForm();
        }
        
        if(e.getSource()==mitwithdraw){
        new WithdrawlForm();
        }
        
        if(e.getSource()==mittransactionLog){
        new TransactionLog();
        }
        
        if(e.getSource()==mitloanAmortization){
        new AmortLoan();
        }
        
        if(e.getSource()==mideveloper){
       SplashClass sc=new SplashClass();
       JButton btnclose=new JButton("X");
       sc.add(btnclose,0);
       btnclose.setBounds(15,320,40,30);
         }
        
    }
}
       
 

