package ex.xmlprocessing.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationBeanConfiguration {

//    @Bean
//    public Gson gson(){
//        return new GsonBuilder()
//                .setPrettyPrinting()
//                .excludeFieldsWithModifiers()
//                .create();
    //}

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }


}



