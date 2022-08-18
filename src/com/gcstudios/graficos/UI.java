package com.gcstudios.graficos;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.gcstudios.entities.Player;
import com.gcstudios.main.Game;
import com.gcstudios.main.Menu;

public class UI {
	
	
	public static int seconds = 0;
	public static int minutes = 0;
	public static int frames = 0;
	
	public void tick() {
		frames++;
		if(frames == 60) {
			//Passou 1 segundo.
			frames = 0;
			seconds++;
			if(seconds == 60) {
				seconds = 0;
				minutes++;
				if(UI.minutes % 2 == 0) {
					
				}
			}
		}
				
	}

	public void render(Graphics g) {
		
		
			if(Player.life >= 1) {
				g.drawImage(Game.spritesheet.getSprite(48, 0, 16, 16), 10 + (0 * 45), 10, 40,40,null);
				
				if(Player.life >=2) {
					g.drawImage(Game.spritesheet.getSprite(48, 0, 16, 16), 10 + (1 * 45), 10, 40,40,null);
					
					if(Player.life>=3) {
						
						g.drawImage(Game.spritesheet.getSprite(48, 0, 16, 16), 10 + (2 * 45), 10, 40,40,null);
						
						if(Player.life == 4) {
							
							g.drawImage(Game.spritesheet.getSprite(112, 0, 16, 16), 10 + (3 * 45), 10, 40,40,null);
							
						}
						
					}
					
				}
				
			}
			
			if(Player.life > 4) {
				
				Player.life = 4;
				
			}
			
				
		String formatTime = "";
		if(minutes < 10) {
			formatTime+="0"+minutes+":";
		}else {
			formatTime+=minutes+":";
		}
		
		if(seconds < 10) {
			formatTime+="0"+seconds;
		}else {
			formatTime+=seconds;
		}
		
		g.drawImage(Game.spritesheet.getSprite(96, 0, 16, 16), 200, 25,32,32,null);
		
		g.setColor(Color.white);
		g.setFont(new Font("arial",Font.BOLD,23));
		g.drawString(formatTime, 235, 48);
		
		if(Menu.language == "BR") {
			
			g.setColor(Color.white);
			g.setFont(new Font("Arial",Font.BOLD,23));
			g.drawString("Pontuação: " + Game.score, 200, 24);
			
		}else if(Menu.language == "EUA"){
			
			g.setColor(Color.white);
			g.setFont(new Font("Arial",Font.BOLD,23));
			g.drawString("Score: " + Game.score, 200, 24);
			
		}
	}
	
}
