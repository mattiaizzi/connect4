package com.mattiaizzi.forzaquattro.controller;

import com.mattiaizzi.forzaquattro.field.Field;
import com.mattiaizzi.forzaquattro.move.ForzaMove;

/**
 * Interfaccia che definisce il controller dell'input e dell'output
 * @author Mattia Izzi
 *
 */
public interface IOController {
	String getMessage(String message);
	int getInt(String message);
	void print(String message);
	void printField(Field field);
}
