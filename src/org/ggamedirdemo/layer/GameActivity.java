package org.ggamedirdemo.layer;

import org.ggamedirdemo.R;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.try_gameengine.framework.Config;
import com.example.try_gameengine.framework.Config.DestanceType;
import com.example.try_gameengine.scene.Scene;
import com.example.try_gameengine.scene.SceneManager;
import com.example.try_gameengine.stage.Stage;
import com.example.try_gameengine.stage.StageManager;


public class GameActivity extends Stage{
	SceneManager sceneManager;
	
	private String[] strs = new String[]{
			"Layers", "MultiDetectArea", "ScaleDetectArea", "SpriteAction", "PositionLayers", "ChildLayers", "ClipOutSideLayers", "BasicLayers", "Transform", "HUDLayers", "SpriteDetectArea", "Collision"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, strs));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = null;
				
				switch (position) {
				case 0:
					sceneManager.startScene(0);
					break;
				case 1:
					sceneManager.startScene(1);
					break;
				case 2:
					sceneManager.startScene(2);
					break;
				case 3:
					sceneManager.startScene(3);
					break;
				case 4:
					sceneManager.startScene(4);
					break;
				case 5:
					sceneManager.startScene(5);
					break;
				case 6:
					sceneManager.startScene(6);
					break;
				case 7:
					sceneManager.startScene(7);
					break;
				case 8:
					sceneManager.startScene(8);
					break;
				case 9:
					sceneManager.startScene(9);
					break;
				case 10:
					sceneManager.startScene(10);
//					intent = new Intent(GameActivity.this, org.ggamedirdemo.stage.MainActivity.class);
//					startActivity(intent);
					break;
				default:
					break;
				}
			}
		});
		
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
	
//		LayerManager.getInstance().setLayerBySenceIndex(0);
		Scene scene = new TransformScene(this, "a", 0, Scene.RESUME);
//		LayerManager.getInstance().setLayerBySenceIndex(1);
//		Scene scene2 = new MultiDetectAreaScene(this, "b", 1, Scene.RESTART);
		
		sceneManager = SceneManager.getInstance();
		sceneManager.addScene(scene);
//		sceneManager.addScene(scene2);
		sceneManager.addScene(MultiDetectAreaScene.class, this, "b", 1, Scene.RESTART);
		sceneManager.addScene(ScaleScene.class, this, "c", 2, Scene.RESTART);
		sceneManager.addScene(SpriteActionScene.class, this, "d", 3, Scene.RESTART);
		sceneManager.addScene(PositionScene.class, this, "e", 4, Scene.RESTART);
		sceneManager.addScene(ChildClipOutSideScene.class, this, "f", 6, Scene.RESTART);
		sceneManager.addScene(BasicLayerScene.class, this, "g", 7, Scene.RESTART);
		sceneManager.addScene(TransformScene.class, this, "g", 8, Scene.RESTART);
		sceneManager.addScene(HUDLayerScene.class, this, "g", 9, Scene.RESTART);
		sceneManager.addScene(CollisionScene.class, this, "g", 10, Scene.RESTART);
		
//		sceneManager.startScene(0);
		
		return sceneManager;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		super.onBackPressed();
		if(!sceneManager.previousWithExistedScenes()){
			super.onBackPressed();
			SceneManager.getInstance().reset();
		}
//		sceneManager.previousAndLeaveWhenNoPrevious();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return super.onTouchEvent(event);
	}
}
