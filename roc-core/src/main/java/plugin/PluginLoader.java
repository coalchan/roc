package plugin;

import com.luckypeng.roc.common.util.SysUtil;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhipeng
 */
public class PluginLoader {

    private String pluginRoot;

    private String pluginName;

    private String pluginClassName;

    private List<URL> urlList = new ArrayList<>();

    private final String pkgPrefix = "com.luckypeng.roc.";

    private final String WRITER_SUFFIX = "writer";

    public PluginLoader(String pluginName, String pluginRoot) {
        this.pluginName = pluginName;
        this.pluginRoot = pluginRoot;
        String lowerPluginName = pluginName.toLowerCase();

        if(lowerPluginName.endsWith(WRITER_SUFFIX)) {
            pluginClassName = pkgPrefix + camelize(pluginName, WRITER_SUFFIX);
        } else {
            throw new IllegalArgumentException("Plugin Name should end with writer");
        }
    }

    /**
     * 转为驼峰
     * @param pluginName
     * @param suffix
     * @return
     */
    private String camelize(String pluginName, String suffix) {
        int pos = pluginName.indexOf(suffix);
        String left = pluginName.substring(0, pos);
        left = left.toLowerCase();
        suffix = suffix.toLowerCase();
        StringBuffer sb = new StringBuffer();
        sb.append(suffix + "." + left + ".");
        sb.append(left.substring(0,1).toUpperCase() + left.substring(1));
        sb.append(suffix.substring(0,1).toUpperCase() + suffix.substring(1));
        return sb.toString();
    }

    public Class<?> getPluginClass() {
        File pluginDir = new File(pluginRoot + File.separator + pluginName);

        try {
            urlList.addAll(SysUtil.findJarsInDir(pluginDir));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        URL[] urls = urlList.toArray(new URL[urlList.size()]);
        ClassLoader classLoader = new URLClassLoader(urls);
        Class<?> clazz;

        try {
            clazz = classLoader.loadClass(this.pluginClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return clazz;
    }

}
