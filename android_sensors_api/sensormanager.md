# SensorManager

[SensorManager](http://developer.android.com/reference/android/hardware/SensorManager.html) lets you access the device's sensors. Get an instance of this class by calling `getSystemService()` with the argument `SENSOR_SERVICE`.

````java
private SensorManager mSensorManager;
...
mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
````

#### Identifying Sensors

[SensorManager](http://developer.android.com/reference/android/hardware/SensorManager.html) provides two methods to access Sensor objects:
* `getSensorList()`: returns all the sensors.
* `getDefaultSensor()`: returns the default sensor for the specified type.

Example:

````java
List<Sensor> deviceSensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);
````

Example:

````java
if (mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null) {
  // Success! There's a pressure sensor.
}
else {
  // Failure! No pressure sensor.
}
````

**Source code:** [SensorManager.java](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/SensorManager.java)