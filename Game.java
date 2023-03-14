import java.util.Scanner;

public class Game {

    private Scanner scan = new Scanner(System.in);


    public void start() {
        System.out.println("Macera Oyununa Hoş Geldiniz !!!");
        System.out.print("Lütfen bir isim giriniz : ");
        String playerName = scan.nextLine();
        Player player = new Player(playerName);
        System.out.println(player.getName() + " Hoş geldin !!!");
        player.selectChar();

        Location location = null;

        while (true) {
            player.printInfo();
            System.out.println("\n++++++++++ Bölgeler ++++++++++\n");
            System.out.println("1 - Güvenli Ev   \t--> Burası sizin için güvenli ev, burada düşman yoktur !");
            System.out.println("2 - Eşya Dükkanı    --> Burada Silah veya Zırh satın alabilirsiniz !");
            System.out.println("3 - Mağara   \t\t--> Ödül [Yemek]  dikkatli ol zombi çıkabilir !!!");
            System.out.println("4 - Orman   \t\t--> Ödül [Odun]   dikkatli ol vampir çıkabilir !!!");
            System.out.println("5 - Nehir   \t\t--> Ödül [Su]     dikkatli ol ayı çıkabilir !!!");
            System.out.println("6 - Maden   \t\t--> Ödül [Altın]  dikkatli ol yılan çıkabilir !!!");
            System.out.println("0 - Çıkış Yap   \t--> Oyunu sonlandır !");

            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = scan.nextInt();

            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (!player.getInventory().isFood()) {
                        location = new Cave(player);
                    } else {
                        location = new CompletedRegion(player);
                    }
                    if (player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()) {
                        System.out.println("\nLütfen oyunu kazanmak için Güvenli Eve gidiniz !!!");
                    }
                    break;
                case 4:
                    if (!player.getInventory().isFirewood()) {
                        location = new Forrest(player);
                    } else {
                        location = new CompletedRegion(player);
                    }
                    if (player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()) {
                        System.out.println("\nLütfen oyunu kazanmak için Güvenli Eve gidiniz !!!");
                    }
                    break;
                case 5:
                    if (!player.getInventory().isWater()) {
                        location = new River(player);
                    } else {
                        location = new CompletedRegion(player);
                    }
                    if (player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()) {
                        System.out.println("\nLütfen oyunu kazanmak için Güvenli Eve gidiniz !!!");
                    }
                    break;
                case 6:
                    location = new Coal(player);

                    break;
                default:
                    location = new WrongLocation(player);


            }

            if (location.getClass().getName().equals("SafeHouse") && player.getInventory().isFood() && player.getInventory().isFirewood() && player.getInventory().isWater()) {

                System.out.println("Tebrikler Oyunu Kazandınız !!!!!!");
                break;

            }

            if (location == null) {
                System.out.println("Oyun bitti ! Tekrar görüşmek üzere ...");
                break;
            }

            if (!location.onLocation()) {
                System.out.println("GAME OVER !!!");
                break;
            }
        }


    }
}