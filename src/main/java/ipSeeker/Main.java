package ipSeeker;


public class Main {
    public static void main(String[] args) {
        String checkip="10.12.54.100";
        IPSeeker ipSeeker=IPSeeker.getInstance();
        String msg = ipSeeker.getCountry(checkip)+ipSeeker.getArea(checkip);
        System.out.println(msg);
    }
}
