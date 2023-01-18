package tasks;

import common.Company;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

/*
Из коллекции компаний необходимо получить всевозможные различные названия вакансий
 */
public class Task7 {

  public static Set<String> vacancyNames(Collection<Company> companies) {
    Set<String> company = companies.stream()
            .flatMap(item -> item.getVacancies().stream())
            .map(item -> item.getTitle())
            .distinct()
            .collect(Collectors.toSet());
    return company;
  }

}
