# SensorEventListener

[SensorEventListener](http://developer.android.com/reference/android/hardware/SensorEventListener.html) is used for receiving notifications from the SensorManager when sensor values have changed. In this class there are two public methods:

* `onAccuracyChanged(Sensor sensor, int accuracy)`: Called when the accuracy of a sensor has changed.
* `abstract void	 onSensorChanged(SensorEvent event)`: Called when sensor values have changed.

**Source code:** [SensorEventListener.java](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/SensorEventListener.java)

Example:

````java
mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
... 

@Override
public final void onAccuracyChanged(Sensor sensor, int accuracy) {
	// Do something here if sensor accuracy changes.
}

@Override
public final void onSensorChanged(SensorEvent event) {
	// The light sensor returns a single value.
	// Many sensors return 3 values, one for each axis.
	float lux = event.values[0];
	// Do something with this sensor value.
}
````