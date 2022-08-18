package com.gcstudios.main;

import com.gcstudios.entities.Entity;
import com.gcstudios.entities.LifePack;

public class LifePackSpawn {
	public int LifePackTargetTime = 60*30;
	public int LifePackTime = 0;
	
	public void tick() {
		LifePackTime++;
		
		if(LifePackTime == LifePackTargetTime) {
			LifePackTime = 0;
			
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			int yy = 0;
			LifePack lifepack = new LifePack(xx,yy,16,16,Entity.rand.nextInt(1)+1,Game.spritesheet.getSprite(80, 0, 16, 16));
			Game.entities.add(lifepack);
			
		}
		
	}
}
