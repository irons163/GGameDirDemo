package org.ggamedirdemo.scenemanager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.PorterDuff.Mode;
import android.view.MotionEvent;

import com.example.try_gameengine.framework.ButtonLayer;
import com.example.try_gameengine.framework.LabelLayer;
import com.example.try_gameengine.framework.Layer;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.scene.DialogScene;

public class GameOverDialogScene extends DialogScene{
	final Layer bgLayer = new Layer(BitmapUtil.gameover, BitmapUtil.gameover.getWidth(), BitmapUtil.gameover.getHeight(), false);
	final ButtonLayer restartButton = new ButtonLayer(0, 0, false);
	final LabelLayer labelLayer = new LabelLayer("hello", 0, 0, false);
	
	public GameOverDialogScene(Context context, String id) {
		super(context, id);
		// TODO Auto-generated constructor stub
		bgLayer.setPosition(0, bgLayer.getHeight());
		
		labelLayer.setTextSize(100);
		labelLayer.setPosition(500, 500);
		
		restartButton.setBitmapAndAutoChangeWH(BitmapUtil.restartBtn01);
		restartButton.setButtonBitmap(BitmapUtil.restartBtn01, BitmapUtil.restartBtn02, BitmapUtil.restartBtn01);
		restartButton.setPosition(CommonUtil.screenWidth/2.0f - restartButton.getWidth()/2.0f, CommonUtil.screenHeight/4.0f*3);
		restartButton.setOnClickListener(new ButtonLayer.OnClickListener() {
			
			@Override
			public void onClick(ButtonLayer buttonLayer) {
				// TODO Auto-generated method stub
				((GameActivity)GameOverDialogScene.this.context).sceneManager.previousWithExistedScenes();
			}
		});
	}
	
	@Override
	public void doDraw(Canvas canvas) {
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
	
	@Override
	public boolean onSceneTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onSceneTouchEvent(event);
	}
		
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return LayerManager.getInstance().onTouchSceneLayers(event, getLayerLevel());
	}
}
