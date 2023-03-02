package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class HelpForm extends Form {
    private final ITextBox txbHELP_FORM = getElementFactory().getTextBox(By.xpath("//div[contains(@class,'help-form')]"), "\"Help-Form\" text box");

    private final IButton btnHIDE_HELP_FORM = getElementFactory().getButton(By.xpath("//button[contains(@class,'help-form__send-to-bottom-button')]"), "\"Send to bottom\" (hide Help-Form) button");

    public HelpForm() {
        super(By.xpath("//div[contains(@class,'help-form')]"), "\"HELP-FORM\" form");
    }

    public void hideHelpForm() {
        btnHIDE_HELP_FORM.clickAndWait();
    }

    public boolean isHelpFormHidden() {
        return txbHELP_FORM.getAttribute("class").equals("help-form is-hidden");
    }
}
