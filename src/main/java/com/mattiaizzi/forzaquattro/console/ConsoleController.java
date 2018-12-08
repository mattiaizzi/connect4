package com.mattiaizzi.forzaquattro.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

import com.mattiaizzi.forzaquattro.controller.IOController;
import com.mattiaizzi.forzaquattro.field.Field;

public class ConsoleController implements IOController {

	private BufferedReader in;
	private PrintStream out;

	public ConsoleController(InputStream in, OutputStream out) {
		this.in = new BufferedReader(new InputStreamReader(in));
		this.out = new PrintStream(out);
	}
	
	public ConsoleController() {
		this(System.in, System.out);
	}

	@Override
	public int getInt(String message) {
		while (true) {
			try {
				out.println(message);
				String s = in.readLine();
				return Integer.parseInt(s.trim());
			} catch (Exception e) {
				out.println("Inserire un numero");
			}
		}
	}

	@Override
	public void print(String message) {
		out.println(message);

	}

	@Override
	public void printField(Field field) {
		print(field.toString());
	}

	@Override
	public String getMessage(String message) {
		while (true) {
			try {
				out.println(message);
				String s = in.readLine();
				if (s.trim().length() == 0) {
					out.println("Impossibile una stringa vuota.");
				} else {
					return s.trim();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void wait(String message) {
		out.println(message);
		try {
			in.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
