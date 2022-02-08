package com.project.store;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class StoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private DataSource dataSource;
    @Test
    void getConnection() throws SQLException {
        //Hikari数据库连接池
        //HikariProxyConnection@982565180 wrapping com.mysql.cj.jdbc.ConnectionImpl@39c96e48
        System.out.println(dataSource.getConnection());
    }


}
