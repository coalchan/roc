package com.luckypeng.roc.core;

import com.luckypeng.roc.common.exception.CommonErrorCode;
import com.luckypeng.roc.common.exception.RocException;
import com.luckypeng.roc.common.util.AssertionUtils;
import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.writer.Writer;
import com.luckypeng.roc.core.writer.WriterFactory;
import com.luckypeng.roc.mock.Mock;
import org.apache.commons.cli.*;

/**
 * @author chenzhipeng
 * @date 2018/11/16 16:06
 */
public class Engine {
    public static void main(String[] args) throws ParseException {
        Options options = new Options();

        options.addOption("job", true, "Job config");
        options.addOption("plugin", true, "plugin root dir");

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "help", options );

        BasicParser parser = new BasicParser();

        CommandLine commandLine = parser.parse(options, args);

        String job = commandLine.getOptionValue("job");
        String plugin = commandLine.getOptionValue("plugin");

        AssertionUtils.isNotEmpty(job, new RocException(CommonErrorCode.PARAM_ERROR, "job参数不能为空"));
        AssertionUtils.isNotEmpty(plugin, new RocException(CommonErrorCode.PARAM_ERROR, "plugin参数不能为空"));

        // parse config
        RocConfig rocConfig = RocConfig.parse(job);

        // mock data
        Object[] data = Mock.mock(rocConfig.getJob().getContent().getMock().getRule());

        // write data
        Writer writer = WriterFactory.getWriter(rocConfig);
        writer.writeData(data);
    }
}
