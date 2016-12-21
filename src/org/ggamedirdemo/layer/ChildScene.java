package org.ggamedirdemo.layer;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FCommandType;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FRemoteController;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.GameTimeUtil;

public class ChildScene extends EasyScene{
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;
	
	private Custom4D2FRemoteContollerListener custom4d2fRemoteContollerListener = new Custom4D2FRemoteContollerListener();
	
	private RectF userRectF = new RectF(100, 200, 200, 300);
	private RectF rotateCenterRectF = new RectF(500, 200, 600, 300);
	private RectF rectF = new RectF(100, 500, 200, 600);
	private RectF rectF2 = new RectF(800, 200, 900, 300);
	private RectF rectF3 = new RectF(500, 500, 600, 600);
	private RectF rectF4 = new RectF(100, 800, 200, 900);
	private LabelLayer dirMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer collisionMsgLayer = new LabelLayer(0.0f, 70.0f, false);
	private Sprite userRectMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rectRotateCenterMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rectMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite circleMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite pointMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect2MsgLayer = new Sprite(0.0f, 50.0f, false);
	
	public ChildScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		isEnableRemoteController(true);
		Custom4D2FRemoteController remoteController = Custom4D2FRemoteController.createRemoteController();
		setRemoteController(remoteController);
		custom4d2fRemoteContollerListener.setCustom4D2FRemoteContollerListener(new Custom4D2FRemoteController.RemoteContollerListener() {
			
			@Override
			public void pressDown(List<Custom4D2FCommandType> commandTypes) {
				// TODO Auto-generated method stub
				
			}
		});
		remoteController.setRemoteContollerListener(custom4d2fRemoteContollerListener);
		
		userRectMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rectRotateCenterMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rectMsgLayer.setAnchorPoint(0.5f, 1.0f);
		circleMsgLayer.setAnchorPoint(0.5f, 1.0f);
		pointMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect2MsgLayer.setAnchorPoint(0.5f, 1.0f);
		
		userRectMsgLayer.setPosition(userRectF.centerX(), userRectF.bottom);
		rectRotateCenterMsgLayer.setPosition(rotateCenterRectF.centerX(), rotateCenterRectF.bottom);
		rectMsgLayer.setPosition(rectF.centerX(), rectF.bottom);
		circleMsgLayer.setPosition(rectF2.centerX(), rectF2.bottom);
		pointMsgLayer.setPosition(rectF3.centerX(), rectF3.bottom);
		rect2MsgLayer.setPosition(rectF4.centerX(), rectF4.bottom);
		
		userRectMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectRotateCenterMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		circleMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		pointMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect2MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		
		userRectMsgLayer.setPaint(paint);
		rectRotateCenterMsgLayer.setPaint(paint);
		rectMsgLayer.setPaint(paint);
		circleMsgLayer.setPaint(paint);
		pointMsgLayer.setPaint(paint);
		rect2MsgLayer.setPaint(paint);
		
		Sprite child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.2f);
		child.setYscale(0.2f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		userRectMsgLayer.addChild(child);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.2f);
		child.setYscale(0.2f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.getLayerParam().setEnabledBindPositionXY(true);
		child.getLayerParam().setBindPositionXY(child.getX(), child.getY());
		rectRotateCenterMsgLayer.addChild(child);
		rectRotateCenterMsgLayer.setRotation(90);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.2f);
		child.setYscale(0.2f);
		child.setRotationType(RotationType.ROTATE_WITH_CENTER);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.getLayerParam().setEnabledBindPositionXY(true);
		child.getLayerParam().setBindPositionXY(child.getX(), child.getY());
		circleMsgLayer.addChild(child);
		circleMsgLayer.setRotation(90);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.2f);
		child.setYscale(0.2f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectMsgLayer.addChild(child);
		rectMsgLayer.setRotation(45);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.2f);
		child.setYscale(0.2f);
		child.setRotationType(RotationType.ROTATE_WITH_ANCHOR_POINT);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		pointMsgLayer.addChild(child);
		pointMsgLayer.setRotation(45);
		pointMsgLayer.setRotationType(RotationType.ROTATE_WITH_ANCHOR_POINT);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.2f);
		child.setYscale(0.2f);
		child.setRotationType(RotationType.ROTATE_WITH_ANCHOR_POINT);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.getLayerParam().setEnabledBindPositionXY(true);
		child.getLayerParam().setBindPositionXY(child.getX(), child.getY());
		rect2MsgLayer.addChild(child);
		rect2MsgLayer.setRotation(45);
		rect2MsgLayer.setRotationType(RotationType.ROTATE_WITH_ANCHOR_POINT);
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
	
	private void checkPlayerMoved(){
		
		if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.LEFT){
			userRectF.offset(-5, 0);
			dirMsgLayer.setText("LEFT");
		}else if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.RIGHT){
			userRectF.offset(5, 0);
			dirMsgLayer.setText("RIGHT");
		}else if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.UP){
			userRectF.offset(0, -5);
			dirMsgLayer.setText("UP");
		}else if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.DOWN){
			userRectF.offset(0, 5);
			dirMsgLayer.setText("DOWN");
		}else{
			dirMsgLayer.setText("");
		}
		
		userRectMsgLayer.setPosition(userRectF.centerX(), userRectF.bottom);
	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		LayerManager.getInstance().drawSceneLayers(canvas, null, sceneLayerLevel);
		
		Paint paint = new Paint();
		paint.setTextSize(50);
		paint.setTypeface(Typeface.DEFAULT_BOLD);
		paint.setColor(Color.WHITE);
		canvas.drawText(gameTime+"", 300, 70, paint);
		dirMsgLayer.drawSelf(canvas, paint);
		collisionMsgLayer.drawSelf(canvas, paint);

		canvas.drawRect(userRectF, paint);
		canvas.drawRect(rotateCenterRectF, paint);
		canvas.drawRect(rectF, paint);
		canvas.drawRect(rectF2, paint);
		canvas.drawRect(rectF3, paint);
		canvas.drawRect(rectF4, paint);
		
		userRectMsgLayer.drawSelf(canvas, null);
		rectRotateCenterMsgLayer.drawSelf(canvas, null);
		rectMsgLayer.drawSelf(canvas, null);
		circleMsgLayer.drawSelf(canvas, null);
		pointMsgLayer.drawSelf(canvas, null);
		rect2MsgLayer.drawSelf(canvas, null);
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
