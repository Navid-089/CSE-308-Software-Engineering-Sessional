import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Folder extends Container {
    private String path;
    private String type;
    private double size;

    List<Container> containers = new ArrayList<Container>();

    public Folder(String name) {
        super(name);
        this.type = "Folder";
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

    public void setPath(String path) {
        this.path = path;
    }

    public void add(Container container) {
        containers.add(container);
        container.setPath(this.getPath() + ":\\" + container.getName());
    }

    public Container getContainer(String name) {
        for (Container container : containers) {
            if (container.getName().equals(name)) return container;

        }
        return null;
    }


    public void delete(String name) {
        Iterator<Container> iterator = containers.iterator();
        while (iterator.hasNext()) {
            Container container = iterator.next();
            if (name.equals(container.getName())) {
                if (container.getType().equalsIgnoreCase("file") || container.getContainerCount() == 0) {
                    this.size -= container.getSize();
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
            if(container.getType().equalsIgnoreCase("File"))
                System.out.println("Warning! Deleting file: " + container.getName()
                        + " inside the folder!");
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
