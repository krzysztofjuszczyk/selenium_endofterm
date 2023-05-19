public class Constants_For_Tests {

    //password requirements
    public static final String regEx = "[$&+,:;=?@#|'<>.^*()%!-]"; // list of special chars in a form of regex
    public static final int minPassLength = 6;
    public static final int maxPassLength = 20;

    //constant for generating fake users & passwords
    public static final int incorectPasswordsQty = 12; // will be increased to divisible by 4
    public static final int usersQty = 10;  // how many fake users do we generate
    public static final int minGeneratedPassLength = 10;
    public static final int maxGeneratedPassLength = 20;
}
