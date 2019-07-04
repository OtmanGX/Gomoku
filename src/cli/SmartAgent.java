package cli;

/**
 * SmartAgent class that uses minimax algorithm and heuristic evaluation to pick next move
 */
public class SmartAgent extends Agent {

	Minimax minimax;

    /**
     *
     * @param game
     * @param isFirst
     */
    public SmartAgent(GomokuPlay game, boolean isFirst) {
		super(game, isFirst);
		minimax = new Minimax();
	}

	GomokuMove firstTurn() {
		// pick default first move
//		String move = "7 7";
                GomokuMove move = new GomokuMove(7, 7);
		game.board.placeMove(me, move, true);
		return move;
	}

	public GomokuMove takeTurn() {
		GomokuMove move = pickMove();
		game.board.placeMove(me, move, true);
		return move;
	}

	public GomokuMove pickMove() {
		// use minimax
		Object[] move = minimax.mmab(game.board, game.level, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY);
		return (GomokuMove) move[1];
	}

    @Override
    public GomokuMove hint() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
}