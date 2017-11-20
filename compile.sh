git reset --hard
git pull

rm -rf src/main/java/com/k9sv/Config2.java
cp /data/resource/Config2.java src/main/java/com/k9sv/
mvn compile


rm -rf /data/server/chongxin/WEB-INF/classes/
rm -rf /data/server/chongxin/WEB-INF/view
rm -rf /data/server/chongxin/WEB-INF/web.xml
rm -rf /data/server/chongxin/assets
rm -rf /data/server/chongxin/userui
rm -rf /data/server/chongxin/WEB-INF/lib

cp -R target/classes /data/server/chongxin/WEB-INF/
rm -rf /data/server/chongxin/WEB-INF/classes/datasource.xml
cp /data/resource/datasource.xml /data/server/chongxin/WEB-INF/classes/

cp -R webapp/WEB-INF/view /data/server/chongxin/WEB-INF/
cp -R webapp/WEB-INF/lib /data/server/chongxin/WEB-INF/
cp -R webapp/WEB-INF/web.xml /data/server/chongxin/WEB-INF/
cp -R webapp/assets /data/server/chongxin/
cp -R webapp/userui /data/server/chongxin/


/data/server/atlassian-confluence-5.7.1/bin/stop-confluence.sh

cd /data/tomcats/apache-tomcat-8.0.12-8082/logs/

../bin/catalina.sh stop
killall java
sleep 2s

../bin/catalina.sh start



/data/server/atlassian-confluence-5.7.1/bin/start-confluence.sh