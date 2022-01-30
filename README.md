# Epilog

Telemetry plugin for bukkit compatible Minecraft servers.

More information can be found at [heapcraft.net](http://heapcraft.net/?p=epilog-manual)

## Fork

- Fork at 2022 add some new event listener
  - `PlayerAdvancementDoneEvent` to listen on advancement event
  - `PlayerInteractEntityEvent` to listen player interact with villager
- Remove some deprecated function that cause build failed
- Add ability to connect to MySQL database and log data into it

## Build
To build jar use this command: `mvn package`
