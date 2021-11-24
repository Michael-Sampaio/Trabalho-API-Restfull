package br.org.serratec.backend.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.org.serratec.backend.dto.AlterarPedidoDTO;
import br.org.serratec.backend.dto.InserirPedidoDTO;
import br.org.serratec.backend.dto.PedidoDTO;
import br.org.serratec.backend.exception.RecursoBadRequestException;
import br.org.serratec.backend.model.Pedido;
import br.org.serratec.backend.repository.PedidoRepository;
import br.org.serratec.backend.service.PedidoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    @ApiOperation(value = "Cadastrar um pedido", notes = "Cadastro de pedido")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra um pedido"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    @ResponseStatus(HttpStatus.CREATED)

    public ResponseEntity<Object> inserir(@Valid @RequestBody InserirPedidoDTO inserirPedidoDTO) {
        try {
            PedidoDTO pedidoDTO = pedidoService.inserir(inserirPedidoDTO);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(pedidoDTO.getCliente()).toUri();
            return ResponseEntity.created(uri).body(pedidoDTO);
        } catch (RecursoBadRequestException recursoBadRequestException) {
            return ResponseEntity.badRequest().body(recursoBadRequestException.getMessage());
        }
    }

    // public Pedido inserir(@Valid @RequestBody Pedido pedido) {
    // return pedidoRepository.save(pedido);
    // }

    @PutMapping("/{id}")
    @ApiOperation(value = "Alterar um pedido", notes = "Alteração de um pedido")
    @ApiResponses(value = { @ApiResponse(code = 201, message = "Altera um pedido"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })
    @ResponseStatus(HttpStatus.OK)

    public ResponseEntity<Object> alterar(@PathVariable Long id,
            @Valid @RequestBody AlterarPedidoDTO alterarPedidoDTO) throws RecursoBadRequestException {

                if (pedidoService.alterar(id, alterarPedidoDTO) != null) {
                    return ResponseEntity.ok(alterarPedidoDTO);
                }
                return ResponseEntity.notFound().build();
            }

    @GetMapping
    @ApiOperation(value = "Listar Pedidos", notes = "Listagem de pedidos")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna todos os pedidos"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<List<PedidoDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar um pedido por id", notes = "Busca um pedido")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna um pedido"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<PedidoDTO> buscar(@PathVariable Long id) {
        Optional<Pedido> PedidoDTO = pedidoRepository.findById(id);
        if (!PedidoDTO.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidoService.buscar(id));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar um pedido", notes = "Deleta pedido")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Exclui um pedido"),
            @ApiResponse(code = 204, message = "Exclui um pedido e retorna vazio"),
            @ApiResponse(code = 401, message = "Erro de autenticação"),
            @ApiResponse(code = 403, message = "Recurso proibido"),
            @ApiResponse(code = 404, message = "Recurso não encontrado"),
            @ApiResponse(code = 500, message = "Erro de servidor") })

    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        pedidoService.deletar(id);
        return ResponseEntity.ok().build();
    }

}
