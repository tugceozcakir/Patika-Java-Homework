package model;

import java.util.Scanner;

public class Game {
     private Location location;
     private Scanner input = new Scanner(System.in);

     public void start(){
          System.out.println("Macera oyununa hoş geldiniz!");
          System.out.println("Lütfen bir isim giriniz: ");
          String playerName = input.nextLine();

          Player player = new Player(playerName);
          System.out.println("Sayın " + player.getName() + " bu karanlık ve sisli adaya hoş geldiniz.");
          System.out.println("Burada yaşananların hepsi gerçek!");
          player.selectChar();
     }
}