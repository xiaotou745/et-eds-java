@echo off 

set projectPullPath=D:\eds.java
set projectPath=%projectPullPath%\eds\admin

set exePath=D:\eds.java\tools\upload2linux

d:
cd %projectPullPath%

echo ��ʼpull���´���
"C:\Program Files (x86)\Git\cmd\git.exe" pull

cd %projectPath%
echo ��ʼ���
call mvn install

cd %exePath%

echo ��ʼ����%projectPath%\target\admin-0.0.1-SNAPSHOT.war
copy /y %projectPath%\target\admin-0.0.1-SNAPSHOT.war war\


echo ��ʼ��ѹ

cd %exePath%\unzipwar

jar -xf %exePath%\war\admin-0.0.1-SNAPSHOT.war

echo ��ʼ���Ƶ�lastĿ¼��

xcopy %exePath%\unzipwar %exePath%\last\ /e /y

cd %exePath%\copytools

echo ��ʼ�ϴ���linux
java -jar ftptools-0.0.1-SNAPSHOT.jar
pause