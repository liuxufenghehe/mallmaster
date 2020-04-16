测试机器：192.168.1.102，均为单机版
#rabbitmq
1.   cd /usr/lib/rabbitmq/bin
2.   ./rabbitmq-server start
3.   访问：http://hadoop102:15672   guest/guest(默认)

#mongodb
1.  cd /opt/software/mongodb/bin
2.  ./mongod  -f /opt/software/mongobd/mongodb/conf/mongo.conf 
#   如果上次非正常关机：删除 data文件夹下mongod.lock

#elsticsearch
1.  cd /opt/software/Elasticsearch/elasticsearch-6.2.4/bin
2.  ./elasticsearch -d
3.  访问：http://hadoop102:9200/
#   删除logs下root生成my-application-es.log
#   切换用户  es/es  

#redis
1.  cd /opt/software/redis/redis-5.0.5/bin
2.  ./redis-server &



#项目访问 admin/123456  test/123456
