package br.com.odontoprev.portalcorretor.controller.admin;

import br.com.odontoprev.portalcorretor.model.CnpjDadosAceiteResponse;
import br.com.odontoprev.portalcorretor.model.ReenvioEmailAceitePMEModel;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

@Controller
public class ReenvioEmailAceitePMEController {

    @Autowired
    EmpresaService empresaService;

    @RequestMapping("admin/email_aceite")
    public String indexInfoPlanoIntegralDocLe() {
        return "admin/email_aceite";

    }

    @RequestMapping(value = "buscaCnpjReenvio", method = RequestMethod.GET)
    public ModelAndView buscarCnpjReenvio(HttpSession session, @ModelAttribute("reenvioEmailAceitePMEModel") ReenvioEmailAceitePMEModel reenvioEmailAceitePMEModel) {

        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");
        CnpjDadosAceiteResponse cnpjDadosAceiteResponse = empresaService.obterDadosEmpresa("09296295000160");

        reenvioEmailAceitePMEModel.setCnpj(cnpjDadosAceiteResponse.getCnpj());
        reenvioEmailAceitePMEModel.setRazaoSocial(cnpjDadosAceiteResponse.getRazaoSocial());
        if (cnpjDadosAceiteResponse.getTokenAceite() != null) {
            if (cnpjDadosAceiteResponse.getTokenAceite().getDataAceite() != null) {
                reenvioEmailAceitePMEModel.setDataAceite(new SimpleDateFormat("dd/MM/yyyy").format(cnpjDadosAceiteResponse.getTokenAceite().getDataAceite()));
            }
            reenvioEmailAceitePMEModel.setEmail(cnpjDadosAceiteResponse.getTokenAceite().getEmail());
        }
        reenvioEmailAceitePMEModel.setObservacao(cnpjDadosAceiteResponse.getObservacao());

        return new ModelAndView("admin/email_aceite", "reenvioEmailAceitePMEModel", reenvioEmailAceitePMEModel);

    }

    /*
    @RequestMapping("/info-planos-master-le")
    public String indexInfoPlanoMasterLe() {
        return "info-planos-master-le";

    }

    @RequestMapping("/info-planos-rol-min")
    public String indexInfoPlanoRolMin() {
        return "info-planos-rol-min";

    }


    @RequestMapping("/info-planos-dental-bem-estar")
    public String indexInfoPlanoDentalBemEstar() {
        return "info-planos-dental-bem-estar";

    }

    @RequestMapping("/info-planos-dental-vip")
    public String indexInfoPlanoDentalVip() {
        return "info-planos-dental-vip";

    }
    @RequestMapping("/info-planos-dental-orto")
    public String indexInfoPlanoDentalOrto() {
        return "info-planos-dental-orto";

    }

    @RequestMapping("/info-planos-dental-estetica")
    public String indexInfoPlanoDentalEstetica() {
        return "info-planos-dental-estetica";

    }
    
    @RequestMapping("/info-planos-PF")
    public String indexInfoPlanoPF() {
        return "info-planos-PF";

    }
    
    @RequestMapping("/info-planos-PME")
    public String indexInfoPlanoPME() {
        return "info-planos-PME";

    }
    
    @RequestMapping("/email")
    public String email() {
        return "email";
    }
    */
}
