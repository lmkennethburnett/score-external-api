package org.oagi.score.e2e.TS_38_WorkingBranchDataTypeManagementForDeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.oagi.score.e2e.BaseTest;
import org.oagi.score.e2e.obj.*;
import org.oagi.score.e2e.page.HomePage;
import org.oagi.score.e2e.page.core_component.DTViewEditPage;
import org.oagi.score.e2e.page.core_component.ViewEditCoreComponentPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.oagi.score.e2e.impl.PageHelper.click;


@Execution(ExecutionMode.CONCURRENT)
public class TC_38_13_DeletingDeveloperDT extends BaseTest {

    private final List<AppUserObject> randomAccounts = new ArrayList<>();

    @BeforeEach
    public void init() {
        super.init();

    }

    private void thisAccountWillBeDeletedAfterTests(AppUserObject appUser) {
        this.randomAccounts.add(appUser);
    }

    @Test
    @DisplayName("TC_38_13_TA_1")
    public void test_TA_1() {
        AppUserObject developerA;
        ReleaseObject branch;
        ArrayList<DTObject> dtForTesting = new ArrayList<>();
        DTObject baseCDT;
        {
            developerA = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
            thisAccountWillBeDeletedAfterTests(developerA);

            LibraryObject library = getAPIFactory().getLibraryAPI().getLibraryByName("connectSpec");
            branch = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber(library, "Working");
            NamespaceObject namespace = getAPIFactory().getNamespaceAPI().getNamespaceByURI(library, "http://www.openapplications.org/oagis/10");

            baseCDT = getAPIFactory().getCoreComponentAPI().getCDTByDENAndReleaseNum(library, "Code. Type", branch.getReleaseNumber());
            DTObject randomBDT = getAPIFactory().getCoreComponentAPI().createRandomBDT(baseCDT, developerA, namespace, "WIP");
            dtForTesting.add(randomBDT);
        }

        HomePage homePage = loginPage().signIn(developerA.getLoginId(), developerA.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage = homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        for (DTObject dt : dtForTesting) {
            DTViewEditPage dtViewEditPage = viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(dt.getDen(), branch.getReleaseNumber());
            assertTrue(dt.getOwnerUserId().equals(developerA.getAppUserId()));
            assertTrue(Integer.valueOf(dtViewEditPage.getRevisionFieldValue()) == 1);
            assertTrue(dt.getState().equals("WIP"));
            assertDoesNotThrow(() -> click(dtViewEditPage.getDeleteButton()));
            assertEquals("Are you sure you want to delete this core component?",
                    dtViewEditPage.getDeleteWarningDialogMessage());
            dtViewEditPage.hitDeleteAnywayButton();
            viewEditCoreComponentPage.setBranch(branch.getReleaseNumber());
            viewEditCoreComponentPage.setDEN(dt.getDen());
            viewEditCoreComponentPage.hitSearchButton();
            assertDoesNotThrow(() -> viewEditCoreComponentPage.getTableRecordByValue(dt.getDen()));
        }
    }

    @Test
    @DisplayName("TC_38_13_TA_2")
    public void test_TA_2() {
        AppUserObject developerA;
        ReleaseObject branch;
        ArrayList<DTObject> dtForTesting = new ArrayList<>();
        ArrayList<DTObject> derivedBDTs = new ArrayList<>();
        DTObject baseCDT;
        {
            developerA = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
            thisAccountWillBeDeletedAfterTests(developerA);

            LibraryObject library = getAPIFactory().getLibraryAPI().getLibraryByName("connectSpec");
            branch = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber(library, "Working");
            NamespaceObject namespace = getAPIFactory().getNamespaceAPI().getNamespaceByURI(library, "http://www.openapplications.org/oagis/10");

            baseCDT = getAPIFactory().getCoreComponentAPI().getCDTByDENAndReleaseNum(library, "Code. Type", branch.getReleaseNumber());
            DTObject randomBDT = getAPIFactory().getCoreComponentAPI().createRandomBDT(baseCDT, developerA, namespace, "WIP");
            dtForTesting.add(randomBDT);

            DTObject derivedBDTLevelOne = getAPIFactory().getCoreComponentAPI().createRandomBDT(randomBDT, developerA, namespace, "WIP");
            derivedBDTs.add(derivedBDTLevelOne);
        }

        HomePage homePage = loginPage().signIn(developerA.getLoginId(), developerA.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage = homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        for (DTObject dt : dtForTesting) {
            DTViewEditPage dtViewEditPage = viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(dt.getDen(), branch.getReleaseNumber());
            dtViewEditPage.hitDeleteButton();

            for (DTObject derivedDT : derivedBDTs){
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
                viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(derivedDT.getDen(), branch.getReleaseNumber());
                WebElement node = dtViewEditPage.getNodeByPath("/" + derivedDT.getDen());
                assertTrue(node.isDisplayed());
                assertEquals("Invalid state", dtViewEditPage.getInvalidStateIconText(node));
            }

            homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
            viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(dt.getDen(), branch.getReleaseNumber());
            dtViewEditPage.hitRestoreButton();

            for (DTObject derivedDT : derivedBDTs){
                homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
                viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(derivedDT.getDen(), branch.getReleaseNumber());
                WebElement node = dtViewEditPage.getNodeByPath("/" + derivedDT.getDen());
                assertTrue(node.isDisplayed());
                assertThrows(NoSuchElementException.class, () -> dtViewEditPage.getInvalidStateIconText(node));
            }

        }
    }

    @Test
    @DisplayName("TC_38_13_TA_3")
    public void test_TA_3() {
        AppUserObject developerA;
        ReleaseObject branch;
        ArrayList<DTObject> dtForTesting = new ArrayList<>();
        DTObject baseCDT;
        {
            developerA = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
            thisAccountWillBeDeletedAfterTests(developerA);

            LibraryObject library = getAPIFactory().getLibraryAPI().getLibraryByName("connectSpec");
            branch = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber(library, "Working");
            NamespaceObject namespace = getAPIFactory().getNamespaceAPI().getNamespaceByURI(library, "http://www.openapplications.org/oagis/10");

            baseCDT = getAPIFactory().getCoreComponentAPI().getCDTByDENAndReleaseNum(library, "Code. Type", branch.getReleaseNumber());
            DTObject randomBDT = getAPIFactory().getCoreComponentAPI().createRandomBDT(baseCDT, developerA, namespace, "Published");
            dtForTesting.add(randomBDT);
        }

        HomePage homePage = loginPage().signIn(developerA.getLoginId(), developerA.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage = homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        for (DTObject dt : dtForTesting) {
            DTViewEditPage dtViewEditPage = viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(dt.getDen(), branch.getReleaseNumber());
            dtViewEditPage.hitReviseButton();
            assertTrue(dtViewEditPage.getStateFieldValue().equals("WIP"));
            assertTrue(Integer.valueOf(dtViewEditPage.getRevisionFieldValue()) > 1);
            assertThrows(TimeoutException.class, () -> dtViewEditPage.hitDeleteButton());
            dtViewEditPage.moveToDraft();
            assertTrue(dtViewEditPage.getStateFieldValue().equals("Draft"));
            assertThrows(TimeoutException.class, () -> dtViewEditPage.hitDeleteButton());
            dtViewEditPage.moveToCandidate();
            assertTrue(dtViewEditPage.getStateFieldValue().equals("Candidate"));
            assertThrows(TimeoutException.class, () -> dtViewEditPage.hitDeleteButton());
        }
    }

    @AfterEach
    public void tearDown() {
        super.tearDown();
        // Delete random accounts
        this.randomAccounts.forEach(newUser -> {
            getAPIFactory().getAppUserAPI().deleteAppUserByLoginId(newUser.getLoginId());
        });
    }
}
