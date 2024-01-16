package test.java;

import com.company.Main;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainTest {

    @Test
    public void testPartOne(){
        String testString = "dn2ncpals9vds7";
        String expectedVal = "27";
        String result = Main.partOne(testString);
        Assertions.assertEquals(expectedVal, result);
    }
}
