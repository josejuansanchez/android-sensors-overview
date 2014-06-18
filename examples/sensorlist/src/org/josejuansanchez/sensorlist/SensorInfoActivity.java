package org.josejuansanchez.sensorlist;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class SensorInfoActivity extends Activity implements SensorEventListener {
	private static final String THETA = "\u0398";
	private static final String ACCELERATION_UNITS = "m/s\u00B2";

	private SensorManager mSensorManager;
	private Sensor mSensor;
    
	private TextView mName;
	private TextView mType;
	private TextView mMaxRange;
	private TextView mMinDelay;
	private TextView mPower;
	private TextView mResolution;
	private TextView mVendor;
	private TextView mVersion;
	private TextView mAccuracy;
	private TextView mTimestampLabel;
	private TextView mTimestamp;
	private TextView mTimestampUnits;
	private TextView mDataLabel;
	private TextView mDataUnits;
	private TextView mXaxis;
	private TextView mXaxisLabel;
	private TextView mYaxis;
	private TextView mYaxisLabel;
	private TextView mZaxis;
	private TextView mZaxisLabel;
	private TextView mSingleValue;
	private TextView mCosLabel;
	private TextView mCos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		setContentView(R.layout.sensor_view);
		
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);	

		// Get the selected sensor type from the intent
		Intent intent = getIntent();
		int sensorType = intent.getIntExtra("SENSOR_TYPE", 0);
		mSensor = mSensorManager.getDefaultSensor(sensorType);
		
		mName = (TextView) findViewById(R.id.name);
		mType = (TextView) findViewById(R.id.type);
		mMaxRange = (TextView) findViewById(R.id.maxRange);
		mMinDelay = (TextView) findViewById(R.id.minDelay);
		mPower = (TextView) findViewById(R.id.power);
		mResolution = (TextView) findViewById(R.id.resolution);
		mVendor = (TextView) findViewById(R.id.vendor);
		mVersion = (TextView) findViewById(R.id.version);
		mAccuracy = (TextView) findViewById(R.id.accuracy);
		mTimestampLabel = (TextView) findViewById(R.id.timestampLabel);
		mTimestamp = (TextView) findViewById(R.id.timestamp);
		mTimestampUnits = (TextView) findViewById(R.id.timestampUnits);
		mDataLabel = (TextView) findViewById(R.id.dataLabel);
		mDataUnits = (TextView) findViewById(R.id.dataUnits);
		mXaxis = (TextView) findViewById(R.id.xAxis);
		mXaxisLabel = (TextView) findViewById(R.id.xAxisLabel);
		mYaxis = (TextView) findViewById(R.id.yAxis);
		mYaxisLabel = (TextView) findViewById(R.id.yAxisLabel);
		mZaxis = (TextView) findViewById(R.id.zAxis);
		mZaxisLabel = (TextView) findViewById(R.id.zAxisLabel);
		mSingleValue = (TextView) findViewById(R.id.singleValue);
		mCosLabel = (TextView) findViewById(R.id.cosLabel);
		mCos = (TextView) findViewById(R.id.cos);
     
		findViewById(R.id.delayFastest).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSensorManager.unregisterListener(SensorInfoActivity.this);
				mSensorManager.registerListener(SensorInfoActivity.this,
						mSensor, 
						SensorManager.SENSOR_DELAY_FASTEST);
			}
		});
        
		findViewById(R.id.delayGame).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSensorManager.unregisterListener(SensorInfoActivity.this);
				mSensorManager.registerListener(SensorInfoActivity.this,
						mSensor, 
						SensorManager.SENSOR_DELAY_GAME);
			}
		});
        
		findViewById(R.id.delayNormal).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSensorManager.unregisterListener(SensorInfoActivity.this);
				mSensorManager.registerListener(SensorInfoActivity.this,
						mSensor, 
						SensorManager.SENSOR_DELAY_NORMAL);
			}
		});
        
		findViewById(R.id.delayUi).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mSensorManager.unregisterListener(SensorInfoActivity.this);
				mSensorManager.registerListener(SensorInfoActivity.this,
						mSensor, 
						SensorManager.SENSOR_DELAY_UI);
			}
		});
     
		displaySensorInfo();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		mSensorManager.unregisterListener(this);
	}	    
    
	public void displaySensorInfo() {
		mName.setText(mSensor.getName());
		mType.setText(String.valueOf(mSensor.getType()));
		mMaxRange.setText(String.valueOf(mSensor.getMaximumRange()));
		mMinDelay.setText(String.valueOf(mSensor.getMinDelay()));
		mPower.setText(String.valueOf(mSensor.getPower()));
		mResolution.setText(String.valueOf(mSensor.getResolution()));
		mVendor.setText(String.valueOf(mSensor.getVendor()));
		mVersion.setText(String.valueOf(mSensor.getVersion()));
	}    
    
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		switch (accuracy) {
			case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
				mAccuracy.setText(R.string.sensor_status_accuracy_high);
				break;
    
			case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
				mAccuracy.setText(R.string.sensor_status_accuracy_medium);
				break;
    
			case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
				mAccuracy.setText(R.string.sensor_status_accuracy_low);
				break;
    
			case SensorManager.SENSOR_STATUS_UNRELIABLE:
				mAccuracy.setText(R.string.sensor_status_unreliable);
				break;
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		onAccuracyChanged(event.sensor, event.accuracy);
	        
		mTimestampLabel.setVisibility(View.VISIBLE);
		mTimestamp.setVisibility(View.VISIBLE);
		mTimestamp.setText(String.valueOf(event.timestamp));
		mTimestampUnits.setVisibility(View.VISIBLE);
	        
		switch (event.sensor.getType()) {
			case Sensor.TYPE_ACCELEROMETER:
				showEventData("Acceleration - gravity on axis",
						ACCELERATION_UNITS,
						event.values[0],
						event.values[1],
						event.values[2]);
				break;
	                
			case Sensor.TYPE_MAGNETIC_FIELD:
				showEventData("Abient Magnetic Field",
						"uT",
						event.values[0],
						event.values[1],
						event.values[2]);
				break;

			case Sensor.TYPE_GYROSCOPE:
				showEventData("Angular speed around axis",
						"radians/sec",
						event.values[0],
						event.values[1],
						event.values[2]);
				break;

			case Sensor.TYPE_LIGHT:
				showEventData("Ambient light",
						"lux",
						event.values[0]);
				break;

			case Sensor.TYPE_PRESSURE:
				showEventData("Atmospheric pressure",
						"hPa",
						event.values[0]);
				break;

			case Sensor.TYPE_PROXIMITY:
				showEventData("Distance",
						"cm",
						event.values[0]);
				break;

			case Sensor.TYPE_GRAVITY:
				showEventData("Gravity",
						ACCELERATION_UNITS,
						event.values[0],
						event.values[1],
						event.values[2]);
				break;

			case Sensor.TYPE_LINEAR_ACCELERATION:
				showEventData("Acceleration (not including gravity)",
						ACCELERATION_UNITS,
						event.values[0],
						event.values[1],
						event.values[2]);
				break;

			case Sensor.TYPE_ROTATION_VECTOR:
				showEventData("Rotation Vector",
						null,
						event.values[0],
						event.values[1],
						event.values[2]);
	                
				mXaxisLabel.setText("x*sin(" + THETA + "/2)");
				mYaxisLabel.setText("y*sin(" + THETA + "/2)");
				mZaxisLabel.setText("z*sin(" + THETA + "/2)");
	                
				if (event.values.length == 4) {
					mCosLabel.setVisibility(View.VISIBLE);
					mCos.setVisibility(View.VISIBLE);
					mCos.setText(String.valueOf(event.values[3]));
				}
	                
				break;

			case Sensor.TYPE_ORIENTATION:
				showEventData("Angle",
						"Degrees",
						event.values[0],
						event.values[1],
						event.values[2]);
	                
				mXaxisLabel.setText(R.string.azimuthLabel);
				mYaxisLabel.setText(R.string.pitchLabel);
				mZaxisLabel.setText(R.string.rollLabel);
	                
				break;

			case Sensor.TYPE_RELATIVE_HUMIDITY:
				showEventData("Relatice ambient air humidity",
						"%",
						event.values[0]);
				break;

			case Sensor.TYPE_AMBIENT_TEMPERATURE:
				showEventData("Ambien temperature",
						"degree Celcius",
						event.values[0]);
				break;
		}		
	}    

	private void showEventData(String label, String units, float x, float y, float z) {
		mDataLabel.setVisibility(View.VISIBLE);
		mDataLabel.setText(label);
        
		if (units == null) {
			mDataUnits.setVisibility(View.GONE);
		} else {
			mDataUnits.setVisibility(View.VISIBLE);
			mDataUnits.setText("(" + units + "):");
		}
        
		mSingleValue.setVisibility(View.GONE);
        
		mXaxisLabel.setVisibility(View.VISIBLE);
		mXaxisLabel.setText(R.string.xAxisLabel);
		mXaxis.setVisibility(View.VISIBLE);
		mXaxis.setText(String.valueOf(x));
        
		mYaxisLabel.setVisibility(View.VISIBLE);
		mYaxisLabel.setText(R.string.yAxisLabel);
		mYaxis.setVisibility(View.VISIBLE);
		mYaxis.setText(String.valueOf(y));
        
		mZaxisLabel.setVisibility(View.VISIBLE);
		mZaxisLabel.setText(R.string.zAxisLabel);
		mZaxis.setVisibility(View.VISIBLE);
		mZaxis.setText(String.valueOf(z));
	}
	
	private void showEventData(String label, String units, float value) {
		mDataLabel.setVisibility(View.VISIBLE);
		mDataLabel.setText(label);
	        
		mDataUnits.setVisibility(View.VISIBLE);
		mDataUnits.setText("(" + units + "):");
	        
		mSingleValue.setVisibility(View.VISIBLE);
		mSingleValue.setText(String.valueOf(value));
	        
		mXaxisLabel.setVisibility(View.GONE);
		mXaxis.setVisibility(View.GONE);
	        
		mYaxisLabel.setVisibility(View.GONE);
		mYaxis.setVisibility(View.GONE);
	        
		mZaxisLabel.setVisibility(View.GONE);
		mZaxis.setVisibility(View.GONE);
	}	
}