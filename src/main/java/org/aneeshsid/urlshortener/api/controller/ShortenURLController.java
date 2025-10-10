package org.aneeshsid.urlshortener.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.aneeshsid.urlshortener.api.dto.ShortenLinkDTO;
import org.aneeshsid.urlshortener.api.dto.ShortenResponse;
import org.aneeshsid.urlshortener.api.model.Link;
import org.aneeshsid.urlshortener.api.service.RedirectURLService;
import org.aneeshsid.urlshortener.api.service.ShortenURLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.OffsetDateTime;

import static org.springframework.http.ResponseEntity.ok;

@RestController
public class ShortenURLController {

    @Autowired
    private ShortenURLService shortenURLService;

    @Autowired
    private RedirectURLService redirectURLService;

    @PostMapping("/api/v1/links/")
    @ResponseBody
    public ResponseEntity<ShortenResponse> shortenURL(@Valid @RequestBody ShortenLinkDTO shortenLinkDTO, HttpServletRequest request) {
        String baseURL = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        Link urlLink = shortenURLService.fetchLinkByURL(shortenLinkDTO.getTargetUrl(), shortenLinkDTO.getClientId());
        if(urlLink != null) {
            ShortenResponse shortenResponse = shortenURLService.getShortenedURL(urlLink,baseURL);
            return ResponseEntity.status(HttpStatus.OK).body(shortenResponse);
        }
        ShortenResponse response = shortenURLService.shortenURL(shortenLinkDTO.getTargetUrl(), shortenLinkDTO.getClientId(),baseURL);
        return  ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{alias}")
    public ResponseEntity<Void> redirectToShortenURL(@PathVariable("alias") String alias, HttpServletResponse response) throws IOException {
        System.out.println("alias: " + alias);
        Link link = redirectURLService.redirect(alias);
        if (link == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404
        }

        if (link.getExpiresAt() != null && link.getExpiresAt().isAfter(OffsetDateTime.now())) {
            return ResponseEntity.status(HttpStatus.GONE).build(); // 410 Gone
        }



        response.sendRedirect(link.getTargetURL());
        redirectURLService.updateClickCount(link.getClickCount(), link.getAlias());
        return ResponseEntity.status(HttpStatus.FOUND).build();
    }


}
