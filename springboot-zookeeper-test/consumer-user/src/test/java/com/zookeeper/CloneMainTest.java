package com.zookeeper;

public class CloneMainTest {
    public static void main(String[] a) {
        CloneB b1 = new CloneB();
        b1.aInt = 11;
        System.out.println("before clone,b1.aInt = " + b1.aInt);
        System.out.println("before clone,b1.unCA = " + b1.unCA);

        CloneB b2 = (CloneB) b1.clone();
        b2.aInt = 22;
        b1.unCA.setI(1000);

        System.out.println("=================================");
        System.out.println("after clone,b1.aInt = " + b1.aInt);
        System.out.println("after clone,b1.unCA = " + b1.unCA);
        System.out.println("after clone,b1.unCA.i = " + b1.unCA.getI());
        System.out.println("=================================");
        System.out.println("after clone,b2.aInt = " + b2.aInt);
        System.out.println("after clone,b2.unCA = " + b2.unCA);
        System.out.println("after clone,b2.unCA.i = " + b2.unCA.getI());
    }
}
class UnCloneA implements Cloneable {
    private int i;

    public UnCloneA(int ii) {
        i = ii;
    }

    public void doublevalue() {
        i *= 2;
    }

    public String toString() {
        return Integer.toString(i);
    }

    public Object clone() {
        UnCloneA o = null;
        try {
            o = (UnCloneA) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }
}

class CloneB implements Cloneable {
    public int aInt;
    public UnCloneA unCA = new UnCloneA(111);

    public Object clone() {
        CloneB o = null;
        try {
            o = (CloneB) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        o.unCA = (UnCloneA) unCA.clone();
        return o;
    }
}
