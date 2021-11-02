package com.example.poc.mapper;

import com.example.poc.model.entity.Language;
import com.example.poc.wsdl_classes.LanguageNameResponse;

public class LanguageMapper {
    public static Language mapToLanguage(LanguageNameResponse response) {
        Language language = new Language();
        language.setName(response.getLanguageNameResult());
        return language;
    }
}
