public class Basketball extends Sport {
    public Basketball() {
        super("Basketball");
    }

    @Override
    public boolean isTeamSport() {
        return true;
    }

    @Override
    public int determineWinner(int s1, int s2) {
        if (s1 == s2) {
            throw new IllegalArgumentException(
                "Ties are not allowed in basketball. Enter the final score after overtime (e.g. 95 90).");
        }
        return s1 > s2 ? 1 : 2;
    }

    @Override
    public String getScoreHint() {
        return "points for each team (no ties; include overtime)";
    }
}

