package storage;

import model.*;

import java.util.ArrayList;

public class Storage {
    private static final ArrayList<Konference> konferencer = new ArrayList<>();
    private static final ArrayList<Deltager> deltagere = new ArrayList<>();
    private static final ArrayList<Bruger> admins = new ArrayList<>();
    private static final ArrayList<Hotel> hoteller = new ArrayList<>();
    private static final ArrayList<Firma> firmaer = new ArrayList<>();


    public static ArrayList<Konference> getKonferencer() {
        return new ArrayList<>(konferencer);
    }

    public static ArrayList<Deltager> getDeltagere() {
        return new ArrayList<>(deltagere);
    }

    public static ArrayList<Bruger> getAdmins() {
        return new ArrayList<>(admins);
    }

    public static ArrayList<Hotel> getHoteller() {
        return new ArrayList<>(hoteller);
    }

    public static ArrayList<Firma> getFirmaer() {
        return new ArrayList<>(firmaer);
    }

    public static void storeKonference(Konference konference) {
        konferencer.add(konference);
    }

    public static void storeDeltager(Deltager deltager) {
        deltagere.add(deltager);
    }

    public static void storeAdmins(Bruger bruger) {
        admins.add(bruger);
    }

    public static void storeHotel(Hotel hotel) {
        hoteller.add(hotel);
    }

    public static void storeFirmaer(Firma firma) {
        firmaer.add(firma);
    }

    public static void deleteKonference(Konference konference) {
        if (konferencer.contains(konference)) {
            konferencer.remove(konference);
        }
    }

    public static void deleteDeltager(Deltager deltager) {
        if (deltagere.contains(deltager)) {
            deltagere.remove(deltager);
        }
    }

    public static void deleteAdmins(Bruger bruger) {
        if (admins.contains(bruger)) {
            admins.remove(bruger);
        }
    }

    public static void deleteHotel(Hotel hotel) {
        if (hoteller.contains(hotel)) {
            hoteller.remove(hotel);
        }
    }

    public static void deleteFirma(Firma firma) {
        if (firmaer.contains(firma)) {
            firmaer.remove(firma);
        }
    }


}
