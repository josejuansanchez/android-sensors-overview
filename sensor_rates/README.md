# Sensor Rates
When you register a listener, you specify the delay or measurement rate for the listener. The predefined rates are:
* `SENSOR_DELAY_FASTEST`: get sensor data as fast as possible.
* `SENSOR_DELAY_GAME`: rate suitable for games.
* `SENSOR_DELAY_UI`: rate suitable for the user interface functions.
* `SENSOR_DELAY_NORMAL`: rate suitable for screen orientation changes. (The default value).

The value of these constants in [Android 4.4.2](https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/hardware/SensorManager.java) are 0, 20000, 66667 and 200000 *microseconds*.