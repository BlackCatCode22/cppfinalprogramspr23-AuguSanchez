// JavaFinalProgramDemo01.java
// dH 10 Apr 23
//
// Sample code for Java Spring '23 final program
//
// Notes: This is a good  starting place for your final program, but a unique ID must be generated which starts with a
// two-character representation of species, e.g. Hy01, Li02, Ti01. Names must be found for your newly created animal
// from the animalNames.txt file (you may add names to the list). You must also create birthDate and location. Note the
// hardcoded values for these just to get this demo working with two class files, Animal.class and
// JavaFinalProgramDemo01.class.
//
//
import java.io.*;
import java.util.*;

public class AnimalJavaFinal {
    public static int numOfAnimals = 0;
    public String id;
    public String species;
    public String name;
    public int age;
    public String birthDate;
    public String color;
    public String gender;
    public int weight;
    public String origin;
    public String arrivalDate;

    // Constructor
    public AnimalJavaFinal(String id, String species, String name, int age, String birthDate, String color, String gender, int weight, String origin, String arrivalDate) {
        this.id = id;
        this.species = species;
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.color = color;
        this.gender = gender;
        this.weight = weight;
        this.origin = origin;
        this.arrivalDate = arrivalDate;
        numOfAnimals++;
    }

    // Override the toString() method
    @Override
    public String toString() {
        return id + " " +
                species + " " +
                name + " " +
                age + " " +
                birthDate + " " +
                color + " " +
                gender + " " +
                weight + " " +
                origin + " " +
                arrivalDate;
    }

    // Generate a unique ID for the animal
    public static String generateId(String species, int numAnimals) {
        String prefix;
        switch (species) {
            case "hyena":
                prefix = "Hy";
                break;
            case "lion":
                prefix = "Li";
                break;
            case "tiger":
                prefix = "Ti";
                break;
            case "bear":
                prefix = "Be";
                break;
            default:
                prefix = "Un"; // for unknown species
                break;
        }
        return prefix + String.format("%02d", numAnimals + 1);
    }

    // Read animal names from a file
    public static List<String> readAnimalNames(String filename) throws FileNotFoundException {
        List<String> names = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                names.add(name);
            }
        }
        return names;
    }

    // Calculate birth date based on birth season
    public static String generateBirthDate(String birthSeason) {
        // Implement your logic to calculate birth date based on the birth season
        // For simplicity, let's assume the birth date is the current date
        Date currentDate = new Date();
        return currentDate.toString(); // Modify this line with the correct logic
    }

    public static void main(String[] args) {
        // Create linked lists for each habitat
        LinkedList<AnimalJavaFinal> hyenaHabitat = new LinkedList<>();
        LinkedList<AnimalJavaFinal> lionHabitat = new LinkedList<>();
        LinkedList<AnimalJavaFinal> tigerHabitat = new LinkedList<>();
        LinkedList<AnimalJavaFinal> bearHabitat = new LinkedList<>();

        try (Scanner scanner = new Scanner(new File("C:\\Users\\augus\\Desktop\\School\\CIT-63\\arrivingAnimals.txt"))) {
            int numAnimals = 0;
            List<String> animalNames = readAnimalNames("C:\\Users\\augus\\Desktop\\School\\CIT-63\\animalNames.txt");
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                String ageString = tokens[0].split(" ")[0].trim().replace("year", "").replace("old", "");
                int age = Integer.parseInt(ageString);

                String gender = tokens[0].split(" ")[1].trim();

                String birthSeason = tokens[1].trim();

                String color = tokens[2].trim();

                int weight = Integer.parseInt(tokens[3].split(" ")[1].trim());

                String origin = tokens[4].trim();

                // Extract species from the line by removing the first and last words
                String species = line.replace(tokens[0], "").replace(tokens[4], "").trim();

                // Generate unique ID based on species and number of animals
                String uniqueId = generateId(species.toLowerCase(), numAnimals);

                // Assign a name from the animalNames list
                String name = animalNames.get(numAnimals % animalNames.size());

                // Calculate birth date based on birth season
                String birthDate = generateBirthDate(birthSeason);

                // Create new Animal object and add it to the appropriate habitat
                AnimalJavaFinal animal = new AnimalJavaFinal(uniqueId, species, name, age, birthDate, color, gender, weight, origin, "N/A");
                switch (species.toLowerCase()) {
                    case "hyena":
                        hyenaHabitat.add(animal);
                        break;
                    case "lion":
                        lionHabitat.add(animal);
                        break;
                    case "tiger":
                        tigerHabitat.add(animal);
                        break;
                    case "bear":
                        bearHabitat.add(animal);
                        break;
                }
                numAnimals++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

    // Generate report file (zooPopulation.txt)
    try (PrintWriter writer = new PrintWriter("C:\\Users\\augus\\Desktop\\School\\CIT-63\\zooPopulation.txt")) {
        writer.println("Hyena Habitat:");
        for (AnimalJavaFinal animal : hyenaHabitat) {
            writer.println(animal.toString());
        }
        writer.println("\nLion Habitat:");
        for (AnimalJavaFinal animal : lionHabitat) {
            writer.println(animal.toString());
        }
        writer.println("\nTiger Habitat:");
        for (AnimalJavaFinal animal : tigerHabitat) {
            writer.println(animal.toString());
        }
        writer.println("\nBear Habitat:");
        for (AnimalJavaFinal animal : bearHabitat) {
            writer.println(animal.toString());
        }
        System.out.println("Report file (zooPopulation.txt) generated successfully.");
    } catch (FileNotFoundException e) {
        System.out.println("Error generating report file.");
    }
}
}