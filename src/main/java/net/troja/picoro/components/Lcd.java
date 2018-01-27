package net.troja.picoro.components;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.pi4j.component.lcd.LCDTextAlignment;
import com.pi4j.component.lcd.impl.I2CLcdDisplay;

@Component
public class Lcd {
    private static final Logger LOGGER = LoggerFactory.getLogger(Lcd.class);
    private static final int LCD_ROWS = 4;
    private static final int LCD_COLUMNS = 20;
    private static final int I2C_BUS_ID = 1;
    private static final int I2C_DEVICE_ID = 39;

    @Autowired
    private I2CLcdDisplay display;

    @PostConstruct
    public void init() {
        display.clear();
        LOGGER.info("LCD started");
    }

    public void writeInputTemperatur(final double temperature) {
        display.write(0, "Tin " + temperature + "C");
    }

    @Bean
    public static I2CLcdDisplay getDisplay() {
        try {
            return new I2CLcdDisplay(LCD_ROWS, LCD_COLUMNS, I2C_BUS_ID, I2C_DEVICE_ID, 3, 0, 1, 2, 7, 6, 5, 4);
        } catch (final Exception e) {
            LOGGER.error("Could not start LCD", e);
            return null;
        }
    }

    public void writeRoastTemperatur(final Double temperature) {
        display.write(0, "Tro " + temperature + "C", LCDTextAlignment.ALIGN_RIGHT);
    }

    public void setFan(final int value) {
        display.write(1, "Fan " + value + "%");
    }
}
