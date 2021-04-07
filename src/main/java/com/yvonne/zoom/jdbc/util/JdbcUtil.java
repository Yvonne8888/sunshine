package com.yvonne.zoom.jdbc.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 *JDBC工具类封装连接释放
 * 1)Class.forName()加载数据库连接驱动
 * 2)DriverManager.getConnection()获取数据连接对象
 * 3)根据SQL获取sql会话对象，有2种方式Statement、PreparedStatement（使用此方式）
 * 4)执行SQL处理结果集，执行SQL前如果有参数值就设置参数值setXXX()
 * 5)关闭结果集、关闭会话、关闭连接
 *
 * @author Yvonne Wang
 */
public class JdbcUtil {
    /**
     * jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
     * String url = "jdbc:mysql://localhost:3306/sunshine?username=root&password=123456&useUnicode=true&characterEncoding=UTF8";
     * 也可在获取连接的时候使用用户名和密码
     * 避免中文乱码要指定useUnicode和characterEncoding, 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
     */
    private static String url;
    private static String username;
    private static String password;
    private static String driver;
    private static Connection connection;
    /**
     *本地线程变量，用于事务控制，为每一个进程创建一个connection对象
     */
    public static ThreadLocal<Connection> localConn = new ThreadLocal<Connection>();

    //1.静态代码块，类加载时就加载数据库连接驱动
    static {
        try {
            Properties properties = new Properties();
            //使用ClassLoader的get Resource方法获得配置文件的io输入流,读取的是src目录的文件
            InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("db.properties");
            properties.load(is);

            driver = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            username = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");

            //Class.forName("");的作用是要求JVM查找并加载指定的类，如果在类中有静态初始化器的话，JVM必然会执行该类的静态代码段。
            //而在JDBC规范中明确要求这个Driver类必须向DriverManager注册自己，即任何一个JDBC Driver的 Driver类的代码都必须类似如下：
            // DriverManager.registerDriver(new OracleDriver())等效于Class.forName("com.mysql.jdbc.Driver")
            //将驱动类的class文件装载到内存中，并且形成一个描述此驱动类结构的Class类实例，并且初始化此驱动类，这样jvm就可以使用它了
            Class.forName(driver);
        }catch (Exception e){
            System.out.println("DBUtils静态代码段异常：" + e.getMessage());
        }
    }
    public JdbcUtil() {}

    /**
     * 2.获取连接方法，只产生一个连接
     * 适用于SELECT，后续用close(ResultSet result, Statement statement, Connection connection)来关闭此事务
     * @return connection 非线程事务
     */
    public static Connection getConnection(){
        try {
            if (connection == null) {
                synchronized (JdbcUtil.class) {
                    if (connection == null) {
                        connection = DriverManager.getConnection(url, username, password);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Exception: get mysql connection Exception");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 最后释放资源
     */
    public static void close(ResultSet result, Statement statement, Connection connection) {
        try {
            //关闭结果集
            if (result != null) {
                result.close();
            }
        } catch (SQLException e) {
            System.out.println("JDBC规范接口result关闭异常：" + e.getMessage());
        }finally {
            try {
                //关闭statement会话
                if (statement != null) {
                    statement.close();
                }
            }catch (SQLException e) {
                System.out.println("JDBC规范接口statement关闭异常：" + e.getMessage());
            }finally {
                try {
                    //关闭连接
                    if (connection != null){
                        connection.close();
                    }
                }catch (SQLException e) {
                    System.out.println("JDBC规范接口connection关闭异常：" + e.getMessage());
                }
            }
        }
    }

    /**
     * 开启非自动Commit的事务保存至本地静态变量localConn
     */
    public static void beginNoAutoTransaction(){
        Connection connection = null;
        try {
            connection = JdbcUtil.getConnection();
            connection.setAutoCommit(false);
            localConn.set(connection);
        } catch (SQLException e) {
            System.out.println("开启非自动Commit事务发生异常：" + e.getMessage());
        }
    }

    /**
     * 提交事务
     */
    public static void commit(){
        Connection connection = null;
        try {
            connection = localConn.get();
            if (connection != null) {
                connection.commit();
                connection.close();
                localConn.remove();
            }
        } catch (SQLException e) {
            System.out.println("事务Commit时发生异常：" + e.getMessage());
        }
    }

    /**
     * 回退事务
     */
    public static void rollback(){
        Connection connection = null;
        try {
            connection = localConn.get();
            if (connection != null){
                connection.rollback();
                connection.close();
                localConn.remove();
            }
        } catch (SQLException e) {
            System.out.println("事务rollback时发生异常：" + e.getMessage());
        }
    }

    /**
     * 适用于INSERT,DELETE,UPDATE
     * 得到本地多线程事务；后续用必须要用commit()或rollbank()来处理此事务，
     * 在使用commit()或rollback()时会关闭此事务
     * @return connection 多线程事务
     */
    public static Connection getLocalConnection(){
        return localConn.get();
    }

}
