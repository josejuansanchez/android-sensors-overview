# Sensor Coordinate System

In general, the sensor framework uses a standard 3-axis coordinate system to express data values. 

<p align="center">
  <img src="http://developer.android.com/images/axis_device.png" alt="Coordinate system used by the SensorEvent API" />
</p>
*Image 1: [Android Developers](http://developer.android.com/reference/android/hardware/SensorEvent.html)*

The coordinate-system is defined relative to the screen of the phone in its default orientation. **The axes are not swapped when the device's screen orientation changes**.

The X axis is horizontal and points to the right, the Y axis is vertical and points up and the Z axis points towards the outside of the front face of the screen. In this system, coordinates behind the screen have negative Z values.

>**Note**: This coordinate system is different from the one used in the Android 2D APIs where the origin is in the top-left corner.