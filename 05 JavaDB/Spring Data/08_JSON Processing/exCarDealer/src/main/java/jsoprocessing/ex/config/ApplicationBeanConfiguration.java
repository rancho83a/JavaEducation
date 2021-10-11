package jsoprocessing.ex.config;

import com.google.gson.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Configuration
    public class ApplicationBeanConfiguration {

        @Bean
        public Gson gson(){
            return new GsonBuilder()
                    .setPrettyPrinting()
                    .excludeFieldsWithModifiers()
//                    .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
//                        @Override
//                        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
//                            return LocalDateTime
//                                    .parse(json.getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
//                        }
//                    })
                    .create();
        }

        @Bean
        public ModelMapper modelMapper() {
            ModelMapper modelMapper = new ModelMapper();
            return modelMapper;
        }


    }



