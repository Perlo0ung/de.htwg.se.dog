package de.htwg.se.dog;

import java.util.Scanner;

import com.google.inject.Inject;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.controller.impl.GameTable;
import de.htwg.se.dog.tui.TextUserInterface;

public class Dog {
    private static Scanner scanner;
    private static GameTableInterface controller;
    private static Dog instance;
    private static TextUserInterface tui;

    @Inject
    public Dog() {
        controller = new GameTable(2);
        controller.newRound();
        tui = new TextUserInterface(controller);

    }

    public static void main(String[] args) {
        instance = new Dog();
        boolean continu = true;
        scanner = new Scanner(System.in);
        while (continu) {
            continu = tui.processTurn(scanner);
        }

    }

}
