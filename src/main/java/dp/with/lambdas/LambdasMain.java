package dp.with.lambdas;

import java.util.function.Predicate;
import java.util.function.Supplier;

class LambdaMain {
    public static void main(String[] args) {

        Predicate<String> containsA = str -> str.contains("A");
        Predicate<String> hasLen10 = str -> str.length() == 10;

        boolean andromeda = hasLen10.or(containsA).test("Andromeda");
        System.out.println(andromeda);
        Supplier<Drive> driveSupplier = Drive::new;
        Predicate<String> meloStringPredicate = driveSupplier.get().foo("melo");
        meloStringPredicate.test("melody");

    }
}

class Drive {


    public Predicate<String> foo(String string) {
        return str -> str.contains(string);
    }
}