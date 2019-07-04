package cli;

/**
 * Human class contains prompts for user input
 */
public class Human extends Agent {

    Minimax minimax;
    /**
     *
     * @param game
     * @param isFirst
     */
    public Human(GomokuPlay game, boolean isFirst) {
		super(game, isFirst);
	}

	public byte takeTurn(GomokuMove move) {
		if (pickMove(move)==0) return 0;
		game.board.placeMove(me, move, false);
		return 1;
	}

    /**
     *
     * @param move
     * @return
     */
    public byte pickMove(GomokuMove move) {
//		
		if (game.board.getEmpties().contains(move))
			return 1;
		return 0;
	}

    @Override
    GomokuMove firstTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GomokuMove takeTurn() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public GomokuMove hint() {
        minimax = new Minimax();
        Object[] move = minimax.mmab(game.board, game.level, Double.NEGATIVE_INFINITY,
				Double.POSITIVE_INFINITY);
		return (GomokuMove) move[1];
    }

}