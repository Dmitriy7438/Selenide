import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CallbackTest {

    @Test
    void test1() {
        String planningDate = generateDate(4);

        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $x("//*[@data-test-id='city']//input").setValue("Вологда");
        $x("//input[@placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT,Keys.HOME), Keys.BACK_SPACE);
        $x("//input[@placeholder='Дата встречи']").setValue(planningDate);
        $x("//span[@data-test-id='name']//input").setValue("Дмитрий Агеев");
        $x("//span[@data-test-id='phone']//input").setValue("+79211448228");
        $x("//label[@data-test-id='agreement']").click();
        $x("//*[text()='Забронировать']").click();
        $x("//div[@data-test-id='notification']").should(visible, Duration.ofSeconds(15));
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + planningDate), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);
    }

    private String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
