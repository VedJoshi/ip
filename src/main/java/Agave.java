public class Agave {

    private UserInterface ui;
    private boolean isRunning;
    private Task taskManager;

    public Agave() {
        this.ui = new UserInterface();
        this.isRunning = true;
        this.taskManager = new Task();
    }

    public void run() {
        ui.showWelcome();

        while (isRunning) {
            String userInput = ui.getUserInput("Enter a command: ");
            System.out.println(userInput);
            if (userInput.equalsIgnoreCase("bye")) {
                isRunning = false;
                ui.showBye();
            } else if (userInput.equalsIgnoreCase("list")) {
                ui.showTasks(taskManager.getTasks(), taskManager.getTaskCount());
            } else {
                taskManager.addTask(new Task(userInput));
                ui.showEcho(userInput);
            }
        }
    }

    public static void main(String[] args) {
        Agave agave = new Agave();
        agave.run();
    }
}
