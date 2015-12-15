@echo off 

set projectPullPath=D:\eds.java

d:
cd %projectPullPath%

echo 开始pull最新代码
"C:\Program Files (x86)\Git\cmd\git.exe" pull


echo 开始打包core
cd %projectPullPath%\eds\core
call mvn clean install -U


echo 开始打包entity
cd %projectPullPath%\eds\entity
call mvn clean install -U


echo 开始打包api
cd %projectPullPath%\eds\api
call mvn install  -U
