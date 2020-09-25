package weather.observer;

import weather.IObserver;
import weather.subject.WeatherData;

public interface IProcessor {
    public void process(IObserver observer);

   }

