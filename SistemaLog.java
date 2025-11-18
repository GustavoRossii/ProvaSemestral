//SINGLETON
class LoggerCentral {

    private static volatile LoggerCentral instanciaUnica;

    private LoggerCentral() {
        System.out.println(">> Inicializando sistema de Log (Abrindo arquivo e conexão)...");
    }

    public static LoggerCentral getInstance() {
        if (instanciaUnica == null) {
            synchronized (LoggerCentral.class) {
                if (instanciaUnica == null) {
                    instanciaUnica = new LoggerCentral();
                }
            }
        }
        return instanciaUnica;
    }

    public void registrarLog(String tipo, String mensagem) {
        synchronized (this) {
            System.out.println("[LOG - " + tipo + "] " + mensagem + " (Enviado para servidor)");
        }
    }
}

public class SistemaLog {
    public static void main(String[] args) {
        
        Runnable tarefaLog = () -> {
            LoggerCentral logger = LoggerCentral.getInstance();
            logger.registrarLog("INFO", "Execução na thread: " + Thread.currentThread().getName());
        };

        Thread t1 = new Thread(tarefaLog);
        Thread t2 = new Thread(tarefaLog);
        Thread t3 = new Thread(tarefaLog);

        t1.start();
        t2.start();
        t3.start();
    }
}