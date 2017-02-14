package lab.aikibo.controller;

import lab.aikibo.services.ReportService;
import org.apache.log4j.Logger;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/get_report")
public class PbbController {

    static final Logger logger = Logger.getLogger(PbbController.class);

    ReportService reportService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<byte[]> getSkNjop(@RequestBody String nop)
            throws ReportProcessingException {
        // proses call pentaho disini
        //System.out.println(nop);
        reportService = new ReportService();

        try {
            logger.debug("Proses report SK NJOP");
            return reportService.prosesSkNjop(nop.substring(1,nop.length() -1));
        } catch(IOException ioe) {
            logger.debug(ioe.toString());
            return null;
        }
    }

}
