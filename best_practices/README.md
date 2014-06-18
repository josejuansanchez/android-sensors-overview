# Best Practices

#### 1) Unregister sensor listeners

````java
public class SensorActivity extends Activity, implements SensorEventListener {
  private final SensorManager mSensorManager;
  private final Sensor mLight;
  ...

  protected void onResume() {
    super.onResume();
    mSensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
  }

  protected void onPause() {
    super.onPause();
    mSensorManager.unregisterListener(this);
  }
}
````
Always make sure to **disable sensors you don't need, especially when your activity is paused**. Failing to do so can drain the battery in just a few hours. Note that the system will not disable sensors automatically when the screen turns off.

#### 2) Don't block the `onSensorChanged()` method

Sensor data can change at a high rate, which means the system may call the `onSensorChanged(SensorEvent)` method quite often. As a best practice, you should do as little as possible within the `onSensorChanged(SensorEvent)` method so you don't block it.

#### 3) Avoid using deprecated methods or sensor types

Several methods and constants have been deprecated. In particular, the TYPE_ORIENTATION sensor type has been deprecated.

#### 4) Verify sensors before you use them

Always verify that a sensor exists on a device before you attempt to acquire data from it. Don't assume that a sensor exists simply because it's a frequently-used sensor.

#### 5) Choose sensor delays carefully

Sensors can provide data at very high rates. Allowing the system to send extra data that you don't need wastes system resources and uses battery power.