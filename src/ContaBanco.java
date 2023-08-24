import java.math.BigDecimal;

public class ContaBanco {

    private int numConta;
    private String tipo;
    private String dono;
    private String mensalidade;
    private BigDecimal saldo;
    private boolean status;
    public BigDecimal valorSaque;
    private String[] cedulas = {"200","100","50","20","10","5","2","1","0.5","0.25","0.10","0.05","0.01"};
    public int[] saque = {0,0,0,0,0,0,0,0,0,0,0,0,0};
    public int[] notasDisponiveis = {2,2,3,3,2,2,3,3,2,2,3,3,2};
    private int numArray;
    private BigDecimal resultado;


    //SETTERS
    public void setDono(String dono) {
        this.dono = dono;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public void setNumArray(int numArray) {
        this.numArray = numArray;
    }

    public void setSaque(int[] saque) {
        this.saque = saque;
    }

    public void setSaldo(BigDecimal saldo) {this.saldo = saldo;}
    public void setValorSaque(BigDecimal valorSaque) {this.valorSaque = valorSaque;}

    public void setResultado(BigDecimal resultado) {this.resultado = resultado;}

    public void setCedulas(String[] cedulas) {
        this.cedulas = cedulas;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMensalidade(String mensalidade) {
        this.mensalidade = mensalidade;
    }
//GETTERS

    public String getMensalidade() {
        return mensalidade;
    }

    public boolean isStatus() {
        return status;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }
    public BigDecimal getValorSaque() {return valorSaque;}
    public BigDecimal getResultado() {return resultado;}

    public String getCedulas(int n) {
        return cedulas[n];
    }

    public int getNumArray() {
        return numArray;
    }

    public int getNumConta() {
        return numConta;
    }
    public int getSaque(int n) {
        return saque[n];
    }
    public String getDono() {
        return dono;
    }

    public String getTipo() {
        return tipo;
    }

    //contrutor
    public ContaBanco(){
        setSaldo(new BigDecimal("0"));
        setStatus(false);
    }

    //Metodos especificos

    public BigDecimal sacarDinheiro(String v){
        this.setValorSaque(new BigDecimal(v));//coloca o valor recebido no valor de saque
        if (this.getSaldo().compareTo(this.getValorSaque()) >= 0) { //compara o valor de saque para retirar da conta
            for (numArray = 0; this.getValorSaque().compareTo(new BigDecimal("0")) > 0 && numArray <= 12;) {//soma no array
                //divide o valor ate o numero ser < que zero
                this.setResultado(this.getValorSaque().subtract(new BigDecimal(this.getCedulas(numArray))));//Retira o valor do valor inicial para contras as notas que seram devolvidas
                //resultado = valorSaque - cedulas[numArray];
                if (this.getResultado().compareTo(new BigDecimal("0")) >= 0 && notasDisponiveis[numArray] > 0) {//compara o valor sacado e se for positovo
                    this.setValorSaque(this.getResultado());
                    notasDisponiveis[numArray]--;
                    saque[numArray]++;

                } else {
                    numArray++;
                }
            }

            this.setSaldo(this.getSaldo().subtract(new BigDecimal(v)) );//Atualiza o valor do saldo

            System.out.println("O valor do saldo da sua conta é " + getSaldo());
            int x;
            for(x=0;x < 13;x++){
                System.out.println(getCedulas(x) + " : " +  getSaque(x));
            }


        }
        return (saldo);


    }
    public void abrirConta(String m, String t, int n){
        setDono(m);
        setTipo(t);
        setNumConta(n);
        setStatus(true);
        if (getTipo()=="CC"){
            setSaldo(new BigDecimal("50"));
        }else {
            setSaldo(new BigDecimal("150"));
        }
    }

    public void fecharConta(){
        if (getSaldo().compareTo(new BigDecimal("0")) < 0){
            System.out.println("Essa conta ainda tem Dinheiro!");
        } else if (getSaldo().compareTo(new BigDecimal("0")) > 0){
            System.out.println("Essa conta esdta em debito!");
        }else {
            setStatus(false);
        }
    }

    public void depositar(String v){
        if (isStatus()){
            setSaldo(getSaldo().add(new BigDecimal(v)));
        } else{
            System.out.println("Erro! Impossivel Depositar!");
        }

    }

    public void pagarMensalidade(){

        if (this.getTipo() == "CC"){
            setMensalidade("12");
        }else{
            setMensalidade("20");
        }
        if (isStatus()){
            if (getSaldo().compareTo(new BigDecimal(mensalidade)) > 0){
                setSaldo(getSaldo().subtract(new BigDecimal(getMensalidade())));
                System.out.println("Mensalidade paga com sucesso!");
            }else{
                System.out.println("Erro! Não é possivel pagar a mensalidade");
            }
        }else{
            System.out.println("Impossivel");
        }
    }
}
