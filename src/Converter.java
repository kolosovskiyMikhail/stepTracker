public class Converter {
    double stepToKm = 75;
    double stepToKalor = 50.0;

    double calcKalor(int step) {
        double kalor = (step * stepToKalor)/1000.0;
        return kalor;
    }

    double stepToKm (int step) {
        double stepKm = step * stepToKm / 100000;
        return stepKm;
    }



}
