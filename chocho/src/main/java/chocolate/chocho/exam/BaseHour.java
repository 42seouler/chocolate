package chocolate.chocho.exam;

import lombok.Getter;

@Getter
public enum BaseHour {

    ZERO("00", 0),
    ONE("01", 1),
    TWO("02", 2),
    THREE("03", 3),
    FOUR("04", 4),
    FIVE("05", 5),
    SIX("06", 6),
    SEVEN("07", 7),
    EIGHT("08", 8),
    NINE("09", 9),
    TEN("10", 10),
    ELEVEN("11", 11),
    TWELVE("12", 12),
    THIRTEEN("13",13),
    FOURTEEN("14", 14),
    FIFTEEN("15", 15),
    SIXTEEN("16", 16),
    SEVENTEEN("17", 17),
    EIGHTEEN("18", 18),
    NINETEEN("19", 19),
    TWENTY("20", 21),
    TWENTYONE("21", 21),
    TWENTYTWO("22", 22),
    TWENTTHREE("23", 23),
    TWENTYFOUR("24", 24),
    ;

    private String expressString;
    private int expressNum;

    BaseHour(String expressString, int expressNum) {
        this.expressString = expressString;
        this.expressNum = expressNum;
    }
}
