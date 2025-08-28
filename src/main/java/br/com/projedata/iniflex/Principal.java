package br.com.projedata.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        // 3.1 – Inserir todos os funcionários (na ordem da tabela fornecida)
        List<Funcionario> funcionarios = new ArrayList<>(List.of(
            new Funcionario("Maria",  LocalDate.of(2000,10,18), new BigDecimal("2009.44"), "Operador"),
            new Funcionario("João",   LocalDate.of(1990,5,12),  new BigDecimal("2284.38"), "Operador"),
            new Funcionario("Caio",   LocalDate.of(1961,5,2),   new BigDecimal("9836.14"), "Coordenador"),
            new Funcionario("Miguel", LocalDate.of(1988,10,14), new BigDecimal("19119.88"), "Diretor"),
            new Funcionario("Alice",  LocalDate.of(1995,1,5),   new BigDecimal("2234.68"), "Recepcionista"),
            new Funcionario("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"),
            new Funcionario("Arthur", LocalDate.of(1993,3,31),  new BigDecimal("4071.84"), "Contador"),
            new Funcionario("Laura",  LocalDate.of(1994,7,8),   new BigDecimal("3017.45"), "Gerente"),
            new Funcionario("Heloísa",LocalDate.of(2003,5,24),  new BigDecimal("1606.85"), "Eletricista"),
            new Funcionario("Helena", LocalDate.of(1996,9,2),   new BigDecimal("2799.93"), "Gerente")
        ));

        // 3.2 – Remover o funcionário “João” da lista.
        funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));

        // 3.3 – Imprimir todos os funcionários (data dd/MM/aaaa e valores com . milhar e , decimal)
        System.out.println("===== 3.3 Funcionários =====");
        imprimirFuncionarios(funcionarios);

        // 3.4 – 10% de aumento de salário
        funcionarios.forEach(f -> f.aplicarAumentoPercentual(new BigDecimal("0.10")));
        System.out.println("\n===== 3.4 Após aumento de 10% =====");
        imprimirFuncionarios(funcionarios);

        // 3.5 – Agrupar por função em um MAP (chave = função; valor = lista)
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream()
            .collect(Collectors.groupingBy(Funcionario::getFuncao, HashMap::new, Collectors.toList()));

        // 3.6 – Imprimir agrupados por função
        System.out.println("\n===== 3.6 Agrupados por função =====");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            imprimirFuncionarios(lista);
            System.out.println();
        });

        // 3.8 – Aniversariantes dos meses 10 e 12
        System.out.println("\n===== 3.8 Aniversariantes em outubro (10) e dezembro (12) =====");
        funcionarios.stream()
            .filter(f -> {
                int m = f.getDataNascimento().getMonthValue();
                return m == 10 || m == 12;
            })
            .forEach(System.out::println);

        // 3.9 – Funcionário com maior idade (mais velho)
        System.out.println("\n===== 3.9 Funcionário com maior idade =====");
        Funcionario maisVelho = funcionarios.stream()
            .min(Comparator.comparing(Funcionario::getDataNascimento))
            .orElse(null);
        if (maisVelho != null) {
            int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
            System.out.printf("%s | %d anos%n", maisVelho.getNome(), idade);
        }

        // 3.10 – Lista por ordem alfabética
        System.out.println("\n===== 3.10 Ordenados por nome (A-Z) =====");
        funcionarios.stream()
            .sorted(Comparator.comparing(Funcionario::getNome, String.CASE_INSENSITIVE_ORDER))
            .forEach(System.out::println);

        // 3.11 – Total dos salários
        System.out.println("\n===== 3.11 Total dos salários =====");
        BigDecimal total = funcionarios.stream()
            .map(Funcionario::getSalario)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total: R$ " + formatarBigDecimal(total));

        // 3.12 – Quantos salários mínimos ganha cada funcionário (salário mínimo R$1212,00)
        System.out.println("\n===== 3.12 Salários mínimos por funcionário =====");
        funcionarios.forEach(f -> {
            BigDecimal qtd = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.printf("%-12s | %6s salários mínimos%n", f.getNome(), formatarBigDecimal(qtd));
        });
    }

    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
        funcionarios.forEach(System.out::println);
    }

    private static String formatarBigDecimal(BigDecimal valor) {
        DecimalFormatSymbols br = new DecimalFormatSymbols(new Locale("pt","BR"));
        br.setDecimalSeparator(',');
        br.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", br);
        return df.format(valor);
    }
}
