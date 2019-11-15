package project1_db;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Project1_db extends JFrame{
    
    private JPanel sidePanel, conPanel, dbPanel, tabPanel, tab2Panel, tab3Panel, tab4Panel, aboutPanel;
    private JLabel conLabel, dbLabel, tabLabel, aboutLabel;
    private JTextField urlText, userText, passText;
    private JButton conBtn;
    private Connection con = null;
    private String db, tableName;
    private int createTableRow = 1;
    private ArrayList<Object> fieldNames, fieldTypes, fieldLength, fieldNull, fieldAI, fieldPK;
    
    public Project1_db(){
        
        setTitle("dbGUI");
        
        //initialize sidePanel
        sidePanel = new JPanel();
        sidePanel.setBackground(new Color(51, 153, 0));
        sidePanel.setLayout(null);
        sidePanel.setBounds(0, 0, 250, 600);
        
        //initialize conPanel
        conPanel = new JPanel();
        conPanel.setBackground(Color.WHITE);
        conPanel.setOpaque(true);
        conPanel.setLayout(null);
        conPanel.setBounds(250, 0, 750, 600);
        
        //initialize dbPanel
        dbPanel = new JPanel();
        dbPanel.setBackground(Color.WHITE);
        dbPanel.setOpaque(true);
        dbPanel.setLayout(null);
        dbPanel.setBounds(250, 0, 750, 600);
        
        //initialize tabPanel
        tabPanel = new JPanel();
        tabPanel.setBackground(Color.WHITE);
        tabPanel.setOpaque(true);
        tabPanel.setLayout(null);
        tabPanel.setBounds(250, 0, 750, 600);
        
        //initialize create table panel
        tab2Panel = new JPanel();
        tab2Panel.setBackground(Color.WHITE);
        tab2Panel.setOpaque(true);
        tab2Panel.setLayout(null);
        tab2Panel.setBounds(250, 0, 750, 600);
        
        //initialize table values panel
        tab3Panel = new JPanel();
        tab3Panel.setBackground(Color.WHITE);
        tab3Panel.setOpaque(true);
        tab3Panel.setLayout(null);
        tab3Panel.setBounds(250, 0, 750, 600);
        
        //initialize table structure panel
        tab4Panel = new JPanel();
        tab4Panel.setBackground(Color.WHITE);
        tab4Panel.setOpaque(true);
        tab4Panel.setLayout(null);
        tab4Panel.setBounds(250, 0, 750, 600);
     
        //initialize aboutPanel
        aboutPanel = new JPanel();
        aboutPanel.setBackground(Color.WHITE);
        aboutPanel.setOpaque(true);
        aboutPanel.setLayout(null);
        aboutPanel.setBounds(250, 0, 750, 600);
        
        //add components to frame
        add(sidePanel);
        add(dbPanel);
        add(tabPanel);
        add(tab2Panel);
        add(tab3Panel);
        add(tab4Panel);
        add(aboutPanel);
        add(conPanel);
        
        //set visibility of panels
        dbPanel.setVisible(false);
        tabPanel.setVisible(false);
        tab2Panel.setVisible(false);
        tab3Panel.setVisible(false);
        tab4Panel.setVisible(false);
        aboutPanel.setVisible(false);
        conPanel.setVisible(true);
        
        //set frame
        setSize(1000, 600);
        setResizable(false);
        setLayout(null);
        setVisible(true);
        
        //display initial components
        showSidePanel();
        showConPanel();
        conLabel.setForeground(Color.YELLOW);
        showAboutPanel();
        
        //action listener
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                try{
                    con.close();            
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(sidePanel, ex.getMessage());
                }
                dispose();
                System.exit(0);
            }
        });      
    }
    
    
    
    //show about panel
    private void showAboutPanel(){
    
        Color mGreen = new Color(51, 153, 0);
        Font font = new Font("serif", Font.BOLD, 30);
        JLabel text = new JLabel("<html>dbGUI is a modern user-interface for working with MySQL database. It is simple to use and very effective for performing CRUD operations on MySQL database. Now, a user with no knowledge of MySQL commands can easily work and handle the MySQL database.</html>");
        text.setFont(font);
        text.setForeground(mGreen);
        text.setBounds(50, 50, 650, 300);
        font = new Font("serif", Font.PLAIN, 18);
        JLabel dev = new JLabel("<html>Developer Info:<br>Name-Hitesh Verma<br>Student at DIT UNIVERSITY, Dehradun<br>Roll No.160102091<br>SAP ID 1000007432</html>");
        dev.setFont(font);
        dev.setForeground(mGreen);
        dev.setBounds(50, 350, 650, 150);
        aboutPanel.add(text);
        aboutPanel.add(dev);
    
    }
    
    
    
    //show side panel
    private void showSidePanel(){
                  
        Font font = new Font("serif", Font.BOLD, 30);
        
        //Product Name
        JLabel productName = new JLabel("dbGUI");
        productName.setFont(font);
        productName.setForeground(Color.WHITE);
        productName.setHorizontalAlignment(JLabel.CENTER);
        productName.setBounds(0, 50, 250, 50);
        
        font = new Font("serif", Font.ITALIC, 14);
        
        //ByLine
        JLabel byline = new JLabel("developed by Hitesh");
        byline.setFont(font);
        byline.setForeground(Color.WHITE);
        byline.setHorizontalAlignment(JLabel.TRAILING);
        byline.setBounds(0, 100, 230, 50);
        
        font = new Font("serif", Font.BOLD, 24);
        
        //connect label
        conLabel = new JLabel("CONNECT");
        conLabel.setFont(font);
        conLabel.setForeground(Color.WHITE);
        conLabel.setBounds(20, 250, 250, 50);
        
        //database label
        dbLabel = new JLabel("DATABASES");
        dbLabel.setFont(font);
        dbLabel.setForeground(Color.WHITE);
        dbLabel.setBounds(20, 300, 250, 50);
        
        //table label
        tabLabel = new JLabel("TABLES");
        tabLabel.setFont(font);
        tabLabel.setForeground(Color.WHITE);
        tabLabel.setBounds(20, 350, 250, 50);
        
        //about label
        aboutLabel = new JLabel("ABOUT US");
        aboutLabel.setFont(font);
        aboutLabel.setForeground(Color.WHITE);
        aboutLabel.setBounds(20, 400, 250, 50);
        
        //add components to side panel
        sidePanel.add(productName);
        sidePanel.add(byline);
        sidePanel.add(conLabel);
        sidePanel.add(dbLabel);
        sidePanel.add(tabLabel);
        sidePanel.add(aboutLabel);
        
        sidePanel.setVisible(false);
        sidePanel.setVisible(true);
        
        //action listeners
        conLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                dbLabel.setForeground(Color.WHITE);
                tabLabel.setForeground(Color.WHITE);
                aboutLabel.setForeground(Color.WHITE);
                conLabel.setForeground(Color.YELLOW);
                
                dbPanel.setVisible(false);
                tabPanel.setVisible(false);
                tab2Panel.setVisible(false);
                tab3Panel.setVisible(false);
                tab4Panel.setVisible(false);
                aboutPanel.setVisible(false);
                conPanel.setVisible(true);
            }
        });
        dbLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                tabLabel.setForeground(Color.WHITE);
                aboutLabel.setForeground(Color.WHITE);
                conLabel.setForeground(Color.WHITE);
                dbLabel.setForeground(Color.YELLOW);
                
                tabPanel.setVisible(false);
                tab2Panel.setVisible(false);
                tab3Panel.setVisible(false);
                tab4Panel.setVisible(false);
                aboutPanel.setVisible(false);
                conPanel.setVisible(false);
                dbPanel.setVisible(true);
            }
        });
        tabLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){ 
                aboutLabel.setForeground(Color.WHITE);
                conLabel.setForeground(Color.WHITE);
                dbLabel.setForeground(Color.WHITE);
                tabLabel.setForeground(Color.YELLOW);
                              
                aboutPanel.setVisible(false);
                conPanel.setVisible(false);
                dbPanel.setVisible(false);
                tab2Panel.setVisible(false);
                tab3Panel.setVisible(false);
                tab4Panel.setVisible(false);
                tabPanel.setVisible(true);
            }
        });
        aboutLabel.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){                                        
                conLabel.setForeground(Color.WHITE);
                dbLabel.setForeground(Color.WHITE);
                tabLabel.setForeground(Color.WHITE);
                aboutLabel.setForeground(Color.YELLOW);
                                                            
                conPanel.setVisible(false);
                dbPanel.setVisible(false);
                tabPanel.setVisible(false);
                tab2Panel.setVisible(false);
                tab3Panel.setVisible(false);
                tab4Panel.setVisible(false);
                aboutPanel.setVisible(true);
            }
        });
          
    }
    
              
    
    //show connect panel
    private void showConPanel(){
      
        Font font = new Font("serif", Font.BOLD, 24);
        Color mGreen = new Color(51, 153, 0);
        
        //text line
        JLabel label = new JLabel("Connect to your MySQL Database ... ");
        label.setFont(font);
        label.setForeground(mGreen);
        label.setBounds(50, 80, 500, 50);
        
        //url label
        JLabel url = new JLabel("URL");
        url.setFont(font);
        url.setForeground(mGreen);
        url.setBounds(50, 150, 200, 50);
        
        //url text
        urlText = new JTextField("jdbc:mysql://localhost:3306");
        urlText.setFont(font);
        urlText.setBounds(220, 150, 400, 50);
        
        //user label
        JLabel user = new JLabel("User");
        user.setFont(font);
        user.setForeground(mGreen);
        user.setBounds(50, 200, 200, 50);
       
        //user text
        userText = new JTextField();
        userText.setFont(font);
        userText.setBounds(220, 200, 400, 50);
        
        //password label
        JLabel pass = new JLabel("Password");
        pass.setFont(font);
        pass.setForeground(mGreen);
        pass.setBounds(50, 250, 200, 50);
        
        //pass text
        passText = new JTextField();
        passText.setFont(font);
        passText.setBounds(220, 250, 400, 50);
       
        //connect Button
        conBtn = new JButton("CONNECT");
        conBtn.setFont(font);
        conBtn.setForeground(Color.WHITE);
        conBtn.setBackground(mGreen);
        conBtn.setBounds(50, 350, 250, 50);
        
        //status label
        JLabel status = new JLabel("");
        status.setForeground(mGreen);
        status.setBounds(50, 420, 600, 50);
        
        //add components to connect panel
        conPanel.add(label);
        conPanel.add(url);
        conPanel.add(urlText);
        conPanel.add(user);
        conPanel.add(userText);
        conPanel.add(pass);
        conPanel.add(passText);
        conPanel.add(conBtn);
        conPanel.add(status);
        
        conPanel.setVisible(false);
        conPanel.setVisible(true);
        
        //connect button action listener
        conBtn.addActionListener((ActionEvent e)->{
            
                //check if con already exists, disconnect it
                if(con != null){               
                    //close connection
                    try{
                        con.close(); 
                        con = null; 
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(conPanel, ex.getMessage());
                    }
                    //hide panels components
                    hidePanel();                   
                    //enable user inputs
                    urlText.setEnabled(true);
                    userText.setEnabled(true);
                    passText.setEnabled(true);                   
                    //rename connect button
                    conBtn.setText("CONNECT");
                    //change status label
                    status.setText("Disconnected");
                
                }
                //create connection
                else{
                    String mUrl = urlText.getText();
                    String mUser = userText.getText();
                    String mPass = passText.getText();
                
                    passText.setText("");
                    
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection(mUrl, mUser, mPass);
                        if(con != null){
                            status.setText("Connection Successful");
                        
                            //disable user inputs
                            urlText.setEnabled(false);
                            userText.setEnabled(false);
                            passText.setEnabled(false);
                        
                            //rename connect btn
                            conBtn.setText("DISCONNECT");
                            
                            //edit DbPanel
                            editDbPanel();
                            
                            //change side panel's labels color
                            conLabel.setForeground(Color.WHITE);
                            dbLabel.setForeground(Color.YELLOW);
                                                                          
                            //set visibility of the panels
                            conPanel.setVisible(false);
                            dbPanel.setVisible(true); 
                        }
                        else{
                            status.setText("Connection Failed");
                        }
                    }
                    catch(Exception ex){ status.setText(ex.toString()); }                   
                }
        });
    }
                  
    
    
    //edit database Panel
    private void editDbPanel(){
        
        Font font = new Font("serif", Font.ITALIC, 20);
        Color mGreen = new Color(51, 153, 0);
        
        //text line
        JLabel label = new JLabel("Click on the database to use ...");
        label.setFont(font);
        label.setForeground(mGreen);
        label.setBounds(50, 50, 700, 50);
        
        //database name table
        DefaultTableModel model = new DefaultTableModel();
        JTable table  = new JTable(model);
        model.addColumn("Database Name");
    
        try{
            String sql = "SHOW DATABASES";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1)});   
            }          
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(dbPanel, e.getMessage());
        }                              
        
        font = new Font("serif", Font.BOLD, 24);
        
        //set database name table
        table.setBackground(Color.WHITE);
        table.setRowHeight(50);
        table.setFont(font);
      
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 100, 650, 300);
        
        //create database button
        JButton btn = new JButton("CREATE DATABASE");
        btn.setFont(font);
        btn.setBackground(mGreen);
        btn.setForeground(Color.WHITE);
        btn.setBounds(480, 450, 220, 50);
        
        //add components to database panel
        dbPanel.add(label);
        dbPanel.add(scroll);
        dbPanel.add(btn);
              
        //action listener
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String dbName = JOptionPane.showInputDialog(dbPanel, "Enter Database Name");
                
                if(dbName != null && !dbName.trim().equals("")){                  
                   try{
                        String sql = "CREATE DATABASE " + dbName;
                        Statement st = con.createStatement();
                        int result = st.executeUpdate(sql);
                        if(result == 1){
                            JOptionPane.showMessageDialog(dbPanel, "Database created successfully!");
                            model.addRow(new Object[]{dbName});
                        }
                        else{
                            JOptionPane.showMessageDialog(dbPanel, "Error while creating the database!");
                        }
                    }
                    catch(Exception ex){
                        JOptionPane.showMessageDialog(dbPanel, ex.getMessage());
                    }
                }
            }
        });       
        table.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
                JTable cell = (JTable)e.getSource();
                int row = cell.getSelectedRow();
                int col = cell.getSelectedColumn();
                db = table.getValueAt(row, col).toString();
                
                Object[] options = {"USE", "DELETE"};
                int op = JOptionPane.showOptionDialog(dbPanel, "Select for database " + db, "Message", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
               
                if(op == 0){
                    //edit the table panel
                    editTabPanel();
                    //change side panel's labels color
                    dbLabel.setForeground(Color.WHITE);
                    tabLabel.setForeground(Color.YELLOW);
                    //set visibility of the panels
                    dbPanel.setVisible(false);
                    tabPanel.setVisible(true);
                }
                else if(op == 1){
                    op = JOptionPane.showConfirmDialog(dbPanel, "Are you sure? You want to delete the database " + db + " permanently?", "Delete", JOptionPane.YES_NO_OPTION);
                    if(op == 0){ 
                        try{
                            String sql = "DROP DATABASE " + db;
                            Statement st = con.createStatement();
                            op = st.executeUpdate(sql);
                            if(op == 0){
                                model.removeRow(row);
                                JOptionPane.showMessageDialog(dbPanel, "Database deleted successfully!");
                            }
                            else{
                               JOptionPane.showMessageDialog(dbPanel, "Error while deleting the database!"); 
                            }
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(dbPanel, ex.getMessage()); 
                        }
                    }
                }
            }
        });
    }
    
       
    
    //edit table Panel
    private void editTabPanel(){
               
        //if table panel is called second time, by selecting another database;
        tabPanel.removeAll();
        
        Font font = new Font("serif", Font.BOLD, 18);
        Color mGreen = new Color(51, 153, 0);
        
        //database name label
        JLabel text = new JLabel("Database: " + db);
        text.setFont(font);
        text.setForeground(mGreen);
        text.setBounds(10, 10, 700, 30);
        
        //text line
        JLabel label = new JLabel("Click on the table to use ...");
        label.setFont(font);
        label.setForeground(mGreen);
        label.setBounds(10, 40, 700, 30);
        
        //table-name table
        DefaultTableModel model = new DefaultTableModel();
        JTable table  = new JTable(model);
        model.addColumn("Table Name");
    
        try{
            String sql = "USE " + db;
            Statement st = con.createStatement();
            st.executeQuery(sql);
            
            sql = "SHOW TABLES";
            st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1)});
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tabPanel, e.getMessage());
        }
                
        font = new Font("serif", Font.BOLD, 24);
        
        //set table-name table
        table.setBackground(Color.WHITE);
        table.setRowHeight(50);
        table.setFont(font);
    
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(50, 100, 650, 350);
        
        //create table button
        JButton btn = new JButton("CREATE TABLE");
        btn.setFont(font);
        btn.setForeground(Color.WHITE);
        btn.setBackground(mGreen);
        btn.setBounds(480, 480, 220, 50);
                
        //add components to table panel
        tabPanel.add(text);
        tabPanel.add(label);
        tabPanel.add(scroll);
        tabPanel.add(btn);
        
        //action listeners
        btn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) { 
                showTab2Panel();
                tabPanel.setVisible(false);
                tab2Panel.setVisible(true);
            }
        });
        table.addMouseListener(new MouseAdapter(){     
            public void mousePressed(MouseEvent e){            
                JTable cell = (JTable)e.getSource();
                int row = cell.getSelectedRow();
                int col = cell.getSelectedColumn();
                tableName = table.getValueAt(row, col).toString();                                
                showTab3Panel();
                tabPanel.setVisible(false);               
                tab3Panel.setVisible(true);
            }
        });              
    }
    
    
     
    //show create table panel
    private void showTab2Panel(){
        
        tab2Panel.removeAll();
        
        Color mGreen = new Color(51, 153, 0);
    
        //back button
        JButton backBtn = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("back_icon.png"));
            backBtn.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tab2Panel, ex.getMessage());
        }
        backBtn.setBackground(mGreen);
        backBtn.setBounds(20, 20, 40, 40);
        
        Font font = new Font("serif", Font.BOLD, 18);
        
        JLabel label1 = new JLabel("Database Name: " + db);
        label1.setFont(font);
        label1.setForeground(mGreen);
        label1.setBounds(100, 10, 650, 20);
        
        JLabel label2 = new JLabel("CREATE TABLE");
        label2.setFont(font);
        label2.setForeground(mGreen);
        label2.setBounds(100, 40, 650, 30);
        
        JLabel label3 = new JLabel("Table Name: ");
        label3.setFont(font);
        label3.setForeground(mGreen);
        label3.setBounds(100, 80, 200, 50);
        
        JTextField tableText = new JTextField();
        tableText.setFont(font);
        tableText.setBounds(250, 90, 300, 30);
        
        //add table fields sub panel 
        JPanel subPanel = new JPanel();
        
        JLabel fieldLabel = new JLabel("Field");
        fieldLabel.setFont(font);
        
        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(font);
        
        JLabel lengthLabel = new JLabel("Length");
        lengthLabel.setFont(font);
        
        JLabel nullLabel = new JLabel("Null");
        nullLabel.setFont(font);
        
        JLabel autoLabel = new JLabel("Auto Increment");
        autoLabel.setFont(font);
        
        JLabel primaryLabel = new JLabel("Primary Key");
        primaryLabel.setFont(font);
        
        //add column labels to sub panel
        subPanel.add(fieldLabel);
        subPanel.add(typeLabel);
        subPanel.add(lengthLabel);
        subPanel.add(nullLabel);
        subPanel.add(autoLabel);
        subPanel.add(primaryLabel);
        
        //arrayLists to store subpanel components
        fieldNames = new ArrayList<>();
        fieldTypes = new ArrayList<>();
        fieldLength = new ArrayList<>();
        fieldNull = new ArrayList<>();
        fieldAI = new ArrayList<>();
        fieldPK = new ArrayList<>();
       
        String[] datatype = {"INT", "VARCHAR", "TEXT", "DATE"};
        
        for(int i=0; i<3; i++){
            
            createTableRow++;
            
            JTextField fieldText = new JTextField();
            fieldNames.add(fieldText);
                     
            JComboBox type = new JComboBox(datatype);
            type.setBackground(Color.WHITE);
            fieldTypes.add(type);
               
            JTextField lenText = new JTextField();
            fieldLength.add(lenText);
             
            JCheckBox nullCheck = new JCheckBox();
            nullCheck.setBackground(Color.WHITE);
            fieldNull.add(nullCheck);
                   
            JCheckBox autoCheck = new JCheckBox();
            autoCheck.setBackground(Color.WHITE);
            fieldAI.add(autoCheck);
            
            JCheckBox primaryCheck = new JCheckBox();
            primaryCheck.setBackground(Color.WHITE);
            fieldPK.add(primaryCheck);
        
            subPanel.add(fieldText);
            subPanel.add(type);
            subPanel.add(lenText);
            subPanel.add(nullCheck);
            subPanel.add(autoCheck);
            subPanel.add(primaryCheck);                      
                
            nullCheck.addItemListener((ItemEvent e)->{
                //if null box is checked, disable AI and PK boxes
                if(e.getStateChange() == 1){
                    autoCheck.setSelected(false);
                    autoCheck.setEnabled(false);
                    primaryCheck.setSelected(false);
                    primaryCheck.setEnabled(false);
                }
                else{
                    autoCheck.setEnabled(true);
                    primaryCheck.setEnabled(true);
                }
            });
            autoCheck.addItemListener((ItemEvent e)->{
                //if AI box is checked, disable NULL and PK boxes
                if(e.getStateChange() == 1){
                    nullCheck.setSelected(false);
                    nullCheck.setEnabled(false);
                    primaryCheck.setSelected(true);
                    primaryCheck.setEnabled(false);
                    disableAICheck();
                }
                else{
                    nullCheck.setEnabled(true);
                    primaryCheck.setEnabled(true);
                    disableAICheck();
                }
            });
            primaryCheck.addItemListener((ItemEvent e)->{
                //if PK box is checked, disable null box
                if(e.getStateChange() == 1){
                    nullCheck.setSelected(false);
                    nullCheck.setEnabled(false);
                    disablePKCheck();
                }
                else{
                    nullCheck.setEnabled(true);
                    disablePKCheck();
                }
            });
            type.addActionListener((ActionEvent e)->{
                String typeSelected = type.getSelectedItem().toString().trim();
            
                //if date is selected, disable length text field
                if(typeSelected.equals("DATE")){                                  
                    lenText.setText("");
                    lenText.setEnabled(false);
                }
                else{
                    lenText.setEnabled(true);
                }
            });
        }
               
        subPanel.setBackground(Color.WHITE);
        subPanel.setLayout(new GridLayout(createTableRow, 6, 10, 10));
        
        JScrollPane scroll = new JScrollPane(subPanel);
        scroll.setBounds(10, 150, 730, 250);
 
        //add row button
        JButton rowBtn = new JButton("Add Row");
        rowBtn.setBounds(400, 410, 150, 30);
        
        //delete row button
        JButton delBtn = new JButton("Delete Row");
        delBtn.setBounds(580, 410, 150, 30);
        
        //save table button
        JButton saveBtn = new JButton("SAVE TABLE");
        saveBtn.setFont(font);
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setBackground(mGreen);
        saveBtn.setBounds(250, 470, 300, 50);
              
        //add components to create table panel
        tab2Panel.add(backBtn);
        tab2Panel.add(label1);
        tab2Panel.add(label2);
        tab2Panel.add(label3);
        tab2Panel.add(tableText);
        tab2Panel.add(scroll);
        tab2Panel.add(rowBtn);
        tab2Panel.add(delBtn);
        tab2Panel.add(saveBtn);
                
        //action Listeners
        backBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createTableRow = 1;
                tab2Panel.removeAll();
                subPanel.removeAll();
                tab2Panel.setVisible(false);
                tabPanel.setVisible(true);
            }
        });
        rowBtn.addActionListener((ActionEvent e)->{
            addRow(subPanel);
        });
        delBtn.addActionListener((ActionEvent e)->{
            delRow(subPanel);
        });
         saveBtn.addActionListener((ActionEvent e)->{
            String name = tableText.getText();
            if(name.trim().equals("")){
                JOptionPane.showMessageDialog(tab2Panel, "You have not entered the TABLE NAME.");
            }
            else{
                saveTable(name);
            }           
        });       
    }
    
    
    
    //disable all other AI checkboxes, if one AI checkbox is selected
    private void disableAICheck(){
    
        int index = -1;
        
        for(int i=0; i<fieldAI.size(); i++){
            JCheckBox checkBox = (JCheckBox) fieldAI.get(i);
            if(checkBox.isSelected()){
                index = i;
                break;
            }
        }
        
        if(index != -1){
            for(int i=0; i<fieldAI.size(); i++){
                JCheckBox checkBox = (JCheckBox) fieldAI.get(i);
                if(i != index){
                    checkBox.setSelected(false);
                    checkBox.setEnabled(false);
                }
            }
        }
        else{
           for(int i=0; i<fieldAI.size(); i++){
                JCheckBox checkBox = (JCheckBox) fieldAI.get(i);
                checkBox.setSelected(false);
                checkBox.setEnabled(true);              
            } 
        }
    }
    
    
       
    //disable all other PK checkboxes, if one PK checkbox is selected
    private void disablePKCheck(){
    
        int index = -1;
        
        for(int i=0; i<fieldPK.size(); i++){
            JCheckBox checkBox = (JCheckBox) fieldPK.get(i);
            if(checkBox.isSelected()){
                index = i;
                break;
            }
        }
        
        if(index != -1){
            for(int i=0; i<fieldPK.size(); i++){
                JCheckBox checkBox = (JCheckBox) fieldPK.get(i);
                if(i != index){
                    checkBox.setSelected(false);
                    checkBox.setEnabled(false);
                }
            }
        }
        else{
            for(int i=0; i<fieldPK.size(); i++){
                JCheckBox checkBox = (JCheckBox) fieldPK.get(i);              
                checkBox.setSelected(false);
                checkBox.setEnabled(true);              
            }
        }
    }
    
     
    
    //add new row to sub panel in create table panel
    private void addRow(JPanel subPanel){
       
        String[] datatype = {"INT", "VARCHAR", "TEXT", "DATE"};
        
        JTextField fieldText = new JTextField();
        fieldNames.add(fieldText);
                     
        JComboBox type = new JComboBox(datatype);
        type.setBackground(Color.WHITE);
        fieldTypes.add(type);
               
        JTextField lenText = new JTextField();
        fieldLength.add(lenText);
             
        JCheckBox nullCheck = new JCheckBox();
        nullCheck.setBackground(Color.WHITE);
        fieldNull.add(nullCheck);
                   
        JCheckBox autoCheck = new JCheckBox();
        autoCheck.setBackground(Color.WHITE);
        fieldAI.add(autoCheck);
            
        JCheckBox primaryCheck = new JCheckBox();
        primaryCheck.setBackground(Color.WHITE);
        fieldPK.add(primaryCheck);
       
        subPanel.add(fieldText);
        subPanel.add(type);
        subPanel.add(lenText);
        subPanel.add(nullCheck);
        subPanel.add(autoCheck);
        subPanel.add(primaryCheck);
        
        type.addActionListener((ActionEvent e)->{
            String typeSelected = type.getSelectedItem().toString().trim();
           
            //if date is selected, disable length text field
            if(typeSelected.equals("DATE")){             
                lenText.setText("");
                lenText.setEnabled(false);
            }
            else{
                lenText.setEnabled(true);
            }
        });
        nullCheck.addItemListener((ItemEvent e)->{
            //if null box is checked, disable AI and PK boxes
            if(e.getStateChange() == 1){
                autoCheck.setSelected(false);
                autoCheck.setEnabled(false);
                primaryCheck.setSelected(false);
                primaryCheck.setEnabled(false);
            }
            else{
                autoCheck.setEnabled(true);
                primaryCheck.setEnabled(true);
            }
        });
        autoCheck.addItemListener((ItemEvent e)->{
            //if AI box is checked, disable NULL and PK boxes
            if(e.getStateChange() == 1){
                nullCheck.setSelected(false);
                nullCheck.setEnabled(false);
                primaryCheck.setSelected(true);
                primaryCheck.setEnabled(false);
                disableAICheck();
            }
            else{
                nullCheck.setEnabled(true);
                primaryCheck.setEnabled(true);
                disableAICheck();
            }
        });
        primaryCheck.addItemListener((ItemEvent e)->{
            //if PK box is checked, disable null box
            if(e.getStateChange() == 1){
                nullCheck.setSelected(false);
                nullCheck.setEnabled(false);
                disablePKCheck();
            }
            else{
                nullCheck.setEnabled(true);
                disablePKCheck();
            }
        });
      
        createTableRow++;
        subPanel.setLayout(new GridLayout(createTableRow, 6, 10, 10));
        subPanel.setVisible(false);
        subPanel.setVisible(true);
        
        //useful to enable or disable new added row acc. to previous rows.
        disableAICheck();
        disablePKCheck();
    }
    
       
    
    //delete last row of subpanel in create table panel
    private void delRow(JPanel subPanel){
        
        int count = 0;
        int start = (createTableRow-1)*6;
    
        while(count < 6){
            subPanel.remove(start);
            count++;
        }
        
        createTableRow--;
        subPanel.setLayout(new GridLayout(createTableRow, 6, 10, 10));
        subPanel.setVisible(false);
        subPanel.setVisible(true);
        
        //remove last row data from arraylists
        fieldNames.remove(fieldNames.size()-1);
        fieldTypes.remove(fieldTypes.size()-1);
        fieldLength.remove(fieldLength.size()-1);
        fieldNull.remove(fieldNull.size()-1);
        fieldAI.remove(fieldAI.size()-1);
        fieldPK.remove(fieldPK.size()-1);
        
        //useful when row with AI or PK checked boxes is deleted.
        disableAICheck();
        disablePKCheck();      
    }
    
     
   
    //to save the created table
    private void saveTable(String name){
        
        StringBuffer sql = new StringBuffer("CREATE TABLE " + name + " ( ");
                   
        for(int i=0; i<fieldNames.size(); i++){
        
            JTextField field = (JTextField) fieldNames.get(i);
            JComboBox type = (JComboBox) fieldTypes.get(i);
            JTextField length = (JTextField) fieldLength.get(i);
            JCheckBox nullCheck = (JCheckBox) fieldNull.get(i);
            JCheckBox aiCheck = (JCheckBox) fieldAI.get(i);
            JCheckBox pkCheck = (JCheckBox) fieldPK.get(i);
            
            String fieldName = field.getText().trim();
            String typeSelected = type.getSelectedItem().toString().trim();
            int len = 0;
            boolean isNull = false, isAI = false, isPK = false;
            
            if(nullCheck.isSelected()){isNull = true; }
            if(aiCheck.isSelected()){isAI = true; }
            if(pkCheck.isSelected()){isPK = true; }
            
            //check if field name is left empty
            if(fieldName.equals("")){
                JOptionPane.showMessageDialog(tab2Panel, "You have not entered a FIELD NAME");
                return;
            }
            
            //check is length is not a number
            if(!length.getText().trim().equals("")){
                try{
                    len = Integer.parseInt(length.getText());
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(tab2Panel, "Invalid LENGTH given.");
                    return;
                } 
            }
            
            //check if auto_increment is used for text, varchar or date.                     
            if(isAI && !typeSelected.equals("INT")){
                JOptionPane.showMessageDialog(tab2Panel, typeSelected + " cannot be auto incremented.");
                return;
            }
                       
            //check if length is not given for varchar
            if(typeSelected.equals("VARCHAR") && len == 0){
                JOptionPane.showMessageDialog(tab2Panel, "No LENGTH given for VARCHAR");
                return;
            }
            
            sql.append(fieldName + " " + typeSelected);
            
            if(len != 0){
                sql.append("(" + len + ")");
            }
            
            if(isNull){
                sql.append(" NULL");
            }
            else{
                sql.append(" NOT NULL");
                if(isAI){
                    sql.append(" AUTO_INCREMENT PRIMARY KEY");
                }
                else if(isPK){
                    sql.append(" PRIMARY KEY");
                }
            }
            
            if(i < fieldNames.size()-1){
                sql.append(",");
            } 
        }
    
        sql.append(")");
        
        try{
            Statement st = con.createStatement();
            int result = st.executeUpdate(sql.toString());
            if(result == 0){
                createTableRow = 1;
                tab2Panel.removeAll();
                showTab2Panel();
                JOptionPane.showMessageDialog(tab2Panel, "Table created successfully.");
                tab2Panel.setVisible(false);
                tab2Panel.setVisible(true);
                editTabPanel();              
            }
            else{
                JOptionPane.showMessageDialog(tab2Panel, "Error while creating the table.");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tab2Panel, e.getMessage());
        }
    }
    
       
    
    //show table values panel
    private void showTab3Panel(){
        
        //if table values is called after changing the table.
        tab3Panel.removeAll();
    
        Color mGreen = new Color(51, 153, 0);
        
        //back button
        JButton backBtn = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("back_icon.png"));
            backBtn.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tab3Panel, ex.getMessage());
        }
        backBtn.setBackground(mGreen);
        backBtn.setBounds(10, 10, 30, 30);
        
        Font font = new Font("serif", Font.BOLD, 18);
        
        //database name label
        JLabel label1 = new JLabel("Database Name: " + db);
        label1.setForeground(mGreen);
        label1.setFont(font);
        label1.setBounds(50, 10, 300, 25);
        
        //table name label
        JLabel label2 = new JLabel("Table Name: " + tableName);
        label2.setForeground(mGreen);
        label2.setFont(font);
        label2.setBounds(350, 10, 300, 25);
        
        //text
        JLabel label3 = new JLabel("Double click the value to change ...");
        label3.setBounds(10, 50, 650, 25);
        
        //table
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        
        String[] rowData = null;
        
        try{
            String sql = "SELECT * FROM " + tableName;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            
            for(int i=0; i<colCount; i++){
                model.addColumn(rsmd.getColumnName(i+1));
            }
            
            model.addColumn("Edit");
            model.addColumn("Delete");
            
            rowData = new String[colCount + 2];
           
            while(rs.next()){
                for(int i=0; i<colCount; i++){                   
                    try{
                        rowData[i] = rs.getObject(i+1).toString();
                    }
                    catch(Exception ex){
                        rowData[i] = "";
                    }
                }
                rowData[colCount] = "Edit";
                rowData[colCount + 1] = "Delete";
                model.addRow(rowData);
            }            
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tab3Panel, e.getMessage());
        }
               
        table.setBackground(Color.WHITE);
        table.setRowHeight(30);
       
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBackground(Color.WHITE);
        scroll.setBounds(10, 90, 730, 250);
        
        //insert button
        JButton insertBtn = new JButton("Insert Values");
        insertBtn.setBounds(550, 350, 150, 30);
        
        //insert and edit subpanel
        JPanel subPanel = new JPanel();
        subPanel.setOpaque(true);
        subPanel.setBackground(Color.WHITE);
        
        JScrollPane subScroll = new JScrollPane(subPanel);
        subScroll.setOpaque(true);
        subScroll.setBackground(Color.WHITE);
        subScroll.setBounds(10, 390, 730, 100);
        
        //table structure button
        JButton structBtn = new JButton("TABLE STRUCTURE");
        structBtn.setFont(font);
        structBtn.setBackground(mGreen);
        structBtn.setForeground(Color.WHITE);
        structBtn.setBounds(25, 500, 220, 50);
        
        //table rename button
        JButton renameBtn = new JButton("RENAME TABLE");
        renameBtn.setFont(font);
        renameBtn.setBackground(mGreen);
        renameBtn.setForeground(Color.WHITE);
        renameBtn.setBounds(265, 500, 220, 50);
        
        //delete table button
        JButton delBtn = new JButton("DELETE TABLE");
        delBtn.setFont(font);
        delBtn.setBackground(mGreen);
        delBtn.setForeground(Color.WHITE);
        delBtn.setBounds(505, 500, 220, 50);
               
        //add components to table values panel
        tab3Panel.add(backBtn);
        tab3Panel.add(label1);
        tab3Panel.add(label2);
        tab3Panel.add(label3);
        tab3Panel.add(scroll);
        tab3Panel.add(insertBtn);
        tab3Panel.add(subScroll);
        tab3Panel.add(structBtn);
        tab3Panel.add(renameBtn);
        tab3Panel.add(delBtn);
        
        //action listeners
        backBtn.addActionListener((ActionEvent e) -> {
            tab3Panel.setVisible(false);
            tabPanel.setVisible(true);
        });
        insertBtn.addActionListener((ActionEvent e) -> {
            insertVal(table, subPanel);
        });
        structBtn.addActionListener((ActionEvent e) -> {
            showTab4Panel();
            tab3Panel.setVisible(false);
            tab4Panel.setVisible(true);
        });
        renameBtn.addActionListener((ActionEvent e) -> {
            String newName = JOptionPane.showInputDialog(tab3Panel, "Rename Table");
            if(newName != null && !newName.trim().equals("")){
                try{
                    String sql = "RENAME TABLE " + tableName + " TO " + newName;
                    Statement st = con.createStatement();
                    int result = st.executeUpdate(sql);
                    if(result == 0){
                        showTab3Panel();
                        label2.setText("Table Name: " + newName);
                        JOptionPane.showMessageDialog(tab3Panel, "Table name changed successfully.");
                        tableName = newName;
                        editTabPanel();
                    }
                    else{
                        JOptionPane.showMessageDialog(tab3Panel, "Error while renaming the table.");
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(tab3Panel, ex.getMessage());
                }
            }
        });
        delBtn.addActionListener((ActionEvent e)->{
            int op = JOptionPane.showConfirmDialog(tab3Panel, "Do you want to delete this table permanently?", "Delete Table", JOptionPane.YES_NO_OPTION);
            if(op == 0){
                try{
                    String sql = "DROP TABLE " + tableName;
                    Statement st = con.createStatement();
                    int result = st.executeUpdate(sql);
                    if(result == 0){
                        editTabPanel();
                        JOptionPane.showMessageDialog(tab3Panel, "Table deleted successfully.");
                        tab3Panel.setVisible(false);
                        tabPanel.setVisible(true);   
                    }
                    else{
                        JOptionPane.showMessageDialog(tab3Panel, "Error while deleting the table.");
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(tab3Panel, ex.getMessage());
                }
            }
        });
        table.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent e){
            
                JTable cell = (JTable)e.getSource();
                int row = cell.getSelectedRow();
                int col = cell.getSelectedColumn();
                
                String tableValue = table.getValueAt(row, col).toString().trim(); 
                
                if(tableValue.equals("Delete")){
                    delVal(table, row);
                } 
                else if(tableValue.equals("Edit")){
                    editVal(table, subPanel, row);
                }
            }
        });
        table.addFocusListener(new FocusAdapter(){
            String oldValue = null, newValue = null;          
            
            @Override
            public void focusGained(FocusEvent e) {
                JTable cell = (JTable)e.getSource();
                int row = cell.getSelectedRow();
                int col = cell.getSelectedColumn();               
                newValue = table.getValueAt(row, col).toString().trim();
                
                if(oldValue != null && newValue != null && !oldValue.equals(newValue)){
                    updateVal(table, row, col, newValue, oldValue);
                    oldValue = null;
                    newValue = null;
                }
            }
            @Override
            public void focusLost(FocusEvent e){
                JTable cell = (JTable)e.getSource();
                int row = cell.getSelectedRow();
                int col = cell.getSelectedColumn();          
                oldValue = table.getValueAt(row, col).toString().trim();
            }
        });
    }
    
     
    
    //delete table row
    private void delVal(JTable table, int row){
    
        int op = JOptionPane.showConfirmDialog(tab3Panel, "Do you want to delete this row from your table permanently?", "Delete Row", JOptionPane.YES_NO_OPTION);
        if(op == 0){
            int colCount = table.getColumnCount();
            StringBuffer sql = new StringBuffer("DELETE FROM " + tableName + " WHERE ");
            
            for(int i=0; i<colCount-2; i++){
                if(!table.getValueAt(row, i).toString().trim().equals("")){
                    sql.append(table.getColumnName(i) + "='" + table.getValueAt(row, i) + "'");
                    if(i != colCount-3){
                        sql.append(" AND ");
                    }
                }
            }
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate(sql.toString());
               
                if(result > 0){
                    JOptionPane.showMessageDialog(tab3Panel, "Row deleted successfully.");
                    showTab3Panel();
                    tab3Panel.setVisible(false);                   
                    tab3Panel.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(tab3Panel, "Error while deleting the row.");
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(tab3Panel, ex.getMessage());
            }
        }
    }
    
    
    
    //update cell value in the database
    private void updateVal(JTable table, int row, int col, String newValue, String oldValue){
    
        StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET " + table.getColumnName(col) + " = '" + newValue + "' WHERE ");
        
        int colCount = table.getColumnCount();
        
        for(int i=0; i<colCount-2; i++){
            
            if(i != col){
                sql.append(table.getColumnName(i) + "='" + table.getValueAt(row, i) + "'");
                
                if(i != colCount-3){
                    sql.append(" AND ");
                }
            }
        
        }
        
        try{
            Statement st = con.createStatement();
            int result = st.executeUpdate(sql.toString());
            
            if(result != 1){
                JOptionPane.showMessageDialog(tab3Panel, "Error while updating the data.");
                table.setValueAt(oldValue, row, col);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tab3Panel, e.getMessage());
            table.setValueAt(oldValue, row, col);
        }              
    }
    
    
    
    //insert row in the table values
    private void editVal(JTable table, JPanel subPanel, int row){
        
        subPanel.removeAll();
        
        int colCount = table.getColumnCount();
              
        for(int i=0; i<colCount-2; i++){
            JLabel label = new JLabel();
            label.setText(table.getColumnName(i));
            subPanel.add(label);
        }
        
        JLabel label1 = new JLabel("Edit");
        subPanel.add(label1);
        
        JLabel label2 = new JLabel("Cancel");
        subPanel.add(label2);
        
        ArrayList<Object> arr = new ArrayList<Object>();
        
        for(int i=0; i<colCount-2; i++){
            JTextField field = new JTextField();
            arr.add(field);
            field.setText(table.getValueAt(row, i).toString().trim());
            subPanel.add(field);
        }
        
        JButton editBtn = new JButton("Edit");
        subPanel.add(editBtn);
        
        JButton cancelBtn = new JButton("Cancel");
        subPanel.add(cancelBtn);
        
        subPanel.setLayout(new GridLayout(2, colCount, 10, 10));
        subPanel.setVisible(false);
        subPanel.setVisible(true);
        
        //action listeners
        cancelBtn.addActionListener((ActionEvent e)->{
            subPanel.removeAll();
            subPanel.setVisible(false);
            subPanel.setVisible(true);
        });
        editBtn.addActionListener((ActionEvent e)->{          
            StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
            
            for(int i=0; i<colCount-2; i++){
                JTextField field = (JTextField) arr.get(i);
                
                sql.append(table.getColumnName(i) + " = '" + field.getText().toString().trim() + "'");
                
                if(i != colCount-3){
                    sql.append(",");
                }
            }
            
            sql.append(" WHERE ");
            
            for(int i=0; i<colCount-2; i++){
                sql.append(table.getColumnName(i) + " = '" + table.getValueAt(row, i) + "'");
                
                if(i != colCount-3){
                    sql.append(" AND ");
                }
            }
            
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate(sql.toString());
                
                if(result == 1){
                    
                    //update tablevalues
                    for(int i=0; i<colCount-2; i++){
                        JTextField field = (JTextField)arr.get(i);
                        String value = field.getText();
                        table.setValueAt(value, row, i);
                    }
                    
                    JOptionPane.showMessageDialog(tab3Panel, "Data updated successfully.");
                    subPanel.removeAll();
                    subPanel.setVisible(false);
                    subPanel.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(tab3Panel, "Error while updating the data.");
                }             
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(tab3Panel, ex.getMessage());
            }
        });              
    }
    
       
    
    //insert new table values
    private void insertVal(JTable table, JPanel subPanel){
    
        subPanel.removeAll();
        
        int colCount = table.getColumnCount();
        
        for(int i=0; i<colCount-2; i++){
            JLabel label = new JLabel();
            label.setText(table.getColumnName(i));
            subPanel.add(label);
        }
        
        JLabel label1 = new JLabel("Insert");
        subPanel.add(label1);
        
        JLabel label2 = new JLabel("Cancel");
        subPanel.add(label2);
        
        ArrayList<Object> arr = new ArrayList<Object>();
        
        for(int i=0; i<colCount-2; i++){
            JTextField field = new JTextField();
            arr.add(field);
            subPanel.add(field);   
        }
        
        JButton insertBtn = new JButton("Insert");
        subPanel.add(insertBtn);
        
        JButton cancelBtn = new JButton("Cancel");
        subPanel.add(cancelBtn);
        
        subPanel.setLayout(new GridLayout(2, colCount, 10, 10));
        subPanel.setVisible(false);
        subPanel.setVisible(true);          
        
        //action listeners
        cancelBtn.addActionListener((ActionEvent e)->{
            subPanel.removeAll();
            subPanel.setVisible(false);
            subPanel.setVisible(true);
        });
        insertBtn.addActionListener((ActionEvent e)->{         
            StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " ( ");
            
            for(int i=0; i<colCount-2; i++){
                sql.append(table.getColumnName(i));
                
                if(i != colCount-3){
                    sql.append(",");
                }
            }
            
            sql.append(" ) VALUES ( ");
            
            for(int i=0; i<arr.size(); i++){
                JTextField field = (JTextField) arr.get(i);
                String value = field.getText().trim();
                
                sql.append("'" + value + "'");
                
                if(i != colCount-3){
                    sql.append(",");
                }
            }
            
            sql.append(")");
            
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate(sql.toString());
                
                if(result == 1){
                    JOptionPane.showMessageDialog(tab3Panel, "Data inserted successfully.");
                    showTab3Panel();
                    tab3Panel.setVisible(false);
                    tab3Panel.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(tab3Panel, "Error while inserting the data.");
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(tab3Panel, ex.getMessage());
            }
        });
    }
    


    //show table structure panel
    private void showTab4Panel(){
    
        //if panel is called after changing the table
        tab4Panel.removeAll();
        
        Color mGreen = new Color(51, 153, 0);
        
        //back button
        JButton backBtn = new JButton();
        try {
            Image img = ImageIO.read(getClass().getResource("back_icon.png"));
            backBtn.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(tab4Panel, ex.getMessage());
        }
        backBtn.setBackground(mGreen);
        backBtn.setBounds(10, 10, 30, 30);
        
        Font font = new Font("serif", Font.BOLD, 18);
        
        //database name label
        JLabel label1 = new JLabel("Database Name: " + db);
        label1.setFont(font);
        label1.setForeground(mGreen);
        label1.setBounds(50, 10, 300, 30);
        
        //table name label
        JLabel label2 = new JLabel("Table Name: " + tableName);
        label2.setFont(font);
        label2.setForeground(mGreen);
        label2.setBounds(400, 10, 300, 30);
        
        //table structure label
        JLabel label3 = new JLabel("Table Structure");
        label3.setFont(font);
        label3.setForeground(mGreen);
        label3.setBounds(50, 50, 700, 30);
        
        String[] colName = null;
        String[][] rowData = null;
        String[] options1 = null;
       
        try{
            String sql = "DESC " + tableName;
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int colCount = rsmd.getColumnCount();
            colName = new String[colCount + 2];
            
            for(int i=0; i<colCount; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            
            colName[colCount] = "Edit";
            colName[colCount+1] = "Delete";
            
            rs.last();
            int rowCount = rs.getRow();
            rs.first();
            
            rowData = new String[rowCount][colCount + 2];
            options1 = new String[rowCount + 1];
            
            if(rowCount == 1){
                for(int j=0; j<colCount; j++){
                    try{
                        String fieldName = rs.getObject(j+1).toString();                       
                        if(j == 0){
                            options1[0] = "Before " + fieldName;
                            options1[1] = "After " + fieldName;
                        }
                        rowData[0][j] = fieldName;
                    }
                    catch(Exception npe){
                        rowData[0][j] = "";
                    }                  
                }
            }
            for(int i=0; rs.next(); i++){
                if(i == 0){
                    rs.first();
                }
                for(int j=0; j<colCount; j++){
                    try{
                        String fieldName = rs.getObject(j+1).toString();
                        
                        if(j == 0 && i == 0){
                            options1[i] = "Before " + fieldName;
                            options1[i+1] = "After " + fieldName;
                        }
                        else if(j == 0 && i != 0){
                            options1[i+1] = "After " + fieldName;
                        }
                        
                        rowData[i][j] = fieldName;
                    }
                    catch(Exception npe){
                        rowData[i][j] = "";
                    }                   
                }
                rowData[i][colCount] = "Edit";
                rowData[i][colCount+1] = "Delete";
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(tab4Panel, e.getMessage());
        }
        
        //table
        JTable table = new JTable(rowData, colName);
        table.setRowHeight(30);
        table.setBackground(Color.WHITE);
        table.setFont(font);
        
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(10, 90, 730, 150);
        
        String[] options2 = {"INT", "TEXT", "VARCHAR", "DATETIME"};
        
        //add column subpanel
        JLabel label4 = new JLabel("ADD FIELD: ");
        label4.setFont(font);
        label4.setBounds(10, 260, 150, 30);
        
        JComboBox beforeAfter = new JComboBox(options1);
        beforeAfter.setSelectedIndex(options1.length -1);
        beforeAfter.setBounds(150, 260, 200, 30);
        
        JPanel subPanel1 = new JPanel();
        
        JLabel label5 = new JLabel("Field Name");
        JLabel label6 = new JLabel("Type");
        JLabel label7 = new JLabel("Length");
        JLabel label8 = new JLabel("Null");
        JLabel label9 = new JLabel("Auto Increment");
        JLabel label10 = new JLabel("Primary Key");
        JLabel label11 = new JLabel("Add");
        
        JTextField field = new JTextField();
        JComboBox type = new JComboBox(options2);
        JTextField length = new JTextField();
        JCheckBox nullCheck = new JCheckBox();
        JCheckBox aiCheck = new JCheckBox();
        JCheckBox pkCheck = new JCheckBox();
        JButton addBtn = new JButton("Add");
        
        subPanel1.add(label5);
        subPanel1.add(label6);
        subPanel1.add(label7);
        subPanel1.add(label8);
        subPanel1.add(label9);
        subPanel1.add(label10);
        subPanel1.add(label11);
        subPanel1.add(field);
        subPanel1.add(type);
        subPanel1.add(length);
        subPanel1.add(nullCheck);
        subPanel1.add(aiCheck);
        subPanel1.add(pkCheck);
        subPanel1.add(addBtn);
        
        subPanel1.setLayout(new GridLayout(2, 7, 10, 10));
        subPanel1.setBackground(Color.WHITE);
        JScrollPane scroll2 = new JScrollPane(subPanel1);
        scroll2.setBackground(Color.WHITE);
        scroll2.setBounds(10, 300, 730, 90);
               
        //edit column sub panel
        JPanel subPanel2 = new JPanel();
        subPanel2.setBackground(Color.WHITE);
        JScrollPane scroll3 = new JScrollPane(subPanel2);
        scroll3.setBackground(Color.WHITE);
        scroll3.setBounds(10, 400, 730, 90);
                     
        //table rename button
        JButton renameBtn = new JButton("RENAME TABLE");
        renameBtn.setFont(font);
        renameBtn.setBackground(mGreen);
        renameBtn.setForeground(Color.WHITE);
        renameBtn.setBounds(20, 500, 250, 50);
        
        //delete table button
        JButton delBtn = new JButton("DELETE TABLE");
        delBtn.setFont(font);
        delBtn.setBackground(mGreen);
        delBtn.setForeground(Color.WHITE);
        delBtn.setBounds(300, 500, 250, 50);
        
        //add components to table structure panel
        tab4Panel.add(backBtn);
        tab4Panel.add(label1);
        tab4Panel.add(label2);
        tab4Panel.add(label3);
        tab4Panel.add(scroll);
        tab4Panel.add(label4);
        tab4Panel.add(beforeAfter);
        tab4Panel.add(scroll2);
        tab4Panel.add(scroll3);
        tab4Panel.add(renameBtn);
        tab4Panel.add(delBtn);
        
        //action listeners
        backBtn.addActionListener((ActionEvent e)->{
            tab4Panel.setVisible(false);
            tab3Panel.setVisible(true);
        });
        renameBtn.addActionListener((ActionEvent e) -> {
            String newName = JOptionPane.showInputDialog(tab4Panel, "Rename Table");
            if(newName != null && !newName.trim().equals("")){
                try{
                    String sql = "RENAME TABLE " + tableName + " TO " + newName;
                    Statement st = con.createStatement();
                    int result = st.executeUpdate(sql);
                    if(result == 0){
                        label2.setText("Table Name: " + newName);
                        JOptionPane.showMessageDialog(tab4Panel, "Table name changed successfully.");
                        tableName = newName;
                        showTab3Panel();
                        editTabPanel();
                    }
                    else{
                        JOptionPane.showMessageDialog(tab4Panel, "Error while renaming the table.");
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(tab4Panel, ex.getMessage());
                }
            }
        });
        delBtn.addActionListener((ActionEvent e)->{
            int op = JOptionPane.showConfirmDialog(tab4Panel, "Do You want to delete this table permanently?", "Delete Table", JOptionPane.YES_NO_OPTION);
            if(op == 0){
                try{
                    String sql = "DROP TABLE " + tableName;
                    Statement st = con.createStatement();
                    int result = st.executeUpdate(sql);
                    if(result == 0){
                        editTabPanel();
                        JOptionPane.showMessageDialog(tab4Panel, "Table deleted successfully.");
                        tab4Panel.setVisible(false);
                        tabPanel.setVisible(true);   
                    }
                    else{
                        JOptionPane.showMessageDialog(tab4Panel, "Error while deleting the table.");
                    }
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(tab4Panel, ex.getMessage());
                }
            }
        });
        type.addActionListener((ActionEvent e)->{
            String op = type.getSelectedItem().toString().trim();
            
            //if type is INT, enable length and ai
            if(op.equals("INT")){
                length.setEnabled(true);
                aiCheck.setEnabled(true);
            }
            
            //if type is TEXT or VARCHAR, disable ai and enable length, null
            else if(op.equals("TEXT") || op.equals("VARCHAR")){
                aiCheck.setSelected(false);
                aiCheck.setEnabled(false);
                length.setEnabled(true);
                nullCheck.setEnabled(true);
            }
            
            //if type is DATETIME, diable length and ai, and enable null
            else if(op.equals("DATETIME")){
                length.setText("");
                length.setEnabled(false);
                aiCheck.setSelected(false);
                aiCheck.setEnabled(false);
                nullCheck.setEnabled(true);
            }
        });        
        nullCheck.addItemListener((ItemEvent e)->{
            //if null box is checked, disable AI and PK boxes
            if(e.getStateChange() == 1){
                aiCheck.setSelected(false);
                pkCheck.setSelected(false);
                aiCheck.setEnabled(false);
                pkCheck.setEnabled(false);
            }
            else{
                aiCheck.setEnabled(true);
                pkCheck.setEnabled(true);
            }
        });
        aiCheck.addItemListener((ItemEvent e)->{
            //if AI box is checked, disable NULL and auto check PK box
            if(e.getStateChange() == 1){
                nullCheck.setSelected(false);
                pkCheck.setSelected(true);
                nullCheck.setEnabled(false);
                pkCheck.setEnabled(false);
            }
            else{
                nullCheck.setEnabled(true);
                pkCheck.setEnabled(true);
            }
        });
        pkCheck.addItemListener((ItemEvent e)->{
            //if PK box is checked, disable NULL 
            if(e.getStateChange() == 1){
                nullCheck.setSelected(false);
                nullCheck.setEnabled(false);              
            }
            else{
                nullCheck.setEnabled(true);               
            }
        });
        addBtn.addActionListener((ActionEvent e)->{                     
            String fieldName = field.getText().trim();
            String len = length.getText().trim();
            boolean isNull = false, isAI = false, isPK = false;
            int l = 0;
            String op = type.getSelectedItem().toString().trim();
            
            //check if field name is empty
            if(fieldName == null || fieldName.equals("")){
                JOptionPane.showMessageDialog(tab4Panel, "Enter FIELD NAME");
                return;
            }
            
            //check if length is invalid
            if(!len.equals("")){
                try{ 
                    l = Integer.parseInt(len); 
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(tab4Panel, "Invalid LENGTH");
                    return;
                }
            }
            
            //check if length is not given for VARCHAR
            if(op.equals("VARCHAR") && len.equals("")){
                JOptionPane.showMessageDialog(tab4Panel, "LENGTH is not given for VARCHAR.");
                return;
            }
            
            StringBuilder sql = new StringBuilder("ALTER TABLE " + tableName + " ADD COLUMN " + fieldName + " " + op);
            
            if(!len.equals("")){
                sql.append("(" + l + ")");
            }
            
            if(nullCheck.isSelected()){
                sql.append(" NULL ");
            }
            else{
                sql.append(" NOT NULL ");
                
                if(aiCheck.isSelected()){
                    sql.append("AUTO_INCREMENT PRIMARY KEY ");
                }
                else if(pkCheck.isSelected()){
                    sql.append("PRIMARY KEY ");
                }
            }
            
            int index = beforeAfter.getSelectedIndex();
            
            if(index == 0){
                sql.append("FIRST");
            }
            else{
                sql.append(beforeAfter.getSelectedItem().toString());
            }
           
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate(sql.toString());
                
                if(result == 0){
                    showTab4Panel();
                    JOptionPane.showMessageDialog(tab4Panel, "Field added successfully.");
                    tab4Panel.setVisible(false);
                    tab4Panel.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(tab4Panel, "Error while adding the field.");
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(tab4Panel, ex.getMessage());
            }                     
        });
        table.addMouseListener(new MouseAdapter(){       
            public void mousePressed(MouseEvent e){
            
                JTable cell = (JTable)e.getSource();
                int row = cell.getSelectedRow();
                int col = cell.getSelectedColumn();
                
                String value = table.getValueAt(row, col).toString().trim();               
                              
                if(value.equals("Edit")){
                    editCol(table, subPanel2, row);
                }
                if(value.equals("Delete")){
                    int op = JOptionPane.showConfirmDialog(tab4Panel, "Do you want to delete this Column from the table?", "Delete Column", JOptionPane.YES_NO_OPTION);
                    if(op == 0){
                        String sql = "ALTER TABLE " + tableName + " DROP COLUMN " + table.getValueAt(row, 0);
                        
                        try{
                            Statement st = con.createStatement();
                            int result = st.executeUpdate(sql);
                            
                            if(result == 0){
                                showTab4Panel();
                                JOptionPane.showMessageDialog(tab4Panel, "Column deleted successfully.");
                                tab4Panel.setVisible(false);
                                tab4Panel.setVisible(true);
                                showTab3Panel();
                            }
                            else{
                                JOptionPane.showMessageDialog(tab4Panel, "Error while deleting the column.");
                            }
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(tab4Panel, ex.getMessage());
                        }
                    }
                }
            }
        });     
    }
    
    
    
    //to edit a column of table
    private void editCol(JTable table, JPanel panel, int row){
    
        panel.removeAll();
        
        String[] op = {"INT", "TEXT", "VARCHAR", "DATETIME"};
        
        JLabel label1 = new JLabel("Field Name");
        JLabel label2 = new JLabel("Type");
        JLabel label3 = new JLabel("Length");
        JLabel label4 = new JLabel("Null");
        JLabel label5 = new JLabel("Auto Increment");
        JLabel label6 = new JLabel("Primary Key");
        JLabel label7 = new JLabel("Edit");
        JLabel label8 = new JLabel("Cancel");
        
        JTextField newField = new JTextField();                
        JComboBox newType = new JComboBox(op);
        JTextField newLen = new JTextField();
        JCheckBox nCheck = new JCheckBox();
        JCheckBox aCheck = new JCheckBox();
        JCheckBox pCheck = new JCheckBox();
        JButton editBtn = new JButton("Edit");
        JButton cancelBtn = new JButton("Cancel");
        
        newField.setText(table.getValueAt(row, 0).toString());
        String val = table.getValueAt(row, 1).toString().trim().substring(0, 3).toLowerCase();
        
        switch(val){
            case "int":
                newType.setSelectedIndex(0);
                break;              
            case "tex":
                newType.setSelectedIndex(1);
                aCheck.setSelected(false);
                aCheck.setEnabled(false);
                break;
            case "var":
                newType.setSelectedIndex(2);
                aCheck.setSelected(false);
                aCheck.setEnabled(false);
                break;              
            case "dat":
                newType.setSelectedIndex(3);
                aCheck.setSelected(false);
                aCheck.setEnabled(false);
                newLen.setText("");
                newLen.setEnabled(false);
                break;
            default:
                newType.setSelectedIndex(0);             
        }
              
        String isNull = table.getValueAt(row, 2).toString().trim().toLowerCase();       
        if(isNull.equals("yes")){
            nCheck.setSelected(true);
            aCheck.setSelected(false);
            aCheck.setEnabled(false);
        }
        
        String isPK = table.getValueAt(row, 3).toString().trim().toLowerCase();
        if(isPK.equals("pri")){
            pCheck.setSelected(true);
            nCheck.setSelected(false);
            nCheck.setEnabled(false);
        }
        
        String isAI = table.getValueAt(row, 5).toString().trim();
        if(!isAI.equals("")){
            aCheck.setSelected(true);
            pCheck.setSelected(true);
            pCheck.setEnabled(false);
            nCheck.setSelected(false);
            nCheck.setEnabled(false);
        }
        
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        
        panel.add(newField);
        panel.add(newType);
        panel.add(newLen);
        panel.add(nCheck);
        panel.add(aCheck);
        panel.add(pCheck);
        panel.add(editBtn);
        panel.add(cancelBtn);
        
        panel.setLayout(new GridLayout(2, 8, 10, 10));
        panel.setVisible(false);
        panel.setVisible(true);
        
        //action listeners
        newType.addActionListener((ActionEvent e)->{           
            String v = newType.getSelectedItem().toString().trim();
            
            if(v.equals("INT")){
                newLen.setEnabled(true);
                aCheck.setEnabled(true);
            }
            else if(v.equals("TEXT") || v.equals("VARCHAR")){
                aCheck.setSelected(false);
                aCheck.setEnabled(true);
                nCheck.setEnabled(true);
                pCheck.setEnabled(true);
                newLen.setEnabled(true);
            }
            else if(v.equals("DATETIME")){
                newLen.setText("");
                newLen.setEnabled(false);
                aCheck.setSelected(false);
                aCheck.setEnabled(false);
            }                              
        });
        nCheck.addItemListener((ItemEvent e)->{
            //if PK box is checked, disable NULL 
            if(e.getStateChange() == 1){
                aCheck.setSelected(false);
                aCheck.setEnabled(false);
                pCheck.setSelected(false);
                pCheck.setEnabled(false); 
            }
            else{
                aCheck.setEnabled(true); 
                pCheck.setEnabled(true); 
            }
        });
        aCheck.addItemListener((ItemEvent e)->{
            //if PK box is checked, disable NULL 
            if(e.getStateChange() == 1){
                pCheck.setSelected(true);
                pCheck.setEnabled(false);
                nCheck.setSelected(false);
                nCheck.setEnabled(false); 
            }
            else{
                nCheck.setEnabled(true); 
                pCheck.setEnabled(true); 
            }
        });
        pCheck.addItemListener((ItemEvent e)->{
            //if PK box is checked, disable NULL 
            if(e.getStateChange() == 1){
                nCheck.setSelected(false);
                nCheck.setEnabled(false);
            }
            else{
                nCheck.setEnabled(true); 
            }
        });
        cancelBtn.addActionListener((ActionEvent e)->{
            panel.removeAll();
            panel.setVisible(true);
            panel.setVisible(false);
        });
        editBtn.addActionListener((ActionEvent e)->{
            
            String oldCol = table.getValueAt(row, 0).toString().trim();
            String newCol = newField.getText().trim();
            String type = newType.getSelectedItem().toString().trim();
            String len = newLen.getText().trim();
            boolean isN = nCheck.isSelected();
            boolean isA = aCheck.isSelected();
            boolean isP = pCheck.isSelected();
            int l = 0;
            
            //check if field is empty
            if(newCol.equals("")){
                JOptionPane.showMessageDialog(tab4Panel, "Enter FIELD NAME");
                return;
            }
            
            //check is length is invalid
            if(!len.equals("")){
                try{
                    l = Integer.parseInt(len);
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(tab4Panel, "Invalid LENGTH");
                    return;
                }
            }
            
            //check if length is given for Varchar
            if(len.equals("") && type.equals("VARCHAR")){
                JOptionPane.showMessageDialog(tab4Panel, "LENGTH is not given for VARCHAR");
                return;
            }
            
            StringBuilder sql = new StringBuilder("ALTER TABLE " + tableName + " CHANGE " + oldCol + " " + newCol + " " + type);
            
            if(!len.equals("")){
                sql.append("(" + len + ")");
            }
            if(isN){
                sql.append(" NULL ");
            }
            else{
                sql.append(" NOT NULL ");
                
                if(isA){
                    sql.append("AUTO_INCREMENT PRIMARY KEY");
                }
                else if(isP){
                    sql.append("PRIMARY KEY");
                }
            }
            
            try{
                Statement st = con.createStatement();
                int result = st.executeUpdate(sql.toString());
               
                if(result > -1){
                    showTab4Panel();
                    JOptionPane.showMessageDialog(tab4Panel, "Column updated successfully.");
                    tab4Panel.setVisible(false);
                    tab4Panel.setVisible(true); 
                    showTab3Panel();
                }
                else{
                   JOptionPane.showMessageDialog(tab4Panel, "Error while updating the column."); 
                }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(tab4Panel, ex.getMessage());
            }          
        });      
    }
     
    
    
    //hide panels component when user disconnects from the database
    private void hidePanel(){
        
        dbPanel.removeAll();
        tabPanel.removeAll();
        tab2Panel.removeAll();
        tab3Panel.removeAll();
        tab4Panel.removeAll();
        
    }
    
    
   
    //main mathod
    public static void main(String[] args) {
        new Project1_db();
    }

}