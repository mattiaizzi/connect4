package com.mattiaizzi.forzaquattro.controller;

import com.mattiaizzi.forzaquattro.field.Field;
import com.mattiaizzi.forzaquattro.move.ForzaMove;

public interface IOController {
	String getMessage(String message);
	int getInt(String message);
	void print(String message);
	void printField(Field field);
}
