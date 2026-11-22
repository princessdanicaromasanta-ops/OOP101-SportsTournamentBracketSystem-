public class SoccerTest {
    public static void main(String[] args) {
        Sport s = new Soccer();
        System.out.println("Sport: " + s.getSportName());
        System.out.println("Team sport: " + s.isTeamSport());
        System.out.println("Hint: " + s.getScoreHint());

        System.out.println("Score " + s.formatScore(3, 1) + " -> winner: player " + s.determineWinner(3, 1));
        System.out.println("Score " + s.formatScore(2, 4) + " -> winner: player " + s.determineWinner(2, 4));

        try {
            s.determineWinner(2, 2);
        } catch (IllegalArgumentException e) {
            System.out.println("Draw rejected: " + e.getMessage());
        }
    }
}