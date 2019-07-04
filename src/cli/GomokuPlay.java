package cli;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * GomokuPlay class contains configurations of the current running game
 */
public class GomokuPlay  implements Serializable{

    
	/**
	 * Main method. Parse command line arguments to get game settings
	 */
         public LinkedList<GomokuBoard> listBoards ;
         public GomokuBoard board;
         public int mode;
         public boolean hint=false;
         public int currentBoardPosition=0;
         int level=3;
        public Agent p1,p2;
         
         private GomokuPlay(int mode){
             /**
            * mode=1 => SmartAgent Vs Human
            * mode=2 => Human Vs Human
            * mode=3 => Replay
            */
             board= new GomokuBoard();
             listBoards = new LinkedList<>();
             this.mode = mode;
             if(mode==1) {
                p1 = new SmartAgent(this, true);
                p1.setName("Computer");
                p2 = new Human(this, false);
                p2.setName("Player");
                p1.firstTurn();
                listBoards.add((GomokuBoard)board.clone());
             } else if (mode==2){
                p1 = new Human(this, true);
                p1.setName("Player1");
                p2 = new Human(this, false);
                p2.setName("Player2");
                listBoards.add((GomokuBoard)board.clone());
             }
         }
         
         public void makeTurn(GomokuMove move) {
             byte state=0;
             board = (GomokuBoard)board.clone();
                              
             if (mode==1) {
                if(board.nextPlayer=='o') {
                    state =p2.takeTurn(move);
                }
             } else if (mode==2){
                        
                if(board.nextPlayer=='x') {   
                    state =p1.takeTurn(move);
                } else {
                    state =p2.takeTurn(move); 
                }
                
             }
             if(state==1) {
                if(currentBoardPosition!=listBoards.size()-1)
                    for(int i=(currentBoardPosition+1);i<listBoards.size();i++)
                        listBoards.remove(i);
                
                listBoards.add(board);
                currentBoardPosition++;
             }
         }
         
         public GomokuMove hint() {
             hint = false;
             if(board.nextPlayer=='x') return p1.hint();
             else return p2.hint();
         }
         
         public void previousBoard() {
            board = listBoards.get(--currentBoardPosition);
                      
         }
         
         
         public void nextBoard() {
                 board = listBoards.get(++currentBoardPosition);
                      
         }
         
         public boolean hasNext() {
             return currentBoardPosition<listBoards.size()-1;
         }
         
         public boolean hasPrevious() {
             if(currentBoardPosition==0) {
                 return false;
             }
             return true;
         }
         
         public void setLevel(int level) {
            this.level = level;
            }
         
    /**
	 * Get the board's winner
	 * @return player's char representation
	 */
    public char getWinner() {
		return board.winner;
	}
         
    /**
     *
     * @param mode
     * @param n
     */
    public static void newGame(int mode) {
             CurrentGame = new GomokuPlay(mode);
         }
         
    public static void newGame(GomokuPlay game) {
             CurrentGame = game;
         }
    /**
     *
     * @throws Throwable
     */
    public static void endGame() throws Throwable {
             CurrentGame.finalize();
             CurrentGame=null;
         }
         
          /** Instance unique pré-initialisée */
        private static GomokuPlay CurrentGame;
        
    /**
     *
     * @return
     */
    public static GomokuPlay getCurrentGame() {
            return CurrentGame;
        }

         
}