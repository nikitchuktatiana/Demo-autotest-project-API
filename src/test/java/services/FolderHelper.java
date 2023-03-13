package services;

import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;

import static services.responces.ResponceSpec.*;

@UtilityClass
public final class FolderHelper extends BaseTestClass {
    private static RequestSpecification specificationreq;

    public static void CreateFolder(String path) {
        RequestAction.methodExucute(Method.PUT, BaseController.RESOURCES, specificationreq, specRespCreateOk201());
    }

    public static void DeleteFolder(String path) {
        RequestAction.methodExucute(Method.DELETE, BaseController.RESOURCES, specificationreq, specRespSimpleDel204());
    }

    public static void CheckExistFolder(String path) {
        specificationreq = specReg();
        specificationreq.queryParam("path", path);
        int folderExistStatus = RequestAction.methodExucute(Method.PUT, BaseController.RESOURCES, specificationreq, specRespAnyCode(409, 201)).extract().statusCode();
        if (folderExistStatus == 409) {
            DeleteFolder(path);
            CreateFolder(path);
            System.out.println("Было изменено тестовое окружение. Чтобы тесты не сломались, папка" + " " + "path" + " " + "была удалена и создана пустой");
        }
    }
}

