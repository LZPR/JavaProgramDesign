# Java 程序设计

课程名称：JAVA程序设计/Java Programming

课程代码：021127

## Features

- [x] 所有的实验内容
- [x] 基于网页实现
- [ ] 日志及统一的异常处理
- [ ] 功能优化
- [ ] 样式表美化
- [ ] 单元测试

## 结构

使用 Jakarta EE (JSP) 框架，基于 MVC 架构。

- `common`: 包含常量定义、工具类及实验所需的基本内容。
- `controller`: 对应 MVC 中的 Controller，存放所有 Servlet。
- `service`：服务层，负责主要业务逻辑。
- `entity`: 对应 MVC 中的 Model，存放所有实体（Entity）。VO、DTO、PO 暂时三合一。
- `dao`: 数据访问层，负责与数据库连接、关闭、增删改查。
- `webapp`: 对应 MVC 中的 View，存放所有网页（JSP 文件）和样式表。

## 部署

*以下步骤仅供参考*

1. 安装开发环境所需的软件。
2. 在 MySQL 中导入**数据库定义文件**(位于 `./java_program_design.sql`)。
3. 在 IDEA 中导入本项目。
4. 添加 Tomcat Server 运行配置。
5. 设置环境变量。

### 环境变量

| 名称       | 解释      | 参考值                                                                       |
|----------|---------|---------------------------------------------------------------------------|
| JDBC_URL | 数据库连接地址 | `jdbc:mysql://localhost:3306/java_program_design?user=root&password=root` |

### 环境需求

- [Tomcat 10](https://tomcat.apache.org/download-10.cgi)
- [MySQL 8](https://dev.mysql.com/downloads/installer/)

## 协作

*以下步骤仅供参考*

1. 在 Github 中 Fork 本仓库
2. 克隆仓库至本地：`git clone <Fork 仓库地址>`
3. 添加上游仓库：`git remote add upstream <本仓库地址>`
4. 查看远程仓库：`git remote -v`
5. 提交至本地仓库：`git commit`
6. 与原仓库的主分支进行同步：`git pull upstream main`
7. 推送至 Fork 的仓库：`git push origin main`
8. 发起 Pull Request，请求合并至原仓库