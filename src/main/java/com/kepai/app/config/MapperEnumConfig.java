package com.kepai.app.config;

import com.kepai.app.enums.BaseEnum;
import com.kepai.app.enums.EnumTypeHandler;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author huang
 * @ProjectName wine
 * @Copyright Hangzhou ShuoChuang Technology Co.,Ltd All Right Reserved
 * @Description 这里是对文件的描述
 * @data 2018/3/28
 * @note 这里写文件的详细功能和改动
 * @note
 */
@Configuration
public class MapperEnumConfig {

    private final static String ENUM_LOCATIONS =
            EnumTypeHandler.class.getPackage().getName().replaceAll("\\.", "/");

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        // 设置配置文件地址
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactory sqlSessionFactory = factory.getObject();
        // 取得类型转换注册器
        TypeHandlerRegistry typeHandlerRegistry = sqlSessionFactory.getConfiguration().getTypeHandlerRegistry();
        // 扫描所有实体类
        Resource[] resources = resolver.getResources("classpath*:" + ENUM_LOCATIONS + "/**/*.class");
        for (Resource resource : resources) {
            String uri = resource.getURI().toString();
            int start = uri.indexOf(ENUM_LOCATIONS);
            String className = uri.substring(start)
                    .replace('/', '.').replaceAll("\\.class", "");
            Class<?> aClass = Class.forName(className, false, getClass().getClassLoader());
            if (aClass.isEnum() && BaseEnum.class.isAssignableFrom(aClass)) {
                typeHandlerRegistry.register(className, EnumTypeHandler.class.getName());
            }
        }
        return sqlSessionFactory;
    }

}
