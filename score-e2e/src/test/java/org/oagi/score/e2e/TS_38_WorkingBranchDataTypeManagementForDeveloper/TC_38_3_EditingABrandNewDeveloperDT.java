package org.oagi.score.e2e.TS_38_WorkingBranchDataTypeManagementForDeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.oagi.score.e2e.BaseTest;
import org.oagi.score.e2e.impl.api.jooq.entity.tables.records.DtScRecord;
import org.oagi.score.e2e.obj.*;
import org.oagi.score.e2e.page.HomePage;
import org.oagi.score.e2e.page.core_component.DTViewEditPage;
import org.oagi.score.e2e.page.core_component.ViewEditCoreComponentPage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.oagi.score.e2e.AssertionHelper.assertDisabled;
import static org.oagi.score.e2e.impl.PageHelper.click;
import static org.oagi.score.e2e.impl.PageHelper.escape;

@Execution(ExecutionMode.CONCURRENT)
public class TC_38_3_EditingABrandNewDeveloperDT extends BaseTest {

    private final List<AppUserObject> randomAccounts = new ArrayList<>();

    @BeforeEach
    public void init() {
        super.init();

    }

    private void thisAccountWillBeDeletedAfterTests(AppUserObject appUser) {
        this.randomAccounts.add(appUser);
    }

    @Test
    @DisplayName("TC_38_3_TA_1")
    public void test_TA_1() {
        AppUserObject developerA;
        ReleaseObject branch;
        ArrayList<DTObject> dtForTesting = new ArrayList<>();
        CodeListObject codeList;
        {
            developerA = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
            thisAccountWillBeDeletedAfterTests(developerA);

            branch = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber("Working");
            NamespaceObject namespace = getAPIFactory().getNamespaceAPI().getNamespaceByURI("http://www.openapplications.org/oagis/10");

            codeList = getAPIFactory().getCodeListAPI().
                    createRandomCodeList(developerA, namespace, branch, "WIP");

            DTObject cdt = getAPIFactory().getCoreComponentAPI().getCDTByDENAndReleaseNum("Code. Type", branch.getReleaseNumber());
            DTObject randomBDT = getAPIFactory().getCoreComponentAPI().createRandomBDT(cdt, developerA, namespace, "WIP");
            dtForTesting.add(randomBDT);
        }

        HomePage homePage = loginPage().signIn(developerA.getLoginId(), developerA.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage = homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        for (DTObject dt : dtForTesting) {
            DTViewEditPage dtViewEditPage = viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(dt.getDen(), branch.getReleaseNumber());

            /**
             * Test Assertion #38.3.1.a
             */
            assertEquals("true", dtViewEditPage.getNamespaceField().getAttribute("aria-required"));
            ArrayList<NamespaceObject> standardNamespaces = getAPIFactory().getNamespaceAPI().getStandardNamespacesURIs();
            for (NamespaceObject namespace : standardNamespaces) {
                assertDoesNotThrow(() -> {
                    dtViewEditPage.setNamespace(namespace);
                });
            }
            escape(getDriver());

            dtViewEditPage.showValueDomain();
            assertDoesNotThrow(() -> dtViewEditPage.getValueDomainByTypeNameAndXSDExpression("Primitive", "NormalizedString", "normalized string"));
            assertDoesNotThrow(() -> dtViewEditPage.getValueDomainByTypeNameAndXSDExpression("Primitive", "String", "any URI, string"));

            assertDisabled(dtViewEditPage.getCheckboxForValueDomainByTypeAndName("Primitive", "NormalizedString"));
            dtViewEditPage.addCodeListValueDomain(codeList.getName());
            dtViewEditPage.selectValueDomain(codeList.getName());
            dtViewEditPage.discardValueDomain();

            /**
             * Test Assertion #38.3.1.b
             */
            dtViewEditPage.setQualifier("newQualifier, " + dt.getQualifier());

            /**
             * Test Assertion #38.3.1.c
             */
            assertEquals("false", dtViewEditPage.getSixHexadecimalIdentifierField().getAttribute("aria-required"));

            /**
             * Test Assertion #38.3.1.d
             */
            assertEquals("false", dtViewEditPage.getContentComponentDefinitionField().getAttribute("aria-required"));
            assertEquals("false", dtViewEditPage.getDefinitionField().getAttribute("aria-required"));
            assertEquals("false", dtViewEditPage.getDefinitionSourceField().getAttribute("aria-required"));
            dtViewEditPage.setDefinition("");
            click(dtViewEditPage.getUpdateButton(true));
            assertEquals("Are you sure you want to update this without definitions?",
                    dtViewEditPage.getDefinitionWarningDialogMessage());
            dtViewEditPage.hitUpdateAnywayButton();
        }
    }

    @Test
    @DisplayName("TC_38_3_TA_2_and_TA_3")
    public void test_TA_2_and_TA_3() {
        AppUserObject developerA;
        ReleaseObject branch;
        ArrayList<DTObject> dtForTesting = new ArrayList<>();
        DTObject baseCDT;
        {
            developerA = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(false);
            thisAccountWillBeDeletedAfterTests(developerA);

            branch = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber("Working");
            NamespaceObject namespace = getAPIFactory().getNamespaceAPI().getNamespaceByURI("http://www.openapplications.org/oagis/10");

            baseCDT = getAPIFactory().getCoreComponentAPI().getCDTByDENAndReleaseNum("Code. Type", branch.getReleaseNumber());
            DTObject randomBDT = getAPIFactory().getCoreComponentAPI().createRandomBDT(baseCDT, developerA, namespace, "WIP");
            dtForTesting.add(randomBDT);
        }

        HomePage homePage = loginPage().signIn(developerA.getLoginId(), developerA.getPassword());
        ViewEditCoreComponentPage viewEditCoreComponentPage = homePage.getCoreComponentMenu().openViewEditCoreComponentSubMenu();
        for (DTObject dt : dtForTesting) {
            DTViewEditPage dtViewEditPage = viewEditCoreComponentPage.openDTViewEditPageByDenAndBranch(dt.getDen(), branch.getReleaseNumber());
            assertDoesNotThrow(() -> dtViewEditPage.addSupplementaryComponent("/" + dt.getDen()));
            List<DTSCObject> supplementaryComponentsFromTheBaseDT = getAPIFactory().getCoreComponentAPI().getSupplementaryComponentsForDT(baseCDT.getDtId(), branch.getReleaseNumber());
            for (DTSCObject dtSC: supplementaryComponentsFromTheBaseDT){
                String dtSCName = dtSC.getObjectClassTerm() + ". " + dtSC.getPropertyTerm() + ". " +dtSC.getRepresentationTerm();
                assertDoesNotThrow(() -> dtViewEditPage.goToNode("/" + dt.getDen() + "/" + dtSCName));
            }
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
