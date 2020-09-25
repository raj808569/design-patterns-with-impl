package weather.subject;

import weather.IObserver;
import weather.ISubject;
import weather.IWeatherData;

import java.util.ArrayList;
import java.util.List;

public class WeatherData implements IWeatherData, ISubject {
    private List<IObserver> observers;
    private double temperature;
    private double humidity;
    private double pressure;

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void register(IObserver observer) {
        if(observers==null){
            observers=new ArrayList<IObserver>();
        }
        if (!observers.contains(observer))
            observers.add(observer);
    }

    public void deRegister(IObserver observer) throws Exception {
        if(observers.contains(observer)) {
            observers.remove(observer);
        }else{
            throw new Exception("you need to be registered first");
        }

    }

    public void notifyObserver(ISubject observable) {
        observers.stream().forEach(observer->{
                observer.update(this);
        });
    }

    public void stateChanged() {
        notifyObserver(this);
    }
}
