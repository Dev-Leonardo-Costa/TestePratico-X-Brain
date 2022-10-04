package com.xbrain.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

    MSG_ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MSG_RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    MSG_INCOPREENSIVEL("/mensagem-incopreensivel", "Mensagem incopreensivel"),
    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");
    private String title;
    private String uri;

    ProblemType(String path, String title) {
        this.uri = "https://xbrain.com.br" + path;
        this.title = title;
    }
}
