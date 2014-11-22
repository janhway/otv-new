otv-new
=======
视频元数据中心：
0.安装mysql
1.执行otv.sql创建数据库
2.编译打包执行
cd otv-new\MediaData
mvn clean package
java -jar java -jar target\MediaData-0.0.1-SNAPSHOT.jar

爬虫：
1.编译打包执行
cd otv-new\spider
mvn clean package
java -jar target\spider-0.0.1-SNAPSHOT.jar


