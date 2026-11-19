import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }

    static Sport chooseSport() {
        System.out.println("Choose a sport:\n 1. Soccer\n 2. Basketball\n 3. Chess\n 4. Badminton");
        while (true) {
            int c = readInt("Choice: ");
            // POLYMORPHISM: all are stored as Sport.
            switch (c) {
                case 1: return new Soccer();
                case 2: return new Basketball();
                case 3: return new Chess();
                case 4: return new Badminton();
                default: System.out.println("Pick 1 to 4.");
            }
        }
    }

    static void addParticipants(Tournament t) {
        boolean isTeam = t.getSport().isTeamSport();
        System.out.println("\nEnter " + (isTeam ? "team" : "player") + " names one at a time. Type 'done' to finish (min 2).");
        int number = 1;
        while (true) {
            System.out.print((isTeam ? "Team" : "Player") + " " + number + ": ");
            String name = sc.nextLine().trim();
            if (name.equalsIgnoreCase("done")) {
                if (t.getParticipantCount() < 2) {
                    System.out.println("Add at least 2 first.");
                    continue;
                }
                break;
            }
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                continue;
            }
            if (isTeam) {
                Team team = new Team(name);
                System.out.print("  Members (comma-separated, or press Enter to skip): ");
                String line = sc.nextLine().trim();
                if (!line.isEmpty()) {
                    int n = 1;
                    for (String m : line.split(",")) team.addMember(new Athlete(m.trim(), n++));
                }
                t.addParticipant(team);
            } else {
                t.addParticipant(new Athlete(name, number));
            }
            number++;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== SPORTS TOURNAMENT BRACKET SYSTEM ===");
        System.out.print("Tournament name: ");
        String name = sc.nextLine().trim();
        Sport sport = chooseSport();

        Tournament t = new Tournament(name, sport);
        addParticipants(t);
        t.generateBracket();

        while (t.getChampion() == null) {
            t.displayBracket();
            System.out.println("\nEnter result for a pending match in the current round.");
            System.out.println("Scores: " + sport.getScoreHint());
            try {
                int matchNo = readInt("Match number: ");
                int s1 = readInt("Score of first side: ");
                int s2 = readInt("Score of second side: ");
                t.updateMatch(matchNo, s1, s2);
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        t.displayBracket();
        System.out.println("\nCHAMPION: " + Match.nameOf(t.getChampion()));
        t.displayLeaderboard();
        System.out.println("\nThank you for using the system!");
    }
}
