package model;

import java.io.IOException;

public class Game {

	private final Board board;
	private Piece nextPiece;
	private Piece pieceSaved;

	private boolean playing = false;
	private boolean paused = false;
	private boolean dropping = false;
	private boolean gameOver = false;
	private boolean saved = false;

	private boolean scoreRecovered = false;

	private int freeFallIterations;

	private int totalScore;
	private ListeScores listeScores;

	public Game() {
		listeScores = new ListeScores();
		try {
			if (!scoreRecovered) {
				listeScores.recupererScores();
				scoreRecovered = true;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board = new Board();
	}

	public BoardCell[][] getBoardCells() {
		return board.getBoardWithPiece();
	}

	public Piece getNextPiece() {
		return nextPiece;
	}

	public long getIterationDelay() {
		return (long) (((11 - getLevel()) * 0.05) * 1000);
	}

	public int getScore() {
		return ((21 + (3 * getLevel())) - freeFallIterations);
	}

	public int getTotalScore() {
		return totalScore;
	}

	public int getLines() {
		return board.getFullLines();
	}

	public int getLevel() {
		if ((board.getFullLines() >= 1) && (board.getFullLines() <= 90)) {
			return 1 + ((board.getFullLines() - 1) / 10);
		} else if (board.getFullLines() >= 91) {
			return 10;
		} else {
			return 1;
		}
	}

	public void startGame() {
		paused = false;
		dropping = false;
		nextPiece = Piece.getRandomPiece();
		board.setCurrentPiece(Piece.getRandomPiece());
		playing = true;
	}

	public boolean isPlaying() {
		return playing;
	}

	public boolean isPaused() {
		return paused;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void pauseGame() {
		paused = !paused;
	}

	public void moveLeft() {
		board.moveLeft();
	}

	public void moveRight() {
		board.moveRight();
	}

	public void moveDown() {
		if (!board.canCurrentPieceMoveDown()) {

			if (freeFallIterations == 0) {
				playing = false;
				gameOver = true;
			} else {
				dropping = false;
				board.setCurrentPiece(nextPiece);
				nextPiece = Piece.getRandomPiece();
				totalScore += getScore();
				freeFallIterations = 0;
			}
		} else {
			board.moveDown();
			freeFallIterations++;
		}
	}

	public void rotate() {
		board.rotate();
	}

	public void drop() {
		dropping = true;
	}

	public boolean isDropping() {
		return dropping;
	}

	public ListeScores getListeScores() {
		return listeScores;
	}

	public Piece getPieceSauvegardee() {
		return pieceSaved;
	}

	public void savedPiece() {
		pieceSaved = new Piece(board.getCurrentPiece().getType(),board.getCurrentPiece().getType().getPoints(), true );
		board.deleteCurrentPiece();
		dropping = false;
		saved = true;
		board.setCurrentPiece(nextPiece);
		nextPiece = Piece.getRandomPiece();
		freeFallIterations = 0;

	}
	
	public void playSavedPiece() {
		if(pieceSaved != null) {
		board.deleteCurrentPiece();
		board.setCurrentPiece(pieceSaved);
		saved = false;
		dropping = false;
		pieceSaved = null;
		nextPiece = Piece.getRandomPiece();
		freeFallIterations = 0;
		
		}
	}

	public Board getBoard() {
		return board;
	}

	public boolean isSaved() {
		return saved;
	}

}