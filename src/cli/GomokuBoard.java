package cli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * GomokuBoard class that contains all board manipulation and look around methods
 first player / white == 'x'
 second player / black == 'o'
 empty space == '.'
 Moves are formatted as "row column" (without quotes) where row and column are integers
 */
public class GomokuBoard extends Board implements Serializable,Cloneable{

    String lastMove;

	public GomokuBoard() {
		super(15, 5);
		// fill char[][] with empty spaces
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = '.';
			}
		}
		winner = '.';
                nextPlayer='x';
	}

	/**
	 * Copy constructor for Board
	 * @param  other board to copy
	 */
	public GomokuBoard(GomokuBoard other) {
                super(other.n, other.m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				this.board[i][j] = other.board[i][j];
			}
		}
		nextPlayer = other.nextPlayer;
		prevPlayer = other.prevPlayer;
                winner= other.winner;
	}

	/**
	 * Get set of empty spots on board
	 * @return ems set of empty locations
	 */
	Set<GomokuMove> getEmpties() {
		Set<GomokuMove> ems = new HashSet<GomokuMove>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '.') {
					ems.add(new GomokuMove(i, j));
				}

			}
		}
		return ems;
	}

	/**
	 * Place a move on the board
	 * @param  p    player's char representation
	 * @param  move location
	 * @param  out  if true, the move gets printed to System.out
	 * @return move
	 */
	GomokuMove placeMove(char p, GomokuMove move, boolean out) {
//		int[] ij = parseMove(move);
		board[move.getX()][move.getY()] = p;
		if (out)
			System.out.println(move);
		prevPlayer = p;
		// sets next and prev player
		if (p == 'x')
			nextPlayer = 'o';
		else
			nextPlayer = 'x';
		winner = setWinner(p, move.getXY());
		return move;
	}

	/**
	 * Get set of all player's pieces
	 * @param  p player
	 * @return ArrayList of locations
	 */
	public ArrayList<GomokuMove> getPlayerPlaces(char p) {
		ArrayList<GomokuMove> places = new ArrayList<GomokuMove>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == p) {
					places.add(new GomokuMove(i, j));
				}
			}
		}
		return places;
	}

	/**
	 * Look for empty spots around a location
	 * @param  pos location to look around
	 * @return ArrayList of empty locations
	 */
	ArrayList<GomokuMove> lookAround(int[] pos) {
		ArrayList<GomokuMove> adjacent = new ArrayList<GomokuMove>();
//		int[] coords = parseMove(pos);
		int i = pos[0];
		int j = pos[1];
		String x;
		if (i - 1 >= 0) {
			if (board[i - 1][j] == '.') {
//				x = strMove(i - 1, j);
                                
				adjacent.add(new GomokuMove(i-1, j));
			}
			if (j - 1 >= 0) {
				if (board[i - 1][j - 1] == '.') {
//					x = strMove(i - 1, j - 1);
					adjacent.add(new GomokuMove(i-1, j-1));
				}
			}
                        
                        // i j-1
		}
		if (j + 1 < n) {
			if (board[i][j + 1] == '.') {
//				x = strMove(i, j + 1);
				adjacent.add(new GomokuMove(i, j+1));
			}
			if (i - 1 >= 0) {
				if (board[i - 1][j + 1] == '.') {
//					x = strMove(i - 1, j + 1);
					adjacent.add(new GomokuMove(i-1,j+1));
				}
			}
		}
		if (i + 1 < n) {
			if (board[i + 1][j] == '.') {
//				x = strMove(i + 1, j);
				adjacent.add(new GomokuMove(i+1,j));
			}
			if (j + 1 < n) {
				if (board[i + 1][j + 1] == '.') {
//					x = strMove(i + 1, j + 1);
					adjacent.add(new GomokuMove(i+1,j+1));
				}
			}
			if (j - 1 >= 0) {
				if (board[i + 1][j - 1] == '.') {
//					x = strMove(i + 1, j - 1);
					adjacent.add(new GomokuMove(i+1,j-1));
				}
			}                        
		}
                if (j-1>=0){
                    	if (board[i][j - 1] == '.') {
//			x = strMove(i, j - 1);
			adjacent.add(new GomokuMove(i,j-1));
				}
                }
		return adjacent;
	}

	/**
	 * Set the board's winner
	 * @param  p  player
	 * @param  ij location
	 * @return a player char or 'd' if the board is in a draw state (no empty spots)
	 */
	char setWinner(char p, int[] ij) {
		if (isRowWin(p, ij) || isColWin(p, ij) || isLtrWin(p, ij)
				|| isRtlWin(p, ij)) {
			return p;
		}
		if (getEmpties().isEmpty()) {
			return 'd';
		}
		return '.';
	}

	/**
	 * Check row of last move for win
	 * @param  p
	 * @param  ij
	 * @return true if row has win
	 */
	boolean isRowWin(char p, int[] ij) {
            //for j=0 j<n j++
		String row = new String(board[ij[0]]);
		if (row.contains(strMatch(p)))
			return true;
		return false;
	}

	/**
	 * Check column of last move for win
	 * @param  p
	 * @param  ij
	 * @return true if column has win
	 */
	boolean isColWin(char p, int[] ij) {
		String column = "";
		for (int i = 0; i < n; i++) {
			column += board[i][ij[1]];
		}
		if (column.contains(strMatch(p)))
			return true;
		return false;
	}

	/**
	 * Check diagonal of last move for win, looking top left to bottom right
	 * @param  p
	 * @param  ij
	 * @return true if diagonal has win
	 */
	boolean isLtrWin(char p, int[] ij) {
		int i = ij[0];
		int j = ij[1];
		String match = strMatch(p, m);
		String diag = "";
		// going up and left
		while (i >= 0 && j >= 0) {
			diag = String.valueOf(board[i][j]) + diag;
			i--;
			j--;
		}
		i = ij[0] + 1;
		j = ij[1] + 1;
		// going down and right
		while (i < n && j < n) {
			diag += String.valueOf(board[i][j]);
			i++;
			j++;
		}
		if (diag.contains(match))
			return true;
		return false;
	}

	/**
	 * Check diagonal of last move for win, looking top right to bottom left
	 * @param  p
	 * @param  ij
	 * @return true if diagonal has win
	 */
	boolean isRtlWin(char p, int[] ij) {
		int i = ij[0];
		int j = ij[1];
		String match = strMatch(p, m);
		String diag = "";
		// going up and right
		while (i >= 0 && j < n) {
			diag = String.valueOf(board[i][j]) + diag;
			i--;
			j++;
		}
		i = ij[0] + 1;
		j = ij[1] - 1;
		// going down and left
		while (i < n && j >= 0) {
			diag += String.valueOf(board[i][j]);
			i++;
			j--;
		}
		if (diag.contains(match))
			return true;
		return false;
	}

	/**
	 * Calculate number of near win chains
	 * @param  p
	 * @param  away from winning chain
	 * @return sum of all nearWin methods
	 */
	int nearWins(char p, int away) {
		return nearWinRows(p, away) + nearWinCols(p, away);
	}

	/**
	 * Calculate number of near win chains in rows
	 * @param  p
	 * @param  away
	 * @return count
	 */
	int nearWinRows(char p, int away) {
		int count = 0;
		int length = m - away;
                char[] subrow;
                int playerCount;
                int vacantCount;
                int adversaryCount;
		
		for (int i = 0; i < n; i++) {
			char[] row = board[i];
                        
                        //char[] row = board[i];
			for(int j=0;j<=n-m;j++){
                            playerCount=0;
                            vacantCount=0;
                            adversaryCount=0;
                            subrow =Arrays.copyOfRange(row, j , j+m);                           
                            for(int k=0; k<m;k++){
                                if(subrow[k]== p){
                                    playerCount+=1;
                                }
                                else if(subrow[k]==".".charAt(0)){
                                    vacantCount+=1;
                                }
                                else{
                                    adversaryCount+=1;
                                }
                            }
                            if(playerCount==length && vacantCount==away){    
                                count++;
                            } 
                            
                        }
                      
		}
		return count;
	}

	/**
	 * Calculate number of near win chains in columns
	 * @param  p
	 * @param  away
	 * @return count
	 */
	int nearWinCols(char p, int away) {
		int count = 0;
		int length = m - away;
                char[] subrow;
                int playerCount;
                int vacantCount;
                int adversaryCount;
		
		//String match1 = strMatch(p, length);
		//String match2 = '.' + match1;
		//match1 += '.';
		for (int j = 0; j < n; j++) {
			char[] column = new char[n];
			for (int i = 0; i < n; i++) {
				column [i]= board[i][j];
			}
			for(int l=0;l<=n-m;l++){
                            playerCount=0;
                            vacantCount=0;
                            adversaryCount=0;
                            subrow =Arrays.copyOfRange(column, l , l+m);                           
                            for(int k=0; k<m;k++){
                                if(subrow[k]== p){
                                    playerCount++;
                                }
                                else if(subrow[k]==".".charAt(0)){
                                    vacantCount++;
                                }
                                else{
                                    adversaryCount++;
                                }
                            }
                            if(playerCount==length && vacantCount==away){    
                                count++;
                            } 
                            
                        }
		}

		return count;
	}

	/**
	 * Generate string to match for win and nearWin methods
	 * @param  p
	 * @return match
	 */
	String strMatch(char p) {
		String match = "";
		for (int i = 0; i < m; i++) {
			match += Character.toString(p);
		}
		return match;
	}

	/**
	 * Generate string to match for win and nearWin methods
	 * @param  p
	 * @param  length of match string
	 * @return match
	 */
	String strMatch(char p, int length) {
		String match = "";
		for (int i = 0; i < length; i++) {
			match += Character.toString(p);
		}
		return match;
	}

	/**
	 * Parse move from a string into int[]
	 */
//	int[] parseMove(String s) {
//		String[] ss = s.split(" ");
//		int[] ij = { Integer.parseInt(ss[0]), Integer.parseInt(ss[1]) };
//		return ij;
//	}


	/**
	 * Create string move from int[] coordinates
	 * @param  ij coordinates
	 * @return move string
	 */
	String strMove(int[] ij) {
		return ij[0] + " " + ij[1];
	}

	/**
	 * Print a nice board
	 * @return String representation of board
	 */
	@Override
	public String toString() {
		String str = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				str += board[i][j] + " ";
			}
			str += "\n";
		}
		return str;
	}
        
        @Override
        protected Object clone() {
            Object output = new GomokuBoard(this);
            return output;
        }

}