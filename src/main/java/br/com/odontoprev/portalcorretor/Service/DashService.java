package br.com.odontoprev.portalcorretor.Service;

import br.com.odontoprev.portalcorretor.Service.dto.DashResponse;
import br.com.odontoprev.portalcorretor.Service.dto.PropostaResponse;
import br.com.odontoprev.portalcorretor.Service.entity.FiltroStatusProposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class DashService {

    //@Value("${odontoprev.servicebase.url}")
    private String requesBasetUrl = "http://172.16.20.30:7001/portal-corretor-servico-0.0.1-SNAPSHOT/";

    //@Value("${odontoprev.service.dash}")
    private String metodoDash;

    //@Value("${odontoprev.service.propostaPF}")
    private String metodoPropostaPFList = "dashboardPropostaPF";

    //@Value("${odontoprev.service.propostaPME}")
    private String metodoPropostaPMEList = "dashboardPropostaPME/";


    public DashResponse ObterPorDocumento(Date dataInicio,
                                          Date dataFim,
                                          String nomeVendedor,
                                          String tipoPlano,
                                          String cnpjCPF) {

        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> resquestMap = new HashMap<>();
        String url = requesBasetUrl + metodoDash;

        resquestMap.put("", cnpjCPF);
        resquestMap.put("", dataInicio.toString());
        resquestMap.put("", dataFim.toString());
        resquestMap.put("", nomeVendedor);
        resquestMap.put("", tipoPlano);

        try {
            ResponseEntity<DashResponse> retorno = restTemplate.postForEntity(url, resquestMap, DashResponse.class);
            //return new LoginResponse(login.getUsuario(), "Corretor", Long.parseLong(loginRetorno.getBody().getCodigo()));

            return null;

        } catch (Exception e) {
            return null;
        }
    }


    public PropostaResponse ObterListaPropostaPME(FiltroStatusProposta statusProposta, String documento) {
        return ObterLista(metodoPropostaPMEList, statusProposta, documento);
    }

    public PropostaResponse ObterListaPropostaPF(FiltroStatusProposta statusProposta, String documento) {
        return ObterLista(metodoPropostaPFList, statusProposta, documento);
    }

    private PropostaResponse ObterLista(String metodo, FiltroStatusProposta statusProposta, String documento) {
        RestTemplate restTemplate = new RestTemplate();
        String url = requesBasetUrl + metodo + statusProposta.getValue() + "/" + documento;

        try {
            ResponseEntity<PropostaResponse> retorno = restTemplate.getForEntity(url, PropostaResponse.class);

            if (retorno.getStatusCode() == HttpStatus.OK) {
                return retorno.getBody();
            }
            return null;

        } catch (Exception e) {
            return null;
        }
    }

}
