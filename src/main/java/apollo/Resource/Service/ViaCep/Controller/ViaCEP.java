package apollo.Resource.Service.ViaCep.Controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ViaCEP {
    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
