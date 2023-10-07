package az.baxtiyargil.concurrencydemo.component;

import lombok.Builder;

/**
 * Sample class for test 'Happens Before' guarantee with 'volatile'
 * <p>
 * <p>
 * The happens-before guarantee guarantees that:
 * <p>
 * Reads from and writes to other variables cannot be reordered to occur after write to a volatile variable,
 * if the reads / writes originally occurred before write to the volatile variable.
 * The reads / writes before write to a volatile variable are guaranteed to "happen before" write to the volatile variable.
 * Notice that it is still possible for e.g. reads / writes of other variables located after write to a volatile to be
 * reordered to occur before that write to the volatile. Just not the other way around.
 * From after to before is allowed, but from before to after is not allowed.
 * <p>
 * <p>
 * Reads from and writes to other variables cannot be reordered to occur before a read of a volatile variable,
 * if the reads / writes originally occurred after the read of the volatile variable.
 * Notice that it is possible for reads of other variables that occur before the read of a volatile variable can be
 * reordered to occur after the read of the volatile. Just not the other way around.
 * From before to after is allowed, but from after to before is not allowed.
 */
public class ValueExchangerVolatile {

    private int valA;
    private int valB;

    /**
     * Turn this variable to normal and volatile to test
     */
    private volatile int valC;

    public void set(Values v) {
        this.valA = v.valA;

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        this.valB = v.valB;
        this.valC = v.valC;
    }

    public void get(Values v) {
        v.valC = this.valC;
        v.valB = this.valB;
        v.valA = this.valA;
        System.out.println(this.valA);
    }

    @Builder
    public static class Values {

        public int valA;
        public int valB;
        public int valC;

    }

    //volatile is Not Always Enough
}
