# SensorEvent
[SensorEvent](http://developer.android.com/reference/android/hardware/SensorEvent.html) is the data structure that contains the information that is passed to an app when a hardware sensor has information to report. The data members of the SensorEvent are:
  * `accuracy`: The accuracy of the event. Can have the following values:
    * `SensorManager.SENSOR_STATUS_ACCURACY_HIGH`
    * `SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM`
    * `SensorManager.SENSOR_STATUS_ACCURACY_LOW`
    * `SensorManager.SENSOR_STATUS_UNRELIABLE`
  * `sensor`: An instance of the `Sensor` class that generated the `SensorEvent`.
  * `timestamp`: The time in milliseconds when the `SensorEvent` occurred.
  * `values`: An array of values that represent sensor data.

**Source code:** [SensorEvent.java](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/SensorEvent.java)