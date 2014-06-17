# SensorEvent
[`SensorEvent`](http://developer.android.com/reference/android/hardware/SensorEvent.html) is the data structure that contains the information that is passed to an app when a hardware sensor has information to report. The data members of the SensorEvent are:
  * `SensorEvent.accuracy`: The accuracy of the event. Can have the following values:
    * `SensorManager.SENSOR_STATUS_ACCURACY_HIGH`
    * `SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM`
    * `SensorManager.SENSOR_STATUS_ACCURACY_LOW`
    * `SensorManager.SENSOR_STATUS_UNRELIABLE`
  * `SensorEvent.sensor`: An instance of the `Sensor` class that generated the `SensorEvent`.
  * `SensorEvent.timestamp`: The time in milliseconds when the `SensorEvent` occurred.
  * `SensorEvent.values`: An array of values that represent sensor data.

##### Definition of the coordinate system used by the SensorEvent API.

<p align="center">
  <img src="http://developer.android.com/images/axis_device.png" alt="Coordinate system used by the SensorEvent API" />
</p>
*Image 1: [Android Developers](http://developer.android.com/reference/android/hardware/SensorEvent.html)*

The coordinate-system is defined relative to the screen of the phone in its default orientation. The axes are not swapped when the device's screen orientation changes.

The X axis is horizontal and points to the right, the Y axis is vertical and points up and the Z axis points towards the outside of the front face of the screen. In this system, coordinates behind the screen have negative Z values.

>**Note**: This coordinate system is different from the one used in the Android 2D APIs where the origin is in the top-left corner.

**Source code:** [SensorEvent.java](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/SensorEvent.java)