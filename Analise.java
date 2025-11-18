// STATE
interface ModeloInvestimento {
    void calcularRisco(double valorPatrimonio);    
} 

class ModeloAgressivo implements ModeloInvestimento{
    @Override
    public void calcularRisco(double valorPatrimonio){
        System.out.println("--- Cálculo: Modelo Agressivo ---");
        double risco = valorPatrimonio * 0.25;
        System.out.println("Aplicação de derivativos e ações voláteis");
        System.out.println("Risco estimado: R$" + risco);
    }
}

class ModeloModerado implements ModeloInvestimento{
    @Override
    public void calcularRisco(double valorPatrimonio){
        System.out.println("--- Cálculo: Modelo Moderado ---");
        double risco = valorPatrimonio * 0.10;
        System.out.println("Fundos imobiliários e tesouros");
        System.out.println("Risco estimado: R$" + risco);
    }
}

class ModeloConservador implements ModeloInvestimento{
    @Override
    public void calcularRisco(double valorPatrimonio){
        System.out.println("--- Cálculo: Modelo Conservador ---");
        double risco = valorPatrimonio * 0.01;
        System.out.println("Foco total em renda fixa e liquidez diária.");
        System.out.println("Risco estimado: R$" + risco);
    }
}

public class Analise {

    private ModeloInvestimento modeloAtual;

    public Analise(){
        this.modeloAtual = new ModeloConservador();
    }

    public void setModelo(ModeloInvestimento novoModelo) {
        this.modeloAtual = novoModelo;
        System.out.println("Modelo de cálculo alterado.");
    }

    public void processarAnalise(double valor) {
        modeloAtual.calcularRisco(valor);
    }

    public static void main(String[] args){
        Analise sistema =new Analise();
        double patrimonioCliente = 100000.00;

        sistema.processarAnalise(patrimonioCliente);
        System.out.println();
        
        sistema.setModelo(new ModeloModerado());
        sistema.processarAnalise(patrimonioCliente);
        System.out.println();

        sistema.setModelo(new ModeloAgressivo());
        sistema.processarAnalise(patrimonioCliente);

    }
}

