package apollo.Resource.Service.Config;

import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResetConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}