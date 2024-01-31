package api.Tests;

import api.endpoints.Base;
import api.endpoints.Routes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.Notifications.Notifications;
import payload.Notifications.TemplateParameters;

public class NotificationTest extends Base {
    Base base;

    public Notifications notificationsPayload(String channel, int priority, String recipient,
                                              String templateId, String name) {
        Notifications notificationsPayload = new Notifications();
        notificationsPayload.setChannel(channel);
        notificationsPayload.setPriority(priority);
        notificationsPayload.setRecipient_list(recipient);
        notificationsPayload.setTemplate_id(templateId);
        TemplateParameters templateParameters = new TemplateParameters();
        templateParameters.setName(name);
        return notificationsPayload;

    }

    @BeforeClass()
    public void setupData() {
        base = new Base();
    }

    @Test(priority = 1)
    public void sendNotificationWithValidRequest() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "01594876029", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 201);
        Assert.assertNotNull(response.body().jsonPath().getString("request_id"));

    }

    @Test(priority = 2)
    public void sendNotificationWithTemplateIdNotStoredInDataBase() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "01594876029", "afafc4d9-8e19-4870-8480-d9bde6bff4bb", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");
    }

    @Test(priority = 3)
    public void sendNotificationWithInvalidApiKey() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "01594876029", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ac");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 403);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Not authenticated");
    }

    @Test(priority = 4)
    public void sendNotificationWithoutApiKey() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "01594876029", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 403);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Not authenticated");
    }

    @Test(priority = 5)
    public void sendNotificationWithNullValuesInTheRequestBody() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, null, null, "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");
    }

    @Test(priority = 6)
    public void sendNotificationWithMissingRequiredFieldInTheRequestBody() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");
    }

    @Test(priority = 7)
    public void sendNotificationWithPriorityLessThan1() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 0, "01594876029", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");
    }

    @Test(priority = 8)
    public void sendNotificationWithPriorityMoreThan3() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 4, "01594876029", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");
    }

    @Test(priority = 9)
    public void sendNotificationWithRecipientsNumberMoreThan11() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "015948760291", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");

    }

    @Test(priority = 10)
    public void sendNotificationWithRecipientsNumberLessThan11() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "0159487602", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");

    }

    @Test(priority = 11)
    public void sendNotificationWithRecipientsNumberDoesNotStartWithEgyptianNumber() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "sms", 2, "0179487602", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");

    }

    @Test(priority = 12)
    public void sendNotificationWithChannelParamNotEqualSms() {
        Response response = base.postRequest(Routes.notificationEndpoint, notificationsPayload(
                        "inApp", 2, "0179487602", "afafc4d9-8e19-4870-8480-d9bde6bff4bc", "Mostafa"),
                "2e8f142c-4f0a-4b8a-a7b9-1234567890ab");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.statusCode(), 400);
        Assert.assertEquals(response.body().jsonPath().getString("detail"), "Invalid request data");

    }
}
