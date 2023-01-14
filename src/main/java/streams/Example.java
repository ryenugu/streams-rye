package streams;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toSet;

public class Example {

    public static void main(String[] args) {
        List<Integer> integers1 = Arrays.asList(10, 20, 30, 40);
        List<Integer> integers2 = Arrays.asList(-10, -20, -30, -40);
        integers1.stream()
                .flatMap(i ->
                        integers2.stream()
                                .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());

        List<Transaction> transactions = TestData.loadData();
        transactions.stream().filter(t -> t.getYear() == 2011)

                .sorted(Comparator.comparing(Transaction::getValue))
                .forEach(System.out::println);

        transactions
                .stream()
                .map(x -> x.getTrader().getCity())
                .collect(toSet())
                .forEach(System.out::println);
//        3.  Find all traders from Cambridge and sort them by name.
        transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("Return a string of all traders’ names sorted alphabetically.");
//        4.  Return a string of all traders’ names sorted alphabetically.
        String reduce = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(reduce);
//        5.  Are any traders based in Milan?
        System.out.println(" Are any traders based in Milan");
        boolean milan = transactions.stream()
                .map(Transaction::getTrader)
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println(milan);
        System.out.println("Print all transactions’ values from the traders living in Cambridge.");
        transactions.stream().filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(transaction -> transaction.getValue())
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println(" 7.  What’s the highest value of all the transactions?");
        Optional<Transaction> integerOptional = transactions.stream()

                .max(Comparator.comparing(Transaction::getValue));
        System.out.println(integerOptional.get().getValue());
//
//        8.  Find the transaction with the smallest value.


    }


}
