package br.com.odontoprev.portalcorretor.controller.pendente;

import br.com.odontoprev.portalcorretor.Service.DashService;
import br.com.odontoprev.portalcorretor.Service.dto.DashboardPropostas;
import br.com.odontoprev.portalcorretor.Service.entity.FiltroStatusProposta;
import br.com.odontoprev.portalcorretor.model.UsuarioSession;
import br.com.odontoprev.portalcorretor.model.ListaPropostas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class ListaPropostasController {


    @Autowired
    DashService dashService;

    @RequestMapping(value = "/others/listaPropostas", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        UsuarioSession usuario = (UsuarioSession) session.getAttribute("usuario");

        ListaPropostas listaPropostas = new ListaPropostas();

        DashboardPropostas propostaPME = dashService.ObterListaPropostaPME(FiltroStatusProposta.TODOS, usuario.getDocumento());
        listaPropostas.setPropostaPME(propostaPME.getPropostasPME());
        listaPropostas.setTotalPME(propostaPME.getPropostasPME().size());


        DashboardPropostas propostaPF = dashService.ObterListaPropostaPF(FiltroStatusProposta.TODOS, usuario.getDocumento());
        listaPropostas.setPropostaPF(propostaPF.getPropostasPF());
        listaPropostas.setTotalPF(propostaPF.getPropostasPF().size());

        listaPropostas.setTotal( propostaPME.getPropostasPF().size() + propostaPF.getPropostasPME().size() );


        return new ModelAndView("/corretor/others/listaPropostas", "listaPropostas", listaPropostas);
    }

}
