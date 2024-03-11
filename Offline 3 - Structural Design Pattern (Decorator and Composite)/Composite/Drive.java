import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Drive extends Container {
    private String path;
    private String type;
    private double size;

    List<Container> containers = new ArrayList<Container>();

    public Drive(String name) {
        super(name);
        this.type = "Drive";
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
        for (Container container : containers)
            size += container.getSize();
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

    public Container getContainer(String name) {
        for (Container container : containers) {
            if (container.getName().equals(name)) return container;

        }
        return null;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void add(Container container) {
        containers.add(container);
        container.setPath(this.getPath() + ":\\" + container.getName());
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

    void deleteAllChildren() {
        for (Container container : containers) {
            if (container.getType().equalsIgnoreCase("Folder"))
                ((Folder) container).deleteAllChildren();
        }
        containers.clear();
    }


    public void recursiveDelete(String name) {
        Container tmpContainer = null;
        for (Container container : containers) {
            if (container.getName().equals(name)) {
                tmpContainer = container;
                break;
            }
        }

        if (tmpContainer != null) {
            if (tmpContainer.getType().equalsIgnoreCase("File")) {
                System.out.println("Warning! Deleting file: " + tmpContainer.getName());
                containers.remove(tmpContainer);
            } else if (tmpContainer.getType().equalsIgnoreCase("Folder")) {
                ((Folder) tmpContainer).deleteAllChildren();
                containers.remove(tmpContainer);
            }
        }
    }
}
