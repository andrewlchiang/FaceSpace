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
        FaceSpace fs = new FaceSpace();
        while(on) {
            System.out.println("Please enter a command, or type 'Help' for more information");
            Scanner command = new Scanner(System.in);
            String commandString = command.nextLine().toLowerCase(); 
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
                    String addedname = command.nextLine();
                    fs.addUser(addedname);
                    break;
                case "remove user":
                    System.out.println("remove user function");
                    String removedname = command.nextLine();
                    fs.removeUser(removedname);
                    break;
                case "user list":
                    System.out.println("print user list function");
                    for(int i = 0; i < fs.getNum(); i++) {
                        System.out.println(fs.getNames(i));
                    }
                    break;
                case "search user":
                    System.out.println("search user function");
                    String searchedname = command.nextLine();
                    System.out.println(searchedname + " is the number " + fs.Search(searchedname)+ " user");
                    break;
                case "add friend":
                    System.out.println("add friend function");
                    System.out.println("say a name");
                    String friendedname1 = command.nextLine();
                    System.out.println("say another name");
                    String friendedname2 = command.nextLine();
                    fs.addFriend(friendedname1, friendedname2);
                    break;
                case "remove friend":
                    System.out.println("remove friend function");
                    System.out.println("say a name");
                    String defriendedname1 = command.nextLine();
                    System.out.println("say another name");
                    String defriendedname2 = command.nextLine();
                    fs.removeFriend(defriendedname1, defriendedname2);
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