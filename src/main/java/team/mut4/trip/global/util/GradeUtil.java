package team.mut4.trip.global.util;

public class GradeUtil {

    private GradeUtil() {}

    public static int toRank(String grade) {
        return switch (grade) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            case "D" -> 4;
            case "E" -> 5;
            default -> 6;
        };
    }

}
