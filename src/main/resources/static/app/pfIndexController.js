﻿$(document).ready(function () {

    var forca = sessionStorage.getItem("usuario");

    forca.codigo = 6;

    put("dadosUsuario", JSON.stringify(forca)) ;

});

function iniciarProposta(cdPlano) {

    var proposta = get("propostaPf");

    if (proposta == null)
        proposta = getRepository("propostaPf");

    plano = getRepository("plano");
    plano.cdPlano = cdPlano;

    proposta.planos = [];
    proposta.planos.push(plano);

    put("propostaPf", JSON.stringify(proposta));


    window.location.href = "venda_pf_dados_proposta.html";
}
