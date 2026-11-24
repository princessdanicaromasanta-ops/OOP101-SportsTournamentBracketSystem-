// ABSTRACTION: callers (Tournament, Main) ask for a sport by name/choice and
// only ever hold a "Sport" reference -- they never depend on a concrete subclass.
public class SportFactory {

    public static final String[] AVAILABLE_SPORTS = {"Soccer", "Basketball", "Chess", "Badminton"};

    private SportFactory() { }

    // Menu choice: 1 = Soccer, 2 = Basketball, 3 = Chess, 4 = Badminton
    public static Sport create(int choice) {
        switch (choice) {
            case 1: return new Soccer();
            case 2: return new Basketball();
            case 3: return new Chess();
            case 4: return new Badminton();
            default:
                throw new IllegalArgumentException("Invalid sport choice: " + choice);
        }
    }

    public static Sport create(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Sport name cannot be null.");
        }
        switch (name.trim().toLowerCase()) {
            case "soccer":     return new Soccer();
            case "basketball": return new Basketball();
            case "chess":      return new Chess();
            case "badminton":  return new Badminton();
            default:
                throw new IllegalArgumentException("Unknown sport: " + name);
        }
    }
}
