package weather;

import weather.observer.CurrentConditionsDisplay;
import weather.observer.IProcessor;
import weather.observer.StatisticalDisplay;
import weather.subject.WeatherData;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ApplicationStarter {
    public static void main(String[] args) {
        long currentTimeInMilli=System.currentTimeMillis();
        ScheduledExecutorService executorService= Executors.newScheduledThreadPool(1);
        IProcessor processor=(observer)->{
            //assume does some processing
        };
        WeatherData weatherData=new WeatherData();
        CurrentConditionsDisplay currentConditionsDisplay=new CurrentConditionsDisplay(processor,weatherData);

        StatisticalDisplay statisticalDisplay=new StatisticalDisplay(processor,weatherData);
        double temp=110;
        double humidity=11.1;
        double pressure=1;
        weatherData.setHumidity(temp++);
        weatherData.setPressure(pressure++);
        weatherData.setHumidity(humidity++);
        Runnable runnable= () -> {
                weatherData.stateChanged();
                System.out.println("______________________________________");
        };
        executorService.scheduleAtFixedRate(runnable,2,5,TimeUnit.SECONDS);
        while (true) {
            if(System.currentTimeMillis()-currentTimeInMilli>=20000){
                executorService.shutdown();
                break;
            }
        }
    }
}
