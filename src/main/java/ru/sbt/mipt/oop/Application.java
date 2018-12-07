package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.events.DoorEventProcessor;
import ru.sbt.mipt.oop.events.HallDoorEventProcessor;
import ru.sbt.mipt.oop.events.LightEventProcessor;
import ru.sbt.mipt.oop.events.SensorEventProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static ru.sbt.mipt.oop.SensorEventType.*;

public class Application {

    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(Paths.get("smart-home-1.js")));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        // начинаем цикл обработки событий
        DoorEventProcessor doorEventProcessor = new DoorEventProcessor(smartHome);
        LightEventProcessor lightEventProcessor = new LightEventProcessor(smartHome);
        HallDoorEventProcessor hallDoorEventProcessor = new HallDoorEventProcessor(smartHome);
        ArrayList<SensorEventProcessor> handlers = new ArrayList<>();
        handlers.add(doorEventProcessor);
        handlers.add(lightEventProcessor);
        handlers.add(hallDoorEventProcessor);
        HomeEventsObserver homeEventsObserver = new HomeEventsObserver(handlers);
        homeEventsObserver.loop();

    }

}
