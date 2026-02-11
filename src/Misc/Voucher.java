package Misc;

/**
 * Vouchers grant permanent effects (e.g. shop discount, extra slot).
 */
public class Voucher {

    public enum Type {
        /** No effect */
        NONE,
        /** -$1 to all pack prices */
        DISCOUNT,
        /** +1 joker slot */
        JOKER_SLOT,
        /** +1 consumable slot */
        CONSUMABLE_SLOT,
        /** +1 hand per round */
        EXTRA_HAND,
        /** +1 discard per round */
        EXTRA_DISCARD
    }

    private final Type type;

    public Voucher() {
        this(Type.NONE);
    }

    public Voucher(Type type) {
        this.type = type != null ? type : Type.NONE;
    }

    public Type getType() {
        return type;
    }

    /**
     * Apply this voucher's effect to the given pack price (e.g. DISCOUNT reduces it).
     */
    public int applyToPrice(int basePrice) {
        if (type == Type.DISCOUNT) return Math.max(0, basePrice - 1);
        return basePrice;
    }

    public void delete() {}
}
