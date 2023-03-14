public class CompletedRegion extends NormalLoc{
    public CompletedRegion(Player player) {
        super(player, "Tamamlanan Bölge");
    }

    @Override
    public boolean onLocation() {
        System.out.println("Bölge Tamamlandı Lütfen Başka Bölge Seçiniz !!!");
        return true;
    }
}
