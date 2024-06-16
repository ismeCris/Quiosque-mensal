package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;

public class RoundButton extends JButton {

    private static final long serialVersionUID = 1L;
    private int radius;

    public RoundButton(String label, int radius) {
        super(label);
        this.radius = radius;
        setOpaque(false);
        setBorderPainted(false);  // Remove the border
        setFocusPainted(false);   // Remove the focus border
        setContentAreaFilled(false); // Remove the content area fill
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.setColor(getForeground());
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), radius, radius);

        g2.dispose();
        super.paintComponent(g);
    }
}