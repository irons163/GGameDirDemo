package org.ggamedirdemo.movement_action;

import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionFactory;
import com.example.try_gameengine.action.MovementActionItemCountDownTimer;
import com.example.try_gameengine.action.MovementActionSetWithThread;

public class UDMovementActionFactory extends MovementActionFactory{

	@Override
	public MovementAction createMovementAction() {
		// TODO Auto-generated method stub
		if(action==null)
			action = new MovementActionSetWithThread();
		action.addMovementAction(new MovementActionItemCountDownTimer(30000, 1000, 0, -10));
		action.addMovementAction(new MovementActionItemCountDownTimer(30000, 1000, 0, 10));
		return action;
	}

}
