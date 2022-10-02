package com.xbrain.domain.exception;

public class VendedorNaoEncontradaException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;
    public VendedorNaoEncontradaException(String message) {
        super(message);
    }

    public VendedorNaoEncontradaException(Long vendedorid) {
        this(String.format("Não existe cadastro de vendedor com esse código %d", vendedorid));
    }
}
