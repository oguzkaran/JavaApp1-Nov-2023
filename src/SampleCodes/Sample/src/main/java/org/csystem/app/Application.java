/*----------------------------------------------------------------------------------------------------------------------
    Aşağıdaki demo örneği inceleyiniz
----------------------------------------------------------------------------------------------------------------------*/
package org.csystem.app;

import com.karandev.io.util.console.Console;
import org.csystem.util.collection.CollectionUtil;
import org.csystem.util.datasource.factory.PersonFactory;
import org.csystem.util.datasource.people.MaritalStatus;
import org.csystem.util.datasource.people.Person;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.karandev.io.util.console.CommandLineArgs.checkLengthEquals;

class Application {
    public static void print(Map<MaritalStatus, List<Person>> map, MaritalStatus maritalStatus)
    {
        if (!map.get(maritalStatus).isEmpty()) {
            Console.writeLine("'%s' people:", maritalStatus.toString().toLowerCase());
            map.get(maritalStatus).forEach(Console::writeLine);
        }
        else
            Console.writeLine("No '%s' person!...", maritalStatus.toString().toLowerCase());
    }

    public static void run(String[] args)
    {
        try {
            checkLengthEquals(2, args.length, "wrong number of arguments!...");
            var factory = PersonFactory.loadFromTextFile(args[0]);
            var threshold = Double.parseDouble(args[1]);
            var people = CollectionUtil.stream(factory.getPeopleAsIterable());
            var map = people
                    .filter(p -> p.getAge() > threshold)
                    .collect(Collectors.groupingBy(Person::getMaritalStatus));

            print(map, MaritalStatus.SINGLE);
            print(map, MaritalStatus.MARRIED);
            print(map, MaritalStatus.DIVORCED);
            print(map, MaritalStatus.WIDOW);
        }
        catch (NumberFormatException ignore) {
            Console.Error.writeLine("Invalid age threshold value!...");
        }
        catch (IOException ex) {
            Console.Error.writeLine("IO problem occurred:%s", ex.getMessage());
        }
        catch (Throwable ex) {
            Console.Error.writeLine("Problem occurred :%s", ex.getMessage());
        }
    }
}
