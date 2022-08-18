package com.gcstudios.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.Player;
import com.gcstudios.graficos.Spritesheet;
import com.gcstudios.graficos.UI;
import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class World {

	public static Tile[] tiles;
	public static int WIDTH,HEIGHT;
	public static final int TILE_SIZE = 16;
	
	private Spritesheet background_tras;
	private BufferedImage BACKGROUND_TRAS;
	
	public World(String path){
		background_tras = new Spritesheet("/background.png");
		BACKGROUND_TRAS = background_tras.getSprite(0, 0, (int) background_tras.getRealWidth(), (int) background_tras.getRealHeight());
		
	}
	
	public static boolean isFree(int xnext,int ynext){
		
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1*World.WIDTH)] instanceof WallTile) ||
				(tiles[x2 + (y2*World.WIDTH)] instanceof WallTile) ||
				(tiles[x3 + (y3*World.WIDTH)] instanceof WallTile) ||
				(tiles[x4 + (y4*World.WIDTH)] instanceof WallTile));
	}
	

	
	public static void restartGame() {

		Game.entities = new ArrayList<Entity>();
		Game.spritesheet = new Spritesheet("/spritesheet.png");
		Game.player = new Player(Game.WIDTH/2,Game.HEIGHT-16,16,16,1,Game.spritesheet.getSprite(0, 0, 16, 16));
		Game.entities.add(Game.player);
		Game.score=0;
		Player.life=3;
		UI.frames=0;
		UI.minutes=0;
		UI.seconds=0;
		Game.backY=-960;
		return;
	}
	
	public void render(Graphics g){
		
		g.drawImage(BACKGROUND_TRAS, 0, Game.backY, null);
		
	}
	
}
