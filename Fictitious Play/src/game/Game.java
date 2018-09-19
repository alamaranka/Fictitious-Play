package game;

import java.util.ArrayList;
import java.util.Collections;

public class Game {

	public static String[] strategies = {"A1", "A2"};	
	public ArrayList<String> _rowActions, _columnActions;
	private double[] _beliefOfRow, _beliefOfColumn;
	private double[][] _payoffOfRow, _payoffOfColumn;
	private double[] _occurrenceOfRow, _occurrenceOfColumn;
	private double _totalUtilityRow, _totalUtilityColumn;
	
	public Game(double[][] payoffOfRow, double[][] payoffOfColumn, double[] occurrenceRow, double[] occurrenceColumn){
		_payoffOfRow=payoffOfRow;
		_payoffOfColumn=payoffOfColumn;
		_occurrenceOfRow = occurrenceRow;
		_occurrenceOfColumn = occurrenceColumn;
	}
	
	public void initializeGame(){
		_rowActions = new ArrayList<String>();
		_columnActions = new ArrayList<String>();
		_beliefOfRow = new double[]{0, 0};
		_beliefOfColumn = new double[]{0, 0};
	}
	
	public double[] getBeliefProb(double[] occurence){
		double[] temp = new double[2];
		double sum = occurence[0]+ occurence[1]; 
		temp[0] = occurence[0] / sum;
		temp[1] = occurence[1] / sum;
		return temp;
	}
	
	public double calculateUtilityRow(String strategy){
		double utility = 0;
		if(strategy.equals("A1")){
			utility = _payoffOfRow[0][0]*_beliefOfRow[0]+_payoffOfRow[0][1]*_beliefOfRow[1];
		}else{
			utility = _payoffOfRow[1][0]*_beliefOfRow[0]+_payoffOfRow[1][1]*_beliefOfRow[1];
		}
		return utility;
	}
	
	public double calculateUtilityColumn(String strategy){
		double utility = 0;
		if(strategy.equals("A1")){
			utility = _payoffOfColumn[0][0]*_beliefOfColumn[0]+_payoffOfColumn[1][0]*_beliefOfColumn[1];
		}else{
			utility = _payoffOfColumn[0][1]*_beliefOfColumn[0]+_payoffOfColumn[1][1]*_beliefOfColumn[1];
		}
		return utility;
	}
	
	public double getUtility(String player, String s1, String s2){
		double utility = 0;
		//row player
		if(player.equals("row")){
			if(s1.equals("A1") && s2.equals("A1")){
				_totalUtilityRow+=_payoffOfRow[0][0];
			}else if(s1.equals("A1") && s2.equals("A2")){
				_totalUtilityRow+=_payoffOfRow[0][1];
			}else if(s1.equals("A2") && s2.equals("A1")){
				_totalUtilityRow+=_payoffOfRow[1][0];
			}else{
				_totalUtilityRow+=_payoffOfRow[1][1];
			}
		}
		//column player
		if(player.equals("column")){
			if(s1.equals("A1") && s2.equals("A1")){
				_totalUtilityColumn+=_payoffOfColumn[0][0];
			}else if(s1.equals("A1") && s2.equals("A2")){
				_totalUtilityColumn+=_payoffOfColumn[0][1];
			}else if(s1.equals("A2") && s2.equals("A1")){
				_totalUtilityColumn+=_payoffOfColumn[1][0];
			}else{
				_totalUtilityColumn+=_payoffOfColumn[1][1];
			}
		}
		return utility;
	}
	
	public void play(int round){
		initializeGame();
		for(int i=0; i<round; i++){
			double uA1, uA2;
			// row player
			double [] bR = getBeliefProb(_occurrenceOfColumn);
			double[] bC = getBeliefProb(_occurrenceOfRow);
			_beliefOfRow[0] = bR[0]; 
			_beliefOfRow[1] = bR[1]; 
			uA1 = calculateUtilityRow("A1"); 
			uA2 = calculateUtilityRow("A2"); 
			if(uA1>=uA2){
				_rowActions.add("A1"); _occurrenceOfColumn[0]++;
			}else{
				_rowActions.add("A2"); _occurrenceOfColumn[1]++;
			}
			// row player
			_beliefOfColumn[0] = bC[0]; 
			_beliefOfColumn[1] = bC[1]; 
			uA1 = calculateUtilityColumn("A1"); 
			uA2 = calculateUtilityColumn("A2"); 
			if(uA1>=uA2){
				_columnActions.add("A1"); _occurrenceOfRow[0]++;
			}else{
				_columnActions.add("A2"); _occurrenceOfRow[1]++;
			}
			getUtility("row",_rowActions.get(i),_columnActions.get(i));
			getUtility("column",_rowActions.get(i),_columnActions.get(i));
		}
	}
	
	public void print(int round){
		for(int i=0; i<round; i++){
			System.out.println(_rowActions.get(i)+" "+_columnActions.get(i));
		}
		double occurrenceRowH, occurrenceRowT, occurrenceColumnH, occurrenceColumnT;
		occurrenceRowH = Collections.frequency(_rowActions, "A1");
		occurrenceRowT = Collections.frequency(_rowActions, "A2");
		occurrenceColumnH = Collections.frequency(_columnActions, "A1");
		occurrenceColumnT = Collections.frequency(_columnActions, "A2");
		System.out.println("-----------------------------");
		//System.out.println("  A1  "+"  A2  "+"    B1  "+" B2  ");
		System.out.println(occurrenceRowH+" "+occurrenceRowT+" : "+occurrenceColumnH+" "+occurrenceColumnT);
		double t1 = occurrenceRowH/(occurrenceRowH+occurrenceRowT);
		double t2 = occurrenceRowT/(occurrenceRowH+occurrenceRowT);
		double t3 = occurrenceColumnH/(occurrenceColumnH+occurrenceColumnT);
		double t4 = occurrenceColumnT/(occurrenceColumnH+occurrenceColumnT);
		System.out.println("-----------------------------");
		//System.out.println("  A1  "+"  A2  "+"    B1  "+" B2  ");
		System.out.println(t1+" "+t2+" : "+t3+" "+t4);
	}
	
	public ArrayList<String> getRowActions(){
		return _rowActions;
	}
	
	public ArrayList<String> getColumnActions(){
		return _columnActions;
	}

}
