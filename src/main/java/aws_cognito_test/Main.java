package aws_cognito_test;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.cognitoidentity.model.Credentials;
import org.json.JSONObject;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        {
            out.println("Starting test...");
            CognitoHelper helper = new CognitoHelper();
            String result = helper.ValidateUser("YOURUSER", "PASSWORD");
            JSONObject payload = CognitoJWTParser.getPayload(result);

            String provider = payload.get("iss").toString().replace("https://", "");

            Credentials credentials = helper.GetCredentials(provider, result);
            BasicSessionCredentials basic_credntials = new BasicSessionCredentials(credentials.getAccssKeyId(), credentials.getSecretKey(), credentials.getSessionToken());
            AWSStaticCredentialsProvider static_credentials_provider = new AWSStaticCredentialsProvider(awsCreds);

            //BLUCognitoSdk client = BLUCognitoSdk.builder()
            //.iamCredentials(new AWSStaticCredentialsProvider(new BasicSessionCredentials(credentials.getAccessKeyId(), credentials.getSecretKey(), credentials.getSessionToken())))
            //.build();

            out.println("Here are your credentials: ");
            out.println("\tAccess key: " + credentials.getAccessKeyId());
            out.println("\tSecret key: " + credentials.getSecretKey());
            out.println("\tSession token: " + credentials.getSessionToken());
        }
    }
}
