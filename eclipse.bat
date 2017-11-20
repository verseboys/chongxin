cd %cd%
call mvn clean eclipse:clean eclipse:eclipse -DdownloadSources=true -e
echo. & pause