import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class MainTest {
    @Disabled
    @Test
    @Timeout(value = 22)
    void checkMainExecutionSpeed() {
        try {
            Main.main(null);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
