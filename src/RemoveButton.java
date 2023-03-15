import javax.swing.Action;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.*;

public class RemoveButton extends JButton implements ActionListener {

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 30;
    private String buttonName;

    private final Color DEFAULT_COLOR = new Color(170, 170, 170);

    private final Color COLOR_OVER = new Color(230, 230, 230);
    private final Color COLOR_CLICK = new Color(250, 250, 250);
    private final Color COLOR_BORDER = new Color(0, 0, 0);
    
    public RemoveButton(Action a, String buttonName) {
        this(a);
        this.buttonName = buttonName;
        setText(buttonName);
    }

    public RemoveButton(Action a) {
        super(a);
        setColor(DEFAULT_COLOR);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        // Add mouse event
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(COLOR_OVER);
                over = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(DEFAULT_COLOR);
                over = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setBackground(COLOR_CLICK); 
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(over) {
                    setBackground(COLOR_OVER);
                } else {
                    setBackground(DEFAULT_COLOR);
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Paint border
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        // Border set 2 Pix
        g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, radius, radius);
        super.paintComponent(g);
    }

    /**
     * @return boolean return the over
     */
    public boolean isOver() {
        return over;
    }

    /**
     * @param over the over to set
     */
    public void setOver(boolean over) {
        this.over = over;
    }

    /**
     * @return Color return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    /**
     * @return Color return the colorOver
     */
    public Color getColorOver() {
        return colorOver;
    }

    /**
     * @param colorOver the colorOver to set
     */
    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    /**
     * @return Color return the colorClick
     */
    public Color getColorClick() {
        return colorClick;
    }

    /**
     * @param colorClick the colorClick to set
     */
    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    /**
     * @return Color return the borderColor
     */
    public Color getBorderColor() {
        return borderColor;
    }

    /**
     * @param borderColor the borderColor to set
     */
    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return int return the radius
     */
    public int getRadius() {
        return radius;
    }

    /**
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //InsertFrame.insert();
    }

}