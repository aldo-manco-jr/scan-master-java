import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * Created by unieuro on 2/28/2020.
 */

public class RectangleButton extends Rectangle {
    
    private Color color;
    
    private String label;
    private int labelX;
    private int labelY;

    public RectangleButton(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public boolean isClicked(MouseEvent e) {
        return this.contains(e.getPoint());
    }

    public void draw(Graphics g) {
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
        g.setColor(Color.black);
        g.drawRect(this.x, this.y, this.width, this.height);

        if (this.label != null) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString(this.label, this.labelX, this.labelY);

            //int labelWidth = g.getFontMetrics().stringWidth(label);
            //g.drawString(label, (this.width / 2) - (labelWidth / 2), this.height / 2+220);
        }

    }

    public void setLabel(String label, int labelX, int labelY) {
        this.label = label;
        this.labelX = labelX;
        this.labelY = labelY;
    }
}