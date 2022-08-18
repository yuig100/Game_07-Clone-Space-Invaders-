package com.gcstudios.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;
import com.gcstudios.main.Sound;
import com.gcstudios.world.Camera;
import com.gcstudios.world.World;

public class Player extends Entity {

	public boolean right,left;
	
	public static int life=3;
	
	public boolean isShooting= false;
	
	public static boolean isDamaged = false,isInvunerable = false;
	private int damageframes = 0;
	
	public Player(int x, int y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		

		
	}

	public void tick() {
		
		if(right) {
			
			x+=speed;
			
		} else if(left) {
			
			x-=speed;
			
		}
		
		if(x <= 0) {
			
			x=0;
			
		}
		if(x >= 104) {
			
			x = 104;
			
		}
		
		if(isShooting) {
			
			isShooting = false;
			int xx=this.getX() + 5;
			int yy =this.getY();
			
			Bullet bullet = new Bullet(xx,yy,3,3,4,null);
			Game.entities.add(bullet);
		}
		
		if (isDamaged) {

			damageframes++;
			if (damageframes == 12) {

				damageframes = 0;
				isDamaged = false;
				isInvunerable = false;
			}
		}
		
		if(life <=0) {
			life = 0;
			Sound.m2.play();
			Explosion explosion = new Explosion(x,y,16,16,0,null);
			Game.entities.add(explosion);
			Game.entities.remove(this);
			
		}
		//Camera.x = Camera.clamp((int)x - Game.WIDTH/2, 0, World.WIDTH * 16 - Game.WIDTH);
		//Camera.y = Camera.clamp((int)y - Game.HEIGHT/2, 0, World.HEIGHT * 16 - Game.HEIGHT);
	}
	
	public void render(Graphics g){
		
		
		if(!isDamaged) {
			
			if(right) {
				
				sprite = Game.spritesheet.getSprite(16, 0, 16, 16);
				
				if(life == 4) {
					
					sprite = Game.spritesheet.getSprite(81, 32, 16, 16);
					
				}
				
			} else if(left) {
				
				sprite = Game.spritesheet.getSprite(32, 0, 16, 16);
				
				if(life == 4) {
					
					sprite = Game.spritesheet.getSprite(96, 32, 16, 16);
					
				}
			} else {
				
				sprite = Game.spritesheet.getSprite(0, 0, 16, 16);
				
				if(life == 4) {
					
					sprite = Game.spritesheet.getSprite(64, 32, 17, 16);
					
				}
				
			}
			
			
		} else {
	
			sprite = Game.spritesheet.getSprite(64, 0, 16, 16);
			
		}
		
		super.render(g);
		
	}

}
