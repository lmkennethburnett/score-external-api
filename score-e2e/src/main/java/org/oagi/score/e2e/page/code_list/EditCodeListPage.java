package org.oagi.score.e2e.page.code_list;

import org.oagi.score.e2e.page.Page;
import org.openqa.selenium.WebElement;

public interface EditCodeListPage extends Page {
    void setDefinition(String test_definition);

    void setDefinitionSource(String test_definition_source);

    WebElement getDefinitionField();

    WebElement getDefinitionSourceField();

    void hitUpdateButton();

    WebElement getUpdateButton();

    EditCodeListValueDialog addCodeListValue();

    WebElement getAddCodeListValueButton();

    AddCodeListCommentDialog hitAddCommentButton();

    WebElement getAddCommentButton();

    void hitRevise();

    WebElement getReviseButton();

    WebElement getCodeListNameField();

    WebElement getVersionField();

    WebElement getDeprecatedSelectField();

    WebElement getNamespaceSelectField();

    WebElement getReleaseField();

    WebElement getRevisionField();

    WebElement getAgencyIDListField();
}
