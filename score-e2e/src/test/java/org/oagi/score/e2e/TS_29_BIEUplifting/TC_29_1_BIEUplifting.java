package org.oagi.score.e2e.TS_29_BIEUplifting;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.oagi.score.e2e.BaseTest;
import org.oagi.score.e2e.api.CoreComponentAPI;
import org.oagi.score.e2e.menu.BIEMenu;
import org.oagi.score.e2e.obj.*;
import org.oagi.score.e2e.page.HomePage;
import org.oagi.score.e2e.page.bie.*;
import org.oagi.score.e2e.page.core_component.ACCExtensionViewEditPage;
import org.oagi.score.e2e.page.core_component.SelectAssociationDialog;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.oagi.score.e2e.impl.PageHelper.escape;
import static org.oagi.score.e2e.impl.PageHelper.getText;

@Execution(ExecutionMode.CONCURRENT)
public class TC_29_1_BIEUplifting extends BaseTest {
    private List<AppUserObject> randomAccounts = new ArrayList<>();

    @BeforeEach
    public void init() {
        super.init();
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();

        // Delete random accounts
        this.randomAccounts.forEach(randomAccount -> {
            getAPIFactory().getAppUserAPI().deleteAppUserByLoginId(randomAccount.getLoginId());
        });
    }

    private void thisAccountWillBeDeletedAfterTests(AppUserObject appUser) {
        this.randomAccounts.add(appUser);
    }

    @Test
    public void test_TA_29_1_1() {
        AppUserObject usera;
        String prev_release = "10.8.6";
        String curr_release = "10.8.8";
        usera = getAPIFactory().getAppUserAPI().createRandomEndUserAccount(false);
        thisAccountWillBeDeletedAfterTests(usera);
        HomePage homePage = loginPage().signIn(usera.getLoginId(), usera.getPassword());
        BIEMenu bieMenu = homePage.getBIEMenu();
        UpliftBIEPage upliftBIEPage = bieMenu.openUpliftBIESubMenu();
        upliftBIEPage.setSourceBranch(curr_release);
        assertThrows(TimeoutException.class, () -> upliftBIEPage.setTargetBranch(prev_release));
    }

    @Test
    public void test_TA_29_1_2() {
        ASCCPObject asccp, asccp_owner_usera, asccp_to_append, asccp_child, asccp_reuse;
        BCCPObject bccp, bccp_to_append, bccp_child, bccp_not_reuse;
        ACCObject acc;
        AppUserObject usera;
        NamespaceObject namespace;
        BusinessContextObject context;
        TopLevelASBIEPObject useraBIE;
        String prev_release = "10.8.6";
        String curr_release = "10.8.8";
        ReleaseObject prevReleaseObject = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber(prev_release);
        ReleaseObject currReleaseObject = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber(curr_release);
        {
            usera = getAPIFactory().getAppUserAPI().createRandomEndUserAccount(false);
            thisAccountWillBeDeletedAfterTests(usera);
            CoreComponentAPI coreComponentAPI = getAPIFactory().getCoreComponentAPI();
            namespace = getAPIFactory().getNamespaceAPI().createRandomEndUserNamespace(usera);

            /**
             * The owner of the ASCCP is usera
             */
            acc = coreComponentAPI.createRandomACC(usera, prevReleaseObject, namespace, "Production");
            DTObject dataType = coreComponentAPI.getBDTByGuidAndReleaseNum("dd0c8f86b160428da3a82d2866a5b48d", prevReleaseObject.getReleaseNumber());
            bccp = coreComponentAPI.createRandomBCCP(dataType, usera, namespace, "Production");
            bccp_child = coreComponentAPI.createRandomBCCP(dataType, usera, namespace, "Production");
            coreComponentAPI.appendBCC(acc, bccp, "Production");

            DTObject dataTypeWithSC = coreComponentAPI.getBDTByGuidAndReleaseNum("3292eaa5630b48ecb7c4249b0ddc760e", prevReleaseObject.getReleaseNumber());
            bccp_not_reuse = coreComponentAPI.createRandomBCCP(dataTypeWithSC, usera, namespace, "Production");

            ACCObject acc_association = coreComponentAPI.createRandomACC(usera, prevReleaseObject, namespace, "Production");
            ACCObject acc_association2 = coreComponentAPI.createRandomACC(usera, prevReleaseObject, namespace, "Production");

            bccp_to_append = coreComponentAPI.createRandomBCCP(dataType, usera, namespace, "Production");
            coreComponentAPI.appendBCC(acc_association, bccp_to_append, "Production");

            asccp_child = coreComponentAPI.createRandomASCCP(acc_association, usera, namespace, "Production");
            ASCCObject ascc = coreComponentAPI.appendASCC(acc_association2, asccp_child, "Production");
            ascc.setCardinalityMax(1);
            coreComponentAPI.updateASCC(ascc);

            asccp = coreComponentAPI.createRandomASCCP(acc_association, usera, namespace, "Production");
            asccp_to_append = coreComponentAPI.createRandomASCCP(acc_association, usera, namespace, "Production");
            asccp_reuse = coreComponentAPI.createRandomASCCP(acc_association2, usera, namespace, "Production");
            ascc = coreComponentAPI.appendASCC(acc, asccp, "Production");
            coreComponentAPI.appendExtension(acc, usera, namespace, "Published");
            asccp_owner_usera = coreComponentAPI.createRandomASCCP(acc, usera, namespace, "Production");

            context = getAPIFactory().getBusinessContextAPI().createRandomBusinessContext(usera);
            useraBIE = getAPIFactory().getBusinessInformationEntityAPI().generateRandomTopLevelASBIEP(Collections.singletonList(context), asccp_reuse, usera, "WIP");
        }

        HomePage homePage = loginPage().signIn(usera.getLoginId(), usera.getPassword());
        BIEMenu bieMenu = homePage.getBIEMenu();
        ViewEditBIEPage viewEditBIEPage = bieMenu.openViewEditBIESubMenu();
        CreateBIEForSelectTopLevelConceptPage createBIEForSelectTopLevelConceptPage = viewEditBIEPage.openCreateBIEPage().next(Collections.singletonList(context));
        createBIEForSelectTopLevelConceptPage.createBIE(asccp_owner_usera.getDen(), prev_release);
        viewEditBIEPage.openPage();
        viewEditBIEPage.setDEN(asccp_owner_usera.getDen());
        viewEditBIEPage.hitSearchButton();
        WebElement tr = viewEditBIEPage.getTableRecordAtIndex(1);
        EditBIEPage editBIEPage = viewEditBIEPage.openEditBIEPage(tr);
        ACCExtensionViewEditPage ACCExtensionViewEditPage = editBIEPage.extendBIELocallyOnNode("/" + asccp_owner_usera.getPropertyTerm() + "/Extension");
        SelectAssociationDialog selectCCPropertyPage = ACCExtensionViewEditPage.appendPropertyAtLast("/" + asccp_owner_usera.getPropertyTerm() + " User Extension Group. Details");
        selectCCPropertyPage.selectAssociation(asccp_to_append.getDen());
        selectCCPropertyPage = ACCExtensionViewEditPage.appendPropertyAtLast("/" + asccp_owner_usera.getPropertyTerm() + " User Extension Group. Details");
        selectCCPropertyPage.selectAssociation(bccp_not_reuse.getDen());
        selectCCPropertyPage = ACCExtensionViewEditPage.appendPropertyAtLast("/" + asccp_owner_usera.getPropertyTerm() + " User Extension Group. Details");
        selectCCPropertyPage.selectAssociation(asccp_reuse.getDen());
        ACCExtensionViewEditPage.setNamespace(namespace);
        ACCExtensionViewEditPage.hitUpdateButton();
        ACCExtensionViewEditPage.moveToQA();
        ACCExtensionViewEditPage.moveToProduction();
        editBIEPage.openPage();
        editBIEPage.clickOnDropDownMenuByPath("/" + asccp_owner_usera.getPropertyTerm() + "/Extension/" + bccp_not_reuse.getPropertyTerm());
        assertEquals(0, getDriver().findElements(By.xpath("//span[contains(text(),\"Reuse BIE\")]")).size());

        escape(getDriver());
        editBIEPage.openPage();
        SelectProfileBIEToReuseDialog selectProfileBIEToReuseDialog = editBIEPage.reuseBIEOnNode("/" + asccp_owner_usera.getPropertyTerm() + "/Extension/" + asccp_reuse.getPropertyTerm());
        selectProfileBIEToReuseDialog.selectBIEToReuse(useraBIE);

        assertEquals(0, getDriver().findElements(By.xpath("//span[.=\"" + asccp_reuse.getPropertyTerm() + "\"]//ancestor::div[1]/fa-icon")).size());
        WebElement asccpNode = editBIEPage.getNodeByPath("/" + asccp_owner_usera.getPropertyTerm() + "/" + asccp.getPropertyTerm());
        EditBIEPage.ASBIEPanel asbiePanel = editBIEPage.getASBIEPanel(asccpNode);
        asbiePanel.toggleUsed();
        asbiePanel.setCardinalityMax(199);
        asbiePanel.setCardinalityMin(77);
        asbiePanel.setContextDefinition("aContextDefinition");
        editBIEPage.hitUpdateButton();

        editBIEPage.openPage();
        asccpNode = editBIEPage.getNodeByPath("/" + asccp_owner_usera.getPropertyTerm() + "/" + asccp.getPropertyTerm());
        asbiePanel = editBIEPage.getASBIEPanel(asccpNode);
        assertEquals("199", getText(asbiePanel.getCardinalityMaxField()));
        assertEquals("77", getText(asbiePanel.getCardinalityMinField()));
        assertEquals("aContextDefinition", getText(asbiePanel.getContextDefinitionField()));

    }

    @Test
    public void test_TA_29_1_3() {

    }

    @Test
    public void test_TA_29_1_4() {

    }

    @Test
    public void test_TA_29_1_5a() {

    }

    @Test
    public void test_TA_29_1_5b() {

    }

    @Test
    public void test_TA_29_1_5c() {

    }

    @Test
    public void test_TA_29_1_5d() {

    }

    @Test
    public void test_TA_29_1_6a() {

    }

    @Test
    public void test_TA_29_1_6b() {

    }

    @Test
    public void test_TA_29_1_7() {

    }

    @Test
    public void test_TA_29_1_8() {

    }

    @Test
    public void test_TA_29_1_9a() {

    }

    @Test
    public void test_TA_29_1_9b() {

    }

    @Test
    public void test_TA_29_1_9c() {

    }

    @Test
    public void test_TA_29_1_10a() {

    }

    @Test
    public void test_TA_29_1_10b() {

    }

    @Test
    public void test_TA_29_1_11a() {

    }

    @Test
    public void test_TA_29_1_11b() {

    }

    @Test
    public void test_TA_29_1_12() {

    }

    @Test
    public void test_TA_29_1_13() {

    }

}
