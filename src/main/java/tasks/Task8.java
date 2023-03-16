package tasks;

import common.Person;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.function.Function;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 {

	// Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со
	// второй
	// Поменял, так как нельзя менять колекцию persons. Тот кто нам её передал
	// врядли надеялся, что мы её поменяем
	// Если в коллекции n элементов и мы вызовем функцию n раз, колекция будет пуста
	public List<String> getNames(List<Person> persons) {
		return persons.stream().skip(1).map(Person::getFirstName).collect(Collectors.toList());
	}

	// ну и различные имена тоже хочется
	public Set<String> getDifferentNames(List<Person> persons) {
		return new HashSet<>(getNames(persons));
	}

	// Для фронтов выдадим полное имя, а то сами не могут
	// Я посчитал, что если хотябы один будет пустой, то выдавть пусто.
	// По предыдущей логике было бы не понятно какое поле пусто Фамилия Имя или
	// Отчество
	public String convertPersonToString(Person person) {
		return Stream.of(person.getSecondName(), person.getFirstName(), person.getMiddleName())
				.filter(s -> s != null && !s.isEmpty()).collect(Collectors.joining(" "));
	}

	public <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
		Map<Object, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	// словарь id персоны -> ее имя
	public Map<Integer, String> getPersonNames(Collection<Person> persons) {
		return persons.stream().collect(Collectors.toMap(Person::getId, person -> convertPersonToString(person)));
	}

	// есть ли совпадающие в двух коллекциях персоны?
	public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
		Set<Person> personSet = new HashSet<>(persons1);
		return persons2.stream().anyMatch(pr1 -> personSet.contains(pr1));
	}

	// вот тут посчитал, что считаем количестов чётных чисел
	public long countEven(Stream<Integer> numbers) {
		return numbers.filter(num -> num % 2 == 0).count();
	}
}
