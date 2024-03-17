public class NBody {

    // 这里为什么用静态类??
    public static double readRadius(String filename){
        double radius = 0.0;
        In in = new In(filename);
        int firstItemInFile = in.readInt();
        double secondItemInFile = in.readDouble();
        radius = secondItemInFile;
        return radius;
    }
    




}
