package net.troja.picoro.components;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.pi4j.io.spi.SpiChannel;
import com.pi4j.io.spi.SpiDevice;
import com.pi4j.io.spi.SpiFactory;

/**
 * Code used from http://www.weargenius.in/thermocouple-interfacing-max31855.html
 */
@Service
public class ThermocoupleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ThermocoupleService.class);

    @Autowired
    private SpiDevice spiDevice;

    public ThermocoupleService() {
        LOGGER.info("Starting Thermocouple Service");
    }

    @Bean
    public SpiDevice initiallizeSpiDevice() {
        SpiDevice device = null;
        try {
            device = SpiFactory.getInstance(SpiChannel.CS0, SpiDevice.DEFAULT_SPI_SPEED, SpiDevice.DEFAULT_SPI_MODE);
        } catch (final IOException e) {
            LOGGER.error("Could not initialize SPI", e);
        }
        return device;
    }

    @Scheduled(fixedRate = 5000)
    public void print() {
        if(spiDevice != null) {
            final Optional<Double> value = getConversionValue();
            if(value.isPresent()) {
                LOGGER.info("Temperature {}", value);
            }
        }
    }

    public Optional<Double> getConversionValue() {
        final byte data[] = new byte[] { 0, 0, 0, 0 };// Dummy payloads. It's
        // not responsible for
        // anything.

        byte[] result;
        try {
            result = spiDevice.write(data);
        } catch (final IOException e) {
            LOGGER.error("Could not write to SPI", e);
            return Optional.empty();
        }
        // Request data from MAX31855 via
        // SPI with dummy pay-load

        if (((result[0] & 128) == 0) && ((result[1] & 1) == 1)) {// Sign bit is
            // 0 and D16 is
            // high
            // corresponds
            // to
            // Thermocouple
            // not
            // connected.
            LOGGER.warn("Thermocouple is not connected");
            return Optional.empty();
        }
        final String stringResult = String.format("%32s", Integer.toString(ByteBuffer.wrap(result).getInt(), 2))
                .replace(' ', '0');
        double value = 0.0;

        if (stringResult.charAt(0) == '1') { // Checking for signed bit. If need
            // to convert to 2's Complement.
            final StringBuilder onesComplementBuilder = new StringBuilder();

            for (final char bit : stringResult.substring(0, 12).toCharArray()) {
                onesComplementBuilder.append((bit == '0') ? 1 : 0); // if bit is
                // '0',
                // append a
                // 1. if bit
                // is '1',
                // append a
                // 0.
            }
            final String onesComplement = onesComplementBuilder.toString();
            value = -1 * (Integer.valueOf(onesComplement, 2) + 1); // two's
            // complement
            // = one's
            // complement
            // + 1. This
            // is the
            // positive
            // value of
            // our
            // original
            // binary
            // string,
            // so make
            // it
            // negative
            // again.

        } else {
            value = Integer.parseInt(stringResult.substring(0, 12), 2); // +ve
            // no
            // convert
            // to
            // double
            // value
        }

        if (stringResult.charAt(12) == '1') {
            value += 0.5;
        }
        if (stringResult.charAt(13) == '1') {
            value += 0.25;
        }

        return Optional.of(value);
    }
}
