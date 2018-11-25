# Roc
Roc是一款生成随机数据并写入到各种数据源的工具，用于大数据相关从事人员进行数据的模拟测试。

# Features
通过配置生成随机数据的规则，利用Writer插件，将数据写入指定的数据源中。其中Writer插件可以基于现有的抽象类自行实现，便于扩展。

# Quick Start
即将上线，敬请期待！

# 源起
本人从事于大数据行业，但是目前所在公司的数据量级不够，因此有些数据的实验无法开展，因此便考虑模拟数据，希望此项目在今后的大数据学习中能够发挥很大的用处。

# 站在他人的肩膀上
1. 架构参考了阿里的[Datax](https://github.com/alibaba/datax)
2. 生成随机数据的规则参考了[Mock.js](https://github.com/nuysoft/Mock)

# 计划
1. Quick Start文档
2. MysqlWriter
3. JVM性能监控
4. 快速生成json文件模板的工具
5. Mock函数完善
6. KafkaWriter
7. Roc on Yarn