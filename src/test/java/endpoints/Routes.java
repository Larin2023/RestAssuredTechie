package endpoints;


/////////////  Gather all required URLS from Petstore API


/*
Swagger URI --> https://petstore.swagger.io

Create user(Post) : https://petstore.swagger.io/v2/user
Get user (Get): https://petstore.swagger.io/v2/user/{username}
Update user (Put) : https://petstore.swagger.io/v2/user/{username}
Delete user (Delete) : https://petstore.swagger.io/v2/user/{username}

*/

public class Routes {

    public static String base_url="https://petstore.swagger.io/v2" ;            // this is the base URL I have defined

    //User module

    public static String post_url=base_url+"/user";                 // Store POST url in variable
    public static String get_url=base_url+"/user/{username}";           // {username} is linked to UserEndPoints---> readUser
    public static String update_url=base_url+"/user/{username}";
    public static String delete_url=base_url+"/user/{username}";

    //Store module

    //Here you will create Store module URL's

    //Pet module
    //Here you will create Pet module URL's


}
