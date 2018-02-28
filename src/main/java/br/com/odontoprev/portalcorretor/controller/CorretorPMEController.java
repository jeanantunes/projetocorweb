package br.com.odontoprev.portalcorretor.controller;

import br.com.odontoprev.portalcorretor.model.DashboardCorretora;
import br.com.odontoprev.portalcorretor.model.Proposta;
import br.com.odontoprev.portalcorretor.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CorretorPMEController {

    @RequestMapping(value = "/corretor/homeCorretora", method = RequestMethod.GET)
    public ModelAndView home(HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("perfil");


        DashboardCorretora corretora = new DashboardCorretora();

        corretora.setCountCorretoresAprovacao(5);
        corretora.setCountCorretoresCriticadas(4);
        corretora.setCountCorretoresSucesso(3);
        List<Proposta> propostas = new ArrayList<>();
        propostas.add(new Proposta("Vendedor 1", "PF", new Date(), 120d));
        propostas.add(new Proposta("Vendedor 2", "PME", new Date(), 140d));
        propostas.add(new Proposta("Vendedor 3", "PME", new Date(), 440d));
        corretora.setPropostas(propostas);
        corretora.setTipoPlano(propostas.stream().map(Proposta::getTipoPlano).distinct().collect(Collectors.toList()));
        corretora.setVendedores(propostas.stream().map(Proposta::getVendedor).distinct().collect(Collectors.toList()));
        corretora.setValorPessoaFisica(propostas.stream().filter(a -> a.getTipoPlano().equals("PF")).mapToDouble(Proposta::getValor).sum());
        corretora.setValorPME(propostas.stream().filter(a -> a.getTipoPlano().equals("PME")).mapToDouble(Proposta::getValor).sum());

        return new ModelAndView("/corretor/homeCorretora", "corretora", corretora);
    }

}
