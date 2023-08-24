import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        ContaBanco c1 = new ContaBanco();
        c1.abrirConta("teste","CC", 123456);
        c1.setSaldo(new BigDecimal("1000"));
        c1.sacarDinheiro("600");
        //System.out.println("Seu novo saldo é " + c1.getSaldo());
        //System.out.println(c1.saque[12]);
        c1.depositar("500  ");
        System.out.println(c1.getSaldo());
        c1.pagarMensalidade();
    }
}