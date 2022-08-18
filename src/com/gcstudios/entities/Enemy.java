package com.gcstudios.entities;

import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class Enemy extends Entity{
	
	
	public int life = 3;
	
	public Enemy(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed,sprite);
	}
	
	public void tick() {
		
		y+=speed;
		
		if(y >= Game.HEIGHT) {
			
			Game.score++;
			Game.entities.remove(this);
			
		}
		
		for(int i = 0;i < Game.entities.size();i++) {
			
			Entity e = Game.entities.get(i);
			if(e instanceof Bullet) {
				
				if(Entity.isColidding(this, e)) {
					Game.entities.remove(e);
					life--;
					if(life == 0) {
					Game.score++;
					Game.entities.remove(this);
					break;
					}
					
					
				}
				
			}
			
			if(e instanceof Player) {
				
				if(Entity.isColidding(this, e)){
					
					if(Player.isInvunerable == false) {
						
						Player.life--;
						Player.isInvunerable = true;
						Player.isDamaged = true;
					}
					
					Game.entities.remove(this);
					break;
					
				}
				
			}
			
		}
		
	}

}
