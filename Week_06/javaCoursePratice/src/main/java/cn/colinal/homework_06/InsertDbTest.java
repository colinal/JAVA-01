package cn.colinal.homework_06;

import java.sql.*;

public class InsertDbTest {

    private static int FOR_INDEX = 0;

    private static StringBuilder sqlSB;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载MySQL驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        String password = "123456";
        String user = "root";
        String url = "jdbc:mysql://localhost:3305/colinalDB?characterEncoding=UTF8";
        long startTimeMills = System.currentTimeMillis();
        Connection connection = DriverManager.getConnection(url, user, password);

        sqlSB = new StringBuilder();
        // 1. 拼接方式 100w一次的8817ms 10w一次8472ms 1w一次9465ms
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
        reset(connection);

        // 2. 参数方式批量提交，关闭自动提交 太久了，没跑完
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
        reset(connection);


        // 3. 参数方式加参数拼接，1000提交18211ms 1w提交12554ms 10w提交17677ms
//        connection.setAutoCommit(false);
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
//                connection.commit();
            }
        }
        System.out.println("type 3 time keep:" + (System.currentTimeMillis() - startTimeMills));
    }

    private static void reset(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("truncate table ES_USER ");
        sqlSB = new StringBuilder();
        FOR_INDEX = 0;
    }
}
