public class NBody {

    // 这里为什么用静态方法??
    private static double readRadius(String filename){
        double radius = 0.0;
        In in = new In(filename);
        in.readInt();
        double secondItemInFile = in.readDouble();
        radius = secondItemInFile;
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int n = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[n];

        double xxPos;
        double yyPos;
        double xxVel;
        double yyVel;
        double mass;
        String img;

        for (int i = 0; i < planets.length; i++) {
            xxPos = in.readDouble(); 
            yyPos = in.readDouble();
            xxVel = in.readDouble();
            yyVel = in.readDouble();
            mass = in.readDouble();
            img = in.readString();
            planets[i] = new Planet(xxPos, yyPos,xxVel, yyVel, mass, img);
            
        }

        return planets;
    }
    
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter command line arguments.");
            System.out.println("e.g. java NBody 157788000.0 25000.0 data/planets.txt");
        }
        
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] planets = NBody.readPlanets(filename);
        
         // init draw panel
         double scale = NBody.readRadius(filename);
         StdDraw.setScale(-scale,scale);
         StdDraw.clear();
           // draw background
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (Planet p: planets) {
            // System.out.println(p.imgFileName);
            p.draw();
        }

         // Enable this to avoid flicking animation
         StdDraw.enableDoubleBuffering();

              // Function my universe in a time span T
        for(double currT = 0; currT != T; currT+=dt){
            // arrays for x- and y-NetForces of each planet
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];

            // update arrays for x- and y-NetForces for all planets
            for(int i = 0; i<planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            // update each planet
            for(int i=0; i<planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // draw background again
            StdDraw.picture(0, 0, "images/starfield.jpg");

            // draw all planets
            for (Planet p: planets) {
                p.draw();
            }

            // draw offscene things to your scene
            StdDraw.show();

            // pause 10 milliseconds
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                        planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                        planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }


}
