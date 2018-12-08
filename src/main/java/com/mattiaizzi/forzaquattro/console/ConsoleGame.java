package com.mattiaizzi.forzaquattro.console;

import com.mattiaizzi.forzaquattro.coin.Coin;
import com.mattiaizzi.forzaquattro.field.Size;
import com.mattiaizzi.forzaquattro.match.GameDifficulty;
import com.mattiaizzi.forzaquattro.match.GameType;
import com.mattiaizzi.forzaquattro.match.Match;
import com.mattiaizzi.forzaquattro.match.Options;
import com.mattiaizzi.forzaquattro.utils.Utils;

public class ConsoleGame {
	private ConsoleController controller;
	private GameType type;
	private GameDifficulty difficulty;
	private String player1;
	private String player2;
	private Size size;

	public ConsoleGame(ConsoleController controller) {
		this.controller = controller;
		controller.print(Utils.Output.WELCOME);
		chooseOptions();
	}

	public void play() {
		boolean rivincita = false;
		Options options = new Options(type, size, difficulty, player1, player2);
		Match match = new Match(controller, options);
		do {
			match.play();
			rivincita = chooseAgain();
			if(rivincita) {
				match.reset();
			}
		} while (rivincita);
	}

	private boolean chooseAgain() {
		String choose;
		do {
			choose = controller.getMessage("Rivincita?(S/N): ");
		} while (!(choose.toUpperCase().equals("S")||choose.toUpperCase().equals("N")));
		if(choose.toUpperCase().equals("S"))return true;
		else return false;
	}

	private void chooseNames() {
		player1 = controller.getMessage("Inserire nome giocatore 1: ");
		if (type == GameType.PVE) {
			player2 = "PC";
		} else {
			player2 = controller.getMessage("Inserire nome giocatore 2: ");
		}
	}

	private void chooseSize() {
		int choose;
		do {
			choose = controller.getInt(Utils.Output.MENU_SIZE + "\n\nScelta: ");
		} while (choose < 1 || choose > 7);
		size = Size.getSize(choose);
	}

	private void chooseDifficulty() {
		if (type == GameType.PVE) {
			int choose;
			do {
				choose = controller.getInt(Utils.Output.MENU_DIFFICULTY + "\n\nScelta: ");
			} while (choose < 1 || choose > 3);
			difficulty = GameDifficulty.getDifficulty(choose);
		}
	}

	private void chooseOptions() {
		chooseMode();
		chooseDifficulty();
		chooseSize();
		chooseNames();
	}

	private void chooseMode() {
		int choose;
		do {
			choose = controller.getInt(Utils.Output.MENU_MODE + "\n\nScelta: ");
		} while (choose < 1 || choose > 4);
		switch(choose) {
		case 1:
			type = GameType.PVP;
			break;
		case 2:
			type = GameType.PVE;
			break;
		case 3:
			printInfo();
			break;
		case 4:
		default: System.exit(0);;
		}
	}

	private void printInfo() {
		controller.print("********************");
		controller.print(Utils.Output.INFO);
		controller.print("********************");
		controller.wait("Premi un tasto per tornare al menù principale...");
		chooseMode();
	}

	public ConsoleGame() {
		this(new ConsoleController());
	}
}
