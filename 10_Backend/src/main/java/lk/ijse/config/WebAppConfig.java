package lk.ijse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@ComponentScan(basePackages = "lk.ijse.controller")
@EnableWebMvc
public class WebAppConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(("/WEB-INF/views/**")).addResourceLocations("/WEB-INF/views/");
        registry.addResourceHandler("/**")
                .addResourceLocations("/11_Frontend/"); // To access the frontend files
    }

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");  // use created file prefix folder name (WEB-INF/views in this case)
        resolver.setSuffix(".html");    // use created file suffix (.html in this case)
        resolver.setOrder(2);   // To give priority to this resolver. If not given, it will be the last resolver.
        return resolver;
    }

    // CORS Configuration (Cross-Origin Resource Sharing -> to access both frontend and backend from different origins)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
