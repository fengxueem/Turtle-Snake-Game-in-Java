package jing.fu;

import java.awt.*;

public class Led {
	private int x,y;
	public static final int WIDTH= Integer.parseInt(PropertyManager.getProperty("ledWidth"));
	public static final int HEIGHT=WIDTH;
	private Brightness b;

	public Led(int x ,int y) {
		this.x=x;
		this.y=y;

	}
	public void setX(int x) {
		this.x=x;
	}

	public void setY(int y) {
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void draw(Graphics g) {//draw the outline of a led 
		int[] temp=MapCoordinates.get(this);//get screen coordinates from MapCoordiantes class
		Color c=g.getColor();
		//g.setColor(Color.black);		
		//g.fillOval(temp[0], temp[1], WIDTH, HEIGHT);
		g.setColor(Color.orange);
		g.drawOval(temp[0], temp[1], WIDTH, HEIGHT);
		g.setColor(c);
	}

	public void draw(Graphics g, Brightness b) {//draw a led on graphcis g based on brightness b
		int[] temp=MapCoordinates.get(this);//get screen coordinates from MapCoordiantes class
		Color c=g.getColor();
		switch (b) {//set the color for this led based on its brightness
			case H:g.setColor(new Color(255,18,18)); break;
			case M:g.setColor(new Color(255,58,58));break;
			case L:g.setColor(new Color(255,107,107));break;
		}
		g.fillOval(temp[0], temp[1], WIDTH, HEIGHT);
		g.setColor(c);
	}
}