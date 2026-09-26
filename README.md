# Rest Assured API Automation

Projeto exclusivamente de automação de testes de API.

## Stack
- Java 17
- Maven
- Rest Assured
- JUnit 5
- Jackson
- JSON Schema Validator

## Estrutura

```text
src/test/java/
├── base/        # configuração comum
├── config/      # URL e Specifications
├── data/        # DTOs e factories de massa
├── requests/    # chamadas HTTP
├── tests/       # cenários e assertions
└── utils/       # utilitários

src/test/resources/
├── data/        # massas externas
├── features/    # futuro Cucumber
└── schemas/     # contratos JSON
```

Não existe `src/main/java`, pois o código da aplicação/API não pertence a este projeto.

## Executar

```bash
mvn clean test
```

URL customizada:

```bash
mvn clean test -DbaseUrl=https://serverest.dev
```

## Próximas evoluções
- autenticação/token
- DTOs de request/response separados
- testes parametrizados
- massas JSON
- validação de JSON Schema
- logs somente em falhas
- reports
- múltiplos ambientes
- GitHub Actions
