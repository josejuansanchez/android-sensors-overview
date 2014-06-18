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
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private static int SHAKE_THRESHOLD = 3;
	private TextToSpeech mTts;
	private TextView mNumber;	

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

	private void generateRandomNumber() {		
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

		float acceleration = (float) Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH;

		if (acceleration > SHAKE_THRESHOLD) {
			generateRandomNumber();	    	
		}
	}
}