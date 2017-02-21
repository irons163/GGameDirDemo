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
import android.widget.Toast;

import com.example.try_gameengine.framework.ALayer;
import com.example.try_gameengine.framework.GameView;
import com.example.try_gameengine.framework.IGameController;
import com.example.try_gameengine.framework.IGameModel;
import com.example.try_gameengine.framework.ILayer;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Sprite;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FCommandType;
import com.example.try_gameengine.remotecontroller.custome.Custom4D2FRemoteController;
import com.example.try_gameengine.scene.EasyScene;
import com.example.try_gameengine.utils.GameTimeUtil;

public class BasicLayerScene extends EasyScene{
	private int gameTime;
	
	private GameTimeUtil gameTimeUtil;
	
	private Custom4D2FRemoteContollerListener custom4d2fRemoteContollerListener = new Custom4D2FRemoteContollerListener();
	
	private RectF userRectF = new RectF(100, 100, 200, 200);
	private RectF rotateCenterRectF = new RectF(300, 100, 400, 200);
	private RectF rectF = new RectF(100, 300, 200, 400);
	private RectF rectF2 = new RectF(500, 100, 600, 200);
	private RectF rectF3 = new RectF(300, 300, 400, 400);
	private RectF rectF4 = new RectF(500, 300, 600, 400);
	private RectF rectF5 = new RectF(100, 500, 200, 600);
	private RectF rectF6 = new RectF(300, 500, 400, 600);
	private RectF rectF7 = new RectF(500, 500, 600, 600);
	private RectF rectF8 = new RectF(100, 700, 200, 800);
	private RectF rectF9 = new RectF(300, 700, 400, 800);
	private RectF rectF10 = new RectF(500, 700, 600, 800);
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

	public BasicLayerScene(final Context context, String id, int level, int mode) {
		super(context, id, level, mode);
		// TODO Auto-generated constructor stub
		setWidth(500);
		setHeight(500);
		setPosition(200, 200);
		setBackgroundColor(Color.WHITE);
		
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
		child.setPosition(0, 0);
		child.setXscale(1.23f);
		child.setYscale(1.23f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.setRotation(5);
		rect1Layer.addChild(child);
		rect1Layer.setRotation(45);
		rect1Layer.setBackgroundColor(Color.BLUE);

		child = new Sprite();
		child.setPosition(0, 0);
		child.setAnchorPoint(-0.5f, -0.1f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect1Layer.getChildAt(0).addChild(child);
		
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(1.23f);
		child.setYscale(1.23f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.setRotation(5);
		rectRotateCenterMsgLayer.addChild(child);
		rectRotateCenterMsgLayer.setRotation(45);
		rectRotateCenterMsgLayer.setIsClipOutside(true);
		rectRotateCenterMsgLayer.setBackgroundColor(Color.BLUE);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setAnchorPoint(-0.5f, -0.1f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectRotateCenterMsgLayer.getChildAt(0).addChild(child);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(1.23f);
		child.setYscale(1.23f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.setRotation(5);
		circleMsgLayer.addChild(child);
		circleMsgLayer.setRotation(45);
		circleMsgLayer.setIsClipOutside(true);
		circleMsgLayer.setBackgroundColor(Color.BLUE);
		child.setIsClipOutside(true);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setAnchorPoint(-0.5f, -0.1f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		circleMsgLayer.getChildAt(0).addChild(child);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(1.23f);
		child.setYscale(1.23f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.setRotation(5);
		rectMsgLayer.addChild(child);
		rectMsgLayer.setIsClipOutside(true);
		rectMsgLayer.setRotation(45);
		rectMsgLayer.setBackgroundColor(Color.BLUE);
		child.setIsClipOutside(true);
		
		child.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Mid touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setAnchorPoint(-0.5f, -0.1f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rectMsgLayer.getChildAt(0).addChild(child);
		
		child.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Top touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		rectMsgLayer.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Bottom touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
		LayerParam layerParam = new LayerParam();
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		pointMsgLayer.addChild(child);
		Layer layer = new Layer();
		layer.setBackgroundColor(Color.RED);
		layer.setzPosition(-1);
		pointMsgLayer.setAutoSizeByChildren(layer);
		
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
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(1.23f);
		child.setYscale(1.23f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.setRotation(5);
		rect3MsgLayer.addChild(child);
		child.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Mid touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		rect3MsgLayer.setRotation(45);
		rect3MsgLayer.setBackgroundColor(Color.BLUE);
		Layer autoSizelayer = new Layer();
		autoSizelayer.setBackgroundColor(Color.YELLOW);
		autoSizelayer.setzPosition(-1);
		rect3MsgLayer.setAutoSizeByChildren(autoSizelayer);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setAnchorPoint(-0.5f, -0.1f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect3MsgLayer.getChildAt(0).addChild(child);
		
		child.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Top touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		rect3MsgLayer.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Bottom touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		autoSizelayer.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "AutoSizeLayer touch", Toast.LENGTH_SHORT).show();
			}
		});		
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setXscale(1.23f);
		child.setYscale(1.23f);
		child.setAnchorPoint(0.5f, 0.5f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		child.setRotation(5);
		rect4MsgLayer.addChild(child);
		rect4MsgLayer.setIsClipOutside(true);
		child.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Mid touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		rect4MsgLayer.setRotation(45);
		rect4MsgLayer.setBackgroundColor(Color.BLUE);
		autoSizelayer = new Layer();
		autoSizelayer.setBackgroundColor(Color.YELLOW);
		autoSizelayer.setzPosition(-1);
		rect4MsgLayer.setAutoSizeByChildren(autoSizelayer);
		
		child = new Sprite();
		child.setPosition(0, 0);
		child.setAnchorPoint(-0.5f, -0.1f);
		child.setBitmapAndFrameColAndRowNumAndAutoWH(BitmapUtil.hamster, 7, 2);
		rect4MsgLayer.getChildAt(0).addChild(child);
		
		child.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Top touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		rect4MsgLayer.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "Bottom touch", Toast.LENGTH_SHORT).show();
			}
		});
		
		autoSizelayer.setOnLayerClickListener(new ALayer.OnLayerClickListener() {
			
			@Override
			public void onClick(ILayer layer) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "AutoSizeLayer touch", Toast.LENGTH_SHORT).show();
			}
		});	
		
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
		rect5MsgLayer.addChild(child);
		
		child = new Sprite();
		child.setBitmapAndAutoChangeWH(BitmapUtil.fireball);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		rect6MsgLayer.addChild(child);
		
		child = new Sprite();
		child.setBitmapAndAutoChangeWH(BitmapUtil.fireball);
		child.setXscale(0.3f);
		child.setYscale(0.3f);
		child.setAnchorPoint(0.5f, 0.5f);
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
		
		
		addChild(rect1Layer);
		addChild(rectRotateCenterMsgLayer);
		addChild(rectMsgLayer);
		addChild(circleMsgLayer);
		addChild(pointMsgLayer);
		addChild(rect2MsgLayer);
		addChild(rect3MsgLayer);
		addChild(rect4MsgLayer);
		addChild(rect5MsgLayer);
		addChild(rect6MsgLayer);
		addChild(rect7MsgLayer);
		addChild(rect8MsgLayer);
		
		rect1Layer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rectRotateCenterMsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rectMsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		circleMsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		pointMsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rect2MsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rect3MsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rect4MsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rect5MsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rect6MsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rect7MsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
		rect8MsgLayer.setRotationType(RotationType.ROTATE_WITH_CENTER);
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
		LayerManager.getInstance().drawSceneLayersForNegativeZOrder(canvas, null, sceneLayerLevel);
		
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
		
		LayerManager.getInstance().drawSceneLayersForOppositeZOrder(canvas, null, sceneLayerLevel);
	}

	@Override
	public boolean onSceneTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onSceneTouchEvent(event);
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
