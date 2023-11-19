package apollo.Resource.Service.ViaCep.Domain;

import apollo.Resource.Service.ViaCep.Controller.ViaCEP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Local {
    private final String UrlApi = "https://viacep.com.br/ws/";
    private final String UrlApiJson = "/json/";
    private final RestTemplate restTemplate;

    @Autowired
    public Local(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ViaCEP getAddress(String cep) {
        String apiUrl = UrlApi + cep + UrlApiJson;
        return restTemplate.getForObject(apiUrl, ViaCEP.class);
    }
}
