public class ExampleIssues {

     Hardcoded sensitive information
    private String password = 12345;  CodeQL will flag hardcoded passwords as insecure

     Null pointer dereference
    public void printMessage(String message) {
        if (message == null) {
            System.out.println(Message is null, but let's try to print it anyway);
        }
        System.out.println(message.length());  This can cause a NullPointerException
    }

     Unused variable (code smell)
    public void processNumbers(int[] numbers) {
        int unusedVariable = 42;  Unused variable

        for (int i = 0; i = numbers.length; i++) {  Array index out of bounds
            System.out.println(Number  + numbers[i]);
        }
    }

     Resource leak
    public void readFile(String filename) {
        java.io.FileInputStream fileStream = null;
        try {
            fileStream = new java.io.FileInputStream(filename);
             Process file...
        } catch (java.io.IOException e) {
            System.out.println(Error reading file.);
        }
         Missing finally block to close fileStream, which causes a resource leak
    }

     SQL Injection vulnerability
    public void getUser(String username) {
        String query = SELECT  FROM users WHERE username = ' + username + ';
        System.out.println(Executing query  + query);
         Imagine this query being run against a database without parameterized statements
    }

     Dead code (code that never executes)
    public void checkCondition(int value) {
        if (value  0) {
            return;
        }
        System.out.println(Value is positive or zero.);
        if (value  0) {  This condition is redundant and will never be true
            System.out.println(This code is unreachable.);
        }
    }

     Infinite recursion
    public int factorial(int n) {
        if (n = 1) {
            return 1;
        }
        return n  factorial(n);  Should be factorial(n - 1) to avoid infinite recursion
    }

    public static void main(String[] args) {
        ExampleIssues example = new ExampleIssues();
        
         Example method calls
        example.printMessage(null);  Null pointer dereference
        example.processNumbers(new int[]{1, 2, 3});  Array index out of bounds
        example.readFile(example.txt);  Resource leak
        example.getUser(admin' OR '1'='1);  SQL injection
        example.checkCondition(5);  Dead code
        System.out.println(Factorial of 5  + example.factorial(5));  Infinite recursion
    }
}
