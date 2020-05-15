# librarian 

## summary
    本项目的功能核心是数据治理，子功能包括元数据管理、数据资产管理、数据地图、数据血缘等。
    项目采用多模块可插拔设计，支持同一组件多个版本同时管理。在此同时，可以

## architecture
``` 
librarian
    ----librarian-web-ui                     # web ui 管理界面 
    ----librarian-common                     # 工具包
    ----librarian-public-beans               # 公共类
    ----librarian-connectors                 # 数据源连接器
    ----librarian-open-api                   # 开放接口
    ----mysql5-data-service                  # MySQL5.x版本数据源服务
    ----elasticsearch7-data-service          # es7.x版本数据源服务
    ----service-config-center-server-10000   # 配置中心（暂时不用）
    ----micro-service-eureka-10001           # 服务治理中心（暂时不用）

```
## 功能介绍
### 元数据管理
#### 创建元数据

#### 导入元数据

#### 导出元数据

#### 更新元数据

#### 删除元数据

#### 检索元数据

### 数据资产管理
#### 查看数据资产详情

#### 数据资产样本查看

#### 数据资产样本导出

#### 数据资产变化趋势监控

### 数据血缘
#### 数据血缘关系查看

### 数据地图
#### 



## 模块介绍

### librarian-web-ui      
             
### librarian-common
                   
### librarian-public-beans
             
### librarian-connectors
               
### librarian-open-api
                 
### mysql5-data-service
                
### elasticsearch7-data-service
        
### service-config-center-server-10000
 
### micro-service-eureka-10001         

