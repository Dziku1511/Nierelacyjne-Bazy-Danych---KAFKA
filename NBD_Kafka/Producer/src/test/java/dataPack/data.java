package dataPack;

import model.Book;
import model.Client;
import model.Rent;


import java.time.LocalDateTime;
import java.util.UUID;

public class data {

    public static Client client = new Client( UUID.fromString("a7747f38-9dbd-11ed-a8fc-0242ac120002"),"Jan", "Lesniak");
    public static Client client2 = new Client(UUID.fromString("a774828a-9dbd-11ed-a8fc-0242ac120002"),"Jakub", "Nowak");
    public static Client client3 = new Client(UUID.fromString("a7748442-9dbd-11ed-a8fc-0242ac120002"),"Jon", "Kowal");
    public static Book book1 = new Book( UUID.fromString("a774858c-9dbd-11ed-a8fc-0242ac120002"), "O tym jak zdac studia", "Komedia",
            401, "Maksimus Mega");
    public static Book book2 = new Book( UUID.fromString("a77486a4-9dbd-11ed-a8fc-0242ac120002"), "O systemach operacyjnych slow kilka", "Dramat",
            666, "Linux Winda");
    public static Book book3 = new Book( UUID.fromString("a77487c6-9dbd-11ed-a8fc-0242ac120002"), "Piesn asemblera i dosu", "Poezja",
            102, "Ryszard Kadowski");


    public static Rent rent1 = new Rent(UUID.fromString("a77488de-9dbd-11ed-a8fc-0242ac120002"),LocalDateTime.now(), LocalDateTime.now(),  client, book1);
    public static Rent rent2 = new Rent(UUID.fromString("a7748a32-9dbd-11ed-a8fc-0242ac120002"), LocalDateTime.now(), LocalDateTime.now(),  client2, book2);
    public static Rent rent3 = new Rent(UUID.fromString("a7748fdc-9dbd-11ed-a8fc-0242ac120002"),LocalDateTime.now(), LocalDateTime.now(),  client3, book3);

}

