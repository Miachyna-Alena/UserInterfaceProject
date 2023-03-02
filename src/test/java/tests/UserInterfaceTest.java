package tests;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.core.logging.Logger;
import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.testng.annotations.Test;
import pages.CardsPage;
import pages.HomePage;
import pages.forms.*;
import utils.RandomUtils;

import java.io.File;

import static org.testng.Assert.*;

public class UserInterfaceTest extends BaseTest {
    private static final Logger LOGGER = AqualityServices.getLogger();
    protected static final ISettingsFile TEST_DATA = new JsonSettingsFile("data/test-data.json");
    private static final File FILE = new File(TEST_DATA.getValue("/AVATAR_FILE").toString());
    private static final String EMAIL = RandomUtils.generateRandomString((int) TEST_DATA.getValue("/EMAIL_LENGTH"));
    private static final String DOMAIN = RandomUtils.generateRandomString((int) TEST_DATA.getValue("/DOMAIN_LENGTH"));
    private static final String PASSWORD = RandomUtils.generateRandomPassword((int) TEST_DATA.getValue("/PASSWORD_LENGTH"), EMAIL);
    private static final String CARD1 = TEST_DATA.getValue("/CARD1").toString();
    private static final String CARD2 = TEST_DATA.getValue("/CARD2").toString();
    private static final String CARD3 = TEST_DATA.getValue("/CARD3").toString();
    private static final String TIMER_START_TIME = TEST_DATA.getValue("/TIMER_START_TIME").toString();
    private static final int DOMAIN_ITEM_NUMBER = (int) TEST_DATA.getValue("/DOMAIN_ITEM_NUMBER");
    private static final int INTERESTS_NUMBER = (int) TEST_DATA.getValue("/INTERESTS_NUMBER");

    @Test
    public void testCaseOne() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().waitForDisplayed(), String.format("%s is NOT open.", homePage.getName()));
        LOGGER.info("Click the start link.");
        homePage.clickStartLink();

        LoginForm loginForm = new LoginForm();
        assertTrue(loginForm.state().waitForDisplayed(), String.format("%s is NOT open.", loginForm.getName()));

        CardsPage cardsPage = new CardsPage();
        assertEquals(cardsPage.getPageNumber(), CARD1, "Wrong page number!!!");
        LOGGER.info("Input random valid password, email.");
        loginForm.inputPassword(PASSWORD);
        loginForm.inputEmail(EMAIL);
        loginForm.inputDomain(DOMAIN);
        LOGGER.info("Choose domain from domain dropdown list.");
        loginForm.chooseDomain(DOMAIN_ITEM_NUMBER);
        LOGGER.info("Accept the terms of use.");
        loginForm.uncheckCheckBox();
        LOGGER.info("Click \"Next\" button.");
        loginForm.clickNextButton();

        AvatarAndInterestsForm avatarAndInterestsForm = new AvatarAndInterestsForm();
        assertTrue(avatarAndInterestsForm.state().waitForDisplayed(), String.format("%s is NOT open.", avatarAndInterestsForm.getName()));
        assertEquals(cardsPage.getPageNumber(), CARD2, "Wrong page number!!!");
        LOGGER.info("Choose 3 random interest.");
        avatarAndInterestsForm.unselectAllInterests();
        avatarAndInterestsForm.selectFewInterests(INTERESTS_NUMBER);
        LOGGER.info("Upload image.");
        avatarAndInterestsForm.uploadImage(FILE);
        LOGGER.info("Click \"Next\" button.");
        avatarAndInterestsForm.clickNextButton();

        PersonalDetailsForm personalDetailsForm = new PersonalDetailsForm();
        assertTrue(personalDetailsForm.state().waitForDisplayed(), String.format("%s is NOT open.", personalDetailsForm.getName()));
        assertEquals(cardsPage.getPageNumber(), CARD3, "Wrong page number!!!");
    }

    @Test
    public void testCaseTwo() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().waitForDisplayed(), String.format("%s is NOT open.", homePage.getName()));
        LOGGER.info("Click the start link.");
        homePage.clickStartLink();

        HelpForm helpForm = new HelpForm();
        assertTrue(helpForm.state().waitForDisplayed(), String.format("%s is NOT open.", helpForm.getName()));
        LOGGER.info("Hide help-form..");
        helpForm.hideHelpForm();
        assertTrue(helpForm.isHelpFormHidden(), String.format("%s is NOT hidden", helpForm.getName()));
    }

    @Test
    public void testCaseThree() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().waitForDisplayed(), String.format("%s is NOT open.", homePage.getName()));
        LOGGER.info("Click the start link.");
        homePage.clickStartLink();

        CookiesForm cookiesForm = new CookiesForm();
        assertTrue(cookiesForm.state().waitForDisplayed(), String.format("%s is NOT open.", cookiesForm.getName()));
        cookiesForm.waitCookiesBanner();
        LOGGER.info("Accept cookies.");
        cookiesForm.acceptCookies();
        assertFalse(cookiesForm.isCookiesBannerClosed(), "COOKIES BANNER did NOT CLOSE.");
    }

    @Test
    public void testCaseFour() {
        HomePage homePage = new HomePage();
        assertTrue(homePage.state().waitForDisplayed(), String.format("%s is NOT open.", homePage.getName()));
        LOGGER.info("Click the start link.");
        homePage.clickStartLink();

        CardsPage cardsPage = new CardsPage();
        assertTrue(cardsPage.state().waitForDisplayed(), String.format("%s  is NOT open.", cardsPage.getName()));
        LOGGER.info("Validate that timer starts from \"00: 00\".");
        assertEquals(cardsPage.getStartTimerValue(), TIMER_START_TIME, "Timer NOT starts from \"00: 00\".");
    }
}
