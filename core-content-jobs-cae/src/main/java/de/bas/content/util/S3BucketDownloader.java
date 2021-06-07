package de.bas.content.util;

import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.AmazonS3URI;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static com.coremedia.blueprint.base.links.UriConstants.Segments.PREFIX_DYNAMIC;

/**
 * @author Markus Schwarz
 */
@Slf4j
@RequestMapping
public class S3BucketDownloader {
    public static final String DYNAMIC_URI_PATTERN = '/' + PREFIX_DYNAMIC + "/content-jobs/s3download";

    @GetMapping(value = DYNAMIC_URI_PATTERN)
    @ResponseBody
    public void downloads3Data(@RequestParam(name = "bucketUrl") String bucketUrl, HttpServletResponse response) throws IOException {
        AmazonS3URI amazonS3URI = new AmazonS3URI(bucketUrl);
        String bucketName = amazonS3URI.getBucket();
        String key = amazonS3URI.getKey();
        
        S3Object fullObject = null;
        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard().build();

            fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));

            response.addHeader("Content-Disposition", "attachment; filename=\"" + key + "\"");
            response.setContentType(fullObject.getObjectMetadata().getContentType());
            response.setCharacterEncoding("UTF-8");
            PrintWriter writer = response.getWriter();

            InputStreamReader inputStreamReader = new InputStreamReader(fullObject.getObjectContent());
            inputStreamReader.transferTo(writer);
            writer.flush();
            writer.close();
        } catch (SdkClientException e) {
            log.error("Error providing {}", bucketUrl, e);
        }
        finally {
            // To ensure that the network connection doesn't remain open, close any open input streams.
            if (fullObject != null) {
                fullObject.close();
            }
        }
    }
}
