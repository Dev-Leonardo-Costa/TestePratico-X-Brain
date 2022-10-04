package com.xbrain.controller;

import com.xbrain.assembler.VendaModelAssembler;
import com.xbrain.domain.exception.NegocioException;
import com.xbrain.domain.exception.VendedorNaoEncontradaException;
import com.xbrain.domain.model.Venda;
import com.xbrain.domain.service.CadastroVendaService;
import com.xbrain.dto.VendaModelAddDTO;
import com.xbrain.dto.VendaModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    private CadastroVendaService cadastroVenda;

    @Autowired
    private VendaModelAssembler vendaModelAssembler;

    @GetMapping
    public List<VendaModelDTO> buscarTodas() {
        return vendaModelAssembler.toCollectionModelVendaDTO(cadastroVenda.buscarTodas());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendaModelDTO adicionar(@RequestBody @Valid VendaModelAddDTO vendaModelAddDTO) {
        try {
            Venda venda = vendaModelAssembler.toDomainObject(vendaModelAddDTO);
            return vendaModelAssembler.toModelVenda(cadastroVenda.salvar(venda));
        } catch (VendedorNaoEncontradaException exception) {
            throw new NegocioException(exception.getMessage(), exception);
        }
    }
}