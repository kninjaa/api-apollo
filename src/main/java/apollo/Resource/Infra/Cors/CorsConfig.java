package apollo.Resource.Infra.Cors;

import org.springframework.context.annotation.*;
import org.springframework.web.cors.*;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");// Permitir requisições de todos os origens
        config.addAllowedMethod("GET, POST, PUT, DELETE");// Permitir os métodos GET, POST, PUT, DELETE, etc.
        config.addAllowedHeader("*");// Permitir os headers Authorization, Content-Type, etc.

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
