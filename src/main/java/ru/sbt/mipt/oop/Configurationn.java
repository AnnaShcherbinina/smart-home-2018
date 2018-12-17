package ru.sbt.mipt.oop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration
import ru.sbt.mipt.oop.adapter.EventsManager;
import ru.sbt.mipt.oop.adapter.HomeEventsObserverAdapter;
import ru.sbt.mipt.oop.events.*;
import ru.sbt.mipt.oop.eventsgenerate.RandomGenerateEvent;
import ru.sbt.mipt.oop.loader.LoadSmartHome;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configurationn {
    private SmartHome smartHome;
    private RandomGenerateEvent randomGenerateEvent;
    private EventsManager eventsManager;
    private LoadSmartHome loadSmartHome = new LoadSmartHome();

    public Configurationn() throws IOException {
        smartHome = loadSmartHome.load();
        randomGenerateEvent = new RandomGenerateEvent();
        eventsManager = new HomeEventsObserverAdapter(configureManager(smartHome), randomGenerateEvent);
        //eventsManager = new EventsManagerAdapter(new SensorEventsManager());
    }


    @Bean
    SmartHome getSmartHome() {
        return smartHome;
    }

    @Bean
    EventsManager getEventsManager() {
        return eventsManager;
    }


    private ArrayList<SensorEventProcessor> configureManager(SmartHome smartHome) {
        ArrayList<SensorEventProcessor> sensorEventProcessor = new ArrayList<>();
        SensorEventProcessor lightEventProcessor = new AlarmDecorator(new LightEventProcessor(smartHome), smartHome);
        SensorEventProcessor doorEventProcessor = new AlarmDecorator(new DoorEventProcessor(smartHome), smartHome);
        SensorEventProcessor hallDoorEventProcessor = new AlarmDecorator(new HallDoorEventProcessor(smartHome), smartHome);
        AlarmEventProcessor alarmEventProcessor = new AlarmEventProcessor(smartHome);
        sensorEventProcessor.add(lightEventProcessor);
        sensorEventProcessor.add(doorEventProcessor);
        sensorEventProcessor.add(hallDoorEventProcessor);
        sensorEventProcessor.add(alarmEventProcessor);
        return sensorEventProcessor;
    }
}
