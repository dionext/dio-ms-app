package com.dionext.dioportal.controllers;


import com.dionext.dioportal.services.DioportalPageCreatorService;
import com.dionext.site.controllers.BaseSiteController;
import com.dionext.site.services.PageParserService;
import com.dionext.site.services.SitemapService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Dioportal Site Controller", description = "Dioportal Site Controller")
@RequestMapping(value = {"/"})
public class DioportalSiteController extends BaseSiteController {
    private DioportalPageCreatorService dioportalPageElementService;
    private PageParserService pageParserService;
    @Autowired
    public void setSitemapService(SitemapService sitemapService) {
    }

    @Autowired
    public void setPageParserService(PageParserService pageParserService) {
        this.pageParserService = pageParserService;
    }

    @Autowired
    public void setDioportalPageElementService(DioportalPageCreatorService dioportalPageElementService) {
        this.dioportalPageElementService = dioportalPageElementService;
    }

    @GetMapping(value = {"/en/**", "ru/**"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> processPage() {
        return sendOk(createSimpleSitePage(pageParserService, dioportalPageElementService));
    }
    @GetMapping(value = {"/en/index.htm"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> processPage1() {
        return sendOk(createSimpleSitePage(pageParserService, dioportalPageElementService));
    }


    @GetMapping(value = {"/admin/generateOfflinePages"}, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<String> generateOfflinePages() {
        int filesCount = dioportalPageElementService.generateOfflinePages(true);
        return sendOk("OK. Generated " + filesCount + " files");
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
