package tasks;

import common.Area;
import common.Person;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/*
Имеются
- коллекция персон Collection<Person>
- словарь Map<Integer, Set<Integer>>, сопоставляющий каждой персоне множество id регионов
- коллекция всех регионов Collection<Area>
На выходе хочется получить множество строк вида "Имя - регион". Если у персон регионов несколько, таких строк так же будет несколько
 */
public class Task6 {

  public static Set<String> getPersonDescriptions(Collection<Person> persons,
                                                  Map<Integer, Set<Integer>> personAreaIds,
                                                  Collection<Area> areas) {
    //Set<String> lstResult = persons.stream().map(mapper)
   
    Set<String> vv = persons.stream().flatMap(person_item ->{
          return personAreaIds.get(person_item.getId()).stream()
                  .map(el_id -> areas.stream()
                          .filter(arr -> arr.getId().equals(el_id))
                          .map(arr -> { 
                              return person_item.getFirstName() + " - " + arr.getName();})
                          .findFirst().orElse(null) );
      }).filter(obj ->Objects.nonNull(obj)).collect(Collectors.toSet());
    return vv;
  }
}
