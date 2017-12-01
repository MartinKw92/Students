import java.time.Duration;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

enum Status {
    ACTIVE, INACTIVE
}



public class Student {

    private String imie;
    private String nazwisko;
    private int wiek;
    private String uczelnia;
    private long rok;
    private double srednia;
    private Status status;
    private LocalDate datarozpoczecia;

    public Student(String imie, String nazwisko, String uczelnia, int wiek, double srednia, Status status, LocalDate datarozpoczecia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.uczelnia = uczelnia;
        this.wiek = wiek;
        this.srednia = srednia;
        this.status = status;
        this.datarozpoczecia = datarozpoczecia;
        this.rok = Duration.between(LocalDate.now().atStartOfDay(), datarozpoczecia.atStartOfDay()).toDays()/365+1;

    }

    @Override
    public String toString() {
        return "Student{" +
                "imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                ", wiek=" + wiek +
                ", uczelnia='" + uczelnia + '\'' +
                ", rok=" + rok +
                ", srednia=" + srednia +
                ", status=" + status +
                '}';
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public int getWiek() {
        return wiek;
    }

    public void setWiek(int wiek) {
        this.wiek = wiek;
    }

    public String getUczelnia() {
        return uczelnia;
    }

    public void setUczelnia(String uczelnia) {
        this.uczelnia = uczelnia;
    }

    public long getRok() {
        return rok;
    }

    public void setRok(long rok) {
        this.rok = rok;
    }

    public double getSrednia() {
        return srednia;
    }

    public void setSrednia(double srednia) {
        this.srednia = srednia;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getDatarozpoczecia() {
        return datarozpoczecia;
    }

    public void setDatarozpoczecia(LocalDate datarozpoczecia) {
        this.datarozpoczecia = datarozpoczecia;
    }

    public static void main (String[] args){

        Student [] Tab ={
                new Student("Jacek","Kowalski","Uniwersytet Warszawski",  20,
                        3.5, Status.ACTIVE, LocalDate.parse("2012-10-01")),
        new Student("Kamil","Nowak","Uniwersytet Warszawski",  22,
                5.0, Status.INACTIVE, LocalDate.parse("2012-10-01")),
        new Student("Jacek","Pietruszka","Uniwersytet Warszawski",  25,
                2.5, Status.INACTIVE, LocalDate.parse("2014-10-01")),
        new Student("Michał","Michalski","Uniwersytet Warszawski",  20,
                3.5, Status.ACTIVE, LocalDate.parse("2011-10-01")),
        new Student("Marcin","Kamilak","Uniwersytet Warszawski",  230,
                4.0, Status.ACTIVE, LocalDate.parse("2015-10-01")),
        new Student("Tomek","Robertowski","Uniwersytet Warszawski",  20,
                5.0, Status.INACTIVE, LocalDate.parse("2012-10-01")),
        new Student("Krzysztof","Twardoń","Uniwersytet Warszawski",  20,
                4.2, Status.ACTIVE, LocalDate.parse("2012-10-01")),
        new Student("Romek","Porczyk","Politechnika Warszawska",  30,
                3.2, Status.INACTIVE, LocalDate.parse("2012-10-01")),
        new Student("Rober","Michnik","Uniwersytet Warszawski",  31,
                3.0, Status.ACTIVE, LocalDate.parse("2012-10-01")),
        new Student("Eryk","Wałęsa","Politechnika Warszawska",  19,
                3.8, Status.INACTIVE, LocalDate.parse("2012-10-01"))

        };
        Object[] Tab2 = Arrays.stream(Tab).filter(e -> e.getStatus() == Status.ACTIVE && e.getWiek() > 30).toArray();


        System.out.println(Arrays.toString(Tab2));

        long ilu = Arrays.stream(Tab).filter(e -> e.getUczelnia().equals("Politechnika Warszawska")).count();
        System.out.println(ilu);

        Predicate<Student> jp = e -> e.getStatus()==Status.ACTIVE;

        double srednia = Arrays.stream(Tab).filter(e -> e.getStatus()==Status.ACTIVE).mapToDouble(e -> e.getSrednia()).sum()
                /Arrays.stream(Tab).filter(jp).count();
        System.out.println(srednia);

        Student x = (Student) Arrays.stream(Tab).sorted((a,b) -> - Double.compare(a.getSrednia(), b.getSrednia())).toArray()[0];
        System.out.println(x);

        MyFunction <Student, String> f = e -> e.getImie() + " " + e.getNazwisko();
        MyPredicate<Student> p = e -> e.getSrednia() > 4.9;
        for(Student s : Tab) {
            if(p.doWork(s))
                System.out.println("Dostałeś stypendium");
            System.out.println(f.doWork(s));

        }
    }
}

