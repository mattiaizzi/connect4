package com.mattiaizzi.forzaquattro.ia;

import java.util.Collections;
import java.util.List;

import com.mattiaizzi.forzaquattro.move.ForzaMove;


/**
 * Specifica dell'algoritmo minimax 
 * @author Mattia Izzi
 *
 * @param <M> Il tipo di mossa(varia in base al gioco: forza quattro, tris,..)
 */
public abstract class MiniMax <M extends Move> implements IA<M>{

	@Override
	public List<M> getBestMoves(int depth, List<M> possibleMoves){
		minimax(possibleMoves,depth, 1);
		Collections.sort(possibleMoves);
		return possibleMoves;
	}

	private int minimax(List<M> initialMoves, int depth, int who) {
		if(gameOver()||depth==0){
			return evaluate();
		}
		if(who>0) {
			return maximize(initialMoves, depth, who);
		}else {
			return minimize(initialMoves, depth, who);
		}	
	}

	private int maximize(List<M> initialMoves, int depth, int who) {
		double bestScore=Double.NEGATIVE_INFINITY;
		for(int i=0; i<initialMoves.size();i++) {
			M move = initialMoves.get(i);
			makeMove(move);
			int score = minimax(this.getPossibleMoves(), depth-1, -who);
			unmakeMove(move);
			move.setValue(score);
			if(score > bestScore) {
				bestScore = score;
			}
		}
		return (int)bestScore;
	}

	private int minimize(List<M> initialMoves, int depth, int who) {
		double bestScore=Double.POSITIVE_INFINITY;
		for(int i=0; i<initialMoves.size();i++) {
			M move = initialMoves.get(i);
			makeMove(move);
			int score = minimax(this.getPossibleMoves(), depth-1, -who);
			unmakeMove(move);
			move.setValue(score);
			if(score < bestScore) {
				bestScore = score;
			}
		}
		return (int)bestScore;
	}


}
