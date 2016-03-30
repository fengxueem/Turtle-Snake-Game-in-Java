package jing.fu;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import java.io.IOException;
import java.util.Random;

import javax.xml.bind.annotation.XmlElementDecl.GLOBAL;

public class GameLaunch extends Frame  {
	private Image offScreenImage = null;
	public static final int GAME_WIDTH=450;// set window size
	public static final int GAME_HEIGHT=450;
	public  static final int rows=Integer.parseInt(PropertyManager.getProperty("rows"));
	public  static final int columns=Integer.parseInt(PropertyManager.getProperty("columns"));
	// Snake snake= new Snake(Integer.parseInt(PropertyManager.getProperty("initX")),Integer.parseInt(PropertyManager.getProperty("initY")),this);
	// private static Random r= new Random();
	// Fruit fruit = new Fruit(r.nextInt(GameLaunch.GAME_WIDTH),r.nextInt(GameLaunch.GAME_HEIGHT),this);
	List<Led> leds= new ArrayList<Led>();
	Snake mySnake = new Snake(this);
	Fruit fruit = new Fruit(this);

	public void paint(Graphics g){
		Color c=g.getColor();
		g.setColor(Color.pink);
		g.drawString("Led size:"+leds.size(),10,50);
		g.drawString("get fruit:"+mySnake.getNumOfFruits(),10,70);
		// g.drawString("Length:"+mySnake.getLength(),10,70);
		// g.drawString("Speed:"+(1001-(int)mySnake.getSnakeSpeed()),10,70);
		int[] px={ 222,387,387,222,57,57};//190
		int[] py={ 26,121,311,406,311,121};
		g.fillPolygon(px,py,6);
		g.setColor(Color.black);
		// px=new int[]{222,383,383,222,61,61};//186
		// py=new int[]{30,123,309,402,309,123};
		// px=new int[]{222,369,369,222,75,75};//170
		// py=new int[]{46,131,301,386,301,131};
		px=new int[]{222,378,378,222,66,66};//180
		py=new int[]{36,126,306,396,306,126};		
		g.fillPolygon(px,py,6);
		g.setColor(c);
		// fruit.draw(g);
		// mySnake.draw(g);
		for (int i=0;i<leds.size() ;i++ ) {
			leds.get(i).draw(g);
		}
		mySnake.snakeGetFruit(fruit);
		mySnake.draw(g);
		fruit.draw(g);
	}

	public void update(Graphics g) {
		if (offScreenImage== null) {// double buffer
		 	offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
		 } 
		 Graphics gOffScreen = offScreenImage.getGraphics();
		 Color c=gOffScreen.getColor();
		 gOffScreen.setColor(Color.BLACK);
		 gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
		 gOffScreen.setColor(c);
		 paint(gOffScreen);
		 g.drawImage(offScreenImage,0,0,this);
	}

	public void launchFrame()  {
		int num=0;
		for (int i=1;i<=rows ;i++ ) {
			for (int j=1; j<=columns ; j++) {
				if (Math.abs(i-j)>=((columns+1)/2)) {
					continue;//do not creat led object with coordinates difference larger or equal than 5
				}
				int[] temp= {(86+(j-1)*31),(132-(j-1)*18+(i-1)*36)};
				leds.add(new Led(i,j));
				MapCoordinates.put(leds.get(num),temp);
				num++;
			}
		}
		this.setLocation(100,100);
		this.setSize(GAME_WIDTH,GAME_HEIGHT);
		this.setTitle("Turtle Snake");
		this.addWindowListener(new WindowAdapter (){//anonymous class
			public void windowClosing( WindowEvent e) {
				System.exit(0);
			}
		});
		this.setResizable(false);
		this.setBackground(Color.BLACK);
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);
		new Thread(new PaintThread()).start();// anonymous class
	}

	public static void main(String[] args) {
		GameLaunch gl= new GameLaunch();
		gl.launchFrame();
	}

	private class PaintThread implements Runnable {//inner class
		public void run() {
			while (true){
			repaint();
			try {
				Thread.sleep(Integer.parseInt(PropertyManager.getProperty("freshSpeed")));//time units milliseconds, period of refreshing the screen
			}
			catch (Exception err) {
				System.err.println(err.getMessage());
			}	
		}
		}
	}

	private class KeyMonitor extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			 mySnake.keyPressed(e);
		}
		public void keyReleased(KeyEvent e) {
			 mySnake.keyReleased(e);
			 int key = e.getKeyCode();
			 switch (key) {
			 	case KeyEvent.VK_1 : mySnake= new Snake(GameLaunch.this);break;
			 }
		}
	}
}