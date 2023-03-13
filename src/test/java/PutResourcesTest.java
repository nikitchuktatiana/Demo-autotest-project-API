import io.qameta.allure.Step;
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

public class PutResourcesTest extends BaseTestClass {
    private static RequestSpecification specificationreq;

    @Step("Создание папки для тестового окружения")
    @BeforeEach
    public void CheckSpecReg() {
        specificationreq = specReg();
    }

    @BeforeAll
    public static void CheckPutFirstFolderTestBeforeAll() {
        FolderHelper.CheckExistFolder("/new7");
    }

    @Test
    public void CheckPutFolderTest() {
        FolderHelper.CheckExistFolder("/new8");
    }

    @Test
    public void CheckPutFOlderWhithAttrib() {
        specificationreq.queryParam("path", "/new9").queryParam("fields", "123");
        RequestAction.methodExucute(Method.PUT, BaseController.RESOURCES, specificationreq, specRespCreateOk201());
    }

    @Test
    public void FolderAlreadyExistsTest() {
        specificationreq.queryParam("path", "/new7");
        RequestAction.methodExucute(Method.PUT, RESOURCES, specificationreq, specRespErrorCodeAndMess(409, "По указанному пути \"/new7\" уже существует папка с таким именем."));
    }

    @Test
    public void IncorrectFolderPathTest() {
        specificationreq.queryParam("path", "\"/new10");
        RequestAction.methodExucute(Method.PUT, RESOURCES, specificationreq, specRespErrorCodeAndMess(409, "Указанного пути \"\"/new10\" не существует."));
    }

    @Test
    public void CreateFolderWithoutTokenTest() {
        specRegNoToken().queryParam("path", "/new10");
        RequestAction.methodExucute(Method.PUT, RESOURCES, specRegNoToken(), specRespErrorCodeAndMess(401, "Не авторизован."));
    }

    @Test
    public void CreateFolderWhithIncirrectTokenTest() {
        specRegIncorrectToken().queryParam("path", "/new10");
        RequestAction.methodExucute(Method.PUT, RESOURCES, specRegIncorrectToken(), specRespErrorCodeAndMess(401, "Не авторизован."));
    }

}
