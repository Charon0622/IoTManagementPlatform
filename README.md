## IoT 数据存储、展示部分

- Cassandra Background 后端即 Cassandra 数据库配置文档：[CassandraBackground](./CassandraBackground/CassandraBackground.md)

- 选择一：本地部署后端

  - 步骤一：用户管理后端（Django）

  > cd Lightning
  >
  > python3 manage.py runserver 127.0.0.1:port

  - 步骤二：修改前端配置（Vue）

  > 修改前端配置文件LightningPredict/config/index.js中的baseUrl（对应Django），databaseUrl（对应Spring boot）

  > cd LightningPredict 
  >
  > npm install
  >
  > npm start

- 选择二：使用服务器上的后端

  - 步骤一：修改前端配置（Vue）

  > 修改前端配置文件LightningPredict/config/index.js中的baseUrl（对应Django）为：http://10.60.38.173:8212/，databaseUrl（对应Spring boot）为：http://10.60.38.173:8000/

  > cd LightningPredict 
  >
  > npm install
  >
  > npm start