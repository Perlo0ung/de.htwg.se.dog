package de.htwg.se.dog;

import java.util.Scanner;

import com.google.inject.Inject;

import de.htwg.se.dog.controller.GameTableInterface;
import de.htwg.se.dog.controller.impl.GameTable;
import de.htwg.se.dog.view.GraphicalUserInterface;
import de.htwg.se.dog.view.TextUserInterface;

/**
 * Main class to start a new doggame
 * 
 * @author Michael,Christian
 * 
 */
public final class Dog {
    private static Scanner scanner;
    private static GameTableInterface controller;
    private static Dog instance;
    private static TextUserInterface tui;
    private static GraphicalUserInterface gui;

    @Inject
    /**
     * Creates a new Doggame
     * @param playernumber
     */
    private Dog(int playernumber) {
        controller = new GameTable(playernumber);
        controller.newRound();
        tui = new TextUserInterface(controller);
        gui = new GraphicalUserInterface(controller);
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
