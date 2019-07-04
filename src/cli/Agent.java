package cli;

import java.io.Serializable;

/**
 * Agent class that is extended by other players
 */
public abstract class Agent implements Serializable{

    public GomokuPlay game;
    public char me;
    char them;
    boolean isFirst;
    String name;

	/**
	 * Agent constructor
	 * Each agent has an instance of Board
         * @param game     the game
	 * @param  isFirst player's turn
	 */
	public Agent(GomokuPlay game, boolean isFirst) {
                this.game = game;
		this.me = isFirst ? 'x' : 'o';
		this.them = isFirst ? 'o' : 'x';
		this.isFirst = isFirst;
	}

	/**
	 * Execute a turn
	 * @param  move to execute
	 * @return agent's move
	 */
	public byte takeTurn(GomokuMove move) {
		return 0;
	}
        
        /**
     *
     * @return player's name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name      the player's name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    abstract GomokuMove firstTurn() ;
    public abstract GomokuMove takeTurn();
    public abstract GomokuMove hint() ;
}