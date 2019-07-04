package cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class Minimax implements Serializable{

	/**
	 * Evaluation function
	 * @param  board node to evaluate
	 * @return score of board
	 */
	double evaluate(GomokuBoard board) {
                int noneAway = board.nearWins(board.prevPlayer, 0);
		int oneAway = board.nearWins(board.prevPlayer, 1);
		int twoAway = board.nearWins(board.prevPlayer, 2);
		int threeAway = board.nearWins(board.prevPlayer, 3);
		double score = noneAway * 1000 + oneAway * 100.0 + twoAway * 5.0 + threeAway * 1.0;
		return score;
	}

	/**
	 * Minimax algorithm with alpha-beta pruning.
	 * @param  depth lookahead
	 * @param  myBest alpha
	 * @param  theirBest beta
	 * @return Object[0] contains (Double) score and Object[1] contains (String) move
	 */
	Object[] mmab(GomokuBoard board, int d, double myBest, double theirBest) {
		ArrayList<GomokuMove> moveList;
		Set<GomokuMove> moves = new HashSet<GomokuMove>();
		ArrayList<GomokuMove> places = board.getPlayerPlaces(board.nextPlayer);
//                                places.addAll(board.getPlayerPlaces(board.prevPlayer)); 
                                
		for (int i = 0; i < places.size(); i++) {
			moves.addAll(board.lookAround(places.get(i).getXY()));
                        
		}
		moves.retainAll(board.getEmpties());
		// make sure that moves is not empty
		// otherwise, pick from list of empty locations
		if (moves.isEmpty())
			moveList = new ArrayList<GomokuMove>(board.getEmpties());
		else
			moveList = new ArrayList<GomokuMove>(moves);

		Double bestScore;
		Object[] temp;
		Double tempScore;
		GomokuMove bestMove=null;

		// evaluate at leaf
		if (d == 0) {
			Object[] x = { evaluate(board), moveList.get(0) };
			return x;
		}
		bestScore = myBest;
		while (moveList.size() > 0) {
			GomokuBoard newBoard = new GomokuBoard(board);
			GomokuMove newMove = moveList.get(0);
			newBoard.placeMove(newBoard.nextPlayer, newMove, false);
			temp = mmab(newBoard, d - 1, -theirBest, -bestScore);
			tempScore = -(Double) temp[0];
			if (tempScore > bestScore) {
				bestScore = tempScore;
				bestMove = newMove;
			}
			if (bestScore > theirBest) {
				Object[] x = { bestScore, bestMove };
				return x;
			}
			moveList.remove(0);
		}
		Object[] x = { bestScore, bestMove };
		return x;
	}
}