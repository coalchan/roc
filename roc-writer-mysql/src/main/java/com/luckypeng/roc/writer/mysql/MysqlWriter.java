package com.luckypeng.roc.writer.mysql;

import com.luckypeng.roc.common.util.ArrayUtils;
import com.luckypeng.roc.common.util.JdbcUtils;
import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.config.WriterConfig;
import com.luckypeng.roc.core.data.Record;
import com.luckypeng.roc.core.writer.Writer;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static com.luckypeng.roc.writer.mysql.JdbcConfigKeys.*;

/**
 * @author chenzhipeng
 * @date 2018/11/21 16:43
 */
@Slf4j
public class MysqlWriter extends Writer {
    private String jdbcUrl;
    private String username;
    private String password;

    private String table;
    private List<String> column;
    private DataSource dataSource;
    private String insertSql;

    public MysqlWriter(RocConfig rocConfig) {
        super(rocConfig);
        WriterConfig writerConfig = rocConfig.getJob().getContent().getWriter();
        jdbcUrl = writerConfig.getParameter().getConnection().getJdbcUrl();
        username = writerConfig.getParameter().getStringVal(KEY_USERNAME);
        password = writerConfig.getParameter().getStringVal(KEY_PASSWORD);
        table = writerConfig.getParameter().getConnection().getTable();
        column = writerConfig.getParameter().getColumn();

        dataSource = JdbcUtils.getDataSource(jdbcUrl, username, password, false);
        List<String> placeholder = ArrayUtils.multiClone("?", column.size());
        insertSql = String.format("insert into %s(%s) values (%s)",
                table, String.join(",", column), String.join(",", placeholder));
        log.info("generate write sql: {}", insertSql);
    }

    @Override
    public void writeData(Record[] records) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try (PreparedStatement pst = conn.prepareStatement(insertSql)) {
                for (int i = 0; i < records.length; i++) {
                    for (int j = 0; j < records[i].getData().length; j++) {
                        pst.setObject(j+1, records[i].getData()[j]);
                    }
                    pst.addBatch();
                }
                pst.executeBatch();
            }
        }
    }
}
