package com.xbrain.enums;

import lombok.Getter;

@Getter
public enum ProblemType {

    MSG_ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
    MSG_ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
    MSG_INCOPREENSIVEL("/mensagem-incopreensivel", "Mensagem incopreensivel");

    private String title;
    private String uri;

   ProblemType(String path, String title) {
        this.uri = "https://xbrain.com.br" + path;
       this.title = title;
   }
}
