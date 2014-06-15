# Raw Sensors and Synthetic Sensors
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

Synthetic sensors do not necessarily have consistent implementation across different devices. For example, some devices may use the gyroscope to determine rotation vector values while others do not. Differences in hardware or sensor synthesis implementations can cause synthetic sensors on some devices to provide better readings than synthetic sensors on other devices. Although these differences exist, it is still generally preferable to utilize synthetic sensor data over raw sensor data. Sensors tend to be designed to provide good results for a device's specific sensor hardware.
