# DivideMe

一个使用 Scala 3 和 sbt 构建的项目。

## 项目简介

DivideMe 是一个基于 Scala 3 的示例项目，演示了基本的 Scala 编程结构。

## 技术栈

- **Scala**: 3.8.3
- **构建工具**: sbt
- **JDK**: 需要 JDK 21

## 项目结构

```
DivideMe/
├── build.sbt              # sbt 构建配置文件
├── project/               # sbt 项目配置目录
│   └── build.properties   # sbt 版本配置
├── src/
│   └── main/
│       └── scala/
│           └── main.scala # 主程序入口
└── .gitignore             # Git 忽略规则
```

## 快速开始

### 环境要求

- JDK 11+
- sbt 1.x

### 运行项目

```bash
sbt run
```

### 编译项目

```bash
sbt compile
```

## 许可证

本项目采用 自定义许可证，详见 [LICENSE](LICENSE) 文件。