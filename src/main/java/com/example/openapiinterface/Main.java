package com.example.openapiinterface;

import com.example.openapiinterface.client.OpenAPIClient;
import com.example.openapiinterface.model.User;

/**
 * @author zuodong
 * @create 2023-08-05 21:48
 */

public class Main {
    public static void main(String[] args) {

        String accesskey = "yupi";
        String secretKey = "abcdefgh";

        OpenAPIClient openAPIClient = new OpenAPIClient(accesskey, secretKey);
        String result1 = openAPIClient.getNameByGet("鱼皮");
        String  result2 = openAPIClient.getNameByPost("鱼皮");
        User user = new User();
        user.setUsername("Glucacxe");
        String result3 = openAPIClient.getUsernameByPost(user);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
