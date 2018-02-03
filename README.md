# martian-robots
The surface of Mars can be modelled by a rectangular grid around which robots are able to move  
according to instructions provided from Earth. This program determines each sequence of robot  
positions and reports the final position of the robot.

# Examples
Run this from your IDE.  
Create a configuration to run a **ConsoleApp** class from **mr-app** module.  
Add below strings as program arguments:

##### Case 1  
3 robots moves on surface of size [5:3]  
```"5 3" "1 1 E" "RFRFRFRF" "3 2 N" "FRRFLLFFRRFLL" "0 3 W" "LLFFFLFLFL"```  

##### Case 2  
5 robots moves on surface of size [10:10]  
```"10 10" "1 1 E" "RFRFRFRFFF" "3 2 N" "FRRFLLFFFRRRFLL" "0 3 W" "LLFFFFFLFLFL" "9 10 N" "LLFFFFFFFFFRFRFFFF" "5 5 S" "RFFRFFLFFFLFFFFLFFF"```  

##### Case 3  
7 robots moves on surface of size [10:10]  
```"10 10" "1 1 E" "RFRFRFRFFF" "3 2 N" "FRRFLLFFFRRRFLL" "0 3 W" "LLFFFFFLFLFL" "9 10 N" "LLFFFFFFFFFRFRFFFF" "5 5 S" "RFFRFFLFFFLFFFFLFFF" "5 6 N" "FFFFFFFFFF" "8 7 W" "FFFFRFRFRFRFFFF"```  
