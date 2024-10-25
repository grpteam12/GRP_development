# Employee Rating Optimization with Z3 Solver - 安装指南

## 概述

本项目是一个基于Spring Boot的后端应用程序，旨在使用Z3求解器对员工评分进行优化处理。应用程序功能包括：
1. 接收评分数据作为输入。
2. 使用Z3求解器计算优化的无偏评分。
3. 返回优化后的评分以及管理者的偏差调整。

此文档将指导您完成项目的环境设置，包括依赖项安装、Z3求解器配置以及项目运行。

---

## 前置条件

### 1. Java开发工具包 (JDK)
- **版本**：JDK 11或更高
- **安装**：[下载JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)并按照操作系统的安装说明进行安装。
- **验证**：
  ```bash
  java -version
  ```

### 2. Apache Maven
- **版本**：Maven 3.6或更高
- **安装**：[下载Maven](https://maven.apache.org/download.cgi)并按照安装说明进行配置。
- **验证**：
  ```bash
  mvn -version
  ```

### 3. Z3求解器
- **版本**：Z3 4.13.3或兼容版本
- **安装步骤**：
  - 从[Z3官方GitHub仓库](https://github.com/Z3Prover/z3/releases)下载Z3求解器。
  - 将文件解压到某个目录，例如`/home/username/z3-4.13.3/`。
  - 确保目录包含以下文件：
    - `libz3java.so`（Linux）或`z3.dll`（Windows）
    - `z3.jar`

### 4. 在本地Maven仓库配置Z3
  将Z3的`z3.jar`文件添加到本地Maven仓库：
  ```bash
  mvn install:install-file -Dfile=/path/to/z3.jar -DgroupId=com.microsoft -DartifactId=z3 -Dversion=4.13.3 -Dpackaging=jar
  ```

---

## 项目设置

### 1. 克隆代码库
从源代码仓库中克隆项目：
```bash
git clone <repository-url>
cd Employee-Rating-Optimization-with-Z3-Solver
```

### 2. 配置`pom.xml`
确保在`pom.xml`中包含Z3依赖项：
```xml
<dependency>
    <groupId>com.microsoft</groupId>
    <artifactId>z3</artifactId>
    <version>4.13.3</version>
    <scope>system</scope>
    <systemPath>${project.basedir}/libs/z3.jar</systemPath>
</dependency>
```
> **注意**：调整`systemPath`以匹配您的`z3.jar`文件路径。

### 3. 配置Z3本地库路径

由于Z3包含本地库，请配置JVM以找到这些库文件。

#### Linux系统
将包含`libz3java.so`的目录添加到`java.library.path`中：
```bash
export LD_LIBRARY_PATH=/path/to/z3/bin:$LD_LIBRARY_PATH
```

#### Windows系统
将包含`z3.dll`的路径添加到`PATH`环境变量中：
1. 打开“系统属性”中的“环境变量”。
2. 编辑`PATH`变量，将`z3.dll`所在路径添加进去。

或者，在`VM选项`中添加以下参数：
```bash
-Djava.library.path="/path/to/z3/bin"
```

---

## 配置

### 应用程序配置
根据需要更新Spring Boot项目的`application.properties`文件：
```properties
spring.application.name=Employee Rating Optimization with Z3 Solver
server.port=8081
spring.main.allow-bean-definition-overriding=true
```

### Vaadin配置
如果项目中使用了Vaadin框架，请确保设置以下属性：
```properties
vaadin.launch-browser=true
```

---

## 运行应用程序

### 1. 构建项目
在项目根目录中执行：
```bash
mvn clean install
```

### 2. 启动应用程序
```bash
mvn spring-boot:run
```

### 3. 访问应用程序
- 在浏览器中打开：`http://localhost:8081`

---

## 故障排查

### 常见错误

- **UnsatisfiedLinkError**：请确保`java.library.path`已正确设置为包含Z3本地库文件（`libz3java.so`或`z3.dll`）的目录。
- **依赖解析问题**：请检查`z3.jar`已正确安装到本地Maven仓库，且`pom.xml`中的路径设置正确。

---

## 其他说明

- **更新Z3**：如果升级Z3，请确保同时更新`.jar`文件和本地库文件，并重新配置路径。
- **Docker支持**：如果需要Docker进行部署，可根据需求添加`Dockerfile`并进行环境配置。
