package com.tests.utils.recorder;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JComponent;

public class ImagePanel extends JComponent {
	private static final long serialVersionUID = 1L;
	private BufferedImage bufferedImage;
	
	public ImagePanel(BufferedImage bufferedImage) {
		super();
		this.bufferedImage = bufferedImage;
		setPreferredSize(new Dimension(bufferedImage.getWidth(), bufferedImage.getHeight()));
	}
	public void setBufferedImage(BufferedImage bufferedImage) {
		this.bufferedImage = bufferedImage;
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    g.drawImage(bufferedImage, 0, 0, null);
	}
}
