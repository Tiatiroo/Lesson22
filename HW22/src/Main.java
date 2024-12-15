import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static List<String> todos = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("Напишите команду");
            String command = new Scanner(System.in).nextLine();
            String[] splittedCommand = splitCommand(command);
            String commandCase = splittedCommand[0];
            switch (commandCase) {
                case "Добавить":
                    if (splittedCommand.length == 3) {
                        int commandPosition = Integer.parseInt(splittedCommand[1]);
                        String commandToDo = splittedCommand[2];
                        add(commandPosition, commandToDo);
                        break;
                    }
                    String commandToDo = splittedCommand[1];
                    add(commandToDo);
                    break;
                case "Удалить":
                    int commandPosition = Integer.parseInt(splittedCommand[1]);
                    delete(commandPosition);
                    break;
                case "Изменить":
                    commandPosition = Integer.parseInt(splittedCommand[1]);
                    commandToDo = splittedCommand[2];
                    set(commandPosition, commandToDo);
                    break;
                case "Листинг":
                    getList();
                    break;
                case "Выход":
                    System.exit(0);
                default:
                    System.out.println("Команда неопознана");
                    break;
            }
        }
    }

    public static void add(String todo) {
        if (todos.contains(todo)) {
            System.out.println("Это дело уже есть в списке");
            return;
        }
        todos.add(todo);
        System.out.println("Дело " + todos.indexOf(todo) + " " + todo + " добавлено");
    }

    public static void add(int pos, String todo) {
        if (pos >= todos.size()) {
            add(todo);
            return;
        }
        todos.set(pos, todo);
        System.out.println("Дело " + todos.indexOf(todo) + " " + todo + " добавлено");
    }

    public static void delete(int pos) {
        if (pos >= todos.size() || !todos.contains(todos.get(pos))) {
            System.out.println("Этого дела нет");
            return;
        }
        String todo = todos.get(pos);
        todos.remove(pos);
        System.out.println("Дело " + pos + " " + todo + " удалено");
    }

    public static void set(int pos, String todo) {
        if (pos >= todos.size()) {
            System.out.println("Этого дела нет");
            return;
        }
        todos.set(pos, todo);
        System.out.println("Дело " + todos.indexOf(todo) + " " + todo + " изменено");
    }

    public static void getList() {
        if (todos.isEmpty()) {
            System.out.println("Список дел пуст");
            return;
        }
        System.out.println("__________");
        System.out.println("Список дел:");
        for (String todo : todos) {
            System.out.println(todos.indexOf(todo) + " " + todo);
        }
        System.out.println("__________");
    }

    public static String[] splitCommand(String command) {
        String[] splittedCommand = command.split(" ", 3);
        String commandCase = splittedCommand[0];
        if (splittedCommand.length == 1) {
            return new String[]{commandCase};
        } else if (!splittedCommand[1].trim().matches("(^\\d+)")) {
            String commandToDo = splittedCommand[1] + " " + splittedCommand[2];
            return new String[]{commandCase, commandToDo};
        } else if (splittedCommand[1].trim().matches("(^\\d+)") && splittedCommand.length == 2) {
            String commandPosition = splittedCommand[1];
            return new String[]{commandCase, commandPosition};
        }
        String commandPosition = splittedCommand[1];
        String commandToDo = splittedCommand[2];
        return new String[]{commandCase, commandPosition, commandToDo};
    }
}