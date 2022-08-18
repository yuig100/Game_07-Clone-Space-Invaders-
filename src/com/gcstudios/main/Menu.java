package com.gcstudios.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.gcstudios.world.World;

public class Menu {

	public String[] options = { "novo jogo", "opcoes", "sair","language" };

	public int currentOption = 0, maxOption = options.length - 1;

	public boolean up, down, enter;

	private static boolean pause = false;

	public static boolean saveExists = false;

	public static boolean saveGame = false;
	
	public BufferedImage MENU;
	public BufferedImage Image_Language;
	
	public static String language = "EUA";
	
	public void tick() {

		File file = new File("save.sp");

		if (file.exists()) {

			saveExists = true;

		} else {

			saveExists = false;

		}

		if (up) {

			up = false;
			currentOption--;
			if (currentOption < 0) {

				currentOption = maxOption;

			}

		}

		if (down) {

			down = false;

			currentOption++;
			if (currentOption > maxOption) {

				currentOption = 0;

			}

		}

		if (enter) {
			enter = false;
			if (options[currentOption] == "novo jogo") {

				Game.gameState = "NORMAL";
				setPause(false);
				file = new File("save.sp");
				file.delete();

			} else if (options[currentOption] == "opcoes") {

				if (file.exists()) {

					String saver = loadGame(10);
					applySave(saver);

				}

			} else if (options[currentOption] == "sair") {

				System.exit(1);

			} else if (options[currentOption] == "language") {

				if(language == "BR") {
					
					language = "EUA";
					
				} else if(language == "EUA") {
					
					language = "BR";
					
				}

			}

		}

	}

	public static void applySave(String str) {

		String[] spl = str.split("/");

		for (int i = 0; i < spl.length; i++) {

			String[] spl2 = spl[i].split(":");
			switch (spl2[0]) {

			case "level":
				
				Game.gameState = "NORMAL";
				setPause(false);
				break;
			case "vida":
				Game.player.life = Integer.parseInt(spl2[1]);
				break;

			}

		}

	}

	public static String loadGame(int encode) {

		String line = "";
		File file = new File("save.sp");
		if (file.exists()) {

			try {

				String singleLine = null;

				BufferedReader reader = new BufferedReader(new FileReader("save.sp"));

				try {

					while ((singleLine = reader.readLine()) != null) {

						String[] trans = singleLine.split(":");

						char[] val = trans[1].toCharArray();

						trans[1] = "";

						for (int i = 0; i < val.length; i++) {

							val[i] -= encode;

							trans[1] += val[i];

						}

						line += trans[0];
						line += ":";
						line += trans[1];
						line += "/";

					}

				} catch (IOException e) {

				}

			} catch (FileNotFoundException e) {

			}

		}
		return line;
	}

	public static void saveGame(String[] val1, int[] val2, int encode) {

		BufferedWriter write = null;

		try {

			write = new BufferedWriter(new FileWriter("save.sp"));

		} catch (IOException e) {

			e.printStackTrace();

		}

		for (int i = 0; i < val1.length; i++) {

			String current = val1[i];
			current += ":";
			char[] value = Integer.toString(val2[i]).toCharArray();

			for (int j = 0; j < value.length; j++) {

				value[j] += encode;
				current += value[j];

			}

			try {

				write.write(current);

				if (i < val1.length - 1) {

					write.newLine();

				}

			} catch (IOException e) {

			}

			try {

				write.flush();
				write.close();

			} catch (IOException e) {

			}
		}

	}

	public void render(Graphics g) {
		
		if(language == "BR") {
			try {
				MENU = ImageIO.read(getClass().getResource("/menu_br.png"));
				Image_Language = ImageIO.read(getClass().getResource("/br.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if(language == "EUA") {
			try {
				MENU= ImageIO.read(getClass().getResource("/menu_eu.png"));
				Image_Language = ImageIO.read(getClass().getResource("/eua.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		g.drawImage(MENU, 0, 0,Game.WIDTH* Game.SCALE,Game.HEIGHT*Game.SCALE,null);
		g.drawImage(Image_Language, 444, 604,9 * Game.SCALE,9 *Game.SCALE,null);
		Graphics2D g2 = (Graphics2D) g;
		
		g.setFont(new Font("arial", Font.BOLD, 36));
		g2.setColor(new Color(0, 0, 0, 0));
		g.fillRect(0, 0, Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE);


		// Opções Menu
		g.setColor(Color.white);

		if (options[currentOption] == "novo jogo") {

			g.drawString(">", ((Game.WIDTH * Game.SCALE) / 2) - 150, 270);

		} else if (options[currentOption] == "opcoes") {

			g.drawString(">", ((Game.WIDTH * Game.SCALE) / 2) - 150, 365);

		} else if (options[currentOption] == "sair") {

			g.drawString(">", ((Game.WIDTH * Game.SCALE) / 2) - 150, 460);

		}else if (options[currentOption] == "language") {

			g.drawString(">", ((Game.WIDTH * Game.SCALE) / 2) +170, 630);

		}

	}

	public static boolean isPause() {
		return pause;
	}

	public static void setPause(boolean pause) {
		Menu.pause = pause;
	}

}
