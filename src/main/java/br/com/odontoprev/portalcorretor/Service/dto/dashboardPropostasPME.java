package br.com.odontoprev.portalcorretor.Service.dto;

import java.util.List;

public class dashboardPropostasPME {
    private List<dashboardPropostas> dashboardPropostasPME;

    public List<dashboardPropostas> getDashboardPropostas() {
        return dashboardPropostasPME;
    }

    public void setDashboardPropostas(List<dashboardPropostas> dashboardPropostas) {
        this.dashboardPropostasPME = dashboardPropostas;
    }
}
