#### 一个基于SpringBoot+Mybatis+通用Mapper的基础框架

主要使用：
 - SpringBoot 1.5.10.RELEASE
 - 通用Mapper 1.2.3
 - FastJson 1.2.47
 - druid 1.1.2
 
 项目结构：
 -  libs：存放jar文件
 - src/main
   - java.com.kepai
        - app 项目代码
          - action 接口层
          - annotation 自定义注解层
            - ParamValidator 检查参数是否为空
          - config 自定义配置层
            - DataSourceConfig 阿里连接池配置
            - JavaMailConfig 邮件发送配置
            - MapperEnumConfig Mapper通用枚举类型注册
            - SpringContext 
            - SpringWebMvcConfig 统一异常处理、参数为空检查等等
          - enums 自定义枚举层，数据库中的字段尽量使用枚举
            - BaseEnum 所有需要转化为数据库字段的必须实现该接口
            - EnumTypeHandler 通用转化类
            - UserSex 例子：用户性别
          - mapper Mybatis映射
          - pojos 项目Java对象
            - table 数据库表存放
            - CommonParam 公共参数
            - LoginToken 用户登录凭证类
          - service 服务层
          - utils 项目方法
          - AppApplication 项目启动类
          - AppConstants 项目常量
          - AppProperties 配置文件中的属性
        - base 通用代码（与项目业务无关）
          - config 基本配置
            - BaseWebConfigurer FastJson配置、通用异常配置
          - crud 基本单表查询
          - encrypt 基本加密
          - utils 工具包
            - BeanHelper 属性转化
            - KLogger 日志打印
            - NumberParse number类型转化
            - PicVerifyHelper 图片验证码生成
            - ThreadHelper 线程管理
          - HttpResult 统一接口返回对象
          - ResultException 统一抛出异常
        - third 第三方框架
   - resource 资源存放
    