import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class ManageAccount extends JDialog implements ActionListener {
     DefaultTableModel model= new DefaultTableModel();
    JTable table= new JTable(model);
    Statement stmt;
    PreparedStatement pstmt;
    ResultSet rs;
    
    JLabel managebar, txtmanage, accountType;
    JComboBox cbaccountType;    
    JButton btnrefresh, btnnew, btnedit, btndelete;
    
    JPopupMenu pmenu;
    JMenuItem minew, miedit, midelete;
    
    JPanel pan=new JPanel();
    
    
    public ManageAccount(){
    setSize(600,410);
    setLocationRelativeTo(null);
    setVisible(true);
        setResizable(false);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
    add(pan);
    pan.setLayout(null);
    pan.setBackground(Color.WHITE);
    
    managebar=new JLabel(new ImageIcon("images/manage_account.png"));
    pan.add(managebar);
    managebar.setBounds(0,0,600,34);
    txtmanage=new JLabel("Manage Account");
    pan.add(txtmanage,0);
    txtmanage.setBounds(20,5,150,25);
    txtmanage.setForeground(Color.WHITE);
    
    accountType=new JLabel("Account Type");
    pan.add(accountType);
    accountType.setBounds(20,45,100,25);
    cbaccountType=new JComboBox();
    pan.add(cbaccountType);
    cbaccountType.addItem("");
    cbaccountType.addItem("Current");
    cbaccountType.addItem("Saving");
    cbaccountType.addItem("Fixed");
    cbaccountType.setBounds(115,45,200,25);
    cbaccountType.addActionListener(this);
    
    btnrefresh=new JButton(new ImageIcon("images/refresh.png"));
    pan.add(btnrefresh);
    btnrefresh.setBounds(330,45,25,25);
    btnrefresh.setToolTipText("Refresh");
    btnrefresh.addActionListener(this);
    
    btnnew=new JButton("New");
    pan.add(btnnew);
    btnnew.setBounds(325,335,80,25);
    btnnew.addActionListener(this);
    btnedit=new JButton("Edit");
    pan.add(btnedit);
    btnedit.addActionListener(this);
    btnedit.setBounds(410,335,80,25);
    btndelete=new JButton("Delete");
    pan.add(btndelete);
    btndelete.addActionListener(this);
    btndelete.setBounds(495,335,80,25);
    
    model.addColumn("Account Type");
    model.addColumn("Account Name");
    model.addColumn("Min. Balance");
    model.addColumn("Interest Rate");
    model.addColumn("Min. Duration");
    
    int v= ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
    int h= ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
    JScrollPane jsp = new JScrollPane(table,v,h);
    pan.add(jsp);
    jsp.setBounds(20,80,555,250);
    
  DisplayData();
    
    }
    
    public void DisplayData(){
       try{
           model.setRowCount(0);
        DBConnection dbc= new DBConnection();
        stmt=dbc.con.createStatement();
        rs=stmt.executeQuery("select * from account_details");          
        while(rs.next()){
        model.addRow(new Object[]{rs.getString("accountType"), rs.getString("accountName"), rs.getString("minimumBalance"), rs.getString("interestRate"), rs.getString("minimumDuration")});
        } 
    }    
    catch(Exception ex){
    JOptionPane.showMessageDialog(null,ex);
    }
    }
    
    public static void main(String args[]){
    new ManageAccount();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==cbaccountType){
           if(cbaccountType.getSelectedItem().toString()!=""){
       try{
           model.setRowCount(0);
       DBConnection dbc=new DBConnection();
       pstmt=dbc.con.prepareStatement("select * from account_details where accountType=?");
       pstmt.setString(1,cbaccountType.getSelectedItem().toString());
       rs=pstmt.executeQuery();
       while(rs.next()){
       model.addRow(new Object[]{rs.getString("accountType"), rs.getString("accountName"), rs.getString("minimumBalance"), rs.getString("interestRate"), rs.getString("minimumDuration")});
       }
       }
       catch(Exception ex){JOptionPane.showMessageDialog(null,ex); }
       }
           else{
           DisplayData();
       
       }
       }
       
       if(e.getSource()==btnrefresh){
       DisplayData();
       cbaccountType.setSelectedItem("");
       }
       
       if(e.getSource()==btnnew){
       new ManageAccountNew();
       }
       
       if(e.getSource()==btnedit){
           try{
         String strat=table.getValueAt(table.getSelectedRow(),0).toString();
         String stran=table.getValueAt(table.getSelectedRow(),1).toString();
         DBConnection dbc=new DBConnection();
         pstmt=dbc.con.prepareStatement("select * from account_details where accountType=? and accountName=?");
         pstmt.setString(1,strat);
         pstmt.setString(2,stran);
         rs=pstmt.executeQuery();
         if(rs.next()){
             ManageAccountNew mgn=new ManageAccountNew();
             mgn.edittext.setText("Edit Account");
             mgn.cbaccountType.setSelectedItem(rs.getString("accountType"));
             mgn.cbaccountType.setEnabled(false);
             mgn.txtaccountName.setEditable(false);
             mgn.txtaccountName.setText(rs.getString("accountName"));
             mgn.txtminimumBalance.setText(rs.getString("minimumBalance"));
             mgn.txtinterestRate.setText(rs.getString("interestRate"));
             mgn.txtminimumDuration.setText(rs.getString("minimumDuration"));
             
             mgn.btnsave.setText("Update");
             mgn.btnsave.setBounds(150,245,210,25);
             mgn.btnreset.hide();
             
         }
      
           }
           catch(Exception ex){
           JOptionPane.showMessageDialog(null, ex);
           }
   
       }
       
       if(e.getSource()==btndelete){
       try{
         String strat=table.getValueAt(table.getSelectedRow(),0).toString();
         String stran=table.getValueAt(table.getSelectedRow(),1).toString();
         DBConnection dbc=new DBConnection();
         pstmt=dbc.con.prepareStatement("delete from account_details where accountType=? and accountName=?");
         pstmt.setString(1,strat);
         pstmt.setString(2,stran);
         int result=pstmt.executeUpdate();
         if(result>0){
         JOptionPane.showMessageDialog(null, "Account Deleted");
         DisplayData();
         }
       }
       catch(Exception ex){
       JOptionPane.showMessageDialog(null,ex);
       }
       }
       
       
       
    }
    
    
    
}
