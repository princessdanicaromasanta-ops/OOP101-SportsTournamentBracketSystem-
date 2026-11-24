// INHERITANCE + POLYMORPHISM: Chess extends Sport.
// Individual sport. Score is entered as the game result: 1 = win, 0 = loss.
// Valid results are only 1-0 or 0-1 (a drawn game must be replayed / settled by
// tiebreak so that the bracket always has one winner).
public class Chess extends Sport {

    public Chess() {
        super("Chess");
    }

    @Override
    public boolean isTeamSport() {
        return false;
    }

    @Override
    public int determineWinner(int score1, int score2) {
        boolean valid = (score1 == 1 && score2 == 0) || (score1 == 0 && score2 == 1);
        if (!valid) {
            throw new IllegalArgumentException(
                "Invalid chess result. Use 1-0 (side 1 wins) or 0-1 (side 2 wins).");
        }
        return (score1 == 1) ? 1 : 2;
    }

    @Override
    public String getScoreHint() {
        return "Enter 1 for the winner and 0 for the loser (1-0 or 0-1). Draws need a tiebreak.";
    }

    @Override
    public String formatScore(int score1, int score2) {
        return score1 + " - " + score2 + " (game result)";
    }
}
