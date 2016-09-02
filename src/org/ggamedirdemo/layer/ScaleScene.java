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
import com.example.try_gameengine.enemy.EnemyManager;
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
import com.example.try_gameengine.framework.LabelLayer.LabelBaseLine;
import com.example.try_gameengine.framework.Sprite.MoveRageType;
import com.example.try_gameengine.framework.Sprite.RotationType;
import com.example.try_gameengine.remotecontroller.IRemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController;
import com.example.try_gameengine.remotecontroller.RemoteController.CommandType;
import com.example.try_gameengine.remotecontroller.RemoteController.RemoteContollerListener;
import com.example.try_gameengine.remotecontroller.custome.Custome4D2FCommand;
import com.example.try_gameengine.remotecontroller.custome.Custome4D2FCommandType;
import com.example.try_gameengine.remotecontroller.custome.Custome4D2FRemoteController;
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

public class ScaleScene extends EasyScene{
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;
	
	private Custom4D2FRemoteContollerListener custom4d2fRemoteContollerListener = new Custom4D2FRemoteContollerListener();
	
	private RectF userRectF = new RectF(100, 200, 200, 300);
	private RectF rotateCenterRectF = new RectF(500, 200, 600, 300);
	private RectF rectF = new RectF(100, 500, 200, 600);
	private float circleRadius = 50;
//	private PointF circleCenter = new PointF(550, 250);
//	private PointF pointF = new PointF(500, 500);
	private RectF rectF2 = new RectF(800, 200, 900, 300);
	private RectF rectF3 = new RectF(500, 500, 600, 600);
	private RectF rectF4 = new RectF(100, 800, 200, 900);
	private Sprite sprite = new Sprite();
	private LabelLayer dirMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer collisionMsgLayer = new LabelLayer(0.0f, 70.0f, false);
	private Sprite userRectMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rectRotateCenterMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rectMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite circleMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite pointMsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect2MsgLayer = new Sprite(0.0f, 50.0f, false);
	private Sprite rect3MsgLayer = new Sprite(0.0f, 50.0f, false);
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
		protected void onTouched(MotionEvent event) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void drawSelf(Canvas canvas, Paint paint) {
			// TODO Auto-generated method stub
			
		}
	};
	
	class MyA extends Activity{
		
	};
	
	class customRectLayer implements ILayer{

		@Override
		public void setPosition(float x, float y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void drawSelf(Canvas canvas, Paint paint) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public RectF getSmallViewRect() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setSmallViewRect(RectF smallViewRect) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void remove(ILayer layer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isAutoAdd() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void addWithLayerLevelIncrease(ILayer layer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addWithLayerLevelIncrease(ILayer layer, int increaseNum) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addWithOutLayerLevelIncrease(ILayer layer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addWithLayerLevel(ILayer layer, int layerLevel) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void addChild(ILayer layer) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ILayer getChild(int i) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ConcurrentLinkedQueue<ILayer> getLayers() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Iterator createIterator() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void moveAllChild(int offsetLayerLevel) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setParent(ILayer parent) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public ILayer getParent() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setInitWidth(int w) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setInitHeight(int h) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setWidth(int w) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setHeight(int h) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getWidth() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int getHeight() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public float getX() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public float getLeft() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public float getCenterX() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setX(float x) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public float getY() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public float getTop() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public float getCenterY() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setY(float y) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBitmapAndAutoChangeWH(Bitmap bitmap) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setBitmap(Bitmap bitmap) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Bitmap getBitmap() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RectF getDst() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getLayerLevel() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setLayerLevel(int layerLevel) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getAlpha() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setAlpha(int alpha) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Paint getPaint() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setPaint(Paint paint) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeFromParent() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeFromAuto() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getzPosition() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void setzPosition(int zPosition) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isTouching() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setTouching(boolean isTouching) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isPressed() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setPressed(boolean pressed) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isComposite() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setComposite(boolean isComposite) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PointF getLocationInScene() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setLocationInScene(PointF locationInScene) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PointF locationInLayer(float x, float y) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public PointF locationInSceneByCompositeLocation(
				float locationInLayerX, float locationInLayerY) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ILayer getRootLayer() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<ILayer> getLayersFromRootLayerToCurrentLayerInComposite() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public LayerParam getLayerParam() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setHidden(boolean isHidden) {
			// TODO Auto-generated method stub
			
		}
		
		
		@Override
		public Object clone() throws CloneNotSupportedException {
			// TODO Auto-generated method stub
			return super.clone();
		}

		@Override
		public RectF frameInSceneByCompositeLocation() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RectF getFrame() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RectF getFrameInScene() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setFrameInScene(RectF frameInScene) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public boolean isClipOutside() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setBackgroundColor(int backgroundColor) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void setFlag(int flag) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public int getFlag() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public void addFlag(int flag) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeFlag(int flag) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	private void setDectecAreas(){
		userRectDetectArea = new DetectAreaRect(userRectF);
		rectDetectArea = new DetectAreaRect(rectF);
//		circleDetectArea = new DetectAreaRound(circleCenter, circleRadius);
//		pointDetectArea = new DetectAreaPoint(pointF);
		
		SpriteDetectAreaHandler spriteDetectAreaHandler = new SpriteDetectAreaHandler();
		DetectArea a = new DetectAreaSpriteRect(new RectF(), new DetectAreaSpriteRect.SpriteRectListener() {
			
			@Override
			public RectF caculateSpriteRect() {
				// TODO Auto-generated method stub
				RectF rectF;
				if(sprite.getLocationInScene()!=null)
					rectF = new RectF(sprite.getLocationInScene().x, sprite.getLocationInScene().y, sprite.getLocationInScene().x + sprite.w, sprite.getLocationInScene().y + sprite.h);
				else
					rectF = sprite.getFrame();
				return rectF;
			}
			
			@Override
			public PointF caculateSpriteCenter() {
				// TODO Auto-generated method stub;
				PointF pointF;
				if(sprite.getLocationInScene()!=null)
					pointF = new PointF(sprite.getLocationInScene().x + sprite.w/2, sprite.getLocationInScene().y + sprite.h/2);
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
	
	public ScaleScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		isEnableRemoteController(true);
		Custome4D2FRemoteController remoteController = Custome4D2FRemoteController.createRemoteController();
		setRemoteController(remoteController);
		custom4d2fRemoteContollerListener.setCustom4D2FRemoteContollerListener(new Custome4D2FRemoteController.RemoteContollerListener() {
			
			@Override
			public void pressDown(List<Custome4D2FCommandType> commandTypes) {
				// TODO Auto-generated method stub
				
			}
		});
		remoteController.setRemoteContollerListener(custom4d2fRemoteContollerListener);
		
		setDectecAreas();
		
		userRectMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rectRotateCenterMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rectMsgLayer.setAnchorPoint(0.5f, 1.0f);
		circleMsgLayer.setAnchorPoint(0.5f, 1.0f);
		pointMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rect2MsgLayer.setAnchorPoint(0.5f, 1.0f);
//		userRectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		rectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		circleMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		pointMsgLayer.setAnchorPoint(0.5f, 0.0f);
		
		userRectMsgLayer.setPosition(userRectF.centerX(), userRectF.bottom);
		rectRotateCenterMsgLayer.setPosition(rotateCenterRectF.centerX(), rotateCenterRectF.bottom);
		rectMsgLayer.setPosition(rectF.centerX(), rectF.bottom);
//		circleMsgLayer.setPosition(circleCenter.x, circleCenter.y+circleRadius);
//		pointMsgLayer.setPosition(pointF.x, pointF.y + 50);
		circleMsgLayer.setPosition(rectF2.centerX(), rectF2.bottom);
		pointMsgLayer.setPosition(rectF3.centerX(), rectF3.bottom);
		rect2MsgLayer.setPosition(rectF4.centerX(), rectF4.bottom);
		
		userRectMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectRotateCenterMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		circleMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		pointMsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect2MsgLayer.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		
		userRectMsgLayer.setXscale(2.0f);
		rectRotateCenterMsgLayer.setXscale(2.0f);
		rectMsgLayer.setXscale(2.0f);
		circleMsgLayer.setXscale(2.0f);
		pointMsgLayer.setXscale(2.0f);
		rect2MsgLayer.setXscale(2.0f);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		
		userRectMsgLayer.setPaint(paint);
		rectRotateCenterMsgLayer.setPaint(paint);
		rectMsgLayer.setPaint(paint);
		circleMsgLayer.setPaint(paint);
		pointMsgLayer.setPaint(paint);
		rect2MsgLayer.setPaint(paint);
		
		Sprite child = new Sprite();
//		child.setPosition(userRectMsgLayer.getWidth(), userRectMsgLayer.getHeight());
		child.setPosition(0, 0);
		child.setXscale(0.2f);
		child.setYscale(0.2f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		userRectMsgLayer.addChild(child);
		
		child = new Sprite();
//		child.setPosition(userRectMsgLayer.getWidth(), userRectMsgLayer.getHeight());
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
		
		userRectMsgLayer.setPosition(userRectF.centerX(), userRectF.bottom);
//		player.frameTrig();
	}

	@Override
	public void doDraw(Canvas canvas) {
		// TODO Auto-generated method stub
//		sprite.drawSelf(canvas, null);
//		LayerManager.drawLayers(canvas, null);
		LayerManager.drawSceneLayers(canvas, null, sceneLayerLevel);
		
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
		
		userRectMsgLayer.drawSelf(canvas, null);
		rectRotateCenterMsgLayer.drawSelf(canvas, null);
		rectMsgLayer.drawSelf(canvas, null);
		circleMsgLayer.drawSelf(canvas, null);
		pointMsgLayer.drawSelf(canvas, null);
		rect2MsgLayer.drawSelf(canvas, null);
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
