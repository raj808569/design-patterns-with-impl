package weather.observer;

import weather.IObserver;
import weather.ISubject;
import weather.subject.WeatherData;

public class CurrentConditionsDisplay implements IObserver {
    private double temperature;
    private double humidity;
    private double pressure;
    private ISubject subject;
    private IProcessor processor;

    public CurrentConditionsDisplay(IProcessor processor,ISubject subject) {
        this.processor = processor;
        this.subject=subject;
        subject.register(this);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public IProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(IProcessor processor) {
        this.processor = processor;
    }
    @Override
    public void update(ISubject observable) {

        if(observable instanceof WeatherData){
            WeatherData weatherData=(WeatherData)observable;
            this.humidity=weatherData.getHumidity();
            this.pressure=weatherData.getPressure();
            this.temperature=weatherData.getPressure();
            System.out.println("Got Notification from Subject "+getClass());
            System.out.println(getClass()+"humidity "+this.humidity+"pressure "+this.pressure+" temperature "+this.temperature);

        }
        processor.process(this);
    }
}
