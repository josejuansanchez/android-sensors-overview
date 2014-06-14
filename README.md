## Android Sensors Overview

#### 1. What is a Sensor?

A **sensor** is a device that detects and responds to some type of input from the physical environment. The specific input could be light, heat, motion, moisture, pressure, or any one of a great number of other environmental phenomena. The output is generally a signal that is converted to human-readable display at the sensor location or transmitted electronically over a network for reading or further processing. 

*Source:* [whatis.techtarget.com](http://whatis.techtarget.com/definition/sensor)

#### 2. Types of Sensors

The Android platform supports three broad categories of sensors:

* **Motion sensors**
These sensors measure acceleration forces and rotational forces along three axes. This category includes accelerometers, gravity sensors, gyroscopes, and rotational vector sensors.
* **Environmental sensors**
These sensors measure various environmental parameters, such as ambient air temperature and pressure, illumination, and humidity. This category includes barometers, photometers, and thermometers.
* **Position sensors**
These sensors measure the physical position of a device. This category includes orientation sensors and magnetometers.

**Note**: **Location Sensors** will not be studied in this overview.  
*Source:* [Sensors Overview - Android Developers](http://developer.android.com/guide/topics/sensors/sensors_overview.html)

#### 3. Android Sensor API

* [`SensorManager`](http://developer.android.com/reference/android/hardware/SensorManager.html): is the Android system service that gives an app access to hardware sensors.
* [`Sensor`](http://developer.android.com/reference/android/hardware/Sensor.html): is the Android representation of a hardware sensor on a device.
* [`SensorEventListener`](http://developer.android.com/reference/android/hardware/SensorEventListener.html): is an interface that provides the callbacks to alert an app to sensor-related events.
* [`SensorEvent`](http://developer.android.com/reference/android/hardware/SensorEvent.html): is the data structure that contains the information that is passed to an app when a hardware sensor has information to report.

#### 4. SensorManager

[`SensorManager`](http://developer.android.com/reference/android/hardware/SensorManager.html) lets you access the device's sensors. Get an instance of this class by calling `Context.getSystemService()` with the argument `SENSOR_SERVICE`.

Always make sure to **disable sensors you don't need, especially when your activity is paused**. Failing to do so can drain the battery in just a few hours. Note that the system will not disable sensors automatically when the screen turns off.

```java
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
```
**Note**: Don't use this mechanism with a Trigger Sensor, have a look at `TriggerEventListener. TYPE_SIGNIFICANT_MOTION` is an example of a trigger sensor.

#### 5. Sensor

[`Sensor`](http://developer.android.com/reference/android/hardware/Sensor.html) represents a hardware sensor on a device.

* Interesting public methods:
  * `getMaximumRange()` returns the maximum range the sensor can measure in the regular units reported by the sensor. 
  * `getResolution()` reports the resolution of the sensor, in the regular units reported by the sensor.


#### 6. SensorEventListener

[`SensorEventListener`](http://developer.android.com/reference/android/hardware/SensorEventListener.html) is used for receiving notifications from the SensorManager when sensor values have changed. In this class there are two public methods:

* `onAccuracyChanged(Sensor sensor, int accuracy)`: Called when the accuracy of a sensor has changed.
* `abstract void	 onSensorChanged(SensorEvent event)`: Called when sensor values have changed.

#### 7. SensorEvent
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

**Note**: This coordinate system is different from the one used in the Android 2D APIs where the origin is in the top-left corner.

#### 8. Sensor Rates
When you register a listener, you specify the delay or measurement rate for the listener. The predefined rates are:
* `SENSOR_DELAY_FASTEST`
* `SENSOR_DELAY_GAME`
* `SENSOR_DELAY_UI` (Suitable for usual user interface functions, like rotating the screen orientation.)
* `SENSOR_DELAY_NORMAL` (The default value.)

In Android 4.0.3, these are hard-coded to be 0, 20, 67, and 200 milliseconds, respectively. You can also specify your own delay in microseconds. 

#### 9. Raw Sensors and Synthetic Sensors
The sensors referenced through the [`Sensor`](http://developer.android.com/reference/android/hardware/Sensor.html) class may be of two types: 
* **Raw sensors** give raw data from a sensor, and one raw sensor corresponds to one actual physical component inside the Android device. 
  * `Sensor.TYPE_LIGHT`
  * `Sensor.TYPE_PROXIMITY`
  * `Sensor.TYPE_PRESSURE`
  * `Sensor.TYPE_TEMPERATURE` (deprecated) 
  * `Sensor.TYPE_ACCELEROMETER`
  * `Sensor.TYPE_GYROSCOPE`
  * `Sensor.TYPE_MAGNETIC_FIELD`
  * `Sensor.TYPE_RELATIVE_HUMIDITY`
  * `Sensor.TYPE_AMBIENT_TEMPERATURE`

* **Synthetic (or composite or virtual) sensors** provide an abstraction layer between application code and low-level device components by either combining the raw data of multiple raw sensors, or by modifying the raw sensor data to make it easier to consume.
  * `Sensor.TYPE_ROTATION_VECTOR`
  * `Sensor.TYPE_LINEAR_ACCELERATION`
  * `Sensor.TYPE_GRAVITY`
  * `Sensor.TYPE_ORIENTATION` (deprecated)

Synthetic sensors do not necessarily have consistent implementation across different devices. For example, some devices may use the gyroscope to determine rotation vector values while others do not. Differences in hardware or sensor synthesis implementations can cause synthetic sensors on some devices to provide better readings than synthetic sensors on other devices. Although these differences exist, it is still generally preferable to utilize synthetic sensor data over raw sensor data. Sensors tend to be designed to provide good results for a device’s specific sensor hardware.

#### 10. References
* Book: [Professional Android Sensor Programming](http://www.wrox.com/WileyCDA/WroxTitle/Professional-Android-Sensor-Programming.productCd-1118183487.html)
  * [Source code used in the book](https://github.com/gast-lib/gast-lib) 
  * [A runnable form of the code can also be downloaded from Google Play](https://play.google.com/store/apps/details?id=root.gast.playground)
* [Android Sensors Overview](http://developer.android.com/guide/topics/sensors/sensors_overview.html). API Guides - Android Developers.
  * [Motion Sensors](http://developer.android.com/guide/topics/sensors/sensors_motion.html)
  * [Position Sensors](http://developer.android.com/guide/topics/sensors/sensors_position.html)
  * [Environment Sensors](http://developer.android.com/guide/topics/sensors/sensors_environment.html)
* [Android Sensor - Tutorial](http://www.vogella.com/tutorials/AndroidSensor/article.html). Lars Vogel.
* [Sensors in Android](http://developer.samsung.com/android/technical-docs/Sensors-in-Android). Samsung Developers.
* Video: [Sensor Fusion on Android Devices: A Revolution in Motion Processing](https://www.youtube.com/watch?v=C7JQ7Rpwn2k). Google Tech Talk 2010.

----
### Examples

#### 1. Dice Shaker
[Source code](https://github.com/josejuansanchez/android-sensors-overview/tree/master/diceshaker).

#### 2. Sensor List
[Source code](https://github.com/josejuansanchez/android-sensors-overview/tree/master/sensorlist).
