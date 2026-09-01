package org.example;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class Duck {

    // Método abstracto del diagrama UML
    public abstract void display();

    // Método toString del diagrama UML
    @Override
    public String toString() {
        return "Duck{}";
    }
}