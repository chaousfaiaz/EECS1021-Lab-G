import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class EventTests {

    @Test
    void coinFlipTest() {
        ButtonListener b = new ButtonListener(null, null);

        for (int i = 0; i < 10; i++) {
            b.simulateCoinTosses();
            double value1 = b.getPercentHeads();

            b.simulateCoinTosses();
            double value2 = b.getPercentHeads();

            assertEquals(value2, value1, 10);

        }
    }

    @Test
    void valueRoundedTest() {
        ButtonListener b = new ButtonListener(null, null);

        for (int i = 0; i < 10; i++) {
            b.simulateCoinTosses();
            double value1 = b.getPercentHeads();

            b.simulateCoinTosses();
            double value2 = b.getPercentHeads();

            assertEquals(false, BigDecimal.valueOf(value1).scale() > 2);
            assertEquals(false, BigDecimal.valueOf(value2).scale() > 2);
        }
    }

}
