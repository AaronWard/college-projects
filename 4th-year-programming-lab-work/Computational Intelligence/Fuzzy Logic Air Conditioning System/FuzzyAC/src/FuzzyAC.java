import com.fuzzylite.*;
import com.fuzzylite.defuzzifier.*;
import com.fuzzylite.norm.s.*;
import com.fuzzylite.norm.t.*;
import com.fuzzylite.rule.*;
import com.fuzzylite.term.*;
import com.fuzzylite.variable.*;

/**
 *  A fuzzy logic controller for an air conditioning system
 * @author aaron
 *
 */
class FuzzyAC{

    // Define our FuzzyLite objects
    private Engine engine;
    private InputVariable inputVariable1;
    private InputVariable inputVariable2;
    private OutputVariable outputVariable;
    private RuleBlock ruleBlock;

    public FuzzyAC(){

        // Create the Fuzzy Logic Engine
        engine = new Engine();
        engine.setName("FuzzyAC");

        // Setup the room_temp linguistic variable
        inputVariable1 = new InputVariable();
        inputVariable1.setEnabled(true);
        inputVariable1.setName("temperature");
        inputVariable1.setRange(-10.00, 40.00);

        // Add each term for the Linguistic variable
        // NOTE: look at the lab sheet to see where the values used below
        // come from.
        inputVariable1.addTerm(new Trapezoid("toocold", -10, -10, 0, 5));
        inputVariable1.addTerm(new Trapezoid("cold", 0, 5, 10, 15));
        inputVariable1.addTerm(new Trapezoid("warm", 10, 15, 20, 25));
        inputVariable1.addTerm(new Trapezoid("hot", 20, 25, 30, 35));
        inputVariable1.addTerm(new Trapezoid("toohot", 30, 35, 40, 40));
        // Add the variable to the fuzzy engine
        engine.addInputVariable(inputVariable1);

        // Setup the target_temp linguistic variable
        inputVariable2 = new InputVariable();
        inputVariable2.setEnabled(true);
        inputVariable2.setName("target");
        inputVariable2.setRange(15, 35);
        // Add each term for the Linguistic variable
        // TODO: create the appropriate terms for inputVariable2 - see lab sheet
        inputVariable2.addTerm(new Trapezoid("cold", 15, 15, 20, 25));
        inputVariable2.addTerm(new Triangle("warm", 20, 25, 30));
        inputVariable2.addTerm(new Trapezoid("hot", 25, 30, 35, 35));
        // Add the variable to the fuzzy engine
        engine.addInputVariable(inputVariable2);

        // Setup the output command variable
        outputVariable = new OutputVariable();
        outputVariable.setEnabled(true);
        outputVariable.setName("command");
        outputVariable.setRange(-1.000, 1.000);
        // How should the rules be accumulated
        outputVariable.fuzzyOutput().setAccumulation(new Maximum());
        // How will the output be Defuzzified?
        outputVariable.setDefuzzifier(new Centroid(200));
        outputVariable.setDefaultValue(Double.NaN);
        outputVariable.setLockValidOutput(false);
        outputVariable.setLockOutputRange(false);
        // Add each term for the Linguistic variable
        // TODO: create the appropriate terms for outputVariable - see lab sheet
        outputVariable.addTerm(new Triangle("cool", 0.00, 0.25, 0.50));
        outputVariable.addTerm(new Triangle("nochange", 0.25, 0.50, 0.75));
        outputVariable.addTerm(new Triangle("heat", 0.50, 0.75, 1.00));
        // Add the variable to the fuzzy engine
        engine.addOutputVariable(outputVariable);

        // Setup the inference rules
        ruleBlock = new RuleBlock();
        ruleBlock.setEnabled(true);
        ruleBlock.setName("Rule Block");
        // Set up fuzzy functions for AND, OR and NOT
        ruleBlock.setConjunction(new Minimum());
        ruleBlock.setDisjunction(new Maximum());
        ruleBlock.setActivation(new Minimum());
        // Add the rules as follows
        ruleBlock.addRule(Rule.parse("if (temperature is toocold) then command is heat", engine));
        ruleBlock.addRule(Rule.parse("if (temperature is toohot) then command is cool", engine));

        // TODO - Add the rest of the rules - see lab sheet
        ruleBlock.addRule(Rule.parse(" if (temperature is toocold or temperature is cold) and (target is warm) then command is heat", engine));
        ruleBlock.addRule(Rule.parse(" if (temperature is toohot or temperature is hot) and (target is warm) then command is cool", engine));
        ruleBlock.addRule(Rule.parse("if (temperature is warm) and (target is warm) then command is nochange", engine));
        ruleBlock.addRule(Rule.parse(" if (temperature is cold) and (target is cold) then command is nochange ", engine));
        ruleBlock.addRule(Rule.parse("if (temperature is hot) and (target is hot) then command is nochange", engine));
        // Add the rule block to the fuzzy engine
        engine.addRuleBlock(ruleBlock);

    }

    // This method will setup the input variables
    // and run them through the fuzzy engine
    public double evaluate(double ct, double tt){
        // Load the input variables
        inputVariable1.setInputValue(ct);
        inputVariable2.setInputValue(tt);
        // Run the engine
        engine.process();

        // Defuzzify the output value and return it
        return (double)(outputVariable.defuzzify());
    }

    public static void main(String args[]){

        // Create a new FuzzyAC controller object
        FuzzyAC fac = new FuzzyAC();

        // NOTE:
        // Current temp range from -10 to +40
        // Target temp range from +15 to +35

        // TEST CASE - Need to heat the room
        // It's cold, current temp is 7 degrees
        double current_temp = 7.0;
        // We need to heat the room up so set the target temp to 25 degrees
        double target_temp = 25.0;
        // Run the system
        double result = fac.evaluate(current_temp, target_temp);
        System.out.printf("Result: AC command value is = %.4f (-1 to +1) (cool to heat)\n",result);

        // TEST CASE - Need to cool the room
        // It's hot, current temp is 38 degrees
        current_temp = 38.0;
        // We need to heat the room up so set the target temp to 25 degrees
        target_temp = 16.8;
        // Run the system
        result = fac.evaluate(current_temp, target_temp);
        System.out.printf("Result: AC command value is = %.4f (-1 to +1) (cool to heat)\n",result);

        // TEST CASE - No change
        // It's just nice
        current_temp = 18.0;
        // We need to heat the room up so set the target temp to 25 degrees
        target_temp = 22.8;
        // Run the system
        result = fac.evaluate(current_temp, target_temp);
        System.out.printf("Result: AC command value is = %.4f (-1 to +1) (cool to heat)\n",result);

    }
}