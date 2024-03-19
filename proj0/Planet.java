
public class Planet {
    private static final double G = 6.67e-11;
    public double xxPos;
    public double yyPos;
    /**x 方向的速度 */
    public double xxVel;
    public double yyVel;
    /**质量 */
    public double mass;
    /**与描述行星的图像相对应的文件名（例如 jupiter.gif） */
    public String imgFileName;


    public Planet(double xP, double yP, double xV,double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;             

    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet otherPlanet){
        double r = 0;
        double dx = 0;
        double dy = 0;
        dx = otherPlanet.xxPos - this.xxPos;
        dy = otherPlanet.yyPos - this.yyPos;
        r = Math.sqrt(dx*dx + dy*dy);
        return r;
    }


    public double calcForceExertedBy(Planet otherPlanet){
        double F = 0;
        double r = this.calcDistance(otherPlanet);
        F = (G * this.mass * otherPlanet.mass)/(r * r);
        
        return F;

    }


    public double calcForceExertedByX(Planet otherPlanet){
        double Fx = 0.0;
        double dx = otherPlanet.xxPos - this.xxPos;
        double r = this.calcDistance(otherPlanet);
        double F = this.calcForceExertedBy(otherPlanet);
        Fx = F*(dx/r);
        return Fx;
    }

    public double calcForceExertedByY(Planet otherPlanet){
        double Fy = 0.0;
        double dy = otherPlanet.yyPos - this.yyPos;
        double r = this.calcDistance(otherPlanet);
        double F = this.calcForceExertedBy(otherPlanet);
        Fy = F*(dy/r);
        return Fy;
    }


    public double calcNetForceExertedByX (Planet[] allPlanets){
        double netFx = 0.0;
        for(int i = 0; i < allPlanets.length; i++){
            if(!this.equals(allPlanets[i])){
                double Fx = this.calcForceExertedByX(allPlanets[i]);
                netFx += Fx;
            }
        }
        return netFx;
    }

    public double calcNetForceExertedByY (Planet[] allPlanets){
        double netFy = 0.0;
        for(int i = 0; i < allPlanets.length; i++){
            if(!this.equals(allPlanets[i])){
                double Fy = this.calcForceExertedByY(allPlanets[i]);
                netFy += Fy;
            }
        }
        return netFy;
    }


    public void update(double dt,double fX,double fY){
        double aX = fX/this.mass;  
        double aY = fY/this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;

        
    }

    public void draw(){
        // System.out.println("img/"+imgFileName);
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }



}
