package pages;

import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CardsPage extends Form {
    private final ILabel lblPAGE_INDICATOR = getElementFactory().getLabel(By.xpath("//div[@class='page-indicator']"), "\"Page Indicator\"");
    private final ILabel lblTIMER = getElementFactory().getLabel(By.xpath("//div[contains(@class,'timer')]"), "\"Timer\"");

    public CardsPage() {
        super(By.xpath("//div[@class='game view']"), "\"CARDS\" page");
    }

    public String getPageNumber() {
        return lblPAGE_INDICATOR.getText();
    }

    public String getStartTimerValue() {
        return lblTIMER.getText();
    }
}
