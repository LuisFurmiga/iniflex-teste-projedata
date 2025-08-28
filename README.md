# Iniflex - Teste Mão na Massa (Projedata)

Projeto Java (Maven) que implementa todos os requisitos descritos no enunciado:
- `Pessoa` (nome, dataNascimento) — `LocalDate`
- `Funcionario` (salario `BigDecimal`, funcao `String`) extendendo `Pessoa`
- Classe `Principal` executa os itens 3.1 a 3.12

## Como rodar
1. Requisitos: **Java 17+** e **Maven** instalados
2. No terminal:
```bash
cd iniflex-teste-projedata
mvn -q -e -DskipTests package
java -cp target/iniflex-teste-projedata-1.0.0.jar br.com.projedata.iniflex.Principal
```
Você verá no console as impressões de cada etapa, com datas no formato `dd/MM/yyyy` e números no padrão PT-BR (ponto para milhar, vírgula para decimal).
> Se estiver utilizando uma IDE (ex.: VS Code, IntelliJ, Eclipse), basta abrir a classe `Principal.java` e executar diretamente.

## Observações
- O aumento de 10% (item 3.4) usa `HALF_UP` e fixa 2 casas decimais.
- O agrupamento por função (item 3.5) é feito com **Streams** e `groupingBy`.
- O funcionário mais velho (item 3.9) é encontrado comparando a **data de nascimento** (o mais antigo é o mais velho) e a idade é calculada via `Period`.
- Salário mínimo (item 3.12) considerado: **R$ 1212,00**.
