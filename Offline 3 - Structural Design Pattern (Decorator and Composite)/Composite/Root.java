import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Root extends Container {
    private String path;
    private String type;
    private double size;

    List<Container> containers = new ArrayList<Container>();

    public Root(String name, String path) {
        super(name);
        this.path = path;
        this.type = "Root";
        this.size = 0;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getSize() {
        double size = 0;
        for(Container container : containers)
            size+= container.getSize();
        return size;
    }

    public int getContainerCount() {
        return this.containers.size();
    }

    public void printList() {
        if (getContainerCount() == 0) {
            System.out.println("List is empty!");
            return;
        }
        for (Container container : containers)
            System.out.println(container.getName() + "\t\t" + container.getSize() +
                    "kB\t\t" + container.getCreationTime());
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Container getContainer(String name) {
        for (Container container : containers) {
            if (container.getName().equals(name)) return container;
        }
        return null;
    }


    public void add(Container container) {
        containers.add(container);
        container.setPath(container.getName());
    }

    public void delete(String name) {
        Iterator<Container> iterator = containers.iterator();
        while (iterator.hasNext()) {
            Container container = iterator.next();
            if (name.equals(container.getName())) {
                if (container.getType().equalsIgnoreCase("file") || container.getContainerCount() == 0) {

                    iterator.remove();
                    System.out.println(name + " got deleted.");
                    return;
                }
            }
        }
        System.out.println("Invalid deletion!");
    }

    public void recursiveDelete(String name) {
    }

    void deleteAllChildren()
    {

    }
}
