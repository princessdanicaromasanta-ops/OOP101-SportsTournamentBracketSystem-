public class Soccer extends Sport {
    public Soccer() { super("Soccer"); }

    @Override
    public boolean isTeamSport() { return true; }

    @Override
    public int determineWinner(int s1, int s2) {
        if (s1 == s2) {
            throw new IllegalArgumentException(
                "Draws are not allowed in knockout. Enter the score after the penalty shootout (e.g. 4 3).");
        }
        return s1 > s2 ? 1 : 2;
    }

    @Override
    public String getScoreHint() { return "goals for each team (no draws; include penalties)"; }
}