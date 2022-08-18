package com.gcstudios.entities;

import java.awt.image.BufferedImage;

import com.gcstudios.main.Game;

public class LifePack extends Entity{

	public LifePack(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		
		y+=speed;
		
		
		for(int i = 0;i < Game.entities.size();i++) {
			
			Entity e = Game.entities.get(i);
		
			if(e instanceof Player) {
				
				if(Entity.isColidding(this, e)){
					
					Player.life++;
					Game.entities.remove(this);
					break;
					
				}
				
			}
			
		}
		
	}
	
}
