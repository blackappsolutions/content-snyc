package de.bas.content.jobs;

import com.coremedia.blueprint.common.contentbeans.CMFolderProperties;
import com.coremedia.blueprint.common.contentbeans.CMObject;
import com.coremedia.cap.undoc.server.importexport.base.exporter.ServerXmlExport;
import de.bas.content.engine.ContentWriter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * Sample Job, which makes use of ServerExporter, which has the ability to zip XML&Blobs and transfer them to one of the
 * following urls:
 * - file:///..     (Local file system)
 * - s3://..        (AWS S3 Bucket)
 * - http(s)://..   (PUT to some REST-API)
 * <p>
 * => Default ist 'file:///temp/' (in a docker environment, this path should be made accessible with a volume mapping.)
 * <p>
 * !!! The URL must be terminated with a final slash ('/') !!!
 * <p>
 * The export-zip will be placed there with the following name-pattern: content-id.zip. E.g. file:///temp/1234.zip
 * <p>
 * If you want to use s3 (e.g. "s3://blackapp-content-sync/"), keep in mind, that you can have only ONE bucket per
 * system, because it is not possible to pass s3-credentials on the url or on any other way to the ServerExporter,
 * except variables in the system environment (see global/deployment/docker/compose/default.yml).
 *
 * @author Markus Schwarz
 */
@Slf4j
@Scope("prototype")
@Component("xmlExport")
public class ExportXMLJob extends AbstractContentJob {

    public ExportXMLJob(de.bas.content.beans.ContentJob contentJob, ContentWriter contentWriter) {
        super(contentJob, contentWriter);
    }

    @Override
    protected void doTheJob() {
        listAppender = getLoggingEventListAppender(ServerXmlExport.class);
        ServerXmlExport serverExporter = new ServerXmlExport(contentJob.getContent().getRepository().getConnection(), null);
        String[] ids = getContentIds();
        serverExporter.setContentIds(ids);
        serverExporter.setRecursive(contentJob.recursive());
        serverExporter.setZip(contentJob.getZipUrl());
        log.info("About to start {} server-export of content-ids {}", contentJob.recursive() ? "recursive" : "", ids);
        serverExporter.init();
        serverExporter.doExport();
        log.info("Finished {} server-export of content-ids {}", contentJob.recursive() ? "recursive" : "", ids);
    }

    private String[] getContentIds() {
        List<String> contentIds = new ArrayList<>();
        for (CMObject sourceContent : contentJob.getSourceContent()) {
            if (sourceContent instanceof CMFolderProperties) {
                contentIds.add(sourceContent.getContent().getParent().getId());
            } else {
                contentIds.add(sourceContent.getContent().getId());
            }
        }
        return contentIds.toArray(String[]::new);
    }
}
