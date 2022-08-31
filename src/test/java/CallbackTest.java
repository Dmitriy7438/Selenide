import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {

    @Test
    void test1() {

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@data-test-id='city']//input").setValue("Вологда");
        $x("//input[@placeholder='Дата встречи']").setValue(getDate());
        $x("//span[@data-test-id='name']//input").setValue("Дмитрий Агеев");
        $x("//span[@data-test-id='phone']//input").setValue("+79211448228");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@data-test-id='notification']").should(visible, Duration.ofSeconds(15));
    }

    private String getDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 3);
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return formatter.format(cal.getTime());
    }
}
