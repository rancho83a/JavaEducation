package com.example.advquerying.services.util;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FormatConverterFactoryImpl implements FormatConverterFactory {
    private final FormatConverter xml;
    private final FormatConverter json;

    public FormatConverterFactoryImpl(
            @Qualifier("XML_format_converter") FormatConverter xml,
            @Qualifier("json_format_converter") FormatConverter json) {
        this.xml = xml;
        this.json = json;
    }

    @Override
    public FormatConverter create(String formatType) {
        return switch (formatType.toLowerCase()) {
            case "xml" -> this.xml;
            case "json" -> this.json;
            default -> null;
        };
    }
}
