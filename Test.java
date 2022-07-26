import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author shory
 */
public class Test extends javax.swing.JFrame {

    /**
     * Creates new form question
     */
    public Test() {
        initComponents();
        
        
        
        Connect();
        LoadQuestions();
        NullAllGivenAnswers();
    }

    Connection con;
    PreparedStatement pst;
    
    //Statement stat;
    String cor=null;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     * 
     * 
     * 
     */
    int answercheck=0;
    int marks=0;
    //String cor=null;
    String answer=null;
    Statement stat;
    
    
    
    public boolean answerCheck()
            
        {
            String answerAnswer="";

            if(r1.isSelected())
            {
                answerAnswer = r1.getText();

            }

           else if(r2.isSelected())
            {
                answerAnswer = r2.getText();

            }

               else if(r3.isSelected())
            {
                answerAnswer = r3.getText();

            }

                else if(r4.isSelected())
            {

                answerAnswer = r4.getText();

            }
           
            if(answerAnswer.equals(cor) && (answer == null  || !answer.equals(cor)))
            {
                marks = marks + 1;
          
                txtmarks.setText(String.valueOf(marks));
               
             
            }
            
           else if(!answerAnswer.equals(cor) && answer != null)
            {
                
                // Only deduct if marks greater than zero
                if( marks > 0){
        
                marks = marks - 1;
                
                }
               
               
                txtmarks.setText(String.valueOf(marks));
               

            }
            // use to store answers
            if(!answerAnswer.equals("")){
                try {
                    String query = "UPDATE question SET givenanswer = ? WHERE question = ?";
                    pst = con.prepareStatement(query);
                    pst.setString(1, answerAnswer);
                    pst.setString(2, txtques.getText());
                    pst.execute();
                } catch (SQLException ex) {
                   ex.printStackTrace();
                }
                   return true;
            }
            // when no button is selected
            return false;

}
    
    private void NullAllGivenAnswers()
    {
        try {
            String query="UPDATE question SET givenanswer =?";
            pst=con.prepareStatement(query);
            pst.setString(1, null);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    private boolean AlreadyAnswered()
    {
        
        try {
            String query="SELECT givenanswer FROM question WHERE question='"+txtques.getText()+"'";
            pst=con.prepareStatement(query);
            
            ResultSet res=pst.executeQuery(query);
            while(res.next())
            {
                answer=res.getString("givenanswer");
                if(answer==null)
                {
                    return false;
                }
                break;
            }
            
          if(r1.getText().equals(answer))  
          {
              r1.setSelected(true);
          }
          else if(r2.getText().equals(answer))
          {
              r2.setSelected(true);
          }
          else if(r3.getText().equals(answer))
          {
              r3.setSelected(true);
          }
          else if(r4.getText().equals(answer))
          {
              r4.setSelected(true);
          } 
               
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    } 
    ResultSet rs;
    public void Connect()
    {
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost/qmsdb","root","");
        }catch(ClassNotFoundException ex)
        {
             Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } 
               
    }
    
    public void LoadQuestions()
    {
        
        
        Statement st=null;
        
        try {
           pst=con.prepareStatement("select * from question");
                rs=pst.executeQuery();
           
            while(rs.next())
            {
                
                jLabel2.setText(rs.getString(1));
                txtques.setText(rs.getString(2));
                r1.setText(rs.getString(3));
                r2.setText(rs.getString(4));
                r3.setText(rs.getString(5));
                r4.setText(rs.getString(6));
                cor =rs.getString(7);
                //for one row only
                
                break;
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        r1 = new javax.swing.JRadioButton();
        r2 = new javax.swing.JRadioButton();
        r3 = new javax.swing.JRadioButton();
        r4 = new javax.swing.JRadioButton();
        txtques = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtmarks = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(51, 0, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI Emoji", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 102));
        jLabel1.setText("Online Test");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(274, 12, 285, 75));

        jLabel2.setBackground(new java.awt.Color(255, 255, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 153));
        jLabel2.setText("No");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 93, 60, 40));

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        buttonGroup1.add(r1);
        r1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r1.setText("jRadioButton1");

        buttonGroup1.add(r2);
        r2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r2.setText("jRadioButton2");

        buttonGroup1.add(r3);
        r3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r3.setText("jRadioButton3");

        buttonGroup1.add(r4);
        r4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        r4.setText("jRadioButton4");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(r1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(r4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(r3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(r2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(528, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(r1)
                .addGap(45, 45, 45)
                .addComponent(r2)
                .addGap(51, 51, 51)
                .addComponent(r3)
                .addGap(50, 50, 50)
                .addComponent(r4)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 710, 360));

        txtques.setBackground(new java.awt.Color(204, 255, 0));
        txtques.setFont(new java.awt.Font("Segoe UI Emoji", 1, 24)); // NOI18N
        txtques.setForeground(new java.awt.Color(153, 51, 0));
        txtques.setText("Questions");
        getContentPane().add(txtques, new org.netbeans.lib.awtextra.AbsoluteConstraints(43, 97, 657, 40));

        jButton1.setBackground(new java.awt.Color(153, 153, 255));
        jButton1.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(51, 51, 0));
        jButton1.setText("NEXT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 522, 141, 54));

        txtmarks.setFont(new java.awt.Font("Segoe UI Historic", 1, 18)); // NOI18N
        txtmarks.setForeground(new java.awt.Color(51, 0, 0));
        txtmarks.setText("Marks");
        txtmarks.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtmarksAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(txtmarks, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 522, 102, 38));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/quizback.jpg"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 860, 610));

        pack();
    }// </editor-fold>                        

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        
        if(answerCheck())
        {
            try {
                if(rs.next())
                {
                    txtques.setText(rs.getString("question"));
                    r1.setText(rs.getString(3));
                    r2.setText(rs.getString(4));
                    r3.setText(rs.getString(5));
                    r4.setText(rs.getString(6));
                    
                    
                 cor=rs.getString(7);
                 
                 
                 
                 if(!AlreadyAnswered())
                 {
                     buttonGroup1.clearSelection();
                 }
                 
                    
                    
                }
                else{
                    JOptionPane.showMessageDialog(this, "This is first record of student");
                      
                      String marks1=String.valueOf(marks);
                      JOptionPane.showMessageDialog(null,marks);
                }
                
                
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        
        
    }                                        

    private void txtmarksAncestorAdded(javax.swing.event.AncestorEvent evt) {                                       
        // TODO add your handling code here:
    }                                      

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton r1;
    private javax.swing.JRadioButton r2;
    private javax.swing.JRadioButton r3;
    private javax.swing.JRadioButton r4;
    private javax.swing.JLabel txtmarks;
    private javax.swing.JLabel txtques;
    // End of variables declaration                   
}
