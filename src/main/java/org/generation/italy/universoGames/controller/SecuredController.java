package org.generation.italy.universoGames.controller;

import org.generation.italy.universoGames.auth.Utente;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

    @GetMapping
    public Utente detail(@AuthenticationPrincipal Utente utente) {
        return utente;
    }
    
    
}