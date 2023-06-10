package homework;

public class TestClass {
    @Test.BeforeSuite
    public static void beforeSuite() {
        System.out.println("BeforeSuite method");
    }

    @Test(priority = 3)
    public static void testMethod1() {
        System.out.println("TestMethod1");
    }

    @Test(priority = 1)
    public static void testMethod2() {
        System.out.println("TestMethod2");
    }

    @Test(priority = 5)
    public static void testMethod3() {
        System.out.println("TestMethod3");
    }

    @Test.AfterSuite
    public static void afterSuite() {
        System.out.println("AfterSuite method");
    }
}

