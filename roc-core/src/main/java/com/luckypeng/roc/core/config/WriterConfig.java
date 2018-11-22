package com.luckypeng.roc.core.config;

import lombok.Getter;

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

    @Getter
    public static class ParameterConfig extends AbstractConfig {
        public static final String KEY_COLUMN_LIST = "column";
        public static final String KEY_CONNECTION_CONFIG = "connection";

        List column;
        ConnectionConfig connection;

        public ParameterConfig(Map<String, Object> map) {
            super(map);
            column = (List) getVal(KEY_COLUMN_LIST);
            connection = new ConnectionConfig((Map<String, Object>) map.get(KEY_CONNECTION_CONFIG));
        }

        @Getter
        public class ConnectionConfig extends AbstractConfig {
            private static final String KEY_JDBC_URL = "jdbcUrl";
            private static final String KEY_TABLE = "table";

            private String jdbcUrl;
            private String table;

            public ConnectionConfig(Map<String, Object> map) {
                super(map);
                jdbcUrl = getStringVal(KEY_JDBC_URL);
                table = getStringVal(KEY_TABLE);
            }
        }

    }


}
