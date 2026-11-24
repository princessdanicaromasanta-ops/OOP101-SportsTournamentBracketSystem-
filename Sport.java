// ABSTRACTION: Tournament only knows "a Sport can determine a winner".
// It does not care HOW each sport does it.
public abstract class Sport {
    private String sportName;

    public Sport(String sportName) {
        this.sportName = sportName;
    }

    public String getSportName() { return sportName; }

    public abstract boolean isTeamSport();

    // Returns 1 if side 1 wins, 2 if side 2 wins.
    // Throws IllegalArgumentException if the score is invalid.
    public abstract int determineWinner(int score1, int score2);

    // Short instruction shown when asking for scores.
    public abstract String getScoreHint();

    public String formatScore(int score1, int score2) {
        return score1 + " - " + score2;
    }
}
