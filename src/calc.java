public class calc {
    public static void main(String[] args) {
        boolean a=false;
        boolean b= true;
        int i=1;
        System.out.println(f(a, b, i)+"   f2:  "+f2(a, b, i));
    }
    public static boolean f(boolean a , boolean b, int i){
        if(a&& (i>0)){
        return b;
        }
        else if (a&& i<=0){
        return false;
        }
        else if(a || b){
        return a;
        }
        else{
        return (i>0);
        }
        }
        public static boolean f2(boolean a , boolean b, int i){
            return ((a&&(i>0))&&b)|| ((a||b)&&a)||(i>0);
        }
    static  public void gcdExtended(long a, long b)
    {
        long x = 0, y = 1, lastx = 1, lasty = 0, temp;
        while (b != 0)
        {
            long q = a / b;
            long r = a % b;
  
            a = b;
            b = r;
  
            temp = x;
            x = lastx - q * x;
            lastx = temp;
  
            temp = y;
            y = lasty - q * y;
            lasty = temp;            
        }
      System.out.println("GCD "+a+" and its Roots  x : "+ lastx +" y :"+ lasty);
 
    }
}
