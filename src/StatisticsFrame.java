import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatisticsFrame extends JPanel {
    JLabel playtime, drops, ratio;
    JLabel[] dungeonStats;

    Connection con;
    static User user;
    private Database database;

    public StatisticsFrame(Connection con, User user, Database database) throws SQLException {
        setBounds(320, Constants.MAINFRAME_PADDING_Y, 855, 310);
        setBackground(Constants.DEFAULT_COLOR);
        setLayout(null);
        ///////////////////////////////////////
        this.con = con;
        this.user = user;
        this.database = database;

        playtime = new JLabel("Total playtime: " + Functions.playtime(con, database));
        playtime.setBounds(10, 10, 250, 25);
        drops = new JLabel("Drops: " + Functions.countDropped(con, database) + " . No drops: " + Functions.countUndropped(con, database) + ".");
        drops.setBounds(10, 30, 250, 25);
        ratio = new JLabel("Ratio: " + Functions.dropRatio(Functions.countDropped(con, database), Functions.countUndropped(con, database), Functions.total(con, database)));
        ratio.setBounds(10, 50, 250, 25);

        dungeonStats = new JLabel[Constants.DRAGONFLIGHT_DUNGEONS_S1.length];
        int[] stats = dungeonStats(con, database);
        int y = 140;

        for(int i = 0; i < Constants.DRAGONFLIGHT_DUNGEONS_S1.length; i++) {
            dungeonStats[i] = new JLabel(Constants.DRAGONFLIGHT_DUNGEONS_S1[i] + ": "+ stats[i]);
            dungeonStats[i].setBounds(10, y, 400, 25);
            dungeonStats[i].setFont(new Font("Aerial", Font.ITALIC, 13));
            dungeonStats[i].setForeground(new Color(100, 100, 100));
            y += 20;
        }

        add(dungeonStats[0]); add(dungeonStats[1]); add(dungeonStats[2]); add(dungeonStats[3]); add(dungeonStats[4]); add(dungeonStats[5]); add(dungeonStats[6]); add(dungeonStats[7]);
        add(playtime);
        add(drops);
        add(ratio);
        ///////////////////////
        setVisible(true);

        dungeonStats(con, database);
    }

    public static int[] dungeonStats(Connection con, Database db) throws SQLException {
        int[] stats = new int[Constants.DRAGONFLIGHT_DUNGEONS_S1.length];
        Statement stmnt = con.createStatement();
        int dbIndex = 1;

        for(int i = 0; i < stats.length; i++) {
            ResultSet rs = stmnt.executeQuery(" SELECT dungeon FROM dungeonruns");


            while(rs.next()) {

                if(rs.getString("dungeon") != null && rs.getString("dungeon").equals(Constants.DRAGONFLIGHT_DUNGEONS_S1[i])) {
                    stats[i]++;
                }
            }
            System.out.println(stats[i]);
        }
        return stats;

    }
}
