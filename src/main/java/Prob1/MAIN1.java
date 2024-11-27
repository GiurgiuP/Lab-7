package Prob1;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class MAIN1
{
    public record Carte(String titlul,String autorul,int anul){}

    public static Map<Integer, Carte> citireCarti(String filePath)
    {
        try
        {
            File file = new File(filePath);
            ObjectMapper mapper = new ObjectMapper();
            Map<Integer, Carte> carti = mapper.readValue(file, new TypeReference<Map<Integer, Carte>>() {});
            return carti;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static void salvareCarti(String filePath, Map<Integer, Carte> carti)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), carti);
            System.out.println("Colectia a fost salvata in " + filePath);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        String filePath = "C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\carti.json";
        Map<Integer, Carte> cartiMap = citireCarti(filePath);
     int opt;
     do
     {
         System.out.println("Meniu");
         System.out.println("1. Afiseaza colectia");
         System.out.println("2. Sterge o carte din colectie");
         System.out.println("3. Adauga o carte in colectie");
         System.out.println("4. Salveaza colectia in JSON");
         System.out.println("5. Creeaza colectia Set<Carte> pentru autorul 'Yuval Noah Harari'");
         System.out.println("6. Afiseaza Set<Carte> ordonat dupa titlu");
         System.out.println("7. Afiseaza cea mai veche carte din Set<Carte>");
         System.out.println("0. Iesire");
         System.out.print("Alege o optiune: ");
         opt=scanner.nextInt();
         switch(opt)
         {
             case 1:cartiMap.forEach((id, carte) -> System.out.println("ID: " + id + ", Carte: " + carte));
                 break;
             case 2:
                 System.out.print("Introdu ID-ul cartii:");
                 int idDeSters = scanner.nextInt();
                 if (cartiMap.remove(idDeSters) != null)
                 {
                     System.out.println("Cartea a fost stearsa");
                 } else
                 {
                     System.out.println("ID-ul nu exista");
                 }
                 break;
             case 3:
                 System.out.print("Introdu ID-ul:");
                 int idNou = scanner.nextInt();
                 scanner.nextLine();
                 System.out.print("Introdu titlul: ");
                 String titlu = scanner.nextLine();
                 System.out.print("Introdu autorul: ");
                 String autor = scanner.nextLine();
                 System.out.print("Introdu anul aparitiei: ");
                 int anAparitie = scanner.nextInt();
                 scanner.nextLine();
                 cartiMap.putIfAbsent(idNou, new Carte(titlu, autor, anAparitie));
                 System.out.println("Cartea a fost adaugata.");
                 break;
             case 4:salvareCarti(filePath, cartiMap);
                 break;
             case 5:
                 Set<Carte> cartiYuval = cartiMap.values().stream()
                     .filter(carte -> "Yuval Noah Harari".equals(carte.autorul()))
                     .collect(Collectors.toSet());
                 System.out.println("Cartile lui Yuval Noah Harari:");
                 cartiYuval.forEach(System.out::println);
                 break;
             case 6:
                 System.out.println("Cartile ordonate dupa titlu:");
                 cartiMap.values().stream()
                         .sorted(Comparator.comparing(Carte::titlul))
                         .forEach(System.out::println);
                 break;
             case 7:
                 Optional<Carte> ceaMaiVecheCarte = cartiMap.values().stream()
                         .min(Comparator.comparingInt(Carte::anul));
                 ceaMaiVecheCarte.ifPresentOrElse(
                         carte -> System.out.println("Cea mai veche carte: " + carte),
                         () -> System.out.println("Nu exista carti in colectie.")
                 );
                 break;
             case 0:exit(0);break;
         }
     }while(opt!=0);
    }
}
