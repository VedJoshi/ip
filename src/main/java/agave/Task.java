package agave;

import java.util.ArrayList;

public class Task {

    private String description;
    private ArrayList<Task> tasks;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return isDone ? "[X] " : "[ ] ";
    }

    public void markAsDone() {
        isDone = true;
    }

    public void unmarkAsDone() {
        isDone = false;
    }

    public String toWriteFormat() {
        return String.format("%s | %d | %s",
                this instanceof ToDo ? "T" : this instanceof Deadline ? "D" : "E",
                isDone ? 1 : 0,
                this.getCorrectFormat()
        );
    }

    public static Task parseTask(String data) {
        String[] split = data.split(" \\| ");
        Task task = split[0].equals("T")
                ? new ToDo(split[2])
                : split[0].equals("D")
                ? new Deadline(split[2], split[3]) // 2 is description, 3 is by
                : new Event(split[2], split[3], split[4]); // 2 is description, 3 is from, 4 is to
        if (split[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    public String getCorrectFormat() {
        return description;
    }

    @Override
    public String toString() {
        return getStatus() + " " + getDescription();
    }
}