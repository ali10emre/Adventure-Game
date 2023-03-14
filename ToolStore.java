public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("\n----- Mağazaya Hoşgeldiniz ! -----");
        boolean showMenu = true;
        while (showMenu) {
            System.out.println("1 - Silahlar\n" +
                    "2 - Zırhlar\n" +
                    "3 - Çıkış Yap");
            System.out.print("Seçiminiz : ");
            int selectCase = scan.nextInt();
            while (selectCase < 1 || selectCase > Weapon.weapons().length) {
                System.out.println("Geçersiz değer girdiniz , lütfen tekrar giriniz : ");
                selectCase = scan.nextInt();
            }
            switch (selectCase) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Yeniden bekleriz !");
                    showMenu = false;
                    break;

            }
        }
        return true;
    }

    public void printWeapon() {
        System.out.println("----- Silahlar -----\n");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + " - " + w.getName() +
                    " \t<<< Para : " + w.getPrice() +
                    "  +++  Hasar : " + w.getDamage() + " >>>");

        }
        System.out.println("0 - Çıkış Yap");

    }

    public void buyWeapon() {
        System.out.print("Bir silah seçiniz : ");
        int selectWeaponID = scan.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.println("Geçersiz değer girdiniz , lütfen tekrar giriniz : ");
            selectWeaponID = scan.nextInt();
        }

        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponID);

            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır !");
                } else {
                    //Satın alımın gerçekleştiği alan
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);

                }
            }

        }


    }

    public void printArmor() {
        System.out.println("----- Zırhlar -----\n");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + " - " + a.getName() +
                    " \t<<< Para : " + a.getPrice() +
                    "  +++  Zırh : " + a.getBlock() + " >>>");
        }
        System.out.println("0 - Çıkış Yap");
    }

    public void buyArmor() {
        System.out.print("Bir zırh seçiniz : ");
        int selectArmorID = scan.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length) {
            System.out.println("Geçersiz değer girdiniz , lütfen tekrar giriniz : ");
            selectArmorID = scan.nextInt();

        }
        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjById(selectArmorID);

            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()) {
                    System.out.println("Yeterli paranız bulunmamaktadır !");
                } else {
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Kalan paranız : " + this.getPlayer().getMoney());
                }
            }
        }

    }
}