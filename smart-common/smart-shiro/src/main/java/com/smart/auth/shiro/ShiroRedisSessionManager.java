package com.smart.auth.shiro;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionKey;
import org.apache.shiro.web.util.WebUtils;


import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;


/**
 * 版本一定要兼容Shiro
 *
 * 请求头中 放置用户加密后的权限KEY 保存到redis 中
 *
 */

public class ShiroRedisSessionManager extends DefaultWebSessionManager {
    public static final String AUTHORIZATION = "Authorization";
    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    /**
     * 获取请求头中的数据 判断用户的权限
     * @param request
     * @param response
     * @return
     */
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头中参数 如果没有则是Token 中的参数
       String id= StringUtils.isEmpty(WebUtils.toHttp(request).getHeader(AUTHORIZATION))?
                request.getParameter(AUTHORIZATION):WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头中有Token 则其值为sessionId
        if(StringUtils.isEmpty(id)){
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,id);
            //是否 需要验证
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return id;
        }else {
            //否则按照迷人的规则从cookie 取sessionId
            return super.getSessionId(request,response);
        }
    }

    /**
     * 获取session 优化单次请求多从访问redis的问题
     * @param sessionKey
     * @return
     * @throws UnknownSessionException
     */
    @Override
    protected Session retrieveSession(SessionKey sessionKey) throws UnknownSessionException {
        Serializable sessionId= getSessionId(sessionKey);
        ServletRequest request=null;
        if(sessionKey instanceof WebSessionKey){
            request = ((WebSessionKey) sessionKey).getServletRequest();
        }
        if(request!=null && null!=sessionId){
            Object object = request.getAttribute(sessionId.toString());
            if(object!=null){
                return (Session) object;
            }
        }
        Session session = super.retrieveSession(sessionKey);
        if (request != null && null != sessionId) {
            request.setAttribute(sessionId.toString(), session);
        }
        return session;
    }
}
