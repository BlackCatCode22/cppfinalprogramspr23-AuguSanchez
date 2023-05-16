// CPPfinalProgramDemo01.cpp
// dH 4/10/23
// Animal class definition by chatGPT
// modified by dH 4/13/23

// Understand pointers and linked lists.
// Create a linked list of animals

// This program will give you a start on your final program. Lots of repetitive code here - reduce that to functions.
// Use pencil and paper when writing a program with lists and pointers (it's very easy to get lost and pointer errors
//    are arcade) 

#include <iostream>
#include <string>
using namespace std;

// Define a class for animals
class Animal {
private:
    string m_id;
    string m_name;
    int m_age;

public:
    // Default constructor
    Animal() {}

    // Constructor with parameters
    Animal(string _id, string _name, int _age) {
        m_id = _id;
        m_name = _name;
        m_age = _age;
    }

    // Getter functions
    string get_id() const { return m_id; }
    string get_name() const { return m_name; }
    int get_age() const { return m_age; }

    // Setter functions
    void set_id(string _id) { m_id = _id; }
    void set_name(string _name) { m_name = _name; }
    void set_age(int _age) { m_age = _age; }
};

// Define a class for a node in a linked list of integers
class Node {
public:
    int value;
    Node* next;
};

// Define a class for a node in a linked list of animal objects
class AnimalNode {
public:
    int anInt;
    Animal* animalObj;
    AnimalNode* next;
};

// Function to print the linked list of integers
void printList(Node* n) {
    cout << "\nLinked List:\n";
    while (n != nullptr) {
        cout << n->value << endl;
        n = n->next;
    }
}

// Function to print the linked list of animal objects
void printAnimalList(AnimalNode* n) {
    cout << "\nAnimal Linked List:\n";
    while (n != nullptr) {
        cout << n->anInt << " " << n->animalObj->get_name() << " " << n->animalObj->get_age() << " " << n->animalObj->get_id() << endl;
        n = n->next;
    }
}

// Function to create a new node for the linked list of integers
Node* createNode(int value) {
    Node* newNode = new Node();
    newNode->value = value;
    newNode->next = nullptr;
    return newNode;
}

// Function to append a new node to the linked list of integers
void appendNode(Node* head, int value) {
    Node* newNode = createNode(value);

    Node* current = head;
    while (current->next != nullptr) {
        current = current->next;
    }

    current->next = newNode;
}

// Function to create a new node for the linked list of animal objects
AnimalNode* createAnimalNode(int anInt, Animal* animalObj) {
    AnimalNode* newNode = new AnimalNode();
    newNode->anInt = anInt;
    newNode->animalObj = animalObj;
    newNode->next = nullptr;
    return newNode;
}

// Function to append a new node to the linked list of animal objects
void appendAnimalNode(AnimalNode* head, int anInt, Animal* animalObj) {
    AnimalNode* newNode = createAnimalNode(anInt, animalObj);

    AnimalNode* current = head;
    while (current->next != nullptr) {
        current = current->next;
    }

    current->next = newNode;
}

// Function to create a linked list of animal objects with predefined animals
void createAnimalLinkedList(AnimalNode*& head) {
    // Create animal objects
    Animal* myAnimal01 = new Animal("Hy01", "Shenzi", 5);
    Animal* myAnimal02 = new Animal("Hy02", "Banzai", 2);
    Animal* myAnimal03 = new Animal("Hy03", "Ed", 7);
    // Create the head of the linked list
    head = createAnimalNode(1, myAnimal01);

    // Append additional nodes to the linked list
    appendAnimalNode(head, 2, myAnimal02);
    appendAnimalNode(head, 3, myAnimal03);
}

// Function to create a linked list of animal objects using objects and a specified number of animals
void createAnimalLinkedListWithObjects(AnimalNode*& head, int numOfAnimals) {
    // Create the head of the linked list
    head = createAnimalNode(1, nullptr);
    AnimalNode* current = head;
    // Create new animal objects and append them to the linked list
    for (int i = 1; i <= numOfAnimals; ++i) {
        string id = "Hy" + to_string(i);
        string name = "Animal" + to_string(i);
        int age = i * 2;

        // Create a new animal object
        Animal* animalObj = new Animal(id, name, age);

        // Set the animal object for the current node
        current->animalObj = animalObj;

        if (i != numOfAnimals) {
            // Create a new node and move the current pointer to the next node
            current->next = createAnimalNode(i + 1, nullptr);
            current = current->next;
        }
    }
}

int main() {
    // Creating a linked list of integers
    Node* head = createNode(1);
    appendNode(head, 2);
    appendNode(head, 3);
    appendNode(head, 4);
    appendNode(head, 5);
    printList(head);
    // Creating a linked list of animals
    AnimalNode* animalHead = nullptr;
    createAnimalLinkedList(animalHead);
    printAnimalList(animalHead);

    // Creating a linked list of animals using objects
    AnimalNode* animalHeadWithObjects = nullptr;
    int numOfAnimals = 4;
    createAnimalLinkedListWithObjects(animalHeadWithObjects, numOfAnimals);
    printAnimalList(animalHeadWithObjects);

    return 0;
}