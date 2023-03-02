package pages.forms;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ICheckBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import utils.UploadUtils;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static aquality.selenium.elements.ElementType.CHECKBOX;

public class AvatarAndInterestsForm extends Form {
    private static final String XPATH_TO_INTERESTS_CHECK_BOXES = "//div[@class='avatar-and-interests__interests-list']//label";
    private static final String FILTER_UNSELECT_ALL = "interest_unselectall";
    private static final String FILTER_SELECT_ALL = "interest_selectall";
    private final IButton btnUPLOAD = getElementFactory().getButton(By.xpath("//a[contains(@class,'upload-button')]"), "\"Upload\" button");
    private final IButton btnNEXT = getElementFactory().getButton(By.xpath("//button[contains(@class,'button--stroked')]"), "\"Next\" button");

    public AvatarAndInterestsForm() {
        super(By.xpath("//div[@class='avatar-and-interests']"), "\"AVATAR and INTERESTS\" form");
    }

    private List<ICheckBox> getInterestsCheckBoxes() {
        return getElementFactory().findElements(By.xpath(XPATH_TO_INTERESTS_CHECK_BOXES), CHECKBOX);
    }

    private ICheckBox getUnselectAllInterestsCheckBox() {
        List<ICheckBox> checkBoxesInterests = getInterestsCheckBoxes();
        return checkBoxesInterests.stream().filter(checkBox -> FILTER_UNSELECT_ALL.equals(checkBox.getAttribute("for"))).findFirst().orElseThrow();
    }

    public void unselectAllInterests() {
        getUnselectAllInterestsCheckBox().clickAndWait();
    }

    private List<ICheckBox> getUnselectInterest() {
        List<ICheckBox> checkBoxesInterests = getInterestsCheckBoxes();
        checkBoxesInterests.removeIf(checkBox -> Objects.equals(checkBox.getAttribute("for"), FILTER_UNSELECT_ALL));
        checkBoxesInterests.removeIf(checkBox -> Objects.equals(checkBox.getAttribute("for"), FILTER_SELECT_ALL));
        return checkBoxesInterests;
    }

    public void selectFewInterests(int number) {
        Random random = new Random();
        List<ICheckBox> checkBoxesInterests = getUnselectInterest();

        for (int i = 0; i < number; i++) {
            checkBoxesInterests.remove(random.nextInt(checkBoxesInterests.size())).clickAndWait();
        }
    }

    public void uploadImage(File file) {
        btnUPLOAD.clickAndWait();
        UploadUtils.uploadFile(file);
    }

    public void clickNextButton() {
        btnNEXT.clickAndWait();
    }
}
