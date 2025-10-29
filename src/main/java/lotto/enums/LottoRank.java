package lotto.enums;

public enum LottoRank {
    FIFTH(3, false, 5_000, "3개 일치"),
    FOURTH(4, false, 50_000, "4개 일치"),
    THIRD(5, false, 1_500_000, "5개 일치"),
    SECOND(5, true, 30_000_000, "5개 일치, 보너스 볼 일치"),
    FIRST(6, false, 2_000_000_000, "6개 일치"),
    ;

    private final int matchCount;
    private final boolean requireBonus;
    private final int prize;
    private final String description;

    LottoRank(int matchCount, boolean requireBonus, int prize, String message) {
        this.matchCount = matchCount;
        this.requireBonus = requireBonus;
        this.prize = prize;
        this.description = message;
    }

    public String getDescription() {
        return description;
    }

    public int getPrize() {
        return prize;
    }
}
