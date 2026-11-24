// INHERITANCE + POLYMORPHISM: Basketball extends Sport.
// Scoring rule: the side with more total points wins. Overtime is played until a tie
// is broken, so a tied final score is invalid.
public class Basketball extends Sport {

    public Basketball() {
        super("Basketball");
    }

    @Override
    public boolean isTeamSport() {
        return true;
    }

    @Override
    public int determineWinner(int score1, int score2) {
        if (score1 < 0 || score2 < 0) {
            throw new IllegalArgumentException("Points cannot be negative.");
        }
        if (score1 == score2) {
            throw new IllegalArgumentException(
                "Basketball games cannot end in a tie. Enter the final score after overtime.");
        }
        return (score1 > score2) ? 1 : 2;
    }

    @Override
    public String getScoreHint() {
        return "Enter total points scored (including overtime). No ties allowed.";
    }

    @Override
    public String formatScore(int score1, int score2) {
        return score1 + " - " + score2 + " (pts)";
    }
}
