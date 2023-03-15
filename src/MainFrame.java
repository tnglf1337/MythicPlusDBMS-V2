import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.*;

public class MainFrame extends JFrame {
    JPanel insertFrame, statisticsFrame;
    static JPanel databaseFrame;
    JMenuBar menuBar;

        User user;
        Database wow;
        Connection con;
    
    public MainFrame() throws SQLException {
        setTitle("MYTHIC+ DBMS " + Constants.VERSION);
        setSize(Constants.MAINFRAME_WIDTH, Constants.MAINFRAME_HEIGTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setIconImage(new ImageIcon("res/main_icon.png").getImage());
        ////////////////////////
        user = new User("timo", "timo1993");
        wow = new Database("wowruns", "dungeonruns");
        con = Functions.createConnection(wow.DRIVERPATH, wow.URL, user.getName(), user.getPassword());

        menuBar = new MainMenu();
        insertFrame = new InsertFrame(con, user, wow);
        statisticsFrame = new StatisticsFrame(con, user, wow);
        databaseFrame = new DatabaseFrame(con, wow, Functions.getColumnNames(con, wow), Functions.getData(con, wow));

        add(insertFrame);
        add(statisticsFrame);
        add(databaseFrame);
        ///////////////////
        setJMenuBar(menuBar);
        setVisible(false);
    }
}
