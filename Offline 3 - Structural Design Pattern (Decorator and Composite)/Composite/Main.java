import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Root root = new Root("My Computer", "My Computer");
        Container tmpContainer;
        Container currentContainer = root;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to My Computer! Write command 'Exit' to terminate.");
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] commands = line.split(" ");
            if (commands.length == 2) {
                String Command = commands[0];
                String Name = commands[1];

                switch (Command.toLowerCase()) {
                    case "mkdrive":
                        if (currentContainer.getType().equalsIgnoreCase("root")) {
                            Drive drive = new Drive(Name);
                            root.add(drive);
                            System.out.println("Drive " + Name + " successfully created.");
                            break;
                        }
                        else
                        {
                            System.out.println("Cannot create a drive here.");
                            break;
                        }

                    case "cd":
                        if (currentContainer.getType().equalsIgnoreCase("root") &&
                                Name.substring(Name.length() - 2).equals(":\\"))
                            Name = Name.substring(0, Name.length() - 2);

                        if (!Name.equalsIgnoreCase("~")) {
                            tmpContainer = currentContainer.getContainer(Name);
                            if (tmpContainer == null) {
                                System.out.println("No such folder or drive exists!");
                                break;
                            }
                            if (tmpContainer.getType().equalsIgnoreCase("file")) {
                                System.out.println("Error! Cannot change directory to a file.");
                                break;
                            }
                            currentContainer = tmpContainer;
                            System.out.println("Current Directory Changed to " + Name + ".");
                            break;
                        } else {
                            currentContainer = root;
                            System.out.println("Current Directory Changed to " + root.getName() + ".");
                            break;
                        }

                    case "ls":
                        tmpContainer = currentContainer.getContainer(Name);
                        if (tmpContainer == null) {
                            System.out.println("No such folder or drive exists!");
                            break;
                        }
                        tmpContainer.printDetails();
                        break;
                    case "delete":
                        currentContainer.delete(Name);
                        break;
                    case "mkdir":
                        if (!currentContainer.getType().equalsIgnoreCase("root")) {
                            Folder folder = new Folder(Name);
                            currentContainer.add(folder);
                            System.out.println("Folder with name \"" + Name + "\" successfully created.");
                        }
                        else
                        {
                            System.out.println("Cannot create a folder here!");
                        }
                        break;
                    default:
                        System.out.println("Invalid instruction!");
                        break;
                }
            } else if (commands.length == 1) {
                String Command = commands[0];
                switch (Command.toLowerCase()) {
                    case "list":
                        currentContainer.printList();
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid instruction!");
                        break;
                }
            } else if (commands.length == 3) {
                if (commands[0].equalsIgnoreCase("delete") && commands[1].equalsIgnoreCase("-r")) {
                    currentContainer.recursiveDelete(commands[2]);
                }
                else if (commands[0].equalsIgnoreCase("touch")) {
                    String name = commands[1];
                    Double size = Double.parseDouble(commands[2]);
                    String currentDirectoryType = currentContainer.getType();
                    if (currentDirectoryType.equalsIgnoreCase("drive") ||
                            currentDirectoryType.equalsIgnoreCase("folder")) {
                        File file = new File(name, size);
                        currentContainer.add(file);
                        System.out.println("Filename: " + name + ", size: " + size + " kB successfully created.");
                    }
                    else
                        System.out.println("Cannot create a file here.");
                }
                else
                    System.out.println("Invalid instruction!");
            }
            else
            {
                System.out.println("Invalid instruction!");
            }
        }
    }
}
