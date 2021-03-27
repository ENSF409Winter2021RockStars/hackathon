public class Desk extends Furniture{
    boolean legs;
    boolean top;
    boolean drawer;

    public Desk(String id, String type, String legs, String top, String drawer, int price, String manuID){
        super(id, type, price, manuID);
        this.legs = yntf(legs);
        this.top = yntf(top);
        this.drawer = yntf(drawer);
    }

    public void print(){
        System.out.println(id +" "+ type +" "+ legs +" "+ top +" "+ drawer +" "+ price +" "+ manuID);
    }


    public boolean yntf(String arg){
        if(arg.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
} 