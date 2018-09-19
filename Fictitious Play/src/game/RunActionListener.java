package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RunActionListener implements ActionListener{

	private Game _game;
	private GameGUI _gameGUI;
	
	public RunActionListener(Game game, GameGUI gameGUI){
		_game = game;
		_gameGUI = gameGUI;
	}
	
	public void actionPerformed(ActionEvent arg0){	
		_game.play(1000);
		_game.print(1000);
		_gameGUI.setResult();
	}
		
}
