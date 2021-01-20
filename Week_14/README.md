### 自定义MQ

已实现如下功能：

1. 自定以Queue，利用Message数组实现循环队列以降低OOM的风险

2. 拆分broker和client，将Queue保存在broker端

3. 设计单独的读写Api，确认，提交offset接口

4. broker和client基于netty tcp进行数据通信

逐步完善中...