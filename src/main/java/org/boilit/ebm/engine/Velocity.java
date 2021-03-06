package org.boilit.ebm.engine;

import java.io.InputStream;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.boilit.ebm.AbstractEngine;

/**
 * @author Boilit
 * @see
 */
public final class Velocity extends AbstractEngine {
    private String templateUrl;
    private VelocityEngine engine;

    @Override
    public final void init(String engineName,Properties properties) throws Exception {
    	      templateUrl = "/vm.html";

        engine = new VelocityEngine();
        //engine.setProperty("file.resource.loader.path", "templates");
        //engine.setProperty("file.resource.loader.cache", "true");
        //engine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.NullLogSystem");
        //engine.init();
        Properties prop = new Properties();
        InputStream inputStream = Velocity.class.getResourceAsStream("/velocity.properties");
        prop.load(inputStream);
        inputStream.close();
        prop.setProperty("file.resource.loader.path", Velocity.class.getResource("/templates").getPath());
        prop.setProperty("input.encoding", properties.getProperty("inputEncoding", "UTF-8"));
        prop.setProperty("output.encoding", properties.getProperty("outputEncoding", "UTF-8"));
        engine.init(prop);
    }

    @Override
    public void work(Map model, Writer writer) throws Exception {
        engine.getTemplate(this.templateUrl).merge(new VelocityContext(model), writer);
    }

    @Override
    public final boolean isSupportByteStream() {
        return false;
    }
}
