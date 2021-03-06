### 插入100w条数据方式展示
1. 使用拼接整个sql的方式多value插入数据
```java
        sqlSB.append("insert into ES_USER(userId,username,displayName)values(" + FOR_INDEX++ + ",\"张三" + FOR_INDEX + "\",\"张先生" + FOR_INDEX + "\")");
        while (FOR_INDEX < 100_0001) {
            sqlSB.append(",(" + FOR_INDEX++ + ",\"张三" + FOR_INDEX + "\",\"张先生" + FOR_INDEX + "\")");
            if (FOR_INDEX % 10_0000 == 0) {
                PreparedStatement statement = connection.prepareStatement(sqlSB.toString());
                statement.executeUpdate();
                sqlSB = new StringBuilder();
                sqlSB.append("insert into ES_USER(userId,username,displayName)values(" + FOR_INDEX++ + ",\"张三" + FOR_INDEX + "\",\"张先生" + FOR_INDEX + "\")");
            }
        }
        System.out.println("type 1 time keep:" + (System.currentTimeMillis() - startTimeMills));
```
*结果如下*：
一次提交数量对应的时长大致为：
+ 100w: 8817ms 
+ 10w: 8472ms 
+ 1w: 9465ms

2. 参数方式批量提交，关闭自动提交
```java
connection.setAutoCommit(false);
        sqlSB.append("insert into ES_USER(userId,username,displayName)values(?,?,?)");
        PreparedStatement statement2 = connection.prepareStatement(sqlSB.toString());
        while (FOR_INDEX++ < 100_0001) {
            statement2.setInt(1, FOR_INDEX);
            statement2.setString(2,"张三"+ FOR_INDEX);
            statement2.setString(3,"张先生"+ FOR_INDEX);
            statement2.addBatch();
            if (FOR_INDEX %1_0000==0){
                statement2.executeBatch();
                connection.commit();
            }
        }
        System.out.println("type 2 time keep:" + (System.currentTimeMillis() - startTimeMills));
```
*结果展示*：
时长超过100s

3. 参数方式加参数拼接
```java
connection.setAutoCommit(false);
        sqlSB.append("insert into ES_USER(userId,username,displayName)values");
        int count = 0;
        int commitCount = 10000;
        while (FOR_INDEX++ < 100_0001) {
            if (FOR_INDEX % commitCount != 1) {
                sqlSB.append(",");
            }
            count++;
            sqlSB.append("(?,?,?)");
            if (FOR_INDEX % commitCount == 0) {
                PreparedStatement statement3 = connection.prepareStatement(sqlSB.toString());
                while (--count >= 0) {
                    statement3.setInt(3*commitCount - (3 * count + 2), count+ FOR_INDEX);
                    statement3.setString(3*commitCount - (3 * count + 1), "张三" + count+ FOR_INDEX);
                    statement3.setString(3*commitCount - (3 * count), "张先生" + count+ FOR_INDEX);
                }
                count = 0;
                statement3.executeUpdate();
                sqlSB = new StringBuilder();
                sqlSB.append("insert into ES_USER(userId,username,displayName)values");
                connection.commit();
            }
        }
        System.out.println("type 3 time keep:" + (System.currentTimeMillis() - startTimeMills));
```
*结果展示*：
一次提交数量对应的时长大致为：

+ 1000：18211ms 
+ 1w：12554ms 
+ 10w：17677ms

