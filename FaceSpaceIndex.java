package FaceSpace;

import java.util.Scanner;

public class FaceSpaceIndex {

    public static void main(String args[]) {
        boolean on = true;
        System.out.println("______             _____                      \n" +
                "|  ___|           /  ___|                     \n" +
                "| |_ __ _  ___ ___\\ `--. _ __   __ _  ___ ___ \n" +
                "|  _/ _` |/ __/ _ \\`--. \\ '_ \\ / _` |/ __/ _ \\\n" +
                "| || (_| | (_|  __/\\__/ / |_) | (_| | (_|  __/\n" +
                "\\_| \\__,_|\\___\\___\\____/| .__/ \\__,_|\\___\\___|\n" +
                "                        | |                   \n" +
                "                        |_|                   ");
        while(on) {
            System.out.println("Please enter a command, or type 'Help' for more information");
            Scanner command = new Scanner(System.in);
            String commandString = command.nextLine().toLowerCase();
            FaceSpaceGraph g = new FaceSpaceGraph(0);
            switch (commandString){
                case "help":
                    System.out.println("Here are a list of commands you can do:");
                    System.out.println("Add/Update a User - Add User");
                    System.out.println("Search for a User - Search User");
                    System.out.println("Add a Friend for a User - Add Friend");
                    System.out.println("Remove a Friend for a User - Remove Friend");
                    System.out.println("Find Shortest Path between two users - Find Path");
                    System.out.println("Exit");
                    break;
                case "add user":
                    System.out.println("add user function");
                    break;
                case "search user":
                    System.out.println("search user function");
                    break;
                case "add friend":
                    System.out.println("add friend function");
                    break;
                case "remove friend":
                    System.out.println("remove friend function");
                    break;
                case "find path":
                    System.out.println("find path function");
                    break;
                case "exit":
                    on = false;
            }
        }

    }

}
