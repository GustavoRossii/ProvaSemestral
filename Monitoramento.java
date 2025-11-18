import java.util.ArrayList;
import java.util.List;

class SensorAmbiental {
    private List<Observer> observers = new ArrayList<>(); 

    private double temperatura;
    private double umidade;
    private int indicePoluicao;

    public double getTemperatura() { return temperatura; }
    public double getUmidade() { return umidade; }
    public int getIndicePoluicao() { return indicePoluicao; }

    public void setDados(double temperatura, double umidade, int indicePoluicao) {
        this.temperatura = temperatura;
        this.umidade = umidade;
        this.indicePoluicao = indicePoluicao;
        System.out.println("\n>> Sensor: Novos dados coletados (T:" + temperatura + ", U:" + umidade + ", P:" + indicePoluicao + ")");
        notifyAllObservers(); 
    }

    public void attach(Observer observer) {
        observers.add(observer); 
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(); 
        }
    }
}

abstract class Observer {
    protected SensorAmbiental sensor; 
    public abstract void update();    
}

class PainelDeControle extends Observer {
    public PainelDeControle(SensorAmbiental sensor) {
        this.sensor = sensor;
        this.sensor.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Atualizando display: " + sensor.getTemperatura() + "°C | " + sensor.getUmidade() + "%");
    }
}

class ModuloAlertas extends Observer {
    public ModuloAlertas(SensorAmbiental sensor) {
        this.sensor = sensor;
        this.sensor.attach(this);
    }

    @Override
    public void update() {
        if (sensor.getIndicePoluicao() > 100) {
            System.out.println("PERIGO: Índice de poluição crítico (" + sensor.getIndicePoluicao() + ")!");
        }
    }
}

class RegistroHistorico extends Observer {
    public RegistroHistorico(SensorAmbiental sensor) {
        this.sensor = sensor;
        this.sensor.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Gravando no banco de dados: T=" + sensor.getTemperatura() + "; P=" + sensor.getIndicePoluicao());
    }
}

public class Monitoramento {
    public static void main(String[] args) {
        SensorAmbiental sensor = new SensorAmbiental();

        new PainelDeControle(sensor);
        new ModuloAlertas(sensor);
        new RegistroHistorico(sensor);

        sensor.setDados(25.0, 60.0, 40);  // Normal
        sensor.setDados(28.5, 55.0, 120); // Alerta 
        sensor.setDados(22.0, 80.0, 30);  // Att
    }
}