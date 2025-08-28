package br.com.projedata.iniflex;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {

    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public void aplicarAumentoPercentual(BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual);
        this.salario = this.salario.multiply(fator).setScale(2, RoundingMode.HALF_UP);
    }

    public String formatarData() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return getDataNascimento().format(fmt);
    }

    public String formatarSalario() {
        DecimalFormatSymbols br = new DecimalFormatSymbols(new Locale("pt","BR"));
        br.setDecimalSeparator(',');
        br.setGroupingSeparator('.');
        DecimalFormat df = new DecimalFormat("#,##0.00", br);
        return df.format(salario);
    }

    @Override
    public String toString() {
        return String.format("%-12s | %-10s | R$ %12s | %-15s",
                getNome(), formatarData(), formatarSalario(), getFuncao());
    }
}
