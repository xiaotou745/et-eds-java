��һ�����ü��±���upload2Linux.bat
�޸���Ŀ���ڵ�Ŀ¼��
projectPullPath��ʾgit��Ҫpull�����·��
projectPath��ʾҪ�Զ������ľ������Ŀ
exePath��ʾupload2Linux.bat���ڵ�·��

set projectPullPath=D:\eds.java
set projectPath=%projectPullPath%\eds\admin

set exePath=D:\eds.java\tools\upload2linux

�ڶ������޸�linux�������������
�򿪣�copytools\conf\conf.properties

host=10.8.8.64
port=22
username=root
password=abcd@1234
lastpath=D:/test/last���ϴν�ѹwar������ʱĿ¼��
src=D:/test/unzipwar��war��ѹ���Ŀ¼����ʱ��ʾ�ϴ������ļ���
#src=D:/test/war����ʱ��ʾ�ϴ�����war��
warsrc=D:/test/war������ϴ�����war������md5У�飩
dst=/usr/local/tomcat/test���ļ���warҪ���Ƶ�linuxĿ¼�£�