package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;

public class Explosion extends Entity{

	private int frames =0,targetFrame=4,maxAnimation =3,curAnimation=0;
	
	public BufferedImage[] explosionsprites= new BufferedImage[4];
	
	public Explosion(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		explosionsprites[0] = Game.spritesheet.getSprite(64, 16, 16, 16);
		explosionsprites[1] = Game.spritesheet.getSprite(80, 16, 16, 16);
		explosionsprites[2] = Game.spritesheet.getSprite(96, 16, 16, 16);
		explosionsprites[3] = Game.spritesheet.getSprite(112, 16, 16, 16);
	}
	
	public void tick() {
		frames++;
		if(frames == targetFrame) {
			frames = 0;
			curAnimation++;
			if(curAnimation > maxAnimation) {
				
				Game.entities.remove(this);
				Game.gameState = "GAME_OVER";
				return;
				
			}
			
		}
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(explosionsprites[curAnimation], this.getX(), this.getY(), null);
		
	}
	
}
