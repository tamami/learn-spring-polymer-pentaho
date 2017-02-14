package lab.aikibo.report;

import lombok.Data;
import org.pentaho.reporting.engine.classic.core.DataFactory;
import org.pentaho.reporting.engine.classic.core.MasterReport;
import org.pentaho.reporting.libraries.resourceloader.Resource;
import org.pentaho.reporting.libraries.resourceloader.ResourceException;
import org.pentaho.reporting.libraries.resourceloader.ResourceManager;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tamami on 09/02/17.
 */
@Data
public class SkNjopReport extends AbstractReportGenerator {

    private String nop;

    public SkNjopReport() {}

    public SkNjopReport(String nop) {
        this.nop = nop;
    }

    @Override
    public MasterReport getReportDefinition() {
        try {
            final ClassLoader classLoader = this.getClass().getClassLoader();
            final URL reportDefinitionURL = classLoader.getResource("./sk-njop.prpt");

            final ResourceManager resourceManager = new ResourceManager();
            final Resource directly = resourceManager.createDirectly(reportDefinitionURL, MasterReport.class);
            return (MasterReport)  directly.getResource();
        } catch(ResourceException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DataFactory getDataFactory() {
        return null;
    }

    @Override
    public Map<String, Object> getReportParameters() {
        final Map parameters = new HashMap<String, Object>();
        parameters.put("kdPropinsi", nop.substring(0,2));
        parameters.put("kdDati2", nop.substring(2,4));
        parameters.put("kdKecamatan", nop.substring(4,7));
        parameters.put("kdKelurahan", nop.substring(7,10));
        parameters.put("kdBlok", nop.substring(10,13));
        parameters.put("noUrut", nop.substring(13,17));
        return parameters;
    }

}
