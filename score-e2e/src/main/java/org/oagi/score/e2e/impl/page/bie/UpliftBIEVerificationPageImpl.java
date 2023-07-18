package org.oagi.score.e2e.impl.page.bie;

import org.oagi.score.e2e.impl.page.BasePageImpl;
import org.oagi.score.e2e.page.BasePage;
import org.oagi.score.e2e.page.bie.UpliftBIEVerificationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.oagi.score.e2e.impl.PageHelper.*;

public class UpliftBIEVerificationPageImpl extends BasePageImpl implements UpliftBIEVerificationPage {
    private static final By NEXT_BUTTON_LOCATOR =
            By.xpath("//span[contains(text(), \"Next\")]//ancestor::button[1]");

    public UpliftBIEVerificationPageImpl(BasePage parent) {
        super(parent);
    }

    @Override
    protected String getPageUrl() {
        return getConfig().getBaseUrl().resolve("/profile_bie/uplift").toString();
    }

    @Override
    public void openPage() {
        String url = getPageUrl();
        getDriver().get(url);
        assert "Uplift BIE".equals(getText(getTitle()));
    }

    @Override
    public WebElement getTitle() {
        return visibilityOfElementLocated(getDriver(), By.className("mat-card-title"));
    }

    @Override
    public WebElement getNextButton() {
        return elementToBeClickable(getDriver(), NEXT_BUTTON_LOCATOR);
    }


}