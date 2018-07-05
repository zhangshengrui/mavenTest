package ListTest;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ListTest {
    public static void main(String[] args) {
        Tem t = new Tem();
        t.setId(1);
        t.setName("1");

        Tem t1 =new Tem();
        t1.setId(1);
        t1.setName("1");

        Set set = new HashSet();
        set.add(t);
        set.add(t1);

    }

}
class Tem{
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object obj) {
        Tem tem = (Tem)obj;
        return this.id==tem.id&&this.name.equals(tem.name);
    }
}
