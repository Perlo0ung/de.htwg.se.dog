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
    public Dog(int playernumber) {
        controller = new GameTable(playernumber);
        controller.newRound();
        tui = new TextUserInterface(controller);

    }

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        //TODO change input of playernumber
        //TODO errorhandling playernumberinput
        System.out.println("Bitte geben sie die Spieler anzahl ein:");
        int playernumber = scanner.nextInt();
        instance = new Dog(playernumber);
        boolean continu = true;

        while (continu) {
            continu = tui.processTurn(scanner);
        }

    }

}
