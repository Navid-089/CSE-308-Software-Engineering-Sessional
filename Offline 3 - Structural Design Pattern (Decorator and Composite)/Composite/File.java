import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class File extends Container {

    private String path;
    private String type;

    private double size;

    public File(String name, double size) {
        super(name);
        this.size = size;
        this.type = "File";
    }

    @Override
    public String getPath() {
        return path;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getSize() {
        return size;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    @Override

    public int getContainerCount() {
        return 0;
    }

    public void printList() {
    }

    public Container getContainer(String name)
    {
        return null;
    }

    void deleteAllChildren(){}

    public void add(Container container) {
    }

    public void delete(String name) {
    }

    public void recursiveDelete(String name) {
    }
}
