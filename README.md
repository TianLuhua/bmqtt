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