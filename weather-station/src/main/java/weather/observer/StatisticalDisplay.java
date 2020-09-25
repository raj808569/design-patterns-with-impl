package weather.observer;

import weather.IObserver;
import weather.ISubject;
import weather.subject.WeatherData;

public class StatisticalDisplay implements IObserver {
    private double humidity;
    private double temperature;
    private IProcessor processor;
    private ISubject subject;
    public StatisticalDisplay(IProcessor processor,ISubject subject) {
        this.subject=subject;
        this.processor = processor;
        subject.register(this);
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public void update(ISubject observable) {
        if (observable instanceof WeatherData){
            WeatherData weatherData=(WeatherData)observable;
            this.humidity=weatherData.getHumidity();
            this.temperature=weatherData.getTemperature();
            System.out.println("Got Notification from Subject "+getClass());
            System.out.println(getClass()+"humidity "+this.humidity+" temperature "+this.temperature);
        }
        processor.process(this);
    }
}
