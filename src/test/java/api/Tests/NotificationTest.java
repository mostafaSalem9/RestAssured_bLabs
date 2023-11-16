package api.Tests;

import api.endpoints.Base;
import api.endpoints.Routes;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class NotificationTest extends Base {
    Base base;
    String sendNotificationBody = "{\n" +
            "  \"channel\": \"sms\",\n" +
            "  \"priority\": 2,\n" +
            "  \"recipient_list\": \"01594876029\",\n" +
            "  \"template_id\": \"afafc4d9-8e19-4870-8480-d9bde6bff4bc\",\n" +
            "  \"template_parameters\": {\n" +
            "    \"name\":\"Mostafa\"\n" +
            "  }\n" +
            "}";

    @BeforeClass(description = "Prepare data for testing")
    public void setupData() throws IOException {
        base = new Base();
    }

    @Test
    public void sendNotification() {
       Response response =  base.postRequest(Routes.notificationEndpoint, sendNotificationBody);
        Assert.assertEquals(response.statusCode(),201);
    }

}
