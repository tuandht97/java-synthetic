package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SortListObject {

    // Assume we have a class called Animal with fields name and type
    static class Animal {
        String name;
        String type;

        public Animal(String name, String type) {
            this.name = name;
            this.type = type;
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
    }

    public static void main(String[] args) {
        // Create a list of animals with different names and types
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Leo", "Lion"));
        animals.add(new Animal("Mia", "Cat"));
        animals.add(new Animal("Max", "Dog"));
        animals.add(new Animal("Zoe", "Zebra"));
        animals.add(new Animal("Luna", "Cat"));
        animals.add(new Animal("Rex", "Dog"));

        // Use the stream method on the list to create a stream of animals
        // Use the collect method on the stream to collect the animals into a map that maps each type to a list of animals of that type, using the groupingBy collector
        Map<String, List<Animal>> map = animals.stream()
                .collect(Collectors.groupingBy(Animal::getType));

        // Use the forEach method on the map to loop through each entry and sort the list of animals by name using the sorted method on the stream
        map.forEach((type,list)->{
            // Sort the list by name using the sorted method on the stream
            List<Animal> sortedList=list.stream()
                    .sorted(Comparator.comparing(Animal::getName))
                    .collect(Collectors.toList());
            // Print the type and the sorted list
            System.out.println(type+": "+sortedList);
        });

        // Output:
        // Lion: [Leo]
        // Cat: [Luna, Mia]
        // Dog: [Max, Rex]
        // Zebra: [Zoe]
    }
}
