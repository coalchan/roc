package com.luckypeng.roc.core;

import com.luckypeng.roc.common.exception.CommonErrorCode;
import com.luckypeng.roc.common.exception.RocException;
import com.luckypeng.roc.common.util.AssertionUtils;
import com.luckypeng.roc.core.config.RocConfig;
import com.luckypeng.roc.core.run.Engine;
import com.luckypeng.roc.core.writer.Writer;
import com.luckypeng.roc.core.writer.WriterFactory;
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * @author chenzhipeng
 */
public class Launcher {
    public static void main(String[] args) throws Exception {
        Options options = new Options();

        options.addOption("job", true, "Job config");
        options.addOption("plugin", true, "plugin root dir");

        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp( "help", options );

        BasicParser parser = new BasicParser();

        CommandLine commandLine = parser.parse(options, args);

        String jobFile = commandLine.getOptionValue("job");
        String pluginRoot = commandLine.getOptionValue("plugin");

        AssertionUtils.isNotEmpty(jobFile, new RocException(CommonErrorCode.PARAM_ERROR, "job参数不能为空"));
        AssertionUtils.isNotEmpty(pluginRoot, new RocException(CommonErrorCode.PARAM_ERROR, "plugin参数不能为空"));

        RocConfig rocConfig = RocConfig.parse(jobFile);
        rocConfig.setPluginRoot(pluginRoot);
        Writer writer = WriterFactory.getWriter(rocConfig);

        if (!writer.checkConfig()) {
            throw new IllegalArgumentException("Writer配置有误");
        }

        Engine engine = new Engine(rocConfig, writer);
        engine.start();
    }
}
