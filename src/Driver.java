import java.util.Scanner;

public class Driver {

    public static void main(String[] args) {

        Burger b = new Burger();
        Pizza p = new Pizza();
        HotDog h = new HotDog();
        Bestellung o = new Bestellung();
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;

        printInstructions();
        while(!quit) {
            System.out.println("Bitte Auswahl treffen: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch(choice) {
                case 0:
                    printInstructions();
                    break;
                case 1:
                    b.createBurger();
                    break;
                case 2:
                    p.createPizza();
                    break;
                case 3:
                    h.createHotDog();
                    break;
                case 4:
                    o.menu();
                    break;
                case 5:
                    quit = true;
                    break;
            }
        }
    }

    public static void printInstructions() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - Auswahl anzeigen.");
        System.out.println("\t 1 - Burger erstellen");
        System.out.println("\t 2 - Pizza erstellen");
        System.out.println("\t 3 - HotDog erstellen\"");
        System.out.println("\t 4 - Bestellen");
        System.out.println("\t 5 - Beenden");
    }



}
