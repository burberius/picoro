package net.troja.picoro.components;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@Component
public class Leds {
    private static final Logger LOGGER = LoggerFactory.getLogger(Leds.class);
    @Autowired
    private GpioPinDigitalOutput green;
    @Autowired
    private GpioPinDigitalOutput yellow;
    @Autowired
    private GpioPinDigitalOutput red;

    @PostConstruct
    public void init() {
        green.blink(800);
        yellow.blink(200);
        red.blink(3000);
        LOGGER.info("LEDs started");
    }

    @Bean(name = "green")
    public GpioPinDigitalOutput getGreen() {
        return getLed(RaspiPin.GPIO_27, "green");
    }

    @Bean(name = "yellow")
    public GpioPinDigitalOutput getYellow() {
        return getLed(RaspiPin.GPIO_28, "yellow");
    }

    @Bean(name = "red")
    public GpioPinDigitalOutput getRed() {
        return getLed(RaspiPin.GPIO_29, "red");
    }

    private GpioPinDigitalOutput getLed(final Pin gpioPin, final String name) {
        LOGGER.info("Init {}", gpioPin.getName());
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinDigitalOutput outputPin = gpio.provisionDigitalOutputPin(gpioPin, name, PinState.LOW);
        outputPin.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
        return outputPin;
    }
}
