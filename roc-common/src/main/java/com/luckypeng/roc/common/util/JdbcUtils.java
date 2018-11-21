package com.luckypeng.roc.common.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.PoolInitializationException;

/**
 * @author chenzhipeng
 */
public class JdbcUtils {
    private JdbcUtils() {}

    /**
     * 通过HiKariCP连接池获取dataSource
     * @param jdbcUrl
     * @param username
     * @param password
     * @return
     */
    public static HikariDataSource getDataSource(String jdbcUrl, String username, String password, boolean isReadOnly) {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setReadOnly(isReadOnly);
        hikariConfig.addDataSourceProperty("useSSL", false);
        hikariConfig.addDataSourceProperty("characterEncoding", "UTF-8");
        try {
            return new HikariDataSource(hikariConfig);
        } catch (PoolInitializationException e) {
            throw new RuntimeException("Error connecting to MySql: " + jdbcUrl, e);
        }
    }
}
