package com.tests.utils;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ScreenState {
	private int[] stateImage;
	private Rectangle rectangle;
	public ScreenState(int stateImageId, int x, int y) {
		super();
		try {
			BufferedImage tmpBufferedImage = ImageIO.read(new File("resources/image"+stateImageId+".png"));
			this.rectangle = new Rectangle(x, y, tmpBufferedImage.getWidth(), tmpBufferedImage.getHeight());
			byte[] tmpByteArray = ((DataBufferByte) tmpBufferedImage.getRaster().getDataBuffer()).getData();
			this.stateImage = new int[tmpByteArray.length/3];
		    for (int i = 0; i < tmpByteArray.length; i+=3) {
		    	byte b1 = tmpByteArray[i], b2 = tmpByteArray[i+1],b3 = tmpByteArray[i+2];
		    	this.stateImage[i/3] = (((b3 & 0xFF) << 16) | ((b2 & 0xFF) << 8) | ((b1 & 0xFF)));
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isInState(Robot robot) {
		int[] currentState = ((DataBufferInt) robot.createScreenCapture(rectangle).getRaster().getDataBuffer()).getData();
		for (int i = 0; i < currentState.length; i++) {
			if ((currentState[i] & 0xFFFFFF) != this.stateImage[i]){
				return false;
			}
		}
		return true;
	}
}
