package driver;

import game.*;

public class Driver{

	public static int round = 10000;
	
	public static void main(String[] args) {
		double[][] rowData = new double[][]{
			{-1, 1},
			{1, -1}
		};;
		double[][] columnData = new double[][]{
			{1, -1},
			{-1, 1}
		};;
		double [] occurrenceHistoryRow = new double[]{0, 0};
		double [] occurrenceHistoryColumn = new double[]{0, 0};
		Game game = new Game(rowData, columnData, occurrenceHistoryRow, occurrenceHistoryColumn);
		game.play(round);;
		game.print(round);
		//GameGUI gui = new GameGUI(new Game(rowData, columnData));
		//gui.run();
	}

}
