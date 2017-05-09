package org.ggamedirdemo.remote_controller.custom4D2F;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.action.listener.IActionListener;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.framework.Sprite.MoveRageType;
import com.example.try_gameengine.remotecontroller.IRemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController.CommandType;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FCommandType;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FRemoteController;
import com.example.try_gameengine.scene.DialogScene;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaRequest;
import com.example.try_gameengine.utils.GameTimeUtil;
import com.example.try_gameengine.utils.IDetectAreaRequest;
import com.example.try_gameengine.utils.ISpriteDetectAreaListener;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;
import com.example.try_gameengine.utils.SpriteDetectAreaHelper;

public class MyScene extends EasyScene{
	private List<Sprite> fireballs = new CopyOnWriteArrayList<Sprite>();
	Sprite player;
	private int move = 0;
	private LinkedHashSet<Integer> keySequence	= new LinkedHashSet<Integer>();
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;
	boolean isPressLeftMoveBtn, isPressRightMoveBtn, isPressUpMoveBtn, isPressDownMoveBtn;
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;
	
	private int getLastMoveAfterRemoveMove(int curentMove){
		int lastMove = 0;
		int tmpMove = 0;
		Iterator<Integer> keySequenceIterator = keySequence.iterator();
		
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
	
	public MyScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		isEnableRemoteController(true);
		Custom4D2FRemoteController remoteController = Custom4D2FRemoteController.createRemoteController();
		setRemoteController(remoteController);
		remoteController.setRemoteContollerListener(new Custom4D2FRemoteController.RemoteContollerListener() {
			
			@Override
			public void pressDown(List<Custom4D2FCommandType> commandTypes) {
				// TODO Auto-generated method stub
				for(Custom4D2FCommandType commandType : commandTypes){
					switch (commandType) {
					case RightKeyUpCommand:
						isPressRightMoveBtn = false;
						move = getLastMoveAfterRemoveMove(RIGHT);
						
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn && !isPressUpMoveBtn && !isPressDownMoveBtn){
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
									isMoveing = false;
								}
							});
							
							move = 0;
							player.currentAction = null;
							isMoveing = false;
						}
						

						break;
					case RightKeyDownCommand:
						isPressRightMoveBtn = true;
						move = RIGHT;	
						keySequence.remove(move);
						keySequence.add(move);
						break;
					case LeftKeyDownCommand:
						isPressLeftMoveBtn = true;
						move = LEFT;
						keySequence.remove(move);
						keySequence.add(move);
						break;
					case LeftKeyUpCommand:
						isPressLeftMoveBtn = false;
						move = getLastMoveAfterRemoveMove(LEFT);
						
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn && !isPressUpMoveBtn && !isPressDownMoveBtn){
							player.setXscale(1.0f);
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
									isMoveing = false;
								}
							});
							
							move = 0;
							player.currentAction = null;
							isMoveing = false;
						}
						break;
					case UPKeyDownCommand:
						isPressUpMoveBtn = true;
						move = UP;
						keySequence.remove(move);
						keySequence.add(move);
						break;
					case UPKeyUpCommand:
						isPressUpMoveBtn = false;
						move = getLastMoveAfterRemoveMove(UP);
						
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn && !isPressUpMoveBtn && !isPressDownMoveBtn){
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
									isMoveing = false;
								}
							});
							
							move = 0;
							player.currentAction = null;
							isMoveing = false;
						}
						break;
					case DownKeyDownCommand:
						isPressDownMoveBtn = true;
						move = DOWN;
						keySequence.remove(move);
						keySequence.add(move);
						break;
					case DownKeyUpCommand:
						isPressDownMoveBtn = false;
						move = getLastMoveAfterRemoveMove(DOWN);
						
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn && !isPressUpMoveBtn && !isPressDownMoveBtn){
							player.setYscale(-1.0f);
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
									isMoveing = false;
								}
							});
							
							move = 0;
							player.currentAction = null;
							isMoveing = false;
						}
						break;
					default:
						break;
					}
				}
			}
		});
		
		player = new Sprite(BitmapUtil.yellowPoint, 100, 1000, false);
		player.setBitmapAndFrameWH(BitmapUtil.hamster, 150, 150);
		player.setCollisionRectFEnable(true);
		player.setPosition(CommonUtil.screenWidth/2.0f - player.w/2.0f, CommonUtil.screenHeight - player.h);
		player.setCollisionOffsetXY(50, 100);
		player.setCollisionRectFWH(100, 100);
	}

	GameView gameview;
	
	@Override
	public GameView initGameView(Activity activity, IGameController gameController,
			IGameModel gameModel) {
		// TODO Auto-generated method stub
		class MyGameView extends GameView{
			public MyGameView(Context context, IGameController gameController,
					IGameModel gameModel) {
				super(context, gameController, gameModel);
				// TODO Auto-generated constructor stub
			}			
		}		
		gameview = new MyGameView(activity, gameController, gameModel); return gameview;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		checkPlayerMoved();
		tickTime();
	}
	
	private void tickTime(){
		if(gameTimeUtil.isArriveExecuteTime()){
			gameTime++;
		}
	}
	
	boolean isMoveing = false;
	private void checkPlayerMoved(){
		
		if(move == LEFT){
			player.move(-5, 0);
			player.setXscale(1.0f);
			player.setYscale(1.0f);
			if(!isMoveing){
				isMoveing = true;
				player.runActionFPSFrame(null, new int[]{12,12,13}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
					
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
						isMoveing = false;
					}
				});
			}
		}else if(move == RIGHT){
			player.move(5, 0);
			player.setXscale(-1.0f);
			player.setYscale(1.0f);
			if(!isMoveing){
				isMoveing = true;
				player.runActionFPSFrame(null, new int[]{12,12,13}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
					
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
						isMoveing = false;
					}
				});
			}
		}else if(move == UP){
			player.move(0, -5);
			player.setYscale(1.0f);
			if(!isMoveing){
				isMoveing = true;
				player.runActionFPSFrame(null, new int[]{0,0,1}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
					
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
						isMoveing = false;
					}
				});
			}
		}else if(move == DOWN){
			player.move(0, 5);
			player.setYscale(-1.0f);
			if(!isMoveing){
				isMoveing = true;
				player.runActionFPSFrame(null, new int[]{0,0,1}, new int[]{0,10,10}, false, new com.example.try_gameengine.framework.IActionListener() {
					
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
						isMoveing = false;
					}
				});
			}
		}
		player.frameTrig();
	}

	private void showGameOverDialog(){
		final Layer bgLayer = new Layer(BitmapUtil.gameover, BitmapUtil.gameover.getWidth(), BitmapUtil.gameover.getHeight(), false);
		bgLayer.setPosition(0, bgLayer.h);
//		final Sprite restartButton = new Sprite(BitmapUtil.restartBtn01, 350, 200, false);
		final ButtonLayer restartButton = new ButtonLayer(0, 0, false);
		restartButton.setBitmapAndAutoChangeWH(BitmapUtil.restartBtn01);
		restartButton.setButtonBitmap(BitmapUtil.restartBtn01, BitmapUtil.restartBtn02, BitmapUtil.restartBtn01);
		restartButton.setPosition(CommonUtil.screenWidth/2.0f - restartButton.w/2.0f, CommonUtil.screenHeight/4.0f*3);
		restartButton.setOnClickListener(new ButtonLayer.OnClickListener() {
			
			@Override
			public void onClick(ButtonLayer buttonLayer) {
				// TODO Auto-generated method stub
				((GameActivity)context).sceneManager.previous();
			}
		});
		final LabelLayer labelLayer = new LabelLayer("hello", 0, 0, false);
		labelLayer.setTextSize(100);
		labelLayer.setPosition(500, 500);
		final DialogScene dialogScene = new DialogScene(context, "c");
		dialogScene.setDialogSceneDraw(new DialogScene.DialogSceneDrawListener() {
			
			@Override
			public void draw(Canvas canvas) {
				canvas.drawColor(Color.TRANSPARENT,Mode.CLEAR);
				Paint paint = new Paint();
				paint.setColor(Color.RED);
				canvas.drawRect(new Rect(100, 100, 300, 300), paint);
				paint.setColor(Color.BLACK);
				paint.setAlpha(150);
				canvas.drawRect(new RectF(0, 0, CommonUtil.screenWidth, CommonUtil.screenHeight ), paint);
				
				bgLayer.drawSelf(canvas, null);
				
				restartButton.drawSelf(canvas, null);
				
				labelLayer.drawSelf(canvas, null);
			}
		});
		dialogScene.setDialogSceneTouchListener(new DialogScene.DialogSceneTouchListener() {
			
			@Override
			public void onTouchEvent(MotionEvent event) {
				// TODO Auto-generated method stub
				float x = event.getX();
				float y = event.getY();
				
				LayerManager.getInstance().onTouchSceneLayers(event, dialogScene.getLayerLevel());
				

			}
		});
		dialogScene.setIsNeedToStopTheActiveScene(false);
		((GameActivity)context).sceneManager.addScene(dialogScene);
		((GameActivity)context).sceneManager.startLastScene();
		
		dialogScene.addAutoDraw(restartButton);
//		dialogScene.start();
	}
	
	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
//		sprite.drawSelf(canvas, null);
//		LayerManager.getInstance().drawLayers(canvas, null);
		LayerManager.getInstance().drawSceneLayers(canvas, null, sceneLayerLevel);
		
		Paint paint = new Paint();
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		canvas.drawText(gameTime+"", 100, 100, paint);
		player.drawSelf(canvas, null);
	}

	int count =0;
	float x = 0;
	float y = 0;
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			x = event.getX();
			y = event.getY();
		}else if(event.getAction() == MotionEvent.ACTION_MOVE){
			float dx = event.getX() - x;
			float dy = event.getY() - y;
			
			x = event.getX();
			y = event.getY();
		}
		return super.onTouchEvent(event);
	}
	
	@Override
	public void beforeGameStart() {
		// TODO Auto-generated method stub
		gameTimeUtil = new GameTimeUtil(1000);
	}

	@Override
	public void arrangeView(Activity activity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setActivityContentView(Activity activity) {
		// TODO Auto-generated method stub
		activity.setContentView(gameview);
	}

	@Override
	public void afterGameStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

}
