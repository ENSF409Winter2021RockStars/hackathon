public abstract class Furniture{
    String id;
    String type;
    int price;
    String manuID;

    public Furniture(String id, String type, int price, String manuID){
        this.id = id;
        this.type = type;
        this.price = price;
        this.manuID = manuID;
    }

    abstract void print();

    private abstract boolean yntf(String arg);
}