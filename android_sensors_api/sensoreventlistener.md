# SensorEventListener

[SensorEventListener](http://developer.android.com/reference/android/hardware/SensorEventListener.html) is used for receiving notifications from the SensorManager when sensor values have changed. In this class there are two public methods:

* `onAccuracyChanged(Sensor sensor, int accuracy)`: Called when the accuracy of a sensor has changed.
* `abstract void	 onSensorChanged(SensorEvent event)`: Called when sensor values have changed.

**Source code:** [SensorEventListener.java](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/SensorEventListener.java)