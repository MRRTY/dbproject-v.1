import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, User, enter your command below:");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = sc.nextLine();
            int index = command.indexOf(" ");
            String mainCommand;
            if(index<command.length()-1 && index != -1) {
                mainCommand = command.substring(0, index);
            }else{
                mainCommand = command;
            }
            String[] commandLine = command.substring(index+1).split(" ");
            switch (mainCommand) {
                case ("create_database"):
                    Dispatcher.create_database(commandLine);
                    break;
                case ("use_database"):
                    Dispatcher.use_database(commandLine);
                    break;
                case ("save_database"):
                    Dispatcher.save_database(commandLine);
                    break;
                case ("load_database"):
                    Dispatcher.load_database(commandLine);
                    break;
                case ("delete_database"):
                    Dispatcher.delete_database(commandLine);
                    break;
                case ("create_table"):
                    try {
                        Dispatcher.create_table(commandLine);
                    }catch (RuntimeException re){
                        System.out.println("invalid command of creating table");
                    }
                    break;
                case ("use_table"):
                    Dispatcher.use_table(commandLine);
                    break;
                case ("add_row"):
                    Dispatcher.add_row(commandLine);
                    break;
                case ("delete_table"):
                    Dispatcher.delete_table(commandLine);
                    break;
                case ("view_table"):
                    Dispatcher.view_table(commandLine);
                    break;
                case ("edit_row"):
                    Dispatcher.edit_row(commandLine);
                    break;
                case ("status"):
                    Dispatcher.status();
                    break;
                default:
                    System.out.println("Incorrect command, try again");
                    break;
            }

        }





    }
}
