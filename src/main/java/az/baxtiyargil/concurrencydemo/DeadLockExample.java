package az.baxtiyargil.concurrencydemo;

import java.util.ArrayList;
import java.util.List;

public class DeadLockExample {

    DeadLockExample parent = null;
    List<DeadLockExample> children = new ArrayList<>();

    public synchronized void addChild(DeadLockExample child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
            child.setParentOnly(this);
        }
    }

    public synchronized void addChildOnly(DeadLockExample child) {
        if (!this.children.contains(child)) {
            this.children.add(child);
        }
    }

    public synchronized void setParent(DeadLockExample parent) {
        this.parent = parent;
        parent.addChildOnly(this);
    }

    public synchronized void setParentOnly(DeadLockExample parent) {
        this.parent = parent;
    }

}
