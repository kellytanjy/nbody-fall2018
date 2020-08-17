public class Body {
    private double myXPos;
    private double myYPos;
    private double myXVel;
    private double myYVel;
    private double myMass;
    private String myFileName;

    public Body(double xp, double yp, double xv, double yv,
                double mass, String filename) {
        myXPos = xp;
        myYPos = yp;
        myXVel = xv;
        myYVel = yv;
        myMass = mass;
        myFileName = filename;
    }

    public Body(Body b) {
        this.myXPos = b.myXPos;
        this.myYPos = b.myYPos;
        this.myXVel = b.myXVel;
        this.myYVel = b.myYVel;
        this.myMass = b.myMass;
        this.myFileName = b.myFileName;
    }

    public double getX() {
        return myXPos;
    }

    public double getY() {
        return myYPos;
    }

    public double getXVel() {
        return myXVel;
    }

    public double getYVel() {
        return myYVel;
    }

    public double getMass() {
        return myMass;
    }

    public String getName() {
        return myFileName;
    }

    public double calcDistance(Body b) {
        return Math.sqrt((Math.pow(myXPos-b.myXPos, 2) + Math.pow(myYPos-b.myYPos, 2)));
    }

    public double calcForceExertedBy(Body p) {
        double G = 6.67e-11;
        return G*myMass*p.myMass/Math.pow(calcDistance(p),2);
    }

    public double calcForceExertedByX(Body p) {
        return calcForceExertedBy(p)*(p.myXPos-myXPos)/calcDistance(p);
    }

    public double calcForceExertedByY(Body p) {
        return calcForceExertedBy(p)*(p.myYPos-myYPos)/calcDistance(p);
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double netforceX = 0;
        for (Body b: bodies) {
            if (!b.equals(this)) {
                netforceX += calcForceExertedByX(b);
            }
        }
        return netforceX;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double netforceY = 0;
        for (Body b: bodies) {
            if (!b.equals(this)) {
                netforceY += calcForceExertedByY(b);
            }
        }
        return netforceY;
    }

    public void update(double deltaT, double xforce, double yforce) {
        double ax = xforce/myMass;
        double ay = yforce/myMass;
        double nvx = myXVel + deltaT*ax;
        double nvy = myYVel + deltaT*ay;
        double nx = myXPos + deltaT*nvx;
        double ny = myYPos + deltaT*nvy;
        myXPos = nx;
        myYPos = ny;
        myXVel = nvx;
        myYVel = nvy;
    }

    public void draw() {
        StdDraw.picture(myXPos,myYPos, "images/"+myFileName);
    }
}

