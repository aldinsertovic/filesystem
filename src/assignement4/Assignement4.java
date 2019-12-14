package assignement4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Assignement4 {

    public static void main(String[] args) throws IOException {

        System.out.println("unesite komandu: ");
        Scanner scan = new Scanner(System.in);

        switch (scan.nextLine()) {
            case "LIST":
                System.out.println("unesite zeljenu putanju: ");
                File list = new File(scan.nextLine());
                if (list.exists() && list.isDirectory()) {
                    String[] array = list.list();
                    for (int i = 0; i < array.length; i++) {
                        System.out.println(array[i]);
                    }
                } else {
                    System.out.println("dali ste nepostojecu putanju");
                }
                break;
            case "INFO":
                System.out.println("unesite zeljenu putanju: ");
                try {
                    File info = new File(scan.nextLine());
                    if (!info.exists() && !info.isDirectory()) {
                        System.out.println("fajl ne postoji");
                        return;
                    }
                    if (info.exists() && info.isDirectory()) {
                        String ime = info.getName();
                        System.out.println(ime);
                        String putanja = info.getPath();
                        System.out.println(putanja);
                        long velicina = info.getTotalSpace();
                        System.out.println(velicina);
                        FileTime datum = Files.readAttributes(info.toPath(), BasicFileAttributes.class).creationTime();
                        System.out.println(datum);
                        Instant instant = Instant.ofEpochMilli(info.lastModified());
                        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
                        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMM yyyy. HH:mm:ss");
                        System.out.println("last modified: " + dateTime.format(dateTimeFormatter));
                    } else {
                        System.out.println("neuspjesna operacija");
                    }
                    break;
                } catch (IOException exc) {
                    System.out.println(exc);
                }
            case "CREATE_DIR":
                System.out.println("unesite putanju gdje cete kreirati folder: ");
                File create = new File(scan.nextLine());
                if (create.exists()) {
                    System.out.println("folder vec postoji");
                }
                if (!create.exists()) {
                    create.mkdir();
                    System.out.println("kreiran fajl pod nazivom: " + create.getName());
                }
                break;

            case "RENAME":
                System.out.println("unesite putanju koju zelite preimenovati: ");
                File oldfile = new File(scan.nextLine());
                System.out.println("unesite novi naziv: ");
                File newfile = new File(scan.nextLine());
                if (!oldfile.exists()) {
                    System.out.println("fajl ne postoji");
                    return;
                }
                if (newfile.exists()) {
                    System.out.println("fajl sa istim imenom vec postoji");
                    return;
                }
                if (oldfile.renameTo(newfile)) {
                    System.out.println("uspjesno ste preimenovali fajl");
                } else {
                    System.out.println("preimenovanje je neuspjesno!!");
                }
                break;
            case "COPY":
                System.out.println("unesi putanju fajla kojeg zelite kopirati: ");
                Path source = Paths.get(scan.nextLine());
                System.out.println("unesi putanju fajla u koji zelite kopirati prethodni kopirani fajl: ");
                Path destination = Paths.get(scan.nextLine());
                try {
                    if (!Files.exists(source)) {
                        System.out.println("fajl ne postoji");
                        return;
                    }
                    if (!Files.exists(destination.getParent())) {
                        System.out.println("lokacija ne postoji");
                        return;
                    }
                    if (Files.exists(destination)) {
                        System.out.println("fajl vec postoji");
                        return;
                    }
                    Files.copy(source, destination);
                    System.out.println("fajl " + source.getFileName() + " je kopiran!!");
                } catch (IOException exc) {
                    System.out.println(exc);
                }
                break;
            case "MOVE":
                System.out.println("unesi putanju fajla kojeg zelite premjestit: ");
                Path source1 = Paths.get(scan.nextLine());
                System.out.println("unesi putanju fajla u koji zelite premjestiti dati fajl ");
                Path destination1 = Paths.get(scan.nextLine());
                try {
                    if (!Files.exists(source1)) {
                        System.out.println("fajl ne postoji");
                        return;
                    }
                    if (!Files.exists(destination1.getParent())) {
                        System.out.println("lokacija ne postoji");
                        return;
                    }
                    if (Files.exists(destination1)) {
                        System.out.println("fajl vec postoji");
                        return;
                    }
                    Files.move(source1, destination1, StandardCopyOption.REPLACE_EXISTING);

                    System.out.println("fajl " + source1.getFileName() + " je premjesten!!");
                } catch (IOException exc) {
                    System.out.println(exc);
                }
                break;
            case "DELETE":
                System.out.println("unesi putanju fajla koji zelite obrisati");
                File file = new File(scan.nextLine());
                if (file.exists()) {
                    file.delete();
                    System.out.println("fajl ste uspjesno obrisali");
                } else {
                    System.out.println("Nemoguce obrisati " + file.getName() + " zato sto " + file.getName() + "ne postoji");
                }
                break;
        }

    }

}
