public class Filing extends Furniture{
    boolean rails;
    boolean drawers;
    boolean cabinet;

    public Filing(String id, String type, String rails, String drawers, String cabinet, int price, String manuID){
        super(id, type, price, manuID);
        this.rails = yntf(rails);
        this.drawers = yntf(drawers);
        this.cabinet = yntf(cabinet);
    }

    public void print(){
        System.out.println(id +" "+ type +" "+ rails +" "+ drawers +" "+ cabinet +" "+ price +" "+ manuID);
    }


    public boolean yntf(String arg){
        if(arg.equals("Y")){
            return true;
        } else{
            return false;
        }
    }
} 