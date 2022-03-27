package brickset;

import repository.Repository;

import java.time.Year;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Kiírja az összes lego set nevét
     */
    public void printAllName() {
        getAll().stream().
                map(LegoSet::getName).forEach(System.out::println);
    }


    /**
     * Visszaadja az összes lego darabot
     */
    public long countAllPiece(){
        return getAll().stream()
                .map(LegoSet::getPieces)
                .filter(Objects::nonNull)
                .count();
    }


    /**
     * Kiírja azon lego set-teknek a nevét, amelyek a megadott évben készültek
     */
    public void printSameYear(Year year){
        getAll().stream()
                .filter(legoSet -> year == legoSet.getYear())
                .map(LegoSet::getName)
                .filter(Objects::nonNull)
                .forEach(System.out::println);
    }


    /**
     * Visszaadja a leghosszabb lego set nevét
     */
    public String getLongestName() {
        return getAll().stream().
                map(LegoSet::getName).
                max(Comparator.comparing(String::length)).
                get();
    }


    /**
     * Ha a megadott súlynál könyebb az adott lego akkor kiírja
     */
    public void printNameLowerWeight(int weight){
        getAll().stream()
                .filter(legoSet -> legoSet.getDimensions() != null && legoSet.getDimensions().getWeight() != null && legoSet.getDimensions().getWeight() <= weight)
                .map(LegoSet::getName)
                .sorted()
                .forEach(System.out::println);
    }


    public static void main(String[] args) {
        var repository = new LegoSetRepository();

        repository.printAllName();

        System.out.println(repository.countAllPiece());

        repository.printSameYear(Year.of(2000));

        System.out.println(repository.getLongestName());

        repository.printNameLowerWeight(50);
    }
}
