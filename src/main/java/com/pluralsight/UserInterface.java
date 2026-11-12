package com.pluralsight;

import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private DealershipFileManager fileManager;
    private Scanner scanner;

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }
}
