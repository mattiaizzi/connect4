/**
 * 
 */
package com.mattiaizzi.forzaquattro.ia;

import java.util.List;

/**Intefaccia che definisce i comportamenti base
 * 
 * di una IA nei giochi a turni come tris, scacchi, forza 4,...
 * 
 * 
 * @author Mattia Izzi
 * 
 * @param <M> implementazione dell'interfaccia Move 
 *
 */
public interface IA<M extends Move> {
	
	/**Valutazione delle migliori mosse possibili
	 * 
	 * @param depth profondità della ricerca
	 * @return lista ordinata delle migliori mosse
	 */
	default List<M> getBestMoves(int depth){
		return getBestMoves(depth, getPossibleMoves());
	}
	
	/**Valutazione delle migliori mosse possibili
	 * 
	 * @param depth profondità della ricerca
	 * @param moves possibili mosse
	 * @return lista ordinata delle migliori mosse
	 */

	public List<M> getBestMoves(int depth, List<M> moves);
	
	/**
	 * 
	 * @return lista delle mosse che si possono effettuare
	 */
	public List<M> getPossibleMoves();
	
	/**
	 * 
	 * @return true se il gioco è finito, false altrimenti
	 */
	public boolean gameOver();
	
	/**Metodo che effettua una mossa
	 * 
	 * @param move la mossa da effettuare
	 */
	public void makeMove(M move);
	
	/**Metodo che annulla una mossa
	 * 
	 * @param move la mossa da annullare
	 */
	public void unmakeMove(M move);
	
	/**valuta lo stato del gioco
	 * 
	 * @return valore dello stato del gioco
	 */
	public int evaluate();
}
