package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearActionListener implements ActionListener{

	private GameGUI _gameGUI;
	
	public ClearActionListener(GameGUI gameGUI){
		_gameGUI = gameGUI;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		_gameGUI.clear();
	}

}
