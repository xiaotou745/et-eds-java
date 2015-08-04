package com.edaisong.business.common.filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PermissionFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletReponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletReponse;

        //MisUser misUser = MisUserUtil.getCurrentMisUser(request);

        String uri=request.getServletPath().split("\\.")[0];
        if(uri.endsWith("/")) 
        	{
        	uri = uri.substring(0, uri.length()-1);
        	}

        //�жϸ�uri�Ƿ���ҪȨ����֤
//        int permissionCode = this.getPermissionCode_MutiLevel(uri);
//        if (permissionCode== PermissionConfig.UNDEFINE) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        String failUri = "";
      Object  misUser=null;
       // if(null==misUser){
            //request.setAttribute("message", "�ף��޷���ȡ���Ȩ����Ϣ�������µ�¼mis��");
            //request.getRequestDispatcher(failUri).forward(request,response);
            //return;
        //}
        // Ȩ����֤�ж�

        //if (!misUser.isIllegal(permissionCode)) {
            // Ȩ��δͨ��
            //request.setAttribute("message", "�ף���û��Ȩ�޷��ʸ���Ϣ����������ʵ��˻���¼mis��");
            //request.getRequestDispatcher(failUri).forward(request, response);
            //return;
        //}
        filterChain.doFilter(request, response);// Ȩ��ͨ��
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}
