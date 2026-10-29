# Laboratório: Desenvolvimento de REST API com Spring Boot

**Aluno:** Victor Rodrigues Cavalcante Rocha  
**Turma:** B - Análise e Desenvolvimento de Sistemas (ADS) - 2º Semestre  
**Disciplina:** Back-End

---

## Respostas das Questões Teóricas

Ao longo do desenvolvimento deste laboratório e com base no material das aulas, aqui estão as respostas para as questões teóricas:

### 1. Diferença entre @Controller e @RestController?
Pelo que entendi nas aulas, o `@Controller` tradicional é mais usado quando a gente quer devolver páginas visuais mesmo (como views em HTML usando Thymeleaf, por exemplo). Já o `@RestController` é o que usamos neste projeto de API, porque ele é basicamente a junção do `@Controller` com o `@ResponseBody`. Isso significa que, por padrão, ele pega os objetos Java que nossos métodos retornam e já converte automaticamente para JSON para enviar na resposta, o que é ideal para APIs REST.

### 2. Qual a função de @RequestBody?
A função do `@RequestBody` é "pegar" o corpo (body) da requisição HTTP que chega (por exemplo, aquele JSON que a gente digita lá no Postman na hora de fazer um POST ou PUT) e converter isso automaticamente para um objeto Java (no nosso caso, o `ProdutoRequest`). Sem essa anotação, o Spring não saberia como preencher os dados recebidos na nossa classe.

### 3. Diferença entre @PathVariable e @RequestParam?
Os dois servem para extrair valores da URL da requisição, mas de formas diferentes:
- **@PathVariable:** Pega um valor que faz parte da própria estrutura da rota (caminho). Por exemplo, em `/api/produtos/1`, ele extrai esse `1` para usarmos como ID na busca.
- **@RequestParam:** Pega valores que são passados como parâmetros de consulta (query parameters), que vêm depois do ponto de interrogação `?` na URL. Usamos isso na prática de pesquisa: `/api/produtos/busca?nome=note`, onde o Spring pega a palavra "note" para filtrarmos no banco.

### 4. Por que utilizar ResponseEntity?
O `ResponseEntity` é muito útil porque nos dá o controle total sobre a resposta que a nossa API vai devolver pro cliente. Em vez de simplesmente devolver o objeto (o que sempre mandaria um status 200 genérico), com o `ResponseEntity` a gente consegue escolher exatamente qual código HTTP (Status Code) vai junto, além de poder adicionar cabeçalhos (headers) personalizados, como fizemos ao devolver o `URI` de onde o recurso foi criado no método POST.

### 5. Quando utilizar HTTP 200, 201, 204 e 404?
Durante os testes no Postman vi bastante esses códigos. Eles servem para avisar o cliente sobre o que aconteceu com a requisição:
- **200 (OK):** Usamos quando a requisição deu certo de forma geral. Por exemplo, quando listamos produtos (GET) ou atualizamos com sucesso (PUT).
- **201 (Created):** Usamos especificamente quando um novo recurso acabou de ser criado e salvo no banco de dados com sucesso (no nosso caso, no método POST).
- **204 (No Content):** Usamos quando uma requisição deu certo, mas a gente não tem nenhum corpo (JSON) para devolver. Usei isso no método DELETE: apagou com sucesso, mas não tem o que mostrar de volta.
- **404 (Not Found):** Usamos quando o cliente tenta acessar, atualizar ou excluir algo que não existe. Por exemplo, buscar por um ID que não está no banco de dados.