package com.dionext.dioportal.controllers;


import com.dionext.dioportal.services.DioportalPageCreatorService;
import com.dionext.site.controllers.BaseSiteController;
import com.dionext.site.dto.PageUrl;
import com.dionext.site.services.PageParserService;
import com.dionext.site.services.SitemapService;
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
@RequestMapping(value = {"/"})
public class DioportalSiteController extends BaseSiteController {
    private DioportalPageCreatorService dioportalPageElementService;
    private SitemapService sitemapService;

    private PageParserService pageParserService;
    @Autowired
    public void setSitemapService(SitemapService sitemapService) {
        this.sitemapService = sitemapService;
    }

    @Autowired
    public void setPageParserService(PageParserService pageParserService) {
        this.pageParserService = pageParserService;
    }

    @Autowired
    public void setDioportalPageElementService(DioportalPageCreatorService dioportalPageElementService) {
        this.dioportalPageElementService = dioportalPageElementService;
    }

    @GetMapping(value = {"/**"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> processPage() {
        return sendOk(createSimpleSitePage(pageParserService, dioportalPageElementService));
    }


    @GetMapping(value = {"/api/generateOfflinePages"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> generateOfflinePages() {
        Path outputDir = Path.of("R:\\doc\\sites\\dioportal\\offline");

        List<PageUrl> list = dioportalPageElementService.findAllPages();
        String base = "http://localhost:8080/en/";
        for (var offlinePage : list) {
            log.debug(offlinePage.getRelativePath());
            String p = sitemapService.downloadPage(base, offlinePage, true);
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

    @GetMapping(value = {"/api/test"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> apitest() {
        return sendOk("OK api test ");
    }
    @GetMapping(value = {"/admin/test"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> admintest() {
        return sendOk("OK admin test ");
    }


}
