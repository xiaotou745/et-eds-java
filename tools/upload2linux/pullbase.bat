@echo off 

set projectPullPath=D:\eds.java

d:
cd %projectPullPath%

echo ��ʼpull���´���
"C:\Program Files (x86)\Git\cmd\git.exe" pull


echo ��ʼ���core
cd %projectPullPath%\eds\core
call mvn clean install -U


echo ��ʼ���entity
cd %projectPullPath%\eds\entity
call mvn clean install -U


echo ��ʼ���api
cd %projectPullPath%\eds\api
call mvn install  -U
