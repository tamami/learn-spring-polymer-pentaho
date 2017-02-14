package lab.aikibo.services;

import lab.aikibo.report.AbstractReportGenerator;
import lab.aikibo.report.SkNjopReport;
import org.apache.commons.io.IOUtils;
import org.pentaho.reporting.engine.classic.core.ReportProcessingException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by tamami on 08/02/17.
 */
public class ReportService {

    public ResponseEntity<byte[]> prosesSkNjop(String nop)
            throws IOException, FileNotFoundException, ReportProcessingException {
        String filename = SkNjopReport.class.getSimpleName() + ".pdf";

        File dir = new File("target/classes/static/reports/");
        if(!dir.exists()) {
            dir.mkdir();
        }

        final File outputFilename = new File("target/classes/static/reports/", filename);
        new SkNjopReport(nop).generateReport(AbstractReportGenerator.OutputType.PDF, outputFilename);
        System.err.println("Generated the report [" + outputFilename.getAbsolutePath() + "]");

        byte[] contents = IOUtils.toByteArray(new FileInputStream(outputFilename));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));

        headers.add("content-disposition", "inline;filename=" + filename);

        // ga perlu ini kalo mau ditampilin di next tab, kalo pake ini artinya file langsung di unduh
        //headers.setContentDispositionFormData(filename, filename);

        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
        return response;
    }

}
