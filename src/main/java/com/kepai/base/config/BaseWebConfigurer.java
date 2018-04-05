package com.kepai.base.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.kepai.base.ResultException;
import com.kepai.base.HttpResult;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author huang
 * @ProjectName elderly
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/4/4
 * @note 这里写文件的详细功能和改动
 * @note
 */
public abstract class BaseWebConfigurer extends WebMvcConfigurerAdapter {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteEnumUsingName,
                SerializerFeature.WriteNullNumberAsZero);
        config.setDateFormat("yyyy-MM-dd HH:mm:ss");
        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.TEXT_HTML);
        converter.setSupportedMediaTypes(mediaTypes);
        converters.add(converter);
    }

    /**
     * 统一异常处理
     *
     * @param exceptionResolvers
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
                //返回异常信息
                HttpResult httpResult = null;
                if (e instanceof ResultException) {
                    //自定义异常
                    httpResult = ((ResultException) e).getHttpResult();
                } else if (e instanceof IOException) {
                    //暂不处理，大部分是由于用户主动断开连接导致

                } else if (e instanceof BindException) {
                    httpResult = HttpResult.respCust(110, "参数格式不正确,请检查");
                } else if (e instanceof HttpRequestMethodNotSupportedException) {
                    httpResult = HttpResult.respCust(110, "不支持的请求方式，请检查");
                } else {
                    httpResult = resolveExceptions(request, response, o, e);
                }
                respResult(response, httpResult);
                return new ModelAndView();
            }
        });
    }

    /**
     * 处理其他异常情况
     *
     * @param request
     * @param response
     * @param o
     * @param e
     * @return
     */
    public abstract HttpResult resolveExceptions(HttpServletRequest request, HttpServletResponse response, Object o, Exception e);

    /**
     * 返回数据
     *
     * @param response
     * @param result
     */
    private void respResult(HttpServletResponse response, HttpResult result) {
        if (result == null) {
            return;
        }
        try {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.print(JSON.toJSONString(result));
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
