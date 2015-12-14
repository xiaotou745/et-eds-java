@echo off 

set projectPullPath=D:\eds.java
set projectPath=%projectPullPath%\eds\admin

set exePath=D:\eds.java\tools\upload2linux

d:
cd %projectPullPath%

echo 开始pull最新代码
"C:\Program Files (x86)\Git\cmd\git.exe" pull

cd %projectPath%
echo 开始打包
call mvn install

cd %exePath%

echo 开始复制%projectPath%\target\admin-0.0.1-SNAPSHOT.war
copy /y %projectPath%\target\admin-0.0.1-SNAPSHOT.war war\


echo 开始解压

cd %exePath%\unzipwar

jar -xf %exePath%\war\admin-0.0.1-SNAPSHOT.war

echo 开始复制到last目录下

xcopy %exePath%\unzipwar %exePath%\last\ /e /y

cd %exePath%\copytools

echo 开始上传到linux
java -jar ftptools-0.0.1-SNAPSHOT.jar
pause