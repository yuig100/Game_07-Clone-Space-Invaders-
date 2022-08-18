package com.gcstudios.main;

import com.gcstudios.entities.Enemy;
import com.gcstudios.entities.Entity;
import com.gcstudios.graficos.UI;

public class EnemySpawn {
	
	public int TargetTime1 = 60;
	public int TargetTime2 = 120;
	public int TargetTime3 = 180;
	public int TargetTime4 = 240;
	public int TargetTime5 = 300;
	public int TargetTime6 = 360;
	
	public int curTime = 0;
	public int curTime2 = 0; 
	public int curTime3 = 0; 
	public int curTime4 = 0; 
	public int curTime5 = 0; 
	public int curTime6 = 0; 
	
	public double velrand1 = 1;
	public double velrand2 = 2;
	public double velrand3 = 3;
	public double velrand4 = 4;
	public double velrand5 = 5;
	public double velrand6 = 6;
	
	
	public void tick() {
		
		curTime++;
		if(UI.minutes >= 2) {
			curTime2++;
			if(UI.minutes >= 4) {
				
				curTime3++;
				if(UI.minutes >= 6) {
					curTime4++;
					if(UI.minutes >= 8) {
						curTime5++;
						if(UI.minutes >= 10) {
							curTime6++;
						}
					}
				}
			}
		}
		
		if(curTime == TargetTime1) {
			curTime = 0;
			
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			int yy = 0;
			Enemy enemy1 = new Enemy(xx,yy,16,16,Entity.rand.nextDouble() + velrand1,Game.spritesheet.getSprite(32, 16, 16, 16));
			Game.entities.add(enemy1);
			
			if(UI.seconds % 5 == 0) {
				
				velrand1 += 0.15;
				TargetTime1 -=1;
				
			} else if(UI.minutes % 2 == 0) {
				
				velrand1 = 1;
				TargetTime1=60;
				
			}
			
		} 
		
		if(UI.minutes >= 2 && curTime2 == TargetTime2) {
			curTime2 = 0;
			
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			int yy = 0;
			Enemy enemy2 = new Enemy(xx,yy,16,16,Entity.rand.nextDouble() + velrand2,Game.spritesheet.getSprite(32, 32, 16, 16));
			Game.entities.add(enemy2);
			
			if(UI.seconds % 10 == 0) {
				
				velrand2 += 0.2;
				TargetTime2 -=2;
				
			} else if(UI.minutes % 2 == 0) {
				
				velrand2 = 2;
				TargetTime2=120;
				
			}
			
		}
		
		if(UI.minutes >= 4 && curTime3 == TargetTime3) {
			curTime3 =0;
			
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			int yy = 0;
			Enemy enemy3 = new Enemy(xx,yy,16,16,Entity.rand.nextDouble()+velrand3,Game.spritesheet.getSprite(16, 16, 16, 16));
			Game.entities.add(enemy3);
			
			if(UI.seconds % 15 == 0) {
				
				velrand3 += 0.3;
				TargetTime3 -=3;
				
			} else if(UI.minutes % 2 == 0) {
				
				velrand3 = 3;
				TargetTime3=180;
				
			}
			
		}
		
		if(UI.minutes >= 6 && curTime4 == TargetTime4) {
			curTime4 = 0;
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			int yy = 0;
				Enemy enemy4 = new Enemy(xx,yy,16,16,Entity.rand.nextDouble()+velrand4,Game.spritesheet.getSprite(16, 32, 16, 16));
				Game.entities.add(enemy4);
				
				if(UI.seconds % 20 == 0) {
					
					velrand4 += 0.5;
					TargetTime4 -=5;
					
				} else if(UI.minutes % 2 == 0) {
					
					velrand4 = 4;
					TargetTime4=240;
					
				}
				
			}
			
		if(UI.minutes >= 8 && curTime5 == TargetTime5) {
			curTime5 = 0;
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			int yy = 0;
				Enemy enemy5 = new Enemy(xx,yy,16,16,Entity.rand.nextDouble()+velrand5,Game.spritesheet.getSprite(16, 48, 16, 16));
				Game.entities.add(enemy5);
				
				if(UI.seconds % 25 == 0) {
					
					velrand5 += 0.8;
					TargetTime4 -=8;
					
				} else if(UI.minutes % 2 == 0) {
					
					velrand5 = 5;
					TargetTime5=300;
					
				}
			}
			
		if(UI.minutes >= 10 && curTime6 == TargetTime6) {
			curTime6 = 0;
			int xx = Entity.rand.nextInt(Game.WIDTH - 16);
			int yy = 0;
				Enemy enemy6 = new Enemy(xx,yy,16,16,Entity.rand.nextDouble()+velrand6,Game.spritesheet.getSprite(16, 64, 16, 16));
				Game.entities.add(enemy6);
				
				if(UI.seconds % 30 == 0) {
					
					velrand5 += 1;
					TargetTime4 -=10;
					
				} else if(UI.minutes % 2 == 0) {
					
					velrand5 = 6;
					TargetTime5=360;
					
				}
				
			}
			
	}
	
}
