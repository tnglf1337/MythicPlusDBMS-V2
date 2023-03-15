import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class LoginFrame extends JFrame implements ActionListener {
    private User user;
    public MainFrame main;
    private static final int WIDTH = 300, HEIGTH = 200;

    public boolean loginStatus = false;
    private int loginTries = 3;

    private JLabel username, password, login_status;
    static JTextField input_username;
    static JTextField input_password;
    private JButton login_btn;

    public LoginFrame(User u, MainFrame m) {
        this.user = u;
        this.main = m;

        setTitle("Login");
        setIconImage(new ImageIcon("res/main_icon.png").getImage());
        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        username = new JLabel("Username: ");
        username.setBounds(50, 20, 100, 20);
        password = new JLabel("Passwort: ");
        password.setBounds(50, 50, 100, 20);
        login_btn = new JButton("login");
        login_btn.setBounds(95, 90, 100, 20);

        input_username = new JTextField();
        input_username.setBounds(130, 20, 100, 20);
        input_password = new JTextField();
        input_password.setBounds(130, 50, 100, 20);
        login_status = new JLabel();
        login_status.setBounds(95, 110, 150, 20);
        login_btn.addActionListener(this);

        add(username);
        add(input_username);
        add(password);
        add(input_password);
        add(login_status);
        add(login_btn);
        ///////////////////
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(user.getName().equals(input_username.getText()) && user.getPassword().equals(input_password.getText())) {
            // Why doesnt this display...
            login_status.setText("Welcome");
            loginStatus = true;
            try {
                Thread.sleep(900);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            main.setVisible(true);
            dispose();
        } else {
            loginTries--;
            shake();
        }

        switch(loginTries) {
            case 2:
                login_status.setText(loginTries + " weiterere Versuche.");
                break;
            case 1:
                login_status.setText(loginTries + " weiterere Versuche.");
                break;
            case 0:
                dispose();
            default:
                System.err.print("error");
        }
        
    }
    //Rework this smoother
    public void shake() {
        final int origX = getX();
        final int origY = getY();
        final int SHAKE = 4;
        final int DUR = 10;

        try {
            setLocation(origX+SHAKE, origY-SHAKE);
            Thread.sleep(DUR);
            setLocation(origX-SHAKE, origY+SHAKE);
            Thread.sleep(DUR);
            setLocation(origX+SHAKE, origY+SHAKE);
            Thread.sleep(DUR);
            setLocation(origX-SHAKE, origY-SHAKE);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}