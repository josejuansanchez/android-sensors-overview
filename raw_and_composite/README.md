# Raw Sensors and Composite Sensors
The sensors referenced through the [Sensor](http://developer.android.com/reference/android/hardware/Sensor.html) class may be of two types: 
* **Raw sensors** (hardware-based) give raw data from a sensor, and one raw sensor corresponds to one actual physical component inside the Android device. 
  * `Sensor.TYPE_LIGHT`
  * `Sensor.TYPE_PROXIMITY`
  * `Sensor.TYPE_PRESSURE`
  * `Sensor.TYPE_TEMPERATURE` (deprecated) 
  * `Sensor.TYPE_ACCELEROMETER`
  * `Sensor.TYPE_GYROSCOPE`
  * `Sensor.TYPE_MAGNETIC_FIELD`
  * `Sensor.TYPE_RELATIVE_HUMIDITY`
  * `Sensor.TYPE_AMBIENT_TEMPERATURE`


* **Composite sensors** (software-based) provide an abstraction layer between application code and low-level device components by either combining the raw data of multiple raw sensors, or by modifying the raw sensor data to make it easier to consume.
  * `Sensor.TYPE_ROTATION_VECTOR`
  * `Sensor.TYPE_LINEAR_ACCELERATION`
  * `Sensor.TYPE_GRAVITY`
  * `Sensor.TYPE_ORIENTATION` (deprecated)
