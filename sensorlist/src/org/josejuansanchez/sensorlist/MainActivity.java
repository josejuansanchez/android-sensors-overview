package org.josejuansanchez.sensorlist;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private SensorManager mSensorManager;
	private List<Sensor> mSensors;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);	
        
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(new SensorListAdapter(this, android.R.layout.simple_list_item_1, mSensors));
		
	}
	
    private class SensorListAdapter extends ArrayAdapter<Sensor> {
        public SensorListAdapter(Context context, int textViewResourceId, List<Sensor> sensors) {
            super(context, textViewResourceId, sensors);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final Sensor selectedSensor = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, null);
            }

            ((TextView) convertView).setText(selectedSensor.getName());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                	Intent intent = new Intent(MainActivity.this, SensorInfoActivity.class);
                	intent.putExtra("SENSOR_TYPE", mSensors.get(position).getType());
                	startActivity(intent);
                }
            });
            return convertView;
        }
    }	
		
}

