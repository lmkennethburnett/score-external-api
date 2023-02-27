package org.oagi.score.e2e.TS_10_WorkingBranchCoreComponentManagementBehaviorsForDeveloper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.oagi.score.e2e.BaseTest;
import org.oagi.score.e2e.api.CoreComponentAPI;
import org.oagi.score.e2e.menu.CoreComponentMenu;
import org.oagi.score.e2e.obj.*;
import org.oagi.score.e2e.page.HomePage;
import org.oagi.score.e2e.page.core_component.ViewEditCoreComponentPage;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Execution(ExecutionMode.CONCURRENT)
public class TC_10_1_Core_Component_Access extends BaseTest {
    private final List<AppUserObject> randomAccounts = new ArrayList<>();

    @BeforeEach
    public void init() {
        super.init();

    }

    private void thisAccountWillBeDeletedAfterTests(AppUserObject appUser) {
        this.randomAccounts.add(appUser);
    }

    private class RandomCoreComponentContainer {
        private AppUserObject appUser;
        private int yieldPointer = 0;
        int numberOfWIPCCs;
        int numberOfDraftCCs;
        int numberOfCandidateCCs;

        private List<ACCObject> arrayWIPACCs = new ArrayList<>();
        private List<ASCCPObject> arrayWIPASCCPs = new ArrayList<>();
        private List<BCCPObject> arrayWIPBCCPs = new ArrayList<>();
        private List<ACCObject> arrayDraftACCs = new ArrayList<>();
        private List<ASCCPObject> arrayDraftASCCPs = new ArrayList<>();
        private List<BCCPObject> arrayDraftBCCPs = new ArrayList<>();

        private List<ACCObject> arrayCandidateACCs = new ArrayList<>();
        private List<ASCCPObject> arrayCandidateASCCPs = new ArrayList<>();
        private List<BCCPObject> arrayCandidateBCCPs = new ArrayList<>();
        public RandomCoreComponentContainer(AppUserObject appUser, ReleaseObject release,NamespaceObject namespace) {
            this(appUser, release, namespace, nextInt(1, 3), nextInt(1, 3), nextInt(1, 3));
        }

        public RandomCoreComponentContainer(AppUserObject appUser, ReleaseObject release, NamespaceObject namespace,
                                           int numberOfWIPCCs, int numberOfDraftCCs, int numberOfCandidateCCs) {
            this.appUser = appUser;
            this.numberOfWIPCCs = numberOfWIPCCs;
            this.numberOfDraftCCs = numberOfDraftCCs;
            this.numberOfCandidateCCs = numberOfCandidateCCs;

            AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(true);
            thisAccountWillBeDeletedAfterTests(developer);

            for (int i = 0; i < numberOfWIPCCs; ++i) {
                ASCCPObject asccp;
                BCCPObject bccp;
                ACCObject acc;

                {
                    CoreComponentAPI coreComponentAPI = getAPIFactory().getCoreComponentAPI();

                    acc = coreComponentAPI.createRandomACC(developer, release, namespace, "WIP");
                    DTObject dataType = coreComponentAPI.getBDTByGuidAndReleaseNum("dd0c8f86b160428da3a82d2866a5b48d", release.getReleaseNumber());
                    bccp = coreComponentAPI.createRandomBCCP(dataType, developer, namespace, "WIP");
                    BCCObject bcc = coreComponentAPI.appendBCC(acc, bccp, "WIP");
                    bcc.setCardinalityMax(1);
                    coreComponentAPI.updateBCC(bcc);

                    ACCObject acc_association = coreComponentAPI.createRandomACC(developer, release, namespace, "WIP");
                    BCCPObject bccp_to_append = coreComponentAPI.createRandomBCCP(dataType, developer, namespace, "WIP");
                    coreComponentAPI.appendBCC(acc_association, bccp_to_append, "WIP");

                    asccp = coreComponentAPI.createRandomASCCP(acc_association, developer, namespace, "WIP");
                    ASCCObject ascc = coreComponentAPI.appendASCC(acc, asccp, "WIP");
                    ascc.setCardinalityMax(1);
                    coreComponentAPI.updateASCC(ascc);
                    arrayWIPACCs.add(acc);
                    arrayWIPASCCPs.add(asccp);
                    arrayWIPBCCPs.add(bccp);
                }
            }
            for (int i = 0; i < numberOfDraftCCs; ++i) {
                ASCCPObject asccp;
                BCCPObject bccp;
                ACCObject acc;

                {
                    CoreComponentAPI coreComponentAPI = getAPIFactory().getCoreComponentAPI();

                    acc = coreComponentAPI.createRandomACC(developer, release, namespace, "Draft");
                    DTObject dataType = coreComponentAPI.getBDTByGuidAndReleaseNum("dd0c8f86b160428da3a82d2866a5b48d", release.getReleaseNumber());
                    bccp = coreComponentAPI.createRandomBCCP(dataType, developer, namespace, "Draft");
                    BCCObject bcc = coreComponentAPI.appendBCC(acc, bccp, "Draft");
                    bcc.setCardinalityMax(1);
                    coreComponentAPI.updateBCC(bcc);

                    ACCObject acc_association = coreComponentAPI.createRandomACC(developer, release, namespace, "Draft");
                    BCCPObject bccp_to_append = coreComponentAPI.createRandomBCCP(dataType, developer, namespace, "Draft");
                    coreComponentAPI.appendBCC(acc_association, bccp_to_append, "Draft");

                    asccp = coreComponentAPI.createRandomASCCP(acc_association, developer, namespace, "Draft");
                    ASCCObject ascc = coreComponentAPI.appendASCC(acc, asccp, "Draft");
                    ascc.setCardinalityMax(1);
                    coreComponentAPI.updateASCC(ascc);
                    arrayDraftACCs.add(acc);
                    arrayDraftASCCPs.add(asccp);
                    arrayDraftBCCPs.add(bccp);
                }

            }
            for (int i = 0; i < numberOfCandidateCCs; ++i) {
                ASCCPObject asccp;
                BCCPObject bccp;
                ACCObject acc;

                {
                    CoreComponentAPI coreComponentAPI = getAPIFactory().getCoreComponentAPI();

                    acc = coreComponentAPI.createRandomACC(developer, release, namespace, "Candidate");
                    DTObject dataType = coreComponentAPI.getBDTByGuidAndReleaseNum("dd0c8f86b160428da3a82d2866a5b48d", release.getReleaseNumber());
                    bccp = coreComponentAPI.createRandomBCCP(dataType, developer, namespace, "Candidate");
                    BCCObject bcc = coreComponentAPI.appendBCC(acc, bccp, "Candidate");
                    bcc.setCardinalityMax(1);
                    coreComponentAPI.updateBCC(bcc);

                    ACCObject acc_association = coreComponentAPI.createRandomACC(developer, release, namespace, "Candidate");
                    BCCPObject bccp_to_append = coreComponentAPI.createRandomBCCP(dataType, developer, namespace, "Candidate");
                    coreComponentAPI.appendBCC(acc_association, bccp_to_append, "Candidate");

                    asccp = coreComponentAPI.createRandomASCCP(acc_association, developer, namespace, "Candidate");
                    ASCCObject ascc = coreComponentAPI.appendASCC(acc, asccp, "Candidate");
                    ascc.setCardinalityMax(1);
                    coreComponentAPI.updateASCC(ascc);
                    arrayCandidateACCs.add(acc);
                    arrayCandidateASCCPs.add(asccp);
                    arrayCandidateBCCPs.add(bccp);
                }

            }
        }

    }

    @Test
    @DisplayName("TC_10_1_TA_1")
    public void test_TA_1(){
        AppUserObject developer = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(true);
        thisAccountWillBeDeletedAfterTests(developer);

        ReleaseObject release = getAPIFactory().getReleaseAPI().getReleaseByReleaseNumber("Working");
        NamespaceObject namespace = getAPIFactory().getNamespaceAPI().getNamespaceByURI("http://www.openapplications.org/oagis/10");

        RandomCoreComponentContainer randomCoreComponentContainer = new RandomCoreComponentContainer(developer, release, namespace);

        AppUserObject developer2 = getAPIFactory().getAppUserAPI().createRandomDeveloperAccount(true);
        thisAccountWillBeDeletedAfterTests(developer2);

        HomePage homePage = loginPage().signIn(developer2.getLoginId(), developer2.getPassword());
        CoreComponentMenu coreComponentMenu = homePage.getCoreComponentMenu();
        ViewEditCoreComponentPage viewEditCoreComponentPage = coreComponentMenu.openViewEditCoreComponentSubMenu();
        int numOfWIPACCs = randomCoreComponentContainer.arrayWIPACCs.size();
        for(int i=0; i < numOfWIPACCs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayWIPACCs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }
        int numOfWIPASCCPs = randomCoreComponentContainer.arrayWIPASCCPs.size();
        for(int i=0; i < numOfWIPASCCPs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayWIPASCCPs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }

        int numOfWIPBCCPs = randomCoreComponentContainer.arrayWIPBCCPs.size();
        for(int i=0; i < numOfWIPBCCPs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayWIPBCCPs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }

        int numOfDraftACCs = randomCoreComponentContainer.arrayDraftACCs.size();
        for(int i=0; i < numOfDraftACCs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayDraftACCs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }

        int numOfDraftASCCPs = randomCoreComponentContainer.arrayDraftASCCPs.size();
        for(int i=0; i < numOfDraftASCCPs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayDraftASCCPs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }

        int numOfDraftBCCPs = randomCoreComponentContainer.arrayDraftBCCPs.size();
        for(int i=0; i < numOfDraftBCCPs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayDraftBCCPs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }

        int numOfCandidateACCs = randomCoreComponentContainer.arrayCandidateACCs.size();
        for(int i=0; i < numOfCandidateACCs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayCandidateACCs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }
        int numOfCandidateASCCPs = randomCoreComponentContainer.arrayCandidateASCCPs.size();
        for(int i=0; i < numOfCandidateASCCPs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayCandidateASCCPs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }
        int numOfCandidateBCCPs = randomCoreComponentContainer.arrayCandidateBCCPs.size();
        for(int i=0; i < numOfCandidateBCCPs; i++){
            viewEditCoreComponentPage.openPage();
            viewEditCoreComponentPage.setDEN(randomCoreComponentContainer.arrayCandidateBCCPs.get(i).getDen());
            assertTrue(viewEditCoreComponentPage.getTableRecordAtIndex(1).isDisplayed());
        }
    }

    @Test
    @DisplayName("TC_10_1_TA_2")
    public void test_TA_2(){

    }

    @Test
    @DisplayName("TC_10_1_TA_3")
    public void test_TA_3(){

    }

    @Test
    @DisplayName("TC_10_1_TA_4")
    public void test_TA_4(){

    }

    @Test
    @DisplayName("TC_10_1_TA_5")
    public void test_TA_5(){

    }

    @Test
    @DisplayName("TC_10_1_TA_6")
    public void test_TA_6(){

    }

    @Test
    @DisplayName("TC_10_1_TA_7")
    public void test_TA_7(){

    }

    @Test
    @DisplayName("TC_10_1_TA_8")
    public void test_TA_8(){

    }

    @Test
    @DisplayName("TC_10_1_TA_9")
    public void test_TA_9(){

    }

    @Test
    @DisplayName("TC_10_1_TA_10")
    public void test_TA_10(){

    }
    @Test
    @DisplayName("TC_10_1_TA_11")
    public void test_TA_11(){

    }

    @Test
    @DisplayName("TC_10_1_TA_12")
    public void test_TA_12(){

    }

    @Test
    @DisplayName("TC_10_1_TA_13")
    public void test_TA_13(){

    }
    @Test
    @DisplayName("TC_10_1_TA_14")
    public void test_TA_14(){

    }

    @Test
    @DisplayName("TC_10_1_TA_15")
    public void test_TA_15(){

    }

    @Test
    @DisplayName("TC_10_1_TA_16")
    public void test_TA_16(){

    }

    @Test
    @DisplayName("TC_10_1_TA_17")
    public void test_TA_17(){

    }

    @Test
    @DisplayName("TC_10_1_TA_18")
    public void test_TA_18(){

    }

    @Test
    @DisplayName("TC_10_1_TA_19")
    public void test_TA_19(){

    }

    @Test
    @DisplayName("TC_10_1_TA_20")
    public void test_TA_20(){

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
