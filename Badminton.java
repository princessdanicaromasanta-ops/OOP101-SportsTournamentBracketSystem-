// INHERITANCE + POLYMORPHISM: Badminton extends Sport.
// Individual sport played as best-of-3 games. Score is the number of GAMES won.
// Valid final results: 2-0, 2-1, 0-2, 1-2.
public class Badminton extends Sport {

    private static final int GAMES_TO_WIN = 2;

    public Badminton() {
        super("Badminton");
    }

    @Override
    public boolean isTeamSport() {
        return false;
    }

    @Override
    public int determineWinner(int score1, int score2) {
        if (score1 < 0 || score2 < 0) {
            throw new IllegalArgumentException("Games won cannot be negative.");
        }
        boolean side1Won = score1 == GAMES_TO_WIN && score2 < GAMES_TO_WIN;
        boolean side2Won = score2 == GAMES_TO_WIN && score1 < GAMES_TO_WIN;
        if (!side1Won && !side2Won) {
            throw new IllegalArgumentException(
                "Invalid badminton result. Best of 3 games: valid scores are 2-0, 2-1, 0-2, 1-2.");
        }
        return side1Won ? 1 : 2;
    }

    @Override
    public String getScoreHint() {
        return "Enter games won (best of 3). Valid: 2-0, 2-1, 0-2, 1-2.";
    }

    @Override
    public String formatScore(int score1, int score2) {
        return score1 + " - " + score2 + " (games)";
    }
}
