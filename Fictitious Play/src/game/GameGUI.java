package game;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;

//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GameGUI extends JFrame implements Runnable{

	private Game _game;
	ArrayList<JPanel> _tables = new ArrayList<JPanel>();
	ArrayList<JTextField> _textFields = new ArrayList<JTextField>();
	ArrayList<JButton> _buttons = new ArrayList<JButton>();
	ArrayList<JLabel> _labels = new ArrayList<JLabel>();
	
	public GameGUI(Game game){
		_game=game;
	}
	
	public void generateTables(){
		//
		JPanel panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,4));
		for(int i=0; i<8; i++){
			JTextField txt = new JTextField();
			txt.setHorizontalAlignment(JTextField.CENTER);
			_textFields.add(txt);
			panel1.add(txt);
			setLayout(null);
			panel1.setBounds(55, 30, 120, 60);
		}
		_tables.add(panel1);
		//
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,2));
		for(int i=0; i<4; i++){
			JTextField txt = new JTextField();
			txt.setHorizontalAlignment(JTextField.CENTER);		
			_textFields.add(txt);
			panel2.add(txt);
			setLayout(null);
			panel2.setBounds(200, 30, 60, 40);
		}
		_tables.add(panel2);
	}
	
	public void generateButtons(){
		JButton buttonR = new JButton("Run");
		setLayout(null);
		buttonR.setBounds(50, 100, 60, 30);
		RunActionListener buttonRActionListener = new RunActionListener(_game, this);
		buttonR.addActionListener(buttonRActionListener);
		_buttons.add(buttonR);
		//
		JButton buttonC = new JButton("Clear");
		setLayout(null);
		buttonC.setBounds(50, 100, 60, 30);
		ClearActionListener buttonCActionListener = new ClearActionListener(this);
		buttonC.addActionListener(buttonCActionListener);
		_buttons.add(buttonC);
	}
	
	public void generateLabels(){
		JLabel label1 = new JLabel("A1");
		setLayout(null);
		label1.setBounds(10, 30, 40, 30);
		label1.setHorizontalAlignment(JLabel.CENTER);
		_labels.add(label1);
		JLabel label2 = new JLabel("A2");
		setLayout(null);
		label2.setBounds(10, 60, 40, 30);
		label2.setHorizontalAlignment(JLabel.CENTER);
		_labels.add(label2);
		//
		JLabel label3 = new JLabel("A1");
		setLayout(null);
		label3.setBounds(65, 0, 40, 30);
		label3.setHorizontalAlignment(JLabel.CENTER);
		_labels.add(label3);
		JLabel label4 = new JLabel("A2");
		setLayout(null);
		label4.setBounds(125, 0, 40, 30);
		label4.setHorizontalAlignment(JLabel.CENTER);
		_labels.add(label4);
	}
	
	public void getAllTogether(){
		generateTables(); generateButtons(); generateLabels();
		for(int i=0; i<_tables.size();i++){
			add(_tables.get(i));
		}
		//
		for(int i=0; i<_buttons.size();i++){
			add(_buttons.get(i));
		}
		//
		for(int i=0; i<_labels.size();i++){
			add(_labels.get(i));
		}
	}
	
	public void setResult(){
		double resultRow = (double) Collections.frequency(_game._rowActions, "A1")/(Collections.frequency(_game._rowActions, "A1")+Collections.frequency(_game._rowActions, "A2"));
		_labels.get(0).setText(Double.toString(resultRow));
		_labels.get(1).setText(Double.toString(1-resultRow));
		double resultColumn = (double) Collections.frequency(_game._columnActions, "A1")/(Collections.frequency(_game._columnActions, "A1")+Collections.frequency(_game._columnActions, "A2"));
		_labels.get(2).setText(Double.toString(resultColumn));
		_labels.get(3).setText(Double.toString(1-resultColumn));
	}
	
	public void clear(){
		for(int i=0; i<_tables.size();i++){
			
		}
		//
		for(int i=0; i<_buttons.size();i++){
			
		}
		//
		for(int i=0; i<_labels.size();i++){
			
		}
	}
	
	public void run(){
		GameGUI game = new GameGUI(_game);
		game.getAllTogether();
		//setting operations
		game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.setSize(300,200);
		game.setVisible(true);
		game.setTitle("Game");
	}

}
