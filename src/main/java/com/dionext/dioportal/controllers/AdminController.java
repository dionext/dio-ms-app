package com.dionext.dioportal.controllers;


import com.dionext.site.controllers.BaseSiteController;
import com.dionext.site.dto.SrcPageContent;
import com.dionext.site.services.AdminService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.security.Principal;
import java.util.Map;

@RestController
@Slf4j
@Tag(name = "Admin Controller", description = "Admin Controller")
@RequestMapping(value = {"/admin"})
public class AdminController extends BaseSiteController {

    private AdminService adminService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("main")
    public ResponseEntity<String> getMainPage(@RequestParam Map<String, String> params, Principal principal) {
        log.debug("Principal " + principal);
        return sendOk(adminService.createHtmlAll(new SrcPageContent(adminService.createAdminPage(params))));
    }
    @GetMapping("logout")
    public  ResponseEntity<Void> logout(HttpServletRequest request) throws Exception {
        request.logout();
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).location(
                URI.create("main")).build();
    }

}
