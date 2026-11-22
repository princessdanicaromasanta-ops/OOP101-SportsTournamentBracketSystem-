public abstract class Sport {
    private String sportName;

    public Sport(String sportName) {
        this.sportName = sportName;
    }

    public String getSportName() {
        return sportName;
    }

    public String formatScore(int s1, int s2) {
        return s1 + " - " + s2;
    }

    public abstract boolean isTeamSport();
    public abstract int determineWinner(int s1, int s2);
    public abstract String getScoreHint();
}


