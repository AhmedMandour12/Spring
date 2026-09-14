import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        // ==================== البيانات ====================
        List<Integer> numbers = Arrays.asList(10, 5, 3, 7, 2, 10, 5, 8, 9, 0, -3, 4);
        List<String> names = Arrays.asList("Ali", "Mona", "Ahmed", "Sara", "Amr", "Laila", "Kareem", "Nada", "Nour", "Samy", "", null);

        System.out.println("\n==================== BASIC OPERATIONS ====================\n");

        // ============ Task 1: Filter Even Numbers ============

        Set<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0 && n != 0)
                .collect(Collectors.toSet());
        System.out.println("Even Numbers: " + evenNumbers);
        System.out.println("________________________________________________________________________");

        // ============ Task 2: Names Starting with 'A' ============

        List<String> namesWithA = names.stream()
                .filter(name -> name != null && !name.isEmpty() && name.startsWith("A"))
                .collect(Collectors.toList());
        System.out.println("Names starting with 'A': " + namesWithA);
        System.out.println("________________________________________________________________________");

        // ============ Task 3: Convert to Uppercase ============

        List<String> upperCaseNames = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("Uppercase Names: " + upperCaseNames);
        System.out.println("________________________________________________________________________");

        // ============ Task 4: Sort Descending & Remove Duplicates ============

        List<Integer> sortedDescending = numbers.stream()
                .distinct()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.toList());
        System.out.println("Sorted (Descending) & Unique: " + sortedDescending);

        System.out.println("\n==================== INTERMEDIATE OPERATIONS ====================\n");

        // ============ Task 5: Count Strings Longer than 5 Characters ============

        List<String> longNames = names.stream()
                .filter(name -> name != null && name.length() >= 5)
                .collect(Collectors.toList());
        System.out.println("Names longer than 5 chars: " + longNames);
        System.out.println("Count: " + longNames.size());
        System.out.println("First Element: " + longNames.stream().findFirst());
        System.out.println("________________________________________________________________________");

        // ============ Task 6: Find Numbers Divisible by 5 ============

        List<Integer> divisibleBy5 = numbers.stream()
                .filter(n -> n != 0 && n % 5 == 0)
                .collect(Collectors.toList());
        System.out.println("Numbers divisible by 5: " + divisibleBy5);
        System.out.println("________________________________________________________________________");

        // ============ Task 7: Collect into Set ============

        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        System.out.println("Unique Numbers (Set): " + uniqueNumbers);
        System.out.println("________________________________________________________________________");

        // ============ Task 8: Skip First 3 Elements ============

        List<Integer> skipped = numbers.stream()
                .skip(3)
                .collect(Collectors.toList());
        System.out.println("After skipping first 3 elements: " + skipped);

        System.out.println("\n==================== COLLECTORS & GROUPING ====================\n");

        // ==================== Advanced data ====================
        List<Employee> employees = Arrays.asList(
                new Employee("Ali", 30, "HR", 5000),
                new Employee("Mona", 25, "IT", 7000),
                new Employee("Ahmed", 30, "HR", 5500),
                new Employee("Sara", 27, "IT", 7200),
                new Employee("Omar", 40, "Finance", 8000),
                new Employee("Laila", 35, "Finance", 8200)
        );

        List<Student> students = Arrays.asList(
                new Student("Ali", "IT", 85),
                new Student("Mona", "CS", 92),
                new Student("Ahmed", "IT", 60),
                new Student("Sara", "CS", 70),
                new Student("Omar", "IS", 45),
                new Student("Laila", "IS", 78)
        );

        List<List<String>> nestedWords = Arrays.asList(
                Arrays.asList("Java", "Stream"),
                Arrays.asList("API", "Lambda"),
                Arrays.asList("FlatMap", "Map")
        );

        // ============ Task 9: Group Employees by Age and Count ============

        Map<Integer, Long> ageCount = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getAge,
                        Collectors.counting()
                ));

        System.out.println("Employees grouped by age:");
        ageCount.forEach((age, count) ->
                System.out.println("  Age " + age + ": " + count + " employee(s)")
        );
        System.out.println("________________________________________________________________________");

        // ============ Task 10: Average Salary per Department ============

        Map<String, Double> avgSalaryPerDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        System.out.println("Average salary per department:");
        avgSalaryPerDept.forEach((dept, avgSalary) ->
                System.out.printf("  %s: %.2f%n", dept, avgSalary)
        );

        System.out.println("\n==================== OPTIONAL, MAP & FLATMAP ====================\n");

        // ============ Task 11: Flatten Nested Lists ============

        List<String> flattened = nestedWords.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Flattened list: " + flattened);
        System.out.println("________________________________________________________________________");

        // ============ Task 12: Extract Unique Characters ============

        Set<String> uniqueChars = nestedWords.stream()
                .flatMap(list -> list.stream())                          // اطلع الكلمات
                .flatMap(word -> Arrays.stream(word.split("")))          // حول كل كلمة لحروف
                .collect(Collectors.toSet());
        System.out.println("Unique characters: " + uniqueChars);
        System.out.println("________________________________________________________________________");

        // ============ Task 13: Filter Non-Empty Optionals ============

        List<Optional<String>> optionalNames = Arrays.asList(
                Optional.of("Ahmed"),
                Optional.empty(),
                Optional.of("Sara"),
                Optional.empty(),
                Optional.of("Omar")
        );

        List<String> nonEmptyNames = optionalNames.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
        System.out.println("Non-empty Optionals: " + nonEmptyNames);
        System.out.println("________________________________________________________________________");

        // ============ Task 14: Map Strings to Their Lengths ============

        List<Integer> lengths = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("String lengths: " + lengths);
        System.out.println("________________________________________________________________________");

        // ============ Task 15: Uppercase Words Starting with 'A' ============

        List<String> wordsStartingA = Arrays.asList("Ali", "Ahmed", "Mona", "Amy", "Sara", "Ahmed");

        List<String> uppercaseA = wordsStartingA.stream()
                .filter(word -> word.startsWith("A"))
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Uppercase words starting with 'A': " + uppercaseA);

        System.out.println("\n==================== ADVANCED OPERATIONS ====================\n");

        // ============ Task 16: Sort by Salary then by Name ============

        List<Employee> sortedEmployees = employees.stream()
                .sorted((emp1, emp2) -> {

                    int salaryCompare = Double.compare(emp2.getSalary(), emp1.getSalary());
                    if (salaryCompare != 0) {
                        return salaryCompare;
                    }

                    return emp1.getName().compareTo(emp2.getName());
                })
                .collect(Collectors.toList());

        System.out.println("Employees sorted by salary (desc) then name:");
        sortedEmployees.forEach(emp ->
                System.out.println("  " + emp.getName() + " - " + emp.getSalary())
        );
        System.out.println("________________________________________________________________________");

        // ============ Task 17: Find Second Highest Number ============

        List<Integer> numbersForSecondHighest = Arrays.asList(45, 23, 67, 12, 89, 34, 89, 56);

        Integer secondHighest = numbersForSecondHighest.stream()
                .distinct()
                .sorted((a, b) -> b - a)
                .skip(1)
                .findFirst()
                .orElse(-1);

        System.out.println("Second highest number: " + secondHighest);
        System.out.println("________________________________________________________________________");

        // ============ Task 18: Find Duplicates ============

        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicates = numbers.stream()
                .filter(num -> !seen.add(num))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Duplicate numbers: " + duplicates);
        System.out.println("________________________________________________________________________");

        // ============ Task 19: Remove Null or Empty Strings ============

        List<String> cleanedNames = names.stream()
                .filter(name -> name != null && !name.isEmpty())
                .collect(Collectors.toList());
        System.out.println("Cleaned names (no null/empty): " + cleanedNames);
        System.out.println("________________________________________________________________________");

        // ============ Task 20: Partition Students Pass/Fail ============

        Map<Boolean, List<Student>> passFailGroups = students.stream()
                .collect(Collectors.partitioningBy(
                        student -> student.getGrade() >= 85
                ));

        List<Student> passed = passFailGroups.get(true);
        List<Student> failed = passFailGroups.get(false);

        System.out.println("Students - PASSED (≥85):");
        passed.forEach(s -> System.out.println("  " + s.getName() + " - Grade: " + s.getGrade()));

        System.out.println("Students - FAILED (<85):");
        failed.forEach(s -> System.out.println("  " + s.getName() + " - Grade: " + s.getGrade()));

        System.out.println("\n==================== END ====================\n");
    }
}