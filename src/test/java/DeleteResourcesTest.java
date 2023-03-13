import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.BaseController;
import services.BaseTestClass;
import services.FolderHelper;
import services.RequestAction;

import static services.BaseController.RESOURCES;
import static services.responces.ResponceSpec.*;

@Epic("v1/disk/resources : Files and folders")
public class DeleteResourcesTest extends BaseTestClass {
    private static RequestSpecification specificationreq;

    @BeforeEach
    public void SpecRegBeforeTest() {
        specificationreq = specReg();
    }

    @BeforeAll
    public static void CheckPutFirstFolderTestBeforeAll() {
        FolderHelper.CheckExistFolder("/new11");
        FolderHelper.CheckExistFolder("/new12");
        FolderHelper.CheckExistFolder("/new14");
        FolderHelper.DeleteFolder("/new14");
    }

    @Test
    @Feature("Folder deletion")
    @Story("Success deletion folder")
    public void DeletingAnExistingEmptyFolderTest() {
        specificationreq.queryParam("path", "/new11");
        RequestAction.methodExucute(Method.DELETE, BaseController.RESOURCES, specificationreq, specRespSimpleDel204());
    }

    @Test
    public void DeletingNotExistingFolderTest() {
        specificationreq.queryParam("path", "/new14");
        RequestAction.methodExucute(Method.DELETE, RESOURCES, specificationreq, specRespErrorCodeAndMess(404, "Не удалось найти запрошенный ресурс."));
    }

    @Test
    public void DeletingFolderWithoutTokenTest() {
        specRegNoToken().queryParam("path", "/new12");
        RequestAction.methodExucute(Method.DELETE, RESOURCES, specRegNoToken(), specRespErrorCodeAndMess(401, "Не авторизован."));
    }

    @Test
    public void DeletingFolderWhithIncirrectTokenTest() {
        specRegIncorrectToken().queryParam("path", "/new12");
        RequestAction.methodExucute(Method.DELETE, RESOURCES, specRegIncorrectToken(), specRespErrorCodeAndMess(401, "Не авторизован."));
    }

    @Test
    public void DeleteFolderNotInRecyclebinTest() {
        specificationreq.queryParam("path", "/new12").queryParam("permanently", "true");
        RequestAction.methodExucute(Method.DELETE, RESOURCES, specificationreq, specRespSimpleDel204());
    }
}
