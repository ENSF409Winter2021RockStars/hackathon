public class Chair extends Furniture{

    boolean legs;
    boolean arms;
    boolean seat;
    boolean cushion;

    public Chair(String id, String type, String legs, String arms, String seat, String cushion, int price, String manuID){
        super(id, type, price, manuID);
        this.legs = yntf(legs);
        this.arms = yntf(arms);
        this.seat = yntf(seat);
        this.cushion = yntf(cushion);
    }

    public void print(){
        System.out.println(id +" "+ type +" "+ legs +" "+ arms +" "+ seat +" "+ cushion +" "+ price +" "+ manuID);
    }

    public boolean yntf(String arg){
        if(arg.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
    
}

