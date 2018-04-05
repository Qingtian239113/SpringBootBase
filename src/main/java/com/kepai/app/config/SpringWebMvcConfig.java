package com.kepai.app.config;

import com.kepai.app.annotation.ParamValidator;
import com.kepai.base.ResultException;
import com.kepai.base.HttpResult;
import com.kepai.base.config.BaseWebConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author huang
 * @ProjectName health
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2017/8/8
 * @note 这里写文件的详细功能和改动
 * @note
 */
@Configuration
public class SpringWebMvcConfig extends BaseWebConfigurer {

    @Override
    public HttpResult resolveExceptions(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        JavaMailConfig.sendBugMail(request, e);
        return HttpResult.respError();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) {

                //验证必要参数
                paramValidator(request, o);

                return true;
            }

            @Override
            public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {

            }

            @Override
            public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

            }
        });
    }

    /**
     * 验证数据必要参数是否为空
     *
     * @param request
     * @param handler
     */
    private void paramValidator(HttpServletRequest request, Object handler) {
        if (!ClassUtils.isAssignableValue(HandlerMethod.class, handler)) {
            return;
        }
        HandlerMethod method = (HandlerMethod) handler;
        ParamValidator validator = method.getMethodAnnotation(ParamValidator.class);
        if (validator == null) {
            return;
        }
        for (String param : validator.params()) {
            if (StringUtils.isEmpty(request.getParameter(param))) {
                throw new ResultException(HttpResult.respNeedParam(param));
            }
        }
    }


}
