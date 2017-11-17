package org.ggamedirdemo.layer;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.ShapeLayer;
import com.example.try_gameengine.framework.ShapeLayer.CircleShape;
import com.example.try_gameengine.framework.ShapeLayer.PolygonShape;
import com.example.try_gameengine.framework.ShapeLayer.RectShape;
import com.example.try_gameengine.framework.ShapeLayer.Shape;
import com.example.try_gameengine.framework.ShapeLayer.Shape.ShapeParam;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FCommandType;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FRemoteController;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.GameTimeUtil;

public class CollisionScene extends EasyScene{
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;
	
	private Custom4D2FRemoteContollerListener custom4d2fRemoteContollerListener = new Custom4D2FRemoteContollerListener();
	
	private RectShape userRectF;
	private RectF rectF = new RectF(100, 500, 200, 600);
	private float circleRadius = 50;
	private PointF circleCenter = new PointF(550, 250);
	private PointF pointF = new PointF(500, 500);
	private Sprite sprite = new Sprite();
	private LabelLayer dirMsgLayer = new LabelLayer(0.0f, 50.0f, false);
	private LabelLayer collisionMsgLayer = new LabelLayer(0.0f, 70.0f, false);
	private LabelLayer userRectMsgLayer = new LabelLayer(0.0f, 50.0f, false);
//	private LabelLayer rectMsgLayer = new LabelLayer(0.0f, 50.0f, false);
//	private LabelLayer circleMsgLayer = new LabelLayer(0.0f, 50.0f, false);
//	private LabelLayer pointMsgLayer = new LabelLayer(0.0f, 50.0f, false);
//	private DetectArea userRectDetectArea;
//	private DetectArea rectDetectArea;
//	private DetectArea circleDetectArea;
//	private DetectArea pointDetectArea;
	
	private void setCollisionRect(){
		sprite.setBitmapAndAutoChangeWH(BitmapUtil.redPoint);
//		sprite.setCollisionOffsetXY(30, 30);
		sprite.setCollisionRectFEnable(true);
	}	
	
	private void checkDetectAreasCollision(){
		if(RectF.intersects(userRectF.getShapeBounds(), rectF)){
			collisionMsgLayer.setText("Collision RectF");
			return;
		}else{
			collisionMsgLayer.setText("");
		}
		
		CircleShape circle = new CircleShape();
		circle.setCenter(circleCenter.x, circleCenter.y, circleRadius);
		
		Path path1 = new Path();
		path1.addCircle(10, 10, 4, Path.Direction.CW);
		Path path2 = new Path();
		path2.addCircle(15, 15, 8, Path.Direction.CW);

		Region region1 = new Region();
		Rect rect = new Rect();
		userRectF.getShapeBounds().roundOut(rect);
		region1.set(rect);
		Region region2 = new Region();
		circle.getShapeBounds().roundOut(rect);
		region2.setPath(circle.getPath(), new Region(rect));

		if (!region1.quickReject(region2) && region1.op(region2, Region.Op.INTERSECT)) {
		    // Collision!
			collisionMsgLayer.setText("Collision Circle");
			return;
		}else{
			collisionMsgLayer.setText("");
		}
		
		if(userRectF.getShapeBounds().contains(pointF.x, pointF.y)){
			collisionMsgLayer.setText("Collision PointF");
			return;
		}else{
			collisionMsgLayer.setText("");
		}
		
	}
	
	public CollisionScene(final Context context, String id, int level, int mode) {
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
		
//		setDectecAreas();
		setCollisionRect();
		
		userRectF = new RectShape();
		userRectF.setRectF(new RectF(100, 200, 200, 300));
		
		userRectMsgLayer.setAnchorPoint(0.5f, 1.0f);
//		rectMsgLayer.setAnchorPoint(0.5f, 1.0f);
//		circleMsgLayer.setAnchorPoint(0.5f, 1.0f);
//		pointMsgLayer.setAnchorPoint(0.5f, 1.0f);
//		userRectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		rectMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		circleMsgLayer.setAnchorPoint(0.5f, 0.0f);
//		pointMsgLayer.setAnchorPoint(0.5f, 0.0f);
		
//		userRectMsgLayer.setLabelBaseLine(LabelBaseLine.BASELINE_FOR_TEXT_TOP);
//		rectMsgLayer.setLabelBaseLine(LabelBaseLine.BASELINE_FOR_TEXT_TOP);
//		circleMsgLayer.setLabelBaseLine(LabelBaseLine.BASELINE_FOR_TEXT_TOP);
//		pointMsgLayer.setLabelBaseLine(LabelBaseLine.BASELINE_FOR_TEXT_TOP);
//		
//		userRectMsgLayer.setText("USER RECTj");
//		rectMsgLayer.setText("RECTj");
//		circleMsgLayer.setText("CIRCLE");
//		pointMsgLayer.setText("POINT");
//		
//		userRectMsgLayer.setPosition(userRectF.centerX(), userRectF.bottom);
//		rectMsgLayer.setPosition(rectF.centerX(), rectF.bottom);
//		circleMsgLayer.setPosition(circleCenter.x, circleCenter.y+circleRadius);
//		pointMsgLayer.setPosition(pointF.x, pointF.y + 50);
//		
//		Paint paint = new Paint();
//		paint.setColor(Color.BLUE);
//		
//		userRectMsgLayer.setPaint(paint);
//		rectMsgLayer.setPaint(paint);
//		circleMsgLayer.setPaint(paint);
//		pointMsgLayer.setPaint(paint);
		
		ShapeLayer shapeLayer = new ShapeLayer();
		shapeLayer.setSize(100, 100);
		shapeLayer.setPosition(550, 500);
		CircleShape circleShape = new ShapeLayer.CircleShape();
		circleShape.setCenter(50, 50, 10);
		circleShape.getPaint().setColor(Color.RED);
		circleShape.getPaint().setStyle(Style.FILL);
		shapeLayer.setShape(circleShape);
		shapeLayer.setBackgroundColor(Color.YELLOW);
		addChild(shapeLayer);
		
		ShapeLayer shapeLayer2 = new ShapeLayer();
		shapeLayer2.setSize(100, 100);
		shapeLayer2.setPosition(550, 600);
		CircleShape circleShape2 = new ShapeLayer.CircleShape();
		circleShape2.setCenter(50, 50, 10);
		circleShape2.getPaint().setColor(Color.RED);
		circleShape2.getPaint().setStyle(Style.FILL);
		ShapeParam shapeParam = new ShapeParam();
		shapeParam.setEnabledPercentageSizeW(true);
		shapeParam.setPercentageW(1f);
		shapeParam.setEnabledPercentageSizeH(true);
		shapeParam.setPercentageH(1f);
		circleShape2.setShapeParam(shapeParam);
		shapeLayer2.setShape(circleShape2);
		shapeLayer2.shapeFitToSize();
		shapeLayer2.setBackgroundColor(Color.YELLOW);
		shapeLayer2.setWidth(200);
		shapeLayer2.setHeight(150);
		addChild(shapeLayer2);
		
		ShapeLayer shapeLayer3 = new ShapeLayer();
		shapeLayer3.setPosition(550, 800);
		CircleShape circleShape3 = new ShapeLayer.CircleShape();
		circleShape3.setCenter(50, 50, 10);
		circleShape3.getPaint().setColor(Color.RED);
		circleShape3.getPaint().setStyle(Style.FILL);
		shapeParam = new ShapeParam();
		shapeParam.setEnabledPercentageSizeW(true);
		shapeParam.setPercentageW(1f);
		shapeParam.setEnabledPercentagePositionX(true);
		shapeParam.setPercentageX(0.5f);
		shapeParam.setEnabledPercentagePositionY(true);
		shapeParam.setPercentageY(0.5f);
		circleShape3.setShapeParam(shapeParam);
		shapeLayer3.setShape(circleShape3);
		shapeLayer3.shapeFitToSize();
		shapeLayer3.setBackgroundColor(Color.YELLOW);
		addChild(shapeLayer3);
		
		ShapeLayer shapeLayer4 = new ShapeLayer();
		shapeLayer4.setPosition(550, 850);
		
		CircleShape circleShape4 = new ShapeLayer.CircleShape();
		circleShape4.setCenter(50, 50, 10);
		circleShape4.getPaint().setColor(Color.RED);
		circleShape4.getPaint().setStyle(Style.FILL);
		shapeParam = new ShapeParam();
		shapeParam.setEnabledPercentageSizeW(true);
		shapeParam.setPercentageW(1f);
		shapeParam.setEnabledPercentageSizeH(true);
		shapeParam.setPercentageH(1f);
		shapeParam.setEnabledPercentagePositionX(true);
		shapeParam.setPercentageX(0.5f);
		shapeParam.setEnabledPercentagePositionY(true);
		shapeParam.setPercentageY(0.5f);
		circleShape4.setShapeParam(shapeParam);
		
		shapeLayer4.setShape(circleShape4);
		shapeLayer4.shapeFitToSize();
		shapeLayer4.setBackgroundColor(Color.YELLOW);
		shapeLayer4.setWidth(100);
		shapeLayer4.setHeight(100);
		addChild(shapeLayer4);
		
		ShapeLayer shapeLayer5 = new ShapeLayer();
		shapeLayer5.setPosition(550, 950);
		PolygonShape polygonShape = new ShapeLayer.PolygonShape();
		polygonShape.setCenter(50, 50);
		Path path  = new Path();
		path.moveTo(20, 20);
		path.lineTo(80, 40);
		path.lineTo(120, 100);
		path.lineTo(10, 80);
		path.close();
		polygonShape.setPath(path);
		polygonShape.getPaint().setColor(Color.RED);
		polygonShape.getPaint().setStyle(Style.FILL);
		shapeParam = new ShapeParam();
		shapeParam.setEnabledPercentageSizeW(true);
		shapeParam.setPercentageW(1f);
		shapeParam.setEnabledPercentageSizeH(true);
		shapeParam.setPercentageH(1f);
		shapeParam.setEnabledPercentagePositionX(true);
		shapeParam.setPercentageX(0.5f);
		shapeParam.setEnabledPercentagePositionY(true);
		shapeParam.setPercentageY(0.5f);
		polygonShape.setShapeParam(shapeParam);
		shapeLayer5.setShape(polygonShape);
		shapeLayer5.shapeFitToSize();
		shapeLayer5.setBackgroundColor(Color.YELLOW);
		shapeLayer5.setWidth(150);
		shapeLayer5.setHeight(150);
		addChild(shapeLayer5);
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
	
	boolean isMoveing = false;
	private void checkPlayerMoved(){
		
		if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.LEFT){
			userRectF.getPath().offset(-5, 0);
			dirMsgLayer.setText("LEFT");
		}else if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.RIGHT){
			userRectF.getPath().offset(5, 0);
			dirMsgLayer.setText("RIGHT");
		}else if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.UP){
			userRectF.getPath().offset(0, -5);
			dirMsgLayer.setText("UP");
		}else if(custom4d2fRemoteContollerListener.getCurrentMove() == Custom4D2FRemoteContollerListener.DOWN){
			userRectF.getPath().offset(0, 5);
			dirMsgLayer.setText("DOWN");
		}else{
			dirMsgLayer.setText("");
		}
		
		userRectMsgLayer.setPosition(userRectF.getShapeBounds().centerX(), userRectF.getShapeBounds().bottom);
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
		

		
//		canvas.drawRect(userRectF, paint);
		userRectF.draw(canvas, paint);
		canvas.drawRect(rectF, paint);
		canvas.drawCircle(circleCenter.x, circleCenter.y, circleRadius, paint);
		canvas.drawPoint(pointF.x, pointF.y, paint);
		
		userRectMsgLayer.drawSelf(canvas, null);
//		rectMsgLayer.drawSelf(canvas, null);
//		circleMsgLayer.drawSelf(canvas, null);
//		pointMsgLayer.drawSelf(canvas, null);
		
		sprite.drawSelf(canvas, paint);
		canvas.drawRect(sprite.getFrame(), paint);
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
