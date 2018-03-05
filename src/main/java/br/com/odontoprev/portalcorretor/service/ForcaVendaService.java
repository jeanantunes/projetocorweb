package br.com.odontoprev.portalcorretor.service;

import br.com.odontoprev.portalcorretor.service.dto.AtivarResponse;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVenda;
import br.com.odontoprev.portalcorretor.service.dto.ForcaVendaResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForcaVendaService {

    @Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl;// = "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/";

    private String metodoGetPorDocuemnto_Post_Put = "forcavenda/";
    private String ativar = "forcavenda/status-ativo";

    private String metodoListaPorCorretora = "forcavenda/corretora/";

    @Autowired
    private ApiManagerTokenService apiManagerTokenService;

    public long Criar(ForcaVenda forcaVenda) {
        String url = requesBasetUrl + metodoGetPorDocuemnto_Post_Put;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            String object = mapper.writeValueAsString(forcaVenda);

            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

            ResponseEntity<ForcaVendaResponse> retorno = restTemplate.exchange(url, HttpMethod.POST, entityReq, ForcaVendaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                result = retorno.getBody();
                return result.getId();
            } else {
                return 0;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }


    }

    public AtivarResponse ativar(String cpf) {
        String url = requesBasetUrl + ativar;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> loginMap = new HashMap<>();
            loginMap.put("cpf", cpf);
            HttpEntity<Map<String, String>> entityReq = new HttpEntity<>(loginMap, headers);

            ResponseEntity<AtivarResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entityReq, AtivarResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new AtivarResponse("0", "Falha ao ativar cpf" );
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new AtivarResponse("0", "Falha ao ativar cpf" );
        }

    }

    public boolean Alterar(ForcaVenda forcaVenda) {
        String url = requesBasetUrl + metodoGetPorDocuemnto_Post_Put;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            headers.setContentType(MediaType.APPLICATION_JSON);

            ObjectMapper mapper = new ObjectMapper();
            mapper.setSerializationInclusion(JsonInclude.Include.USE_DEFAULTS);
            String object = mapper.writeValueAsString(forcaVenda);

            HttpEntity<String> entityReq = new HttpEntity<>(object, headers);

            ResponseEntity<ForcaVendaResponse> retorno = restTemplate.exchange(url, HttpMethod.PUT, entityReq, ForcaVendaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<ForcaVenda> ObterListaPorCorretora(Integer codigoCorretora) {
        String url = requesBasetUrl + metodoListaPorCorretora + codigoCorretora;
        RestTemplate restTemplate = new RestTemplate();
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<ForcaVenda[]> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ForcaVenda[].class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return Arrays.asList(retorno.getBody());
            } else {
                return Arrays.asList(new ForcaVenda[0]);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return Arrays.asList(new ForcaVenda[0]);
        }
    }

    public ForcaVenda ObterPorDocumento(String documento) {
        String url = requesBasetUrl + metodoGetPorDocuemnto_Post_Put + documento;
        RestTemplate restTemplate = new RestTemplate();
        ForcaVendaResponse result = null;
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiManagerTokenService.getToken());
            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
            ResponseEntity<ForcaVenda> retorno = restTemplate.exchange(url, HttpMethod.GET, entity, ForcaVenda.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            } else {
                return new ForcaVenda();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ForcaVenda();
        }
    }
}
