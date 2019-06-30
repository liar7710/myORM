package com.DAOFactory;

import org.apache.commons.dbcp2.BasicDataSource;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.SQLException;

public class DAOFactory {
    private static BasicDataSource dataSource;
    private static volatile Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream("classpath:/dbcp.properties"));
            dataSource = BasicDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static ThreadLocal<Connection> TL=new ThreadLocal<>();
    public static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    public static Connection getThreadConnection() throws SQLException {
        if(TL.get()==null)
            TL.set(getConnection());
        return TL.get();
    }
    public static boolean removeThreadConnection() throws SQLException {
        TL.remove();
        return true;
    }
}
