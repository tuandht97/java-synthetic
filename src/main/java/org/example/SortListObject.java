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

     //   sortByName(animals);

        sortByTypeAndName(animals);
    }

    private static void sortByTypeAndName(List<Animal> animals) {
        // Use the stream method on the list to create a stream of animals
        // Use the collect method on the stream to collect the animals into a map that maps each type to a list of animals of that type, using the groupingBy collector
        Map<String, List<Animal>> map = animals.stream()
                .collect(Collectors.groupingBy(Animal::getType));

        // Use the entrySet method on the map to create a set of entries that contain the type and the list of animals
        // Use the stream method on the set to create a stream of entries
        // Use the sorted method on the stream to sort the entries by type using a comparator
        // Use the map method on the stream to transform each entry into a list of animals
        // Use the flatMap method on the stream to flatten the lists of animals into a single stream of animals
        // Use the sorted method on the stream to sort the animals by name using a comparator
        // Use the collect method on the stream to collect the animals into a list
        List<Animal> sortedAnimals = map.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(Map.Entry::getValue)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Animal::getName))
                .collect(Collectors.toList());

        // Print the list of animals
        System.out.println(sortedAnimals);
    }

    private static void sortByName(List<Animal> animals) {
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
    }
}
