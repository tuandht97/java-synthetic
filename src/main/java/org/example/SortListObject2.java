package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortListObject2 {

    // Assume we have a class called Task with fields name, type and status
    static class Task {
        String name;
        String type;
        String status;

        public Task(String name, String type, String status) {
            this.name = name;
            this.type = type;
            this.status = status;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static void main(String[] args) {
        // Create a list of tasks with different names, types and statuses
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Write report", "Writing", "Done"));
        tasks.add(new Task("Fix bug", "Coding", "In progress"));
        tasks.add(new Task("Design logo", "Designing", "Pending"));
        tasks.add(new Task("Test feature", "Testing", "Done"));
        tasks.add(new Task("Refactor code", "Coding", "Pending"));
        tasks.add(new Task("Edit video", "Editing", "In progress"));

        // Use the stream method on the list to create a stream of tasks
        // Use the collect method on the stream to collect the tasks into a map that maps each status to a list of tasks of that status, using the groupingBy collector
        Map<String, List<Task>> map = tasks.stream()
                .collect(Collectors.groupingBy(Task::getStatus));

        // Use the entrySet method on the map to create a set of entries that contain the status and the list of tasks
        // Use the stream method on the set to create a stream of entries
        // Use the sorted method on the stream to sort the entries by status using a comparator
        // Use the map method on the stream to transform each entry into a list of tasks
        // Use the flatMap method on the stream to flatten the lists of tasks into a single stream of tasks
        // Use the sorted method on the stream to sort the tasks by type and then by name using a comparator
        // Use the collect method on the stream to collect the tasks into a list
        List<Task> sortedTasks = map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Task::getType).thenComparing(Task::getName))
                .collect(Collectors.toList());

        // Print the list of tasks
        System.out.println(sortedTasks);

    // Output:
    // [Fix bug, Refactor code, Design logo, Edit video, Test feature, Write report]
    }
}
