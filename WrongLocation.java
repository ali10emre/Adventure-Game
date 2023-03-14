public class WrongLocation extends NormalLoc {
    public WrongLocation(Player player) {
        super(player, "Hatalı girdi");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Lütfen geçerli bir bölge giriniz !");
        return true;
    }
}
