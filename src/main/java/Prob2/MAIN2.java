package Prob2;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

import static java.lang.System.exit;

public class MAIN2
{
    public static void salvareInstrumente(Set<InstumentMuzical> instrumente, String filePath)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(new File(filePath), instrumente);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static Set<InstumentMuzical> incarcaInstrumente(String filePath)
    {
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), new TypeReference<Set<InstumentMuzical>>() {
            });
        }
        catch (IOException e)
        {
            System.out.println("Eroare : " + e.getMessage());
        }
        return new HashSet<>();
    }


    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int opt;
        Set<InstumentMuzical> instrumente = new HashSet<>();
        do
        {
            System.out.println("Meniu");
            System.out.println("1. Creeare coletie");
            System.out.println("2. Salvare coletie in JSON");
            System.out.println("3. Incarcare date din JSON");
            System.out.println("4. Afisare implementare utilizata");
            System.out.println("5. Verificare coletie de duplicate");
            System.out.println("6. Stergere intrument >3000 ron");
            System.out.println("7. Afisare toate chitarile");
            System.out.println("8. Afisare toate tobele");
            System.out.println("9. Afisare chitara cu cele mai multe corzi");
            System.out.println("10.Afisare dateleor tobelor acustice ");
            System.out.println("11.Iesire ");
            System.out.println("OPT:");
            opt = scanner.nextInt();
            switch (opt)
            {
                case 1:
                    System.out.println("Introduceti tipul de instrument (1 - Chitara, 2 - SetTobe): ");
                    int tip = scanner.nextInt();
                    scanner.nextLine();

                    if (tip == 1)
                    {
                        System.out.print("Producator: ");
                        String producatorChitara = scanner.nextLine();
                        System.out.print("Pret: ");
                        double pretChitara = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Tip chitara (1 - ELECTRICA, 2 - ACUSTICA, 3 - CLASICA): ");
                        int tipChitaraInt = scanner.nextInt();
                        Chitara.TipChitara tipChitara = Chitara.TipChitara.values()[tipChitaraInt - 1];
                        System.out.print("Numar corzi: ");
                        int nrCorzi = scanner.nextInt();
                        scanner.nextLine();
                        instrumente.add(new Chitara(producatorChitara, pretChitara, tipChitara, nrCorzi));
                    }
                    else if (tip == 2)
                    {
                        System.out.print("Producator: ");
                        String producatorSetTobe = scanner.nextLine();
                        System.out.print("Pret: ");
                        double pretSetTobe = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Tip set tobe (1 - ELECTRONICE, 2 - ACUSTICE): ");
                        int tipSetTobeInt = scanner.nextInt();
                        SetTobe.TipTobe tipSetTobe = SetTobe.TipTobe.values()[tipSetTobeInt - 1];
                        System.out.print("Numar tobe: ");
                        int nrTobe = scanner.nextInt();
                        System.out.print("Numar cinele: ");
                        int nrCinele = scanner.nextInt();
                        scanner.nextLine();
                        instrumente.add(new SetTobe(producatorSetTobe, pretSetTobe, tipSetTobe, nrTobe, nrCinele));
                    }
                    break;
                case 2:
                    salvareInstrumente(instrumente, "C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                    break;
                case 3:
                    Set<InstumentMuzical> instrumenteIncarcate = incarcaInstrumente("C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                    System.out.println("\nInstrumente incarcate din fisier:");
                    instrumenteIncarcate.forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("\nInterfata utilizata pentru ObjectMapper: com.fasterxml.jackson.databind.ObjectMapper");
                    break;
                case 5:
                    System.out.println("Introduceti instrumentul pentru verificare duplicat:");
                    System.out.println("1. Chitara");
                    System.out.println("2. SetTobe");
                    int tipInstrument = scanner.nextInt();
                    scanner.nextLine();
                    if (tipInstrument == 1)
                    {
                        System.out.print("Producator: ");
                        String producatorChitara = scanner.nextLine();
                        System.out.print("Pret: ");
                        double pretChitara = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Tip chitara (1 - ELECTRICA, 2 - ACUSTICA, 3 - CLASICA): ");
                        int tipChitaraInt = scanner.nextInt();
                        Chitara.TipChitara tipChitara = Chitara.TipChitara.values()[tipChitaraInt - 1];
                        System.out.print("Numar corzi: ");
                        int nrCorzi = scanner.nextInt();
                        scanner.nextLine();
                        Chitara chitaraNoua = new Chitara(producatorChitara, pretChitara, tipChitara, nrCorzi);
                        Set<InstumentMuzical> instrumenteIncarcateChitara = incarcaInstrumente("C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                        boolean exista = instrumenteIncarcateChitara.stream().anyMatch(i -> i instanceof Chitara &&
                                ((Chitara) i).getProducator().equals(chitaraNoua.getProducator()) &&
                                ((Chitara) i).getPret() == chitaraNoua.getPret());
                        if (exista)
                        {
                            System.out.println("Chitara exista deja in fișierul JSON.");
                        }
                        else
                        {
                            instrumente.add(chitaraNoua);
                            System.out.println("Chitara a fost adaugata.");
                        }
                    }
                    else if (tipInstrument == 2)
                    {
                        System.out.print("Producator: ");
                        String producatorSetTobe = scanner.nextLine();
                        System.out.print("Pret: ");
                        double pretSetTobe = scanner.nextDouble();
                        scanner.nextLine();
                        System.out.print("Tip set tobe (1 - ELECTRONICE, 2 - ACUSTICE): ");
                        int tipSetTobeInt = scanner.nextInt();
                        SetTobe.TipTobe tipSetTobe = SetTobe.TipTobe.values()[tipSetTobeInt - 1];
                        System.out.print("Numar tobe: ");
                        int nrTobe = scanner.nextInt();
                        System.out.print("Numar cinele: ");
                        int nrCinele = scanner.nextInt();
                        scanner.nextLine();
                        SetTobe setTobeNou = new SetTobe(producatorSetTobe, pretSetTobe, tipSetTobe, nrTobe, nrCinele);
                        Set<InstumentMuzical> instrumenteIncarcateSetTobe = incarcaInstrumente("C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                        boolean existaSetTobe = instrumenteIncarcateSetTobe.stream().anyMatch(i -> i instanceof SetTobe &&
                                ((SetTobe) i).getProducator().equals(setTobeNou.getProducator()) &&
                                ((SetTobe) i).getPret() == setTobeNou.getPret());
                        if (existaSetTobe)
                        {
                            System.out.println("Setu de tobe exista in fisierul JSON.");
                        }
                        else
                        {
                            instrumente.add(setTobeNou);
                            System.out.println("Setul de tobe a fost adaugat.");
                        }
                    }
                    break;
                case 6:
                    long initialSize = instrumente.size();
                    instrumente.removeIf(instrument -> instrument.getPret() > 3000);
                    if (instrumente.size() < initialSize)
                    {
                        System.out.println("Instrumentele cu pret mai mare de 3000 RON au fost eliminate.");
                    }
                    else
                    {
                        System.out.println("Nu au fost instrumente cu pret mai mare de 3000 RON.");
                    }
                    break;
                case 7:
                    System.out.println("\nToate chitarile:");
                    Set<InstumentMuzical> instrumenteIncarcateChitara = incarcaInstrumente("C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                    instrumenteIncarcateChitara.stream()
                            .filter(instrument -> instrument instanceof Chitara)
                            .forEach(System.out::println);
                    break;
                case 8:
                    System.out.println("\nToate seturile de tobe:");
                    Set<InstumentMuzical> instrumenteIncarcateSetTobe = incarcaInstrumente("C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                    instrumenteIncarcateSetTobe.stream()
                            .filter(instrument -> instrument instanceof SetTobe)
                            .forEach(System.out::println);
                    break;
                case 9:
                    System.out.println("\nChitara cu cele mai multe corzi:");
                    Set<InstumentMuzical> instrumenteIncarcate9 = incarcaInstrumente("C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                    Optional<Chitara> chitaraCuCeleMaiMulteCorzi = instrumenteIncarcate9.stream()
                            .filter(instrument -> instrument instanceof Chitara)
                            .map(instrument -> (Chitara) instrument)
                            .max((chitara1, chitara2) -> Integer.compare(chitara1.getNrCorzi(), chitara2.getNrCorzi()));
                    chitaraCuCeleMaiMulteCorzi.ifPresent(chitara -> System.out.println(chitara));
                    break;
                case 10:
                    System.out.println("\nToate tobele acustice ordonate după numărul de tobe:");
                    Set<InstumentMuzical> instrumenteIncarcate10 = incarcaInstrumente("C:\\Users\\adigi\\IdeaProjects\\Lab7\\src\\main\\resources\\instrumente.json");
                    instrumenteIncarcate10.stream()
                            .filter(instrument -> instrument instanceof SetTobe)
                            .map(instrument -> (SetTobe) instrument)
                            .filter(setTobe -> setTobe.getTip() == SetTobe.TipTobe.ACUSTICE)
                            .sorted((setTobe1, setTobe2) -> Integer.compare(setTobe1.getNrTobe(), setTobe2.getNrTobe()))
                            .forEach(System.out::println);
                    break;
                case 11:
                    exit(0);
                    break;
            }
        } while (opt != 0);
    }
}
