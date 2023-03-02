package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class CookiesForm extends Form {
    private final ITextBox txbCOOKIES_BANNER = getElementFactory().getTextBox(By.xpath("//div[@class='cookies']"), "\"Cookies Banner\"");
    private final IButton btnACCEPT_COOKIES = getElementFactory().getButton(By.xpath("//div[@class='align__cell']//button[starts-with(@class,'button')]"), "\"Accept Cookies\" button ");

    public CookiesForm() {
        super(By.xpath("//div[@class='cookies']"), "\"COOKIES\" form");
    }

    public void waitCookiesBanner() {
        txbCOOKIES_BANNER.state().waitForDisplayed();
    }

    public void acceptCookies() {
        btnACCEPT_COOKIES.clickAndWait();
    }

    public boolean isCookiesBannerClosed() {
        return txbCOOKIES_BANNER.state().isDisplayed();
    }
}
