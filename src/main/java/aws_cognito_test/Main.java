package aws_cognito_test;

import com.amazonaws.services.cognitoidentity.model.Credentials;
import org.json.JSONObject;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        {
            out.println("Starting test...");
            CognitoHelper helper = new CognitoHelper();
            String result = helper.ValidateUser("your.user@email.com", "password2");
            JSONObject payload = CognitoJWTParser.getPayload(result);

            String provider = payload.get("iss").toString().replace("https://", "");

            Credentials credentails = helper.GetCredentials(provider, result);

            out.println("Here are your credentials: " + credentails.toString());
        }
    }
}
