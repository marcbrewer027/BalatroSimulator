package Scoring;

import Cards.PlayingCard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Evaluates a set of playing cards and returns the best Balatro hand rank.
 * Supports 1-5 cards; with 5 cards considers standard poker hands plus Balatro-specific (Flush House, etc.).
 */
public final class HandEvaluator {

    private HandEvaluator() {}

    /**
     * Evaluates the given cards and returns the best hand rank.
     * @param cards list of 1-5 playing cards (typically the "played" cards)
     * @return the best HandRank for this set
     */
    public static HandRank evaluate(List<PlayingCard> cards) {
        if (cards == null || cards.isEmpty()) return HandRank.HIGH_CARD;
        List<PlayingCard> list = new ArrayList<>(cards);
        if (list.size() > 5) list = list.subList(0, 5);
        int n = list.size();

        if (n == 1) return HandRank.HIGH_CARD;

        List<Integer> ranks = list.stream().map(PlayingCard::getRank).sorted().toList();
        Map<Integer, Long> rankCounts = list.stream().collect(Collectors.groupingBy(PlayingCard::getRank, Collectors.counting()));
        boolean flush = isFlush(list);
        boolean straight = isStraight(list);

        if (n == 2) {
            if (rankCounts.containsValue(2L)) return HandRank.PAIR;
            return HandRank.HIGH_CARD;
        }

        if (n == 3) {
            if (rankCounts.containsValue(3L)) return HandRank.THREE_OF_A_KIND;
            if (straight) return HandRank.STRAIGHT;
            if (flush) return HandRank.FLUSH;
            if (rankCounts.size() == 2) return HandRank.TWO_PAIR; // 2+1 as two pair? Balatro: Two Pair needs 4 cards. So 3 cards = three of a kind, straight, flush, or high. So no two pair for 3.
            return HandRank.HIGH_CARD;
        }

        if (n == 4) {
            if (rankCounts.containsValue(4L)) return HandRank.FOUR_OF_A_KIND;
            if (rankCounts.containsValue(2L) && rankCounts.size() == 2) return HandRank.TWO_PAIR;
            if (straight) return HandRank.STRAIGHT;
            if (flush) return HandRank.FLUSH;
            if (rankCounts.containsValue(3L)) return HandRank.THREE_OF_A_KIND;
            if (rankCounts.containsValue(2L)) return HandRank.PAIR;
            return HandRank.HIGH_CARD;
        }

        // n == 5
        if (flush && isRoyal(list)) return HandRank.ROYAL_FLUSH;
        if (flush && straight) return HandRank.STRAIGHT_FLUSH;
        if (rankCounts.containsValue(5L)) return HandRank.FIVE_OF_A_KIND;
        if (flush && rankCounts.containsValue(3L) && rankCounts.containsValue(2L)) return HandRank.FLUSH_HOUSE;
        if (flush && rankCounts.containsValue(4L)) return HandRank.FLUSH_FIVE;
        if (rankCounts.containsValue(4L)) return HandRank.FOUR_OF_A_KIND;
        if (rankCounts.containsValue(3L) && rankCounts.containsValue(2L)) return HandRank.FULL_HOUSE;
        if (flush) return HandRank.FLUSH;
        if (straight) return HandRank.STRAIGHT;
        if (rankCounts.containsValue(3L)) return HandRank.THREE_OF_A_KIND;
        if (rankCounts.size() == 3 && rankCounts.containsValue(2L)) return HandRank.TWO_PAIR; // 2+2+1
        if (rankCounts.containsValue(2L)) return HandRank.PAIR;
        return HandRank.HIGH_CARD;
    }

    private static boolean isFlush(List<PlayingCard> cards) {
        if (cards.size() < 2) return true;
        PlayingCard.Suit first = cards.get(0).getSuit();
        return cards.stream().allMatch(c -> c.getSuit() == first);
    }

    private static boolean isStraight(List<PlayingCard> cards) {
        if (cards.size() < 2) return false;
        List<Integer> r = cards.stream().map(PlayingCard::getRank).distinct().sorted().toList();
        if (r.size() != cards.size()) return false; // duplicate ranks
        int min = r.get(0);
        int max = r.get(r.size() - 1);
        if (max - min == cards.size() - 1) return true;
        // A-2-3-4-5 low straight
        if (cards.size() == 5 && max == 14 && r.contains(2) && r.contains(3) && r.contains(4) && r.contains(5)) return true;
        return false;
    }

    private static boolean isRoyal(List<PlayingCard> cards) {
        if (cards.size() != 5) return false;
        List<Integer> r = cards.stream().map(PlayingCard::getRank).sorted().toList();
        return r.equals(List.of(10, 11, 12, 13, 14));
    }
}
