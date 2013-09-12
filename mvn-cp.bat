mvn assembly:assembly -DdescriptorId=jar-with-dependencies

cp target\sink-0.0.1-SNAPSHOT-jar-with-dependencies.jar  D:\work\flume\apache-flume-1.4.0-bin\lib2

mvn jar:test-jar
cp target\sink-0.0.1-SNAPSHOT-tests.jar  D:\work\flume\apache-flume-1.4.0-bin\lib2