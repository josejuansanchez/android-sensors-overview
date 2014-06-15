package org.josejuansanchez.diceshaker;

import java.util.Random;

import org.josejuansanchez.shaker.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.TextView;

public class DiceShakerActivity extends Activity implements SensorEventListener, TextToSpeech.OnInitListener {

	private TextToSpeech mTts;
	private TextView mNumber;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private static float SHAKE_THRESHOLD = 2;

	// Acceleration without gravity
	private float mAcceleration = 0.00f; 
	
	// Curent acceleration with gravity
	private float mCurrentAcceleration = SensorManager.GRAVITY_EARTH;
	
	// Last acceleration with gravity
	private float mLastAcceleration = SensorManager.GRAVITY_EARTH;		

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNumber = (TextView) findViewById(R.id.number);		
		mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);		
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mTts = new TextToSpeech(this, this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	    mSensorManager.unregisterListener(this);
	}	

	@Override
	public void onInit(int status) {		
		if(status != TextToSpeech.ERROR) {
			mTts.setLanguage(getResources().getConfiguration().locale);			
		}				
	}
		
	@Override
	protected void onDestroy() {
		if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        super.onDestroy();
	}

	private void speakOut() {		
		Random randomGenerator = new Random();
		int randomNum = randomGenerator.nextInt(6) + 1;				
        mNumber.setText(Integer.toString(randomNum));
		mTts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null);
    }	
	
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
	    float x = event.values[0];
	    float y = event.values[1];
	    float z = event.values[2];

	    mLastAcceleration = mCurrentAcceleration;
	    mCurrentAcceleration = (float) Math.sqrt((double) (x*x + y*y + z*z));
	    float delta = mCurrentAcceleration - mLastAcceleration;
	    
	    // Low-cut filter
	    mAcceleration = mAcceleration * 0.9f + delta;	
	    
	    if (mAcceleration > SHAKE_THRESHOLD) {
		    speakOut();	    	
	    }
	}

}
