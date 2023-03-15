import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;

public class DatabaseFrame extends JPanel {
    User user = new User("timo", "timo1993");
    Database wow = new Database("wowruns", "dungeonruns");
    Connection con = Functions.createConnection(wow.DRIVERPATH, wow.URL, user.getName(), user.getPassword());

    static JTable databaseTable;
    
    private String[][] data;
    private String[] dbColumns;

    public DatabaseFrame(Connection con, Database db, String[] dbColumns, String[][] data) throws SQLException {
        setBounds(320, 330, 855, 300);
        setBackground(Constants.DEFAULT_COLOR);
        //////////////////////////////////////
        this.dbColumns = dbColumns;
        this.data = data;

        databaseTable = new JTable(data, dbColumns);
        databaseTable.setPreferredScrollableViewportSize(new Dimension(825, 265));
        databaseTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(databaseTable);
        
        add(scrollPane);
        setVisible(true);
    }
}
