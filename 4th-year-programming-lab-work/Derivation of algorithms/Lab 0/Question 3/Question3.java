/**
    aaron ward - B00079288
    Lab 0 - question 3
    Search the char array for the % character
 */

public class Question3{
    public static void main(String [] args){

        final int N = 7;
        char [] f = new char [N];
        f[0] = '@';
        f[1] = '%';
        f[2] = '6';
        f[3] = '{';
        f[4] = '%';
        f[5] = 'F';
        f[6] = '<';
        
        for(int i = 0; i < f.length; i++){
            if(f[i] == ('%')){
                System.out.println("Found % in index:" + i);
            }
        }
    }
}