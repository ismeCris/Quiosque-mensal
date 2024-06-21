package view;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;

public class TransparentTextField extends JTextField {

	  private static final long serialVersionUID = 1L;
	    private float transparency;

	    public TransparentTextField(float transparency) {
	        super();
	        this.transparency = transparency;
	        setOpaque(false); // Torna o JPanel não opaco para aplicar a transparência
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g.create();
	        g2d.setComposite(AlphaComposite.SrcOver.derive(transparency));
	        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	        g2d.setColor(getBackground());
	        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Bordas arredondadas
	        g2d.dispose();
	        super.paintComponent(g);
	    }
	}