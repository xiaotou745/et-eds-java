
            �q�����������������������������������������������r
    ����������           ֧��������ʾ���ṹ˵��             ����������
            �t�����������������������������������������������s 
��                                                                  
         �������ԣ�JAVA
	 ����JDK�汾��1.5
         ��    Ȩ��֧�������й������缼�����޹�˾
��������������
 1�������ļ�˵��
��������������
alipay-sdk-java*.jar��������������֧����SDK�����ļ�jar
alipay-sdk-java*-source.jar������ ֧����SDKԴ���ļ�jar
commons-logging-1.1.1.jar������������SDK��������־jar
commons-logging-1.1.1-sources.jar����SDK��������־Դ��jar

��ע���
����֧�����ӿ���Ҫ������ļ��ǣ�
alipay-sdk-java*.jar
commons-logging-1.1.1.jar

����һ���˽����ʵ���������ļ���
alipay-sdk-java*-source.jar
commons-logging-1.1.1-sources.jar


������������������
 2����Ҫ���ļ�����˵��
������������������
��������������������������������������������������������������
DefaultAlipayClient.java

public DefaultAlipayClient(String serverUrl, String appId, String privateKey)
���ܣ����췽��
���룺serverUrl �ǿգ������������ַ�����ԣ�http://openapi.alipaydev.com/gateway.do ���ϣ�https://openapi.alipay.com/gateway.do ��
      appId �ǿգ�Ӧ��ID
      privateKey �ǿգ�˽Կ
��������ÿͻ���ʵ������


public <T extends AlipayResponse> T execute(AlipayRequest<T> request)
���ܣ�ִ��������ã������ڲ���Ҫ��Ȩ�ӿڵ��ã�
���룺request �ӿ��������
�����T  ���󷵻ض���

public <T extends AlipayResponse> T execute(AlipayRequest<T> request, String accessToken)
���ܣ�ִ��������ã���������Ҫ��Ȩ�ӿڵ��ã�
���룺request �ӿ��������
      authToken ��Ȩ����
�����T  ���󷵻ض���

������������������
 3������ʾ��
������������������
��������������������������������������������������������������
��ο��� com.alipay.api.test.PublicTest.java

��������������������������������������������������������������

������������������
 4��ǩ�������
������������������
��������������������������������������������������������������

AlipaySignature.java

public static String rsaSign(Map<String, String> params, String privateKey, String charset)
���ܣ�RSAǩ��
���룺params ��ǩ������map
      privateKey ˽Կ
      charset ǩ�������ʽ
�����ǩ�����

public static boolean rsaCheckV2(Map<String, String> params, String publicKey, String charset)
���ܣ�RSA��ǩ
���룺params ǩ����������map
      publicKey ��Կ
      charset ǩ�������ʽ
�������ǩ���

public static boolean rsaCheckContent(String content, String sign, String publicKey,String charset)
���ܣ�RSA��ǩ
���룺content ǩ�����������ַ���
      sign ǩ��
      publicKey ��Կ
      charset ǩ�������ʽ
�������ǩ���

��������������������������������������������������������������