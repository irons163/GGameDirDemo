package org.ggamedirdemo.remote_controller.custom4D2F;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;

import com.example.try_gameengine.framework.Config;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.framework.Config.DestanceType;
import com.example.try_gameengine.scene.Scene;
import com.example.try_gameengine.scene.SceneManager;
import com.example.try_gameengine.stage.Stage;
import com.example.try_gameengine.stage.StageManager;


public class GameActivity extends Stage{
	SceneManager sceneManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;
		CommonUtil.statusBarHeight = CommonUtil.getStatusBarHeight(this);
		CommonUtil.screenHeight -= CommonUtil.statusBarHeight;
		
		Config.enableFPSInterval = true;
		Config.fps = 40;
		Config.showFPS = true;
		Config.destanceType = DestanceType.ScreenPersent;
		Config.currentScreenWidth = CommonUtil.screenWidth;
		Config.currentScreenHeight = CommonUtil.screenHeight;
		
		BitmapUtil.initBitmap(this);
		
		StageManager.init(this);
		initStage();
	}
	
	@Override
	public SceneManager initSceneManager() {
		// TODO Auto-generated method stub
		if(CommonUtil.screenHeight<=0){
			DisplayMetrics dm = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(dm);
			
			CommonUtil.screenHeight = dm.heightPixels;
			CommonUtil.screenWidth = dm.widthPixels;
			CommonUtil.statusBarHeight = CommonUtil.getStatusBarHeight(this);
			CommonUtil.screenHeight -= CommonUtil.statusBarHeight;
		}
		BitmapUtil.initBitmap(this);
	
		LayerManager.getInstance().setLayerBySenceIndex(0);
		Scene scene = new MyScene(this, "a", 1, Scene.RESUME);
//		LayerManager.getInstance().setLayerBySenceIndex(1);
//		Scene scene2 = new MyScene2(this, "b", 2, Scene.RESTART);
		
		sceneManager = SceneManager.getInstance();
		sceneManager.addScene(scene);
//		sceneManager.addScene(scene2);
		
		sceneManager.startScene(0);
		
		return sceneManager;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		
		if(!sceneManager.previousWithExistedScenes())
			super.onBackPressed();
//		sceneManager.previousAndLeaveWhenNoPrevious();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
}
