The jar file can be opened through cmd line using
cd ==> to the folder where the jar file is located
set PATH="path to javafx\lib" ==> the path to javafx jars
java -jar --module-path %PATH% --add-modules ALL-MODULE-PATH newpacman.jar

** this adds the external jars into the running jar