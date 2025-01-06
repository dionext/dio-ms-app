package com.dionext.dioportal.services;

import com.dionext.configuration.CacheConfiguration;
import com.dionext.site.dto.PageUrl;
import com.dionext.site.services.PageParserService;
import com.dionext.site.services.SitemapService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class DioportalSitemapService {
    private DioportalPageCreatorService dioportalPageElementService;
    private SitemapService sitemapService;

    @Autowired
    public void setSitemapService(SitemapService sitemapService) {
        this.sitemapService = sitemapService;
    }

    @Autowired
    public void setDioportalPageElementService(DioportalPageCreatorService dioportalPageElementService) {
        this.dioportalPageElementService = dioportalPageElementService;
    }

    @Cacheable(CacheConfiguration.CACHE_COMMON)
    public String createSitemap(boolean langSupport) {
        log.debug("creating sitemap");
        List<PageUrl> pages = new ArrayList<>();
        pages.addAll(dioportalPageElementService.findAllPages());
        return sitemapService.createSitemap(pages, dioportalPageElementService, langSupport);
    }


}
