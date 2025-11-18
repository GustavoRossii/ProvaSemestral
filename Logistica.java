//FACTORY
interface Relatorio {
    void gerarRelatorio();
}

class RelatorioDiario implements Relatorio {
    @Override
    public void gerarRelatorio() {
        System.out.println("--- Gerando Relatório Diário ---");
        System.out.println("1. Coletando dados das últimas 24h.");
        System.out.println("2. Priorizando métricas de volume de entrega.");
        System.out.println("3. Exportando em formato PDF simplificado.");
    }
}

class RelatorioEmergencial implements Relatorio {
    @Override
    public void gerarRelatorio() {
        System.out.println("--- Gerando Relatório Emergencial ---");
        System.out.println(" ALERTA DE PRIORIDADE MÁXIMA ");
        System.out.println("1. Conectando fonte de dados em tempo real.");
        System.out.println("2. Priorizando incidentes e bloqueios.");
        System.out.println("3. Notificando diretoria via e-mail.");
    }
}

interface RelatorioFactory {
    Relatorio criarRelatorio();
}

class FabricaRelatorioDiario implements RelatorioFactory {
    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioDiario();
    }
}

class FabricaRelatorioEmergencial implements RelatorioFactory {
    @Override
    public Relatorio criarRelatorio() {
        return new RelatorioEmergencial();
    }
}

public class Logistica {
    public static void main(String[] args) {
        RelatorioFactory fabrica;
        
        fabrica = new FabricaRelatorioDiario();
        
        Relatorio relatorio1 = fabrica.criarRelatorio();
        relatorio1.gerarRelatorio();

        System.out.println(); 
        fabrica = new FabricaRelatorioEmergencial();

        Relatorio relatorio2 = fabrica.criarRelatorio();
        relatorio2.gerarRelatorio();
    }
}