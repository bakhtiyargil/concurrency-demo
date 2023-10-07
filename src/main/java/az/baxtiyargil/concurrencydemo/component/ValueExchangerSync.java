package az.baxtiyargil.concurrencydemo.component;

import lombok.Builder;

/**
 * Sample class for test 'Happens Before' guarantee with 'synchronized' block
 */
public class ValueExchangerSync {

    private int valA;
    private int valB;
    private int valC;

    /**
     * Remove or add 'synchronized' block for test Happens Before
     *
     * @param v Object of ValueExchangerSync.Values class
     */
    public void set(Values v) {
        this.valA = v.valA;
        this.valB = v.valB;

        synchronized (this) {
            this.valC = v.valC;
        }
    }

    /**
     * Remove or add 'synchronized' block for test Happens Before
     *
     * @param v Object of ValueExchangerSync.Values class
     */
    public void get(Values v) {
        synchronized (this) {
            v.valC = this.valC;
        }
        v.valB = this.valB;
        v.valA = this.valA;
    }

    @Builder
    public static class Values {

        public int valA;
        public int valB;
        public int valC;

    }

}
