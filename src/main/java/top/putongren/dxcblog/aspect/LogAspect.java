package top.putongren.dxcblog.aspect;

import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.putongren.dxcblog.annotation.Log;
import top.putongren.dxcblog.common.utils.HttpClientUtil;
import top.putongren.dxcblog.common.utils.JSONUtils;
import top.putongren.dxcblog.common.utils.WebUtils;
import top.putongren.dxcblog.model.LogDO;
import top.putongren.dxcblog.model.UserDO;
import top.putongren.dxcblog.service.LogService;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Date;

/**
 * @ClassName: LogAspect
 * @Description:
 * @Author dxc
 * @Date: 2021/5/26
 */
@Aspect
@Component
public class LogAspect {
    @Autowired
    private LogService logService;

    @Pointcut("@annotation(top.putongren.dxcblog.annotation.Log)")
    public void logPointCut(){}

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    public void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        Log log = method.getAnnotation(Log.class);
        if (log != null) {
            // 注解上的描述
            sysLog.setOperation(log.value());
        }
        //请求方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        //请求参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = Arrays.toString(args);
            //太长的没啥意义
            if (params.length() > 4999) {
                params = null;
            }
            sysLog.setParams(params);
            String params2 = JSONUtils.objectToJson(args[0]);
            if (params2.length() > 4999) {
                params2 = null;
            }
            sysLog.setParams2(params2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 获取request
        HttpServletRequest request = HttpClientUtil.getHttpServletRequest();
        // 设置IP地址
        sysLog.setIp(WebUtils.getIpAddress(request));
        // 用户名
        UserDO currUser = (UserDO) SecurityUtils.getSubject().getPrincipal();
        if (null == currUser) {
            if (null != sysLog.getParams2()) {
                sysLog.setUserId(-1L);
                sysLog.setUsername(sysLog.getParams2());
            } else {
                sysLog.setUserId(-1L);
                sysLog.setUsername("获取用户信息为空");
            }
        } else {
            sysLog.setUserId(currUser.getId());
            sysLog.setUsername(currUser.getUsername());
        }
        sysLog.setTime((int) time);
        // 系统当前时间
        Date date = new Date();
        sysLog.setCreateTime(date);
        // 保存系统日志
        logService.save(sysLog);
    }
}
