package com.example.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component  // 可以扫描到对象
public class logAspect {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.blog.web.*.*(..))")
    public void log(){}

    //切面之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //切面之前获取url,ip
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        //通过AOP获取类名+方法名
        String classMethod = joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
        //通过AOP来接受请求参数
        Object[] args = joinPoint.getArgs();

        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);
        logger.info("Request+{}",requestLog);
    }
    //切面之后
    @After("log()")
    public void doAfter(){
        logger.info("------doAfter-------");
    }

    //方法返回的内容 ,通过result来捕获拦截方法的内容
    @AfterReturning(returning = "result",pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result:{}",result);
    }

    //封装的内部类
    private class RequestLog{
        private String url;
        private String ip;
        private String classMethod;
        private Object[] args;

        //全参构造
        public RequestLog(String url, String ip, String classMethod, Object[] args) {
            this.url = url;
            this.ip = ip;
            this.classMethod = classMethod;
            this.args = args;
        }

        @Override
        public String toString() {
            return "RequestLog{" +
                    "url='" + url + '\'' +
                    ", ip='" + ip + '\'' +
                    ", classMethod='" + classMethod + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
 }
