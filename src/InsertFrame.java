import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;


public class InsertFrame extends JPanel{

    JPanel row1, row2, row3, row4, row5, row6;
    static JTextField textfield_diff, textfield_gd, textfield_gsc, textfield_usefull, textfield_name, textfield_fin, textfield_dungeon;
    JLabel label1, label2, label3, label4, label5, label6, label7;
    JLabel info;
    JButton insertButton, deleteButton;
    
    JComboBox<String> dungeon_cb;

    Connection con;
    User user;
    private Database database;
    Timer timer;

    private final int WIDTH = 300;
    private final int HEIGTH = 620;
    private final int LABEL_LEFT_DIST = 40, TEXTF_LEFT_DIST = 150;
    private final static Color PENDING = new Color(150, 150, 150);
    private final static Color SUCCESS = Color.green.darker();
    private final static Color FAIL = Color.red;
    private final static String DEFAULT_STATUS = "Waiting...";

    public InsertFrame(Connection con, User user, Database database) throws SQLException {
        setBounds(10, Constants.MAINFRAME_PADDING_Y, WIDTH, HEIGTH);
        setBackground(Constants.DEFAULT_COLOR);
        setLayout(null);
        ////////////////////
        this.con = con;
        this.user = user;
        this.database = database;

        label1 = new JLabel("Difficulty: "); label1.setBounds(LABEL_LEFT_DIST, 30, 125, 25);
        label2 = new JLabel("Geardrop: "); label2.setBounds(LABEL_LEFT_DIST, 70, 125, 25);
        label3 = new JLabel("Gearscore: "); label3.setBounds(LABEL_LEFT_DIST, 110, 125, 25);
        label4 = new JLabel("Usefull: "); label4.setBounds(LABEL_LEFT_DIST, 150, 125, 25);
        label5 = new JLabel("Name: "); label5.setBounds(LABEL_LEFT_DIST, 190, 125, 25);
        label6 = new JLabel("Finished in: "); label6.setBounds(LABEL_LEFT_DIST, 230, 125, 25);
        label7 = new JLabel("Dungeon: "); label7.setBounds(LABEL_LEFT_DIST, 270, 125, 25);
        
        textfield_diff = new JTextField(); textfield_diff.setBounds(TEXTF_LEFT_DIST, 30, 125, 25);
        textfield_gd = new JTextField(); textfield_gd.setBounds(TEXTF_LEFT_DIST, 70, 125, 25);
        textfield_gsc = new JTextField(); textfield_gsc.setBounds(TEXTF_LEFT_DIST, 110, 125, 25);
        textfield_usefull = new JTextField(); textfield_usefull.setBounds(TEXTF_LEFT_DIST, 150, 125, 25);
        textfield_name = new JTextField(); textfield_name.setBounds(TEXTF_LEFT_DIST, 190, 125, 25);
        textfield_fin = new JTextField(); textfield_fin.setBounds(TEXTF_LEFT_DIST, 230, 125, 25);
        dungeon_cb = new JComboBox<String>(Constants.DRAGONFLIGHT_DUNGEONS_S1); dungeon_cb.setBounds(TEXTF_LEFT_DIST, 270, 125, 25);

        info = new JLabel(DEFAULT_STATUS);
        info.setBounds(10, 595, 125, 25);
        info.setForeground(PENDING);
        //Declare button action
        insertButton = new InsertButton(new AbstractAction("Insert run") { 
            @Override
            public void actionPerformed(ActionEvent e) {
                    insert();
            }
        }); 
        insertButton.setBounds(LABEL_LEFT_DIST+50, 320, 125, 30);

        deleteButton = new RemoveButton(new AbstractAction("Remove last") { 
            @Override
            public void actionPerformed(ActionEvent e) {
                    delete();
            }
        }); 
        deleteButton.setBounds(LABEL_LEFT_DIST+50, 365, 125, 30); 

        //Adding components to the frame
        add(label1); add(textfield_diff);

        add(label2); add(textfield_gd);

        add(label3); add(textfield_gsc);

        add(label4); add(textfield_usefull);

        add(label5); add(textfield_name);

        add(label6); add(textfield_fin);

        add(label7); add(dungeon_cb);

        add(info);
        add(insertButton); add(deleteButton);

        ///////////////////////
        setVisible(true);
    }

    public void insert() {
        Boolean wasSuccessfull = true;
        
        try {
            Functions.insert(con, database, textfield_diff.getText(), Functions.returnDate(), textfield_gd.getText(), textfield_gsc.getText(), textfield_usefull.getText(), textfield_name.getText(), textfield_fin.getText(), Constants.DRAGONFLIGHT_DUNGEONS_S1[dungeon_cb.getSelectedIndex()]);
        } catch (SQLException e1) {
            e1.printStackTrace();
            wasSuccessfull = false;
        }
        
        if( wasSuccessfull ) {
            info.setForeground(SUCCESS);
            info.setText("Insertion successfull.");
        } else {
            info.setForeground(FAIL);
            info.setText("Insertion failed.");
        }
         // Sets up a delayed timer which executes the code after 2000ms
        Timer timer = new Timer(2000, event -> {
            info.setText(DEFAULT_STATUS);
            info.setForeground(PENDING);
        });
        timer.setRepeats(false);
        timer.start();
        resetTextfields();
    }

    private void delete() {
        Boolean wasSuccessfull = true;
        
        try {
            Functions.removeLast(con, database);
        } catch (SQLException e1) {
            e1.printStackTrace();
            wasSuccessfull = false;
        }
        
        if( wasSuccessfull ) {
            info.setForeground(SUCCESS);
            info.setText("Deletion successfull.");
        } else {
            info.setForeground(FAIL);
            info.setText("Deletion failed.");
        }
         // Sets up a delayed timer which executes the code after 2000ms
        Timer timer = new Timer(2000, event -> {
            info.setText(DEFAULT_STATUS);
            info.setForeground(PENDING);
        });
        timer.setRepeats(false);
        timer.start();
        
    }

    private static void resetTextfields() {
        textfield_diff.setText("");
        textfield_gd.setText(""); textfield_gsc.setText(""); textfield_usefull.setText(""); textfield_fin.setText("");
        textfield_name.setText("");
    }

    
}
