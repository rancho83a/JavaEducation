package com.example.advquerying.services.util;

import com.example.advquerying.exception.UnableToConvertException;

public interface FormatConverter  {
    void setPrettyPrint();
    String serialize(Object obj) throws UnableToConvertException;
    void serialize(Object obj, String fileName) throws UnableToConvertException;

    <T> T deserialize(String input, Class<T> toType);
    <T> T deserializeFromFile(String fileName, Class<T> toType);

}
