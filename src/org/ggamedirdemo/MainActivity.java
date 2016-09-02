package org.ggamedirdemo;

import org.ggamedirdemo.remote_controller.GameActivity;
import org.w3c.dom.ls.LSException;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class MainActivity extends Activity {

	
	private String[] strs = new String[]{
			 "RemoteController", "RemoteController4D2F", "Stage", "DetectArea", "Layer"
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ListView listView = (ListView) findViewById(R.id.listView1);
		listView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strs));
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = null;
				
				switch (position) {
				case 0:
					intent = new Intent(MainActivity.this, org.ggamedirdemo.remote_controller.MainActivity.class);
					break;
				case 1:
					intent = new Intent(MainActivity.this, org.ggamedirdemo.remote_controller.custom4D2F.GameActivity.class);
					break;
				case 2:
					intent = new Intent(MainActivity.this, org.ggamedirdemo.stage.MainActivity.class);
					break;
				case 3:
					intent = new Intent(MainActivity.this, org.ggamedirdemo.detect_area.GameActivity.class);
					break;
				case 4:
					intent = new Intent(MainActivity.this, org.ggamedirdemo.layer.GameActivity.class);
					break;
				default:
					break;
				}
				
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}