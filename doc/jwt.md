# 博客功能开发文档
1. 首先用户登录成功生成唯一的UUID
2. 将UUID生成Token
3. redis保存Token-login对应关系
4. 返回Token
5. 客户端保存
6. 其他API校验Token

+ redis配置
+ jwt配置

> 权限设置 内部人员（2）、外部人员（1） （隐私文章（2） 公开文章（1））