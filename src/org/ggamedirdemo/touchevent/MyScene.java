package org.ggamedirdemo.touchevent;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.framework.ALayer;
import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.ITouchable;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.LabelLayer.AlignmentVertical;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.framework.TouchDispatcher;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FCommandType;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FRemoteContollerListener;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FRemoteController;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.DetectArea;
import com.example.try_gameengine.utils.DetectAreaPoint;
import com.example.try_gameengine.utils.DetectAreaRect;
import com.example.try_gameengine.utils.DetectAreaRound;
import com.example.try_gameengine.utils.DetectAreaSpriteRect;
import com.example.try_gameengine.utils.GameTimeUtil;
import com.example.try_gameengine.utils.SpriteDetectAreaHandler;



public class MyScene extends EasyScene{
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;
	
	private Custom4D2FRemoteContollerListener custom4d2fRemoteContollerListener = new Custom4D2FRemoteContollerListener();
	
	private RectF userRectF = new RectF(100, 200, 200, 300);
	private RectF rectF = new RectF(100, 500, 200, 600);
	private float circleRadius = 50;
	private PointF circleCenter = new PointF(550, 250);
	private PointF pointF = new PointF(500, 500);
	private Sprite sprite = new Sprite();
	private LabelLayer dirMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer collisionMsgLayer = new LabelLayer(0.0f, 70.0f, false);
	private LabelLayer userRectMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer rectMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer circleMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer pointMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private DetectArea userRectDetectArea;
	private DetectArea rectDetectArea;
	private DetectArea circleDetectArea;
	private DetectArea pointDetectArea;
	
	private void setDectecAreas(){
		userRectDetectArea = new DetectAreaRect(userRectF);
		rectDetectArea = new DetectAreaRect(rectF);
		circleDetectArea = new DetectAreaRound(circleCenter, circleRadius);
		pointDetectArea = new DetectAreaPoint(pointF);
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
	
	private void checkDetectAreasCollision(){
		if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, rectDetectArea)){
			collisionMsgLayer.setText("Collision RECT");
		}else if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, circleDetectArea)){
			collisionMsgLayer.setText("Collision CIRCLE");
		}else if(DetectArea.detectConditionWithTwoArea(userRectDetectArea, pointDetectArea)){
			collisionMsgLayer.setText("Collision POINT");
		}else{
			collisionMsgLayer.setText("");
		}
	}
	
	public MyScene(final Context context, String id, int level, int mode) {
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
		
		userRectMsgLayer.setAnchorPoint(0.5f, 1.0f);
		rectMsgLayer.setAnchorPoint(0.5f, 1.0f);
		circleMsgLayer.setAnchorPoint(0.5f, 1.0f);
		pointMsgLayer.setAnchorPoint(0.5f, 1.0f);
//		userRectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		rectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		circleMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		pointMsgLayer.setAnchorPoint(0.5f, 0.0f);
		
		userRectMsgLayer.setAlignmentVertical(AlignmentVertical.ALIGNMENT_TOP);
		rectMsgLayer.setAlignmentVertical(AlignmentVertical.ALIGNMENT_TOP);
		circleMsgLayer.setAlignmentVertical(AlignmentVertical.ALIGNMENT_TOP);
		pointMsgLayer.setAlignmentVertical(AlignmentVertical.ALIGNMENT_TOP);
		
		userRectMsgLayer.setText("USER RECTj");
		rectMsgLayer.setText("RECTj");
		circleMsgLayer.setText("CIRCLE");
		pointMsgLayer.setText("POINT");
		
		userRectMsgLayer.setPosition(userRectF.centerX(), userRectF.bottom);
		rectMsgLayer.setPosition(rectF.centerX(), rectF.bottom);
		circleMsgLayer.setPosition(circleCenter.x, circleCenter.y+circleRadius);
		pointMsgLayer.setPosition(pointF.x, pointF.y + 50);
		
		Paint paint = new Paint();
		paint.setColor(Color.BLUE);
		
		userRectMsgLayer.setPaint(paint);
		rectMsgLayer.setPaint(paint);
		circleMsgLayer.setPaint(paint);
		pointMsgLayer.setPaint(paint);
		
		ButtonLayer bg = new ButtonLayer(600, 600, true);
//		addChild(bg);
//		addAutoDraw(bg);
		touchableObjectMsgLabel.setText("MsgLabelText");
		addAutoDraw(touchableObjectMsgLabel);
		
		ButtonLayer buttonLayer1 = new ButtonLayer();
		
		buttonLayer1.setHeight(200);
		buttonLayer1.setWidth(400);
		buttonLayer1.setText("1");
		bg.addChild(buttonLayer1);
		TouchDispatcher.getInstance().addStandardTouchDelegate(buttonLayer1, 3);
		
		ButtonLayer buttonLayer2 = new ButtonLayer();
		
		buttonLayer2.setHeight(200);
		buttonLayer2.setWidth(400);
		buttonLayer2.setText("222");
		buttonLayer2.setPosition(200, 120);
		bg.addChild(buttonLayer2);
		TouchDispatcher.getInstance().addStandardTouchDelegate(buttonLayer2, 2);
		
		ButtonLayer buttonLayer3 = new ButtonLayer();
		
		buttonLayer3.setHeight(200);
		buttonLayer3.setWidth(400);
		buttonLayer3.setText("3");
		buttonLayer3.setPosition(-150, 120);
		bg.addChild(buttonLayer3);
		TouchDispatcher.getInstance().addStandardTouchDelegate(buttonLayer3, 1);
		
		TouchableObject touchableObject = new TouchableObject();
		TouchDispatcher.getInstance().addStandardTouchDelegate(touchableObject, 4);
		
		TouchableObject touchableObject2 = new TouchableObject();
		TouchDispatcher.getInstance().addTargetTouchDelegate(touchableObject2, 4);
		
		TouchableRectObject touchableRectObject = new TouchableRectObject();
		touchableRectObject.setRect(new RectF(0, 0, 50, 50));
		TouchDispatcher.getInstance().addTargetTouchDelegate(touchableRectObject, 5);
		
	}
	
	LabelLayer touchableObjectMsgLabel = new LabelLayer(150f, 100f, false);
	
	class TouchableObject implements ITouchable{
		
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			// TODO Auto-generated method stub
			if(event.getAction() == MotionEvent.ACTION_DOWN){
				touchableObjectMsgLabel.setText("TouchableObject onStandarTouch");
				return true;
			}
			return false;
		}

		@Override
		public boolean onTouchBegan(MotionEvent event) {
			// TODO Auto-generated method stub
			touchableObjectMsgLabel.setText("TouchableObject onTouchBegan");
			return true;
		}

		@Override
		public void onTouchMoved(MotionEvent event) {
			// TODO Auto-generated method stub
			touchableObjectMsgLabel.setText("TouchableObject onTouchMoved");
		}

		@Override
		public void onTouchEnded(MotionEvent event) {
			// TODO Auto-generated method stub
			touchableObjectMsgLabel.setText("TouchableObject onTouchEnded");
		}

		@Override
		public void onTouchCancelled(MotionEvent event) {
			// TODO Auto-generated method stub
			touchableObjectMsgLabel.setText("TouchableObject onTouchCancelled");
		}
	}
	
	class TouchableRectObject extends TouchableObject{
		private RectF rectf = new RectF();
		
		public void setRect(RectF rectf){
			this.rectf.set(rectf);
		}
		
		@Override
		public boolean onTouchBegan(MotionEvent event) {
			// TODO Auto-generated method stub
			if(rectf.contains(event.getX(), event.getY())){
				touchableObjectMsgLabel.setText("TouchableRectObject onTouchBegan");
				return true;
			}
			touchableObjectMsgLabel.setText("TouchableRectObject onTouchBegan fail");
			return false;
		}

		@Override
		public void onTouchMoved(MotionEvent event) {
			// TODO Auto-generated method stub
			touchableObjectMsgLabel.setText("TouchableRectObject onTouchMoved");
		}

		@Override
		public void onTouchEnded(MotionEvent event) {
			// TODO Auto-generated method stub
			touchableObjectMsgLabel.setText("TouchableRectObject onTouchEnded");
		}

		@Override
		public void onTouchCancelled(MotionEvent event) {
			// TODO Auto-generated method stub
			touchableObjectMsgLabel.setText("TouchableRectObject onTouchCancelled");
		}
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
		checkDetectAreasCollision();
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

	public void setMode(){
		
	}
	
	@Override
	public boolean onSceneTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onSceneTouchEvent(event);
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
		canvas.drawRect(rectF, paint);
		canvas.drawCircle(circleCenter.x, circleCenter.y, circleRadius, paint);
		canvas.drawPoint(pointF.x, pointF.y, paint);
		
		userRectMsgLayer.drawSelf(canvas, null);
		rectMsgLayer.drawSelf(canvas, null);
		circleMsgLayer.drawSelf(canvas, null);
		pointMsgLayer.drawSelf(canvas, null);
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
