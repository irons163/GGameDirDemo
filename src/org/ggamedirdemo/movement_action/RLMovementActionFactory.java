package org.ggamedirdemo.movement_action;

import android.util.Log;

import com.example.try_gameengine.action.DoubleDecorator;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementActionFactory;
import com.example.try_gameengine.action.MovementActionItemCountDownTimer;
import com.example.try_gameengine.action.MovementActionSetWithThread;

public class RLMovementActionFactory extends MovementActionFactory{

	@Override
	public MovementAction createMovementAction() {
		// TODO Auto-generated method stub
		MovementAction newAction;
		
		if(action==null){
			newAction = new DoubleDecorator(new MovementActionSetWithThread());
		}else
			newAction = new DoubleDecorator(new MovementActionSetWithThread());
			newAction.addMovementAction(new DoubleDecorator(new MovementActionItemCountDownTimer(1000, 200, 10, 0, "R")) );
			newAction.addMovementAction(new DoubleDecorator(new MovementActionItemCountDownTimer(1000, 200, -10, 0, "L")) );
		
			if(action!=null){
				action.addMovementAction(newAction);
				newAction =	action;
			}
		
		Log.i("MovementDescription", newAction.getDescription());
		
		return newAction;
	}

}
