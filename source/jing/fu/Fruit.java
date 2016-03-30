package jing.fu;

import java.awt.Graphics;
import java.util.Random;
import java.lang.Math;

public class Fruit {
	private int x,y;//the position info of the fruit
	private boolean live = true;// live or dead.

	private static Random r = new Random();
	private Brightness bn = Brightness.H;
	public GameLaunch gl;
	

	public Fruit (int x, int y) {
		this.x = x;
		this.y = y;
		initializeFruit();
	}//constructor of fruit. given x&y can new a fruit.

	public Fruit (GameLaunch gl) {
		this.gl = gl;
		initializeFruit();
	}

	public void draw(Graphics g) {

		if (!live) {
             initializeFruit();
			this.live = true;
		}//if the fruit has been eaten, find a new location and draw a fruit.

		for(int i = 0; i < gl.leds.size();i++){
			if(gl.leds.get(i).getX() == x && gl.leds.get(i).getY() == y){
				gl.leds.get(i).draw(g,bn);
			}
		}

	}


	public boolean hitSnake(Snake s) {

		for (int i = 0; i < 3; i++){
			if(this.x == s.x.get(i) && this.y == s.y.get(i)) {	
				return true;
			}
		} return false;
	}
	
	public void initializeFruit() {
		do {
			this.x = r.nextInt(8)+1;
			this.y = r.nextInt(8)+1;
		}while(Math.abs(this.x-this.y)>=5 || hitSnake(gl.mySnake));
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean getLive() {
		return live;
	}


	public void setLive(boolean live) {
		this.live = live;
	}


}
