package top.xiongmingcai.bbs.clone;

import java.io.*;

public class Dancer implements Cloneable, Serializable {
    private String name;
    private Dancer partner;

    public Dancer(String name) {
        this.name = name;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Dancer dancer = new Dancer("刘明");
        Dancer dancer1 = new Dancer("吴彦祖");
        dancer.setPartner(dancer1);
//        shallowClone(dancer, dancer1);
        Dancer clone = dancer.deepClone();
        System.out.println("# =============深复制：对象、变量值和引用对象都进行复制 ================ ");
        System.out.println("dancer1 = " + dancer1.hashCode());
        System.out.println(dancer1);

        System.out.println("dancer1 = " + clone.getPartner().hashCode());
        System.out.println("clone = " + clone);
        System.out.println("# =============深复制：对象、变量值和引用对象都进行复制，================ ");
        dancer1.setName("吴彦祖好帅");
        System.out.println(dancer1);
        System.out.println("clone = " + clone);
    }

    private static void shallowClone(Dancer dancer, Dancer dancer1) throws CloneNotSupportedException {
        System.out.println(" =============浅复制：只对对象和和变量值进行复制，引用对象的地址不变================ ");
        System.out.println("dancer1 = " + dancer1.hashCode());
        System.out.println(dancer1);
        Dancer clone = (Dancer) dancer.clone();

        System.out.println("dancer1 = " + clone.getPartner().hashCode());
        System.out.println("clone = " + clone);

        System.out.println(" =============浅复制：引用对象值被更改，拷贝对象的引用对象也发生变化================ ");
        dancer1.setName("吴彦祖好帅");
        System.out.println(dancer1);
        System.out.println("clone = " + clone);

        System.out.println(" =============浅复制：对象和和变量改变，拷贝对象的不变================ ");
        dancer.setName("刘明好美");
        System.out.println("clone = " + clone);

    }

    public Dancer deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);
        //字节数组还原成对象
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Dancer) ois.readObject();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dancer getPartner() {
        return partner;
    }

    public void setPartner(Dancer partner) {
        this.partner = partner;
    }

    @Override
    public String toString() {
        return "Dancer{" +
                "name='" + name + '\'' +
                ", partner=" + partner +
                '}';
    }
}

