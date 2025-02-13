package com.cb.controller;

import com.cb.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TranslateController {

    @Autowired
    private TranslationService translationService;

    @PostMapping("/translate")
    @ResponseBody
    public String translate(@RequestParam String text, @RequestParam String sourceLang, @RequestParam String targetLang) {
        return translationService.translateText(text, sourceLang, targetLang);
    }
}