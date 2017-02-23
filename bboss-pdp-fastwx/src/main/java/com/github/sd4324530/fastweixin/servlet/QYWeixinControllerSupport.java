package com.github.sd4324530.fastweixin.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.frameworkset.util.annotations.ResponseBody;


import com.github.sd4324530.fastweixin.message.aes.AesException;
import com.github.sd4324530.fastweixin.message.aes.WXBizMsgCrypt;
import com.github.sd4324530.fastweixin.util.StrUtil;

/**
 * 微信企业平台交互操作基类
 * ====================================================================
 * 上海聚攒软件开发有限公司
 * --------------------------------------------------------------------
 *
 * @author Nottyjay
 * @version 1.0.beta
 * ====================================================================
 */
 
public abstract  class QYWeixinControllerSupport extends QYWeixinSupport {

    /**
     * 绑定微信服务器
     *
     * @param request 请求
     * @return 结果
     */
    @ResponseBody
    public final String bind(HttpServletRequest request){
        return legalStr(request);
    }

    /**
     * 微信消息交互处理
     *
     * @param request 请求
     * @param response 响应
     * @return 结果
     * @throws ServletException servlet异常
     * @throws IOException IO异常
     */
    @ResponseBody
    public final String process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//        if(StrUtil.isBlank(legalStr(request))){
//            return "";
//        }
        String result = processRequest(request);
        response.getWriter().write(result);
        return null;
    }

    /**
     * 验证请求URL是否正确的方法
     *
     * @param request 请求
     * @return 结果
     */
    protected String legalStr(HttpServletRequest request){
        String echoStr = "";
        if(StrUtil.isBlank(getToken()) || StrUtil.isBlank(getAESKey()) || StrUtil.isBlank(getCropId())){
            return echoStr;
        }
        try {
            WXBizMsgCrypt pc = new WXBizMsgCrypt(getToken(), getAESKey(), getCropId());
            echoStr = pc.verifyUrl(request.getParameter("msg_signature"), request.getParameter("timestamp"), request.getParameter("nonce"), request.getParameter("echostr"));
        } catch (AesException e) {
            e.printStackTrace();
        }
        return echoStr;
    }
}
