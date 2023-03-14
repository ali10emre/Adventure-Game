import java.util.Random;
import java.util.Scanner;


public class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    private Scanner scan = new Scanner(System.in);


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    public boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();

        System.out.println("\nŞu an buradasınız : " + this.getName());
        System.out.println("Dikkatli ol ! Burada " + obsNumber + " tane " + this.getObstacle().getName() + " yaşıyor.");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = scan.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(obsNumber)) {

            System.out.println(this.getName() + " tüm düşmanları yendiniz !");

            if (this.award.equals("Food") && this.getPlayer().getInventory().isFood() == false) {
                System.out.println(this.getAward() + " kazandınız !!!");
                getPlayer().getInventory().setFood(true);
            } else if (this.award.equals("Firewood") && this.getPlayer().getInventory().isFirewood() == false) {
                System.out.println(this.getAward() + " kazandınız !!!");
                getPlayer().getInventory().setFirewood(true);
            } else if (this.award.equals("Water") && this.getPlayer().getInventory().isWater() == false) {
                System.out.println(this.getAward() + " kazandınız !!!");
                getPlayer().getInventory().setWater(true);
            } else if (this.award.equals("Gold") && this.getPlayer().getInventory().isGold() == false) {
                System.out.println(this.getAward() + " kazandınız !!!");
                getPlayer().getInventory().setGold(true);
            }

            return true;

        }


        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz !!!!");
            return false;
        }
        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOrjinalHealt());
            playerStats();
            if (this.getName().equals("Maden")) {
                SnakeStats(i);
            } else {
                obstacleStats(i);
            }
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat = scan.nextLine().toUpperCase();
                if (isStart()) {
                    if (selectCombat.equals("V")) {

                        System.out.println("Siz vurdunuz !!!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println("\nCanavar size vurdu !!!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }

                    } else {
                        return false;
                    }
                } else {
                    if (this.getObstacle().getHealth() > 0) {
                        System.out.println("\nCanavar size vurdu !!!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obstacleDamage < 0) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                        afterHit();
                    }
                    if (selectCombat.equals("V")) {

                        System.out.println("Siz vurdunuz !!!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();


                    } else {
                        return false;
                    }

                }


            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("Düşmanı yendiniz !");

                if (this.getName().equals("Maden")) {
                    double random = Math.random() * 100;

                    if (random <= 15) {  //Silah kazanma olasılığı %15 olarak ayarladım.
                        double randomWeapon = Math.random() * 100;
                        if (randomWeapon <= 20) {//Tüfek kazanma olasılığı %20.
                            Weapon weapon = Weapon.getWeaponObjById(3);
                            this.getPlayer().getInventory().setWeapon(weapon);
                            System.out.println("Tüfek kazandınız !!!");
                            System.out.println("Yeni hasarınız : " + this.getPlayer().getTotalDamage());
                        }
                        if (randomWeapon > 20 && randomWeapon <= 50) {//Kılıç kazanma olasılığı %30.
                            Weapon weapon = Weapon.getWeaponObjById(2);
                            this.getPlayer().getInventory().setWeapon(weapon);
                            System.out.println("Kılıç kazandınız !!!");
                            System.out.println("Yeni hasarınız : " + this.getPlayer().getTotalDamage());
                        }
                        if (randomWeapon > 50) {//Tabanca kazanma olasılığı %50.
                            Weapon weapon = Weapon.getWeaponObjById(1);
                            this.getPlayer().getInventory().setWeapon(weapon);
                            System.out.println("Tabanca kazandınız !!!");
                            System.out.println("Yeni hasarınız : " + this.getPlayer().getTotalDamage());
                        }

                    }
                    if (random > 15 && random <= 30) { //Zırh kazanma olasılığı %15 olarak ayarladımç
                        double randomArmor = Math.random() * 100;
                        if (randomArmor <= 20) {//Ağır zırh kazanma olasılığı %20.
                            Armor armor = Armor.getArmorObjById(3);
                            this.getPlayer().getInventory().setArmor(armor);
                            System.out.println("Ağır Zırh kazandınız !!!");
                            System.out.println("Yeni bloklama değeriniz : " + this.getPlayer().getInventory().getArmor().getBlock());
                        }
                        if (randomArmor > 20 && randomArmor <= 50) {//Orta zırh kazanma olasılığı %30.
                            Armor armor = Armor.getArmorObjById(2);
                            this.getPlayer().getInventory().setArmor(armor);
                            System.out.println("Orta Zırh kazandınız !!!");
                            System.out.println("Yeni bloklama değeriniz : " + this.getPlayer().getInventory().getArmor().getBlock());
                        }
                        if (randomArmor > 50) { //Hafif zırh kazanma olasılığı %50.
                            Armor armor = Armor.getArmorObjById(1);
                            this.getPlayer().getInventory().setArmor(armor);
                            System.out.println("Hafif Zırh kazandınız !!!");
                            System.out.println("Yeni bloklama değeriniz : " + this.getPlayer().getInventory().getArmor().getBlock());
                        }

                    }
                    if (random > 30 && random <= 55) {//Para kazanma olasılığı %25 olarak ayarladım
                        double randomMoney = Math.random() * 100;
                        if (randomMoney <= 20) {//10 para kazanma olasılığı %20.
                            System.out.println("10 para kazandınız !");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                            System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
                        }
                        if (randomMoney > 20 && randomMoney <= 50) {//5 para kazanma olasılığı %30.
                            System.out.println("5 para kazandınız !");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                            System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
                        }
                        if (randomMoney > 50) {//1 para kazanma olasılığı %50.
                            System.out.println("1 para kazandınız !");
                            this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                            System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
                        }
                    }
                    if (random > 55) { //Hiç birşey kazanamama olasılığını %45 olarak ayarladım.
                        System.out.println("Hiç birşey kazanamadınız :(:(:(");
                    }

                } else {
                    System.out.println(this.getObstacle().getAward() + " para kazandınız !");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                    System.out.println("Güncel Paranız : " + this.getPlayer().getMoney());
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public void afterHit() {
        System.out.println("\nCanınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Canı " + this.getObstacle().getHealth());
        System.out.println("-------------------");
    }

    public void playerStats() {
        System.out.println("\nOyuncu Değerleri");
        System.out.println("---------------------");
        System.out.println("Sağlık : " + this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : " + this.getPlayer().getTotalDamage());
        System.out.println("Para : " + this.getPlayer().getMoney());

    }

    public void obstacleStats(int i) {
        System.out.println("\n" + i + "." + this.getObstacle().getName() + " Değerleri");
        System.out.println("-------------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());

    }

    public void SnakeStats(int i) {
        System.out.println("\n" + i + "." + this.getObstacle().getName() + " Değerleri");
        System.out.println("-------------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + "Silah, Zırh veya Para kazanma ihtimali !!!");
    }


    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle()) + 1; // Buradaki +1 mantığı şöyle bu random nesnesi örneğin r.nextInt(3) olursa  0-1-2 gibi sayıları üretiyor ben +1 ekleyerek 1-2-3 sayılarından birini üretmesini sağladım, çünkü 0 sayıda canavarım olsun istemiyorum.
    }

    public boolean isStart() { // Burada ilk olarak kimin vuracağını %50 ihtimalli olarak belirledim.
        double randomNumber = Math.random() * 100;
        return randomNumber <= 50;
    }


    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
