@echo off 

set envconfig=test
set projectPullPath=D:\eds.java
set projectName=admin
set resPath=D:\repository\edaisong\admin\0.0.1-SNAPSHOT
set exePath=D:\eds.java\tools\upload2linux


set projectPath=%projectPullPath%\eds\%projectName%

d:
echo ��ʼ���core,entity,api
call pullbase

cd %projectPath%
echo ��ʼ���
call mvn install

cd %exePath%

echo ��ʼ����%resPath%\%projectName%-0.0.1-SNAPSHOT.war
copy /y %resPath%\%projectName%-0.0.1-SNAPSHOT.war war\


echo ��ʼ��ѹ

cd %exePath%\unzipwar

jar -xf %exePath%\war\%projectName%-0.0.1-SNAPSHOT.war

echo ��ʼ���������ļ�%envconfig%-conf.properties��%envconfig%-env.properties
copy /y WEB-INF\classes\conf\custom\%envconfig%-conf.properties WEB-INF\classes\conf\core\current-conf.properties
copy /y WEB-INF\classes\conf\custom\%envconfig%-env.properties WEB-INF\classes\conf\core\current-env.properties
pause

cd %exePath%\copytools

echo ��ʼ�ϴ���linux
java -jar ftptools-0.0.1-SNAPSHOT.jar


echo ��ʼ���ݵ�lastĿ¼��
xcopy %exePath%\unzipwar %exePath%\last\ /e /y /q
pause