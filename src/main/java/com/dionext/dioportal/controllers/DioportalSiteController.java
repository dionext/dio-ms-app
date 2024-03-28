package com.dionext.dioportal.controllers;


import com.dionext.dioportal.services.DioportalPageCreatorService;
import com.dionext.site.controllers.BaseSiteController;
import com.dionext.site.dto.OfflinePage;
import com.dionext.site.dto.SrcPageContent;
import com.dionext.site.services.OfflineSiteService;
import com.dionext.site.services.PageCreatorService;
import com.dionext.site.services.PageParserService;
import com.dionext.utils.exceptions.DioRuntimeException;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Dioportal Site Controller", description = "Dioportal Site Controller")
@RequestMapping(value = {"/dioportal"})
public class DioportalSiteController extends BaseSiteController {
    private DioportalPageCreatorService dioportalPageElementService;
    private OfflineSiteService offlineSiteService;

    private PageParserService pageParserService;
    @Autowired
    public void setPageParserService(PageParserService pageParserService) {
        this.pageParserService = pageParserService;
    }

    @Autowired
    public void setDioportalPageElementService(DioportalPageCreatorService dioportalPageElementService) {
        this.dioportalPageElementService = dioportalPageElementService;
    }

    @Autowired
    public void setOfflineSiteService(OfflineSiteService offlineSiteService) {
        this.offlineSiteService = offlineSiteService;
    }

    @GetMapping(value = {"/**"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> processPage() {
        return sendOk(createSimpleSitePage(pageParserService, dioportalPageElementService));
    }


    @GetMapping(value = {"/api/generateOfflinePages"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> generateOfflinePages() {
        Path outputDir = Path.of("R:\\doc\\sites\\dioportal\\offline");


        List<OfflinePage> list = offlineSiteService.findAllPages(pageInfo.getSiteStoragePaths(),
                "en", "en");
        String base = "http://localhost:8080/dioportal/en/";
        for (var offlinePage : list) {
            log.debug("" + offlinePage.getRelativePath());
            String p = offlineSiteService.downloadPage(base, offlinePage, true);
            Path path = outputDir.resolve(offlinePage.getRelativePath());
            try {
                File file = path.toFile();
                if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
                Files.writeString(path, p);
            } catch (IOException e) {
                throw new DioRuntimeException();
            }
        }

        return sendOk("OK");
    }

}
