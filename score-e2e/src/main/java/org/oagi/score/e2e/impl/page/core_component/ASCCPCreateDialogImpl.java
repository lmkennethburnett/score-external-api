package org.oagi.score.e2e.impl.page.core_component;

import org.oagi.score.e2e.impl.page.SearchBarPageImpl;
import org.oagi.score.e2e.obj.ASCCPObject;
import org.oagi.score.e2e.page.core_component.ASCCPCreateDialog;
import org.oagi.score.e2e.page.core_component.ASCCPViewEditPage;
import org.oagi.score.e2e.page.core_component.ViewEditCoreComponentPage;
import org.openqa.selenium.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.Duration.ofMillis;
import static org.oagi.score.e2e.impl.PageHelper.*;

public class ASCCPCreateDialogImpl extends SearchBarPageImpl implements ASCCPCreateDialog {

    private static final By STATE_SELECT_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//*[contains(text(), \"State\")]//ancestor::mat-form-field[1]//mat-select");

    private static final By DEPRECATED_SELECT_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//*[contains(text(), \"Deprecated\")]//ancestor::mat-form-field[1]//mat-select");

    private static final By TAG_SELECT_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//*[contains(text(), \"Tag\")]//ancestor::mat-form-field[1]//mat-select");

    private static final By OWNER_SELECT_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//*[contains(text(), \"Owner\")]//ancestor::div[1]/mat-select[1]");

    private static final By UPDATER_SELECT_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//*[contains(text(), \"Updater\")]//ancestor::div[1]/mat-select[1]");

    private static final By DROPDOWN_SEARCH_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//input[@aria-label=\"dropdown search\"]");

    private static final By UPDATED_START_DATE_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//input[contains(@placeholder, \"Updated start date\")]");

    private static final By UPDATED_END_DATE_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//input[contains(@placeholder, \"Updated end date\")]");

    private static final By DEFINITION_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//input[contains(@placeholder, \"Definition\")]");

    private static final By MODULE_FIELD_LOCATOR =
            By.xpath("//mat-dialog-container//input[contains(@placeholder, \"Module\")]");

    private static final By CANCEL_BUTTON_LOCATOR =
            By.xpath("//mat-dialog-container//span[contains(text(), \"Cancel\")]//ancestor::button[1]");

    private static final By CREATE_BUTTON_LOCATOR =
            By.xpath("//mat-dialog-container//span[contains(text(), \"Create\")]//ancestor::button[1]");

    private ViewEditCoreComponentPageImpl parent;

    private String branch;

    public ASCCPCreateDialogImpl(ViewEditCoreComponentPageImpl parent, String branch) {
        super(parent.getDriver(), "//mat-dialog-container");
        this.parent = parent;
        this.branch = branch;
    }

    @Override
    public boolean isOpened() {
        WebElement title;
        try {
            title = getTitle();
        } catch (TimeoutException e) {
            return false;
        }
        assert "Select ACC to create ASCCP".equals(getText(title.findElement(By.tagName("span"))));
        return true;
    }

    @Override
    public WebElement getTitle() {
        return visibilityOfElementLocated(getDriver(), By.xpath("//mat-dialog-container//div[contains(@class, \"mat-mdc-dialog-title\")]"));
    }

    @Override
    public WebElement getStateSelectField() {
        return visibilityOfElementLocated(getDriver(), STATE_SELECT_FIELD_LOCATOR);
    }

    @Override
    public void setState(String state) {
        click(getStateSelectField());
        WebElement optionField = visibilityOfElementLocated(getDriver(),
                By.xpath("//mat-dialog-container//mat-option//span[contains(text(), \"" + state + "\")]"));
        click(optionField);
        escape(getDriver());
    }

    @Override
    public WebElement getDeprecatedSelectField() {
        return visibilityOfElementLocated(getDriver(), DEPRECATED_SELECT_FIELD_LOCATOR);
    }

    @Override
    public void setDeprecated(boolean deprecated) {
        click(getDeprecatedSelectField());
        WebElement optionField = visibilityOfElementLocated(getDriver(),
                By.xpath("//mat-dialog-container//mat-option//span[contains(text(), \"" + (deprecated ? "True" : "False") + "\")]"));
        click(optionField);
    }

    @Override
    public WebElement getOwnerSelectField() {
        return visibilityOfElementLocated(getDriver(), OWNER_SELECT_FIELD_LOCATOR);
    }

    @Override
    public void setOwner(String owner) {
        click(getOwnerSelectField());
        sendKeys(visibilityOfElementLocated(getDriver(), DROPDOWN_SEARCH_FIELD_LOCATOR), owner);
        WebElement searchedSelectField = visibilityOfElementLocated(getDriver(),
                By.xpath("//mat-dialog-container//mat-option//span[contains(text(), \"" + owner + "\")]"));
        click(searchedSelectField);
        escape(getDriver());
    }

    @Override
    public WebElement getUpdaterSelectField() {
        return visibilityOfElementLocated(getDriver(), UPDATER_SELECT_FIELD_LOCATOR);
    }

    @Override
    public void setUpdater(String updater) {
        click(getUpdaterSelectField());
        sendKeys(visibilityOfElementLocated(getDriver(), DROPDOWN_SEARCH_FIELD_LOCATOR), updater);
        WebElement searchedSelectField = visibilityOfElementLocated(getDriver(),
                By.xpath("//mat-dialog-container//mat-option//span[contains(text(), \"" + updater + "\")]"));
        click(searchedSelectField);
        escape(getDriver());
    }

    @Override
    public WebElement getUpdatedStartDateField() {
        return visibilityOfElementLocated(getDriver(), UPDATED_START_DATE_FIELD_LOCATOR);
    }

    @Override
    public void setUpdatedStartDate(LocalDateTime updatedStartDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        sendKeys(getUpdatedStartDateField(), formatter.format(updatedStartDate));
    }

    @Override
    public WebElement getUpdatedEndDateField() {
        return visibilityOfElementLocated(getDriver(), UPDATED_END_DATE_FIELD_LOCATOR);
    }

    @Override
    public void setUpdatedEndDate(LocalDateTime updatedEndDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        sendKeys(getUpdatedEndDateField(), formatter.format(updatedEndDate));
    }

    @Override
    public WebElement getDENField() {
        return getInputFieldInSearchBar();
    }

    @Override
    public void setDEN(String den) {
        sendKeys(getDENField(), den);
    }

    @Override
    public WebElement getDefinitionField() {
        return visibilityOfElementLocated(getDriver(), DEFINITION_FIELD_LOCATOR);
    }

    @Override
    public void setDefinition(String definition) {
        sendKeys(getDefinitionField(), definition);

    }

    @Override
    public WebElement getModuleField() {
        return visibilityOfElementLocated(getDriver(), MODULE_FIELD_LOCATOR);
    }

    @Override
    public void setModule(String module) {
        sendKeys(getModuleField(), module);
    }

    @Override
    public WebElement getTagSelectField() {
        return visibilityOfElementLocated(getDriver(), TAG_SELECT_FIELD_LOCATOR);
    }

    @Override
    public void setTag(String tag) {
        click(getTagSelectField());
        WebElement optionField = visibilityOfElementLocated(getDriver(),
                By.xpath("//mat-dialog-container//mat-option//span[contains(text(), \"" + tag + "\")]"));
        click(optionField);
    }

    @Override
    public void hitSearchButton() {
        click(getSearchButton());
        waitFor(ofMillis(500L));
    }

    @Override
    public WebElement getTableRecordAtIndex(int idx) {
        return visibilityOfElementLocated(getDriver(), By.xpath("//mat-dialog-container//tbody/tr[" + idx + "]"));
    }

    @Override
    public WebElement getTableRecordByValue(String value) {
        return visibilityOfElementLocated(getDriver(), By.xpath("//mat-dialog-container//td//span[contains(text(), \"" + value + "\")]/ancestor::tr"));
    }

    @Override
    public WebElement getColumnByName(WebElement tableRecord, String columnName) {
        return tableRecord.findElement(By.className("mat-column-" + columnName));
    }

    @Override
    public void setItemsPerPage(int items) {
        WebElement itemsPerPageField = elementToBeClickable(getDriver(),
                By.xpath("//mat-dialog-container//div[.=\" Items per page: \"]/following::mat-form-field//mat-select"));
        click(getDriver(), itemsPerPageField);
        waitFor(ofMillis(500L));
        WebElement itemField = elementToBeClickable(getDriver(),
                By.xpath("//mat-dialog-container//span[contains(text(), \"" + items + "\")]//ancestor::mat-option//div[1]//preceding-sibling::span"));
        click(getDriver(), itemField);
        waitFor(ofMillis(500L));
    }

    @Override
    public WebElement getCancelButton() {
        return elementToBeClickable(getDriver(), CANCEL_BUTTON_LOCATOR);
    }

    @Override
    public ViewEditCoreComponentPage cancel() {
        click(getCancelButton());
        return parent;
    }

    @Override
    public WebElement getCreateButton() {
        return elementToBeClickable(getDriver(), CREATE_BUTTON_LOCATOR);
    }

    @Override
    public ASCCPViewEditPage create(String den) {
        setDEN(den);
        hitSearchButton();

        retry(() -> {
            WebElement tr;
            WebElement td;
            try {
                tr = getTableRecordAtIndex(1);
                td = getColumnByName(tr, "den");
            } catch (TimeoutException e) {
                throw new NoSuchElementException("Cannot locate an ACC using " + den, e);
            }
            String denColumn = getText(td.findElement(By.tagName("span")));
            if (!denColumn.contains(den)) {
                throw new NoSuchElementException("Cannot locate a ACC using " + den);
            }
            WebElement select = getColumnByName(tr, "select");
            click(select);
        });

        click(getCreateButton());
        waitFor(ofMillis(1000L));

        String url = getDriver().getCurrentUrl();
        BigInteger asccpManifestId = new BigInteger(url.substring(url.lastIndexOf("/") + 1));

        ASCCPObject asccp = parent.getAPIFactory().getCoreComponentAPI().getASCCPByManifestId(asccpManifestId);
        ASCCPViewEditPage asccpViewEditPage = new ASCCPViewEditPageImpl(parent, asccp);
        assert asccpViewEditPage.isOpened();
        return asccpViewEditPage;
    }
}
