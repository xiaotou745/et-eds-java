第一步：用记事本打开upload2Linux.bat
修改项目所在的目录：
projectPullPath表示git需要pull代码的路径
projectPath表示要自动发布的具体的项目
exePath表示upload2Linux.bat所在的路径

set projectPullPath=D:\eds.java
set projectPath=%projectPullPath%\eds\admin

set exePath=D:\eds.java\tools\upload2linux

第二步：修改linux服务器相关配置
打开：copytools\conf\conf.properties

host=10.8.8.64
port=22
username=root
password=abcd@1234
lastpath=D:/test/last（上次解压war包的临时目录）
src=D:/test/unzipwar（war解压后的目录，此时表示上传单个文件）
#src=D:/test/war（此时表示上传整个war）
warsrc=D:/test/war（如果上传整个war，则不用md5校验）
dst=/usr/local/tomcat/test（文件或war要复制的linux目录下）