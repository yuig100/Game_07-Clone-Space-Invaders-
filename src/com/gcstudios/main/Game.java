package com.gcstudios.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.graficos.UI;
import com.gcstudios.world.World;

public class Game extends Canvas implements Runnable,KeyListener,MouseListener,MouseMotionListener{

	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 120;
	public static final int HEIGHT = 160;
	public static final int SCALE = 4;
	
	private BufferedImage image;
	private boolean restartGame = false;
	public static World world;
	public static List<Entity> entities;
	public static Spritesheet spritesheet;
	public static Player player;
	public EnemySpawn enemyspawn;
	public LifePackSpawn lifepackspawn;
	public boolean gamerun = false;
	public BufferedImage GAME_BACKGROUND;
	public static int backY=-960;
	public int backSpeed=1;
	public int countback =0;

	//
	public static String gameState = "MENU";
	private boolean showMessageGameOver = true;
	private boolean showMessageGameWin = true;
	private int framesGameOver = 0;
	private int framesGameWin = 0;
	public Menu menu;
	//
	public boolean saveGame = false;
	
	public UI ui;
	public static int score =0;
	
	public Game(){
		
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		initFrame();
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
		
		//Inicializando objetos.
		spritesheet = new Spritesheet("/spritesheet.png");
		entities = new ArrayList<Entity>();
		player = new Player(Game.WIDTH/2,Game.HEIGHT-16,16,16,1.2,spritesheet.getSprite(0, 0, 16, 16));
		world = new World("/background.png");
		ui = new UI();
		enemyspawn = new EnemySpawn();
		
		lifepackspawn = new LifePackSpawn();
		//backgrounds
		/*
		try {
			GAME_BACKGROUND = ImageIO.read(getClass().getResource("/background.png"));

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		//backgrounds
		
		
		entities.add(player);
		menu = new Menu();
		
	}
	
	public void initFrame(){
		frame = new JFrame("Space Collision");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start(){
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop(){
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		Game game = new Game();
		game.start();
	}
	
	public void tick(){
		
		if(UI.minutes == 12) {
			
			gameState = "WIN";
			
		}
		
		if(gameState == "NORMAL") {
			gamerun = true;
			countback++;
			
			if(countback == 45) {
				countback = 0;
				backY +=1;
				
			}
			
			ui.tick();			
			enemyspawn.tick();
			lifepackspawn.tick();
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.tick();
			}
			
		} else if(gameState == "GAME_OVER") {
			
			this.framesGameOver++;
			if (this.framesGameOver == 30) {

				this.framesGameOver = 0;
				if (this.showMessageGameOver) {

					this.showMessageGameOver = false;

				} else {

					this.showMessageGameOver = true;

				}
			}
			
			
			if (restartGame) {

				this.restartGame = false;
				gameState = "NORMAL";
				World.restartGame();

			}

		}else if(gameState == "MENU") {
			
			menu.tick();
			
		} else if(gameState == "WIN") {
			
			this.framesGameWin++;
			if (this.framesGameWin == 30) {

				this.framesGameWin = 0;
				if (this.showMessageGameWin) {

					this.showMessageGameWin = false;

				} else {

					this.showMessageGameWin = true;

				}
			}
			
			
		}

	}
	


	
	public void render(){
	
		
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		//render back
		//g.drawImage(GAME_BACKGROUND, 0,0,null);
		world.render(g);
		//

		Collections.sort(entities,Entity.nodeSorter);
		for(int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}
		
		/***/
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
		ui.render(g);
		
		if (gameState == "GAME_OVER") {

			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(250, 0, 0, 120));
			g2.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
			g.setFont(new Font("arial", Font.BOLD, 40));
			g.setColor(Color.white);
			
			
			if(Menu.language == "EUA") {
				
				g.drawString("Game Over", ((WIDTH * SCALE) / 2) - 110, ((HEIGHT * SCALE) / 2));
				g.setFont(new Font("arial", Font.BOLD, 32));
				
				if (showMessageGameOver) {

					g.drawString("Press Z to restart the game", ((WIDTH * SCALE) / 2) - 210,((HEIGHT * SCALE) / 2) + 60);

				}
				
			} else if(Menu.language == "BR") {
				
				g.drawString("Fim de Jogo", ((WIDTH * SCALE) / 2) - 120, ((HEIGHT * SCALE) / 2));
				g.setFont(new Font("arial", Font.BOLD, 32));
				
				if (showMessageGameOver) {

					g.drawString("Pressione Z", ((WIDTH * SCALE) / 2) - 100,((HEIGHT * SCALE) / 2) + 60);
					g.drawString("Para reiniciar o jogo", ((WIDTH * SCALE) / 2) - 160,((HEIGHT * SCALE) / 2) + 100);

				}
				
			}
			
		} else if (gameState == "MENU") {
			
			menu.render(g);

		} else if(gameState == "WIN") {
			
			if(Menu.language == "EUA") {
				
				
				g.drawString("Press Z to exit the game", ((WIDTH * SCALE) / 2) - 130,((HEIGHT * SCALE) / 2) + 10);
				
				if(showMessageGameWin) {
					
					g.setColor(Color.YELLOW);
					g.setFont(new Font("arial", Font.BOLD, 40));			
					g.drawString("YOU WIN", ((WIDTH * SCALE) / 2) - 90, ((HEIGHT * SCALE) / 2) - 100);
					
				}
				
				
				
			}else if(Menu.language == "BR") {
				
				g.drawString("Pressione Z", ((WIDTH * SCALE) / 2) - 70,((HEIGHT * SCALE) / 2) + 10);
				g.drawString("Para fechar o jogo", ((WIDTH * SCALE) / 2) - 100,((HEIGHT * SCALE) / 2) + 50);
				
				if(showMessageGameWin) {
					
					g.setColor(Color.YELLOW);
					g.setFont(new Font("arial", Font.BOLD, 40));			
					g.drawString("Você Venceu", ((WIDTH * SCALE) / 2) - 120, ((HEIGHT * SCALE) / 2) - 100);
					
				}
				
			}
			
		}

		bs.show();
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				System.out.println("FPS: "+ frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			player.right = true;
			
		} else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			player.left = true;
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			
			//player.isShooting = true;
			if (gameState == "MENU") {

				menu.enter = true;

			}
			
			if (gameState == "GAME_OVER") {

				this.restartGame = true;

			}
			
			if (gameState == "WIN") {

				System.exit(1);

			}

		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			
			if (gameState == "MENU") {

				menu.up = true;

			}

		}else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
			
			if (gameState == "MENU") {

				menu.down = true;

			}

		}
		
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {

			if (gameState == "NORMAL") {

				gameState = "MENU";

				Menu.setPause(true);

			}

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			player.right = false;
			
		} else if(e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			player.left = false;
			
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z) {
			
			player.isShooting = false;
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	
	}

	
}
