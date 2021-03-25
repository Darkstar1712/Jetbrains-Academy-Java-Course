public class Ship {
    private String name;
    private int size;
    private String[] startCoord;
    private String[] endCoord;
    public int hitCount = 0;

    Ship(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public void setStartCoord(String[] coord) {
        this.startCoord = coord;
    }

    public String getStartCoord(int i) {
        if (i == 0) {
            return this.startCoord[0];
        } else {
            return this.startCoord[1];
        }
    }

    public void setEndCoord(String[] coord) {
        this.endCoord = coord;
    }

    public String getEndCoord(int i) {
        if (i == 0) {
            return this.endCoord[0];
        } else {
            return this.endCoord[1];
        }
    }

    public void increaseHitCount() {
        this.hitCount += 1;
    }

    public int getHitCount() {
        return this.hitCount;
    }
}