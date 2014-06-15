# Sensor Rates
When you register a listener, you specify the delay or measurement rate for the listener. The predefined rates are:
* `SENSOR_DELAY_FASTEST`
* `SENSOR_DELAY_GAME`
* `SENSOR_DELAY_UI` (Suitable for usual user interface functions, like rotating the screen orientation.)
* `SENSOR_DELAY_NORMAL` (The default value.)

In Android 4.0.3, these are hard-coded to be 0, 20, 67, and 200 milliseconds, respectively. You can also specify your own delay in microseconds. 
