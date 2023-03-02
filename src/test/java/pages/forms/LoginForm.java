package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

import static aquality.selenium.elements.ElementType.CHECKBOX;

public class LoginForm extends Form {
    private static final String DOMAIN_DROPDOWN_ITEM = "//div[%d]";
    private final ITextBox txbPASSWORD = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'Password')]"), "\"Password\" text box");
    private final ITextBox txbEMAIL = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'email')]"), "\"Email\" text box");
    private final ITextBox txbDOMAIN = getElementFactory().getTextBox(By.xpath("//input[contains(@placeholder,'Domain')]"), "\"Domain\" text box");
    private final IButton btnDOMAIN_DROPDOWN_LIST_OPENER = getElementFactory().getButton(By.className("dropdown__opener"), "\"Domain Dropdown List Opener\" button");
    private final IButton btnDOMAIN_DROPDOWN_LIST_ITEMS = getElementFactory().getButton(By.xpath("//div[@class='dropdown__list']"), "\"Domain Dropdown List Item\" button");
    private final ICheckBox chbACCEPT = getElementFactory().getCheckBox(By.className("checkbox__box"), "\"Accept\" check box");
    private final IButton btnNEXT = getElementFactory().getButton(By.className("button--secondary"), "\"Next\" button");

    public LoginForm() {
        super(By.xpath("//div[@class='login-form']"), "\"LOGIN\" form");
    }

    public void inputPassword(String password) {
        txbPASSWORD.clearAndType(password);
    }

    public void inputEmail(String email) {
        txbEMAIL.clearAndType(email);
    }

    public void inputDomain(String domain) {
        txbDOMAIN.clearAndType(domain);
    }

    public void chooseDomain(int item_number) {
        btnDOMAIN_DROPDOWN_LIST_OPENER.clickAndWait();
        btnDOMAIN_DROPDOWN_LIST_ITEMS.findChildElement(By.xpath(String.format(DOMAIN_DROPDOWN_ITEM, item_number)), CHECKBOX).click();
    }

    public void uncheckCheckBox() {
        chbACCEPT.clickAndWait();
    }

    public void clickNextButton() {
        btnNEXT.clickAndWait();
    }
}
