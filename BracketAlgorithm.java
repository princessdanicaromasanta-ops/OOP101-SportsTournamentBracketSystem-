import java.util.ArrayList;
import java.util.List;

// ALGORITHMS: single-elimination bracket logic.
// Generic (<T>) so it works with Athlete or Team without depending on either class.
//
//  - Bracket size      : next power of two >= number of participants
//  - Byes              : bracketSize - participants (given to the top seeds)
//  - Round count       : log2(bracketSize)
//  - First-round pairs : standard seeding (1 vs last, 2 vs second-last, ...)
//  - Advancement       : winners of adjacent matches meet in the next round
public class BracketAlgorithm {

    private BracketAlgorithm() { }

    // One pairing in a round. 'second' is null when 'first' receives a bye.
    public static class Pairing<T> {
        private final T first;
        private final T second;

        public Pairing(T first, T second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst()  { return first; }
        public T getSecond() { return second; }
        public boolean isBye() { return second == null; }

        @Override
        public String toString() {
            return first + " vs " + (isBye() ? "BYE" : second);
        }
    }

    // ---- size / counting helpers -------------------------------------------------

    public static int nextPowerOfTwo(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Participant count must be at least 1.");
        }
        int p = 1;
        while (p < n) {
            p <<= 1;
        }
        return p;
    }

    public static int totalRounds(int participants) {
        if (participants < 2) {
            throw new IllegalArgumentException("A tournament needs at least 2 participants.");
        }
        int size = nextPowerOfTwo(participants);
        int rounds = 0;
        while (size > 1) {
            size >>= 1;
            rounds++;
        }
        return rounds;
    }

    public static int byeCount(int participants) {
        return nextPowerOfTwo(participants) - participants;
    }

    // Matches in a given round (1-based), counting bye pairings.
    public static int matchesInRound(int participants, int round) {
        int rounds = totalRounds(participants);
        if (round < 1 || round > rounds) {
            throw new IllegalArgumentException("Round must be between 1 and " + rounds + ".");
        }
        return nextPowerOfTwo(participants) >> round;
    }

    public static String roundName(int round, int totalRounds) {
        int fromEnd = totalRounds - round;
        switch (fromEnd) {
            case 0:  return "Finals";
            case 1:  return "Semifinals";
            case 2:  return "Quarterfinals";
            default: return "Round " + round;
        }
    }

    // ---- seeding -----------------------------------------------------------------

    // Returns seed numbers (1-based) in bracket order for a power-of-two size.
    // size 4 -> [1, 4, 2, 3] ; size 8 -> [1, 8, 4, 5, 2, 7, 3, 6]
    // Top seeds are kept apart so they can only meet in later rounds.
    public static int[] seedOrder(int size) {
        int[] order = {1};
        while (order.length < size) {
            int newLen = order.length * 2;
            int[] next = new int[newLen];
            for (int i = 0; i < order.length; i++) {
                next[2 * i] = order[i];
                next[2 * i + 1] = newLen + 1 - order[i];
            }
            order = next;
        }
        return order;
    }

    // ---- round generation --------------------------------------------------------

    // Builds round 1. 'participants' is ordered by seed (index 0 = seed 1).
    // Seeds beyond the participant count become byes for their opponents.
    public static <T> List<Pairing<T>> generateFirstRound(List<T> participants) {
        int n = participants.size();
        if (n < 2) {
            throw new IllegalArgumentException("A tournament needs at least 2 participants.");
        }
        int size = nextPowerOfTwo(n);
        int[] order = seedOrder(size);

        List<Pairing<T>> pairings = new ArrayList<>();
        for (int i = 0; i < size; i += 2) {
            int seedA = order[i];
            int seedB = order[i + 1];
            T a = (seedA <= n) ? participants.get(seedA - 1) : null;
            T b = (seedB <= n) ? participants.get(seedB - 1) : null;
            // The higher seed (a) is always a real participant when a bye is needed.
            pairings.add(new Pairing<>(a, b));
        }
        return pairings;
    }

    // Winners of adjacent matches (0&1, 2&3, ...) meet in the next round.
    public static <T> List<Pairing<T>> generateNextRound(List<T> winners) {
        if (winners.size() < 2 || winners.size() % 2 != 0) {
            throw new IllegalArgumentException(
                "Next round needs an even number of winners (at least 2); got " + winners.size() + ".");
        }
        List<Pairing<T>> pairings = new ArrayList<>();
        for (int i = 0; i < winners.size(); i += 2) {
            pairings.add(new Pairing<>(winners.get(i), winners.get(i + 1)));
        }
        return pairings;
    }
}
