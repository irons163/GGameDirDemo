package org.ggamedirdemo.remote_controller.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

import com.example.try_gameengine.remotecontroller.custom.CustomRemoteController;
import com.example.try_gameengine.remotecontroller.custom.CustomTouch;
import com.example.try_gameengine.remotecontroller.custome.Key;

import android.util.Log;
import android.view.MotionEvent;

public class KeyDispatcher {
	private Key move = null;
	private LinkedHashSet<Key> keySequence	= new LinkedHashSet<Key>();
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	
	interface KeyDispatchListener{
		public void onTouchDown(Key key);
		public void onTouchUp(Key key);
		public void onLatestTouching(Key key);
		public void onLastKeyUp(Key key);
	}
	
	KeyDispatchListener keyDispatchListener = new KeyDispatchListener() {
			
		@Override
		public void onTouchUp(Key key) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onTouchDown(Key key) {
			// TODO Auto-generated method stub
			
		}
		
//		@Override
//		public void onLastTouching(Key key) {
//			// TODO Auto-generated method stub
//			
//		}
		
		@Override
		public void onLastKeyUp(Key key) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onLatestTouching(Key key) {
			// TODO Auto-generated method stub
			
		}
	};
	
	List<Key> keys = new ArrayList<Key>();
	
	final Key leftKey = new Key(BitmapUtil.fireball, 0, 0, 1, false);
//	key.setBitmap(BitmapUtil.fireball);
	final Key rightKey = new Key(BitmapUtil.fireball, 0, 0, 1, false);
	
	public void pressDown(List<CustomTouch> commandTouchs) {
		// TODO Auto-generated method stub
		for(CustomTouch commandType : commandTouchs){
//				if(commandType.getTouch() != leftKey)
//					break;
//				
//				if(commandType.getTouch() == leftKey){
//					isPressRightMoveBtn = false;
//					move = getLastMoveAfterRemoveMove(RIGHT);
//				}
			
			if(commandType.getTouch() == leftKey){
//					isPressRightMoveBtn = false;
//					move = getLastMoveAfterRemoveMove(RIGHT);
				
				switch (commandType.getEvent().getAction()) {
					case MotionEvent.ACTION_DOWN:
//						move = LEFT;
//						keySequence.remove(move);
//						keySequence.add(move);
						touchDown(leftKey);
						break;
					case MotionEvent.ACTION_UP:
						
						touchUp(leftKey);
						
						break;
				}
				
				
			}else if(commandType.getTouch() == rightKey){
				switch (commandType.getEvent().getAction()) {
				case MotionEvent.ACTION_DOWN:
					move = rightKey;	
					keySequence.remove(rightKey);
					keySequence.add(rightKey);
					
					touchDown(rightKey);
					break;
				case MotionEvent.ACTION_UP:
					
					break;
				}
			}
		}
	}
	
	private void touchDown(Key key){
		keyDispatchListener.onTouchDown(key);
		
		if(key == leftKey){
			move = key;
			keySequence.remove(rightKey);
			keySequence.add(rightKey);
		}else{
			move = key;	
			keySequence.remove(rightKey);
			keySequence.add(rightKey);
			
		}
		keyDispatchListener.onLatestTouching(move);
	}
	
	private void touchUp(Key key){
		keyDispatchListener.onTouchUp(key);
		move = getLastMoveAfterRemoveMove(key);
		
		if(move==null){
			lastKeyUp(leftKey);
		}else{
			keyDispatchListener.onLatestTouching(move);
		}
	}
	
	private void lastKeyUp(Key key){
		keyDispatchListener.onLastKeyUp(key);
		
		/*
		player.setXscale(-1.0f);
		player.setYscale(1.0f);
		player.runActionFPSFrame(null, new int[]{11,10,9}, new int[]{8,8,8}, false, new com.example.try_gameengine.framework.IActionListener() {
			
			@Override
			public void beforeChangeFrame(int nextFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterChangeFrame(int periousFrameId) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void actionFinish() {
				// TODO Auto-generated method stub
//					isMoveing = false;
				player.currentAction = null;
				isMoveing = false;
			}
		});*/
	}
	
//	private int getLastMoveAfterRemoveMove(int curentMove){
//		int lastMove = 0;
//		int tmpMove = 0;
//		Iterator<Integer> keySequenceIterator = keySequence.iterator();
//		
//		while(keySequenceIterator.hasNext()){
//			tmpMove = keySequenceIterator.next();
//			if(tmpMove==curentMove)
//				keySequenceIterator.remove();	
//		}
//		
//		keySequenceIterator = keySequence.iterator();
//		while(keySequenceIterator.hasNext()){
//			lastMove = keySequenceIterator.next();	
//		}
//		
//		Log.e("lastMove", lastMove+"");
//		
//		return lastMove;
//	}
	
	private Key getLastMoveAfterRemoveMove(Key curentMove){
		Key lastMove = null;
		Key tmpMove = null;
		Iterator<Key> keySequenceIterator = keySequence.iterator();
		
		while(keySequenceIterator.hasNext()){
			tmpMove = keySequenceIterator.next();
			if(tmpMove==curentMove)
				keySequenceIterator.remove();	
		}
		
		keySequenceIterator = keySequence.iterator();
		while(keySequenceIterator.hasNext()){
			lastMove = keySequenceIterator.next();	
		}
		
		Log.e("lastMove", lastMove+"");
		
		return lastMove;
	}

	public KeyDispatchListener getKeyDispatchListener() {
		return keyDispatchListener;
	}

	public void setKeyDispatchListener(KeyDispatchListener keyDispatchListener) {
		this.keyDispatchListener = keyDispatchListener;
	}
	
}
