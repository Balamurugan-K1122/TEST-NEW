public class ExampleIssues {
    // Hardcoded sensitive information
    private String password = "12345"; // CodeQL will flag hardcoded passwords as insecure

    public void printMessage(String message) {
        System.out.println("Message: " + message); // This can cause a NullPointerException
    }

    public void processNumbers(int[] numbers) {
        int unusedVariable = 42; // Unused variable (code smell)
        for (int i = 0; i <= numbers.length; i++) { // Array index out of bounds
            System.out.println("Number: " + numbers[i]);
        }
    }

    public void readFile(String fileName) {
        java.io.InputStream fileStream = null;
        try {
            fileStream = new java.io.FileInputStream(fileName);
            System.out.println("Processing file...");
        } catch (java.io.IOException e) {
            System.out.println("Error reading file.");
        }
        // Missing finally block to close fileStream, causing a resource leak
    }

    public void getUser(String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "'"; // SQL Injection vulnerability
        System.out.println("Executing query: " + query);
        // Imagine this query being run against a database without parameterized statements
    }

    public void checkCondition(int value) {
        if (value < 0) {
            System.out.println("Value is negative.");
            return;
        }
        System.out.println("Value is positive or zero.");
        if (value < 0) { // Redundant condition, dead code
            System.out.println("This code is unreachable.");
        }
    }

    public int factorial(int n) {
        return n * factorial(n); // Infinite recursion, should be factorial(n - 1)
    }

    public static void main(String[] args) {
        ExampleIssues example = new ExampleIssues();
        example.printMessage(null); // Null pointer dereference
        example.processNumbers(new int[]{1, 2, 3}); // Array index out of bounds
        example.readFile("example.txt"); // Resource leak
        example.getUser("admin' OR '1'='1"); // SQL injection
        example.checkCondition(5); // Dead code
        System.out.println("Factorial of 5: " + example.factorial(5)); // Infinite recursion
    }
}
