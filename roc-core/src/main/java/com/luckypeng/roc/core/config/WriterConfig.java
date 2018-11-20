package com.luckypeng.roc.core.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The configuration of writer configuration
 * @author chenzhipeng
 */
public class WriterConfig extends AbstractConfig {

    public static String KEY_PARAMETER_CONFIG = "parameter";
    public static String KEY_WRITER_NAME = "name";

    ParameterConfig parameter;

    public WriterConfig(Map<String, Object> map) {
        super(map);
        parameter = new ParameterConfig((Map<String, Object>) getVal(KEY_PARAMETER_CONFIG));
    }

    public String getName() {
        return getStringVal(KEY_WRITER_NAME);
    }

    public void setName(String name) {
        setStringVal(KEY_WRITER_NAME, name);
    }

    public ParameterConfig getParameter() {
        return parameter;
    }

    public void setParameter(ParameterConfig parameter) {
        this.parameter = parameter;
    }

    public static class ParameterConfig extends AbstractConfig {
        public static final String KEY_COLUMN_LIST = "column";
        public static final String KEY_CONNECTION_CONFIG_LIST = "connection";

        List column;
        List<ConnectionConfig> connection;

        public ParameterConfig(Map<String, Object> map) {
            super(map);
            column = (List) getVal(KEY_COLUMN_LIST);
            List<Map<String,Object>> connList = (List<Map<String, Object>>) getVal(KEY_CONNECTION_CONFIG_LIST);
            connection = new ArrayList<>();
            if(connList != null) {
                for(Map<String,Object> conn : connList) {
                    connection.add(new ConnectionConfig(conn));
                }
            }
        }

        public List getColumn() {
            return column;
        }

        public void setColumn(List column) {
            this.column = column;
        }

        public List<ConnectionConfig> getConnection() {
            return connection;
        }

        public void setConnection(List<ConnectionConfig> connection) {
            this.connection = connection;
        }

        public class ConnectionConfig extends AbstractConfig {
            private static final String KEY_JDBC_URL = "jdbcUrl";
            private static final String KEY_TABLE_LIST = "table";

            private String jdbcUrl;
            private List<String> table;

            public ConnectionConfig(Map<String, Object> map) {
                super(map);
                jdbcUrl = getStringVal(KEY_JDBC_URL);
                table = (List<String>) getVal(KEY_TABLE_LIST);
            }

            public String getJdbcUrl() {
                return jdbcUrl;
            }

            public void setJdbcUrl(String jdbcUrl) {
                this.jdbcUrl = jdbcUrl;
            }

            public List<String> getTable() {
                return table;
            }

            public void setTable(List<String> table) {
                this.table = table;
            }
        }

    }


}
