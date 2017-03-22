import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by nirav on 10/25/2016.
 */
public class Client {
    private static String[] names = {
            "James",
            "John",
            "Marc",
            "Donald",
            "Mister",
            "Hillary",
            "Gina",
            "Trump",
            "Random",
            "AAA",
            "BBB",
            "CCC",
            "DDD"
    };

    private static String[] nodes = {
            "node1",
            "node2",
            "node3",
            "node4",
            "node5"
    };

    private static final int NUM_REPLICAS = 3;

    public static void main(String[] args) {
        HashFunction hf = new HashFunction();
        ConsistentHash<String> caches = new ConsistentHash<>(hf, NUM_REPLICAS, Arrays.asList(nodes));

        for (String name : names) {
            caches.add(name);
        }

        System.out.println("Added names");

        System.out.print("Cache status: " + caches.toString());

    }

}
