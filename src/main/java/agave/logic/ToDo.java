package agave.logic;

import agave.task.Task;

/**
 * Represents a task that can be added to the task list.
 */
public class ToDo extends Task {

    public ToDo (String description) {
        super(description);
    }

    @Override
    public String toString() {
        return " [T] " + super.toString();
    }
}
