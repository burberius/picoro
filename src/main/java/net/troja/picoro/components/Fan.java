package net.troja.picoro.components;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinPwmOutput;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.wiringpi.Gpio;

@Component
public class Fan {
    private static final Logger LOGGER = LoggerFactory.getLogger(Fan.class);

    private GpioPinPwmOutput fan;
    @Autowired
    private Lcd display;

    @PostConstruct
    public void init() {
        fan = getFan();
        Gpio.pwmSetMode(Gpio.PWM_MODE_MS);
        Gpio.pwmSetRange(1000);
        Gpio.pwmSetClock(500);
        setValue(0);
        LOGGER.info("Fan started");
        setValue(250);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setValue(500);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setValue(750);
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (final InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        setValue(1000);
    }

    /**
     * @param value only 0 - 100
     */
    public void setValue(final int value) {
        fan.setPwm(value);
        display.setFan(value);
    }

    public GpioPinPwmOutput getFan() {
        final GpioController gpio = GpioFactory.getInstance();
        final GpioPinPwmOutput pwm = gpio.provisionPwmOutputPin(RaspiPin.GPIO_01);
        return pwm;
    }
}
