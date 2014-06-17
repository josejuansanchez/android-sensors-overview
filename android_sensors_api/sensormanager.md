# SensorManager

[SensorManager](http://developer.android.com/reference/android/hardware/SensorManager.html) lets you access the device's sensors. Get an instance of this class by calling `Context.getSystemService()` with the argument `SENSOR_SERVICE`.

````java
mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
````

[SensorManager](http://developer.android.com/reference/android/hardware/SensorManager.html) provides two methods to access Sensor objects:
* `getSensorList()`: returns all the sensors.
* `getDefaultSensor()`: returns the default sensor for the specified type.

Example of use:

````java
 public class SensorActivity extends Activity, implements SensorEventListener {
     private final SensorManager mSensorManager;
     private final Sensor mAccelerometer;

     public SensorActivity() {
         mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
         mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
     }

     protected void onResume() {
         super.onResume();
         mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
     }

     protected void onPause() {
         super.onPause();
         mSensorManager.unregisterListener(this);
     }

     public void onAccuracyChanged(Sensor sensor, int accuracy) {
     }

     public void onSensorChanged(SensorEvent event) {
     }
 }
````

Always make sure to **disable sensors you don't need, especially when your activity is paused**. Failing to do so can drain the battery in just a few hours. Note that the system will not disable sensors automatically when the screen turns off.

**Source code:** [SensorManager.java](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/SensorManager.java)
