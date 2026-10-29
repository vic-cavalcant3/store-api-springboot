package br.edu.storeapi.controller;

import br.edu.storeapi.dto.ProdutoRequest;
import br.edu.storeapi.model.Produto;
import br.edu.storeapi.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

 private final ProdutoRepository repository;

 public ProdutoController(ProdutoRepository repository) {
  this.repository = repository;
 }

 // Prática 4: Listagem Geral (GET /api/produtos)
 @GetMapping
 public List<Produto> listar() {
  return repository.findAll();
 }

 // Prática 6: Consultar por ID (GET /api/produtos/{id})
 @GetMapping("/{id}")
 public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
  return repository.findById(id)
          .map(ResponseEntity::ok)
          .orElseGet(() -> ResponseEntity.notFound().build());
 }

 // Prática 5 e 9: Cadastrar com Validação (POST /api/produtos)
 @PostMapping
 public ResponseEntity<Produto> criar(@Valid @RequestBody ProdutoRequest dto) {
  Produto produto = new Produto();
  produto.setNome(dto.nome());
  produto.setDescricao(dto.descricao());
  produto.setPreco(dto.preco());
  produto.setEstoque(dto.estoque());
  produto.setCategoria(dto.categoria());

  Produto salvo = repository.save(produto);
  URI uri = URI.create("/api/produtos/" + salvo.getId());
  return ResponseEntity.created(uri).body(salvo);
 }

 // Prática 7: Atualizar (PUT /api/produtos/{id})
 @PutMapping("/{id}")
 public ResponseEntity<Produto> atualizar(@PathVariable Long id, @Valid @RequestBody ProdutoRequest dto) {
  return repository.findById(id)
          .map(produtoExistente -> {
           produtoExistente.setNome(dto.nome());
           produtoExistente.setDescricao(dto.descricao());
           produtoExistente.setPreco(dto.preco());
           produtoExistente.setEstoque(dto.estoque());
           produtoExistente.setCategoria(dto.categoria());

           Produto atualizado = repository.save(produtoExistente);
           return ResponseEntity.ok(atualizado);
          })
          .orElseGet(() -> ResponseEntity.notFound().build());
 }

 // Prática 7: Excluir (DELETE /api/produtos/{id})
 @DeleteMapping("/{id}")
 public ResponseEntity<Void> excluir(@PathVariable Long id) {
  if (!repository.existsById(id)) {
   return ResponseEntity.notFound().build();
  }
  repository.deleteById(id);
  return ResponseEntity.noContent().build();
 }

 // Prática 8: Pesquisa por nome (GET /api/produtos/busca?nome=...)
 @GetMapping("/busca")
 public List<Produto> buscarPorNome(@RequestParam String nome) {
  return repository.findByNomeContainingIgnoreCase(nome);
 }
}