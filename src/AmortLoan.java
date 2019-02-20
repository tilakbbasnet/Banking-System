import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
public class AmortLoan extends JFrame implements ActionListener {
    JLabel headingBar;
    JLabel valueBar, loanDetails, loanAmount, interestRate, loanPeriod, paymentNo, startDate, lblpercent, lbldateformat, lblcredit;
    JTextField txtloanAmount, txtinterestRate, txtloanPeriod, txtpaymentNo, txtstartDate;
    JLabel summaryBar, loanSummary, scheduledPayment, scheduledNo, totalInterest;
    JTextField txtscheduledPayment, txtscheduleNo, txttotalInterest;
    
    JButton btnCalculate;
    
    DefaultTableModel model=new DefaultTableModel();
    JTable table=new JTable(model);
    
     DecimalFormat df = new DecimalFormat("##.## ");
    public AmortLoan(){
    setSize(500,500);
        setExtendedState(MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NIBL-Loan Amortization Schedule");
        
        JPanel pan=new JPanel(null);
        add(pan);
        pan.setBackground(Color.WHITE);
        
        headingBar=new JLabel(new ImageIcon("images/amort_bar.png"));
        pan.add(headingBar);
        headingBar.setBounds(85,5,1200,30);
        ////////////////////////Enter values////////////////////////////////////////////////////////////////////////////////////
        valueBar=new JLabel(new ImageIcon("images/personal_bar.png"));
        pan.add(valueBar);
        valueBar.setBounds(85,35,400,25);
        loanDetails=new JLabel("Loan Details");
        pan.add(loanDetails, 0);
        loanDetails.setBounds(90,35,200,25);
        loanDetails.setForeground(Color.WHITE);
        
        loanAmount=new JLabel("Loan Amount");
        pan.add(loanAmount);
        loanAmount.setBounds(90,65,200,25);
        txtloanAmount=new JTextField();
        pan.add(txtloanAmount);
        txtloanAmount.setBounds(280,65,200,25);
        
        interestRate=new JLabel("Annual Interest Rate");
        pan.add(interestRate);
        interestRate.setBounds(90,95,200,25);
        txtinterestRate=new JTextField();
        pan.add(txtinterestRate);
        txtinterestRate.setBounds(280,95,185,25);
        lblpercent=new JLabel("%");
        pan.add(lblpercent);
        lblpercent.setBounds(470,95,20,25);
        lblpercent.setForeground(Color.GRAY);
        
        loanPeriod=new JLabel("Loan Period in Years");
        pan.add(loanPeriod);
        loanPeriod.setBounds(90,125,200,25);
         txtloanPeriod=new JTextField();
        pan.add(txtloanPeriod);
        txtloanPeriod.setBounds(280,125,200,25);
        
        paymentNo=new JLabel("No. of Payment per year");
        pan.add(paymentNo);
        paymentNo.setBounds(90,155,200,25);
         txtpaymentNo=new JTextField();
        pan.add(txtpaymentNo);
        txtpaymentNo.setBounds(280,155,200,25);
        
        startDate=new JLabel("Start Date of Loan");
        pan.add(startDate);
        startDate.setBounds(90,185,200,25);
         txtstartDate=new JTextField();
        pan.add(txtstartDate);
        txtstartDate.setBounds(280,185,115,25);
        lbldateformat=new JLabel("(dd-mm-yyyy)");
        pan.add(lbldateformat);
        lbldateformat.setBounds(400,185,100,25);
        lbldateformat.setForeground(Color.GRAY);
        
        btnCalculate=new JButton("Calculate");
        pan.add(btnCalculate);
        btnCalculate.setBounds(280,215,200,25);
        btnCalculate.addActionListener(this);
        
        lblcredit=new JLabel("By: Tilak Basnet");
        pan.add(lblcredit);
        lblcredit.setBounds(1170,215,200,25);
        lblcredit.setForeground(Color.GRAY);
 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        /////////////////////////////////////////////loan summary//////////////////////////////////////////////////////////////
        summaryBar=new JLabel(new ImageIcon("images/personal_bar.png"));
        pan.add(summaryBar);
        summaryBar.setBounds(885,35,400,25);
        loanSummary=new JLabel("Loan Summary");
        pan.add(loanSummary,0);
        loanSummary.setBounds(890,35,200,25);
        loanSummary.setForeground(Color.WHITE);
        
        scheduledPayment=new JLabel("Scheduled Payment");
        pan.add(scheduledPayment);
        scheduledPayment.setBounds(890,65,200,25);
        txtscheduledPayment=new JTextField();
        pan.add(txtscheduledPayment);
        txtscheduledPayment.setBounds(1080,65,200,25);
        txtscheduledPayment.setBorder(null);
        txtscheduledPayment.setForeground(Color.red);
        txtscheduledPayment.setEditable(false);
        
        scheduledNo=new JLabel("Scheduled No. of Payment");
        pan.add(scheduledNo);
        scheduledNo.setBounds(890,95,200,25);
        txtscheduleNo=new JTextField();
        pan.add(txtscheduleNo);
        txtscheduleNo.setBounds(1080,95,200,25);
        txtscheduleNo.setBorder(null);
        txtscheduleNo.setForeground(Color.red);
        txtscheduleNo.setEditable(false);
        
        totalInterest=new JLabel("Total Interest");
        pan.add(totalInterest);
        totalInterest.setBounds(890,125,200,25);
         txttotalInterest=new JTextField();
        pan.add(txttotalInterest);
        txttotalInterest.setBounds(1080,125,200,25);
        txttotalInterest.setBorder(null);
        txttotalInterest.setForeground(Color.red);
        txttotalInterest.setEditable(false);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
        model.addColumn("Pmt. No.");
        model.addColumn("Payment Date");
        model.addColumn("Beginning Balance");
        model.addColumn("Scheduled Payment");
        model.addColumn("Total Payment");
        model.addColumn("Principle");
        model.addColumn("Interest");
        model.addColumn("Ending Balance");
        model.addColumn("Cumulative Interest");
        int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp=new JScrollPane(table,v,h);
        table.setRowHeight(30);
        pan.add(jsp);
        jsp.setBounds(85,245,1200,455);
        table.getColumn("Pmt. No.").setMaxWidth(60);
    }
    
    public static void main(String args[]){
//        try {
//            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
//        } catch (Exception ex) {
//        }
      new AmortLoan();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        double atemp, aprin, ainb, cinterest=0;
        String pmtDate, pmtDay;
        int pmtMonth=0, pmtYear=0;
       if(e.getSource()==btnCalculate){
           model.setRowCount(0);
    int mult=Integer.parseInt(txtloanPeriod.getText())*Integer.parseInt(txtpaymentNo.getText());
    double p=Double.parseDouble(txtloanAmount.getText());   //main principle
    double r=Double.parseDouble(txtinterestRate.getText())/1200;  //monthly interest rate
    double n=Double.parseDouble(txtloanPeriod.getText())*Double.parseDouble(txtpaymentNo.getText());  //No. of payment
    double temp=Math.pow(1+r,n);  //power
    double a=(r*p*temp)/(temp-1);  //monthly payment
    pmtDate=txtstartDate.getText(); //Date
    pmtDay=pmtDate.substring(0,2);  //Day
    pmtMonth=Integer.parseInt(pmtDate.substring(3,5));  //Month
    pmtYear= Integer.parseInt(pmtDate.substring(6,10)); //Year
    for(int i=1;i<=mult;i++){
        if(p>a){
        atemp=a;
        }
        else{
            atemp=p;
        }
        
        double ins=p*r;  //monthly interest
        double prin=a-ins;  //monthly principle
        double inb=p-prin;  //ending balance
        cinterest=cinterest+ins; //cumulative interest
            
      if(i==mult){
        aprin=atemp-ins;
        ainb=0;
        }
      else{
      aprin=prin;
      ainb=inb;
      }
      
      pmtMonth=pmtMonth+1;
      if(pmtMonth>12){
      pmtMonth=1;
      pmtYear=pmtYear+1;
      }
  
    model.addRow(new Object[]{i,pmtDay+"-"+pmtMonth+"-"+pmtYear,df.format(p),df.format(a),df.format(atemp),df.format(aprin),df.format(ins),df.format(ainb),df.format(cinterest)});
    p=p-prin;
    }
     ////////////////////////////////////To loan summary//////////////////////////////////////////////////////////////////////
    txtscheduledPayment.setText(""+df.format(a));
    txtscheduleNo.setText(""+Math.round(n));
    txttotalInterest.setText(""+df.format(cinterest));
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    }
    }
    
}
