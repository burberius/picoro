package net.troja.picoro.components;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.pi4j.component.temperature.TemperatureSensor;
import com.pi4j.component.temperature.impl.TmpDS18B20DeviceType;
import com.pi4j.io.w1.W1Device;
import com.pi4j.io.w1.W1Master;

/**
 * This is a DS18B20 sensor connected with 1-wire.
 */
@Component
public class InputTemperature {
    private static final Logger LOGGER = LoggerFactory.getLogger(InputTemperature.class);
    @Autowired
    private TemperatureSensor sensor;
    @Autowired
    private Lcd display;

    @PostConstruct
    public void started() {
        LOGGER.info("Input temperature component started");
    }

    @Scheduled(fixedRate = 1000)
    public void updateDisplay() {
        display.writeInputTemperatur(sensor.getTemperature());
    }

    @Bean
    public static TemperatureSensor getTemperatureSensor() {
        final W1Master master = new W1Master();
        final List<W1Device> w1Devices = master.getDevices(TmpDS18B20DeviceType.FAMILY_CODE);
        if (w1Devices.size() != 1) {
            LOGGER.warn("Found {} input temperature devices!", w1Devices.size());
        }
        return (TemperatureSensor) w1Devices.get(0);
    }
}
