package com.mojang.ld22;

import com.mojang.ld22.screen.MenuJAMS;
import com.mojang.ld22.gfx.ScreenJAMS;
import com.mojang.ld22.gfx.SpriteSheetJAMS;

import com.mojang.ld22.level.LevelJAMS;
import com.mojang.ld22.entity.PlayerJAMS;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.Random;


/**
 *
 * @author James Dinsdale, Student ID: 18019702
 */
public class GameJAMS extends Canvas implements Runnable {
	//1st buncha fields
	private static final long serialVersionUID = 1L;
	private Random random = new Random();
	public static final String NAME = "Minicraft";
	public static final int HEIGHT = 120;
	public static final int WIDTH = 160;
	private static final int SCALE = 3;
	
	//2nd buncha fields
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private boolean running = false;
	private ScreenJAMS screen; //PAUSE
	private ScreenJAMS lightScreen; //PAUSE
	private InputHandlerJAMS input = new InputHandlerJAMS(this); //PAUSE

	//3rd buncha fields
	private int[] colors = new int[256];
	private int tickCount = 0;
	public int gameTime = 0;

	//4th buncha fields
	private LevelJAMS level;
	private LevelJAMS[] levels = new LevelJAMS[5];
	private int currentLevel = 3;
	public PlayerJAMS player;
	
	//5th ones
	public MenuJAMS menu;
	private int playerDeadTime;
	private int pendingLevelChange;
	private int wonTimer = 0;
	public boolean hasWon = false;
	
	
	public void setMenu(MenuJAMS menu)
	{
		this.menu = menu;
		if(menu != null) menu.init(this, input);
	}
	public void start()
	{
		running = true;
		new Thread(this).start();
	}
	public void stop()
	{
		running = false;
	}
	public void resetGame()
	{
		
	}
	private void init(){
		int pp = 0;
		for(int r=0;r<6;r++) {
			for(int g=0;g<6;g++) {
				for (int b=0;b<6;b++) {
					int rr=(r*255/5);
					int gg=(g*255/5);
					int bb=(b*255/5);
					int mid = (rr*30+gg*59+bb*11)/100;
					
					int r1=((rr+mid*1) / 2) * 230 / 255 + 10;
					int g1=((gg+mid*1) / 2) * 230 / 255 + 10;
					int b1=((bb+mid*1) / 2) * 230 / 255 + 10;
					
					colors[pp++] = r1 << 16 | g1 << 8 | b1;
				}
			}
		}
		try {
			screen = new ScreenJAMS(WIDTH, HEIGHT, new SpriteSheetJAMS(ImageIO.read(GameJAMS.class.getResourceAsStream("/icons.png"))));
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		resetGame();
		setMenu(new TitleMenuJAMS());
	}
	public void run()
	{
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60;
		int frames = 0;
		int ticks = 0;
		long lastTimer1 = System.currentTimeMillis();
		
		init();
		
		while(running) {
			long now = System.nanoTime();
			unprocessed += (now-lastTime)/nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while(unprocessed >= 1) {
				ticks++;
				tick();
				unprocessed -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			
			if(shouldRender) {
				frames++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}
		}
	}
	public void tick()
	{
		
	}
	public void changeLevel(int dir)
	{
		
	}
	public void render()
	{
		
	}
	private void renderGui()
	{
		
	}
	private void renderFocusNagger()
	{
		
	}
	public void scheduleLevelChange(int dir)
	{
		
	}
	public static void main(String[] args)
	{
		GameJAMS game = new GameJAMS();
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		JFrame frame = new JFrame(GameJAMS.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		game.start();
	}
	public void won()
	{
		
	}
}
