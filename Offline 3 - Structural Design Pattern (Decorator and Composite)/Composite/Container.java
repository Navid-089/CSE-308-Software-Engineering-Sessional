import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public abstract class Container {
    private String name;
    private LocalDateTime creationTime;

    public Container(String name)
    {
        this.name = name;
        this.creationTime = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getCreationTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM, yyyy h:mm a");
        String formattedDateTime = creationTime.format(formatter);
        return formattedDateTime;
    }

    public void printDetails() {
        System.out.println("Name: " + getName());
        System.out.println("Type: " + getType());
        System.out.println("Size: " + getSize() + " kB");
        System.out.println("Directory: \"" + getPath() + "\"");
        System.out.println("Component Count: " + getContainerCount());
        System.out.println("Creation Time: " + getCreationTime());
    }

    abstract public String getPath();
    abstract void setPath(String name);

    abstract public String getType();

    abstract public int getContainerCount();

    abstract public double getSize();
    abstract public void printList();

    abstract void deleteAllChildren();

    abstract public Container getContainer(String name);

    abstract void add(Container container);

    abstract void delete(String name);

    abstract void recursiveDelete(String name);

}
