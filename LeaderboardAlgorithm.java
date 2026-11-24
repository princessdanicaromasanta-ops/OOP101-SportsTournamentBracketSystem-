import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

// ALGORITHMS: leaderboard ranking.
// Ranking order: more wins -> more points -> fewer losses -> name (A-Z).
// Works on a lightweight Standing so it can be fed from Athlete or Team stats.
public class LeaderboardAlgorithm {

    private LeaderboardAlgorithm() { }

    public static class Standing {
        private final String name;
        private int wins;
        private int losses;
        private int points;

        public Standing(String name, int wins, int losses, int points) {
            this.name = name;
            this.wins = wins;
            this.losses = losses;
            this.points = points;
        }

        public String getName() { return name; }
        public int getWins()    { return wins; }
        public int getLosses()  { return losses; }
        public int getPoints()  { return points; }

        public void addWin()           { wins++; }
        public void addLoss()          { losses++; }
        public void addPoints(int pts) {
            if (pts < 0) throw new IllegalArgumentException("Points cannot be negative.");
            points += pts;
        }
    }

    private static final Comparator<Standing> RANKING =
        Comparator.comparingInt(Standing::getWins).reversed()
            .thenComparing(Comparator.comparingInt(Standing::getPoints).reversed())
            .thenComparingInt(Standing::getLosses)
            .thenComparing(Standing::getName, String.CASE_INSENSITIVE_ORDER);

    public static List<Standing> rank(List<Standing> standings) {
        List<Standing> sorted = new ArrayList<>(standings);
        Collections.sort(sorted, RANKING);
        return sorted;
    }

    // Competition ranking: equal records share a rank (1, 2, 2, 4).
    public static String format(List<Standing> standings) {
        List<Standing> sorted = rank(standings);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-5s %-20s %-5s %-7s %-6s%n", "Rank", "Name", "Wins", "Losses", "Points"));
        int rank = 0;
        Standing prev = null;
        for (int i = 0; i < sorted.size(); i++) {
            Standing s = sorted.get(i);
            boolean tied = prev != null && s.getWins() == prev.getWins()
                    && s.getPoints() == prev.getPoints() && s.getLosses() == prev.getLosses();
            if (!tied) rank = i + 1;
            sb.append(String.format("%-5d %-20s %-5d %-7d %-6d%n",
                    rank, s.getName(), s.getWins(), s.getLosses(), s.getPoints()));
            prev = s;
        }
        return sb.toString();
    }
}
