#!/bin/sh
AGENT_VERSION="1.6.0"
AGENT_ID="dubbo160_1"
APPLICATION_NAME="dubbo-thinkpad-s5hj"
AGENT_PATH="d:/pinpoint/pinpoint-agent-1.6.0"
java -Xms1024m -Xmx1024m -Xmn512m -XX:PermSize=256M -XX:MaxPermSize=512M -javaagent:\$AGENT_PATH/pinpoint-bootstrap-1.6.0.jar -Dpinpoint.agentId=\$AGENT_ID -Dpinpoint.applicationName=\$APPLICATION_NAME -jar bboss-rt-${bboss_version}.jar 
