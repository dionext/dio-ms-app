package com.dionext.dioportal.services;


import com.dionext.site.services.PageCreatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
@SuppressWarnings({"java:S5663"})
public class DioportalPageCreatorService extends PageCreatorService {

    @Override
    public String createBodyBottomInformation() {
        return MessageFormat.format("""
                <small class="d-block mb-3 text-muted">&copy; 2023-2025 v 1.2 {0}</small>""",
                dfs(getVersionInformation())) ;
    }

    @Override
    public String createHeadBottom() {
        return dfs(super.createHeadBottom()) + """
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/styles/default.min.css"/>
                <link rel="stylesheet" href="https://unpkg.com/highlightjs-copy/dist/highlightjs-copy.min.css"/>
                                """;
    }

    @Override
    public String createBodyScripts() {
        return dfs(super.createBodyScripts()) + """
                <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/highlight.min.js"></script>
                <!-- and it's easy to individually load additional languages -->
                <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.9.0/languages/java.min.js"></script>
                <script src="https://unpkg.com/highlightjs-copy/dist/highlightjs-copy.min.js"></script>
                <script>hljs.highlightAll();</script>    
                <script>hljs.addPlugin(new CopyButtonPlugin());</script>    
                """;
    }


    @Override
    public String createBodyTopBanner() {

        return MessageFormat.format("""
                <div class="page-header container">
                   <h1 class="text-page-header">{0}</h1>
                </div>""", pageInfo.getSiteTitle());
    }

    @Override
    public String createBodyTopMenuStyle() {
        return """
                <nav class="navbar navbar-expand-lg navbar-light">""";
    }

}
