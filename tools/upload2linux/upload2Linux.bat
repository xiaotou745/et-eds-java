@echo off 

set projectPullPath=D:\eds.java
set projectName=admin
set resPath=D:\repository\edaisong\admin\0.0.1-SNAPSHOT
set exePath=D:\eds.java\tools\upload2linux


set projectPath=%projectPullPath%\eds\%projectName%

d:
echo 开始打包core,entity,api
call pullbase

cd %projectPath%
echo 开始打包
call mvn install

cd %exePath%

echo 开始复制%resPath%\%projectName%-0.0.1-SNAPSHOT.war
copy /y %resPath%\%projectName%-0.0.1-SNAPSHOT.war war\


echo 开始解压

cd %exePath%\unzipwar

jar -xf %exePath%\war\%projectName%-0.0.1-SNAPSHOT.war



cd %exePath%\copytools

echo 开始上传到linux
java -jar ftptools-0.0.1-SNAPSHOT.jar


echo 开始备份到last目录下
xcopy %exePath%\unzipwar %exePath%\last\ /e /y /q
pause