set "AGENT_VERSION=1.6.0"
set "AGENT_ID=dubbo160_1"
set "APPLICATION_NAME=dubbo-thinkpad-s5hj"
set "AGENT_PATH=d:/pinpoint/pinpoint-agent-1.6.0"
java -Xms512m -Xmx512m -Xmn256m -XX:PermSize=256M -XX:MaxPermSize=256M -javaagent:%AGENT_PATH%/pinpoint-bootstrap-1.6.0.jar -Dpinpoint.agentId=%AGENT_ID% -Dpinpoint.applicationName=%APPLICATION_NAME% -jar bboss-rt-${bboss_version}.jar 