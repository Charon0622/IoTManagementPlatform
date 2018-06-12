## 说明文档

### Cassandra 配置

#### 先决环境

测试的系统环境为 Ubuntu 14.04 LTS

安装 Cassandra 需要首先安装 Java 8 环境，Oracle Java Standard Edition 8 或者 OpenJDK 8 均可。OpenJDK 8 的安装方法如下：

```shell
$ sudo apt-get update
$ sudo apt-get install openjdk-8-jdk
$ sudo apt-get install openjdk-8-jre
```

如果想使用 cqlsh，需要安装 Python 2.7 版本，安装方法如下：

```shell
$ sudo apt-get update
$ sudo apt-get install python2.7 python-pip
```

#### 利用二进制文件安装

从 [Cassandra](http://cassandra.apache.org/download/) 官网下载所需要的安装包，可以使用如下方法下载：

```shell
$ wget https://mirrors.tuna.tsinghua.edu.cn/apache/cassandra/3.11.2/apache-cassandra-3.11.2-bin.tar.gz
```

解压文件：

```shell
$ tar -xvf apache-cassandra-3.11.2-bin.tar.gz
$ mv apache-cassandra-3.11.2 cassandra
```

可以添加 Cassandra 的路径到系统环境中，方便之后操作。

```shell
$ export CASSANDRA_HOME = ./cassandra
$ export PATH = $PATH:$CASSANDRA_HOME/bin
```

之后修改 ./cassandra/conf/cassandra.yaml 文件部分配置，具体如下：

```yaml
rpc_address: 10.60.38.173（你想作为远程连接的 ip）
```

这样就完成了 cassandra 的单节点配置。如果想启动 cassandra，可以进行如下操作：

```shell
$ cassandra/bin/cassandra -f # 在命令行前台运行
$ cassandra/bin/cassandra # 在命令行后台运行
$ cassandra/bin/nodetoll status # 可以查看当前的 cassandra 的节点状态
```

### Cassandra 的 java 后端配置

