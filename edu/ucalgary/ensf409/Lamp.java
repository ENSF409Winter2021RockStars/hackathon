public class Lamp extends Furniture{
    boolean base;
    boolean bulb;

    public Lamp(String id, String type, String base, String bulb, int price, String manuID){
        super(id, type, price, manuID);
        this.base = yntf(base);
        this.bulb = yntf(bulb);
    }

    public void print(){
        System.out.println(id +" "+ type +" "+ bulb +" "+ base + " " + price +" "+ manuID);
    }


    public boolean yntf(String arg){
        if(arg.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
}