package agave;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);

        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error when creating file: " + e.getMessage());
        }
    }

    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        BufferedWriter bufferedWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                bufferedWriter.write(task.toWriteFormat());
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error when writing to file: " + e.getMessage());
        } finally {
            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
        }
    }

    public ArrayList<Task> loadTasks() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                Task task = Task.parseTask(line);
                tasks.add(task);
                line = bufferedReader.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error when reading from file: " + e.getMessage());
        }
        return tasks;
    }
}
