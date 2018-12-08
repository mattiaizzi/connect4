package com.mattiaizzi.forzaquattro.utils;



import com.mattiaizzi.forzaquattro.field.Size;

import javafx.scene.paint.Color;

public class Utils {
	public static class Output{
		public static final String WELCOME = "****BENVENUTO NEL GIOCO FORZA 4****";
		public static final String MENU_MODE = "\n"
				+ "1) Player vs Player\n"
				+"2) Player vs Pc\n"
				+"3) Info\n"
				+ "4) Exit";
		public static final String MENU_DIFFICULTY = "Scegliere la difficoltà:\n"
				+ "1) Facile\n"
				+"2) Intermedio\n"
				+ "3) Difficile";
		public static final String MENU_SIZE = "Scegliere le dimensioni del campo (colonna x riga):\n"
				+ "1) 5 x 4\n"
				+"2) 6 x 5\n"
				+ "3) 8 x 7\n"
				+ "4) 9 x 7\n"
				+ "5) 10 x 7\n"
				+ "6) 8 x 8\n"
				+ "7) 7 x 6";
		public static final String INFO = "Videogioco del celebre gioco da tavolo Forza 4.\n"
										+ "Implementato con modalità PVP(Player vs Player)\n"
										+ "e modalità PVE(Player vs Environment).\n"
										+ "L'intelligenza artificiale, nella modalità PVE,\n"
										+ "è stata realizzata implementando l'algorirmo minimax\n"
										+ "e le varie difficoltà sono state realizzate aumentando\n"
										+ "la profondità di analisi dell'algoritmo.\n"
										+ "Inoltre è possibile selezionarie varie dimensioni per\n"
										+ "la scacchiera e vari colori per le pedine dei giocatori.\n\n"
										+ "Gioco realizzato interamente da Mattia Izzi\n"
										+ "matricola: 098394 per l'esame di programmazione avanzata ";

		public static String askMove(String player) {
			return "Turno di " + player + ". Effettua la tua mossa\n";
		}
	}
	
	public static class Decode{
		
		public static Size getSize(String string) {
			switch (string) {
			case "5x4":
				return Size.FIVE_FOUR;
			case "6x5":
				return Size.SIX_FIVE;
			case "8x7":
				return Size.EIGHT_SEVEN;
			case "9x7":
				return Size.NINE_SEVEN;
			case "10x7":
				return Size.TEN_SEVEN;
			case "8x8":
				return Size.EIGHT_EIGHT;
			case "7x6":
			default:
				return Size.SEVEN_SIX;
			}
		}
		
	}
}
