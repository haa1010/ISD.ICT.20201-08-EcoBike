import entity.bike.Bike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BikeTest {
    private Bike bike;

    @BeforeEach
    void setUp() throws Exception {
        bike = new Bike();
    }

    @ParameterizedTest
    @CsvSource({
            "1,true"

    })
    public void testGetBikeById(int id, boolean expected) throws SQLException {
        Bike bike1 = bike.getBikeById(id);
        System.out.println(bike1.getId());
        assertEquals(expected, true);
    }
}