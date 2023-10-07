package az.baxtiyargil.concurrencydemo.component;

/**
 * Sample class can be used as object for test 'False Sharing'
 */
public class FalseSharingCounter {

    public volatile long count1;
    public volatile long count2;

}
