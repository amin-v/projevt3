package configControll;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;

public class DBCP2 {
    private static BasicDataSource basicDataSource=new BasicDataSource();
    static {
        basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("amin");
        basicDataSource.setPassword("amin123456");
        basicDataSource.setMaxTotal(10);
    }
    public static Connection getConnection() throws Exception{
        return basicDataSource.getConnection();
    }
}
