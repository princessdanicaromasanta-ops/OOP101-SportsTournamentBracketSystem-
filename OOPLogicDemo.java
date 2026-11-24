import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Console demo/test of the OOP logic & algorithms. Not the project's Main class.
public class OOPLogicDemo {

    public static void main(String[] args) {
        // 1) POLYMORPHISM: one loop, four different determineWinner() behaviours
        Sport[] sports = { new Soccer(), new Basketball(), new Chess(), new Badminton() };
        int[][] scores = { {3, 1}, {88, 95}, {0, 1}, {2, 1} };
        for (int i = 0; i < sports.length; i++) {
            Sport s = sports[i];
            int w = s.determineWinner(scores[i][0], scores[i][1]);
            System.out.println(s.getSportName() + " (" + (s.isTeamSport() ? "team" : "individual") + ") "
                    + s.formatScore(scores[i][0], scores[i][1]) + " -> side " + w + " wins");
        }

        // 2) EXCEPTION HANDLING: invalid scores are rejected
        System.out.println();
        int[][] bad = { {2, 2}, {70, 70}, {1, 1}, {3, 0} };
        for (int i = 0; i < sports.length; i++) {
            try {
                sports[i].determineWinner(bad[i][0], bad[i][1]);
                System.out.println("ERROR: " + sports[i].getSportName() + " accepted invalid score");
            } catch (IllegalArgumentException e) {
                System.out.println(sports[i].getSportName() + " rejected " + bad[i][0] + "-" + bad[i][1]
                        + ": " + e.getMessage());
            }
        }

        // 3) BRACKET ALGORITHM: 6 participants -> bracket of 8, 2 byes
        System.out.println();
        List<String> players = Arrays.asList("Ana", "Ben", "Cara", "Dan", "Eli", "Fay");
        int n = players.size();
        int rounds = BracketAlgorithm.totalRounds(n);
        System.out.println(n + " participants -> bracket size " + BracketAlgorithm.nextPowerOfTwo(n)
                + ", byes " + BracketAlgorithm.byeCount(n) + ", rounds " + rounds);

        Sport sport = SportFactory.create("Badminton");
        List<BracketAlgorithm.Pairing<String>> round = BracketAlgorithm.generateFirstRound(players);
        for (int r = 1; r <= rounds; r++) {
            System.out.println("-- " + BracketAlgorithm.roundName(r, rounds) + " --");
            List<String> winners = new ArrayList<>();
            for (BracketAlgorithm.Pairing<String> p : round) {
                System.out.println("  " + p);
                if (p.isBye()) {
                    winners.add(p.getFirst());
                } else {
                    // demo rule: side 1 always wins 2-1
                    int w = sport.determineWinner(2, 1);
                    winners.add(w == 1 ? p.getFirst() : p.getSecond());
                }
            }
            if (winners.size() == 1) {
                System.out.println("CHAMPION: " + winners.get(0));
                break;
            }
            round = BracketAlgorithm.generateNextRound(winners);
        }

        // 4) LEADERBOARD ALGORITHM
        System.out.println();
        List<LeaderboardAlgorithm.Standing> table = new ArrayList<>();
        table.add(new LeaderboardAlgorithm.Standing("Ben", 1, 1, 4));
        table.add(new LeaderboardAlgorithm.Standing("Ana", 3, 0, 6));
        table.add(new LeaderboardAlgorithm.Standing("Cara", 1, 1, 4));
        table.add(new LeaderboardAlgorithm.Standing("Dan", 0, 1, 2));
        System.out.print(LeaderboardAlgorithm.format(table));
    }
}
