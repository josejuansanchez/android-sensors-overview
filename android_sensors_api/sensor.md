# Sensor

[Sensor](http://developer.android.com/reference/android/hardware/Sensor.html) represents a hardware sensor on a device. This class provides information about the sensor, such as:

* Maximum range
* Minimum delay
* Name
* Power
* Resolution
* Type
* Vendor
* Version

* Some public methods:
  * `getMaximumRange()`: returns the maximum range the sensor can measure in the regular units reported by the sensor. 
  * `getResolution()`: reports the resolution of the sensor (smallest difference between two values reported by this sensor), in the regular units reported by the sensor.