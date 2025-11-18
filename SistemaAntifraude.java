//CHAIN
class Transacao {
    double valor;
    String localizacao;
    String dispositivoID;

    public Transacao(double valor, String localizacao, String dispositivoID) {
        this.valor = valor;
        this.localizacao = localizacao;
        this.dispositivoID = dispositivoID;
    }
}

abstract class Verificador {
    protected Verificador proximoVerificador;

    public void setProximo(Verificador proximo) {
        this.proximoVerificador = proximo;
    }

    public void processarVerificacao(Transacao t) {
        if (verificar(t)) {
            if (proximoVerificador != null) {
                proximoVerificador.processarVerificacao(t);
            } else {
                System.out.println("SUCESSO: Transação aprovada com segurança.");
            }
        } else {
            System.out.println("BLOQUEIO: Transação barrada em " + this.getClass().getSimpleName());
        }
    }

    protected abstract boolean verificar(Transacao t);
}


class AnaliseValor extends Verificador {
    @Override
    protected boolean verificar(Transacao t) {
        System.out.println("1. Analisando valor...");
        if (t.valor > 5000) {
            System.out.println("   X Falha: Valor muito alto (" + t.valor + ")");
            return false;
        }
        return true;
    }
}

class AnaliseGeolocalizacao extends Verificador {
    @Override
    protected boolean verificar(Transacao t) {
        System.out.println("2. Verificando localização...");
        if ("Narnia".equalsIgnoreCase(t.localizacao)) {
            System.out.println("   X Falha: Localização suspeita detectada.");
            return false;
        }
        return true;
    }
}

class AnaliseDispositivo extends Verificador {
    @Override
    protected boolean verificar(Transacao t) {
        System.out.println("3. Verificando dispositivo...");
        if ("Unknown".equals(t.dispositivoID)) {
            System.out.println("   X Falha: Dispositivo não reconhecido.");
            return false;
        }
        return true;
    }
}

public class SistemaAntifraude {
    public static void main(String[] args) {
        Verificador v1_Valor = new AnaliseValor();
        Verificador v2_Geo = new AnaliseGeolocalizacao();
        Verificador v3_Device = new AnaliseDispositivo();
        v1_Valor.setProximo(v2_Geo);
        v2_Geo.setProximo(v3_Device);

        System.out.println("--- Teste 1: Transação Válida ---");
        v1_Valor.processarVerificacao(new Transacao(1000, "Brasil", "iPhone12"));
        
        System.out.println("\n--- Teste 2: Valor Suspeito (Falha na 1ª etapa) ---");
        v1_Valor.processarVerificacao(new Transacao(10000, "Brasil", "iPhone12"));

        System.out.println("\n--- Teste 3: Localização Inválida (Falha na 2ª etapa) ---");
        v1_Valor.processarVerificacao(new Transacao(200, "Narnia", "PC-Gamer"));
    }
}
