package org.ggamedirdemo.scenemanager;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.remotecontroller.RemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController.CommandType;
import com.example.try_gameengine.scene.DialogScene;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.GameTimeUtil;

public class MyScene extends EasyScene{
	private int move = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	boolean isPressLeftMoveBtn, isPressRightMoveBtn;
	private int gameTime;
	private GameTimeUtil gameTimeUtil;
	
	public MyScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		isEnableRemoteController(true);
		RemoteController remoteController = createDefaultRemoteController();
		setRemoteController(remoteController);
		remoteController.setRightKyPosition(CommonUtil.screenWidth - remoteController.getRightKey().w, CommonUtil.screenHeight - remoteController.getRightKey().h);
		remoteController.setLeftKyPosition(0, CommonUtil.screenHeight - remoteController.getRightKey().h);
		remoteController.setRemoteContollerListener(new RemoteController.RemoteContollerListener() {
			
			@Override
			public void pressDown(
					List<com.example.try_gameengine.remotecontroller.RemoteController.CommandType> commandTypes) {
				// TODO Auto-generated method stub
				for(CommandType commandType : commandTypes){
					switch (commandType) {
					case RightKeyUpCommand:
						isPressRightMoveBtn = false;
						move = LEFT;
						
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn){
							move = 0;
							isMoveing = false;
						}
						break;
					case RightKeyDownCommand:
						isPressRightMoveBtn = true;
						move = RIGHT;
						
						synchronized (LayerManager.getInstance()) {
							final Layer bgLayer = new Layer(BitmapUtil.gameover, BitmapUtil.gameover.getWidth(), BitmapUtil.gameover.getHeight(), false);
							bgLayer.setPosition(0, bgLayer.h);
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
									// TODO Auto-generated method stub
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
									LayerManager.getInstance().onTouchSceneLayers(event, dialogScene.getLayerLevel());
								}
							});
							dialogScene.setIsNeedToStopTheActiveScene(false);
							((GameActivity)context).sceneManager.addScene(dialogScene);
							((GameActivity)context).sceneManager.startLastScene();
							
							dialogScene.addAutoDraw(restartButton);
						}
						
						break;
					case LeftKeyDownCommand:
						isPressLeftMoveBtn = true;
						move = LEFT;
						break;
					case LeftKeyUpCommand:
						isPressLeftMoveBtn = false;
						move = RIGHT;
				
						if(!isPressLeftMoveBtn && !isPressRightMoveBtn){
							move = 0;
							isMoveing = false;
						}
						break;
					default:
						break;
					}
				}
			}
		});
		
		Sprite bg = new Sprite(BitmapUtil.bg, CommonUtil.screenWidth, CommonUtil.screenHeight, false);
		addAutoDraw(bg);
		Sprite flower = new Sprite(BitmapUtil.flower, BitmapUtil.flower.getWidth(), BitmapUtil.flower.getHeight(), false);
		addAutoDraw(flower);
		flower.setPosition(0, CommonUtil.screenHeight - flower.h*1.5f);
		Sprite cloud1 = new Sprite(BitmapUtil.cloud1, BitmapUtil.cloud1.getWidth(), BitmapUtil.cloud1.getHeight(), false);
		addAutoDraw(cloud1);
		cloud1.setPosition(CommonUtil.screenWidth/2.0f - cloud1.w/2.0f, 0);
		Sprite cloud2 = new Sprite(BitmapUtil.cloud2, BitmapUtil.cloud2.getWidth(), BitmapUtil.cloud2.getHeight(), false);
		addAutoDraw(cloud2);
		cloud2.setPosition(0, 0);
		Sprite cloud3 = new Sprite(BitmapUtil.cloud3, BitmapUtil.cloud3.getWidth(), BitmapUtil.cloud3.getHeight(), false);
		addAutoDraw(cloud3);
		cloud3.setPosition(CommonUtil.screenWidth - cloud1.w, 0);
	}

	GameView gameview;
	
	@Override
	public void initGameView(Activity activity, IGameController gameController,
			IGameModel gameModel) {
		// TODO Auto-generated method stub
		class MyGameView extends GameView{
			public MyGameView(Context context, IGameController gameController,
					IGameModel gameModel) {
				super(context, gameController, gameModel);
				// TODO Auto-generated constructor stub
			}			
		}		
		gameview = new MyGameView(activity, gameController, gameModel);
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
			if(!isMoveing){
				isMoveing = true;
			}
		}else if(move == RIGHT){
			if(!isMoveing){
				isMoveing = true;
			}
		}
	}
	
	public void drawEnemis(Canvas canvas){
	}
	
	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		LayerManager.getInstance().drawSceneLayers(canvas, null, sceneLayerLevel);
		
		Paint paint = new Paint();
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		canvas.drawText(gameTime+"", 100, 100, paint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
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
