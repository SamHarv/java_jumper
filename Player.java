public class Player {

    private String userName;

    public Player() {
        this.userName = "Unknown";
    }

    public Player(String userName) {
        this.userName = userName;
    }

    public void acceptUserName() {
        Validation validate = new Validation();
        Input input = new Input();
        try {
            String name = input.acceptStringInput("Please enter your name (3 - 12 characters only):");
            while (validate.isBlank(name) || !(validate.lengthWithinRange(name, 3, 12))) {
                name = input.acceptStringInput("Name must be between 3 and 12 characters! Please enter your name:");
            }
            setUserName(name);
        } catch (Exception e) {
            System.out.println("Invalid input! " + e);
            acceptUserName();
        }

        System.out.println("\nWelcome, " + getUserName() + "\n");
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}