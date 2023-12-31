//package com.skillstorm.project2.configurations;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                .allowedOrigins("*") //try "https://project2-cabin-fever.s3.amazonaws.com" if this fails
//                .allowedMethods("GET","POST","DELETE","PUT")
//                .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
//        
//    }
//}
