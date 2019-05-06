# 学习SpringCloud例子

### Spring Cloud Config的两种方式：本地和远程（github）
1. 本地配置如下：
~~~
#本地配置如下：1.先指定native关键字 2.指定配置文件的路径(配置文件命名规则：${applicationName}-${profile}.yml或者${applicationName}-${profile}.properties)
spring:
  profiles:
    active: native
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/properties/
          
~~~
2. 远程（github）配置如下：
~~~
#网络配置如下：1.指定配置文件所在的github仓库 2.指定具体目录 3.设置github的用户名、密码（公有库不需要指定） 4.指定分支名
spring:    
  cloud:
    config:
      server:
        git:
          uri: https://github.com/TianLuhua/SpringcloudConfig
          search-paths: config
          username:
          password:
      label: master
~~~

### 为了提高Eureka的安全性，添加http basic认证
1.在Eureka server端添加security依赖
~~~
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-security</artifactId>
        </dependency>
~~~
2.在配置文件（application.yml）中使能basic和设置访问的用户和密码：
~~~
spring:
  security:
      basic:
        enabled: true
      user:
        name: admin
        password: admin
~~~
3 .由于spring-boot-security默认开启了csrf校验，对于client端这类非界面应用来说不合适，但是又没有配置文件的方式可以禁用，需要自己通过代码来禁用。
~~~
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.csrf().disable();
    }
}
~~~
4 . 修改客户端配置：
~~~
eureka:
  client:
    security:
      basic:
        user: admin
        password: admin
    service-url:
      defaultZone: http://${eureka.client.security.basic.user}:${eureka.client.security.basic.password}@localhost:8761/eureka/
~~~