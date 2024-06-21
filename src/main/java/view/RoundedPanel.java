package view;

import javax.swing.*;
import java.awt.*;

public class RoundedPanel extends JPanel {

    private int radius;

    public RoundedPanel(int radius) {
        super();
        this.radius = radius;
        setOpaque(false); // Garante que o fundo do JPanel seja transparente
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension arcs = new Dimension(radius, radius);
        int width = getWidth();
        int height = getHeight();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Desenha a borda arredondada usando um gradiente de cor
        graphics.setColor(getBackground());
        graphics.fillRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);
        graphics.setColor(getForeground());
        graphics.drawRoundRect(0, 0, width - 1, height - 1, arcs.width, arcs.height);

        // Chama o método para desenhar os componentes filhos
        super.paintChildren(g);
    }

    @Override
    public void setBackground(Color bg) {
        super.setBackground(bg);
    }
}

