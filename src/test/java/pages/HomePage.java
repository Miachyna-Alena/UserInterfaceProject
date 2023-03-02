package pages;

import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HomePage extends Form {
    private final ILink lnkSTART = getElementFactory().getLink(By.xpath("//a[@class='start__link']"), "\"Start\" link");

    public HomePage() {
        super(By.xpath("//button[@class='start__button']"), "\"HOME\" page");
    }

    public void clickStartLink() {
        lnkSTART.clickAndWait();
    }
}
