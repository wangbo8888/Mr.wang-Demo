//package com.example.demo.interceptor;
//
//import com.example.demo.util.RedisUtil;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @BelongsProject: Mr.wang-Demo
// * @BelongsPackage: com.example.demo.interceptor
// * @Author: Mr.wang
// * @Date: 2022/6/5 12:44
// * @Description: Ip拦截器 ip+url重复请求现在拦截器
// */
//public class IpUrlLimitInterceptor implements HandlerInterceptor {
//
//    @Resource
//    private RedisUtil redisUtil;
//
//    private static final String LOCK_IP_URL_KEY = "lock_ip_";
//
//    private static final String IP_URL_REQ_TIME = "ip_url_times_";
//
//    private static final long LIMIT_TIMES = 5;
//
//    private static final int IP_LOCK_TIME = 60;
//
//    @Override
//    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        log.info("request请求地址uri={},ip={}", httpServletRequest.getRequestURI(), IpAdrressUtil.getIpAdrress(httpServletRequest));
//        if (ipIsLock(IpAdrressUtil.getIpAdrress(httpServletRequest))){
//            log.info("ip访问被禁止={}",IpAdrressUtil.getIpAdrress(httpServletRequest));
//            ApiResult result = new ApiResult(ResultEnum.LOCK_IP);
//            returnJson(httpServletResponse, JSON.toJSONString(result));
//            return false;
//        }
//        if(!addRequestTime(IpAdrressUtil.getIpAdrress(httpServletRequest),httpServletRequest.getRequestURI())){
//            ApiResult result = new ApiResult(ResultEnum.LOCK_IP);
//            returnJson(httpServletResponse, JSON.toJSONString(result));
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
//
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
//
//    }
//
//    /**
//     * @Description: 判断ip是否被禁用
//     * @author: shuyu.wang
//     * @date: 2019-10-12 13:08
//     * @param ip
//     * @return java.lang.Boolean
//     */
//    private Boolean ipIsLock(String ip){
//        if(redisUtil.hasKey(LOCK_IP_URL_KEY+ip)){
//            return true;
//        }
//        return false;
//    }
//    /**
//     * @Description: 记录请求次数
//     * @author: shuyu.wang
//     * @date: 2019-10-12 17:18
//     * @param ip
//     * @param uri
//     * @return java.lang.Boolean
//     */
//    private Boolean addRequestTime(String ip,String uri){
//        String key=IP_URL_REQ_TIME+ip+uri;
//        if (redisUtil.hasKey(key)){
//            long time=redisUtil.incr(key,(long)1);
//            if (time>=LIMIT_TIMES){
//                redisUtil.getLock(LOCK_IP_URL_KEY+ip,ip,IP_LOCK_TIME);
//                return false;
//            }
//        }else {
//            redisUtil.getLock(key,(long)1,1);
//        }
//        return true;
//    }
//
//    private void returnJson(HttpServletResponse response, String json) throws Exception {
//        PrintWriter writer = null;
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/json; charset=utf-8");
//        try {
//            writer = response.getWriter();
//            writer.print(json);
//        } catch (IOException e) {
//            log.error("LoginInterceptor response error ---> {}", e.getMessage(), e);
//        } finally {
//            if (writer != null) {
//                writer.close();
//            }
//        }
//    }
//
//}
