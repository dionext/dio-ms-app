package com.dionext.dioportal.configuration;

import com.dionext.configuration.SiteContextHandler;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
public class I18nConfiguration implements WebMvcConfigurer {


    // FixedLocaleResolver — always resolves the locale to a singular fixed language mentioned in the project properties. Mostly used for debugging purposes.
    // AcceptHeaderLocaleResolver — resolves the locale using an “accept-language” HTTP header retrieved from an HTTP request.
    // SessionLocaleResolver — resolves the locale and stores it in the HttpSession of the user. But as you might have wondered, yes, the resolved locale data is persisted only for as long as the session is live.
    // CookieLocaleResolver — resolves the locale and stores it in a cookie stored on the user’s machine. Now, as long as browser cookies aren’t cleared by the user, once resolved the resolved locale data will last even between sessions. Cookies save the day!     *


    @Bean
    public SiteContextHandler contextHandlerInterceptor() {
        return new SiteContextHandler();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(contextHandlerInterceptor());
    }

    @Bean
    public MessageSource messageSource() {
        //note: for ReloadableResourceBundleMessageSource use "classpath:...."
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //to do load from settings
        messageSource.setBasenames("classpath:ideaportalmessages", "classpath:dioportalmessages",
                "classpath:messages", "classpath:sitemessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

}
