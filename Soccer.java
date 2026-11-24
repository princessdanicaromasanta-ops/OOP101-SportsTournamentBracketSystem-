// INHERITANCE + POLYMORPHISM: Soccer extends Sport and overrides its abstract methods.
// Scoring rule: the side with more goals wins. A knockout bracket cannot end in a draw,
// so a tied score must be resolved (e.g., penalty shootout) before it is recorded.
public class Soccer extends Sport {

    public Soccer() {
        super("Soccer");
    }

    @Override
    public boolean isTeamSport() {
        return true;
    }

    @Override
    public int determineWinner(int score1, int score2) {
        if (score1 < 0 || score2 < 0) {
            throw new IllegalArgumentException("Goals cannot be negative.");
        }
        if (score1 == score2) {
            throw new IllegalArgumentException(
                "Draw is not allowed in a knockout match. Enter the penalty shootout result.");
        }
        return (score1 > score2) ? 1 : 2;
    }

    @Override
    public String getScoreHint() {
        return "Enter goals scored (no draws; include penalty shootout goals if tied).";
    }

    @Override
    public String formatScore(int score1, int score2) {
        return score1 + " - " + score2 + " (goals)";
    }
}
