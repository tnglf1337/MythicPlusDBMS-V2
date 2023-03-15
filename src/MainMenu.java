import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JMenuBar implements ActionListener {
    JMenu helpMenu, infoMenu, quickMenu;
    JMenuItem use, about, setDefault, setDefaultNoDrop, setLogin;

    public MainMenu() {
        quickMenu = new JMenu("Quick");
        setDefault = new JMenuItem("Set failed run");
        setDefaultNoDrop = new JMenuItem("Set nodrop");
        setLogin = new JMenuItem("Set login information");
        helpMenu = new JMenu("Help");
        use = new JMenuItem("How to use");
        infoMenu = new JMenu("Info");
        about = new JMenuItem("Information");

        quickMenu.add(setDefault);
        quickMenu.add(setDefaultNoDrop);
        quickMenu.add(setLogin);
        helpMenu.add(use);
        infoMenu.add(about);

        setDefault.addActionListener(this);
        setDefaultNoDrop.addActionListener(this);
        setLogin.addActionListener(this);
        about.addActionListener(this);

        add(quickMenu);
        add(helpMenu);
        add(infoMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == setDefault) {
            InsertFrame.textfield_diff.setText("+");
            InsertFrame.textfield_gd.setText("F"); InsertFrame.textfield_gsc.setText("null"); InsertFrame.textfield_usefull.setText("null"); InsertFrame.textfield_fin.setText("null");
            InsertFrame.textfield_name.setText("Vyvari");
        } else if(e.getSource() == setDefaultNoDrop) {
            InsertFrame.textfield_diff.setText("+");
            InsertFrame.textfield_gd.setText("N"); InsertFrame.textfield_gsc.setText("null"); InsertFrame.textfield_usefull.setText("null");
            InsertFrame.textfield_name.setText("Vyvari");
        } else if(e.getSource() == setLogin) {
            //LoginFrame.input_username.setText(InsertFrame.user.getName());
            //LoginFrame.input_password.setText(InsertFrame.user.getPassword());
        } else if(e.getSource() == about) {
            JFrame infoFrame = new JFrame("Information");
            infoFrame.setSize(300, 300);
            infoFrame.setLocationRelativeTo(null);
            infoFrame.setLayout(null);
            infoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            JLabel text = new JLabel();
            String asText = String.format("Lorem ipsum dolor sit amet, consetetur.");
            text.setText(asText);
            text.setBounds(5, -145, 300, 300);

            infoFrame.add(text);
            infoFrame.setVisible(true);
        }
    }
}
