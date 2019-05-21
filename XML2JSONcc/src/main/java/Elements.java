import java.util.LinkedList;
import java.util.List;

public class Elements {
    public String name, text;
    public List<Elements> child;

    public String getText(String text){
        this.text = text;
        return text;
    }
    public Elements(String name){
        this.name = name;
        this.child = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public Elements addChild(String name){
        Elements childNode = new Elements(name);
        this.child.add(childNode);
        return childNode;
    }


}
