package commands;

import data.Request;
import server.Server;

/**
 * The type Help command.
 */
public class HelpCommand implements Command {
    @Override
    public void execute(Request request) {
        Server.print(request.client,
                "help : вывести справку по доступным командам\n" +
                "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n" +
                "show {coordinates} : вывести элементы коллекции, упорядоченные по расстоянию от заданных координат \n" +
                "add {element} : добавить новый элемент в коллекцию\n" +
                "update id {element} : обновить значение элемента коллекции, id которого равен заданному\n" +
                "remove_by_id id : удалить элемент из коллекции по его id\n" +
                "clear : очистить коллекцию\n" +
                "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n" +
                "exit : завершить программу на клиенте\n" +
                "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции\n" +
                "remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный\n" +
                "count_less_than_mpaa_rating {mpaaRating} : вывести количество элементов, значение поля mpaaRating которых меньше заданного\n" +
                "filter_greater_than_mpaa_rating {mpaaRating} : вывести элементы, значение поля mpaaRating которых больше заданного\n" +
                "print_field_descending_genre : вывести значения поля genre всех элементов в порядке убывания\n"
        );
    }
}
