package org.ggamedirdemo.extension_module.skill_view;

import android.animation.AnimatorSet.Builder;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.try_gameengine.framework.BitmapUtil;
import com.example.try_gameengine.framework.LayerManager;
import com.example.try_gameengine.scene.Scene;
import com.example.try_gameengine.scene.SceneBuilder;
import com.example.try_gameengine.scene.SceneManager;
import com.example.try_gameengine.stage.Stage;

public class MainActivity extends Stage {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		
		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		CommonUtil.screenHeight = dm.heightPixels;
		CommonUtil.screenWidth = dm.widthPixels;

//		AudioUtil.init(this);

		CommonUtil.statusBarHeight = CommonUtil.getStatusBarHeight(this);
		CommonUtil.screenHeight -= CommonUtil.statusBarHeight;

		BitmapUtil.initBitmap(this);
		BitmapUtil.initBitmapForTest();
		
		initStage();
	}

	@Override
	public SceneManager initSceneManager() {
		// TODO Auto-generated method stub
		/*
		SceneManager sceneManager = new SceneManager();
		LayerManager.getInstance().setLayerBySenceIndex(0);
		sceneManager.addScene(new MyScence(this, "game"));
		sceneManager.startScene(0);
		*/
		
		SceneManager sceneManager = SceneManager.getInstance();
		SceneBuilder sceneBuilder = new SceneBuilder(0) {
			
			@Override
			public Scene createScene(int sceneIndex) {
				// TODO Auto-generated method stub
				MyScence myScence = new MyScence(MainActivity.this, "game");
				return myScence;
			}
		};
		
		sceneManager.addScene(sceneBuilder);
		sceneManager.startScene(0);
		
		return sceneManager;
	}
	

}


