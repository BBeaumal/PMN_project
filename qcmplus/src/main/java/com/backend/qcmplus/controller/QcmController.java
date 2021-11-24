package com.backend.qcmplus.controller;

import com.backend.qcmplus.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/rest")
public class QcmController {

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/login")
    Mono<String> hello(){
       return Mono.just("loged");
    }


}