package com.xmomen.module.wx.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonMapper extends ObjectMapper {
    private static final long serialVersionUID = -5382012949634670295L;

    public JsonMapper() {
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
        configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        //null
        getSerializerProvider().setNullValueSerializer(
                new JsonSerializer<Object>() {
                    @Override
                    public void serialize(Object value, JsonGenerator jgen,
                                          SerializerProvider provider) throws IOException {
                        jgen.writeString("");
                    }
                });
    }
}
