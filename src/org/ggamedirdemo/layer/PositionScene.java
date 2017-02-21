package org.ggamedirdemo.layer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.example.try_gameengine.action.MAction;
import com.example.try_gameengine.action.MovementAction;
import com.example.try_gameengine.action.MovementAtionController;
import com.example.try_gameengine.action.listener.IActionListener;
import com.example.try_gameengine.framework.ALayer;
import com.example.try_gameengine.framework.ALayer.LayerParam;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.ILayer;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.framework.Sprite.MoveRageType;
import com.example.try_gameengine.framework.Sprite.RotationType;
import com.example.try_gameengine.remotecontroller.IRemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController.CommandType;
import com.example.try_gameengine.remotecontroller.RemoteController.RemoteContollerListener;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FCommand;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FCommandType;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FRemoteController;
import com.example.try_gameengine.scene.DialogScene;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaPoint;
import com.example.try_gameengine.utils.DetectAreaRect;
import com.example.try_gameengine.utils.DetectAreaRequest;
import com.example.try_gameengine.utils.DetectAreaRound;
import com.example.try_gameengine.utils.DetectAreaSpriteRect;
import com.example.try_gameengine.utils.GameTimeUtil;
import com.example.try_gameengine.utils.IDetectAreaRequest;
import com.example.try_gameengine.utils.ISpriteDetectAreaListener;
import com.example.try_gameengine.utils.SpriteDetectAreaBehavior;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;
import com.example.try_gameengine.utils.SpriteDetectAreaHelper;

public class PositionScene extends EasyScene{
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;
	
	private Custom4D2FRemoteContollerListener custom4d2fRemoteContollerListener = new Custom4D2FRemoteContollerListener();
	
	private RectF userRectF = new RectF(100, 100, 200, 200);
	private RectF rotateCenterRectF = new RectF(300, 100, 400, 200);
	private RectF rectF = new RectF(100, 300, 200, 400);
	private float circleRadius = 50;
//	private PointF circleCenter = new PointF(550, 250);
//	private PointF pointF = new PointF(500, 500);
	private RectF rectF2 = new RectF(500, 100, 600, 200);
	private RectF rectF3 = new RectF(300, 300, 400, 400);
	private RectF rectF4 = new RectF(500, 300, 600, 400);
	private RectF rectF5 = new RectF(100, 500, 200, 600);
	private RectF rectF6 = new RectF(300, 500, 400, 600);
	private RectF rectF7 = new RectF(500, 500, 600, 600);
	private RectF rectF8 = new RectF(100, 700, 200, 800);
	private RectF rectF9 = new RectF(300, 700, 400, 800);
	private RectF rectF10 = new RectF(500, 700, 600, 800);
	private Sprite sprite = new Sprite();
	private LabelLayer dirMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer collisionMsgLayer = new LabelLayer(0.0f, 70.0f, false);
	private Sprite rect1Layer = new Sprite(0.0f, 50.0f, false);
	private Sprite rectRotateCenterMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rectMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite circleMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite pointMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect2MsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect3MsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect4MsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect5MsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect6MsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect7MsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect8MsgLayer = new Sprite(0.0f, 50.0f, false);
	private DetectArea userRectDetectArea;
	private DetectArea rectDetectArea;
	private DetectArea circleDetectArea;
	private DetectArea pointDetectArea;
	
	private LabelLayer labelLayer = new LabelLayer(0, 0, false);
	private ButtonLayer buttonLayer = new ButtonLayer(0, 0, false);
	
	private ALayer s = new Sprite();
	
	private ALayer a = new Layer();
	
	private ALayer m = new ALayer() {
		
		@Override
		public void onTouched(MotionEvent event) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void drawSelf(Canvas canvas, Paint paint) {
			// TODO Auto-generated method stub
			
		}
	};
	
	class MyA extends Activity{
		
	};
	
	
	private void setDectecAreas(){
		userRectDetectArea = new DetectAreaRect(userRectF);
		rectDetectArea = new DetectAreaRect(rectF);
//		circleDetectArea = new DetectAreaRound(circleCenter, circleRadius);
//		pointDetectArea = new DetectAreaPoint(pointF);
		
		SpriteDetectAreaHandler spriteDetectAreaHandler = new SpriteDetectAreaHandler();
		DetectArea a = new DetectAreaSpriteRect(new RectF(), new DetectAreaSpriteRect.SpriteRectListener() {
			
			@Override
			public RectF calculateSpriteRect() {
				// TODO Auto-generated method stub
				RectF rectF;
				if(sprite.getLocationInScene()!=null)
					rectF = new RectF(sprite.getLocationInScene().x, sprite.getLocationInScene().y, sprite.getLocationInScene().x + sprite.getWidth(), sprite.getLocationInScene().y + sprite.getHeight());
				else
					rectF = sprite.getFrame();
				return rectF;
			}
			
			@Override
			public PointF calculateSpriteCenter() {
				// TODO Auto-generated method stub;
				PointF pointF;
				if(sprite.getLocationInScene()!=null)
					pointF = new PointF(sprite.getLocationInScene().x + sprite.getWidth()/2, sprite.getLocationInScene().y + sprite.getHeight()/2);
				else
					pointF = new PointF(sprite.getFrame().centerX(), sprite.getFrame().centerY());
				return pointF;
			}
		});
	
		sprite.setSpriteDetectAreaHandler(spriteDetectAreaHandler);
		spriteDetectAreaHandler.addSuccessorDetectArea(a);
	}	
	
//	private void checkDetectAreasCollision(){
//		if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, rectDetectArea)){
//			collisionMsgLayer.setText("Collision RECT");
//		}else if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, circleDetectArea)){
//			collisionMsgLayer.setText("Collision CIRCLE");
//		}else if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, pointDetectArea)){
//			collisionMsgLayer.setText("Collision POINT");
//		}else{
//			collisionMsgLayer.setText("");
//		}
//	}
	
	public PositionScene(final Context context, String id, int level, int mode) {
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
		
		setDectecAreas();
		
		rect1Layer.setAnchorPoint(0.5f, 1.0f);
		rectRotateCenterMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rectMsgLayer.setAnchorPoint(0.5f, 1.0f);
		circleMsgLayer.setAnchorPoint(0.5f, 1.0f);
		pointMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect2MsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect3MsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect4MsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect5MsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect6MsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect7MsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect8MsgLayer.setAnchorPoint(0.5f, 1.0f);
//		userRectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		rectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		circleMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		pointMsgLayer.setAnchorPoint(0.5f, 0.0f);
		
		rect1Layer.setPosition(userRectF.centerX(), userRectF.bottom);
		rectRotateCenterMsgLayer.setPosition(rotateCenterRectF.centerX(), rotateCenterRectF.bottom);
		rectMsgLayer.setPosition(rectF.centerX(), rectF.bottom);
//		circleMsgLayer.setPosition(circleCenter.x, circleCenter.y+circleRadius);
//		pointMsgLayer.setPosition(pointF.x, pointF.y + 50);
		circleMsgLayer.setPosition(rectF2.centerX(), rectF2.bottom);
		pointMsgLayer.setPosition(rectF3.centerX(), rectF3.bottom);
		rect2MsgLayer.setPosition(rectF4.centerX(), rectF4.bottom);
		rect3MsgLayer.setPosition(rectF5.centerX(), rectF5.bottom);
		rect4MsgLayer.setPosition(rectF6.centerX(), rectF6.bottom);
		rect5MsgLayer.setPosition(rectF7.centerX(), rectF7.bottom);
		rect6MsgLayer.setPosition(rectF8.centerX(), rectF8.bottom);
		rect7MsgLayer.setPosition(rectF9.centerX(), rectF9.bottom);
		rect8MsgLayer.setPosition(rectF10.centerX(), rectF10.bottom);
		
		rect1Layer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectRotateCenterMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		circleMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		pointMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect2MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect3MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect4MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect5MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect6MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect7MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect8MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		
		rect1Layer.setPaint(paint);
		rectRotateCenterMsgLayer.setPaint(paint);
		rectMsgLayer.setPaint(paint);
		circleMsgLayer.setPaint(paint);
		pointMsgLayer.setPaint(paint);
		rect2MsgLayer.setPaint(paint);
		rect3MsgLayer.setPaint(paint);
		rect4MsgLayer.setPaint(paint);
		rect5MsgLayer.setPaint(paint);
		rect6MsgLayer.setPaint(paint);
		rect7MsgLayer.setPaint(paint);
		rect8MsgLayer.setPaint(paint);
		
		Sprite child = new Sprite();
//		child.setPosition(userRectMsgLayer.getWidth(), userRectMsgLayer.getHeight());
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect1Layer.addChild(child);
		
		child = new Sprite();
//		child.setPosition(userRectMsgLayer.getWidth(), userRectMsgLayer.getHeight());
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectRotateCenterMsgLayer.addChild(child);
//		rectRotateCenterMsgLayer.setRotation(90);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(-1.1f, -0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		circleMsgLayer.addChild(child);
//		circleMsgLayer.setRotation(90);
		
		child = new Sprite();
		child.setPosition(100, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectMsgLayer.addChild(child);
//		rectMsgLayer.setRotation(45);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
		LayerParam layerParam = new LayerParam();
		layerParam.setPercentageX(0.5f);
		layerParam.setEnabledPercentagePositionX(true);
		child.setLayerParam(layerParam);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		pointMsgLayer.addChild(child);
//		pointMsgLayer.setRotation(45);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
		layerParam = new LayerParam();
		layerParam.setPercentageX(0.5f);
		layerParam.setPercentageY(0.5f);
		layerParam.setEnabledPercentagePositionX(true);
		layerParam.setEnabledPercentagePositionY(true);
		child.setLayerParam(layerParam);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect2MsgLayer.addChild(child);
//		rect2MsgLayer.setRotation(45);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
		layerParam = new LayerParam();
		layerParam.setPercentageW(0.5f);
		layerParam.setPercentageH(0.5f);
		layerParam.setEnabledPercentageSizeW(true);
		layerParam.setEnabledPercentageSizeH(true);
		child.setLayerParam(layerParam);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect3MsgLayer.addChild(child);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
		layerParam = new LayerParam();
		layerParam.setPercentageW(0.5f);
		layerParam.setPercentageH(0.5f);
		layerParam.setEnabledPercentageSizeW(true);
		layerParam.setEnabledPercentageSizeH(true);
		layerParam.setPercentageX(0.5f);
		layerParam.setPercentageY(0.5f);
		layerParam.setEnabledPercentagePositionX(true);
		layerParam.setEnabledPercentagePositionY(true);
		child.setLayerParam(layerParam);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect4MsgLayer.addChild(child);
		
		child = new Sprite();
		child.setPosition(rect5MsgLayer.getX(), rect5MsgLayer.getY());
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
//		rect5MsgLayer.addChild(child);
		rect5MsgLayer.addWithOutLayerLevelIncrease(child);
		
		child = new Sprite();
		child.setBitmapAndAutoChangeWH(BitmapUtil.fireball);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
//		child.setAnchorPoint(0.5f, 0.5f);
//		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect6MsgLayer.addChild(child);
		
		child = new Sprite();
		child.setBitmapAndAutoChangeWH(BitmapUtil.fireball);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
//		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect7MsgLayer.addChild(child);
		
		child = new Sprite();
		child.setBitmapAndAutoChangeWH(BitmapUtil.fireball);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		layerParam = new LayerParam();
		layerParam.setPercentageW(0.5f);
		layerParam.setPercentageH(0.5f);
		layerParam.setEnabledPercentageSizeW(true);
		layerParam.setEnabledPercentageSizeH(true);
		layerParam.setPercentageX(0.5f);
		layerParam.setEnabledPercentagePositionX(true);
		child.setLayerParam(layerParam);
		child.setAnchorPoint(0.5f, 0.5f);
		rect8MsgLayer.addChild(child);
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
		return gameview = new MyGameView(activity, gameController, gameModel);
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		checkPlayerMoved();
//		checkDetectAreasCollision();
		tickTime();
	}
	
	private void tickTime(){
		if(gameTimeUtil.isArriveExecuteTime()){
			gameTime++;
		}
	}
	
	boolean isMoveing = false;
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
		
		rect1Layer.setPosition(userRectF.centerX(), userRectF.bottom);
//		player.frameTrig();
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
		paint.setColor(Color.WHITE);
		canvas.drawText(gameTime+"", 300, 70, paint);
		dirMsgLayer.drawSelf(canvas, paint);
		collisionMsgLayer.drawSelf(canvas, paint);
		
		canvas.drawRect(userRectF, paint);
		canvas.drawRect(rotateCenterRectF, paint);
		canvas.drawRect(rectF, paint);
//		canvas.drawCircle(circleCenter.x, circleCenter.y, circleRadius, paint);
//		canvas.drawPoint(pointF.x, pointF.y, paint);
		canvas.drawRect(rectF2, paint);
		canvas.drawRect(rectF3, paint);
		canvas.drawRect(rectF4, paint);
		canvas.drawRect(rectF5, paint);
		canvas.drawRect(rectF6, paint);
		canvas.drawRect(rectF7, paint);
		canvas.drawRect(rectF8, paint);
		canvas.drawRect(rectF9, paint);
		canvas.drawRect(rectF10, paint);
		
		rect1Layer.drawSelf(canvas, null);
		rectRotateCenterMsgLayer.drawSelf(canvas, null);
		rectMsgLayer.drawSelf(canvas, null);
		circleMsgLayer.drawSelf(canvas, null);
		pointMsgLayer.drawSelf(canvas, null);
		rect2MsgLayer.drawSelf(canvas, null);
		rect3MsgLayer.drawSelf(canvas, null);
		rect4MsgLayer.drawSelf(canvas, null);
		rect5MsgLayer.drawSelf(canvas, null);
		rect6MsgLayer.drawSelf(canvas, null);
		rect7MsgLayer.drawSelf(canvas, null);
		rect8MsgLayer.drawSelf(canvas, null);
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
