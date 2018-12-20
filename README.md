# bboss group website:
http://www.bbossgroups.com

# bboss group project blog:
http://yin-bp.iteye.com/

# bboss pdp源码
github托管地址： 

https://github.com/bbossgroups/bboss-pdp 

oschina托管地址:
https://git.oschina.net/bboss/bboss-pdp



## bboss项目特色特点介绍文档：
http://yin-bp.iteye.com/blog/1080824

## pinpoint config

-javaagent:F:/6_environment/blackcat/pinpoint-agent-1.6.2/pinpoint-bootstrap-1.6.2.jar -Dpinpoint.agentId=pdpagent -Dpinpoint.applicationName=pdp"

## License

The BBoss Framework is released under version 2.0 of the [Apache License][].

[Apache License]: http://www.apache.org/licenses/LICENSE-2.0

## 部署和运行平台源码工程

### 第一步 数据源修改，两个地方

dbinit-system/resources/dbcp-mysql.xml
bboss-pdp-web/resources/dbcp-mysql.xml

### 第二步 初始化db，运行指令

https://m.gitee.com/bboss/bboss-pdp/blob/master/dbinit-system/startup.bat

### 第三步 构建和发布平台版本

在工程根目录运行指令

gradle install

构建成功后，部署bboss-pdp-web/build/libs/目录下面的war文件即可

bboss-pdp-web/build/libs/bboss-pdp-web-5.1.2.war

默认访问账号和口令(线上系统请注意修改默认口令):

admin/123456

## 搭建基于平台的开发环境参考文档

http://yin-bp.iteye.com/blog/2356672

## app登录
/sso/applogin.page
入参:
userName  -账号
password  -口令

http://localhost/sso/applogin.page?userName=admin&password=1234567
登录成功返回值：
{
​	"code": "200",
​	"message": "success",
​	"user": {
​		"userSex": "M",
​		"theme_style": "darkblue",
​		"title": "系统管理员(admin)",
​		"userName": "系统管理员",
​		"userID": "1",
​		"worknumber": "1",
​		"LOGINSTYLE_CACHE_KEY": "5",
​		"REMOTEADDR_CACHE_KEY": "127.0.0.1",
​		"userAccount": "admin",
​		"telphone": " 18807409059",
​		"departId": null,
​		"theme": "admin_1",
​		"depart": null,
​		"job": ""
​	},
​	"systemid": "module",
​	"systemName": "BBOSS开发平台",
​	"language": null,
​	"defaultmodulename": null,
​	"clientIP": "127.0.0.1",
​	"fromsso": false,
​	"sessionId": "BB03A9A4BD7BCA6A7D07AA77ACBDCA8A"
}

登录成功后每次请求应用带sessionId对应的cookie信息：
cookie名称：JSESSIONID
cookie值：对应返回数据的sessionid的值

## 登出
http://localhost/sso/logout.page
登录失败返回值：
{
​	"code": "500",
​	"message": "登录失败！请确保用户名和密码是否正确！",
​	"user": null,
​	"systemid": "module",
​	"systemName": "BBOSS开发平台",
​	"language": null,
​	"defaultmodulename": null,
​	"clientIP": "127.0.0.1",
​	"fromsso": false,
​	"sessionId": null
}


