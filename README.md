# Simulated Gauges
#### A Deployer-based addon for Create: Simulated, best used with Extra Gauges!

**Simulated Gauges** uses [**Deployer API**](https://github.com/LIUKRAST/Deployer) to allow Logistics Gauges to get and utilize physics information from various [**Create: Simulated**](https://github.com/Creators-of-Aeronautics/Simulated-Project) blocks!

*Note: This mod is best used alongside [**Create: Extra Gauges**](https://github.com/LIUKRAST/CreateExtraGauges)!*

---
# List of New Gauges:
## Altitude Gauge
This gauge can read the height of its block in the world OR the air pressure of its position.
It outputs both a String and a Number for both altitude and air pressure.
## Velocity Gauge
This gauge can read the velocity of its block in the world separated into its X, Y, and Z axes.
It outputs both a String and a Number for the selected axis.
## Gimbal Gauge
This gauge can read the orientation of its block in the world as Euler Angles separated into its X, Y, and Z axes.
It outputs both a String and a Number for the selected axis.
The values are derived from the orientation quaternion in YXZ rotation order.

---
# List of supported Blocks:
## Altitude Sensor
This sensor can output its height in the world for Deployer-based gauges.
It outputs its value as a String and a Number, as well as, when it outputs a signal, it will also toggle the Redstone output boolean.
## Velocity Sensor
This sensor can output its velocity in the world as a number for Deployer-based gauges.
It outputs its value as a String and a Number, as well as, when it outputs a signal, it will also toggle the Redstone output boolean.
## Navigation Table
This block can output its adjusted angle relative to its target destination for Deployer-based gauges.
It outputs its value as a String and a Number.
## Nameplate
This block can output its text for Deployer-based gauges as well as accepts String input to change its text.
## Linked Typewriter
This block can output its held key's keycode as a number for Deployer-based gauges as well as output the typed string of the Typewriter as a string for Deployer-based gauges.

# Future Content:
- Mass Gauge