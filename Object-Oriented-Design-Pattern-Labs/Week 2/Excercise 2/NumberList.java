public abstract class NumberList{

    protected String [] currentList;
    protected String [] investmentList;
    NumberList(){
        currentList = null;
        investmentList = null;
    }


    public String[] getCurrentList(){
        return currentList;
    }
    public String[] getInvestmentList(){
        return investmentList;
    }


    public void display(){}
}