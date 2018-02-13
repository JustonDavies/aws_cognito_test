package aws_cognito_test;

import static java.lang.System.out;

public class Main {

    public static void main(String[] args) {
        {
            out.println("beans");
            CognitoHelper test = new CognitoHelper();
            String result = test.ValidateUser("youruser@email.com", "password");
            out.println(result);
        }
    }
}
