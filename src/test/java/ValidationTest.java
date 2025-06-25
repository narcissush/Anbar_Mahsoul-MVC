import anbar.controller.validation.UserValidation;

public class ValidationTest {
    public static void main(String[] args) {
boolean b;


        b= UserValidation.isValidName("نرگس");
        System.out.println("name : " + b);

        b= UserValidation.isValidFamilye("حاجی زاده");
        System.out.println("family : " + b);

        b= UserValidation.isValidNationalId("0080386822");
        System.out.println("NationalId : " + b);

        b= UserValidation.isValidUserName("nargesh.123");
        System.out.println("usename : " + b);

        b= UserValidation.isValidPassword("Narges123@");
        System.out.println("password : " + b);






    }

}
